package org.projectrocket.restapi.webserver

import akka.actor.ActorSystem
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.stream.ActorMaterializer

/**
  * Created by bento on 24/01/2017.
  */
trait RestService {
  implicit val system:ActorSystem
  implicit val materializer:ActorMaterializer

  val route:Route =
    path("hello") {
      get {
        complete {
          HttpResponse(entity = HttpEntity(ContentTypes.`text/html(UTF-8)`,
            "<html><body>Hello world!</body></html>"))
        }
      }
    } ~
      path("ping") {
        get {
          complete {
            HttpResponse(entity = HttpEntity(ContentTypes.`text/html(UTF-8)`,
              "<html><body>pong</body></html>"))
          }
        }
      }
}
