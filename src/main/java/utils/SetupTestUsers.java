package utils;


import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Date;

public class SetupTestUsers {

    public static void main(String[] args) {

        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        User user = new User("user", "test1");
        User admin = new User("admin", "test2");
        User both = new User("user_admin", "test3");

        Driver driver = new Driver("Rapper", 1998, "Male");
        Driver driver2 = new Driver("Bobbert", 1998, "Male");
        Driver driver3 = new Driver("Tutti", 2000, "Male");

        Car car = new Car("The winner", "Benz", "Mercedes", 1987);
        Car car2 = new Car("The second best", "Yaris", "Toyota", 2000);
        Car c3 = new Car("The third best", "Impala", "Seat", 1800);

        Race race = new Race("Best Race Ever", "13-01-2022", 1.5, "Springfield");
        Race race2 = new Race("Crazy race 2000", "14-01-2022", 666.6, "Hell");
        Race race3 = new Race("Snowboard race", "29-01-2022", 2.1, "Schweiz");

        if (admin.getUserPass().equals("test") || user.getUserPass().equals("test") || both.getUserPass().equals("test"))
            throw new UnsupportedOperationException("You have not changed the passwords");

        em.getTransaction().begin();

        car.addDriver(driver);
        car.addRace(race);
        em.persist(car);

        car2.addDriver(driver2);
        car2.addRace(race2);
        em.persist(car2);

        c3.addDriver(driver3);
        em.persist(c3);
        em.persist(race3);

        Role userRole = new Role("user");
        Role adminRole = new Role("admin");
        user.addRole(userRole);
        admin.addRole(adminRole);
        both.addRole(userRole);
        both.addRole(adminRole);
        em.persist(userRole);
        em.persist(adminRole);
        em.persist(user);
        em.persist(admin);
        em.persist(both);
        em.getTransaction().commit();

        System.out.println("Created TEST Users");
    }
}
