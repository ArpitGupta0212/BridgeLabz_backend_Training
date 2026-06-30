package com.greet.servlet;

import com.greet.model.Greeting;
import com.greet.model.User;
import com.greet.service.GreetingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 20
)
public class GreetingServlet extends HttpServlet {

    private GreetingService greetingService;

    @Override
    public void init() throws ServletException {

        var context = WebApplicationContextUtils
                .getRequiredWebApplicationContext(getServletContext());

        greetingService = context.getBean(GreetingService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI()
                .substring(request.getContextPath().length());

        switch (path) {

            case "/greetings":

                List<Greeting> greetings =
                        greetingService.getAllGreetings();

                request.setAttribute("greetings", greetings);

                request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp")
                        .forward(request, response);

                break;

            case "/greetings/new":

                request.setAttribute("title", "Create Greeting");
                request.setAttribute("actionUrl", "new");
                request.setAttribute("greeting", new Greeting());

                request.getRequestDispatcher("/WEB-INF/views/greeting-form.jsp")
                        .forward(request, response);

                break;

            case "/greetings/edit":

                int id = Integer.parseInt(request.getParameter("id"));

                Greeting greeting =
                        greetingService.getGreetingById(id);

                request.setAttribute("title", "Edit Greeting");
                request.setAttribute("actionUrl", "edit?id=" + id);
                request.setAttribute("greeting", greeting);

                request.getRequestDispatcher("/WEB-INF/views/greeting-form.jsp")
                        .forward(request, response);

                break;

            case "/greetings/delete":

                greetingService.deleteGreeting(
                        Integer.parseInt(request.getParameter("id"))
                );

                response.sendRedirect(
                        request.getContextPath() + "/greetings"
                );

                break;

            default:

                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI()
                .substring(request.getContextPath().length());

        User currentUser =
                (User) request.getSession()
                        .getAttribute("currentUser");

        switch (path) {

            case "/greetings/new":

                createGreeting(request, currentUser);

                response.sendRedirect(
                        request.getContextPath() + "/greetings"
                );

                break;

            case "/greetings/edit":

                updateGreeting(request);

                response.sendRedirect(
                        request.getContextPath() + "/greetings"
                );

                break;
        }
    }

    private void createGreeting(HttpServletRequest request,
                                User currentUser)
            throws IOException, ServletException {

        Greeting greeting = new Greeting();

        greeting.setMessage(request.getParameter("message"));

        Part part = request.getPart("imageFile");

        if (part != null && part.getSize() > 0) {

            greeting.setImagePath(
                    saveUploadedFile(request, part)
            );
        }

        greeting.setCreatedById(currentUser.getId());

        greetingService.createGreeting(greeting);
    }
    private void updateGreeting(HttpServletRequest request)
            throws IOException, ServletException {

        Greeting greeting = new Greeting();

        greeting.setId(
                Integer.parseInt(request.getParameter("id"))
        );

        greeting.setMessage(request.getParameter("message"));

        String existingImage =
                request.getParameter("existingImagePath");

        Part part = request.getPart("imageFile");

        if (part != null && part.getSize() > 0) {

            greeting.setImagePath(
                    saveUploadedFile(request, part)
            );

            if (existingImage != null &&
                    !existingImage.isBlank()) {

                deleteFile(existingImage);
            }

        } else {

            greeting.setImagePath(existingImage);

        }

        greetingService.updateGreeting(greeting);

    }

    private String saveUploadedFile(HttpServletRequest request,
                                    Part part)
            throws IOException {

        String fileName = Paths.get(part.getSubmittedFileName())
                .getFileName()
                .toString();

        String extension = "";

        int index = fileName.lastIndexOf(".");

        if (index != -1) {
            extension = fileName.substring(index);
        }

        String uniqueName = UUID.randomUUID() + extension;

        String uploadPath =
                getServletContext().getRealPath("/uploads");

        File folder = new File(uploadPath);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        part.write(uploadPath + File.separator + uniqueName);

        return "/uploads/" + uniqueName;
    }

    private void deleteFile(String imagePath) {

        if (imagePath == null || imagePath.isBlank()) {
            return;
        }

        String uploadPath =
                getServletContext().getRealPath("/uploads");

        File file = new File(
                uploadPath +
                        File.separator +
                        imagePath.replace("/uploads/", "")
        );

        if (file.exists()) {
            file.delete();
        }
    }
}