package com.movie.rater;

import com.movie.rater.api.OmdbApiClient;

import java.util.Scanner;

/**
 * Main class to rate the movies.
 */
public class MovieRater {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the movie name you would like to rate: ");
    String movieName = scanner.nextLine();
    if (movieName == null || movieName.trim().isEmpty() || movieName.equals(System.lineSeparator())) {
      System.out.println("Input error  - Movie name entered is empty");
      System.exit(1);
    }
    OmdbApiClient apiClient = new OmdbApiClient();
    String rating = apiClient.getRottenTomatoRating(movieName);
    if (rating == null || rating.trim().length() == 0) {
      System.out.println(String.format("Can not find Rotten Tomato rating for the movie %s", movieName));
      System.exit(1);
    }
    System.out.println(String.format("The Rotten Tomato Rating for the movie %s is %s ", movieName, rating));
  }
}
