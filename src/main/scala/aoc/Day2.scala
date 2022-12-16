package aoc

import aoc.*

import scala.io.Source
import scala.util.Using

val gameSituationMap: Map[(String, String), String] = Map(
  ("Rock", "Rock") -> "draw",
  ("Rock", "Paper") -> "win",
  ("Rock", "Scissors") -> "lose",
  ("Paper", "Paper") -> "draw",
  ("Paper", "Rock") -> "lose",
  ("Paper", "Scissors") -> "win",
  ("Scissors", "Scissors") -> "draw",
  ("Scissors", "Rock") -> "win",
  ("Scissors", "Paper") -> "lose"
)

val handScoreMap: Map[String, Int] = Map(
  "Rock" -> 1,
  "Paper" -> 2,
  "Scissors" -> 3
)

val gameSituationScoreMap: Map[String, Int] = Map(
  "lose" -> 0,
  "draw" -> 3,
  "win" -> 6
)

val handMap: Map[String, String] = Map(
  "A" -> "Rock",
  "B" -> "Paper",
  "C" -> "Scissors",
  "X" -> "Rock",
  "Y" -> "Paper",
  "Z" -> "Scissors"
)

val handRequestMap: Map[String, String] = Map(
  "X" -> "lose",
  "Y" -> "draw",
  "Z" -> "win"
)

def determineRequestedHand(opponentHand: String, ownHand: String): String = {
  val requestedSituation = handRequestMap(ownHand)
  var requestedHand = ""
  for (gameSituation <- gameSituationMap) {
    if (gameSituation._1._1 == opponentHand && gameSituation._2 == requestedSituation) requestedHand = gameSituation._1._2
  }
  requestedHand
}

def determineScorePart1(opponentHand: String, ownHand: String): Int = {
  val replacedOpponentHand = handMap(opponentHand)
  val replacedOwnHand = handMap(ownHand)
  handScoreMap(replacedOwnHand) + gameSituationScoreMap(gameSituationMap(replacedOpponentHand, replacedOwnHand))
}

def determineScorePart2(opponentHand: String, ownHand: String): Int = {
  val replacedOpponentHand = handMap(opponentHand)
  val requestedOwnHand = determineRequestedHand(replacedOpponentHand, ownHand)
  handScoreMap(requestedOwnHand) + gameSituationScoreMap(gameSituationMap(replacedOpponentHand, requestedOwnHand))
}

def day2(determineScore: (String, String) => Int): Int = {
  var score: Int = 0
  applyToEachInputLine("day2", { line =>
    val game = line.split(' ')
    score += determineScore(game(0), game(1))
  })
  score
}

def day2part1(): Unit = {
  println(s"day 2 part 1: ${day2(determineScorePart1)}")
}

def day2part2(): Unit = {
  println(s"day 2 part 2: ${day2(determineScorePart2)}")
}
