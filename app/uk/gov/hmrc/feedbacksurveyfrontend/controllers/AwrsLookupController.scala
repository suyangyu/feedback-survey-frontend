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
import uk.gov.hmrc.play.frontend.auth.AuthContext
import uk.gov.hmrc.play.frontend.controller.FrontendController
import play.api.mvc._
import uk.gov.hmrc.play.http.HeaderCarrier
import scala.concurrent.Future
import play.api.Play.current
import play.api.i18n.Messages.Implicits._

import scala.util.{Failure, Success, Try}


object AwrsLookupController extends AwrsLookupController

trait AwrsLookupController extends FrontendController {

  val page1 = Action.async { implicit request =>
    Future.successful(Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.awrsLookup.page1(formMappings.page1Form)))
  }

  val page1Continue = Action(parse.form(formMappings.page1Form)) { implicit request =>
    Redirect(routes.AwrsLookupController.page2())
  }

//  val page1Continue: Future[Result] = {
//    formMappings.page1Form.bindFromRequest.fold(
//      errors => {
//        Future.successful(Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.awrsLookup.page1(errors)))
//      },
//      formData => {
//        Redirect(controllers.routes.AwrsLookupController.page2)
//      }
//    )
//  }

  val page2 = Action.async { implicit request =>
    Future.successful(Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.awrsLookup.page2(formMappings.page2Form)))
  }

  val page2Continue = Action(parse.form(formMappings.page2Form)) { implicit request =>
    Redirect(routes.AwrsLookupController.page3())
  }

  val page3 = Action.async { implicit request =>
    Future.successful(Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.awrsLookup.page3(formMappings.page3Form)))
  }

  val page3Continue = Action(parse.form(formMappings.page3Form)) { implicit request =>
    Redirect(routes.AwrsLookupController.page4())
  }

  val page4 = Action.async { implicit request =>
    Future.successful(Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.awrsLookup.page4(formMappings.page4Form)))
  }

  val page4Continue = Action(parse.form(formMappings.page4Form)) { implicit request =>
    Redirect(routes.AwrsLookupController.page5())
  }

  val page5 = Action.async { implicit request =>
    Future.successful(Ok(uk.gov.hmrc.feedbacksurveyfrontend.views.html.awrsLookup.page5()))
  }

}
