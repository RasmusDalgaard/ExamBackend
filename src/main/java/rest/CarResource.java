package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CarDTO;
import dtos.CarsDTO;
import facades.CarFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/car")
public class CarResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final CarFacade CAR_FACADE = CarFacade.getCarFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCars() {
        CarsDTO carDTOs = CAR_FACADE.getAllCars();
        return GSON.toJson(carDTOs);
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCarsInRace(@PathParam("id") int id) {
        CarsDTO carDTOs = CAR_FACADE.getCarsInRace(id);
        return GSON.toJson(carDTOs);
    }

    @Path("/connect/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String connectRaceToCar(@PathParam("id") int carId, String _raceId) {
        Integer raceId = GSON.fromJson(_raceId, Integer.class);
        CarDTO carDTO = CAR_FACADE.connectRaceToCar(carId, raceId);
        return GSON.toJson(carDTO);
    }


    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteBoat(@PathParam("id") int id) {
        CarDTO carDTO = CAR_FACADE.deleteCar(id);
        return GSON.toJson(carDTO);
    }



}