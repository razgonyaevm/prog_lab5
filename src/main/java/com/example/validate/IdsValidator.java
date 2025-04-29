package com.example.validate;

import com.example.service.model.IdGenerator;
import com.example.service.model.Movie;
import java.util.*;

public class IdsValidator implements Validator<List<Movie>> {
  @Override
  public void validate(List<Movie> movies) {
    validateAndGenerateIds(movies);
  }

  /* Метод для валидации и генерации ID */
  private void validateAndGenerateIds(List<Movie> movies) {
    // Сбрасываем генератор ID для того, чтобы новые ID не конфликтовали с существующими
    resetIdGenerator(movies);

    // Проверяем уникальность ID и генерируем новые для элементов без ID
    for (Movie movie : movies) {
      if (movie.getId() == null) {
        movie.updateId(IdGenerator.getNextId());
      }
    }
  }

  /* Метод для сброса генератора ID */
  private void resetIdGenerator(List<Movie> movies) {

    // Ищем максимальный ID в загружаемой коллекции и устанавливаем флаг, если в файле есть movies с
    // id
    boolean hasIds = movies.stream().anyMatch(movie -> movie.getId() != null);
    long maxId =
        movies.stream()
            .map(Movie::getId)
            .filter(Objects::nonNull)
            .max(Comparator.naturalOrder())
            .orElse(0L);

    // Если нашли какие-нибудь ID, устанавливаем новое стартовое значение генератора
    if (hasIds) {
      IdGenerator.reset(maxId + 1);

      // Проверяем уникальность всех id
      checkDuplicateIds(movies);
    }
  }

  /* Метод для проверки дубликатов ID */
  private void checkDuplicateIds(List<Movie> movies) {
    Set<Long> ids = new HashSet<>();
    for (Movie movie : movies) {
      if (movie.getId() != null) {
        if (ids.contains(movie.getId())) {
          throw new IllegalArgumentException("Найдены дубликаты ID в XML файле: " + movie.getId());
        }
        ids.add(movie.getId());
      }
    }
  }
}
