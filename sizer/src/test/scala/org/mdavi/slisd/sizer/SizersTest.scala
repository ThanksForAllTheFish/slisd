package org.mdavi.slisd.sizer

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SizersTest extends UnitSpec {
  val urls = getUrls

  describe("A sizer") {
    describe("When given a list of urls") {
      it("sequentially computes the size of every page and the execution time") {
        sizerUnderTest(new SequentialSizer(urls), urls)
      }
      
      it("concurrently computes the size of every page and the execution time") {
        sizerUnderTest(new ConcurrentSizer(urls), urls)
      }
    }
  }

  private def getUrls(): List[String] = {
    val stackOverFlow = "http://stackoverflow.com"
    val linux = "http://www.linux.org"
    val github = "https://github.com"
    val scalaLang = "http://scala-lang.org/"
    List(stackOverFlow, linux, github, scalaLang)
  }

  private def sizerUnderTest(sizer: RealSizer, urls: List[String]) {
    val sizes = sizer.size();

    withClue(urls(0)) { sizes.getOrElse(urls(0), 0) should be > 0 }
    withClue(urls(1)) { sizes.getOrElse(urls(1), 0) should be > 0 }
    withClue(urls(2)) { sizes.getOrElse(urls(2), 0) should be > 0 }
    withClue(urls(3)) { sizes.getOrElse(urls(3), 0) should be > 0 }
  }
}