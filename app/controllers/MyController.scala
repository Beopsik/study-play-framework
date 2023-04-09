package controllers

import configs.AppConfig
import play.api._
import play.api.mvc._

import javax.inject.Inject

class MyController @Inject() (config: Configuration, c: ControllerComponents) extends AbstractController (c){
  def description = Action{
    Ok(config.get[String]("description"))
  }

  def appConfig=Action{
    Ok(config.get[AppConfig]("app.config").toString)
  }
}