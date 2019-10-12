package com.kngovand.moviecatalogservice.resources;

import com.kngovand.moviecatalogservice.model.Movie;
import com.kngovand.moviecatalogservice.model.CatalogItem;
import com.kngovand.moviecatalogservice.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        UserRating ratings = restTemplate.getForObject("http://localhost:8082/ratingsdata/users/" + userId, UserRating.class);

        return ratings.getRatings().stream().map(rating -> {
            // Depreciated - For each movie ID, call movie info service and get details
            Movie movie = restTemplate.getForObject("http://localhost:8081/movies/" + rating.getMovieId(), Movie.class);
            // Put them together
            return new CatalogItem(movie.getName(), "Description Test", rating.getRating());
        })
        .collect(Collectors.toList());
    }
}

// WebClient method:
// WebClient.Builder builder = WebClient.builder();

/*            Movie movie = webClientBuilder.build()
                    .get()
                    .uri("httplocalhost:8081/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
*/