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

import models.awrsModels._
import uk.gov.hmrc.feedbacksurveyfrontend.FrontendAppConfig._
import uk.gov.hmrc.play.frontend.controller.FrontendController
import play.api.mvc._

import scala.concurrent.Future
import play.api.Play.{configuration, current}
import play.api.i18n.Messages
import play.api.i18n.Messages.Implicits._
import utils.LoggingUtils


object FeedbackSurveyController extends FeedbackSurveyController

trait FeedbackSurveyController extends FrontendController with LoggingUtils {

  val ableToDo  = Action.async { implicit request =>
    Future.successful(Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.feedbackSurvey.ableToDo(formMappings.ableToDoForm)))
  }

  val ableToDoContinue =  Action.async(parse.form(formMappings.ableToDoForm)) { implicit request =>
        val ableToDoWhatNeeded = request.body.ableToDoWhatNeeded
    audit("feedback-survey", Map("ableToDoWhatNeeded" -> ableToDoWhatNeeded.getOrElse("")), eventTypeSuccess)
    Future.successful(Redirect(routes.FeedbackSurveyController.usingService))
  }

  val usingService =  Action.async { implicit request =>
    Future.successful(Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.feedbackSurvey.usingService(formMappings.usingServiceForm)))
  }

  val usingServiceContinue = Action(parse.form(formMappings.usingServiceForm)) { implicit request =>
    val completedAnOnlineForm = request.body.completedAnOnlineForm
    val readGuidanceOnGovUk = request.body.readGuidanceOnGovUk
    val spokeToAFriendOrFamilyMember = request.body.spokeToAFriendOrFamilyMember
    val spokeToEmployerAgentOrAccountant = request.body.spokeToEmployerAgentOrAccountant
    val telephonedHmrc = request.body.telephonedHmrc
    val wroteToHmrc = request.body.wroteToHmrc
    audit("feedback-survey", Map("completedAnOnlineForm" -> completedAnOnlineForm.getOrElse(""),
      "readGuidanceOnGovUk" -> readGuidanceOnGovUk.getOrElse(""),
      "spokeToAFriendOrFamilyMember" -> spokeToAFriendOrFamilyMember.getOrElse(""),
      "spokeToEmployerAgentOrAccountant" -> spokeToEmployerAgentOrAccountant.getOrElse(""),
      "telephonedHmrc" -> telephonedHmrc.getOrElse(""),
      "wroteToHmrc" -> wroteToHmrc.getOrElse("")), eventTypeSuccess)
    Redirect(routes.FeedbackSurveyController.aboutService)
  }

  val aboutService = Action.async { implicit request =>
    Future.successful(Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.feedbackSurvey.aboutService(formMappings.aboutServiceForm)))
  }

  val aboutServiceContinue =  Action(parse.form(formMappings.aboutServiceForm)) { implicit request =>
    val serviceReceived = request.body.serviceReceived
    audit("feedback-survey", Map("serviceReceived" -> serviceReceived.getOrElse("")), eventTypeSuccess)
    Redirect(routes.FeedbackSurveyController.recommendService)
  }

  val recommendService = Action.async { implicit request =>
    Future.successful(Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.feedbackSurvey.recommendService(formMappings.recommendServiceForm)))
  }

  val recommendServiceContinue =  Action(parse.form(formMappings.recommendServiceForm)) { implicit request =>
    val reasonForRating = request.body.reasonForRating
    val recommendRating = request.body.recommendRating
    audit("feedback-survey", Map("reasonForRating" -> reasonForRating.getOrElse(""),
      "recommendRating" -> recommendRating.getOrElse("")), eventTypeSuccess)
    Redirect(routes.FeedbackSurveyController.thankYou)
  }

  val thankYou = Action.async { implicit request =>
    Future.successful(Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.feedbackSurvey.thankYou()))
  }

}
