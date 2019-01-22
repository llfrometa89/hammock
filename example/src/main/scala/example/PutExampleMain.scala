package example

import cats.effect._
import hammock._
import hammock.apache._
import hammock.circe.implicits._
import hammock.marshalling._
import io.circe.generic.auto._

object PutExampleMain extends App {
  implicit val interpTrans: ApacheInterpreter[IO] = ApacheInterpreter[IO]

  case class Resp(data: String)
  case class Req(name: String, number: Int)

  val uri = uri"http://httpbin.org/put"

  val resp = Hammock
    .request(Method.PUT, uri, Map(), Some(Req("name", 4)))
    .as[Resp]
    .exec[IO]
    .unsafeRunSync


  val resp1 = Hammock
    .request(Method.POST, uri"http://httpbin.org/status/200", Map.empty[String,String])
    .exec[IO]
    .unsafeRunSync

  println(resp)
}
