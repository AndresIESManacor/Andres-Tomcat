package com.example.andrestomcat;

import com.example.andrestomcat.AnteriorProject.AccesoDatos.CRUDProduct;
import com.example.andrestomcat.AnteriorProject.Business;
import com.example.andrestomcat.AnteriorProject.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet(name = "ServletDataBase", value = "/ServletDataBase")
public class ServletDataBase extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        CRUDProduct crudProduct = new CRUDProduct();

        Business business = new Business();

        String name = null;
        String descript = null;
        String price = null;
        String submit = null;
        String idUpdate = null;
        String readNameProduct = null;
        String deleteidProduct = null;
        String buttonUpdate = null;
        String buttonReadAll = null;
        String buttonreadName = null;
        String buttondeleteAllTable = null;
        String buttondeleteID = null;
        try {
            name = request.getParameter("nameProduct");
            descript = request.getParameter("descProduct");
            price = request.getParameter("priceProduct");
            submit = request.getParameter("submit");
            idUpdate = request.getParameter("idUpdate");
            readNameProduct = request.getParameter("readNameProduct");
            deleteidProduct = request.getParameter("deleteIDProduct");
            buttonUpdate = request.getParameter("update");
            buttonReadAll = request.getParameter("readAll");
            buttonreadName = request.getParameter("readName");
            buttondeleteAllTable = request.getParameter("deleteAllTable");
            buttondeleteID = request.getParameter("buttonDeleteId");
        } catch (Exception ignored) {
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //BUTTONS FUNTION
        if (isNotNull(submit)) {
            // send HTML page to client
            out.println("<html>");
            out.println("<head><title>A Test Servlet</title></head>");
            out.println("<body>");
            if (Objects.requireNonNull(name).length()!=0 && Objects.requireNonNull(descript).length()!=0 && Objects.requireNonNull(price).length()!=0) {
                business.insert(new Product(name, descript, Double.parseDouble(price)));
                out.print("<p>Product added!! " + name + descript + price + "</p>");
            }
            out.println("</body>");
        }

        if (isNotNull(buttonUpdate)) {
            out.println("<body>");
            if (isNotNull(idUpdate)) {
                out.print("<p style='text-align:center;'>You dont selected a id on update</p>");
            } else {
                if (Objects.requireNonNull(name).length() != 0 && Objects.requireNonNull(descript).length() != 0 && Objects.requireNonNull(price).length() != 0) {
                    business.updateProduct(Integer.parseInt(idUpdate), out, new Product(Integer.parseInt(idUpdate), name, descript, Double.parseDouble(price)));
                } else {
                    out.print("<p style='text-align:center;'>You dont write the product correctly</p>");
                }
            }
            out.println("</body>");
        }
        if (isNotNull(buttonReadAll)) {
            out.println("<body>");
            out.println("<h1 style='text-align:center'>LIST OF PRODUCTS</h1>");
            business.listProduct(out);
            out.println("</body>");
        }
        if (isNotNull(buttonreadName)) {
            out.println("<body>");
            if (isNotNull(readNameProduct)) {
                business.searchProduct(readNameProduct,out);
            } else {
                out.print("<p>Name dont selected</p>");
            }
            out.println("</body>");
        }
        if (isNotNull(buttondeleteID)) {
            out.println("<body>");
            assert deleteidProduct != null;
            business.deleteByID(Integer.parseInt(deleteidProduct), out);
            out.println("</body>");
        }
        if (isNotNull(buttondeleteAllTable)) {
            out.println("<body>");
            business.restartCataleg();
            out.println("</body>");
        }
    }
    public Boolean isNotNull(String name) {
        return name != null;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
