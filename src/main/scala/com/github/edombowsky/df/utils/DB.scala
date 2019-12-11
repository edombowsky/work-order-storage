package com.github.edombowsky.df.utils

import scala.concurrent.Future
import scala.util.Try

import slick.dbio.DBIO
import slick.jdbc.JdbcProfile

import com.github.edombowsky.df.config.AppSettings


trait DB {

  val databaseUrl = AppSettings.databaseUrl
  val databaseUser = AppSettings.databaseUser
  val databasePassword = AppSettings.databasePassword

  val db: JdbcProfile#Backend#Database = AppSettings.db

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
