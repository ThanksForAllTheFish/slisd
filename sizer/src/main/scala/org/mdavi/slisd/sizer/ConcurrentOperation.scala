package org.mdavi.slisd.sizer

import scala.collection.immutable.Map
import scala.actors._
import Actor._
import org.mdavi.slisd.sizer.link.counter.LinkCounter

class ConcurrentOperation(override val urls: List[String]) extends AbstractOperation(urls) with Sizer with LinkCounter {
  
  @Override
  def size(): Map[String, Int] = {
    execute( getSize )
  }
  
  @Override
  def count(): Map[String, Int] = {
    execute( linkCount )
  }
  
  private def execute(func: (String) => Int): scala.collection.immutable.Map[String,Int] = {
    val sizer = self
    urls.foreach {
      url => 
      actor { sizer ! (url, func(url)) }
    }
    
    def size = (r: Map[String, Int], url: String) => {
      receive {
        case (url: String, size: Int) => r + (url -> size)
      }
    }
    urls.foldLeft(Map.empty[String, Int])(size)
  }

}