package org.mdavi.slisd.sizer

import scala.io.Source
import scala.collection.immutable.Map

trait Sizer {

  def getSize(url : String) : Int = {
    Source.fromURL(url).mkString.getBytes().length
  }
}

abstract class RealSizer(val urls: List[String]) {
  def size(): Map[String, Int]
}