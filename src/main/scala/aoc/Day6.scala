package aoc

import scala.collection.mutable
import scala.collection.mutable.Stack
import scala.io.BufferedSource
import scala.util.matching.Regex

def getLetterSeriesPattern(num: Int): Regex = {
  var pattern = ""
  1 to num foreach (n => {
    pattern += "(\\w)"
    if (n < num) {
      pattern += s"(?!\\1"
      if (n > 1) (2 to n).foreach(pattern += "|\\" + _)
      pattern += ")"
    }
  })
  pattern.r
}

def day6part1(): Unit = {
  val input = getInputString("day6")
  println(s"day 6 part 1: ${getLetterSeriesPattern(4).findFirstMatchIn(input).map(_.end).get}")
}

def day6part2(): Unit = {
  val input = getInputString("day6")
  println(s"day 6 part 2: ${getLetterSeriesPattern(14).findFirstMatchIn(input).map(_.end).get}")
}