package com.kngovand.moviecatalogservice.resources;

import com.kngovand.moviecatalogservice.model.Movie;
import com.kngovand.moviecatalogservice.model.Rating;
import com.kngovand.moviecatalogservice.model.CatalogItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        RestTemplate restTemplate = new RestTemplate();
        // Get all rated movie IDs
        // For each movie ID, call movie info service and get details
        // Return data
        List<Rating> ratings = Arrays.asList(
                new Rating("1233", 3),
                new Rating("1234", 4)
        );

        return ratings.stream().map(rating -> {
            Movie movie = restTemplate.getForObject("httplocalhost:8081/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(), "Test", rating.getRating());
        })
        .collect(Collectors.toList());

    }
}
