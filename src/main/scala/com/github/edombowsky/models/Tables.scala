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

package com.github.edombowsky.models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.PostgresProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  import slick.collection.heterogeneous._
  import slick.collection.heterogeneous.syntax._
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(SchemaHistory.schema, WorkOrderExternalIds.schema, WorkOrderLocationGis.schema, WorkOrders.schema, WorkOrderTemplateDependencies.schema, WorkOrderTemplates.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table SchemaHistory
   *  @param installedRank Database column installed_rank SqlType(int4), PrimaryKey
   *  @param version Database column version SqlType(varchar), Length(50,true), Default(None)
   *  @param description Database column description SqlType(varchar), Length(200,true)
   *  @param `type` Database column type SqlType(varchar), Length(20,true)
   *  @param script Database column script SqlType(varchar), Length(1000,true)
   *  @param checksum Database column checksum SqlType(int4), Default(None)
   *  @param installedBy Database column installed_by SqlType(varchar), Length(100,true)
   *  @param installedOn Database column installed_on SqlType(timestamp)
   *  @param executionTime Database column execution_time SqlType(int4)
   *  @param success Database column success SqlType(bool) */
  case class SchemaHistoryRow(installedRank: Int, version: Option[String] = None, description: String, `type`: String, script: String, checksum: Option[Int] = None, installedBy: String, installedOn: java.sql.Timestamp, executionTime: Int, success: Boolean)
  /** GetResult implicit for fetching SchemaHistoryRow objects using plain SQL queries */
  implicit def GetResultSchemaHistoryRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[String], e3: GR[Option[Int]], e4: GR[java.sql.Timestamp], e5: GR[Boolean]): GR[SchemaHistoryRow] = GR{
    prs => import prs._
    SchemaHistoryRow.tupled((<<[Int], <<?[String], <<[String], <<[String], <<[String], <<?[Int], <<[String], <<[java.sql.Timestamp], <<[Int], <<[Boolean]))
  }
  /** Table description of table schema_history. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class SchemaHistory(_tableTag: Tag) extends profile.api.Table[SchemaHistoryRow](_tableTag, Some("datafabric_workorder"), "schema_history") {
    def * = (installedRank, version, description, `type`, script, checksum, installedBy, installedOn, executionTime, success) <> (SchemaHistoryRow.tupled, SchemaHistoryRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(installedRank), version, Rep.Some(description), Rep.Some(`type`), Rep.Some(script), checksum, Rep.Some(installedBy), Rep.Some(installedOn), Rep.Some(executionTime), Rep.Some(success))).shaped.<>({r=>import r._; _1.map(_=> SchemaHistoryRow.tupled((_1.get, _2, _3.get, _4.get, _5.get, _6, _7.get, _8.get, _9.get, _10.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column installed_rank SqlType(int4), PrimaryKey */
    val installedRank: Rep[Int] = column[Int]("installed_rank", O.PrimaryKey)
    /** Database column version SqlType(varchar), Length(50,true), Default(None) */
    val version: Rep[Option[String]] = column[Option[String]]("version", O.Length(50,varying=true), O.Default(None))
    /** Database column description SqlType(varchar), Length(200,true) */
    val description: Rep[String] = column[String]("description", O.Length(200,varying=true))
    /** Database column type SqlType(varchar), Length(20,true)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Rep[String] = column[String]("type", O.Length(20,varying=true))
    /** Database column script SqlType(varchar), Length(1000,true) */
    val script: Rep[String] = column[String]("script", O.Length(1000,varying=true))
    /** Database column checksum SqlType(int4), Default(None) */
    val checksum: Rep[Option[Int]] = column[Option[Int]]("checksum", O.Default(None))
    /** Database column installed_by SqlType(varchar), Length(100,true) */
    val installedBy: Rep[String] = column[String]("installed_by", O.Length(100,varying=true))
    /** Database column installed_on SqlType(timestamp) */
    val installedOn: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("installed_on")
    /** Database column execution_time SqlType(int4) */
    val executionTime: Rep[Int] = column[Int]("execution_time")
    /** Database column success SqlType(bool) */
    val success: Rep[Boolean] = column[Boolean]("success")

    /** Index over (success) (database name schema_history_s_idx) */
    val index1 = index("schema_history_s_idx", success)
  }
  /** Collection-like TableQuery object for table SchemaHistory */
  lazy val SchemaHistory = new TableQuery(tag => new SchemaHistory(tag))

  /** Entity class storing rows of table WorkOrderExternalIds
   *  @param workOrderId Database column work_order_id SqlType(varchar), PrimaryKey, Length(36,true)
   *  @param creatorId Database column creator_id SqlType(varchar), Length(50,true), Default(None)
   *  @param masterOfRecordId Database column master_of_record_id SqlType(varchar), Length(50,true), Default(None)
   *  @param lastModifiedTimestamp Database column last_modified_timestamp SqlType(timestamp), Default(None)
   *  @param lastModifiedBy Database column last_modified_by SqlType(varchar), Length(20,true), Default(None)
   *  @param createdTimestamp Database column created_timestamp SqlType(timestamp), Default(None)
   *  @param createdBy Database column created_by SqlType(varchar), Length(20,true), Default(None) */
  case class WorkOrderExternalIdsRow(workOrderId: String, creatorId: Option[String] = None, masterOfRecordId: Option[String] = None, lastModifiedTimestamp: Option[java.sql.Timestamp] = None, lastModifiedBy: Option[String] = None, createdTimestamp: Option[java.sql.Timestamp] = None, createdBy: Option[String] = None)
  /** GetResult implicit for fetching WorkOrderExternalIdsRow objects using plain SQL queries */
  implicit def GetResultWorkOrderExternalIdsRow(implicit e0: GR[String], e1: GR[Option[String]], e2: GR[Option[java.sql.Timestamp]]): GR[WorkOrderExternalIdsRow] = GR{
    prs => import prs._
    WorkOrderExternalIdsRow.tupled((<<[String], <<?[String], <<?[String], <<?[java.sql.Timestamp], <<?[String], <<?[java.sql.Timestamp], <<?[String]))
  }
  /** Table description of table work_order_external_ids. Objects of this class serve as prototypes for rows in queries. */
  class WorkOrderExternalIds(_tableTag: Tag) extends profile.api.Table[WorkOrderExternalIdsRow](_tableTag, Some("datafabric_workorder"), "work_order_external_ids") {
    def * = (workOrderId, creatorId, masterOfRecordId, lastModifiedTimestamp, lastModifiedBy, createdTimestamp, createdBy) <> (WorkOrderExternalIdsRow.tupled, WorkOrderExternalIdsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(workOrderId), creatorId, masterOfRecordId, lastModifiedTimestamp, lastModifiedBy, createdTimestamp, createdBy)).shaped.<>({r=>import r._; _1.map(_=> WorkOrderExternalIdsRow.tupled((_1.get, _2, _3, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column work_order_id SqlType(varchar), PrimaryKey, Length(36,true) */
    val workOrderId: Rep[String] = column[String]("work_order_id", O.PrimaryKey, O.Length(36,varying=true))
    /** Database column creator_id SqlType(varchar), Length(50,true), Default(None) */
    val creatorId: Rep[Option[String]] = column[Option[String]]("creator_id", O.Length(50,varying=true), O.Default(None))
    /** Database column master_of_record_id SqlType(varchar), Length(50,true), Default(None) */
    val masterOfRecordId: Rep[Option[String]] = column[Option[String]]("master_of_record_id", O.Length(50,varying=true), O.Default(None))
    /** Database column last_modified_timestamp SqlType(timestamp), Default(None) */
    val lastModifiedTimestamp: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("last_modified_timestamp", O.Default(None))
    /** Database column last_modified_by SqlType(varchar), Length(20,true), Default(None) */
    val lastModifiedBy: Rep[Option[String]] = column[Option[String]]("last_modified_by", O.Length(20,varying=true), O.Default(None))
    /** Database column created_timestamp SqlType(timestamp), Default(None) */
    val createdTimestamp: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("created_timestamp", O.Default(None))
    /** Database column created_by SqlType(varchar), Length(20,true), Default(None) */
    val createdBy: Rep[Option[String]] = column[Option[String]]("created_by", O.Length(20,varying=true), O.Default(None))

    /** Uniqueness Index over (creatorId) (database name creator_id_unique) */
    val index1 = index("creator_id_unique", creatorId, unique=true)
    /** Uniqueness Index over (masterOfRecordId) (database name master_of_record_id_unique) */
    val index2 = index("master_of_record_id_unique", masterOfRecordId, unique=true)
    /** Uniqueness Index over (workOrderId,creatorId,masterOfRecordId) (database name work_order_external_ids_idx) */
    val index3 = index("work_order_external_ids_idx", (workOrderId, creatorId, masterOfRecordId), unique=true)
  }
  /** Collection-like TableQuery object for table WorkOrderExternalIds */
  lazy val WorkOrderExternalIds = new TableQuery(tag => new WorkOrderExternalIds(tag))

  /** Entity class storing rows of table WorkOrderLocationGis
   *  @param workOrderId Database column work_order_id SqlType(varchar), PrimaryKey, Length(36,true)
   *  @param assetReference Database column asset_reference SqlType(varchar), Length(20,true), Default(None)
   *  @param `type` Database column type SqlType(varchar), Length(20,true), Default(None)
   *  @param referenceType Database column reference_type SqlType(varchar), Length(20,true), Default(None)
   *  @param routeConnection Database column route_connection SqlType(varchar), Length(20,true), Default(None)
   *  @param routeFeatureClass Database column route_feature_class SqlType(varchar), Length(20,true), Default(None)
   *  @param routeFeatureId Database column route_feature_id SqlType(varchar), Length(36,true), Default(None)
   *  @param startPoint Database column start_point SqlType(point), Length(2147483647,false), Default(None)
   *  @param endPoint Database column end_point SqlType(point), Length(2147483647,false), Default(None)
   *  @param startDistance Database column start_distance SqlType(numeric), Default(None)
   *  @param endDistance Database column end_distance SqlType(numeric), Default(None)
   *  @param units Database column units SqlType(varchar), Length(20,true), Default(None)
   *  @param lastModifiedTimestamp Database column last_modified_timestamp SqlType(timestamp), Default(None)
   *  @param lastModifiedBy Database column last_modified_by SqlType(varchar), Length(20,true), Default(None)
   *  @param createdTimestamp Database column created_timestamp SqlType(timestamp), Default(None)
   *  @param createdBy Database column created_by SqlType(varchar), Length(20,true), Default(None) */
  case class WorkOrderLocationGisRow(workOrderId: String, assetReference: Option[String] = None, `type`: Option[String] = None, referenceType: Option[String] = None, routeConnection: Option[String] = None, routeFeatureClass: Option[String] = None, routeFeatureId: Option[String] = None, startPoint: Option[String] = None, endPoint: Option[String] = None, startDistance: Option[scala.math.BigDecimal] = None, endDistance: Option[scala.math.BigDecimal] = None, units: Option[String] = None, lastModifiedTimestamp: Option[java.sql.Timestamp] = None, lastModifiedBy: Option[String] = None, createdTimestamp: Option[java.sql.Timestamp] = None, createdBy: Option[String] = None)
  /** GetResult implicit for fetching WorkOrderLocationGisRow objects using plain SQL queries */
  implicit def GetResultWorkOrderLocationGisRow(implicit e0: GR[String], e1: GR[Option[String]], e2: GR[Option[scala.math.BigDecimal]], e3: GR[Option[java.sql.Timestamp]]): GR[WorkOrderLocationGisRow] = GR{
    prs => import prs._
    WorkOrderLocationGisRow.tupled((<<[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[scala.math.BigDecimal], <<?[String], <<?[java.sql.Timestamp], <<?[String], <<?[java.sql.Timestamp], <<?[String]))
  }
  /** Table description of table work_order_location_gis. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class WorkOrderLocationGis(_tableTag: Tag) extends profile.api.Table[WorkOrderLocationGisRow](_tableTag, Some("datafabric_workorder"), "work_order_location_gis") {
    def * = (workOrderId, assetReference, `type`, referenceType, routeConnection, routeFeatureClass, routeFeatureId, startPoint, endPoint, startDistance, endDistance, units, lastModifiedTimestamp, lastModifiedBy, createdTimestamp, createdBy) <> (WorkOrderLocationGisRow.tupled, WorkOrderLocationGisRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(workOrderId), assetReference, `type`, referenceType, routeConnection, routeFeatureClass, routeFeatureId, startPoint, endPoint, startDistance, endDistance, units, lastModifiedTimestamp, lastModifiedBy, createdTimestamp, createdBy)).shaped.<>({r=>import r._; _1.map(_=> WorkOrderLocationGisRow.tupled((_1.get, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column work_order_id SqlType(varchar), PrimaryKey, Length(36,true) */
    val workOrderId: Rep[String] = column[String]("work_order_id", O.PrimaryKey, O.Length(36,varying=true))
    /** Database column asset_reference SqlType(varchar), Length(20,true), Default(None) */
    val assetReference: Rep[Option[String]] = column[Option[String]]("asset_reference", O.Length(20,varying=true), O.Default(None))
    /** Database column type SqlType(varchar), Length(20,true), Default(None)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Rep[Option[String]] = column[Option[String]]("type", O.Length(20,varying=true), O.Default(None))
    /** Database column reference_type SqlType(varchar), Length(20,true), Default(None) */
    val referenceType: Rep[Option[String]] = column[Option[String]]("reference_type", O.Length(20,varying=true), O.Default(None))
    /** Database column route_connection SqlType(varchar), Length(20,true), Default(None) */
    val routeConnection: Rep[Option[String]] = column[Option[String]]("route_connection", O.Length(20,varying=true), O.Default(None))
    /** Database column route_feature_class SqlType(varchar), Length(20,true), Default(None) */
    val routeFeatureClass: Rep[Option[String]] = column[Option[String]]("route_feature_class", O.Length(20,varying=true), O.Default(None))
    /** Database column route_feature_id SqlType(varchar), Length(36,true), Default(None) */
    val routeFeatureId: Rep[Option[String]] = column[Option[String]]("route_feature_id", O.Length(36,varying=true), O.Default(None))
    /** Database column start_point SqlType(point), Length(2147483647,false), Default(None) */
    val startPoint: Rep[Option[String]] = column[Option[String]]("start_point", O.Length(2147483647,varying=false), O.Default(None))
    /** Database column end_point SqlType(point), Length(2147483647,false), Default(None) */
    val endPoint: Rep[Option[String]] = column[Option[String]]("end_point", O.Length(2147483647,varying=false), O.Default(None))
    /** Database column start_distance SqlType(numeric), Default(None) */
    val startDistance: Rep[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("start_distance", O.Default(None))
    /** Database column end_distance SqlType(numeric), Default(None) */
    val endDistance: Rep[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("end_distance", O.Default(None))
    /** Database column units SqlType(varchar), Length(20,true), Default(None) */
    val units: Rep[Option[String]] = column[Option[String]]("units", O.Length(20,varying=true), O.Default(None))
    /** Database column last_modified_timestamp SqlType(timestamp), Default(None) */
    val lastModifiedTimestamp: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("last_modified_timestamp", O.Default(None))
    /** Database column last_modified_by SqlType(varchar), Length(20,true), Default(None) */
    val lastModifiedBy: Rep[Option[String]] = column[Option[String]]("last_modified_by", O.Length(20,varying=true), O.Default(None))
    /** Database column created_timestamp SqlType(timestamp), Default(None) */
    val createdTimestamp: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("created_timestamp", O.Default(None))
    /** Database column created_by SqlType(varchar), Length(20,true), Default(None) */
    val createdBy: Rep[Option[String]] = column[Option[String]]("created_by", O.Length(20,varying=true), O.Default(None))

    /** Index over (startPoint,endPoint) (database name work_order_location_gis_points_idx) */
    val index1 = index("work_order_location_gis_points_idx", (startPoint, endPoint))
  }
  /** Collection-like TableQuery object for table WorkOrderLocationGis */
  lazy val WorkOrderLocationGis = new TableQuery(tag => new WorkOrderLocationGis(tag))

  /** Entity class storing rows of table WorkOrders
   *  @param workOrderUuid Database column work_order_uuid SqlType(varchar), PrimaryKey, Length(36,true)
   *  @param parentWorkOrder Database column parent_work_order SqlType(varchar), Length(36,true), Default(None)
   *  @param description Database column description SqlType(varchar), Length(100,true)
   *  @param status Database column status SqlType(varchar), Length(32,true)
   *  @param priority Database column priority SqlType(int4), Default(None)
   *  @param workType Database column work_type SqlType(varchar), Length(64,true), Default(None)
   *  @param asset Database column asset SqlType(varchar), Length(36,true), Default(None)
   *  @param assignedTo Database column assigned_to SqlType(varchar), Length(36,true), Default(None)
   *  @param requiredByDateTime Database column required_by_date_time SqlType(timestamp), Default(None)
   *  @param extendedDescription Database column extended_description SqlType(text), Default(None)
   *  @param lastModifiedTimestamp Database column last_modified_timestamp SqlType(timestamp), Default(None)
   *  @param lastModifiedBy Database column last_modified_by SqlType(varchar), Length(20,true), Default(None)
   *  @param createdTimestamp Database column created_timestamp SqlType(timestamp), Default(None)
   *  @param createdBy Database column created_by SqlType(varchar), Length(20,true), Default(None)
   *  @param workOrderId Database column work_order_id SqlType(varchar), Length(32,true), Default(None)
   *  @param workOrderTemplate Database column work_order_template SqlType(varchar), Length(36,true), Default(None)
   *  @param userStatus Database column user_status SqlType(varchar), Length(32,true), Default(None)
   *  @param assetWork Database column asset_work SqlType(bool), Default(None)
   *  @param raisedDateTime Database column raised_date_time SqlType(timestamp), Default(None)
   *  @param raisedBy Database column raised_by SqlType(varchar), Length(36,true), Default(None)
   *  @param plannedStartDateTime Database column planned_start_date_time SqlType(timestamp), Default(None)
   *  @param plannedFinishDateTime Database column planned_finish_date_time SqlType(timestamp), Default(None)
   *  @param actualStartDateTime Database column actual_start_date_time SqlType(timestamp), Default(None)
   *  @param actualFinishDateTime Database column actual_finish_date_time SqlType(timestamp), Default(None)
   *  @param closedDateTime Database column closed_date_time SqlType(timestamp), Default(None)
   *  @param plannedDuration Database column planned_duration SqlType(int8), Default(None)
   *  @param actualDuration Database column actual_duration SqlType(int8), Default(None)
   *  @param completedBy Database column completed_by SqlType(varchar), Length(36,true), Default(None)
   *  @param completionComments Database column completion_comments SqlType(text), Default(None)
   *  @param location Database column location SqlType(varchar), Length(36,true), Default(None)
   *  @param assetPosition Database column asset_position SqlType(varchar), Length(36,true), Default(None)
   *  @param solutionAttributes Database column solution_attributes SqlType(jsonb), Length(2147483647,false), Default(None)
   *  @param customAttributes Database column custom_attributes SqlType(jsonb), Length(2147483647,false), Default(None)
   *  @param responsibleOrg Database column responsible_org SqlType(varchar), Length(36,true), Default(None)
   *  @param account Database column account SqlType(varchar), Length(36,true), Default(None)
   *  @param attachment Database column attachment SqlType(jsonb), Length(2147483647,false), Default(None)
   *  @param productServiceRequirement Database column product_service_requirement SqlType(jsonb), Length(2147483647,false), Default(None)
   *  @param activity Database column activity SqlType(jsonb), Length(2147483647,false), Default(None)
   *  @param nodeClass Database column node_class SqlType(int4), Default(0) */
  case class WorkOrdersRow(workOrderUuid: String, parentWorkOrder: Option[String] = None, description: String, status: String, priority: Option[Int] = None, workType: Option[String] = None, asset: Option[String] = None, assignedTo: Option[String] = None, requiredByDateTime: Option[java.sql.Timestamp] = None, extendedDescription: Option[String] = None, lastModifiedTimestamp: Option[java.sql.Timestamp] = None, lastModifiedBy: Option[String] = None, createdTimestamp: Option[java.sql.Timestamp] = None, createdBy: Option[String] = None, workOrderId: Option[String] = None, workOrderTemplate: Option[String] = None, userStatus: Option[String] = None, assetWork: Option[Boolean] = None, raisedDateTime: Option[java.sql.Timestamp] = None, raisedBy: Option[String] = None, plannedStartDateTime: Option[java.sql.Timestamp] = None, plannedFinishDateTime: Option[java.sql.Timestamp] = None, actualStartDateTime: Option[java.sql.Timestamp] = None, actualFinishDateTime: Option[java.sql.Timestamp] = None, closedDateTime: Option[java.sql.Timestamp] = None, plannedDuration: Option[Long] = None, actualDuration: Option[Long] = None, completedBy: Option[String] = None, completionComments: Option[String] = None, location: Option[String] = None, assetPosition: Option[String] = None, solutionAttributes: Option[String] = None, customAttributes: Option[String] = None, responsibleOrg: Option[String] = None, account: Option[String] = None, attachment: Option[String] = None, productServiceRequirement: Option[String] = None, activity: Option[String] = None, nodeClass: Int = 0)
  /** GetResult implicit for fetching WorkOrdersRow objects using plain SQL queries */
  implicit def GetResultWorkOrdersRow(implicit e0: GR[String], e1: GR[Option[String]], e2: GR[Option[Int]], e3: GR[Option[java.sql.Timestamp]], e4: GR[Option[Boolean]], e5: GR[Option[Long]], e6: GR[Int]): GR[WorkOrdersRow] = GR{
    prs => import prs._
    WorkOrdersRow(<<[String], <<?[String], <<[String], <<[String], <<?[Int], <<?[String], <<?[String], <<?[String], <<?[java.sql.Timestamp], <<?[String], <<?[java.sql.Timestamp], <<?[String], <<?[java.sql.Timestamp], <<?[String], <<?[String], <<?[String], <<?[String], <<?[Boolean], <<?[java.sql.Timestamp], <<?[String], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[Long], <<?[Long], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<[Int])
  }
  /** Table description of table work_orders. Objects of this class serve as prototypes for rows in queries. */
  class WorkOrders(_tableTag: Tag) extends profile.api.Table[WorkOrdersRow](_tableTag, Some("datafabric_workorder"), "work_orders") {
    def * = (workOrderUuid :: parentWorkOrder :: description :: status :: priority :: workType :: asset :: assignedTo :: requiredByDateTime :: extendedDescription :: lastModifiedTimestamp :: lastModifiedBy :: createdTimestamp :: createdBy :: workOrderId :: workOrderTemplate :: userStatus :: assetWork :: raisedDateTime :: raisedBy :: plannedStartDateTime :: plannedFinishDateTime :: actualStartDateTime :: actualFinishDateTime :: closedDateTime :: plannedDuration :: actualDuration :: completedBy :: completionComments :: location :: assetPosition :: solutionAttributes :: customAttributes :: responsibleOrg :: account :: attachment :: productServiceRequirement :: activity :: nodeClass :: HNil).mapTo[WorkOrdersRow]
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(workOrderUuid) :: parentWorkOrder :: Rep.Some(description) :: Rep.Some(status) :: priority :: workType :: asset :: assignedTo :: requiredByDateTime :: extendedDescription :: lastModifiedTimestamp :: lastModifiedBy :: createdTimestamp :: createdBy :: workOrderId :: workOrderTemplate :: userStatus :: assetWork :: raisedDateTime :: raisedBy :: plannedStartDateTime :: plannedFinishDateTime :: actualStartDateTime :: actualFinishDateTime :: closedDateTime :: plannedDuration :: actualDuration :: completedBy :: completionComments :: location :: assetPosition :: solutionAttributes :: customAttributes :: responsibleOrg :: account :: attachment :: productServiceRequirement :: activity :: Rep.Some(nodeClass) :: HNil).shaped.<>(r => WorkOrdersRow(r(0).asInstanceOf[Option[String]].get, r(1).asInstanceOf[Option[String]], r(2).asInstanceOf[Option[String]].get, r(3).asInstanceOf[Option[String]].get, r(4).asInstanceOf[Option[Int]], r(5).asInstanceOf[Option[String]], r(6).asInstanceOf[Option[String]], r(7).asInstanceOf[Option[String]], r(8).asInstanceOf[Option[java.sql.Timestamp]], r(9).asInstanceOf[Option[String]], r(10).asInstanceOf[Option[java.sql.Timestamp]], r(11).asInstanceOf[Option[String]], r(12).asInstanceOf[Option[java.sql.Timestamp]], r(13).asInstanceOf[Option[String]], r(14).asInstanceOf[Option[String]], r(15).asInstanceOf[Option[String]], r(16).asInstanceOf[Option[String]], r(17).asInstanceOf[Option[Boolean]], r(18).asInstanceOf[Option[java.sql.Timestamp]], r(19).asInstanceOf[Option[String]], r(20).asInstanceOf[Option[java.sql.Timestamp]], r(21).asInstanceOf[Option[java.sql.Timestamp]], r(22).asInstanceOf[Option[java.sql.Timestamp]], r(23).asInstanceOf[Option[java.sql.Timestamp]], r(24).asInstanceOf[Option[java.sql.Timestamp]], r(25).asInstanceOf[Option[Long]], r(26).asInstanceOf[Option[Long]], r(27).asInstanceOf[Option[String]], r(28).asInstanceOf[Option[String]], r(29).asInstanceOf[Option[String]], r(30).asInstanceOf[Option[String]], r(31).asInstanceOf[Option[String]], r(32).asInstanceOf[Option[String]], r(33).asInstanceOf[Option[String]], r(34).asInstanceOf[Option[String]], r(35).asInstanceOf[Option[String]], r(36).asInstanceOf[Option[String]], r(37).asInstanceOf[Option[String]], r(38).asInstanceOf[Option[Int]].get), (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column work_order_uuid SqlType(varchar), PrimaryKey, Length(36,true) */
    val workOrderUuid: Rep[String] = column[String]("work_order_uuid", O.PrimaryKey, O.Length(36,varying=true))
    /** Database column parent_work_order SqlType(varchar), Length(36,true), Default(None) */
    val parentWorkOrder: Rep[Option[String]] = column[Option[String]]("parent_work_order", O.Length(36,varying=true), O.Default(None))
    /** Database column description SqlType(varchar), Length(100,true) */
    val description: Rep[String] = column[String]("description", O.Length(100,varying=true))
    /** Database column status SqlType(varchar), Length(32,true) */
    val status: Rep[String] = column[String]("status", O.Length(32,varying=true))
    /** Database column priority SqlType(int4), Default(None) */
    val priority: Rep[Option[Int]] = column[Option[Int]]("priority", O.Default(None))
    /** Database column work_type SqlType(varchar), Length(64,true), Default(None) */
    val workType: Rep[Option[String]] = column[Option[String]]("work_type", O.Length(64,varying=true), O.Default(None))
    /** Database column asset SqlType(varchar), Length(36,true), Default(None) */
    val asset: Rep[Option[String]] = column[Option[String]]("asset", O.Length(36,varying=true), O.Default(None))
    /** Database column assigned_to SqlType(varchar), Length(36,true), Default(None) */
    val assignedTo: Rep[Option[String]] = column[Option[String]]("assigned_to", O.Length(36,varying=true), O.Default(None))
    /** Database column required_by_date_time SqlType(timestamp), Default(None) */
    val requiredByDateTime: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("required_by_date_time", O.Default(None))
    /** Database column extended_description SqlType(text), Default(None) */
    val extendedDescription: Rep[Option[String]] = column[Option[String]]("extended_description", O.Default(None))
    /** Database column last_modified_timestamp SqlType(timestamp), Default(None) */
    val lastModifiedTimestamp: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("last_modified_timestamp", O.Default(None))
    /** Database column last_modified_by SqlType(varchar), Length(20,true), Default(None) */
    val lastModifiedBy: Rep[Option[String]] = column[Option[String]]("last_modified_by", O.Length(20,varying=true), O.Default(None))
    /** Database column created_timestamp SqlType(timestamp), Default(None) */
    val createdTimestamp: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("created_timestamp", O.Default(None))
    /** Database column created_by SqlType(varchar), Length(20,true), Default(None) */
    val createdBy: Rep[Option[String]] = column[Option[String]]("created_by", O.Length(20,varying=true), O.Default(None))
    /** Database column work_order_id SqlType(varchar), Length(32,true), Default(None) */
    val workOrderId: Rep[Option[String]] = column[Option[String]]("work_order_id", O.Length(32,varying=true), O.Default(None))
    /** Database column work_order_template SqlType(varchar), Length(36,true), Default(None) */
    val workOrderTemplate: Rep[Option[String]] = column[Option[String]]("work_order_template", O.Length(36,varying=true), O.Default(None))
    /** Database column user_status SqlType(varchar), Length(32,true), Default(None) */
    val userStatus: Rep[Option[String]] = column[Option[String]]("user_status", O.Length(32,varying=true), O.Default(None))
    /** Database column asset_work SqlType(bool), Default(None) */
    val assetWork: Rep[Option[Boolean]] = column[Option[Boolean]]("asset_work", O.Default(None))
    /** Database column raised_date_time SqlType(timestamp), Default(None) */
    val raisedDateTime: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("raised_date_time", O.Default(None))
    /** Database column raised_by SqlType(varchar), Length(36,true), Default(None) */
    val raisedBy: Rep[Option[String]] = column[Option[String]]("raised_by", O.Length(36,varying=true), O.Default(None))
    /** Database column planned_start_date_time SqlType(timestamp), Default(None) */
    val plannedStartDateTime: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("planned_start_date_time", O.Default(None))
    /** Database column planned_finish_date_time SqlType(timestamp), Default(None) */
    val plannedFinishDateTime: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("planned_finish_date_time", O.Default(None))
    /** Database column actual_start_date_time SqlType(timestamp), Default(None) */
    val actualStartDateTime: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("actual_start_date_time", O.Default(None))
    /** Database column actual_finish_date_time SqlType(timestamp), Default(None) */
    val actualFinishDateTime: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("actual_finish_date_time", O.Default(None))
    /** Database column closed_date_time SqlType(timestamp), Default(None) */
    val closedDateTime: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("closed_date_time", O.Default(None))
    /** Database column planned_duration SqlType(int8), Default(None) */
    val plannedDuration: Rep[Option[Long]] = column[Option[Long]]("planned_duration", O.Default(None))
    /** Database column actual_duration SqlType(int8), Default(None) */
    val actualDuration: Rep[Option[Long]] = column[Option[Long]]("actual_duration", O.Default(None))
    /** Database column completed_by SqlType(varchar), Length(36,true), Default(None) */
    val completedBy: Rep[Option[String]] = column[Option[String]]("completed_by", O.Length(36,varying=true), O.Default(None))
    /** Database column completion_comments SqlType(text), Default(None) */
    val completionComments: Rep[Option[String]] = column[Option[String]]("completion_comments", O.Default(None))
    /** Database column location SqlType(varchar), Length(36,true), Default(None) */
    val location: Rep[Option[String]] = column[Option[String]]("location", O.Length(36,varying=true), O.Default(None))
    /** Database column asset_position SqlType(varchar), Length(36,true), Default(None) */
    val assetPosition: Rep[Option[String]] = column[Option[String]]("asset_position", O.Length(36,varying=true), O.Default(None))
    /** Database column solution_attributes SqlType(jsonb), Length(2147483647,false), Default(None) */
    val solutionAttributes: Rep[Option[String]] = column[Option[String]]("solution_attributes", O.Length(2147483647,varying=false), O.Default(None))
    /** Database column custom_attributes SqlType(jsonb), Length(2147483647,false), Default(None) */
    val customAttributes: Rep[Option[String]] = column[Option[String]]("custom_attributes", O.Length(2147483647,varying=false), O.Default(None))
    /** Database column responsible_org SqlType(varchar), Length(36,true), Default(None) */
    val responsibleOrg: Rep[Option[String]] = column[Option[String]]("responsible_org", O.Length(36,varying=true), O.Default(None))
    /** Database column account SqlType(varchar), Length(36,true), Default(None) */
    val account: Rep[Option[String]] = column[Option[String]]("account", O.Length(36,varying=true), O.Default(None))
    /** Database column attachment SqlType(jsonb), Length(2147483647,false), Default(None) */
    val attachment: Rep[Option[String]] = column[Option[String]]("attachment", O.Length(2147483647,varying=false), O.Default(None))
    /** Database column product_service_requirement SqlType(jsonb), Length(2147483647,false), Default(None) */
    val productServiceRequirement: Rep[Option[String]] = column[Option[String]]("product_service_requirement", O.Length(2147483647,varying=false), O.Default(None))
    /** Database column activity SqlType(jsonb), Length(2147483647,false), Default(None) */
    val activity: Rep[Option[String]] = column[Option[String]]("activity", O.Length(2147483647,varying=false), O.Default(None))
    /** Database column node_class SqlType(int4), Default(0) */
    val nodeClass: Rep[Int] = column[Int]("node_class", O.Default(0))
  }
  /** Collection-like TableQuery object for table WorkOrders */
  lazy val WorkOrders = new TableQuery(tag => new WorkOrders(tag))

  /** Entity class storing rows of table WorkOrderTemplateDependencies
   *  @param workOrderTemplateDependencyUuid Database column work_order_template_dependency_uuid SqlType(varchar), PrimaryKey, Length(36,true)
   *  @param sourceWorkOrderTemplate Database column source_work_order_template SqlType(varchar), Length(36,true), Default(None)
   *  @param targetWorkOrderTemplate Database column target_work_order_template SqlType(varchar), Length(36,true), Default(None)
   *  @param dependencyType Database column dependency_type SqlType(varchar), Length(20,true), Default(None)
   *  @param solutionAttributes Database column solution_attributes SqlType(jsonb), Length(2147483647,false), Default(None)
   *  @param customAttributes Database column custom_attributes SqlType(jsonb), Length(2147483647,false), Default(None)
   *  @param lastModifiedTimestamp Database column last_modified_timestamp SqlType(timestamp), Default(None)
   *  @param lastModifiedBy Database column last_modified_by SqlType(varchar), Length(20,true), Default(None)
   *  @param createdTimestamp Database column created_timestamp SqlType(timestamp), Default(None)
   *  @param createdBy Database column created_by SqlType(varchar), Length(20,true), Default(None) */
  case class WorkOrderTemplateDependenciesRow(workOrderTemplateDependencyUuid: String, sourceWorkOrderTemplate: Option[String] = None, targetWorkOrderTemplate: Option[String] = None, dependencyType: Option[String] = None, solutionAttributes: Option[String] = None, customAttributes: Option[String] = None, lastModifiedTimestamp: Option[java.sql.Timestamp] = None, lastModifiedBy: Option[String] = None, createdTimestamp: Option[java.sql.Timestamp] = None, createdBy: Option[String] = None)
  /** GetResult implicit for fetching WorkOrderTemplateDependenciesRow objects using plain SQL queries */
  implicit def GetResultWorkOrderTemplateDependenciesRow(implicit e0: GR[String], e1: GR[Option[String]], e2: GR[Option[java.sql.Timestamp]]): GR[WorkOrderTemplateDependenciesRow] = GR{
    prs => import prs._
    WorkOrderTemplateDependenciesRow.tupled((<<[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[java.sql.Timestamp], <<?[String], <<?[java.sql.Timestamp], <<?[String]))
  }
  /** Table description of table work_order_template_dependencies. Objects of this class serve as prototypes for rows in queries. */
  class WorkOrderTemplateDependencies(_tableTag: Tag) extends profile.api.Table[WorkOrderTemplateDependenciesRow](_tableTag, Some("datafabric_workorder"), "work_order_template_dependencies") {
    def * = (workOrderTemplateDependencyUuid, sourceWorkOrderTemplate, targetWorkOrderTemplate, dependencyType, solutionAttributes, customAttributes, lastModifiedTimestamp, lastModifiedBy, createdTimestamp, createdBy) <> (WorkOrderTemplateDependenciesRow.tupled, WorkOrderTemplateDependenciesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(workOrderTemplateDependencyUuid), sourceWorkOrderTemplate, targetWorkOrderTemplate, dependencyType, solutionAttributes, customAttributes, lastModifiedTimestamp, lastModifiedBy, createdTimestamp, createdBy)).shaped.<>({r=>import r._; _1.map(_=> WorkOrderTemplateDependenciesRow.tupled((_1.get, _2, _3, _4, _5, _6, _7, _8, _9, _10)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column work_order_template_dependency_uuid SqlType(varchar), PrimaryKey, Length(36,true) */
    val workOrderTemplateDependencyUuid: Rep[String] = column[String]("work_order_template_dependency_uuid", O.PrimaryKey, O.Length(36,varying=true))
    /** Database column source_work_order_template SqlType(varchar), Length(36,true), Default(None) */
    val sourceWorkOrderTemplate: Rep[Option[String]] = column[Option[String]]("source_work_order_template", O.Length(36,varying=true), O.Default(None))
    /** Database column target_work_order_template SqlType(varchar), Length(36,true), Default(None) */
    val targetWorkOrderTemplate: Rep[Option[String]] = column[Option[String]]("target_work_order_template", O.Length(36,varying=true), O.Default(None))
    /** Database column dependency_type SqlType(varchar), Length(20,true), Default(None) */
    val dependencyType: Rep[Option[String]] = column[Option[String]]("dependency_type", O.Length(20,varying=true), O.Default(None))
    /** Database column solution_attributes SqlType(jsonb), Length(2147483647,false), Default(None) */
    val solutionAttributes: Rep[Option[String]] = column[Option[String]]("solution_attributes", O.Length(2147483647,varying=false), O.Default(None))
    /** Database column custom_attributes SqlType(jsonb), Length(2147483647,false), Default(None) */
    val customAttributes: Rep[Option[String]] = column[Option[String]]("custom_attributes", O.Length(2147483647,varying=false), O.Default(None))
    /** Database column last_modified_timestamp SqlType(timestamp), Default(None) */
    val lastModifiedTimestamp: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("last_modified_timestamp", O.Default(None))
    /** Database column last_modified_by SqlType(varchar), Length(20,true), Default(None) */
    val lastModifiedBy: Rep[Option[String]] = column[Option[String]]("last_modified_by", O.Length(20,varying=true), O.Default(None))
    /** Database column created_timestamp SqlType(timestamp), Default(None) */
    val createdTimestamp: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("created_timestamp", O.Default(None))
    /** Database column created_by SqlType(varchar), Length(20,true), Default(None) */
    val createdBy: Rep[Option[String]] = column[Option[String]]("created_by", O.Length(20,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table WorkOrderTemplateDependencies */
  lazy val WorkOrderTemplateDependencies = new TableQuery(tag => new WorkOrderTemplateDependencies(tag))

  /** Entity class storing rows of table WorkOrderTemplates
   *  @param workOrderTemplateUuid Database column work_order_template_uuid SqlType(varchar), PrimaryKey, Length(36,true)
   *  @param workOrderTemplateId Database column work_order_template_id SqlType(varchar), Length(32,true), Default(None)
   *  @param parentWorkOrderTemplate Database column parent_work_order_template SqlType(varchar), Length(36,true), Default(None)
   *  @param description Database column description SqlType(varchar), Length(100,true), Default(None)
   *  @param extendedDescription Database column extended_description SqlType(text), Default(None)
   *  @param extendeddescription Database column extendeddescription SqlType(text), Default(None)
   *  @param status Database column status SqlType(varchar), Length(32,true), Default(None)
   *  @param assetWork Database column asset_work SqlType(bool), Default(None)
   *  @param workType Database column work_type SqlType(varchar), Length(20,true), Default(None)
   *  @param defaultPriority Database column default_priority SqlType(int4), Default(None)
   *  @param duration Database column duration SqlType(int8), Default(None)
   *  @param ownedBy Database column owned_by SqlType(varchar), Length(36,true), Default(None)
   *  @param activeDateTime Database column active_date_time SqlType(timestamp), Default(None)
   *  @param activatedBy Database column activated_by SqlType(varchar), Length(36,true), Default(None)
   *  @param asset Database column asset SqlType(varchar), Length(36,true), Default(None)
   *  @param assetPosition Database column asset_position SqlType(varchar), Length(36,true), Default(None)
   *  @param assignedTo Database column assigned_to SqlType(varchar), Length(36,true), Default(None)
   *  @param solutionAttributes Database column solution_attributes SqlType(jsonb), Length(2147483647,false), Default(None)
   *  @param customAttributes Database column custom_attributes SqlType(jsonb), Length(2147483647,false), Default(None)
   *  @param lastModifiedTimestamp Database column last_modified_timestamp SqlType(timestamp), Default(None)
   *  @param lastModifiedBy Database column last_modified_by SqlType(varchar), Length(20,true), Default(None)
   *  @param createdTimestamp Database column created_timestamp SqlType(timestamp), Default(None)
   *  @param createdBy Database column created_by SqlType(varchar), Length(20,true), Default(None)
   *  @param responsibleOrg Database column responsible_org SqlType(varchar), Length(36,true), Default(None)
   *  @param attachment Database column attachment SqlType(jsonb), Length(2147483647,false), Default(None)
   *  @param productServiceRequirement Database column product_service_requirement SqlType(jsonb), Length(2147483647,false), Default(None)
   *  @param activityTemplate Database column activity_template SqlType(jsonb), Length(2147483647,false), Default(None) */
  case class WorkOrderTemplatesRow(workOrderTemplateUuid: String, workOrderTemplateId: Option[String] = None, parentWorkOrderTemplate: Option[String] = None, description: Option[String] = None, extendedDescription: Option[String] = None, extendeddescription: Option[String] = None, status: Option[String] = None, assetWork: Option[Boolean] = None, workType: Option[String] = None, defaultPriority: Option[Int] = None, duration: Option[Long] = None, ownedBy: Option[String] = None, activeDateTime: Option[java.sql.Timestamp] = None, activatedBy: Option[String] = None, asset: Option[String] = None, assetPosition: Option[String] = None, assignedTo: Option[String] = None, solutionAttributes: Option[String] = None, customAttributes: Option[String] = None, lastModifiedTimestamp: Option[java.sql.Timestamp] = None, lastModifiedBy: Option[String] = None, createdTimestamp: Option[java.sql.Timestamp] = None, createdBy: Option[String] = None, responsibleOrg: Option[String] = None, attachment: Option[String] = None, productServiceRequirement: Option[String] = None, activityTemplate: Option[String] = None)
  /** GetResult implicit for fetching WorkOrderTemplatesRow objects using plain SQL queries */
  implicit def GetResultWorkOrderTemplatesRow(implicit e0: GR[String], e1: GR[Option[String]], e2: GR[Option[Boolean]], e3: GR[Option[Int]], e4: GR[Option[Long]], e5: GR[Option[java.sql.Timestamp]]): GR[WorkOrderTemplatesRow] = GR{
    prs => import prs._
    WorkOrderTemplatesRow(<<[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[Boolean], <<?[String], <<?[Int], <<?[Long], <<?[String], <<?[java.sql.Timestamp], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[java.sql.Timestamp], <<?[String], <<?[java.sql.Timestamp], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String])
  }
  /** Table description of table work_order_templates. Objects of this class serve as prototypes for rows in queries. */
  class WorkOrderTemplates(_tableTag: Tag) extends profile.api.Table[WorkOrderTemplatesRow](_tableTag, Some("datafabric_workorder"), "work_order_templates") {
    def * = (workOrderTemplateUuid :: workOrderTemplateId :: parentWorkOrderTemplate :: description :: extendedDescription :: extendeddescription :: status :: assetWork :: workType :: defaultPriority :: duration :: ownedBy :: activeDateTime :: activatedBy :: asset :: assetPosition :: assignedTo :: solutionAttributes :: customAttributes :: lastModifiedTimestamp :: lastModifiedBy :: createdTimestamp :: createdBy :: responsibleOrg :: attachment :: productServiceRequirement :: activityTemplate :: HNil).mapTo[WorkOrderTemplatesRow]
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(workOrderTemplateUuid) :: workOrderTemplateId :: parentWorkOrderTemplate :: description :: extendedDescription :: extendeddescription :: status :: assetWork :: workType :: defaultPriority :: duration :: ownedBy :: activeDateTime :: activatedBy :: asset :: assetPosition :: assignedTo :: solutionAttributes :: customAttributes :: lastModifiedTimestamp :: lastModifiedBy :: createdTimestamp :: createdBy :: responsibleOrg :: attachment :: productServiceRequirement :: activityTemplate :: HNil).shaped.<>(r => WorkOrderTemplatesRow(r(0).asInstanceOf[Option[String]].get, r(1).asInstanceOf[Option[String]], r(2).asInstanceOf[Option[String]], r(3).asInstanceOf[Option[String]], r(4).asInstanceOf[Option[String]], r(5).asInstanceOf[Option[String]], r(6).asInstanceOf[Option[String]], r(7).asInstanceOf[Option[Boolean]], r(8).asInstanceOf[Option[String]], r(9).asInstanceOf[Option[Int]], r(10).asInstanceOf[Option[Long]], r(11).asInstanceOf[Option[String]], r(12).asInstanceOf[Option[java.sql.Timestamp]], r(13).asInstanceOf[Option[String]], r(14).asInstanceOf[Option[String]], r(15).asInstanceOf[Option[String]], r(16).asInstanceOf[Option[String]], r(17).asInstanceOf[Option[String]], r(18).asInstanceOf[Option[String]], r(19).asInstanceOf[Option[java.sql.Timestamp]], r(20).asInstanceOf[Option[String]], r(21).asInstanceOf[Option[java.sql.Timestamp]], r(22).asInstanceOf[Option[String]], r(23).asInstanceOf[Option[String]], r(24).asInstanceOf[Option[String]], r(25).asInstanceOf[Option[String]], r(26).asInstanceOf[Option[String]]), (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column work_order_template_uuid SqlType(varchar), PrimaryKey, Length(36,true) */
    val workOrderTemplateUuid: Rep[String] = column[String]("work_order_template_uuid", O.PrimaryKey, O.Length(36,varying=true))
    /** Database column work_order_template_id SqlType(varchar), Length(32,true), Default(None) */
    val workOrderTemplateId: Rep[Option[String]] = column[Option[String]]("work_order_template_id", O.Length(32,varying=true), O.Default(None))
    /** Database column parent_work_order_template SqlType(varchar), Length(36,true), Default(None) */
    val parentWorkOrderTemplate: Rep[Option[String]] = column[Option[String]]("parent_work_order_template", O.Length(36,varying=true), O.Default(None))
    /** Database column description SqlType(varchar), Length(100,true), Default(None) */
    val description: Rep[Option[String]] = column[Option[String]]("description", O.Length(100,varying=true), O.Default(None))
    /** Database column extended_description SqlType(text), Default(None) */
    val extendedDescription: Rep[Option[String]] = column[Option[String]]("extended_description", O.Default(None))
    /** Database column extendeddescription SqlType(text), Default(None) */
    val extendeddescription: Rep[Option[String]] = column[Option[String]]("extendeddescription", O.Default(None))
    /** Database column status SqlType(varchar), Length(32,true), Default(None) */
    val status: Rep[Option[String]] = column[Option[String]]("status", O.Length(32,varying=true), O.Default(None))
    /** Database column asset_work SqlType(bool), Default(None) */
    val assetWork: Rep[Option[Boolean]] = column[Option[Boolean]]("asset_work", O.Default(None))
    /** Database column work_type SqlType(varchar), Length(20,true), Default(None) */
    val workType: Rep[Option[String]] = column[Option[String]]("work_type", O.Length(20,varying=true), O.Default(None))
    /** Database column default_priority SqlType(int4), Default(None) */
    val defaultPriority: Rep[Option[Int]] = column[Option[Int]]("default_priority", O.Default(None))
    /** Database column duration SqlType(int8), Default(None) */
    val duration: Rep[Option[Long]] = column[Option[Long]]("duration", O.Default(None))
    /** Database column owned_by SqlType(varchar), Length(36,true), Default(None) */
    val ownedBy: Rep[Option[String]] = column[Option[String]]("owned_by", O.Length(36,varying=true), O.Default(None))
    /** Database column active_date_time SqlType(timestamp), Default(None) */
    val activeDateTime: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("active_date_time", O.Default(None))
    /** Database column activated_by SqlType(varchar), Length(36,true), Default(None) */
    val activatedBy: Rep[Option[String]] = column[Option[String]]("activated_by", O.Length(36,varying=true), O.Default(None))
    /** Database column asset SqlType(varchar), Length(36,true), Default(None) */
    val asset: Rep[Option[String]] = column[Option[String]]("asset", O.Length(36,varying=true), O.Default(None))
    /** Database column asset_position SqlType(varchar), Length(36,true), Default(None) */
    val assetPosition: Rep[Option[String]] = column[Option[String]]("asset_position", O.Length(36,varying=true), O.Default(None))
    /** Database column assigned_to SqlType(varchar), Length(36,true), Default(None) */
    val assignedTo: Rep[Option[String]] = column[Option[String]]("assigned_to", O.Length(36,varying=true), O.Default(None))
    /** Database column solution_attributes SqlType(jsonb), Length(2147483647,false), Default(None) */
    val solutionAttributes: Rep[Option[String]] = column[Option[String]]("solution_attributes", O.Length(2147483647,varying=false), O.Default(None))
    /** Database column custom_attributes SqlType(jsonb), Length(2147483647,false), Default(None) */
    val customAttributes: Rep[Option[String]] = column[Option[String]]("custom_attributes", O.Length(2147483647,varying=false), O.Default(None))
    /** Database column last_modified_timestamp SqlType(timestamp), Default(None) */
    val lastModifiedTimestamp: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("last_modified_timestamp", O.Default(None))
    /** Database column last_modified_by SqlType(varchar), Length(20,true), Default(None) */
    val lastModifiedBy: Rep[Option[String]] = column[Option[String]]("last_modified_by", O.Length(20,varying=true), O.Default(None))
    /** Database column created_timestamp SqlType(timestamp), Default(None) */
    val createdTimestamp: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("created_timestamp", O.Default(None))
    /** Database column created_by SqlType(varchar), Length(20,true), Default(None) */
    val createdBy: Rep[Option[String]] = column[Option[String]]("created_by", O.Length(20,varying=true), O.Default(None))
    /** Database column responsible_org SqlType(varchar), Length(36,true), Default(None) */
    val responsibleOrg: Rep[Option[String]] = column[Option[String]]("responsible_org", O.Length(36,varying=true), O.Default(None))
    /** Database column attachment SqlType(jsonb), Length(2147483647,false), Default(None) */
    val attachment: Rep[Option[String]] = column[Option[String]]("attachment", O.Length(2147483647,varying=false), O.Default(None))
    /** Database column product_service_requirement SqlType(jsonb), Length(2147483647,false), Default(None) */
    val productServiceRequirement: Rep[Option[String]] = column[Option[String]]("product_service_requirement", O.Length(2147483647,varying=false), O.Default(None))
    /** Database column activity_template SqlType(jsonb), Length(2147483647,false), Default(None) */
    val activityTemplate: Rep[Option[String]] = column[Option[String]]("activity_template", O.Length(2147483647,varying=false), O.Default(None))
  }
  /** Collection-like TableQuery object for table WorkOrderTemplates */
  lazy val WorkOrderTemplates = new TableQuery(tag => new WorkOrderTemplates(tag))
}
