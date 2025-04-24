package org.example.Servlets;

import org.example.dto.SignUpForm;
import org.example.service.SignUpService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private SignUpService signUpService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        signUpService = (SignUpService) config.getServletContext().getAttribute("signUpService");

        if (signUpService == null) {
            throw new ServletException("SignUpService не инициализирован.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String password = req.getParameter("password");
        String username = req.getParameter("username");


        if (password == null || password.trim().isEmpty() || username == null || username.trim().isEmpty()) {
            resp.sendRedirect("/signUp?error=emptyFields");
            return;
        }


        if (password.length() < 6) {
            resp.sendRedirect("/signUp?error=shortPassword");
            return;
        }

        boolean userExists;
        userExists = signUpService.isUsernameExist(username);

        if (userExists) {
            resp.sendRedirect("/signUp?error=userExists");
            return;
        }


        SignUpForm signUpForm = SignUpForm.builder()
                .password(password)
                .username(username)
                .street(req.getParameter("street"))
                .city(req.getParameter("city"))
                .house(Long.valueOf(req.getParameter("house")))
                .flat(Long.valueOf(req.getParameter("flat")))
                .build();


        try {
            signUpService.signUp(signUpForm);
            resp.sendRedirect("/signIn?success=registrationComplete");
        } catch (SQLException e) {
            throw new ServletException("Ошибка при регистрации пользователя.", e);
        }
    }
}