package org.example.Servlets;

import org.example.dto.SignInForm;
import org.example.dto.UserDto;
import org.example.service.SignInService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private SignInService signInService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        signInService = (SignInService) config.getServletContext().getAttribute("signInService");

        if (signInService == null) {
            throw new ServletException("SignInService не инициализирован.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        SignInForm signInForm = SignInForm.builder()
                .username(username)
                .password(password)
                .build();

        UserDto userDto = signInService.signIn(signInForm);
        if (userDto != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("authentication", true);
            session.setAttribute("id", userDto.getId());
            session.setAttribute("username", userDto.getUsername());
            session.setAttribute("role", userDto.getRole());
            resp.sendRedirect("/");
        } else {
            resp.sendRedirect("/signIn?error=invalidCredentials");
        }

    }
}