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

package uk.gov.hmrc.feedbacksurveyfrontend.bindable

import controllers.bindable.Origin
import org.mockito.Matchers.{eq => meq}
import utils.UnitTestTraits

class OriginSpec extends UnitTestTraits {

  "The validation of an origin" should {

    val originServicesList: List[String] = List(
      "check-the-awrs-register",
      "PERTAX",
      "TES",
      "TCS",
      "TCSHOME",
      "TCR",
      "BBSI",
      "NISP",
      "PAYE",
      "REPAYMENTS",
      "P800",
      "FANDF",
      "TYF",
      "CARBEN",
      "MEDBEN",
      "TAMC",
      "CHARITIES",
      "EI",
      "ERS",
      "GMP",
      "PLA",
      "AWRS",
      "CC",
      "CDS",
      "LISA",
      "AGENTSUB",
      "HTS"
    )

    //Write OriginService

    originServicesList.map { originServiceItem =>
      s"pass with valid '$originServiceItem' origin" in {
        Origin(originServiceItem).isValid shouldBe true
        //originService.isValid(Origin(originServiceItem)) shouldbe true
      }
    }

    "fail with an invalid origin" in {
      Origin("INVALID").isValid shouldBe false
    }
  }

  "The customFeedbackUrl of an origin" should {

    "return a custom feedback url if present" in {
      Origin("PERTAX").customFeedbackUrl shouldBe Some("//localhost:9232/personal-account/custom-feedback")
    }

    "not return a custom feedback url if not present" in {
      Origin("check-the-awrs-register").customFeedbackUrl shouldBe None
    }
  }

}
