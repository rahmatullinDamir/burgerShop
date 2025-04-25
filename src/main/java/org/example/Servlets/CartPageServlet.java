package org.example.Servlets;

import com.google.gson.Gson;
import org.example.Models.Burger;
import org.example.dto.BurgerDto;
import org.example.dto.BurgerWithImage;
import org.example.dto.CardDto;
import org.example.dto.OrderDto;
import org.example.service.BurgerService;
import org.example.service.ImageService;
import org.example.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/cart")
public class CartPageServlet extends HttpServlet {
    BurgerService burgerService;
    OrderService orderService;
    ImageService imageService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("Order")) {
            HttpSession session = req.getSession();
                List<OrderDto> orderDtos = orderService.findOrdersByUserId((Long) session.getAttribute("id"));
                for (OrderDto orderDto : orderDtos) {
                    orderService.delete(orderDto.getOrderId());
                }
            resp.sendRedirect("/");
        }
        else if (action.equals("Delete")) {
            Long orderId = Long.parseLong(req.getParameter("orderId"));
                orderService.delete(orderId);
            resp.sendRedirect("/cart");
        }


    }

    @Override
    public void init() throws ServletException {
        burgerService = (BurgerService) getServletContext().getAttribute("burgerService");
        orderService = (OrderService) getServletContext().getAttribute("orderService");
        imageService = (ImageService) getServletContext().getAttribute("imageService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<CardDto> cardDtos = new ArrayList<>();
            List<OrderDto> orderDtos = orderService.findOrdersByUserId((Long) session.getAttribute("id"));

            for (OrderDto orderDto : orderDtos) {
                BurgerDto burgerDto = burgerService.findById(orderDto.getBurgerid());
                BurgerWithImage burgerWithImage = new BurgerWithImage(burgerDto, imageService.getImageByBurger(burgerDto.getId()));
                cardDtos.add(CardDto.builder()
                        .burger(burgerWithImage)
                        .orderId(orderDto.getOrderId())
                        .quantity(orderDto.getQuantity())
                        .build());
            }

        req.setAttribute("cardDtos", cardDtos);
        req.getRequestDispatcher("jsp/cart.jsp").forward(req, resp);

    }
}
