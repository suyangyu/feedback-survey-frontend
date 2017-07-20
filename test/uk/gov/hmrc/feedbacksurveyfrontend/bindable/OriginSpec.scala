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

import com.typesafe.config.ConfigFactory
import controllers.bindable.Origin
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.{Application, Configuration}
import utils.UnitTestTraits


class OriginSpec extends UnitTestTraits {

  "The validation of an origin" should {

    "pass with valid AWRS_LOOKUP origin" in {
      Origin("check-the-awrs-register").isValid shouldBe true
    }
    "pass with valid PTA origin" in {
      Origin("PERTAX").isValid shouldBe true
    }
    "pass with valid TCS origin" in {
      Origin("TCS").isValid shouldBe true
    }
    "pass with valid NISP origin" in {
      Origin("NISP").isValid shouldBe true
    }
    "pass with valid PAYE origin" in {
      Origin("PAYE").isValid shouldBe true
    }
    "pass with valid 'REPAYMENTS' origin" in {
      Origin("REPAYMENTS").isValid shouldBe true
    }
    "pass with valid P800 origin" in {
      Origin("P800").isValid shouldBe true
    }
    "pass with valid AWRS origin" in {
      Origin("AWRS").isValid shouldBe true
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
