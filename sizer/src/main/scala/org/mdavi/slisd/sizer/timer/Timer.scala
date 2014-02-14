package org.mdavi.slisd.sizer.timer

class Timer {

  def misureTime(method : () => Any) : (Long, Any) = {
    val start = System.nanoTime()
    val value = method()
    val end = System.nanoTime()
    ((end - start)/1000000, value)
  }
}