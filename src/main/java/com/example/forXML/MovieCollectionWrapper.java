package com.example.forXML;

import com.example.forCollection.classes.Movie;
import jakarta.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

import lombok.Setter;

/** Класс-обертка для реализации Movie в XML */
@Setter
@XmlRootElement(name = "movies")
public class MovieCollectionWrapper {
  private List<Movie> movies = new LinkedList<>();

  @XmlElement(name = "movie")
  public LinkedList<Movie> getMovies() {
    return (LinkedList<Movie>) movies;
  }
}
