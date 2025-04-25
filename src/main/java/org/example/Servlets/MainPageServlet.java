package org.example.Servlets;

import org.example.dto.BurgerDto;
import org.example.dto.BurgerWithImage;
import org.example.service.BurgerService;
import org.example.service.ImageService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/")
public class MainPageServlet extends HttpServlet {
    private  BurgerService burgerService;
    private ImageService imageService;

    @Override
    public void init() throws ServletException {
        burgerService = (BurgerService) getServletContext().getAttribute("burgerService");
        imageService = (ImageService) getServletContext().getAttribute("imageService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BurgerDto> burgersDto = burgerService.findAll();
        List<BurgerWithImage> burgerWithImages = new ArrayList<>();
        for (BurgerDto burgerDto : burgersDto) {
            try {
                burgerWithImages.add(new BurgerWithImage(burgerDto, imageService.getImageByBurger(burgerDto.getId())));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        System.out.println(burgerWithImages);
        req.setAttribute("burgerWithImages", burgerWithImages);
        req.getRequestDispatcher("/jsp/mainPage.jsp").forward(req, resp);
    }
}
