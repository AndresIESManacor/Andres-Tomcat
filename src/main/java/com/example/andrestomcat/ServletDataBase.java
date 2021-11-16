package com.example.andrestomcat;

import com.example.andrestomcat.AnteriorProject.Business;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletDataBase", value = "/ServletDataBase")
public class ServletDataBase extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

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

        // DONT SELECTED AND CLICK ANYTHING
        if (isNotNull(name) && isNotNull(descript) && isNotNull(price)) {
            out.println("<html>");
            out.println("<head><title>A Test Servlet</title></head>");
            out.println("<body>");
            out.print("<p style='text-align:center;'>You dont selected anything yet</p>");
            out.println("</body>");
        }

        //BUTTONS FUNTION
        if (isNotNull(submit)) {
            // send HTML page to client
            out.println("<html>");
            out.println("<head><title>A Test Servlet</title></head>");
            out.println("<body>");
            out.print("<p>" + name + descript + price + "</p>");
            out.println("</body>");
        }

        if (isNotNull(buttonUpdate)) {
            if (isNotNull(name) && isNotNull(descript) && isNotNull(price)) {
                out.println("<body>");
                out.print("<p>" + buttonUpdate + "</p>");
            } else {
                out.println("<html>");
                out.println("<head><title>A Test Servlet</title></head>");
                out.println("<body>");
                out.print("<p style='text-align:center;'>You dont write the product correctly</p>");
                out.print("<p style='text-align:center;'>"+buttonUpdate+"</p>");
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
                business.deleteName(deleteNameProduct, out);
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
