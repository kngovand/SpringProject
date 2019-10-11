package com.kngovand.moviecatalogservice.resources;

import com.kngovand.moviecatalogservice.model.Movie;
import com.kngovand.moviecatalogservice.model.Rating;
import com.kngovand.moviecatalogservice.model.CatalogItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    // Get all rated movie IDs
    // For each movie ID, call movie info service and get details
    // Return data

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        WebClient.Builder builder = WebClient.builder();

        List<Rating> ratings = Arrays.asList(
                new Rating("1233", 3),
                new Rating("1234", 4)
        );

        return ratings.stream().map(rating -> {
            //Depreciated:
            //Movie movie = restTemplate.getForObject("httplocalhost:8081/movies/" + rating.getMovieId(), Movie.class);

            Movie movie = webClientBuilder.build()
                    .get()
                    .uri("httplocalhost:8081/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();

            return new CatalogItem(movie.getName(), "Test", rating.getRating());
        })
        .collect(Collectors.toList());
    }
}
