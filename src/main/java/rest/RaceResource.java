package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.RaceDTO;
import facades.RaceFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/race")
public class RaceResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final RaceFacade RACE_FACADE = RaceFacade.getRaceFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllRaces() {
        List<RaceDTO> raceDTOs = RACE_FACADE.getAllRaces();
        for (RaceDTO r : raceDTOs) {
            System.out.println(r.toString());
        }
        return GSON.toJson(raceDTOs);
    }

}