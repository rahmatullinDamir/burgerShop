package org.example.Listener;

import org.example.repositories.*;
import org.example.repositories.impl.*;
import org.example.service.*;
import org.example.service.impl.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CustomServletContextListener implements ServletContextListener {

    private static final String BD_USERNAME = "postgres";
    private static final String BD_PASSWORD = "278145";
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

        OrderRepository orderRepository = new OrderRepositoryJdbcImpl(dataSource);
        ImageRepository imageRepository = new ImageRepositoryJdbcImpl(dataSource);
        BurgerRepository burgerRepository = new BurgerRepositoryJdbcImpl(dataSource);
        AddressRepository addressRepository = new AddressRepositoryJdbcImpl(dataSource);
        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);


        SignUpService signUpService = new SignUpServiceImpl(userRepository, addressRepository);
        SignInService signInService = new SignInServiceImpl(userRepository);
        ProfileService profileService = new ProfileServiceImpl(userRepository);
        BurgerService burgerService = new BurgerServiceImpl(burgerRepository);
        OrderService orderService = new OrderServiceImpl(orderRepository);
        ImageService imageService = new ImageServiceImpl(imageRepository);
        AddressService addressService = new AddressServiceImpl(addressRepository);

        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("profileService", profileService);
        servletContext.setAttribute("burgerService", burgerService);
        servletContext.setAttribute("orderService", orderService);
        servletContext.setAttribute("imageService", imageService);
        servletContext.setAttribute("addressService", addressService);



    }
}
