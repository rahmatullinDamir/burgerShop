package org.example.Servlets;

import org.example.Models.Image;
import org.example.service.ImageService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/uploaded/files")
public class ImageServlet extends HttpServlet {

    private ImageService imageService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.imageService = (ImageService) config.getServletContext().getAttribute("imageService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String fileId = req.getParameter("id");
        Image image = imageService.getFileInfo(Long.parseLong(fileId));
        response.setContentType(image.getType());
        response.setContentLength(image.getSize().intValue());
        response.setHeader("Content-Disposition", "filename=\"" + image.getOriginalFileName() + "\"");
        imageService.writeFileFromStorage(Long.parseLong(fileId), response.getOutputStream());
        response.flushBuffer();
    }
}