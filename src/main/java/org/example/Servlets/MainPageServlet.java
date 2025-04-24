package org.example.Servlets;

import org.example.Listener.CustomServletContextListener;
import org.example.service.BurgerService;
import org.example.service.ImageService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class MainPageServlet extends HttpServlet {
    private  BurgerService burgerService;
    private ImageService imageService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void init() throws ServletException {
        burgerService = (BurgerService) getServletContext().getAttribute("burgerService");
        imageService = (ImageService) getServletContext().getAttribute("imageService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("burgers", burgerService.findAll());
        req.getRequestDispatcher("/jsp/mainPage.jsp").forward(req, resp);
    }
}
