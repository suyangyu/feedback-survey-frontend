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

package views.awrsLookup

import org.jsoup.nodes.Document
import org.mockito.Matchers
import org.mockito.Mockito._
import play.api.i18n.Messages
import play.api.libs.json.Json
import play.api.mvc.AnyContentAsEmpty
import play.api.test.FakeRequest
import play.api.test.Helpers._

import scala.concurrent.Future
import utils.{AwrsUnitTestTraits, HtmlUtils}
import controllers.AwrsLookupController

class pageTests extends AwrsUnitTestTraits with HtmlUtils {
  val lookupFailure = Json.parse( """{"reason": "Generic test reason"}""")

  def testRequest(originService: Option[String]): FakeRequest[AnyContentAsEmpty.type] =
    FakeRequest(GET, "/feedback-survey-frontend" + originService.fold("")(q => s"?originService=$q"))

  object TestLookupController extends AwrsLookupController

  "AWRS Lookup Controller" should {

    "render page1 correctly" in {
      val document: Document = TestLookupController.page1.apply(testRequest(originService = "awrs-lookup"))
      document.getElementById("title").text shouldBe Messages("awrslookup.page1.title")
      document.getElementById("intro").text shouldBe Messages("awrslookup.page1.para1")
      document.getElementById("ableToDoWhatNeeded").text should include(Messages("awrslookup.page1.question1"))
      document.getElementById("ableToDoWhatNeeded-yes").text shouldBe ""
      document.getElementById("ableToDoWhatNeeded-no").text shouldBe ""
      document.getElementById("save-and-continue").text shouldBe Messages("generic.continue")
    }

    "render page2 correctly" in {
      val document: Document = TestLookupController.page2.apply(testRequest(originService = "awrs-lookup"))
      document.getElementById("title").text shouldBe Messages("awrslookup.page2.question1")
      document.getElementById("save-and-continue").text shouldBe Messages("generic.continue")
    }

    "render page3 correctly" in {
      val document: Document = TestLookupController.page3.apply(testRequest(originService = "awrs-lookup"))
      document.getElementById("title").text shouldBe Messages("awrslookup.page3.question1")
      document.getElementById("save-and-continue").text shouldBe Messages("generic.continue")
    }

    "render page4 correctly" in {
      val document: Document = TestLookupController.page4.apply(testRequest(originService = "awrs-lookup"))
      document.getElementById("title").text shouldBe Messages("awrslookup.page4.question1")
      document.getElementById("reasonForRating_field").text should include(Messages("awrslookup.page4.question2"))
      document.getElementById("save-and-continue").text shouldBe Messages("generic.continue")
    }

    "render page5 correctly" in {
      val document: Document = TestLookupController.page5.apply(testRequest(originService = "awrs-lookup"))
      document.getElementById("title").text shouldBe Messages("awrslookup.page5.title")

    }

  }
}
