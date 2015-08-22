package com.kanban.application.web.controller

import com.kanban.application.model.Story
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod}
import org.springframework.web.servlet.ModelAndView

import scala.collection.JavaConversions

@Controller
class KanbanBoardController {

  @RequestMapping(value = Array("/board", "/index.html"), method = Array(RequestMethod.GET))
  def helloPage: ModelAndView = {
    println(s"Controller $this called")
    val modelAndView = new ModelAndView("board")
    val readyStories = JavaConversions.asJavaCollection(Story.findAllByPhase("ready"))
    println(s"Ready stories = $readyStories")
    val devStories = JavaConversions.asJavaCollection(Story.findAllByPhase("dev"))
    modelAndView.addObject("readyStories", readyStories)
    modelAndView.addObject("devStories", devStories)
    modelAndView
  }

}
