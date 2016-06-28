package com.echo.rst;

import com.echo.rst.customer.Customer;
import com.echo.rst.customer.CustomerRepository;
import com.echo.rst.entity.Category;
import com.echo.rst.menu.Menu;
import com.echo.rst.menu.MenuRepository;
import com.echo.rst.menu.MenuService;
import com.echo.rst.operlog.OperLog;
import com.echo.rst.operlog.OperLogRepository;
import com.echo.rst.operlog.OperLogService;
import com.echo.rst.order.Order;
import com.echo.rst.order.OrderRepository;
import com.echo.rst.order.OrderService;
import com.echo.rst.user.User;
import com.echo.rst.user.UserRepository;
import com.echo.rst.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * Created by echo on 16-6-11.
 */
@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

//    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Customer customer = repository.findOne(1L);
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            for (Customer bauer : repository.findByLastName("Bauer")) {
                log.info(bauer.toString());
            }
            log.info("");
        };
    }

//    @Bean
    public CommandLineRunner user(UserRepository repository) {
        return (args) -> {
            log.info("##user test##");
            log.info("--------------------------------------------");
            repository.save(new User("zyc01", "zyc01_login", "abcd", "desc01"));
            repository.save(new User("zyc02", "zyc02_login", "abcd", "desc02"));

            log.info("user found with findAll()");
            log.info("--------------------------------------------");
            for (User user : repository.findAll()) {
                log.info(user.toString());
            }

            log.info("user found with findOne(1L): ");
            log.info("--------------------------------------------");
            User user = repository.findOne(1L);
            log.info(user.toString());

            log.info("user found with findByUserName(zyc01)");
            log.info("--------------------------------------------");
            for (User zyc01 : repository.findByUserName("zyc01")) {
                log.info(zyc01.toString());
            }
        };
    }

//    @Bean
    public CommandLineRunner menu(MenuRepository repository) {
        return (args) -> {
            log.info("##menu test##");
            log.info("--------------------------------------------");
            repository.save(new Menu("menu01", "menu01_login", 8.5));
            repository.save(new Menu("menu02", "menu02_login", 10.5));

            log.info("menu found with findAll()");
            log.info("--------------------------------------------");
            for (Menu Menu : repository.findAll()) {
                log.info(Menu.toString());
            }

            log.info("Menu found with findOne(1L): ");
            log.info("--------------------------------------------");
            Menu Menu = repository.findOne(1L);
            log.info(Menu.toString());

            log.info("Menu found with findByName(menu01)");
            log.info("--------------------------------------------");
            for (Menu menu01 : repository.findByName("menu01")) {
                log.info(menu01.toString());
            }
        };
    }

//    @Bean
    public CommandLineRunner order(OrderRepository repository) {
        return (args) -> {
            log.info("##order test##");
            log.info("--------------------------------------------");
            repository.save(new Order("order01", new Date(), Order.STATE_CANCEL, 2, 1));
            repository.save(new Order("order02", new Date(), Order.STATE_FINISHED, 1, 2));

            log.info("order found with findAll()");
            log.info("--------------------------------------------");
            for (Order Order : repository.findAll()) {
                log.info(Order.toString());
            }

            log.info("Order found with findOne(1L): ");
            log.info("--------------------------------------------");
            Order Order = repository.findOne(1L);
            log.info(Order.toString());

            log.info("Order found with findByNum(1)");
            log.info("--------------------------------------------");
            for (Order order01 : repository.findByNum(1)) {
                log.info(order01.toString());
            }
        };
    }

//    @Bean
    public CommandLineRunner menuAndOrder(MenuRepository menuRepository, OrderRepository orderRepository) {
        return (args) -> {
            log.info("##menu and order test##");
            log.info("--------------------------------------------");
            Menu menu = new Menu("menu01", "menu01_login", 8.5);
            Order order = new Order("order01", new Date(), Order.STATE_CANCEL, 2, 1);
            menuRepository.save(menu);

            order.setMenu(menu);
            orderRepository.save(order);

            log.info("Menu found with findByName(menu01)");
            log.info("--------------------------------------------");
            List<Menu> menuList = menuRepository.findByName("menu01");
            for (Menu m : menuList) {
                log.info(m.toString());
            }
        };
    }

//    @Bean
    public CommandLineRunner operLog(OperLogRepository repository) {
        return (args) -> {
            log.info("##operLog test##");
            log.info("--------------------------------------------");
            repository.save(new OperLog(new Date(), 1, 1, "add succss", "no reason"));
            repository.save(new OperLog(new Date(), 2, 2, "add failure", "system error"));

            log.info("operLog found with findAll()");
            log.info("--------------------------------------------");
            for (OperLog OperLog : repository.findAll()) {
                log.info(OperLog.toString());
            }

            log.info("OperLog found with findOne(1L): ");
            log.info("--------------------------------------------");
            OperLog OperLog = repository.findOne(1L);
            log.info(OperLog.toString());

            log.info("OperLog found with findByNum(1)");
            log.info("--------------------------------------------");
            for (OperLog operLog01 : repository.findByState(1)) {
                log.info(operLog01.toString());
            }
        };
    }

//    @Bean
    public CommandLineRunner userServiceAddUser(UserService userService) {
        return (args) -> {
            log.info("##userService.adduser test##");
            log.info("--------------------------------------------");
            User user = new User("zyc001", "zyc_login001", "abcd", "admin");
            userService.addUser(user);
        };
    }

//	@Bean
    public CommandLineRunner menuServiceAddMenu(MenuService menuService) {
        return (args) -> {
            log.info("##menuService.addmenu test##");
            log.info("--------------------------------------------");
            Menu menu = new Menu("凉皮", "陕西凉皮", 7.00);
            menuService.addMenu(menu);
        };
    }

//    @Bean
    public CommandLineRunner operLogServiceAddOperLog(OperLogService operLogService) {
        return (args) -> {
            log.info("##operLogService.addoperLog test##");
            log.info("--------------------------------------------");
            operLogService.success(Category.USER, "add user success");
            operLogService.failure(Category.USER, "add user failure", "user is exists");
        };
    }

//	@Bean
    public CommandLineRunner orderServiceAddUser(OrderService orderService) {
        return (args) -> {
            log.info("##orderService.addorder test##");
            log.info("--------------------------------------------");
            Order order = new Order("order", new Date(), Order.STATE_FINISHED, 2, 1);
            orderService.addOrder(order);
        };
    }

//    @Bean
    public CommandLineRunner userServiceDeleteUser(UserService userService) {
        return (args) -> {
            log.info("##userService.deleteUser test##");
            log.info("--------------------------------------------");
            User user = new User("zyc001", "zyc_login001", "abcd", "admin");
            User u = userService.addUser(user);
            userService.deleteUser(u.getId());
        };
    }

//    @Bean
    public CommandLineRunner menuServiceDeleteMenu(MenuService menuService) {
        return (args) -> {
            log.info("##menuService.deleteMenu test##");
            log.info("--------------------------------------------");
            Menu menu = new Menu("凉皮", "陕西凉皮", 7.00);
            Menu u = menuService.addMenu(menu);
            menuService.deleteMenu(u.getId());
        };
    }

//	@Bean
    public CommandLineRunner orderServiceDeleteOrder(OrderService orderService) {
        return (args) -> {
            log.info("##orderService.deleteOrder test##");
            log.info("--------------------------------------------");
            Order order = new Order("order", new Date(), Order.STATE_FINISHED, 2, 1);
            Order u = orderService.addOrder(order);
            orderService.deleteOrder(u.getId());
        };
    }

//	@Bean
    public CommandLineRunner userServiceUpdateUser(UserService userService) {
        return (args) -> {
            log.info("##userService.updateUser test##");
            log.info("--------------------------------------------");
            User user = new User("zyc001", "zyc_login001", "abcd", "admin");
            User u = userService.addUser(user);
            u.setUserName("hdq001");
            userService.updateUser(u);
        };
    }

//	@Bean
    public CommandLineRunner orderServiceUpdateOrder(OrderService orderService) {
        return (args) -> {
            log.info("##orderService.updateOrder test##");
            log.info("--------------------------------------------");
            Order order = new Order("order", new Date(), Order.STATE_FINISHED, 2, 1);
            Order u = orderService.addOrder(order);
            u.setCopies(5);
            orderService.updateOrder(u);
        };
    }

//    @Bean
    public CommandLineRunner userServiceQueryUsers(UserService userService) {
        return (args) -> {
            log.info("##userService.queryUsers test##");
            log.info("--------------------------------------------");
            User user = new User("zyc001", "zyc_login001", "abcd", "admin");
            userService.addUser(user);
            Page<User> pageUser = userService.queryUsers(0);
            pageUser.map((User u) -> {
                log.info(u.toString());
                return user.getUserName();
            });
        };
    }

//	@Bean
    public CommandLineRunner operLogServiceQueryOperLogs(OperLogService operLogService) {
        return (args) -> {
            log.info("##operLogService.queryOperLogs test##");
            log.info("--------------------------------------------");
            operLogService.success(Category.USER, "user add success");
            Page<OperLog> pageOperLog = operLogService.queryOperLogs(0);
            pageOperLog.map((OperLog u) -> {
                log.info(u.toString());
                return u;
            });
        };
    }

//	@Bean
    public CommandLineRunner orderServiceQueryOrders(OrderService orderService) {
        return (args) -> {
            log.info("##orderService.queryOrders test##");
            log.info("--------------------------------------------");
            Order order = new Order("order", new Date(), Order.STATE_FINISHED, 2, 1);
            orderService.addOrder(order);
            Page<Order> pageOrder = orderService.queryOrders(0);
            pageOrder.map((Order u) -> {
                log.info(u.toString());
                return u;
            });
        };
    }
}
