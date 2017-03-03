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
import uk.gov.hmrc.play.frontend.controller.FrontendController
import play.api.mvc._
import scala.concurrent.Future
import play.api.Play.current
import play.api.i18n.Messages.Implicits._

import scala.util.{Failure, Success, Try}
import utils.LoggingUtils


object AwrsLookupController extends AwrsLookupController

trait AwrsLookupController extends FrontendController with LoggingUtils {

  val page1 = Action.async { implicit request =>
    Future.successful(Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.awrsLookup.page1(formMappings.page1Form, "", "")))
  }

  val page1Continue = Action(parse.form(formMappings.page1Form)) { implicit request =>
    val ableToDoWhatNeeded = request.body.ableToDoWhatNeeded
    audit("awrs-lookup", Map("ableToDoWhatNeeded" -> ableToDoWhatNeeded.getOrElse("")), eventTypeSuccess)
    Redirect(routes.AwrsLookupController.page2())
  }

  val page2 = Action.async { implicit request =>
    Future.successful(Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.awrsLookup.page2(formMappings.page2Form)))
  }

  val page2Continue = Action(parse.form(formMappings.page2Form)) { implicit request =>
    val completedAnOnlineForm = request.body.completedAnOnlineForm
    val readGuidanceOnGovUk = request.body.readGuidanceOnGovUk
    val spokeToAFriendOrFamilyMember = request.body.spokeToAFriendOrFamilyMember
    val spokeToEmployerAgentOrAccountant = request.body.spokeToEmployerAgentOrAccountant
    val telephonedHmrc = request.body.telephonedHmrc
    val wroteToHmrc = request.body.wroteToHmrc
    audit("awrs-lookup", Map("completedAnOnlineForm" -> completedAnOnlineForm.getOrElse(""),
      "readGuidanceOnGovUk" -> readGuidanceOnGovUk.getOrElse(""),
      "spokeToAFriendOrFamilyMember" -> spokeToAFriendOrFamilyMember.getOrElse(""),
      "spokeToEmployerAgentOrAccountant" -> spokeToEmployerAgentOrAccountant.getOrElse(""),
      "telephonedHmrc" -> telephonedHmrc.getOrElse(""),
      "wroteToHmrc" -> wroteToHmrc.getOrElse("")), eventTypeSuccess)
    Redirect(routes.AwrsLookupController.page3())
  }

  val page3 = Action.async { implicit request =>
    Future.successful(Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.awrsLookup.page3(formMappings.page3Form)))
  }

  val page3Continue = Action(parse.form(formMappings.page3Form)) { implicit request =>
    val serviceReceived = request.body.serviceReceived
    audit("awrs-lookup", Map("serviceReceived" -> serviceReceived.getOrElse("")), eventTypeSuccess)
    Redirect(routes.AwrsLookupController.page4())
  }

  val page4 = Action.async { implicit request =>
    Future.successful(Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.awrsLookup.page4(formMappings.page4Form)))
  }

  val page4Continue = Action(parse.form(formMappings.page4Form)) { implicit request =>
    val reasonForRating = request.body.reasonForRating
    val recommendRating = request.body.recommendRating
    audit("awrs-lookup", Map("reasonForRating" -> reasonForRating.getOrElse(""),
      "recommendRating" -> recommendRating.getOrElse("")), eventTypeSuccess)
    Redirect(routes.AwrsLookupController.page5())
  }

  val page5 = Action.async { implicit request =>
    Future.successful(Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.awrsLookup.page5()))
  }

}
