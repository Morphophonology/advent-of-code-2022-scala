package aoc

import scala.io.{BufferedSource, Source}
import scala.util.{Try, Using}

def applyToInput(day: String, func: BufferedSource => Unit): Unit = {
  Using(Source.fromFile(s"src/main/resources/$day/input")).apply(func)
}

def getInputString(day: String): String = {
  var input: String = ""
  applyToInput(day, reader => input = reader.mkString)
  input
}

def getAllInputLines(day: String): Seq[String] = {
  var lines: Seq[String] = Seq.empty
  applyToInput(day, reader => lines = reader.getLines().toSeq)
  lines
}
def applyToEachInputLine(day: String, func: String => Unit): Unit = {
  getAllInputLines(day).foreach(func)
}
