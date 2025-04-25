package org.example.Servlets;

import com.google.gson.Gson;
import org.example.Models.Burger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/cart")
public class CartPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int burgerId = Integer.parseInt(req.getParameter("burgerId"));

        HttpSession session = req.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>();
        }

        if ("add".equals(action)) {
            cart.put(burgerId, cart.getOrDefault(burgerId, 0) + 1);
        } else if ("remove".equals(action)) {
            if (cart.containsKey(burgerId)) {
                cart.remove(burgerId);
            }
        }

        session.setAttribute("cart", cart);

        int totalItems = cart.values().stream().mapToInt(Integer::intValue).sum();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(new Gson().toJson(Map.of("totalItems", totalItems)));
    }

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

        List<Burger> burgersInCart = new ArrayList<>();

        req.getRequestDispatcher("jsp/cart.jsp").forward(req, resp);
    }
}
