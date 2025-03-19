package com.example.mainProgram.forXML;

import com.example.toCollection.classes.Movie;
import jakarta.xml.bind.annotation.*;
import java.util.LinkedList;

/** Класс-обертка для реализации Movie в XML */
@XmlRootElement(name = "movies")
public class MovieCollectionWrapper {
  private LinkedList<Movie> movies = new LinkedList<>();

  @XmlElement(name = "movie")
  public LinkedList<Movie> getMovies() {
    return movies;
  }

  public void setMovies(LinkedList<Movie> movies) {
    this.movies = movies;
  }
}
