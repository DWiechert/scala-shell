package com.github.dwiechert

import java.util.regex.Pattern
import scala.swing.TextArea
import scala.swing.event.Key
import scala.swing.event.KeyReleased
import com.github.dwiechert.commands.Echo
import com.github.dwiechert.commands.Exit
import com.github.dwiechert.commands.Pwd
import com.github.dwiechert.commands.Invalid

class InputArea(lineStart: String) extends TextArea {
  rows = 40
  columns = 60
  append(lineStart)

  listenTo(keys)
  reactions += {
    case KeyReleased(_, Key.Enter, _, _) => {
      val lines = this.peer.getText.split("\n")
      val line = lines.last.split(Pattern.quote(lineStart))
      val input = line(1).split(" ")
      val command = input(0)
      val arguments = input.drop(1)
      var toAppend: Any = None
      command match {
        case "exit" => new Exit().run(null)
        case "echo" => toAppend = new Echo().run(arguments)
        case "pwd"  => toAppend = new Pwd().run(arguments)
        case _ => toAppend = new Invalid(command).run(null)
      }

      toAppend match {
        case x: String => append(toAppend.toString())
      }
      append(lineStart)
    }
  }
}