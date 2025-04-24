package org.example.Listener;

import org.example.repositories.*;
import org.example.repositories.impl.*;
import org.example.service.ProfileService;
import org.example.service.SignInService;
import org.example.service.impl.ProfileServiceImpl;
import org.example.service.impl.SignInServiceImpl;
import org.example.service.SignUpService;
import org.example.service.impl.SignUpServiceImpl;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CustomServletContextListener implements ServletContextListener {

    private static final String BD_USERNAME = "postgres";
    private static final String BD_PASSWORD = "admin";
    private static final String BD_URL = "jdbc:postgresql://localhost:5432/burgerDB";
    private static final String BD_DRIVER = "org.postgresql.Driver";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(BD_DRIVER);
        dataSource.setUsername(BD_USERNAME);
        dataSource.setPassword(BD_PASSWORD);
        dataSource.setUrl(BD_URL);

        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);
        SignUpService signUpService = new SignUpServiceImpl(userRepository);
        SignInService signInService = new SignInServiceImpl(userRepository);
        ProfileService profileService = new ProfileServiceImpl(userRepository);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("profileService", profileService);

        OrderRepository orderRepository = new OrderRepositoryJdbcImpl(dataSource);
        ImageRepository imageRepository = new ImageRepositoryJdbcImpl(dataSource);
        BurgerRepository burgerRepository = new BurgerRepositoryJdbcImpl(dataSource);
        AddressRepository addressRepository = new AddressRepositoryJdbcImpl(dataSource);

    }
}
