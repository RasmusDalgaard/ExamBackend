package facades;

import dtos.DriversDTO;
import dtos.RaceDTO;
import entities.Car;
import entities.Driver;
import entities.Race;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class DriverFacade {
    private static EntityManagerFactory emf;
    private static DriverFacade instance;

    private DriverFacade() {
    }

    public static DriverFacade getDriverFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new DriverFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public DriversDTO getDriversInRace(int raceId) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<Driver> query = em.createQuery("SELECT d FROM Driver d JOIN d.car c JOIN c.races r WHERE r.id IN (:raceId)", Driver.class);
            query.setParameter("raceId", raceId);
            List<Driver> res = query.getResultList();
/*
            List<Driver> drivers = new ArrayList<>();
            for (Car car: res) {
                for (Driver driver: car.getDrivers()) {
                    drivers.add(driver);
                }
            }

 */
            em.getTransaction().commit();
            return new DriversDTO(res);
        } finally {
            em.close();
        }
    }


}
