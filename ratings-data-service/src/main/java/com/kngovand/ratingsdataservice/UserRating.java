package com.kngovand.ratingsdataservice;
import java.util.Arrays;
import java.util.List;

//userID
//movieId
// rating

public class UserRating {
    private String userId;
    private List<Rating> ratings;

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
                new Rating("movietest1", 5),
                new Rating("movietest2", 3)
        ));
    }

    public void initializeTest(String userId) {
        this.setUserId("test");
        this.setRatings(Arrays.asList(
                new Rating("testrating1", 1),
                new Rating("testrating2", 1)
        ));
    }
}
