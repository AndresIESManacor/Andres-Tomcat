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
        String readNameProduct = null;
        String deleteNameProduct = null;
        String buttonUpdate = null;
        String buttonReadAll = null;
        String buttonreadName = null;
        String buttondeleteAllTable = null;
        String buttondeleteName = null;
        try {
            name = request.getParameter("nameProduct");
            descript = request.getParameter("descProduct");
            price = request.getParameter("priceProduct");
            submit = request.getParameter("submit");
            deleteNameProduct = request.getParameter("deleteNameProduct");
            readNameProduct = request.getParameter("readNameProduct");
            buttonUpdate = request.getParameter("update");
            buttonReadAll = request.getParameter("readAll");
            buttonreadName = request.getParameter("readName");
            buttondeleteAllTable = request.getParameter("deleteAllTable");
            buttondeleteName = request.getParameter("buttondeleteName");
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
            assert name != null;
            if (name.length()!=0 && Objects.requireNonNull(descript).length()!=0 && Objects.requireNonNull(price).length()!=0) {
                assert price != null;
                business.insert(new Product(name, descript, Double.parseDouble(price)));
                out.print("<p>Product added!! " + name + descript + price + "</p>");
            }
            out.println("</body>");
        }

        if (isNotNull(buttonUpdate)) {
            out.println("<body>");
            assert name != null;
            if (name.length()!=0 && Objects.requireNonNull(descript).length()!=0 && Objects.requireNonNull(price).length()!=0) {
                business.updateProduct(1, out, new Product(1,name,descript,Double.parseDouble(price)));
            } else {
                out.print("<p style='text-align:center;'>You dont write the product correctly</p>");
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
        if (isNotNull(buttondeleteName)) {
            out.println("<body>");
            if (isNotNull(deleteNameProduct)) {
                business.deleteByID(crudProduct.readByName(deleteNameProduct).getIdPro(), out);
            } else {
                out.print("<p>Name dont selected</p>");
            }
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
