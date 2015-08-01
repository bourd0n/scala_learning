package com.kanban.application

import javax.servlet.RequestDispatcher
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

import com.kanban.application.web.CreateStory

class WeKanbanApplication extends HttpServlet {
  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    println(req)
    val uri = req.getRequestURI
    uri match {
      case UriPath("kanban" :: "card" :: "create" :: Nil) => resp.getWriter.println(CreateStory.apply("message"))
      case UriPath("kanban" :: listPath) =>
        forward(req, resp, listPath) {
          "/html/" + _.mkString("/") + ".html"
        }
      case UriPath("kanban" :: path :: Nil) =>
        forward(req, resp, path) {
          "/html/" + _ + ".html"
        }
    }

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
