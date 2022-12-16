package aoc

import scala.io.BufferedSource

def day4(part: String, test: (Set[Int], Set[Int]) => Boolean): Unit = {
  var counter = 0
  applyToEachInputLine("day4", { line =>
    val sets: Array[Set[Int]] = line.split(",").map(s => {
      val ranges = s.split("-")
      Range.inclusive(ranges(0).toInt, ranges(1).toInt).toSet
    })
    if (test(sets(0), sets(1))) counter += 1
  })
  println(s"day 4 part $part: $counter")
}

def day4v2(part: String, test: Array[Set[Int]] => Boolean): Unit = {
  println(s"day 4 part $part: ${
    getAllInputLines("day4")
      .map(_.split(",")
        .map(_.split("-"))
        .map(ranges => Range.inclusive(ranges(0).toInt, ranges(1).toInt).toSet))
      .count(test(_))
  }")
}

def day4part1(): Unit = {
  day4("part1", (set1, set2) => set1.subsetOf(set2) || set2.subsetOf(set1))
}

def day4part2(): Unit = {
  day4("part2", (set1, set2) => set1.intersect(set2).nonEmpty)
}

def day4part1v2(): Unit = {
  day4v2("part1", sets => sets(0).subsetOf(sets(1)) || sets(1).subsetOf(sets(0)))
}

def day4part2v2(): Unit = {
  day4v2("part2", sets => sets(0).intersect(sets(1)).nonEmpty)
}
