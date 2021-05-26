package rot.pot.Controllers;

import rot.pot.Interceptors.RestRequestLogger;
import rot.pot.entities.Movie;
import rot.pot.persistence.MoviesDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("movies")
@RequestScoped
@RestRequestLogger
public class MoviesController {

    @Inject
    private MoviesDAO moviesDAO;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovie(@PathParam("id") Integer movieId) {
        Movie movie = moviesDAO.findOne(movieId);

        if (movie == null){
            return Response.status(404).build();
        }

        return Response.status(200).entity(movie).build();
    }

    @POST
    @Path("movie")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createMovie(Movie movie) {
        boolean isPersisted = this.moviesDAO.persist(movie);

        if(!isPersisted){
            return Response.status(500).build();
        }

        return Response.status(201).entity(movie).build();
    }

    @PUT
    @Path("{id}/{newtitle}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createMovie(@PathParam("id") Integer movieId, @PathParam("newtitle") String newTitle) {
        Movie movie = moviesDAO.findOne(movieId);

        if (movie == null){
            return Response.status(404).build();
        }

        movie.setName(newTitle);

        boolean isPersisted = this.moviesDAO.persist(movie);

        if(!isPersisted){
            return Response.status(500).build();
        }

        return Response.status(201).entity(movie).build();
    }
}
