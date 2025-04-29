package com.example.validate;

import com.example.service.model.IdGenerator;
import com.example.service.model.Movie;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class IdsValidator implements Validator<LinkedList<Movie>> {
  @Override
  public void validate(LinkedList<Movie> movies) {
    validateAndGenerateIds(movies);
  }

  /* Метод для валидации и генерации ID */
  private void validateAndGenerateIds(LinkedList<Movie> movies) {
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
  private void resetIdGenerator(LinkedList<Movie> movies) {
    long maxId = 0;
    boolean hasIds = false;

    // Ищем максимальный ID в загружаемой коллекции
    for (Movie movie : movies) {
      if (movie.getId() != null) {
        hasIds = true;
        if (movie.getId() > maxId) {
          maxId = movie.getId();
        }
      }
    }

    // Если нашли какие-нибудь ID, устанавливаем новое стартовое значение генератора
    if (hasIds) {
      IdGenerator.reset(maxId + 1);

      // Проверяем уникальность всех id
      checkDuplicateIds(movies);
    }
  }

  /* Метод для проверки дубликатов ID */
  private void checkDuplicateIds(LinkedList<Movie> movies) {
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
