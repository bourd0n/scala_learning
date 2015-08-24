package application.web.controller

import application.model.Story
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestParam, RequestMethod, ResponseBody, RequestMapping}
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

  @RequestMapping(value = Array("/card/move"), method = Array(RequestMethod.POST))
  @ResponseBody
  def moveCard(@RequestParam("storyNumber") storyNumber: String, @RequestParam("phase") phase: String): String = {
    println(s"move card called")
    val story = Story.findByNumber(storyNumber)
    story.moveTo(phase) match {
      case Right(message) => message
      case Left(ex) => ex.getMessage
    }
  }

}
