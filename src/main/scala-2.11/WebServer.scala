import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer

import scala.io.StdIn

/**
  * Created by bento on 18/01/2017.
  */
object WebServer {
  def main(args: Array[String]) {

    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher

    val route: Route =
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


    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
