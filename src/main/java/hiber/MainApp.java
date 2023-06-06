package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.persistence.NoResultException;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User egor = new User("Egor", "Rybakov", "Egor@mail.ru");
      User pavel = new User("Pavel", "Perviy", "pavel@mail.ru");
      User sasha = new User("Sasha", "Govorov", "Govor@mail.ru");
      User ramil = new User("Ramil", "Latfullin", "Ramil@mail.ru");

      Car carEgor = new Car("BMW318", 3);
      Car carPavel = new Car("BMW750", 7);
      Car carSasha = new Car("BMW525", 5);
      Car carRamil = new Car("BMW325", 3);

      userService.add(egor.setCar(carEgor).setUser(egor));
      userService.add(pavel.setCar(carPavel).setUser(pavel));
      userService.add(sasha.setCar(carSasha).setUser(sasha));
      userService.add(ramil.setCar(carRamil).setUser(ramil));


      System.out.println("Список всех владельцев авто ");
      for (User user : userService.listUsers()) {
        System.out.println(user + " " + user.getCar());
      }

      System.out.println("_____________________________________________________________");
      try {
         System.out.println("Выбор владельца машины по модели и серии "
                 + userService.getUserByCar("BMW318", 3));
      } catch (NoResultException e) {
           System.out.println("Владелец авто не найден");
      }
      context.close();
   }
}