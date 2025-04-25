package org.example.Servlets;


import org.example.dto.AddressDto;
import org.example.dto.UserDto;
import org.example.service.AddressService;
import org.example.service.ProfileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private ProfileService profileService;
    private AddressService addressService;
    @Override
    public void init() throws ServletException {
        profileService = (ProfileService) getServletContext().getAttribute("profileService");
        addressService = (AddressService) getServletContext().getAttribute("addressService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = (Long) req.getSession().getAttribute("id");
        UserDto userDto = profileService.getUserById(id);
        AddressDto addressDto = addressService.getAddressById(userDto.getId());
        req.setAttribute("user", userDto);
        req.setAttribute("address", addressDto);
        req.getRequestDispatcher("/profile.jsp").forward(req, resp);
    }
}