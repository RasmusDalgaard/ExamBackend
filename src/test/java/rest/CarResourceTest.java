package rest;

import dtos.CarDTO;
import entities.Car;
import entities.Driver;
import entities.Race;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;
@Disabled
class CarResourceTest {
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/exambackend/api";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private Car c1, c2, c3;
    private Race r1, r2, r3;
    private Driver d1, d2, d3;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    @AfterEach
    public void resetTestDB() {
        //Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
        em.getTransaction().begin();
        em.createNamedQuery("Driver.deleteAllRows").executeUpdate();
        em.createNamedQuery("Race.deleteAllRows").executeUpdate();
        em.createNamedQuery("Car.deleteAllRows").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
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

    private static String securityToken;

    private static void login(String role, String password) {
        String json = String.format("{username: \"%s\", password: \"%s\"}", role, password);
        securityToken = given()
                .contentType("application/json")
                .body(json)
                //.when().post("/api/login")
                .when().post("/login")
                .then()
                .extract().path("token");
        //System.out.println("TOKEN ---> " + securityToken);
    }

    @Test
    void deleteCartest(){
        given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .pathParam("id", c1.getId())
                .when()
                .delete("/car/delete/{id}").then()
                .statusCode(200);

        List<CarDTO> allCars;

        allCars = given()
                .contentType("application/json")
                .when()
                .get("/car/all")
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList("cars", CarDTO.class);

        CarDTO carDTO = new CarDTO(c1);
        assertEquals(2, allCars.size());
    }
}