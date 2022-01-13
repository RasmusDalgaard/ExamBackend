package facades;

import dtos.CarDTO;
import entities.Car;

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

    public List<CarDTO> getCarsByRace(int raceId) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<Car> query = em.createQuery("SELECT c FROM Car c JOIN c.races r WHERE r.id =: raceId", Car.class);
            query.setParameter("raceId", raceId);
            List<Car> res = query.getResultList();
            em.getTransaction().commit();
            return (List<CarDTO>) (List<?>) res;
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
}
