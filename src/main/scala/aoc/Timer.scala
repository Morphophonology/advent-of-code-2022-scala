package aoc

def time[R](block: R): R = {
  val t0 = System.nanoTime()
  val result = block
  val t1 = System.nanoTime()
  println(s"exec time: ${t1 - t0}ns")
  result
}
