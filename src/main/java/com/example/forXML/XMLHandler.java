package com.example.forXML;

import com.example.forCollection.classes.Movie;
import jakarta.xml.bind.*;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

/**
 * Сохранение и загрузка коллекции в XML. Документация взята из <a
 * href="https://javarush.com/quests/lectures/questcollections.level03.lecture07">JavaRush</a>
 */
public class XMLHandler {
  private String filePath;

  public XMLHandler(String filePath) {
    this.filePath = filePath;
  }

  /** Сохранение коллекции в файл, хранящийся в директории с jar архивом (./resources/xml/) */
  public void save(LinkedList<Movie> movies) {
    try {
      // Получаем путь к JAR-файлу
      String jarPath =
          new File(XMLHandler.class.getProtectionDomain().getCodeSource().getLocation().toURI())
              .getParent();
      this.filePath = jarPath + "/resources/xml/" + filePath;
    } catch (URISyntaxException e) {
      System.err.println("\u001B[31mОшибка: некорректный URI!\u001B[0m");
    }

    File file = new File(filePath);

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
      System.out.println("\u001B[31mОшибка при сохранении XML: \u001B[0m" + e.getMessage());
    }
  }

  /** Загрузка коллекции из JAR-файла */
  public LinkedList<Movie> loadJar() {
    try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
      if (inputStream == null) {
        System.out.println("\u001B[31mФайл не найден\u001B[0m");
        return new LinkedList<>();
      }

      try (BufferedInputStream bis = new BufferedInputStream(inputStream);
          InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);
          BufferedReader br = new BufferedReader(isr)) {

        return getMovies(br);
      }

    } catch (Exception e) {
      System.out.println("\u001B[31mОшибка загрузки XML: \u001B[0m" + e.getMessage());
      return new LinkedList<>();
    }
  }

  /**
   * Загрузка коллекции из локального репозитория, хранящегося в директории с jar архивом
   * (./resources/xml/)
   */
  public LinkedList<Movie> loadLocal() {
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      return getMovies(br);
    } catch (FileNotFoundException e) {
      System.out.println("\u001B[31mФайл не найден\u001B[0m");
    } catch (IOException e) {
      System.out.println("\u001B[31mОшибка при чтении файла: \u001B[0m" + e.getMessage());
    } catch (Exception e) {
      System.out.println("\u001B[31mОшибка загрузки XML: \u001B[0m" + e.getMessage());
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
    for (Movie movie : movies) {
      validateMovie(movie);
    }
    return movies;
  }

  /** Валидация параметров фильма */
  private void validateMovie(Movie movie) {
    if (movie.getName() == null || movie.getName().trim().isEmpty()) {
      throw new IllegalArgumentException("\u001B[31mMovie name cannot be null or empty\u001B[0m");
    }
    if (movie.getCoordinates() == null) {
      throw new IllegalArgumentException("\u001B[31mMovie coordinates cannot be null\u001B[0m");
    }
    if (movie.getOscarsCount() <= 0) {
      throw new IllegalArgumentException(
          "\u001B[31mOscars count must be a positive number\u001B[0m");
    }
    if (movie.getLength() == null || movie.getLength() <= 0) {
      throw new IllegalArgumentException("\u001B[31mLength must be a positive number\u001B[0m");
    }
  }
}
