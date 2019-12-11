package com.github.edombowsky.df.utils

import scala.concurrent.Future
import scala.util.Try

import slick.basic.DatabaseConfig
import slick.dbio.DBIO
import slick.jdbc.JdbcProfile

import com.github.edombowsky.df.config.AppSettings


trait DB {
  //private val Test = "test"
  //private val Dev = "dev"
  //private val Prod = "prod"
  //
  //private val TestProfile = "h2mem"
  //private val ProductionProfile = "postgres"

  //lazy val dbConfig: DatabaseConfig[JdbcProfile] =
  //  sys.env.get("RUN_MODE") match {
  //    case Some(Test) => DatabaseConfig.forConfig(TestProfile)
  //    case Some(Prod) | Some(Dev) => DatabaseConfig.forConfig(ProductionProfile)
  //    case _ => DatabaseConfig.forConfig(ProductionProfile)//throw FatalError("unknown runMode")
  //  }

  val databaseUrl = AppSettings.databaseUrl           // dbConfig.config.getString("db.url")
  val databaseUser = AppSettings.databaseUser         // dbConfig.config.getString("db.user")
  val databasePassword = AppSettings.databasePassword // dbConfig.config.getString("db.password")

  val db: JdbcProfile#Backend#Database = AppSettings.db // dbConfig.db

  /**
   * Wrapper around the db.run so that this can it can be easily spied on
   * if required.
   *
   * @param action action to be run
   * @tparam M the type
   *
   * @return Future[M]
   */
  def run[M](action: DBIO[M]): Try[Future[M]] =
    Try(db.run(action))
}
