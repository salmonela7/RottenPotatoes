package rot.pot.persistence;

import rot.pot.entities.Movie;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import java.util.List;

@Specializes
@ApplicationScoped
public class LoggedMoviesDAO extends MoviesDAO{

    private String className = getClass().getName();

    @Override
    public List<Movie> loadAll() {
        Log("Getting all movies...");

        List<Movie> movies = this.em.createNamedQuery("Movie.findAll", Movie.class).getResultList();

        Log("Found " + movies.size() + " movies...");
        for (Movie movie: movies) {
            Log("movieId: " + movie.getMovieId() + "; " + "movieName: " + movie.getName() + ".");
        }

        return movies;
    }

    @Override
    public boolean persist(Movie movie){
        try{
            Log("Persisting movie: " + movie.getName());
            em.persist(movie);
        }
        catch (Exception exception){
            Log("Movie: " + movie.getName() + " could not be persisted! Exception thrown: " + exception.getMessage());
            return false;
        }
        Log("Movie: " + movie.getName() + " was persisted successfully!");
        return true;
    }

    @Override
    public boolean merge(Movie movie){
        try{
            Log("Merging movie: " + movie.getName());
            em.merge(movie);
        }
        catch (Exception exception){
            Log("Movie: " + movie.getName() + " could not be merged! Exception thrown: " + exception.getMessage());
            return false;
        }
        Log("Movie: " + movie.getName() + " was merged successfully!");
        return true;
    }

    @Override
    public Movie findOne(Integer id) {
        Log("Getting movie with movieId: " + id);

        Movie movie = em.find(Movie.class, id);

        if(movie == null){
            Log("No movie with movieId: " + id + " was found...");
        }
        else{
            Log("Movie " + movie.getName() + " found!");
        }

        return movie;
    }

    private void Log(String message){
        System.out.println("[" + className + "]: " + message);
    }
}
