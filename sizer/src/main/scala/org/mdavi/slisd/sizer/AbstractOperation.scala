package org.mdavi.slisd.sizer

abstract class AbstractOperation(val urls: List[String]) {

  def size(): Map[String, Int]
  
  def count(): Map[String, Int]
}