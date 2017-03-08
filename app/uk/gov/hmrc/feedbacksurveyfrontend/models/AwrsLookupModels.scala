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

package models.awrsModels

import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.Messages
import play.api.libs.json.Json
import play.api.Play.current
import play.api.i18n.Messages.Implicits._

case class Page1(ableToDoWhatNeeded: Option[String])

object Page1 {
  implicit val format = Json.format[Page1]
}

case class Page2(telephonedHmrc: Option[String],
                  wroteToHmrc: Option[String],
                  completedAnOnlineForm: Option[String],
                  readGuidanceOnGovUk: Option[String],
                  spokeToEmployerAgentOrAccountant: Option[String],
                  spokeToAFriendOrFamilyMember: Option[String])

object Page2 {
  implicit val format = Json.format[Page2]
}

case class Page3(serviceReceived: Option[String])

object Page3 {
  implicit val format = Json.format[Page3]
}

case class Page4(recommendRating: Option[String],
                  reasonForRating: Option[String])

object Page4 {
  implicit val format = Json.format[Page4]
}


object formMappings {

  val page1Form = Form(mapping(
    "ableToDoWhatNeeded" -> optional(text))(Page1.apply)(Page1.unapply))

  val page2Form = Form(mapping(
    "telephonedHmrc" -> optional(text.verifying("required field", _.nonEmpty)),
    "wroteToHmrc" -> optional(text.verifying("required field", _.nonEmpty)),
    "completedAnOnlineForm" -> optional(text.verifying("required field", _.nonEmpty)),
    "spokeToEmployerAgentOrAccountant" -> optional(text.verifying("required field", _.nonEmpty)),
    "spokeToEmployerAgentOrAccountant" -> optional(text.verifying("required field", _.nonEmpty)),
    "spokeToAFriendOrFamilyMember" -> optional(text.verifying("required field", _.nonEmpty)))(Page2.apply)(Page2.unapply))

  val page3Form = Form(mapping(
    "serviceReceived" -> optional(text.verifying("required field", _.nonEmpty)))(Page3.apply)(Page3.unapply))

  val page4Form = Form(mapping(
    "recommendRating" -> optional(text.verifying("required field", _.nonEmpty)),
    "reasonForRating" -> optional(text.verifying("required field", _.nonEmpty)))(Page4.apply)(Page4.unapply))

  def validInputCharacters(field: String, regXValue: String) = {
    if (field.matches(regXValue)) true else false
  }

}

object formFields {

  val page2Question1Options = Seq(
    "01" -> Messages("awrslookup.page2.question1.option1"),
    "02" -> Messages("awrslookup.page2.question1.option2"),
    "03" -> Messages("awrslookup.page2.question1.option3"),
    "04" -> Messages("awrslookup.page2.question1.option4"),
    "05" -> Messages("awrslookup.page2.question1.option5"),
    "06" -> Messages("awrslookup.page2.question1.option6"),
    "07" -> Messages("awrslookup.page2.question1.option7")
  )

  val page3Question1Options = Seq(
    "01" -> Messages("awrslookup.page3.question1.option1"),
    "02" -> Messages("awrslookup.page3.question1.option2"),
    "03" -> Messages("awrslookup.page3.question1.option3"),
    "04" -> Messages("awrslookup.page3.question1.option4"),
    "05" -> Messages("awrslookup.page3.question1.option5")
  )

  val page4Question1Options = Seq(
    "10" -> Messages("awrslookup.page4.question1.option1"),
    "09" -> Messages("awrslookup.page4.question1.option2"),
    "08" -> Messages("awrslookup.page4.question1.option3"),
    "07" -> Messages("awrslookup.page4.question1.option4"),
    "06" -> Messages("awrslookup.page4.question1.option5"),
    "05" -> Messages("awrslookup.page4.question1.option6"),
    "04" -> Messages("awrslookup.page4.question1.option7"),
    "03" -> Messages("awrslookup.page4.question1.option8"),
    "02" -> Messages("awrslookup.page4.question1.option9"),
    "01" -> Messages("awrslookup.page4.question1.option10"),
    "00" -> Messages("awrslookup.page4.question1.option11")
  )

}

object fieldValidationPatterns {
  def addresssRegx = """^[A-Za-zÀ-ÿ0-9 &'(),-./]{0,}$"""
  def yesNoRegPattern = "^([1-2]{1})$"
}
