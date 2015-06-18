package alex.scala.rest

import java.io.{IOException, PrintWriter}
import java.util
import javax.servlet.ServletException
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

class TestRestService extends HttpServlet {
  @throws(classOf[ServletException])
  @throws(classOf[IOException])
  protected override def doGet(req: HttpServletRequest, resp: HttpServletResponse) {
    val writer: PrintWriter = resp.getWriter
    writer.println("Get method called")
    writer.println("parameters: " + parameters(req))
    writer.println("headers: " + headers(req))
  }

  @throws(classOf[ServletException])
  @throws(classOf[IOException])
  protected override def doPost(req: HttpServletRequest, resp: HttpServletResponse) {
    val writer: PrintWriter = resp.getWriter
    writer.println("Post method called")
    writer.println("parameters: " + parameters(req))
    writer.println("headers: " + headers(req))
  }

  @throws(classOf[ServletException])
  @throws(classOf[IOException])
  protected override def doDelete(req: HttpServletRequest, resp: HttpServletResponse) {
    val out: PrintWriter = resp.getWriter
    out.println("Delete method called")
  }

  private def parameters(req: HttpServletRequest): String = {
    val sb: StringBuilder = new StringBuilder
    val e: util.Enumeration[_] = req.getParameterNames
    while (e.hasMoreElements) {
      val name: String = e.nextElement.asInstanceOf[String]
      sb.append("|").append(name).append("->").append(req.getParameter(name))
    }
    sb.toString()
  }

  private def headers(req: HttpServletRequest): String = {
    val sb: StringBuilder = new StringBuilder
    val e: util.Enumeration[_] = req.getHeaderNames
    while (e.hasMoreElements) {
      val name: String = e.nextElement.asInstanceOf[String]
      sb.append("|").append(name).append("->").append(req.getHeader(name))
    }
    sb.toString()
  }
}