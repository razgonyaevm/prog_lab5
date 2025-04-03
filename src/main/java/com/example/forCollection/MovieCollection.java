package com.example.forCollection;

import com.example.forCollection.classes.Movie;
import com.example.forXML.XMLHandler;
import java.time.LocalDateTime;
import java.util.*;
import lombok.Getter;

/** Класс для управления коллекцией с элементами {@link com.example.forCollection.classes.Movie} */
@Getter
public class MovieCollection {
  private final List<Movie> movies = new LinkedList<>();
  private final LocalDateTime initializationDate = LocalDateTime.now();

  /** Добавляет элемент в коллекцию */
  public void add(Movie movie) {
    movies.add(movie);
  }

  /** Устанавливает новый элемент на место с индексом id */
  public void update(long id, Movie newMovie) {
    newMovie.setId(id);

    ListIterator<Movie> iterator = movies.listIterator();
    while (iterator.hasNext()) {
      if (iterator.next().getId().equals(id)) {
        iterator.set(newMovie);
        return;
      }
    }
  }

  /** Удаляет элемент по значению его id */
  public void removeById(long id) {
    movies.removeIf(movie -> movie.getId().equals(id));
  }

  /** Очищает коллекцию */
  public void clear() {
    movies.clear();
  }

  /** Выводит все элементы коллекции в строковом представлении */
  public void show() {
    int index = 1; // Нумерация начинается с 1
    for (Movie movie : movies) {
      System.out.println("Фильм " + index + ": " + movie + "\n");
      index++;
    }
    if (movies.isEmpty()) {
      System.out.println("В коллекции нет элементов");
    }
  }

  /** Меняет порядок элементов коллекции на противоположный */
  public void reorder() {
    Collections.reverse(movies);
  }

  /** Удаляет элемент коллекции по индексу */
  public void removeAt(int index) {
    if (index >= 0 && index < movies.size()) {
      movies.remove(index);
    } else {
      System.out.println("Ошибка: неверный индекс");
    }
  }

  /** Удаляет первый элемент коллекции */
  public void removeFirst() {
    if (!movies.isEmpty()) {
      movies.removeFirst();
    } else {
      System.out.println("В коллекции нет элементов");
    }
  }

  /** Выводит сумму значений поля length для всех элементов коллекции */
  public void sumOfLength() {
    int sum = movies.stream().mapToInt(Movie::getLength).sum();
    System.out.println("Сумма length: " + sum);
  }

  /** Выводит количество элементов, у которых имя оператора равно заданному */
  public void countByOperator(String operatorName) {
    long count =
        movies.stream()
            .filter(m -> Objects.equals(m.getOperator().getName(), operatorName))
            .count();
    System.out.println(
        "Количество фильмов оператора " + operatorName + ": " + count);
  }

  /** Выводит количество оскаров у всех фильмов в порядке убывания */
  public void printDescendingOscarsCount() {
    movies.stream()
        .map(Movie::getOscarsCount)
        .sorted(Comparator.reverseOrder())
        .forEach(System.out::println);
  }

  /** Устанавливает новую коллекцию */
  public void setMovies(LinkedList<Movie> movies) {
    this.movies.clear();
    this.movies.addAll(movies);
  }

  /** Возвращает размер коллекции */
  public int size() {
    return movies.size();
  }

  /** Сохраняет коллекцию в файл save_movies.xml */
  public void save() {
    XMLHandler xmlHandler = new XMLHandler("save_movies.xml");
    xmlHandler.save((LinkedList<Movie>) movies);
  }

  /** Возвращает элемент коллекции по индексу */
  public Movie get(int index) {
    return movies.get(index);
  }
}
