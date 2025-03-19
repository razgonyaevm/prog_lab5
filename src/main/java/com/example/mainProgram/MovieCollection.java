package com.example.mainProgram;

import com.example.toCollection.classes.Movie;
import com.example.toCollection.classes.Person;
import java.util.*;

public class MovieCollection {
  private final LinkedList<Movie> movies = new LinkedList<>();

  public void add(Movie movie) {
    movies.add(movie);
  }

  public void update(long id, Movie newMovie) {
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

  public void countByOperator(Person operator) {
    long count = movies.stream().filter(m -> Objects.equals(m.getOperator(), operator)).count();
    System.out.println("Количество фильмов с оператором " + operator + ": " + count);
  }

  public void printDescendingOscarsCount() {
    movies.stream()
        .map(Movie::getOscarsCount)
        .sorted(Comparator.reverseOrder())
        .forEach(System.out::println);
  }
}
