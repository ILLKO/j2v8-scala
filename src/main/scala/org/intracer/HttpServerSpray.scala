package org.intracer

import akka.actor.ActorSystem
import akka.util.Timeout
import spray.http.MediaTypes.`text/html`
import spray.http._
import spray.routing._
import scala.concurrent.duration._

class HttpServerSpray extends SimpleRoutingApp {

  implicit val system = ActorSystem("server")

  implicit val bindingTimeout: Timeout = 2.seconds

  def start() = {

    startServer(interface = "localhost", port = 8080) {
      (pathSingleSlash & get) {
        getFromFile("web/index.html")
      } ~
        (path("ping") & get) {
          complete("pong")
        }

    }
  }
}

object HttpServerSpray extends App {
  private val spray = new HttpServerSpray()
  spray.start()
}



