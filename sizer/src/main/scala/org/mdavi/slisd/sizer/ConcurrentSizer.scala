package org.mdavi.slisd.sizer

import scala.collection.immutable.Map
import scala.actors._
import Actor._

class ConcurrentSizer(override val urls: List[String]) extends RealSizer(urls) with Sizer {
  
  def size(): Map[String, Int] = {
    val sizer = self
    urls.foreach {
      url => 
      actor { sizer ! (url, getSize(url)) }
    }
    
    def size = (r: Map[String, Int], url: String) => {
      receive {
        case (url: String, size: Int) => r + (url -> size)
      }
    }
    urls.foldLeft(Map.empty[String, Int])(size)
  }

}