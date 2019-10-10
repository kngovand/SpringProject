package com.kngovand.ratingsdataservice.resources;
import com.kngovand.ratingsdataservice.Rating;
import com.kngovand.ratingsdataservice.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    @RequestMapping("/movies/{movieId}")
    public Rating getMovieRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }
    @RequestMapping("/users/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
        UserRating userRating = new UserRating();
        userRating.initialize(userId);
        return userRating;
    }
}
