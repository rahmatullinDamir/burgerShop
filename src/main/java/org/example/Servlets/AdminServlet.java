package org.example.Servlets;


import org.example.Models.Burger;
import org.example.dto.BurgerDto;
import org.example.dto.BurgerWithImage;
import org.example.service.BurgerService;
import org.example.service.ImageService;
import org.example.service.impl.BurgerServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin")
@MultipartConfig
public class AdminServlet extends HttpServlet {
    BurgerService burgerService;
    ImageService imageService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        burgerService = (BurgerService) config.getServletContext().getAttribute("burgerService");
        imageService = (ImageService) config.getServletContext().getAttribute("imageService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BurgerDto> burgerDtos = burgerService.findAll();
        List<BurgerWithImage> burgerWithImages = new ArrayList<>();
        for (BurgerDto burgerDto : burgerDtos) {
            System.out.println(burgerDto);
            try {
                burgerWithImages.add(new BurgerWithImage(burgerDto, imageService.getImageByBurger(burgerDto.getId())));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        req.setAttribute("burgerWithImages", burgerWithImages);
        req.getRequestDispatcher("jsp/admin.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("text");
        int price = Integer.parseInt(req.getParameter("price"));

        Part part = req.getPart("image");
        try {
            burgerService.save(BurgerDto.builder()
                    .name(name)
                    .description(description)
                    .price(price)
                    .build());
            BurgerDto burgerDto = burgerService.findByName(name);
            imageService.saveFileToStorage(part.getInputStream(), part.getSubmittedFileName(), part.getContentType(), burgerDto.getId(), part.getSize());
            resp.sendRedirect("/admin");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
