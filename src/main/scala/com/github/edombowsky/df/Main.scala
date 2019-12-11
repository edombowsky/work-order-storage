package com.github.edombowsky.df

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Failure
import scala.util.Success

import io.jvm.uuid._
import io.circe._
import io.circe.parser._

import com.github.edombowsky.df.config.AppSettings
import com.github.edombowsky.df.model.WorkOrder
import com.github.edombowsky.df.repository.WorkOrderRepository
//import com.github.edombowsky.df.repository.WorkOrderRepository
import com.github.edombowsky.df.utils.ExtendedPostgresProfile

//object Main extends MigrationConfig {
object Main {
  //private val Test = "test"
  //private val Dev = "dev"
  //private val Prod = "prod"

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
      solutionAttributes = "{}", //parse(""" { "a":101, "b":"aaa", "c":[3,4,5,9] } """).getOrElse(Json.Null),
      customAttributes = None,
      responsibleOrg = None,
      account = None,
      attachment = None,
      productServiceRequirement = None,
      activity = None
    )
    println(s"WorkOrder to be saved:: $wo")
    val workOrderRepository = new WorkOrderRepository(ExtendedPostgresProfile)
    val workOrder = AppSettings.db.run(workOrderRepository.save(wo))
    //val workOrder: workOrderRepository.driver.api.DBIO[WorkOrder] = workOrderRepository.save(wo)
    //
    //workOrder map { result =>
    //  val id = result.id.getOrElse("NULL")
    //  val desc = result.description
    //  println(s"Inserted $id::$desc")
    //}

    //val w: Future[WorkOrder] = workOrderRepository.db.run(workOrderRepository.save())
    workOrder.onComplete {
      case Success(value) =>
        println("Id of WorkOrder Added : " + value.id.getOrElse("0000"))
        println("SUCCESS")
      case Failure(exception) =>
        exception.printStackTrace()
        //println(s"Encountered an exception::\n${exception.printStackTrace()}")
    }
    Thread.sleep(2000)
  }
}
