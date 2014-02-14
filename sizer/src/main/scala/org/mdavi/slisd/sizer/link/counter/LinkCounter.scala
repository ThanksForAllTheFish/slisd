package org.mdavi.slisd.sizer.link.counter

import scala.io.Source
import scala.annotation.tailrec

trait LinkCounter {
  
  def linkCount(url: String) : Int = {
    val page = Source.fromURL(url).mkString
    val linkRegex = """<(a|A).*>.*</(a|A)>""".r
    linkRegex.findAllIn(page).length
  }
}