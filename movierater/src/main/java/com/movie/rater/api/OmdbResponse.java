package com.movie.rater.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Map the Ratings from the movie details response of OMDB api.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OmdbResponse {

  @JsonProperty("Ratings")
  private List<Rating> ratings;

  public List<Rating> getRatings() {
    return ratings;
  }

  public void setRatings(List<Rating> ratings) {
    this.ratings = ratings;
  }
}
