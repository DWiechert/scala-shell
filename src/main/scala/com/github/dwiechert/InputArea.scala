package com.github.dwiechert

import java.util.regex.Pattern

import scala.swing.TextArea
import scala.swing.event.Key
import scala.swing.event.KeyReleased

import com.github.dwiechert.commands.Echo

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
      command match {
        case "echo" => new Echo(this).run(arguments)
      }
      append(lineStart)
    }
  }
}