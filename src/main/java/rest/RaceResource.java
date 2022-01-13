package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.RaceDTO;
import dtos.RacesDTO;
import facades.RaceFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/race")
public class RaceResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final RaceFacade RACE_FACADE = RaceFacade.getRaceFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllRaces() {
        RacesDTO raceDTOs = RACE_FACADE.getAllRaces();
        return GSON.toJson(raceDTOs);
    }

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createRace(RaceDTO _raceDTO) {
        RaceDTO raceDTO = RACE_FACADE.createRace(_raceDTO);
        return GSON.toJson(raceDTO);
    }

}