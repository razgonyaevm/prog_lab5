package com.example.mainProgram.forCommands.commands;

import com.example.forCollection.MovieCollection;
import com.example.mainProgram.forCommands.Command;

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
