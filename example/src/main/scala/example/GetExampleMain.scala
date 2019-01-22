package example

import cats.effect._
import hammock._
import hammock.apache._
import hammock.circe.implicits._
import hammock.marshalling._
import io.circe.generic.auto._

object GetExampleMain extends App {
  implicit val interpTrans: ApacheInterpreter[IO] = ApacheInterpreter[IO]

  case class RespArgs(name: String, number: Int)
  case class Resp(args: RespArgs, origin: String, url: String)

  val uri = uri"http://httpbin.org/get?name=name&number=4"

  val resp = Hammock
    .request(Method.GET, uri, Map.empty[String,String])
    .as[Resp]
    .exec[IO]
    .unsafeRunSync

  println(resp)
}
