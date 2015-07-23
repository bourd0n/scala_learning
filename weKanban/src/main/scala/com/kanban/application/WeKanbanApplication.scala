package com.kanban.application

import javax.servlet.RequestDispatcher
import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}

class WeKanbanApplication extends HttpServlet {
  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    println(req)
    println(req.getRequestURI)
    val reqDispatcher: RequestDispatcher = req.getRequestDispatcher("/app/app.html")
    reqDispatcher.forward(req, resp)
  }
}
