package com.github.dwiechert

import scala.swing.TextArea
import scala.swing.event.Key
import scala.swing.event.KeyReleased

class InputArea(lineStart: String) extends TextArea {
  rows = 40
  columns = 60
  append(lineStart)
  
  listenTo(keys)
  reactions += {
    case KeyReleased(_, Key.Enter, _, _) => append(lineStart)
  }
}