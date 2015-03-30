package com.github.dwiechert.commands

import com.github.dwiechert.InputArea

sealed abstract class Command {
  def run(line: Array[String]): Any
}

case class Echo() extends Command {
  def run(line: Array[String]): Any = {
    val builder = new StringBuilder
    line.foreach { l => builder.append(l + " ") }
    builder.append(System.getProperty("line.separator"))
    builder.toString()
  }
}

case class Exit() extends Command {
  def run(line: Array[String]): Any = {
    System.exit(0)
  }
}