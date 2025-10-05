package com.example.laborator_1;

import java.io.IOException;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "controller", value = "/controller")
public class Controller extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Controller.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String parameter = request.getParameter("page");

        logger.info("Used HTTP method: " + request.getMethod());
        logger.info("Client IP: " + request.getRemoteAddr());
        logger.info("User Agent: " + request.getHeader("User-Agent"));
        logger.info("Languages: " + request.getHeader("Accept-Language"));
        logger.info("Page parameter: " + parameter);

        String plain = request.getHeader("Text-Flag");
        if ("true".equals(plain)) {
            response.setContentType("text/plain");
            response.getWriter().println(parameter);
            return;
        }

        if ("1".equals(parameter)) {
            request.getRequestDispatcher("page1.html").forward(request, response);
        } else if ("2".equals(parameter)) {
            request.getRequestDispatcher("page2.html").forward(request, response);
        } else {
            response.getWriter().println("Invalid selection.");
        }
    }
}
