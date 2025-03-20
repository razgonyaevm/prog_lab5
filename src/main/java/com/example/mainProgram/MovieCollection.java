package com.example.mainProgram;

import com.example.mainProgram.forXML.XMLHandler;
import com.example.toCollection.classes.Movie;
import java.time.LocalDateTime;
import java.util.*;
import lombok.Getter;

@Getter
public class MovieCollection {
  private final LinkedList<Movie> movies = new LinkedList<>();
  private final LocalDateTime initializationDate = LocalDateTime.now();

  public void add(Movie movie) {
    movies.add(movie);
  }

  public void update(long id, Movie newMovie) {
    if (id <= 0) {
      throw new IllegalArgumentException("ID must be greater than 0");
    }
    for (int i = 0; i < movies.size(); i++) {
      if (movies.get(i).getId().equals(id)) {
        movies.set(i, newMovie);
        return;
      }
    }
    System.out.println("Фильм с таким ID не найден.");
  }

  public void removeById(long id) {
    movies.removeIf(movie -> movie.getId().equals(id));
  }

  public void clear() {
    movies.clear();
  }

  public void show() {
    movies.forEach(System.out::println);
  }

  public void reorder() {
    Collections.reverse(movies);
  }

  public void removeAt(int index) {
    if (index >= 0 && index < movies.size()) {
      movies.remove(index);
    } else {
      System.out.println("Неверный индекс.");
    }
  }

  public void removeFirst() {
    if (!movies.isEmpty()) {
      movies.removeFirst();
    }
  }

  public void sumOfLength() {
    int sum = movies.stream().mapToInt(Movie::getLength).sum();
    System.out.println("Сумма length: " + sum);
  }

  public void countByOperator(String operatorName) {
    long count =
        movies.stream()
            .filter(m -> Objects.equals(m.getOperator().getName(), operatorName))
            .count();
    System.out.println("Количество фильмов оператора " + operatorName + ": " + count);
  }

  public void printDescendingOscarsCount() {
    movies.stream()
        .map(Movie::getOscarsCount)
        .sorted(Comparator.reverseOrder())
        .forEach(System.out::println);
  }

  public void setMovies(LinkedList<Movie> movies) {
    this.movies.clear();
    this.movies.addAll(movies);
  }

  public int size() {
    return movies.size();
  }

  public void save() {
    XMLHandler xmlHandler = new XMLHandler("save_movies.xml");
    xmlHandler.save(movies);
  }
}
