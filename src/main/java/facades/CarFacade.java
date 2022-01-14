package facades;

import dtos.CarDTO;
import dtos.CarsDTO;
import entities.Car;
import entities.Race;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class CarFacade {
    private static EntityManagerFactory emf;
    private static CarFacade instance;

    private CarFacade() {
    }

    public static CarFacade getCarFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CarsDTO getCarsInRace(int raceId) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<Car> query = em.createQuery("SELECT c FROM Car c JOIN c.races r WHERE r.id IN (:raceId)", Car.class);
            query.setParameter("raceId", raceId);
            List<Car> res = query.getResultList();
            em.getTransaction().commit();
            return new CarsDTO(res);
        } finally {
            em.close();
        }
    }

    public CarDTO deleteCar(int carId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Car car = em.find(Car.class, carId);
            car.removeDrivers();
            car.removeRaces();
            em.remove(car);
            em.getTransaction().commit();
            return new CarDTO(car);
        } finally {
            em.close();
        }
    }

    public CarsDTO getAllCars() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Car> query = em.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> result = query.getResultList();
            return new CarsDTO(result);
        } finally {
            em.close();
        }
    }

    public CarDTO connectRaceToCar(int carId, int raceId) {
        EntityManager em = emf.createEntityManager();
        Car car = em.find(Car.class, carId);
        Race race = em.find(Race.class, raceId);

        try {
            em.getTransaction().begin();
            car.addRace(race);
            em.merge(car);
            em.getTransaction().commit();
            return new CarDTO(car);
        } finally {
            em.close();
        }
    }
}
