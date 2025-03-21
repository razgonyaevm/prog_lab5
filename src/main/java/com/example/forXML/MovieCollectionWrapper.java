package com.example.forXML;

import com.example.forCollection.classes.Movie;
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
