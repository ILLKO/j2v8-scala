package org.intracer

import java.io.File

import com.eclipsesource.v8.{JavaCallback, V8, V8Array, V8Object}
import org.specs2.mutable.Specification

import scala.io.Source

class V8Spec extends Specification {

  def v8Strings(strings: Seq[String], runtime: V8): V8Array = {
    val array = new V8Array(runtime)
    strings.foldLeft(array)((r, s) => r.push(s))
    array
  }

  "V8" should {
    "pass data from/to java" in {
      val runtime = V8.createV8Runtime()

      val lines = Source.fromFile(new File("data/zips.json")).getLines().toBuffer

      val dataCallback = new JavaCallback() {
        override def invoke(receiver: V8Object, parameters: V8Array): V8Array = v8Strings(lines, runtime)
      }

      runtime.registerJavaMethod(dataCallback, "dataCallback")
      runtime.executeVoidScript("var ids = dataCallback().length;")

      val ids = runtime.getInteger("ids")

      ids === lines.length
    }

    "fetch data" in {
      val runtime = V8.createV8Runtime()
      var response = ""
      val responseCallback = new JavaCallback() {
        override def invoke(receiver: V8Object, parameters: V8Array)  = {
          response = parameters.getString(0)
          "something"
        }
      }
      runtime.registerJavaMethod(responseCallback, "responseCallback")

      val script = Source.fromFile(new File("web/fetch.js")).getLines().toBuffer.mkString("\n")

      runtime.executeVoidScript(script)

//      Thread.sleep(10000)
      eventually {
        response === "qwerty"
      }
//      ids === lines.length
    }

  }

}
