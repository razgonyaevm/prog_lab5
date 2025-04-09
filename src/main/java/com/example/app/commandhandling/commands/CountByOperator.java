package com.example.app.commandhandling.commands;

import com.example.service.MovieCollection;
import com.example.app.commandhandling.Command;

/** Подсчет количества фильмов, у которых имя оператора равно заданному */
public class CountByOperator implements Command {
  private final MovieCollection collection;
  private final String command;

  public CountByOperator(MovieCollection collection, String command) {
    this.collection = collection;
    this.command = command;
  }

  @Override
  public void execute() {
    if (command.length() <= 18) {
      System.out.println("Ошибка: укажите имя оператора");
      return;
    }
    collection.countByOperator(command.substring(18));
  }
}
