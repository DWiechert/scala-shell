package com.github.dwiechert.commands

import com.github.dwiechert.InputArea

sealed abstract class Command {
  def run(line: Array[String]): Any
}

case class Echo(inputArea: InputArea) extends Command {
  def run(line: Array[String]): Any = {
    line.foreach { l => inputArea.append(l + " ") }
    inputArea.append(System.getProperty("line.separator"))
    None
  }
}

case class Exit() extends Command {
  def run(line: Array[String]): Any = {
    System.exit(0)
  }
}