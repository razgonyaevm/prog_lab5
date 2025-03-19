package com.example.mainProgram.forXML;

import com.example.toCollection.classes.Movie;
import jakarta.xml.bind.*;
import java.io.*;
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
    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
      JAXBContext context = JAXBContext.newInstance(MovieCollectionWrapper.class);
      Marshaller marshaller = context.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      MovieCollectionWrapper wrapper = new MovieCollectionWrapper();
      wrapper.setMovies(movies);
      marshaller.marshal(wrapper, bos);
    } catch (Exception e) {
      System.out.println("Ошибка при сохранении XML: " + e.getMessage());
    }
  }

  public LinkedList<Movie> load() {
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      JAXBContext context = JAXBContext.newInstance(MovieCollectionWrapper.class);
      Unmarshaller unmarshaller = context.createUnmarshaller();
      MovieCollectionWrapper wrapper = (MovieCollectionWrapper) unmarshaller.unmarshal(br);
      return wrapper.getMovies();
    } catch (Exception e) {
      System.out.println("Ошибка загрузки XML: " + e.getMessage());
      return new LinkedList<>();
    }
  }
}
