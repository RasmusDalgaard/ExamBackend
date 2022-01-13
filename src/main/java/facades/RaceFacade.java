package facades;

import dtos.CarDTO;
import dtos.DriverDTO;
import dtos.RaceDTO;
import entities.Car;
import entities.Driver;
import entities.Race;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import java.util.List;

public class RaceFacade {
    private static EntityManagerFactory emf;
    private static RaceFacade instance;

    private RaceFacade() {
    }

    public static RaceFacade getCarFacade(EntityManagerFactory _emf) {
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

    public RaceDTO updateRace(RaceDTO raceDTO) {
        EntityManager em = emf.createEntityManager();
        try {
            Race race = em.find(Race.class, raceDTO.getId());

            //Edit race
            race.setName(raceDTO.getName());
            race.setDate(raceDTO.getDate());
            race.setTime(race.getTime());

            // Edit cars
            race.getCars().clear();
            for (CarDTO carDTO: raceDTO.getCarDTOs()) {
                String currentCarName = carDTO.getName();
                try {
                    Car car = em.createQuery("SELECT c FROM Car c WHERE c.name = :currentCarName", Car.class).setParameter("carName", currentCarName).getSingleResult();
                    race.addCarToRace(car);
                    //Edit driver
                    car.getDrivers().clear();
                    for (DriverDTO driverDTO: carDTO.getDriverDTOs()) {
                        int currentDriverId = driverDTO.getId();
                        Driver driver = em.createQuery("SELECT d FROM Driver d WHERE d.id = :currenDriverId", Driver.class).setParameter("currentDriverId", currentDriverId).getSingleResult();
                        car.addDriverToCar(driver);
                    }
                } catch (NoResultException error) {
                    throw new WebApplicationException("Car: " + carDTO.getName() + ", does not exist", 400);
                }
            }
            em.getTransaction().begin();
            em.merge(race);
            em.getTransaction().commit();
            return new RaceDTO(race);
        } finally {
            em.close();
        }
    }
}
