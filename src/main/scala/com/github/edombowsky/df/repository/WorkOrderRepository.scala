package com.github.edombowsky.df.repository

import java.sql.Timestamp

import com.byteslounge.slickrepo.meta.Keyed
import com.byteslounge.slickrepo.repository.Repository
import com.byteslounge.slickrepo.scalaversion.JdbcProfile
import io.circe.Json
import slick.ast.BaseTypedType
import slick.collection.heterogeneous.HNil

import com.github.edombowsky.df.model.WorkOrder
import com.github.edombowsky.df.utils.ExtendedPostgresProfile.api._


class WorkOrderRepository(override val driver: JdbcProfile)
  extends Repository[WorkOrder, String](driver)  {

  import driver.api._
  val pkType = implicitly[BaseTypedType[String]]
  val tableQuery = TableQuery[WorkOrders]
  type TableType = WorkOrders

  //To deal with the schama see::  https://stackoverflow.com/questions/13381153/slick-issue-when-going-with-postgresql
  class WorkOrders(tag: Tag) extends Table[WorkOrder](tag, Some("datafabric_workorder"), "work_orders") with Keyed[String] {
    def id = column[String]("work_order_uuid", O.PrimaryKey)
    def parentWorkOrder = column[Option[String]]("parent_work_order")
    def description = column[String]("description")
    def status = column[String]("status")
    def priority = column[Option[Int]]("priority")
    def workType = column[Option[String]]("work_type")
    def asset = column[Option[String]]("asset")
    def assignedTo = column[Option[String]]("assigned_to")
    def requiredByDateTime = column[Option[Timestamp]]("required_by_date_time")
    def extendedDescription = column[Option[String]]("extended_description")
    def lastModifiedTimestamp = column[Option[Timestamp]]("last_modified_timestamp")
    def lastModifiedBy = column[Option[String]]("last_modified_by")
    def createdTimestamp = column[Option[Timestamp]]("created_timestamp")
    def createdBy = column[Option[String]]("created_by")
    def workOrderId = column[Option[String]]("work_order_id")
    def workOrderTemplate = column[Option[String]]("work_order_template")
    def userStatus = column[Option[String]]("user_status")
    def assetWork = column[Option[Boolean]]("asset_work")
    def raisedDateTime = column[Option[Timestamp]]("raised_date_time")
    def raisedBy = column[Option[String]]("raised_by")
    def plannedStartDateTime = column[Option[Timestamp]]("planned_start_date_time")
    def plannedFinishDateTime = column[Option[Timestamp]]("planned_finish_date_time")
    def actualStartDateTime = column[Option[Timestamp]]("actual_start_date_time")
    def actualFinishDateTime = column[Option[Timestamp]]("actual_finish_date_time")
    def closedDateTime = column[Option[Timestamp]]("closed_date_time")
    def plannedDuration = column[Option[Long]]("planned_duration")
    def actualDuration = column[Option[Long]]("actual_duration")
    def completedBy = column[Option[String]]("completed_by")
    def completionComments = column[Option[String]]("completion_comments")
    def location = column[Option[String]]("location")
    def assetPosition = column[Option[String]]("asset_position")
    def solutionAttributes = column[Option[Json]]("solution_attributes")
    def customAttributes = column[Option[Json]]("custom_attributes")
    def responsibleOrg = column[Option[String]]("responsible_org")
    def account = column[Option[String]]("account")
    def attachment = column[Option[Json]]("attachment")
    def productServiceRequirement = column[Option[Json]]("product_service_requirement")
    def activity = column[Option[Json]]("activity")
    def nodeClass = column[Int]("node_class")

    def m3 = (
      id.? ::
      parentWorkOrder :: description :: status :: priority :: workType ::
      asset :: assignedTo :: requiredByDateTime :: extendedDescription ::
      lastModifiedTimestamp :: lastModifiedBy :: createdTimestamp ::
      createdBy :: workOrderId :: workOrderTemplate :: userStatus ::
      assetWork :: raisedDateTime :: raisedBy :: plannedStartDateTime ::
      plannedFinishDateTime :: actualStartDateTime :: actualFinishDateTime ::
      closedDateTime :: plannedDuration :: actualDuration :: completedBy ::
      completionComments :: location :: assetPosition :: solutionAttributes ::
      customAttributes :: responsibleOrg :: account :: attachment ::
      productServiceRequirement :: activity :: nodeClass :: HNil
      ).mapTo[WorkOrder]

    def * = m3
  }
}
