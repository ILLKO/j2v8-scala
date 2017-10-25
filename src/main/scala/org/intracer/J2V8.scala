package org.intracer

import java.io.File

import com.eclipsesource.v8.{JavaCallback, NodeJS, V8Array, V8Object}

object J2V8 {

  def main(args: Array[String]): Unit = {
    val nodeJS = NodeJS.createNodeJS
    val callback = new JavaCallback() {
      override def invoke(receiver: V8Object, parameters: V8Array) = "Hello, JavaWorld!"
    }
    nodeJS.getRuntime.registerJavaMethod(callback, "someJavaMethod")

    val nodeScript = new File("web/nodejs.js")
    nodeJS.exec(nodeScript)
    while (nodeJS.isRunning) {
      nodeJS.handleMessage
    }
    nodeJS.release()
  }

}
