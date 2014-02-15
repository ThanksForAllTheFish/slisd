package org.mdavi.slisd.sizer

import scala.collection.immutable.Map
import org.mdavi.slisd.sizer.link.counter.LinkCounter

class SequentialOperation(override val urls: List[String]) extends AbstractOperation(urls) with Sizer with LinkCounter {
  
  @Override
  def size() : Map[String, Int] = {
    execute( getSize )
  }
  
  @Override
  def count(): Map[String, Int] = {
    execute( linkCount )
  }
  
  private def execute( func: (String) => Int ): scala.collection.immutable.Map[String,Int] = {
    val size = (r : Map[String, Int], url : String) => r + (url -> func(url))
    val sizes = urls.foldLeft(Map.empty[String, Int])(size)
    sizes
  }
}