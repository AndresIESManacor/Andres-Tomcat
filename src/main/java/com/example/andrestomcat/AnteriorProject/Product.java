package com.example.andrestomcat.AnteriorProject;

public class Product {

    private int idPro;
    private String namePro;
    private String descriptPro;
    private double pricePro;

    public Product (int idPro, String namePro, String descriptPro, double pricePro) {
        this.idPro = idPro;
        this.namePro =  namePro;
        this.descriptPro = descriptPro;
        this.pricePro = pricePro;
    }
    public Product (String namePro, String descriptPro, double pricePro) {
        this.namePro =  namePro;
        this.descriptPro = descriptPro;
        this.pricePro = pricePro;
    }
    public Product () {
    }

    public Product (String namePro) {
        this.namePro = namePro;
    }

    //GETTERS

    public int getIdPro() {
        return idPro;
    }

    public String getNamePro() {
        return namePro;
    }

    public String getDescriptPro() {
        return descriptPro;
    }

    public double getPricePro() {
        return pricePro;
    }

    //SETTERS


    public void setIdPro(int idPro) {
        this.idPro = idPro;
    }


    public void setNamePro(String namePro) {
        this.namePro = namePro;
    }

    public void setDescriptPro(String descriptPro) {
        this.descriptPro = descriptPro;
    }

    public void setPricePro(double pricePro) {
        this.pricePro = pricePro;
    }

    @Override
    public String toString() {
        return "Product (" +
                "idPro= " + idPro +
                ", namePro= '" + namePro + '\'' +
                ", descriptPro= '" + descriptPro + '\'' +
                ", pricePro= " + pricePro +
                ')';
    }

    public static boolean isString(String string, int length){
        return string.length() <= length;
    }
    
    public static double isDouble(String string){
        return Double.parseDouble(string);
    }
}
