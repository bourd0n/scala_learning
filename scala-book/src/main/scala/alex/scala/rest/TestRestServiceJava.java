package alex.scala.rest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class TestRestServiceJava extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("Get method called");
        writer.println("parameters: " + parameters(req));
        writer.println("headers: " + headers(req));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("Post method called");
        writer.println("parameters: " + parameters(req));
        writer.println("headers: " + headers(req));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("Delete method called");
    }

    private String parameters(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        for (Enumeration e = req.getParameterNames(); e.hasMoreElements(); ) {
            String name = (String) e.nextElement();
            sb.append("|").append(name).append("->").append(req.getParameter(name));
        }
        return sb.toString();
    }

    private String headers(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        for (Enumeration e = req.getHeaderNames(); e.hasMoreElements(); ) {
            String name = (String) e.nextElement();
            sb.append("|").append(name).append("->").append(req.getHeader(name));
        }
        return sb.toString();
    }

}
