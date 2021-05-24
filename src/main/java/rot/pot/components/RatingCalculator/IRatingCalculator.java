package rot.pot.components.RatingCalculator;

import javax.transaction.Transactional;

public interface IRatingCalculator {
    @Transactional
    float CalculateMovieRating(Integer movieId);
}
