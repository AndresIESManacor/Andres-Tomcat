package com.example.andrestomcat;

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
        String name = "not Selected";
        String descript = "not Selected";
        String price = "not Selected";
        try {
            name = request.getParameter("nameProduct");
            descript = request.getParameter("descProduct");
            price = request.getParameter("priceProduct");
        } catch (Exception ignored) {
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // send HTML page to client
        out.println("<html>");
        out.println("<head><title>A Test Servlet</title></head>");
        out.println("<body>");
        out.print("<p>"+name+descript+price+"</p>");
        out.println("</body>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
