package com.example.app.commandhandling.commands;

import com.example.service.MovieCollection;
import com.example.app.commandhandling.Command;

/** Вывод информации о коллекции */
public class InfoCommand implements Command {
  private final MovieCollection collection;

  public InfoCommand(MovieCollection collection) {
    this.collection = collection;
  }

  @Override
  public void execute() {
    System.out.println("Тип коллекции: LinkedList");
    System.out.println("Количество элементов: " + collection.size());
    System.out.println("Дата инициализации: " + collection.getInitializationDate());
  }
}
