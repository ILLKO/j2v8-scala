package org.intracer

import com.eclipsesource.v8.JavaCallback
import com.eclipsesource.v8.NodeJS
import com.eclipsesource.v8.V8Array
import com.eclipsesource.v8.V8Function
import com.eclipsesource.v8.V8Object

object J2V8 {

  def main(args: Array[String]): Unit = {
    import com.eclipsesource.v8.V8
    val runtime = V8.createV8Runtime
    val result = runtime.executeIntegerScript("var hello = 'hello, ';\n"
      + "var world = 'world!';\n"
      + "hello.concat(world).length;\n"
    )
    System.out.println(result)
  }

}
