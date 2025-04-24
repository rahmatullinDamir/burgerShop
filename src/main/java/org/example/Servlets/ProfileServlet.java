package org.example.Servlets;

import org.example.dto.UserDto;
import org.example.service.ProfileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    ProfileService profileService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        profileService = (ProfileService) servletConfig.getServletContext().getAttribute("profileService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long profileId = (Long) req.getSession().getAttribute("id");
        UserDto userDto = profileService.getUserById(profileId);
        req.setAttribute("profile", userDto);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
