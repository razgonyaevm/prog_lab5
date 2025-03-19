package com.example.mainProgram.forXML;

import com.example.toCollection.classes.Movie;
import jakarta.xml.bind.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

/**
 * Сохранение и загрузка коллекции в XML. Документация взята из <a
 * href="https://javarush.com/quests/lectures/questcollections.level03.lecture07">JavaRush</a>
 */
public class XMLHandler {
  private final String filePath;

  public XMLHandler(String filePath) {
    this.filePath = filePath;
  }

  public void save(LinkedList<Movie> movies) {
    try (OutputStream outputStream = new FileOutputStream(filePath);
        BufferedOutputStream bos = new BufferedOutputStream(outputStream);
        OutputStreamWriter osw = new OutputStreamWriter(bos, StandardCharsets.UTF_8); ) {

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

  public LinkedList<Movie> load() {
    try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
      if (inputStream == null) {
        System.out.println("Файл не найден");
        return new LinkedList<>();
      }

      try (BufferedInputStream bis = new BufferedInputStream(inputStream);
          InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);
          BufferedReader br = new BufferedReader(isr)) {

        JAXBContext context = JAXBContext.newInstance(MovieCollectionWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        MovieCollectionWrapper wrapper = (MovieCollectionWrapper) unmarshaller.unmarshal(br);
        return wrapper.getMovies();
      }

    } catch (Exception e) {
      System.out.println("Ошибка загрузки XML: " + e.getMessage());
      return new LinkedList<>();
    }
  }
}
