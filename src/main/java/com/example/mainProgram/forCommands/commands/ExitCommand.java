package com.example.mainProgram.forCommands.commands;

import com.example.mainProgram.forCommands.Command;

/** Завершает выполнение программы */
public class ExitCommand implements Command {
  @Override
  public void execute() {
    System.out.println("Программа завершена");
    System.exit(0);
  }
}
