package com.example.validate;

import com.example.service.model.IdGenerator;
import com.example.service.model.Movie;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/* Валидация id фильмов */
public class IdsValidator implements Validator<List<Movie>> {

  static Long maxId = 0L;

  @Override
  public void validate(List<Movie> movies) {
    validateIdsExisted(movies);
    validateNoDuplicates(movies);
    updateIdGenerator();
  }

  /* Метод для проверки наличия ID */
  private void validateIdsExisted(List<Movie> movies) {
    long moviesWithoutId = movies.stream().filter(movie -> movie.getId() == null).count();

    if (moviesWithoutId > 0) {
      throw new IllegalArgumentException("Найдены фильмы без ID");
    }
  }

  /* Метод для проверки дубликатов ID */
  private void validateNoDuplicates(List<Movie> movies) {
    movies.stream()
        .map(Movie::getId)
        .filter(Objects::nonNull)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .forEach(
            (id, count) -> {
              if (count > 1)
                throw new IllegalArgumentException("В файле есть ID, которые дублируются");
              updateMaxId(id);
            });
  }

  /* Метод для обновления максимального ID */
  public static void updateMaxId(Long id) {
    maxId = Math.max(maxId, id);
  }

  /* Метод для обновления генератора ID */
  private void updateIdGenerator() {
    IdGenerator.reset(maxId + 1);
  }
}
