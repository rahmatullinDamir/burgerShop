package org.example.Servlets;


import org.example.dto.OrderDto;
import org.example.service.OrderService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/count")
public class CountServet extends HttpServlet {

    OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int cnt = 0;

        List<OrderDto> orderDtos = orderService.findOrdersByUserId((Long) session.getAttribute("id"));
        for (OrderDto orderDto : orderDtos) {
            cnt += orderDto.getQuantity();
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("{\"count\":" + cnt + "}");


    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        orderService = (OrderService) config.getServletContext().getAttribute("orderService");
    }
}
