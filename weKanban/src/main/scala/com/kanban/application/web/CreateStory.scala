package com.kanban.application.web

import scala.xml.Elem

object CreateStory {
  def apply(message: String = ""): Elem = {
    <html>
      <head>
        <title>Create new story</title>
        <link rel="stylesheet" href="/css/main.css" type="text/css" media="screen" charset="utf-8"/>
      </head>
      <body>
        <span class="message">
          {message}
        </span>
        <div class="createStory">
          <form action="/card/save" method="post" accept-charset="utf-8">
            <fieldset>
              <legend>Create new story</legend>
              <div class="section">
                <label for="storyNumber">Story Number
                  <span class="subtle">
                    (uniquely identifies story)
                  </span>
                </label>
                <input type="text" size="10" maxlength="10" minlength="3" name="storyNumber" id="storyNumber"/>
              </div>
              <div class="section">
                <label for="title">Title
                  <span class="subtle">
                    (describe the story)
                  </span>
                </label>
                <textarea rows="5" cols="30" name="title"
                          id="title"></textarea>
              </div>
              <div class="section">
                <button type="submit">Save</button>
              </div>
            </fieldset>
          </form>
          <span class="linkLabel">
            <a href="/kanban/board">Go to Kanban board</a>
          </span>
        </div>
      </body>
    </html>
  }
}
