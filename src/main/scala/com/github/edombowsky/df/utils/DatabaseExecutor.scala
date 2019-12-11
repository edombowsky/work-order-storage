package com.github.edombowsky.df.utils

import scala.concurrent.Future

import slick.dbio.DBIO

import com.github.edombowsky.df.config.AppSettings

object DatabaseExecutor {
  private val db = AppSettings.db

  implicit def executeOperation[T](databaseOperation: DBIO[T]): Future[T] = {
    db.run(databaseOperation)
  }
}
