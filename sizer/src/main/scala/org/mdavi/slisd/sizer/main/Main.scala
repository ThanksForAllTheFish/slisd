package org.mdavi.slisd.sizer.main

import scala.collection.immutable.Map
import org.mdavi.slisd.sizer.timer.Timer
import org.mdavi.slisd.sizer.ConcurrentSizer
import org.mdavi.slisd.sizer.RealSizer
import org.mdavi.slisd.sizer.SequentialSizer

object Main {
  
  def main(args: Array[String]) {
    val urls = getUrls
    val ss = new SequentialSizer(urls)
    val cs = new ConcurrentSizer(urls)
    
    val timer = new Timer
    val ssd = timer.misureTime { ss.size }
    val csd = timer.misureTime { cs.size }
    
    println("Sequential sizer took " + ssd._1 + " ms, while concurrent sizer took " + csd._1 + " ms")
    println("Sequential sites sizes");
    ssd._2.asInstanceOf[Map[String, Int]].foreach {
      entry =>
        println(entry._1 + " is " + entry._2 + " B")
    }
    
    println("Concurrent sites sizes");
    csd._2.asInstanceOf[Map[String, Int]].foreach {
      entry =>
        println(entry._1 + " is " + entry._2 + " B")
    }
      
  }
  
  private def getUrls(): List[String] = {
    val stackOverFlow = "http://stackoverflow.com"
    val linux = "http://www.linux.org"
    val github = "https://github.com"
    val scalaLang = "http://scala-lang.org/"
    List(stackOverFlow, linux, github, scalaLang)
  }
}