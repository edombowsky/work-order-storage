package com.github.edombowsky.df.config

import scala.reflect.internal.FatalError

import com.typesafe.config.ConfigFactory
import slick.basic.DatabaseConfig
import slick.driver.JdbcProfile
import slick.jdbc.JdbcProfile

object AppSettings {

  private val Test = "test"
  private val Dev = "dev"
  private val Prod = "prod"

  private val TestProfile = "h2mem"
  private val ProductionProfile = "postgres"

  val runMode: Option[String] = sys.env.get("RUN_MODE")

  lazy val config = ConfigFactory.parseResources("product.conf").withFallback(ConfigFactory.load())
  lazy val dbConfig: DatabaseConfig[JdbcProfile]  = runMode match {
    case Some(Test) => DatabaseConfig.forConfig(TestProfile) //config.getConfig(TestProfile)
    case Some(Prod) => DatabaseConfig.forConfig(ProductionProfile) //config.getConfig(ProductionProfile)
    case _ => throw FatalError("unknown runMode")
  }

  val databaseUrl = dbConfig.config.getString("db.url")
  val databaseUser = dbConfig.config.getString("db.user")
  val databasePassword = dbConfig.config.getString("db.password")
  val db = dbConfig.db
}

