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
import play.api.i18n.Messages
import uk.gov.hmrc.play.frontend.controller.FrontendController
import uk.gov.hmrc.feedbacksurveyfrontend.FrontendAppConfig._
import play.api.mvc._
import uk.gov.hmrc.play.http.InternalServerException
import scala.concurrent.Future
import play.api.Play.current
import play.api.i18n.Messages.Implicits._

object HomeController extends HomeController

trait HomeController extends FrontendController  {

  def start(originService :String) = Action.async { implicit request =>
    originService match {
      case originService => {
        val callbackUrl = loadConfig(s"microservice.services.awrs-lookup.callback-url")
        val serviceTitle = loadConfig(s"microservice.services.awrs-lookup.service-name")
        Future.successful(Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.awrsLookup.page1(formMappings.page1Form, callbackUrl, serviceTitle,originService)).withSession(request.session + ("originService" -> originService)))
      }
      case _ => {
        Future.successful(Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.error_template(Messages("global_errors.title"), Messages("global_errors.heading"), Messages("global_errors.message"))))
      }
    }
  }


}
