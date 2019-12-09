package com.github.edombowsky.df.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.ZoneOffset
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeUnit.{NANOSECONDS => Nanos}
import java.util.concurrent.TimeUnit.{MILLISECONDS => Millis}
import java.util.Date

import scala.math.ceil

package object Time {

  private val TimestampFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"

  def time[A](unit: TimeUnit = Millis)(thunk: => A): A = {
    val (result, time) = measure(thunk)
    println(s"snippet took: ${unit.convert(time, Nanos)} ${unit.toString.toLowerCase}")

    result
  }

  def benchmark[A](unit: TimeUnit = Millis, times: Int = 100)(thunk: => A) = {

    // warm up.
    (1 to 100) foreach (_ => thunk)

    // measure.
    val results = (1 to times) map (_ => measure(thunk)) map (_._2)
    val info = stats(results)

    // inform.
    println("RESULTS:")
    println("--------")
    println()
    info foreach { case (label, value) =>
      printf("%-4s: %-6s %s %n", label, unit.convert(value, Nanos), unit.toString.toLowerCase)
    }
  }

  private def measure[A](thunk: => A): (A, Long) = {
    val before = System.nanoTime
    val result = thunk
    val delta = (System.nanoTime - before)

    (result, delta)
  }

  private def stats(times: Seq[Long]) = {
    val sortedTimes = times.sorted
    val mean = ("mean", (times.sum / times.size))
    val fifty = ("50%", sortedTimes(ceil(times.size * 0.5).toInt))
    val seventyFive = ("75%", sortedTimes(ceil(times.size * 0.75).toInt))
    val ninety = ("90%", sortedTimes(ceil(times.size * 0.90).toInt))
    val ninetyNine = ("99%", sortedTimes(ceil(times.size * 0.99).toInt))

    mean :: fifty :: seventyFive :: ninety :: ninetyNine :: Nil
  }

  def toISO8601UTC(date: Option[Timestamp]): String = {
    date match {
      case None => "null"
      case Some(d) =>
        val sdf = new SimpleDateFormat(TimestampFormat)
        sdf.format(d)
    }
  }

  /**
   * Convert a [[ZonedDateTime]] to [[Timestamp]]
   *
   * @param dateTime date time to be converted
   *
   * @return timestamp
   */
  def toTimeStamp(dateTime: ZonedDateTime): Timestamp =
    new Timestamp(dateTime.toInstant.toEpochMilli)

  /**
   * Convert a [[Date]] to [[Timestamp]]
   *
   * @param dateTime date time to be converted
   *
   * @return timestamp
   */
  def toTimeStamp(dateTime: java.util.Date): Timestamp =
    new Timestamp(dateTime.toInstant.toEpochMilli)

  def nowUTC: ZonedDateTime =
    ZonedDateTime.ofInstant(new Date().toInstant, ZoneOffset.UTC)
}
