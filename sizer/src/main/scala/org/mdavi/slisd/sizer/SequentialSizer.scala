package org.mdavi.slisd.sizer

import scala.collection.immutable.Map

class SequentialSizer(override val urls: List[String]) extends RealSizer(urls) with Sizer {
  
  def size() : Map[String, Int] = {
    val size = (r : Map[String, Int], url : String) => r + (url -> getSize(url))
    val sizes = urls.foldLeft(Map.empty[String, Int])(size)
    sizes
  }
}