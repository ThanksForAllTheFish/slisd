package org.mdavi.slisd.sizer.main

import scala.collection.immutable.Map
import org.mdavi.slisd.sizer.timer.Timer
import org.mdavi.slisd.sizer.ConcurrentOperation
import org.mdavi.slisd.sizer.RealSizer
import org.mdavi.slisd.sizer.SequentialOperation

object Main {
  
  def main(args: Array[String]) {
    val urls = getUrls
    val ss = new SequentialOperation(urls)
    val cs = new ConcurrentOperation(urls)
    
    val timer = new Timer
    sizeOutput(ss, cs, timer)
    linkCountOutput(ss, cs, timer)
      
  }
  
  private def getUrls(): List[String] = {
    val stackOverFlow = "http://stackoverflow.com"
    val linux = "http://www.linux.org"
    val github = "https://github.com"
    val scalaLang = "http://scala-lang.org/"
    List(stackOverFlow, linux, github, scalaLang)
  }
  
  private def sizeOutput(ss: org.mdavi.slisd.sizer.SequentialOperation, cs: org.mdavi.slisd.sizer.ConcurrentOperation, timer: org.mdavi.slisd.sizer.timer.Timer): Unit = {
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
  
  private def linkCountOutput(ss: org.mdavi.slisd.sizer.SequentialOperation, cs: org.mdavi.slisd.sizer.ConcurrentOperation, timer: org.mdavi.slisd.sizer.timer.Timer): Unit = {
    
    val ssl = timer.misureTime { ss.count }
    val ccl = timer.misureTime { cs.count }
    
    println("Sequential linker took " + ssl._1 + " ms, while concurrent linker took " + ccl._1 + " ms")
    println("Sequential sites links");
    ssl._2.asInstanceOf[Map[String, Int]].foreach {
      entry =>
        println(entry._1 + " has " + entry._2 + " links")
    }
    
    println("Concurrent sites links");
    ccl._2.asInstanceOf[Map[String, Int]].foreach {
      entry =>
        println(entry._1 + " has " + entry._2 + " links")
    }
  }
}