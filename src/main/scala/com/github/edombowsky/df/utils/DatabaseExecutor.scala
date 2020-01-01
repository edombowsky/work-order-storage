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

import scala.concurrent.Future

import slick.dbio.DBIO

import com.github.edombowsky.df.config.AppSettings

object DatabaseExecutor {
  private val db = AppSettings.db

  implicit def executeOperation[T](databaseOperation: DBIO[T]): Future[T] = {
    db.run(databaseOperation)
  }
}
