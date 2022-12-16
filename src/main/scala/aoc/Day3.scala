package aoc

import scala.io.BufferedSource

def prioritize(c: Char): Int = {
  if (c.isUpper) c.toInt - 38 else c.toInt - 96
}

def day3part1(): Unit = {
  var priority = 0
  applyToEachInputLine("day3", { line =>
    val (compartment1, compartment2) = line.toCharArray.splitAt(line.length / 2)
    priority += prioritize(compartment1.toSet.intersect(compartment2.toSet).head)
  })
  println(s"day 3 part 1: $priority")
}

def day3part2(): Unit = {
  var priority = 0
  applyToInput("day3", { reader =>
    for (group <- reader.getLines().grouped(3)) {
      priority += prioritize(group.head.toSet.intersect(group(1).toSet.intersect(group(2).toSet)).head)
    }
  })
  println(s"day 3 part 2: $priority")
}