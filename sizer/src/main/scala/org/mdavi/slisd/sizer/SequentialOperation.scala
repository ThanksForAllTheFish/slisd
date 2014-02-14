package org.mdavi.slisd.sizer

import scala.collection.immutable.Map
import org.mdavi.slisd.sizer.link.counter.LinkCounter

class SequentialOperation(override val urls: List[String]) extends AbstractOperation(urls) with Sizer with LinkCounter {
  
  @Override
  def size() : Map[String, Int] = {
    val size = (r : Map[String, Int], url : String) => r + (url -> getSize(url))
    val sizes = urls.foldLeft(Map.empty[String, Int])(size)
    sizes
  }
  
  @Override
  def count(): Map[String, Int] = {
    val linkNumber = (r : Map[String, Int], url : String) => r + (url -> linkCount(url))
    val links = urls.foldLeft(Map.empty[String, Int])(linkNumber)
    links
  }
}