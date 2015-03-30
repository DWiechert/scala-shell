package com.github.dwiechert.commands

import java.io.File

sealed abstract class Command {
  var currentDir = System.getProperty("user.dir")
  def run(line: Array[String]): Any
}

case class Exit() extends Command {
  def run(line: Array[String]): Any = {
    System.exit(0)
  }
}

case class Echo() extends Command {
  def run(line: Array[String]): Any = {
    val builder = new StringBuilder
    line.foreach { l => builder.append(l + " ") }
    builder.append(System.getProperty("line.separator"))
    builder.toString()
  }
}

case class Pwd() extends Command {
  def run(line: Array[String]): Any = {
    val builder = new StringBuilder
    builder.append(new File(currentDir).getAbsolutePath)
    builder.append(System.getProperty("line.separator"))
    builder.toString()
  }
}