package com.github.dwiechert.commands

import java.io.File

sealed abstract class Command {
  protected[this] val NEWLINE = System.getProperty("line.separator")

  var currentDir = System.getProperty("user.dir")
  def run(line: Array[String]): Any
}

case class Exit() extends Command {
  def run(line: Array[String]): Any = {
    System.exit(0)
  }
}

case class Invalid(command: String) extends Command {
  def run(line: Array[String]): Any = {
    val builder = new StringBuilder
    builder.append(s"Command ${command} is invalid.")
    builder.append(NEWLINE)
    builder.toString()
  }
}

case class Echo() extends Command {
  def run(line: Array[String]): Any = {
    val builder = new StringBuilder
    line.foreach { l => builder.append(l + " ") }
    builder.append(NEWLINE)
    builder.toString()
  }
}

case class Pwd() extends Command {
  def run(line: Array[String]): Any = {
    val builder = new StringBuilder
    builder.append(new File(currentDir).getAbsolutePath)
    builder.append(NEWLINE)
    builder.toString()
  }
}

case class Ls() extends Command {
  def run(line: Array[String]): Any = {
    val directory = if (line.isEmpty) currentDir else line(0)
    val builder = new StringBuilder
    for (file <- new File(directory).listFiles()) {
      builder.append(file.getName)
      if (file.isDirectory()) builder.append(File.separatorChar)
      builder.append(NEWLINE)
    }
    builder.toString()
  }
}