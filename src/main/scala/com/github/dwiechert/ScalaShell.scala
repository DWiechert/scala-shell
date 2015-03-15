package com.github.dwiechert

import scala.swing.FlowPanel
import scala.swing.MainFrame
import scala.swing.ScrollPane
import scala.swing.SimpleSwingApplication

object ScalaShell extends SimpleSwingApplication {
  val userName = System.getProperty("user.name")
  
  var currentDir = System.getProperty("user.dir")

  def top = new MainFrame {
    title = "scala-shell"
    
    val inputArea = new InputArea(userName + ":~$ ")
    
    contents = new FlowPanel(new ScrollPane(inputArea))
  }
}