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

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Failure
import scala.util.Success

import io.circe._
import io.circe.parser._
import io.jvm.uuid._

import com.github.edombowsky.df.model.WorkOrder
import com.github.edombowsky.df.repository.WorkOrderRepository
import com.github.edombowsky.df.utils.DatabaseExecutor._
import com.github.edombowsky.df.utils.ExtendedPostgresProfile


object Main {

  //migrate()

  def main(args: Array[String]): Unit = {
    println("Hello, world")

    System.getenv.forEach((name, value) => println(s"$name: $value"))

    val wo = WorkOrder(
      id = Some(UUID.random.string),
      parentWorkOrder = None,
      description = "description",
      status = "status",
      priority = None,
      workType = None,
      asset = None,
      assignedTo = None,
      requiredByDateTime = None,
      extendedDescription = None,
      lastModifiedTimestamp = None,
      lastModifiedBy = None,
      createdTimestamp = None,
      createdBy = None,
      workOrderId = None,
      workOrderTemplate = None,
      userStatus = None,
      assetWork = None,
      raisedDateTime = None,
      raisedBy = None,
      plannedStartDateTime = None,
      plannedFinishDateTime = None,
      actualStartDateTime = None,
      actualFinishDateTime = None,
      closedDateTime = None,
      plannedDuration = None,
      actualDuration = None,
      completedBy = None,
      completionComments = None,
      location = None,
      assetPosition = None,
      solutionAttributes = Some(parse(""" { "a":101, "b":"aaa", "c":[3,4,5,9] } """).getOrElse(Json.Null)),
      customAttributes = None,
      responsibleOrg = None,
      account = None,
      attachment = None,
      productServiceRequirement = None,
      activity = None
    )
    println(s"WorkOrder to be saved:: $wo")

    val workOrderRepository = new WorkOrderRepository(ExtendedPostgresProfile)
    val workOrder = workOrderRepository.save(wo)

    workOrder.onComplete {
      case Success(value) =>
        println("Got called back and Id of WorkOrder Added : " + value.id.getOrElse("0000"))
      case Failure(exception) =>
        exception.printStackTrace()
        //println(s"Encountered an exception::\n${exception.printStackTrace()}")
    }
    Thread.sleep(2000)


    workOrderRepository.count().onComplete {
      case Success(value) =>
        println(s"Got called back and total Number of work_orders is $value")
      case Failure(exception) =>
        exception.printStackTrace()
    }
    Thread.sleep(2000)
  }
}
