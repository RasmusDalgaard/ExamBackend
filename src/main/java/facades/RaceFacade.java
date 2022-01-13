package facades;

import dtos.RaceDTO;
import entities.Car;
import entities.Race;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class RaceFacade {
    private static EntityManagerFactory emf;
    private static RaceFacade instance;

    private RaceFacade() {
    }

    public static RaceFacade getRaceFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RaceFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<RaceDTO> getAllRaces() {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<Race> query = em.createQuery("SELECT r FROM Race r", Race.class);
            List<Race> res = query.getResultList();
            em.getTransaction().commit();
            return (List<RaceDTO>) (List<?>) res;
        } finally {
            em.close();
        }
    }

    public RaceDTO createRace(RaceDTO raceDTO) {
        EntityManager em = getEntityManager();
        Race race = new Race(raceDTO.getName(), raceDTO.getDate(), raceDTO.getTime(), raceDTO.getLocation());
        try {
            em.getTransaction().begin();
            em.persist(race);
            em.getTransaction().commit();
            return new RaceDTO(race);
        } finally {
            em.close();
        }
    }

    public RaceDTO addCarToRace(int carId, int raceId) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Car car = em.find(Car.class, carId);
            Race race = em.find(Race.class, raceId);
            race.addCarToRace(car);
            em.merge(race);
            em.getTransaction().commit();
            return new RaceDTO(race);
        } finally {
            em.close();
        }
    }
}
