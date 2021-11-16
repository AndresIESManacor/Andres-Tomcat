package com.example.andrestomcat;

import com.example.andrestomcat.AnteriorProject.AccesoDatos.CRUDProduct;
import com.example.andrestomcat.AnteriorProject.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ServletList", value = "/ServletList")
public class ServletList extends HttpServlet {
    CRUDProduct crud = new CRUDProduct();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quantity = 0;
        try {
            quantity = Integer.parseInt(request.getParameter("quantity"));
        } catch (Exception ignored) {
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // send HTML page to client
        out.println("<html>");
        out.println("<head><title>A Test Servlet</title></head>");
        out.println("<body>");
        out.print("<p>Quantity Selected: ");
        out.print(" "+quantity+" </p>");
        List<Product> products = crud.readAll();
        String[] productQuantity = new String[quantity];
        for (int x = 0; x < productQuantity.length; x++) {
            out.println("<p>" + products.get(x) + "</p>");
        }
        out.println("</body></html>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
