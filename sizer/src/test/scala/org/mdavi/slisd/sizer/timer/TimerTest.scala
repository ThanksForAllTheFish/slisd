package org.mdavi.slisd.sizer.timer

import org.mdavi.slisd.sizer.SequentialOperation
import org.mdavi.slisd.sizer.UnitSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TimerTest extends UnitSpec {

  describe("A timer") {
    describe("When given a method") {
      it("computes its duration in seconds and return its output") {
        val sizer = new SequentialOperation(List("https://github.com"))
        val timer = new Timer()
        val timeAndOutput = timer.misureTime(sizer.size)
        withClue("Duration should be longer ") { timeAndOutput._1 should be > 0L }
        withClue("Duration should be longer ") { timeAndOutput._2 should not be None }
      }
    }
  }
}