package example

import cats.effect._
import hammock._
import hammock.apache._
import hammock.circe.implicits._
import hammock.marshalling._
import io.circe.generic.auto._

object DeleteExampleMain extends App {
  implicit val interpTrans: ApacheInterpreter[IO] = ApacheInterpreter[IO]

  case class Resp(data: String)
  case class Req(name: String)

  val uri = uri"http://httpbin.org/delete"

  val resp = Hammock
    .request(Method.DELETE, uri, Map.empty[String,String], Some(Req("name")))
    .as[Resp]
    .exec[IO]
    .unsafeRunSync

  println(resp)
}
