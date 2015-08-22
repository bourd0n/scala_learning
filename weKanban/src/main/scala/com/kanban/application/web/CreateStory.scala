package com.kanban.application.web

import scala.xml.Elem

/**
 * Just to test scala xml support
 */
object CreateStory {
  def apply(message: String = ""): Elem = {
    <html>
      <head>
        <title>Create new story</title>
        <script src="/js/jquery-2.1.4.min.js" type="text/javascript"></script>
        <link rel="stylesheet" href="/css/main.css" type="text/css" media="screen" charset="utf-8"/>
        <link rel="stylesheet" href="/css/materialize.min.css" type="text/css" media="screen" charset="utf-8"/>
        <script src="/js/materialize.min.js" type="text/javascript"></script>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
      </head>
      <body>
        <div class="container">
          <span class="message red-text">
            {message}
          </span>
          <div class="row">
            <form action="/kanban/card/save" method="post" accept-charset="utf-8" class="col s12">
              <h3 class="header">Create new story</h3>
              <div class="row">
                <div class="input-field col s6">
                  <input placeholder="(uniquely identifies story)" type="text" size="10" maxlength="10" minlength="3" name="storyNumber" id="storyNumber" class="validate"/>
                  <label for="storyNumber">Story Number</label>
                </div>
              </div>
              <div class="row">
                <div class="input-field col s6">
                  <label for="title">Title</label>
                  <textarea placeholder="(describe the story)" rows="5" cols="30" name="title"
                            id="title" class="materialize-textarea"></textarea>
                </div>
              </div>
              <div class="row">
                <button type="submit" class="btn waves-effect waves-light" name="action">Submit
                  <i class="material-icons right">send</i>
                </button>
              </div>
            </form>
          </div>
          <footer class="page-footer">
            <div class="container">
              <div class="row">
                <div class="col l6 s12">
                  <h5 class="white-text">Links</h5>
                  <a href="/spring/board" class="grey-text text-lighten-3">Go to Kanban board</a>
                </div>
              </div>
            </div>
          </footer>
        </div>
      </body>
    </html>
  }
}
