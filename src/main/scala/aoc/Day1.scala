package aoc

import aoc.*

import scala.collection.mutable
import scala.io.{BufferedSource, Source}
import scala.util.{Sorting, Using}

def day1(): mutable.Map[Int, Int] = {
  val elves = mutable.Map[Int, Int]()
  var idx = 0
  applyToEachInputLine("day1", { line =>
    if (line.isBlank) idx += 1
    else elves.put(idx, elves.getOrElse(idx, 0) + line.toInt)
  })
  elves
}

def day1part1(): Unit = {
  println(s"day 1 part 1: ${day1().values.max}")
}

def day1part2(): Unit = {
  val calories = day1().values.toArray
  Sorting.quickSort(calories)
  println(s"day 1 part 2: ${calories.reverse.take(3).sum}")
}
