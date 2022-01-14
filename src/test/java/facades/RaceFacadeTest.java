package facades;

import dtos.RaceDTO;
import entities.Car;
import entities.Driver;
import entities.Race;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;
@Disabled
class RaceFacadeTest {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static RaceFacade raceFacade;
    private Car c1, c2, c3;
    private Race r1, r2, r3;
    private Driver d1, d2, d3;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        raceFacade = RaceFacade.getRaceFacade(emf);
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        em = emf.createEntityManager();
        d1 = new Driver("Rapper", 1998, "Male");
        d2 = new Driver("Tutti", 2000, "Male");
        d3 = new Driver("Robert", 1998, "Male");

        c1 = new Car("The winner", "Benz", "Mercedes", 1987);
        c2 = new Car("The second best", "Yaris", "Toyota", 2000);
        c3 = new Car("The third best", "Impala", "Seat", 1800);


        r1 = new Race("Best Race Ever", "13-01-2022", 1.5, "Springfield");
        r2 = new Race("Crazy race 2000", "14-01-2022", 666.6, "Hell");
        r3 = new Race("Snowboard race", "29-01-2022", 2.1, "Schweiz");

        em.getTransaction().begin();
        c1.addDriver(d1);
        c2.addDriver(d2);
        c3.addDriver(d3);
        c1.addRace(r1);
        c2.addRace(r2);
        c3.addRace(r2);
        em.persist(c1);
        em.persist(c2);
        em.persist(c3);
        em.persist(r3);
        em.getTransaction().commit();
    }
    @AfterEach
    public void resetTestDB() {
        //        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
        em.getTransaction().begin();
        em.createNamedQuery("Driver.deleteAllRows").executeUpdate();
        em.createNamedQuery("Race.deleteAllRows").executeUpdate();
        em.createNamedQuery("Car.deleteAllRows").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void getAllRacesTest() {
        int expected = 3;
        int actual = raceFacade.getAllRaces().getRaces().size();
        assertEquals(expected, actual);
    }

    @Test
    public void createRaceTest() {
        Race race = new Race(4,"Fiery Frenzy Race", "28-06-2022", 4.0, "Bali");
        String expected = race.getLocation();
        RaceDTO raceDTO = new RaceDTO(race);
        String actual = raceFacade.createRace(raceDTO).getLocation();
        assertEquals(expected, actual);
    }


}