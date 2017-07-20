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

package controllers.bindable

import play.api.Play.current
import uk.gov.hmrc.play.config.ServicesConfig
import scala.collection.JavaConversions._

case class OriginService(token: Option[String], customFeedbackUrl: Option[String])

case class Origin(value: String) extends ServicesConfig {

  val originServices: List[OriginService] = current.configuration.getConfigList("origin-services").map(_.toList).getOrElse(Nil).map { configItem =>
    OriginService(configItem.getString("token"), configItem.getString("customFeedbackUrl"))
  }

  override def toString : String = value

  def isValid: Boolean = !originServices.filter(o => o.token.equals(Some(value))).isEmpty

  def customFeedbackUrl: Option[String] = originServices.filter(o => o.token.equals(Some(value))).head.customFeedbackUrl
}
