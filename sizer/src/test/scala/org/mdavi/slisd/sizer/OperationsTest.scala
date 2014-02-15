package org.mdavi.slisd.sizer

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.junit.Ignore

@RunWith(classOf[JUnitRunner])
class OperationsTest extends UnitSpec {
  val urls = getUrls

  describe("An operator") {
    describe("When given a list of urls") {
      it("sequentially computes the size of every page and the execution time") {
        sizerUnderTest(new SequentialOperation(urls), urls)
      }

      it("sequentially counts the links in the page") {
        linkCounterUnderTest(new SequentialOperation(urls), urls)
      }

      it("concurrently computes the size of every page and the execution time") {
        sizerUnderTest(new ConcurrentOperation(urls), urls)
      }

      it("concurrently counts the links in the page") {
        linkCounterUnderTest(new ConcurrentOperation(urls), urls)
      }

      ignore("shows same results for concurrent and sequential version") {
        val seq = new SequentialOperation(urls)
        val con = new ConcurrentOperation(urls)

        sameAs(seq.size, con.size)
        sameAs(seq.count, con.count)
      }
    }
  }

  private def sameAs(func1: () => Map[String, Int], func2: () => Map[String, Int]) {
    val seqSize = func1.apply
    val conSize = func2.apply
    seqSize.foreach {
      entry =>
        withClue(entry._1) { entry._2 should equal(conSize.getOrElse(entry._1, 0)) }
    }
  }

  private def getUrls(): List[String] = {
    val stackOverFlow = "http://stackoverflow.com"
    val linux = "http://www.linux.org"
    val github = "https://github.com"
    val scalaLang = "http://scala-lang.org/"
    List(stackOverFlow, linux, github, scalaLang)
  }

  private def linkCounterUnderTest(linkCounter: AbstractOperation, urls: List[String]) {
    val links = linkCounter.count()

    withClue(urls(0)) { links.getOrElse(urls(0), -1) should be >= 0 }
    withClue(urls(1)) { links.getOrElse(urls(1), -1) should be >= 0 }
    withClue(urls(2)) { links.getOrElse(urls(2), -1) should be >= 0 }
    withClue(urls(3)) { links.getOrElse(urls(3), -1) should be >= 0 }
  }

  private def sizerUnderTest(sizer: AbstractOperation, urls: List[String]) {
    val sizes = sizer.size()

    withClue(urls(0)) { sizes.getOrElse(urls(0), 0) should be > 0 }
    withClue(urls(1)) { sizes.getOrElse(urls(1), 0) should be > 0 }
    withClue(urls(2)) { sizes.getOrElse(urls(2), 0) should be > 0 }
    withClue(urls(3)) { sizes.getOrElse(urls(3), 0) should be > 0 }
  }
}