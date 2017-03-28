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

import models.feedbackSurveyModels._
import uk.gov.hmrc.play.frontend.controller.FrontendController
import play.api.mvc._

import scala.concurrent.Future
import play.api.Play.{current}
import play.api.i18n.Messages.Implicits._
import utils.{SessionUtil, LoggingUtils}
import utils.FeedbackSurveySessionKeys._



object FeedbackSurveyController extends FeedbackSurveyController

trait FeedbackSurveyController extends FrontendController with LoggingUtils {

  val ableToDo  = Action { implicit request =>
    Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.feedbackSurvey.ableToDo(formMappings.ableToDoForm))
  }

  val ableToDoContinue =  Action (parse.form(formMappings.ableToDoForm)) { implicit request =>
        val ableToDoWhatNeeded = request.body.ableToDoWhatNeeded
    audit("feedback-survey", Map("origin" -> request.session.get(sessionOriginService).get,
      "ableToDoWhatNeeded" -> ableToDoWhatNeeded.getOrElse("")), eventTypeSuccess)
    Redirect(routes.FeedbackSurveyController.usingService)
  }

  val usingService =  Action { implicit request =>
    Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.feedbackSurvey.usingService(formMappings.usingServiceForm))
  }

  val usingServiceContinue = Action (parse.form(formMappings.usingServiceForm)) { implicit request =>
    val beforeUsingThisService = request.body.beforeUsingThisService
    audit("feedback-survey", Map(
      "origin" -> request.session.get(sessionOriginService).get,
      "exitSurveyWhatDidYouTryBeforeUsingThisService" -> beforeUsingThisService.mkString(" ")), eventTypeSuccess)
    Redirect(routes.FeedbackSurveyController.aboutService)
  }

  val aboutService = Action { implicit request =>
    Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.feedbackSurvey.aboutService(formMappings.aboutServiceForm))
  }

  val aboutServiceContinue =  Action (parse.form(formMappings.aboutServiceForm)) { implicit request =>
    val serviceReceived = request.body.serviceReceived
    audit("feedback-survey", Map("origin" -> request.session.get(sessionOriginService).get,
      "serviceReceived" -> serviceReceived.getOrElse("")), eventTypeSuccess)
    Redirect(routes.FeedbackSurveyController.recommendService)
  }

  val recommendService = Action { implicit request =>
    Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.feedbackSurvey.recommendService(formMappings.recommendServiceForm))
  }

  val recommendServiceContinue =  Action (parse.form(formMappings.recommendServiceForm)) { implicit request =>
    val reasonForRating = request.body.reasonForRating
    val recommendRating = request.body.recommendRating
    audit("feedback-survey", Map(
      "origin" -> request.session.get(sessionOriginService).get,
      "reasonForRating" -> reasonForRating.getOrElse(""),
      "recommendRating" -> recommendRating.getOrElse("")), eventTypeSuccess)
    Redirect(routes.FeedbackSurveyController.thankYou)
  }

  val thankYou = Action { implicit request =>
    Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.feedbackSurvey.thankYou())
  }

}
