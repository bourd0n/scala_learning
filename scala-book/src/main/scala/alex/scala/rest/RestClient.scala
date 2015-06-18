package alex.scala.rest

import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.{HttpGet, HttpPost}
import org.apache.http.impl.client.{BasicResponseHandler, HttpClientBuilder}
import org.apache.http.message.{BasicHeader, BasicNameValuePair}

object RestClient extends App {
  def parseArgs(args: Array[String]): Map[String, List[String]] = {
    def nameValuePair(paramName: String): (String, List[String]) = {
      def values(commaSeparatedValues: String): List[String] = {
        commaSeparatedValues.split(",").toList
      }

      val index = args.indexWhere(_ == paramName)
      (paramName, if (index == -1) Nil else values(args(index + 1)))
    }

    Map(nameValuePair("-d"), nameValuePair("-h"))
  }


  require(args.length >= 2, "at minimum you should specify action and url")

  val command = args.head
  val params = parseArgs(args)
  val url = args.last

  def splitByEqual(str: String) = str.split("=")

  def headers = for (nameValuePair <- params("-h")) yield {
    def tokens = splitByEqual(nameValuePair)
    new BasicHeader(tokens(0), tokens(1))
  }

  def formEntity = {
    def toJavaList(scalaList: List[BasicNameValuePair]) = {
      java.util.Arrays.asList(scalaList.toArray: _*)
    }

    def formParams = for (nameValue <- params("-d")) yield {
      def tokens = splitByEqual(nameValue)
      new BasicNameValuePair(tokens(0), tokens(1))
    }

    new UrlEncodedFormEntity(toJavaList(formParams), "UTF-8")
  }

  def handleGetRequest() = {
    val query = params("-d").mkString("&")
    val httpGet = new HttpGet(s"$url?$query")
    headers.foreach(httpGet.addHeader(_))
    val responseBody = HttpClientBuilder.create().build().execute(httpGet, new BasicResponseHandler())
    println(responseBody)
  }

  def handlePostRequest() = {
    val httpPost = new HttpPost(url)
    headers.foreach(httpPost.addHeader(_))
    httpPost.setEntity(formEntity)
    val responseBody = HttpClientBuilder.create().build().execute(httpPost, new BasicResponseHandler())
    println(responseBody)
  }

  command match {
    case "post" => handlePostRequest()
    case "get" => handleGetRequest()
  }

}
