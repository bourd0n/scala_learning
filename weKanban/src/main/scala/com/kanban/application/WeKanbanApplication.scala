package com.kanban.application

import javax.servlet.RequestDispatcher
import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}

class WeKanbanApplication extends HttpServlet {
  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    println(req)
    val reqDispatcher: RequestDispatcher = req.getRequestDispatcher("/app" + req.getRequestURI.stripPrefix("/static"))
    reqDispatcher.forward(req, resp)
  }
}
