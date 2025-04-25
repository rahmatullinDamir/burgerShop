package org.example.Servlets;

import org.example.dto.BurgerDto;
import org.example.dto.BurgerWithImage;
import org.example.dto.OrderDto;
import org.example.service.BurgerService;
import org.example.service.ImageService;
import org.example.service.OrderService;
import org.json.JSONException;
import org.json.JSONObject;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/")
public class MainPageServlet extends HttpServlet {
    private BurgerService burgerService;
    private ImageService imageService;

    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        burgerService = (BurgerService) getServletContext().getAttribute("burgerService");
        imageService = (ImageService) getServletContext().getAttribute("imageService");
        orderService = (OrderService) getServletContext().getAttribute("orderService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BurgerDto> burgersDto = burgerService.findAll();
        List<BurgerWithImage> burgerWithImages = new ArrayList<>();
        for (BurgerDto burgerDto : burgersDto) {

            burgerWithImages.add(new BurgerWithImage(burgerDto, imageService.getImageByBurger(burgerDto.getId())));


        }
        req.setAttribute("burgerWithImages", burgerWithImages);
        req.getRequestDispatcher("/jsp/mainPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }
        HttpSession session = req.getSession();
        try {
            JSONObject jsonObject = new JSONObject(requestBody.toString());
            long burgerId = jsonObject.getLong("burgerId");
            int quantity = jsonObject.getInt("quantity");


            orderService.save(OrderDto.builder()
                    .burgerid(burgerId)
                    .userId((Long) session.getAttribute("id"))
                    .quantity((long) quantity)
                    .build());

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{\"status\":\"success\",\"message\":\"Burger added to cart\"}");
        } catch (JSONException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"status\":\"error\",\"message\":\"Invalid JSON\"}");

        }
    }
}
