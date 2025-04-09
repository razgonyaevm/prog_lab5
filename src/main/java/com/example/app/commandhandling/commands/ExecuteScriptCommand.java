package com.example.app.commandhandling.commands;

import static com.example.app.Program.notSimpleMethods;

import com.example.app.commandhandling.Command;
import com.example.app.commandhandling.CommandInvoker;
import com.example.service.MovieCollection;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/** Выполнение скрипта */
public class ExecuteScriptCommand implements Command {
  private final MovieCollection collection;
  private final String fileName;
  private final CommandInvoker invoker;

  public ExecuteScriptCommand(MovieCollection collection, String fileName, CommandInvoker invoker) {
    this.collection = collection;
    this.fileName = fileName;
    this.invoker = invoker;
  }

  @Override
  public void execute() {
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      Scanner fileScanner = new Scanner(br);
      while (fileScanner.hasNextLine()) {
        String command = fileScanner.nextLine().trim();
        notSimpleMethods(command, collection, fileScanner, invoker);
      }
    } catch (FileNotFoundException e) {
      System.out.println("Файл не найден");
    } catch (Exception e) {
      System.out.println("Ошибка выполнения скрипта: " + e.getMessage());
    }
  }
}
