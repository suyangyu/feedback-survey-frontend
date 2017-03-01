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

import controllers._
import models.awrsModels._
import uk.gov.hmrc.play.frontend.auth.AuthContext
import uk.gov.hmrc.play.frontend.controller.FrontendController
import play.api.mvc._
import uk.gov.hmrc.play.http.HeaderCarrier
import scala.concurrent.Future
import play.api.Play.current
import play.api.i18n.Messages.Implicits._
import utils._
import scala.util.{Failure, Success, Try}

object HomeController extends HomeController

trait HomeController extends FrontendController  {

  implicit val sessionUtil = SessionUtil.sessionUtilForRequest

  val start = Action.async { implicit request =>
    println("***************************** 2 ***************************")
    println("serviceTransactionName: "+request.session.get("serviceTransactionName"))
    request.session.get("serviceTransactionName").fold("")(x => x) match {
      case "awrs-lookup-frontend" => Future.successful(Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.awrsLookup.page1(formMappings.page1Form)))
      case _ => Future.successful(Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.awrsLookup.page5()))
    }
  }

}
