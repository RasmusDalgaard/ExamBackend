package facades;

import dtos.DriversDTO;
import entities.Driver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
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
            TypedQuery<Driver> query = em.createQuery("SELECT d FROM Driver d JOIN d.car c JOIN c.races r WHERE r.id =: raceId", Driver.class);
            List<Driver> res = query.getResultList();
            em.getTransaction().commit();
            return new DriversDTO(res);
        } finally {
            em.close();
        }
    }


}
