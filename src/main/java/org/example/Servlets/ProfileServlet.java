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
        Long id = (Long) req.getSession().getAttribute("id");
        UserDto userDto = profileService.getUserById(id);
        String street = req.getParameter("street");
        String city = req.getParameter("city");
        Long house = Long.parseLong(req.getParameter("house"));
        Long flat = Long.parseLong(req.getParameter("flat"));
        AddressDto addressDto = AddressDto.builder()
                .id(userDto.getId())
                .street(street)
                .city(city)
                .house(house)
                .flat(flat)
                .build();
        addressService.updateAddress(addressDto);

        resp.sendRedirect("/profile");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = (Long) req.getSession().getAttribute("id");
        UserDto userDto = profileService.getUserById(id);
        AddressDto addressDto = addressService.getAddressById(userDto.getId());
        req.setAttribute("user", userDto);
        req.setAttribute("address", addressDto);
        req.getRequestDispatcher("jsp/profile.jsp").forward(req, resp);
    }
}