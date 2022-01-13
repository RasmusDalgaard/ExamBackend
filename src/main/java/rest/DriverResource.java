package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.DriversDTO;
import dtos.RaceDTO;
import facades.DriverFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/driver")
public class DriverResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final DriverFacade DRIVER_FACADE = DriverFacade.getDriverFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCarsByRace(@PathParam("id") int id) {
        DriversDTO driverDTOs = DRIVER_FACADE.getDriversInRace(id);
        return GSON.toJson(driverDTOs);
    }
}