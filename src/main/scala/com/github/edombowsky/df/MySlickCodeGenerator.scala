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

package com.github.edombowsky.df

import scala.concurrent.Await
import scala.concurrent.duration.Duration

import slick.codegen.SourceCodeGenerator
import slick.jdbc.JdbcProfile

import com.github.edombowsky.df.utils.ExtendedPostgresProfile


object MySlickCodeGenerator {

  import concurrent.ExecutionContext.Implicits.global

  val slickDriver = "slick.jdbc.PostgresProfile"
  val jdbcDriver = "org.postgresql.Driver"
  val url = "jdbc:postgresql://localhost:25432/caeadom?useUnicode=true&characterEncoding=utf-8&stringtype=unspecified&currentSchema=datafabric_workorde"
  val outputFolder = "target/gencode/genTablesPsql"
  val pkg = "com.github.edombowsky.models"
  val user = "caeadom"
  val password = ""

  def genDefaultTables(): Unit = {

    slick.codegen.SourceCodeGenerator.main(
      Array(slickDriver, jdbcDriver, url, outputFolder, pkg, user, password)
    )
  }

  def main(args: Array[String]) {
    genCustomTables(ExtendedPostgresProfile)

    println(s"Tables.scala generated in $outputFolder")
  }


  def genCustomTables(dbDriver: JdbcProfile): Unit = {

    // fetch data model
    val driver: JdbcProfile =
      Class.forName(slickDriver + "$").getField("MODULE$").get(null).asInstanceOf[JdbcProfile]
    val dbFactory = driver.api.Database
    val db = dbFactory.forURL(url, driver = jdbcDriver,
      user = user, password = password, keepAliveConnection = true)

    // fetch data model
    val modelAction = dbDriver.createModel(Some(dbDriver.defaultTables)) // you can filter specific tables here
    val modelFuture = db.run(modelAction)

    // customize code generator
    val codeGenFuture = modelFuture.map(model => new SourceCodeGenerator(model) {
      // override mapped table and class name
      override def entityName = dbTableName => "r" + dbTableName.toCamelCase
      override def tableName = dbTableName => "t" + dbTableName.toCamelCase
    })

    val codeGenerator = Await.result(codeGenFuture, Duration.Inf)
    codeGenerator.writeToFile(slickDriver, outputFolder, pkg,
      "SlickTables",
      "SlickTables.scala"
    )
  }
}
