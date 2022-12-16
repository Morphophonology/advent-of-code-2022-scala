package aoc

import scala.collection.mutable
import scala.collection.mutable.Stack
import scala.io.BufferedSource
import scala.util.matching.Regex

class SupplyCrateAutomaton(moveFunc: (Int, mutable.Stack[String], mutable.Stack[String]) => Unit) {
  private val movePattern: Regex = "move (\\d+) from (\\d+) to (\\d+)".r
  private var state: List[mutable.Stack[String]] = List.empty

  def initialize(stateContent: Seq[String], header: String): Unit = {
    state = List.fill(header.last.asDigit)(mutable.Stack.empty)
    stateContent.map(_.grouped(4)).foreach { chunk =>
      var i = 0
      for (crate <- chunk) {
        val cleanCrate = crate.trim.replaceAll("[\\[\\]]", "")
        if (!cleanCrate.isBlank) state(i).push(cleanCrate)
        i += 1
      }
    }
    state = state.map(_.reverse)
  }

  def move(moveInstruction: String): Unit = {
    val movePattern(numCrates, fromStack, toStack) = moveInstruction: @unchecked
    move(numCrates.toInt, fromStack.toInt - 1, toStack.toInt - 1)
  }

  def move(numCrates: Int, fromStack: Int, toStack: Int): Unit = {
    moveFunc(numCrates, state(fromStack), state(toStack))
  }

  override def toString: String = {
    state.map(_.top).mkString
  }
}

def day5(part: String, moveFunc: (Int, mutable.Stack[String], mutable.Stack[String]) => Unit): Unit = {
  val automaton = new SupplyCrateAutomaton(moveFunc)
  val lines = getAllInputLines("day5")
  automaton.initialize(lines.slice(0, 8), lines(8))
  lines.slice(10, lines.length).foreach(automaton.move)
  println(s"day 5 part $part: $automaton")
}

def day5part1(): Unit = {
  day5("part1", (numCrates, src, tgt) => (0 until numCrates).foreach(_ => tgt.push(src.pop)))
}
def day5part2(): Unit = {
  day5("part2", (numCrates, src, tgt) => {
    val tmp: mutable.Stack[String] = mutable.Stack.empty
    (0 until numCrates).foreach(_ => tmp.push(src.pop))
    (0 until numCrates).foreach(_ => tgt.push(tmp.pop))
  })
}
