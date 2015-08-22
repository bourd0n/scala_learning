package com.kanban.application

import javax.servlet.RequestDispatcher
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

import com.kanban.application.model.Story
import com.kanban.application.web.CreateStory

class WeKanbanApplication extends HttpServlet {

  import Method._

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    println(req)
    req match {
      case MethodParts(GET, "kanban" :: "card" :: "create" :: Nil) =>
        val param = Option(req.getParameter("message"))
          .orElse(Option(req.getAttribute("message")))
          .orElse(Option(req.getSession.getAttribute("message")))
          .getOrElse("")
        req.getSession.removeAttribute("message")
        resp.getWriter.println(CreateStory(param.toString))
      case MethodParts(GET, "kanban" :: listPath) =>
        forward(req, resp, listPath) {
          "/html/" + _.mkString("/") + ".html"
        }
      case MethodParts(GET, "kanban" :: path :: Nil) =>
        forward(req, resp, path) {
          "/html/" + _ + ".html"
        }
    }
  }


  override def doPost(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    println(req)
    req match {
      case MethodParts(POST, "kanban" :: "card" :: "save" :: Nil) => saveStory(req, resp)
    }
  }

  private def saveStory(req: HttpServletRequest, resp: HttpServletResponse) = {
    val title = req.getParameter("title")
    val number = req.getParameter("storyNumber")
    val message = Story(number, title).save() match {
      case Right(_) => "success"
      case Left(ex) => ex.toString
    }
    req.getSession.setAttribute("message", message)
    resp.sendRedirect("/kanban/card/create")
  }

  def forward[A](req: HttpServletRequest, resp: HttpServletResponse, uri: A)(urlConverter: A => String): Unit = {
    val reqDispatcher: RequestDispatcher = req.getRequestDispatcher(urlConverter(uri))
    reqDispatcher.forward(req, resp)
  }

}

object UriPath {
  def unapply(r: String): Option[List[String]] = {
    Some(r.stripPrefix("/").split('/').toList)
  }
}

object Method extends Enumeration {
  type Method = Value
  val GET, POST = Value
}

object MethodParts {

  import Method._

  def unapply(req: HttpServletRequest): Option[(Method, List[String])] = {
    val method = req.getMethod
    val paths = req.getRequestURI.stripPrefix("/").split('/').toList
    println(method.toLowerCase + " " + paths)
    method.toLowerCase match {
      case "get" => Some((GET, paths))
      case "post" => Some((POST, paths))
      case _ => None
    }
  }
}