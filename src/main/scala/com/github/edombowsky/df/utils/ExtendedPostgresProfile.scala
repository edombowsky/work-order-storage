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

package com.github.edombowsky.df.utils

import com.github.tminglei.slickpg._
import com.github.tminglei.slickpg.PgCirceJsonSupport


//trait MyPostgresProfile extends PostgresProfile
//  with PgCirceJsonSupport
//  with array.PgArrayJdbcTypes {
//  override val pgjson = "jsonb"
//
//  override val api: API = new API {}
//
//  val plainAPI = new API with CirceJsonPlainImplicits
//
//  ///
//  trait API extends super.API with JsonImplicits {
//    implicit val strListTypeMapper = new SimpleArrayJdbcType[String]("text").to(_.toList)
//  }
//}
//object MyPostgresProfile extends MyPostgresProfile

trait ExtendedPostgresProfile extends ExPostgresProfile /*PostgresProfile*/
  with PgCirceJsonSupport
  with array.PgArrayJdbcTypes {

  override val pgjson = "jsonb"

  override val api: API = new API {}

  val plainAPI = new API with CirceJsonPlainImplicits

  trait API extends super.API with JsonImplicits {
    implicit val strListTypeMapper = new SimpleArrayJdbcType[String]("text").to(_.toList)
  }
}

object ExtendedPostgresProfile extends ExtendedPostgresProfile

//trait ExtendedPostgresProfile extends ExPostgresProfile with PgPlayJsonSupport {
//  def pgjson = "jsonb"
//
//  override protected def computeCapabilities: Set[Capability] =
//    super.computeCapabilities + JdbcCapabilities.insertOrUpdate
//
//  override val api = PostgresJsonSupportAPI
//
//  object PostgresJsonSupportAPI extends API with JsonImplicits
//}
//
//object ExtendedPostgresProfile extends ExtendedPostgresProfile
/**
 * {@link https://github.com/tminglei/slick-pg } Custom Slick PostgreSql Driver
 *
 */
//trait ExtendedPostgresProfile extends ExPostgresProfile
//  with PgArraySupport
//  with PgDateSupport
//  with PgRangeSupport
//  with PgHStoreSupport
//  with PgPlayJsonSupport
//  with PgSearchSupport
//  with PgNetSupport
//  with PgLTreeSupport {
//
//  def pgjson = "jsonb"
//
//  // Add back `capabilities.insertOrUpdate` to enable native `upsert` support;
//  // for postgres 9.5+
//  override protected def computeCapabilities: Set[Capability] =
//      super.computeCapabilities + JdbcCapabilities.insertOrUpdate
//
//  override val api = MyAPI
//
//  object MyAPI extends API
//    with ArrayImplicits
//    with DateTimeImplicits
//    with JsonImplicits
//    with NetImplicits
//    with LTreeImplicits
//    with RangeImplicits
//    with HStoreImplicits
//    with SearchImplicits
//    with SearchAssistants {
//
//    //implicit val jsonListTypeMapper = new SimpleArrayJdbcType[String]("jsonb").to(_.toList)
//
//    implicit val strListTypeMapper = new SimpleArrayJdbcType[String]("text").to(_.toVector)
//    implicit val playJsonArrayTypeMapper =
//      new AdvancedArrayJdbcType[JsValue](pgjson,
//        (s) => SimpleArrayUtils.fromString[JsValue](Json.parse(_))(s).orNull,
//        (v) => SimpleArrayUtils.mkString[JsValue](_.toString())(v)
//      ).to(_.toList)
//
//  }
//}
//
//object ExtendedPostgresProfile extends ExtendedPostgresProfile

/*
trait MyPostgresProfile extends ExPostgresProfile with PgPlayJsonSupport {

  def pgjson = "jsonb" // jsonb support is in postgres 9.4.0 onward; for 9.3.x use "json"

  //// Add back `capabilities.insertOrUpdate` to enable native `upsert` support; for postgres 9.5+
  //override protected def computeCapabilities: Set[Capability] =
  //  super.computeCapabilities + JdbcProfile.capabilities.insertOrUpdate

  override val api = MyAPI

  object MyAPI extends API with JsonImplicits {
    implicit val playJsonArrayTypeMapper =
      new AdvancedArrayJdbcType[JsValue](pgjson,
        (s) => utils.SimpleArrayUtils.fromString[JsValue](Json.parse(_))(s).orNull,
        (v) => utils.SimpleArrayUtils.mkString[JsValue](_.toString())(v)
      ).to(_.toList)
  }
}

object MyPostgresProfile extends MyPostgresProfile
*/
