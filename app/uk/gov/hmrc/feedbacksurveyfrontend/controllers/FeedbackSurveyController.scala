/*
 * Copyright 2017 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers

import java.io.{PrintWriter, StringWriter}
import controllers.auth.{ExternalUrls, AwrsRegistrationGovernmentGateway, AwrsRegistrationRegime}
import controllers.routes
import models.awrsModels._
import uk.gov.hmrc.play.config.RunMode
import uk.gov.hmrc.play.frontend.auth.{Actions, AuthContext}
import uk.gov.hmrc.play.frontend.controller.{UnauthorisedAction, FrontendController}
import play.api.mvc._
import uk.gov.hmrc.play.http.HeaderCarrier
import scala.concurrent.Future
import play.api.Play.current
import play.api.i18n.Messages.Implicits._
import utils._

import scala.util.{Failure, Success, Try}

object FeedbackSurveyController extends Results with LoggingUtils {

  def showErrorPageRaw(implicit request: Request[AnyContent]): Result =
    InternalServerError(uk.gov.hmrc.feedbacksurveyfrontend.views.html.application_error()(request))

  def showErrorPage(implicit request: Request[AnyContent]): Future[Result] =
    Future.successful(showErrorPageRaw)

  def timedOut() = UnauthorisedAction {
    implicit request =>
      Redirect(ExternalUrls.signOut)
  }

  def keepAlive = UnauthorisedAction {
    implicit request =>
      Ok("OK")
  }
   def unauthorised = Action { implicit request =>
    Unauthorized(uk.gov.hmrc.feedbacksurveyfrontend.views.html.unauthorised())
  }


}


trait FeedbackSurveyController extends FrontendController with Actions with LoggingUtils with RunMode {

  type AsyncUserRequest = (AuthContext) => (Request[AnyContent]) => Future[Result]

  implicit class StackTraceUtil(e: Throwable) {
    def getStacktraceString: String = {
      val stringWriter: StringWriter = new StringWriter()
      val printWriter: PrintWriter = new PrintWriter(stringWriter)
      e.printStackTrace(printWriter)
      stringWriter.toString
    }
  }

  @inline def async(body: AsyncUserRequest): Action[AnyContent] =
    AuthorisedFor(AwrsRegistrationRegime, pageVisibility = GGConfidence).async {
      implicit user => implicit request =>
        Try(executeBody(body)) match {
          case Success(future) => future
          case Failure(ex) =>
            val logInfo = s"Error occured for:\nServiceTransactionName: ${getServiceTransactionName.fold("unknown")(x => x)}\n" + "Stacktrace: " + ex.getStacktraceString + "\n"
            warn(logInfo)
            showErrorPage
        }
    }

  private def executeBody(body: AsyncUserRequest)(implicit user: AuthContext, request: Request[AnyContent]): Future[Result] =
    body(user)(request).recover {
      case error =>
        warn(error.getStacktraceString)
        showErrorPageRaw

    }


  def showErrorPageRaw(implicit request: Request[AnyContent]): Result =
    FeedbackSurveyController.showErrorPageRaw

  def showErrorPage(implicit request: Request[AnyContent]): Future[Result] =
    FeedbackSurveyController.showErrorPage

  implicit val sessionUtil = SessionUtil.sessionUtilForRequest
  implicit val sessionUtilForResult = SessionUtil.sessionUtilForResult

  def getServiceTransactionName(implicit request: Request[AnyContent]): Option[String] =
    request getServiceTransactionName


}

