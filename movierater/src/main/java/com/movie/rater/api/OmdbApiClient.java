package com.movie.rater.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.rater.util.Constants;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Class designed to make a OMDB api call to get the movie details.
 */
public class OmdbApiClient {

  private static Client client = null;

  private static void createClient() {
    if (client == null) {
      client = ClientBuilder.newClient();
    }
  }

  /**
   * Get the Rotten Tomato rating from the api result.
   *
   * @param movieName Name of the movie
   * @return Rotten Tomato rating for the movie
   */
  public String getRottenTomatoRating(String movieName) {
    OmdbResponse omdbResponse = callOmdbApi(movieName);
    List<Rating> ratings = omdbResponse.getRatings();
    if (ratings != null) {
      for (Rating rating : ratings) {
        if (Constants.ROTTEN_TOMATOES.equalsIgnoreCase(rating.getSource())) {
          return rating.getValue();
        }
      }
    }
    return null;
  }

  /**
   * Make a api call to OMDB to get the movie details.
   *
   * @param movieName Name of the movie
   * @return The movie details
   */
  private OmdbResponse callOmdbApi(String movieName) {
    try {
      createClient();

      WebTarget webTarget = client.target(Constants.OMDB_ENDPOINT)
          .queryParam(Constants.API_KEY, Constants.API_KEY_VALUE).queryParam(Constants.MOVIE_NAME, movieName);
      String response = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
      return new ObjectMapper().readValue(response, OmdbResponse.class);
    } catch (Exception exception) {
      throw new RuntimeException("Failed to call Omdb api");
    }
  }
}
