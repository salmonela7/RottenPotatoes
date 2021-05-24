package rot.pot.components.RatingCalculatorAsync;

import java.util.concurrent.Future;

public interface IRatingCalculatorAsync {
    Future<Float> CalculateMovieRating(Integer movieId);
}
