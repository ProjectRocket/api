package org.projectrocket.restapi.webserver

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.stream.ActorMaterializer

import scala.io.StdIn

/**
  * Created by bento on 18/01/2017.
  */
class RestServer(implicit val system: ActorSystem,
                 implicit val materializer: ActorMaterializer) extends RestService {
  def startServer(address: String, port: Int) = {
    Http().bindAndHandle(route, address, port)
  }
}


object RestServer {
  def main(args: Array[String]) {

    implicit val actorSystem = ActorSystem("rest-server")
    implicit val materializer = ActorMaterializer()

    val server = new RestServer()
    val bindingFuture = server.startServer("localhost",8080)
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = actorSystem.dispatcher

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => actorSystem.terminate()) // and shutdown when done
  }
}
