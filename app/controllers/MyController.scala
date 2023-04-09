package controllers

import akka.util.ByteString
import configs.AppConfig
import play.api._
import play.api.http.HttpEntity
import play.api.mvc._

import javax.inject.Inject

class MyController @Inject()(config: Configuration, c: ControllerComponents) extends AbstractController(c) {
  def description = Action {
    Ok(config.get[String]("description"))
  }

  def appConfig = Action {
    Result(
      header = ResponseHeader(200, Map.empty),
      body = HttpEntity.Strict(ByteString(config.get[AppConfig]("app.config").toString), Some("application/json"))
    )
  }

  def request = Action {
    request =>
      Ok("Got request [" + request + "]")
  }

  def implicitRequest = Action {
    implicit request =>
      anotherMethod("test implicit keyword")
      Ok("Got request [" + request + "]")
  }
  def anotherMethod(p: String)(implicit request: Request[_]) = {
    println("====================================================")
    println("====================================================")
    println("Implicit request: "+request)
    println("====================================================")
    println("====================================================")
  }

  def simpleResult=Action{
    Result(
      header = ResponseHeader(200, Map.empty),
      body = HttpEntity.Strict(ByteString("simple results"), Some("text/plain"))
    )
  }

  def redirect = Action{
    Redirect("/tutorial")
  }
}