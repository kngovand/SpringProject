package com.kngovand.moviecatalogservice.model;
import java.util.Arrays;
import java.util.List;

//userID
//movieId
// rating

public class UserRating {
    private String userId;
    private List<Rating> ratings;

    public UserRating() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void initialize(String userId) {
        this.setUserId(userId);
        this.setRatings(Arrays.asList(
                new Rating("100", 5),
                new Rating("200", 3)
        ));
    }
}
