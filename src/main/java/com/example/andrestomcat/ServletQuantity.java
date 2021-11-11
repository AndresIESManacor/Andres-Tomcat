package com.example.andrestomcat;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletQuantity", value = "/ServletQuantity")
public class ServletQuantity extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = "not Selected";
        String password = "not Selected";
        try {
            user = request.getParameter("user");
            password = request.getParameter("password");
        } catch (Exception ignored) {
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // send HTML page to client
        out.println("<html>");
        out.println("<head><title>A Test Servlet</title></head>");
        out.println("<body>");
        out.print("<p>Your user is: ");
        out.print(" "+user+" with password ");
        out.println(password+"</p>");
        out.println("<a href='Quantity.html'>Go to Quantity</a>");
        out.println("</body></html>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
