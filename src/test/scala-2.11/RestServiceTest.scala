import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.projectrocket.restapi.webserver.RestService
import org.scalatest.{Matchers, WordSpec}
import akka.http.scaladsl.model.StatusCodes

/**
  * Created by bento on 24/01/2017.
  */
class RestServiceTest extends WordSpec with Matchers with ScalatestRouteTest with RestService {

  implicit val tildeArrow = TildeArrow.injectIntoRoute

  "Rest API" should {
    "respond with a pong when GET to /ping" in {
      Get("/ping") ~> route ~> check {
        status shouldBe StatusCodes.OK
        responseAs[String] shouldEqual "<html><body>pong</body></html>"
      }
    }

    "leave GET requests to other paths unhandled" in {
      // tests:
      Get("/pathdoesnotexist") ~> route ~> check {
        handled shouldBe false
      }
    }
  }
}
