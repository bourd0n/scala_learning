package com.kanban.application.web.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMethod, RequestMapping}
import org.springframework.web.servlet.ModelAndView

@Controller
class KanbanBoardController {

  @RequestMapping(value = Array("/spring/board", "/board"), method = Array(RequestMethod.GET))
  def helloPage: ModelAndView = {
    println(s"Controller $this called")
    val modelAndView = new ModelAndView("board")
    modelAndView.addObject("helloMessage", "hello from spring")
    modelAndView
  }

}
