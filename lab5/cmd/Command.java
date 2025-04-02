package lab5.cmd;

import lab5.err.*;

interface Command {

  String getName();

  void exe(String[] args) throws InvalidCommandException;
}