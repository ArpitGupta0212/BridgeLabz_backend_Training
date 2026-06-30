package com.greet.servlet;

import com.greet.model.Greeting;
import com.greet.service.GreetingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

public class GreetingServlet extends HttpServlet {

    private GreetingService greetingService;

    @Override
    public void init() throws ServletException {

        ApplicationContext context =
                WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());

        greetingService = context.getBean("greetingService", GreetingService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");

        Greeting greeting = greetingService.greet(name);

        request.setAttribute("greeting", greeting);

        request.getRequestDispatcher("greeting.jsp")
                .forward(request, response);
    }
}