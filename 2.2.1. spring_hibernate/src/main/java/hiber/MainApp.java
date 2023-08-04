package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);


      CarService carService = context.getBean(CarService.class);
//      carService.deleteAllCars();
      carService.add(new Car("MB", "S500"));
      carService.add(new Car("MB", "E300"));
      carService.add(new Car("BMW", "X5"));
      carService.add(new Car("BMW", "520i"));


      UserService userService = context.getBean(UserService.class);
//      userService.deleteAllUsers();
      List<Car> cars = carService.listCars();


      userService.add(new User("User1", "Lastname1", "user1@mail.ru", cars.get(0)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", cars.get(1)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", cars.get(2)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", cars.get(3)));

      System.out.println("Владелец \"MB E300\"");
      System.out.println(userService.carOwner("MB", "E300"));

      context.close();
   }
}
