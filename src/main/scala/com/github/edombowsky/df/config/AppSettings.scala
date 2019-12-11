package com.github.edombowsky.df.config

import scala.reflect.internal.FatalError

import com.typesafe.config.ConfigFactory
import slick.basic.DatabaseConfig
import slick.driver.JdbcProfile
import slick.jdbc.JdbcProfile

object AppSettings {

  lazy val dbConfig: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig("database")

  val databaseUrl = dbConfig.config.getString("db.url")
  val databaseUser = dbConfig.config.getString("db.user")
  val databasePassword = dbConfig.config.getString("db.password")
  val db = dbConfig.db
}

