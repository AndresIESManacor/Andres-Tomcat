package com.example.andrestomcat;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class CRUD {


    public String login = "andres5";
    public String password = "andres12345A_";
    public String database = "productos";
    public String table = "producto";
    public String serverAddress = "localhost";
    public int puerto = 3306;

    public Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://"+serverAddress+":"+puerto+"/"+database+"?allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC", login, password);
    }
    public Connection connectionUserPasswordOnly(){
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", login);
        connectionProps.put("password", password);
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(
                    "jdbc:" + "mysql" + "://" +
                            serverAddress +
                            ":" + "3306" + "/"+
                            "?allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    connectionProps);
        } catch (SQLException ex) {
            // handle any errors
            ex.printStackTrace();
        } catch (Exception ex) {
            // handle any errors
            ex.printStackTrace();
        }
        return conn;
    }

    public String showTables(){
        Connection conn;
        ArrayList<String> table = new ArrayList<>();
        try {
            conn = connection();
            if (conn != null) {
                System.out.println("Connexion a base de dates ... Ok");
                Statement sta = conn.createStatement();
                sta.executeQuery("USE "+database);
                ResultSet rs = sta.executeQuery("Show tables");
                while(rs.next()) {
                    table.add(rs.getString(1));
                }
                conn.close();
                String text = "";
                for (int i = 0; i < table.size(); i++) {
                    text += table.get(i)+"|";
                }
                return text;
            }
        } catch(SQLException ex) {
            return ex.getMessage();
        }
        return "HAVE_NOTHING_IN_THERE";
    }

    public String tableExist(){
        Connection conn;
        try {
            conn = connection();
            if (conn != null) {
                System.out.println("Connexion a base de dates ... Ok");
                Statement sta = conn.createStatement();
                sta.executeQuery("SELECT * FROM " + table + ";");
                conn.close();
                return "Ok";
            }
        } catch(SQLException ex) {
            return ex.getMessage();
        }
        return "HAVE_NOTHING_IN_THERE";
    }

    public String databaseExist(){
        Connection conn;
        try {
            conn = connection();
            if (conn != null) {
                System.out.println("Connexion a base de dates ... Ok");
                Statement sta = conn.createStatement();
                sta.executeQuery("USE "+database);
                conn.close();
                return "Ok";
            }
        } catch (SQLException ex) {
            return ex.getMessage();
        }
        return "HAVE_NOTHING_IN_THERE";
    }

    public String showDatabase(){
        Connection conn;
        ArrayList<String> table = new ArrayList<>();
        try {
            conn = connectionUserPasswordOnly();
            if (conn != null) {
                System.out.println("Connexion a base de dates ... Ok");
                Statement sta = conn.createStatement();
                ResultSet resultSet = sta.executeQuery("SHOW DATABASES;");
                if (sta.execute("SHOW DATABASES;")) {
                    resultSet = sta.getResultSet();
                }
                while(resultSet.next()) {
                    System.out.println(resultSet.getString("database"));
                    table.add(resultSet.getString("Database"));
                }
                conn.close();
                resultSet.close();
                String text = "";
                for (int i = 0; i < table.size(); i++) {
                    text += table.get(i)+"|";
                }
                return text;
            }
        } catch(SQLException ex) {
            return ex.getMessage();
        }
        return "HAVE_NOTHING_IN_THERE";
    }

    public void insert(String[] object) {
        Connection conn = null;
        String mysqlText = "";
        for (int i = 0; i < object.length; i++) {
            if (i==object.length-1) {
                mysqlText = mysqlText +"'"+ object[i]+"'";
            } else {
                mysqlText = mysqlText +"'"+ object[i] +"',";
            }
        }
        System.out.println(mysqlText);
        try {
            conn = connection();
            if (conn != null) {
                    System.out.println("Connexion a base de dates ... Ok");
                    Statement sta = conn.createStatement();
                    sta.executeUpdate("INSERT INTO " + table + " VALUES (" +mysqlText+ ")");
                    conn.close();
            }
        } catch(SQLException ex) {
           JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }

    public String[] readAll(){
        Connection conn = null;
        String[] columns = getColumnName();
        ArrayList<String> resultText = new ArrayList<>();
        System.out.println(database);
        System.out.println(table);

        try {
            conn = connection();

            if (conn != null) {
                System.out.println("Conexión a base de datos ... Ok");
                for (int i = 0; i < columns.length; i++) {
                    Statement sta = conn.createStatement();
                    ResultSet result = sta.executeQuery("SELECT "+columns[0]+", "+ columns[i] + " FROM " + table+" ORDER BY "+columns[0]);
                    while (result.next()) {
                        resultText.add(result.getString(columns[i]));
                    }
                }
                conn.close();
            }
        } catch(SQLException ex) {
            System.out.println(ex);
        }
        int divide = resultText.size()/getColumnName().length;
        String[] object = new String[divide]; int y = 0;
        for (int x = 0; x < resultText.size(); x++) {
            if (object[y] != null) {
                object[y] += resultText.get(x)+",";
            } else {
                object[y] = resultText.get(x)+",";
            }
            y++;
            if (y%divide==0){
                y = 0;
            }
        }
        for (int i = 0; i < object.length; i++) {
            System.out.println(object[i]);
        }
        return object;
    }


    public void updateById(Object[] object, int id){
        Connection conn = null;
        String mysqlText = " ";
        String[] columns = getColumnName();
        for (int i = 0;i < columns.length; i++) {
            if (i == columns.length - 1) {
                mysqlText = mysqlText + columns[i] + "='" + object[i] + "'";
            } else {
                mysqlText = mysqlText + columns[i] + "='" + object[i] + "', ";
            }
        }
        String where = " where "+getColumnName()[0]+"='"+id+"';";

        try {
            conn = connection();

            if (conn != null) {
                System.out.println("Connexion a base de dates ... Ok");
                String query = "update "+table+ " SET"+mysqlText+where;
                System.out.println("update "+table+ " SET"+mysqlText+where);
                Statement sta = conn.createStatement();
                sta.executeUpdate(query);
                conn.close();
            }
        } catch(SQLException ex) {
            System.out.println(ex);
        }
    }

    public void deleteById(int id){
        Connection conn = null;
        String[] columns = getColumnName();

        try {
            conn = connection();

            if (conn != null) {
                System.out.println("Connexion a base de dates ... Ok");
                String query = "delete from "+table+" where "+columns[0]+"= '"+id+"'";
                Statement sta = conn.createStatement();
                sta.executeUpdate(query);
                conn.close();
            }
        } catch(SQLException ex) {
            System.out.println(ex);
        }
    }

    public void deleteAllINFOonTABLE(){
        try {
            Connection conn = connection();

            if (conn != null) {
                System.out.println("Connexion a base de dates ... Ok");
                Statement sta = conn.createStatement();
                sta.executeUpdate("DELETE FROM "+table);
                conn.close();
            }
        } catch(SQLException ex) {
            System.out.println(ex);
        }
    }
    public String[] getColumnName(){
        String[] finalcolName = new String[0];
        try {
            Connection conn = connection();

            if (conn != null) {
                System.out.println("Connexion a base de dates ... Ok");
                Statement sta = conn.createStatement();
                ResultSet resultSet = sta.executeQuery("SELECT * FROM "+table);
                ResultSetMetaData md = (ResultSetMetaData) resultSet.getMetaData();
                int counter = md.getColumnCount();
                String colName[] = new String[counter];
                for (int loop = 1; loop <= counter; loop++) {
                    colName[loop-1] = md.getColumnLabel(loop);
                }
                finalcolName = colName;
                conn.close();
            }
        } catch(SQLException ex) {
            System.out.println(ex);
        }
        return finalcolName;
    }
}
