package com.example.xml;

import com.example.service.model.IdGenerator;
import com.example.service.model.Movie;
import com.example.validate.MovieValidator;
import com.example.validate.Validator;
import jakarta.xml.bind.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Сохранение и загрузка коллекции в XML. Документация взята из <a
 * href="https://javarush.com/quests/lectures/questcollections.level03.lecture07">JavaRush</a>
 */
public class XMLHandler {
  private String filePath;

  private static final Validator<Movie> movieValidator = new MovieValidator();

  public XMLHandler(String filePath) {
    this.filePath = filePath;
  }

  /** Сохранение коллекции в файл (пользователь сам прописывает путь до файла) */
  public void save(LinkedList<Movie> movies) {

    if (filePath.charAt(0) == '~')
      filePath = System.getProperty("user.home") + filePath.substring(1);

    File file = new File(filePath);
    file.getParentFile().mkdirs();

    try (OutputStream outputStream = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(outputStream);
        OutputStreamWriter osw = new OutputStreamWriter(bos, StandardCharsets.UTF_8)) {

      JAXBContext context = JAXBContext.newInstance(MovieCollectionWrapper.class);
      Marshaller marshaller = context.createMarshaller();

      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      MovieCollectionWrapper wrapper = new MovieCollectionWrapper();
      wrapper.setMovies(movies);

      marshaller.marshal(wrapper, osw);
    } catch (Exception e) {
      System.out.println("Ошибка при сохранении XML: " + e.getMessage());
    }
  }

  /** Загрузка коллекции из JAR-файла */
  public LinkedList<Movie> loadJar() {
    try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
      if (inputStream == null) {
        System.out.println("Файл не найден");
        return new LinkedList<>();
      }

      try (BufferedInputStream bis = new BufferedInputStream(inputStream);
          InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);
          BufferedReader br = new BufferedReader(isr)) {

        return getMovies(br);
      }

    } catch (Exception e) {
      System.out.println("Ошибка загрузки XML: " + e.getMessage());
      return new LinkedList<>();
    }
  }

  /** Загрузка коллекции из локального репозитория */
  public LinkedList<Movie> loadLocal() {
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      return getMovies(br);
    } catch (FileNotFoundException e) {
      System.out.println("Файл не найден");
    } catch (IOException e) {
      System.out.println("Ошибка при чтении файла: " + e.getMessage());
    } catch (Exception e) {
      System.out.println("Ошибка загрузки XML: " + e.getMessage());
      return new LinkedList<>();
    }
    return new LinkedList<>();
  }

  /** Получение коллекции из BufferedReader */
  private LinkedList<Movie> getMovies(BufferedReader br) throws JAXBException {
    JAXBContext context = JAXBContext.newInstance(MovieCollectionWrapper.class);
    Unmarshaller unmarshaller = context.createUnmarshaller();
    MovieCollectionWrapper wrapper = (MovieCollectionWrapper) unmarshaller.unmarshal(br);

    LinkedList<Movie> movies = wrapper.getMovies();

    // Проверяем уникальность ID и генерируем новые при необходимости
    validateAndGenerateIds(movies);

    for (Movie movie : movies) {
      movieValidator.validate(movie);
    }
    return movies;
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
