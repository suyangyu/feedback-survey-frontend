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

import org.jsoup.Jsoup
import org.mockito.Matchers._
import org.mockito.Mockito._
import org.scalatest.Suite
import org.scalatest.mock.MockitoSugar
import play.api.http.Status
import play.api.test.{FakeApplication, FakeRequest}
import play.api.test.Helpers._
import uk.gov.hmrc.play.http.HeaderCarrier
import uk.gov.hmrc.play.test.{WithFakeApplication, UnitSpec}

import scala.concurrent.Future

trait FakeApplication extends WithFakeApplication {
  this: Suite =>
  implicit val hc = HeaderCarrier()
  override lazy val fakeApplication = FakeApplication()
}

class HomeControllerTest extends UnitSpec with FakeApplication with MockitoSugar {

  "Page1 GET" should {

    def buildFakeHomeController = new HomeController{}

    "give a status of OK, return error page if origin service not found" in {
      val controllerUnderTest = buildFakeHomeController
      val result = controllerUnderTest.start("").apply(FakeRequest("GET", ""))
      status(result) shouldBe SEE_OTHER
      //contentAsString(result) should include("Service unavailable")
    }

  }

}
