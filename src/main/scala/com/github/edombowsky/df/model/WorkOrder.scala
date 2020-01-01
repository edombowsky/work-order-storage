/*
 * Copyright 2019 com.github.edombowsky
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

package com.github.edombowsky.df.model

import java.sql.Timestamp

import com.byteslounge.slickrepo.meta.Entity
import io.circe.Json
import io.circe.Printer

import com.github.edombowsky.df.utils.Time.toISO8601UTC


final case class WorkOrder(override val id: Option[String],
  parentWorkOrder: Option[String],
  description: String,
  status: String,
  priority: Option[Int],
  workType: Option[String],
  asset: Option[String],
  assignedTo: Option[String],
  requiredByDateTime: Option[Timestamp],
  extendedDescription: Option[String],
  lastModifiedTimestamp: Option[Timestamp],
  lastModifiedBy: Option[String],
  createdTimestamp: Option[Timestamp],
  createdBy: Option[String],
  workOrderId: Option[String],
  workOrderTemplate: Option[String],
  userStatus: Option[String],
  assetWork: Option[Boolean],
  raisedDateTime: Option[Timestamp],
  raisedBy: Option[String],
  plannedStartDateTime: Option[Timestamp],
  plannedFinishDateTime: Option[Timestamp],
  actualStartDateTime: Option[Timestamp],
  actualFinishDateTime: Option[Timestamp],
  closedDateTime: Option[Timestamp],
  plannedDuration: Option[Long],
  actualDuration: Option[Long],
  completedBy: Option[String],
  completionComments: Option[String],
  location: Option[String],
  assetPosition: Option[String],
  solutionAttributes: Option[Json],
  customAttributes: Option[Json],
  responsibleOrg: Option[String],
  account: Option[String],
  attachment: Option[Json],
  productServiceRequirement: Option[Json],
  activity: Option[Json]
) extends Entity[WorkOrder, String] {

  def withId(id: String): WorkOrder = this.copy(id = Some(id))

  override def toString: String =
    s"""
      |com.github.edombowsky.model.WorkOrder: id: $id
      |  parentWorkOrder: ${parentWorkOrder.getOrElse("NULL")}
      |  workOrderTemplate: ${workOrderTemplate.getOrElse("NULL")}
      |  description: $description
      |  extendedDescription: ${extendedDescription.getOrElse("NULL")}
      |  status: $status
      |  userStatus: ${userStatus.getOrElse("NULL")}
      |  assetWork: ${assetWork.getOrElse("NULL")}
      |  workType: ${workType.getOrElse("NULL")}
      |  priority: ${priority.getOrElse("NULL")}
      |  raisedDateTime: ${raisedDateTime.getOrElse("NULL")}
      |  raisedBy: ${raisedBy.getOrElse("NULL")}
      |  requiredByDateTime: ${toISO8601UTC(requiredByDateTime)}
      |  plannedStartDateTime: ${toISO8601UTC(plannedStartDateTime)}
      |  plannedFinishDateTime: ${toISO8601UTC(plannedFinishDateTime)}
      |  actualStartDateTime: ${toISO8601UTC(actualStartDateTime)}
      |  actualFinishDateTime: ${toISO8601UTC(actualFinishDateTime)}
      |  closedDateTime: ${toISO8601UTC(closedDateTime)}
      |  plannedDuration: ${plannedDuration.getOrElse("NULL")}
      |  actualDuration: ${actualDuration.getOrElse("NULL")}
      |  completedBy: ${completedBy.getOrElse("NULL")}
      |  completionComments: ${completionComments.getOrElse("NULL")}
      |  solutionAttributes: ${solutionAttributes.map(Printer.noSpaces.print).getOrElse("NULL")}
      |  customAttributes: ${customAttributes.map(Printer.noSpaces.print).getOrElse("NULL")}
      |  assignedTo: ${assignedTo.getOrElse("NULL")}
      |  asset: ${asset.getOrElse("NULL")}
      |  assetPosition: ${assetPosition.getOrElse("NULL")}
      |  location: ${location.getOrElse("NULL")}
      |  responsibleOrg: ${responsibleOrg.getOrElse("NULL")}
      |  account: ${account.getOrElse("NULL")}
      |  attachment: ${attachment.map(Printer.noSpaces.print).getOrElse("NULL")}
      |  productServiceRequirement: ${productServiceRequirement.map(Printer.noSpaces.print).getOrElse("NULL")}
      |  activity: ${activity.map(Printer.noSpaces.print).getOrElse("NULL")}
    """.stripMargin
}
