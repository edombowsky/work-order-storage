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

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.ZoneOffset
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeUnit.{NANOSECONDS => Nanos}
import java.util.concurrent.TimeUnit.{MILLISECONDS => Millis}
import java.util.Calendar
import java.util.Date

import scala.math.ceil

import com.github.nscala_time.time.Imports._
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter


object Time {

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

  val fmt_yyyyMMddHHmm = DateTimeFormat.forPattern("yyyyMMddHHmm")
  val fmt_yyyyMMddHHmmss = DateTimeFormat.forPattern("yyyyMMddHHmmss")

  def format_yyyyMMddHHmm(millis: Long) = format(millis,fmt_yyyyMMddHHmm)
  def format_yyyyMMddHHmmss(millis: Long) = format(millis,fmt_yyyyMMddHHmmss)
  val fmt_yyyyMMddHH = DateTimeFormat.forPattern("yyyyMMddHH")
  val fmt_yyyyMMdd = DateTimeFormat.forPattern("yyyyMMdd")
  val fmt_yyyyMM = DateTimeFormat.forPattern("yyyyMM")
  val fmt_yyyyWW = DateTimeFormat.forPattern("yyyyww")
  def format_yyyyWW(millis:Long)=format(millis,fmt_yyyyWW)
  def format_yyyyMM(millis:Long)=format(millis,fmt_yyyyMM)
  def format_yyyyMMddHH(millis: Long) = format(millis,fmt_yyyyMMddHH)
  def format_yyyyMMdd(millis: Long) = format(millis,fmt_yyyyMMdd)

  private def format(millis: Long, fmt: DateTimeFormatter):String = {
    val d = new DateTime(millis)
    d.toString(fmt)
  }

  def formatMillisWithInterval(millis:Long,interval:String)={
    interval match {
      case "yyyyMMddHH24MI" => format_yyyyMMddHHmm(millis)
      case "yyyyMMddHH5M" => getLatest5Minute(format_yyyyMMddHHmm(millis))
      case "yyyyMMddHH24" => format_yyyyMMddHH(millis)
      case "yyyyMMdd" => format_yyyyMMdd(millis)
      case "yyyyww"=> format_yyyyWW(millis)
      case "yyyyMM" => format_yyyyMM(millis)
    }
  }

  def getCurHourEnd(date:Date) = {
    val c = Calendar.getInstance()
    c.setTime(date)
    c.clear(Calendar.MINUTE)
    c.clear(Calendar.SECOND)
    c.clear(Calendar.MILLISECOND)
    c.add(Calendar.HOUR,1)
    c.add(Calendar.SECOND,0)
    c.add(Calendar.MILLISECOND,0)
    c.getTimeInMillis - 1
  }

  def getCurHourStart(date:Date) = {
    val c = Calendar.getInstance()
    c.setTime(date)
    c.clear(Calendar.MINUTE)
    c.clear(Calendar.SECOND)
    c.clear(Calendar.MILLISECOND)
    c.add(Calendar.MINUTE,0)
    c.add(Calendar.SECOND,0)
    c.add(Calendar.MILLISECOND,0)
    c.getTimeInMillis
  }
  def getCurMinuteEnd(date:Date) = {
    val c = Calendar.getInstance()
    c.setTime(date)
    c.clear(Calendar.SECOND)
    c.clear(Calendar.MILLISECOND)
    c.add(Calendar.MINUTE,1)
    c.add(Calendar.SECOND,0)
    c.add(Calendar.MILLISECOND,0)
    c.getTimeInMillis - 1
  }

  def getCurMinuteStart(date:Date) = {
    val c = Calendar.getInstance()
    c.setTime(date)
    c.clear(Calendar.SECOND)
    c.clear(Calendar.MILLISECOND)
    c.add(Calendar.MINUTE,0)
    c.add(Calendar.SECOND,0)
    c.add(Calendar.MILLISECOND,0)
    c.getTimeInMillis
  }

  def getCurFiveEnd(date:Date) = {
    val c = Calendar.getInstance()
    c.setTime(date)
    val tmp = c.get(Calendar.MINUTE)
    val sec = c.get(Calendar.SECOND)
    val mil = c.get(Calendar.MILLISECOND)
    val x = if(tmp%5 > 0) 1 else if(sec>0 || mil>0) 1 else 0
    val t = (tmp/5 + x)*5
    c.clear(Calendar.SECOND)
    c.clear(Calendar.MILLISECOND)
    c.set(Calendar.MINUTE,0)
    c.add(Calendar.MINUTE,t)
    c.add(Calendar.SECOND,0)
    c.add(Calendar.MILLISECOND,0)
    c.getTimeInMillis - 1
  }

  def getCurFiveStart(date:Date) = {
    val c = Calendar.getInstance()
    c.setTime(date)
    val tmp = c.get(Calendar.MINUTE)
    val sec = c.get(Calendar.SECOND)
    val mil = c.get(Calendar.MILLISECOND)
    val t = (tmp/5)*5
    c.clear(Calendar.SECOND)
    c.clear(Calendar.MILLISECOND)
    c.set(Calendar.MINUTE,0)
    c.add(Calendar.MINUTE,t)
    c.add(Calendar.SECOND,0)
    c.add(Calendar.MILLISECOND,0)
    c.getTimeInMillis
  }

  def getCurWeekEnd(date:Date) = {
    val c = Calendar.getInstance()
    c.setTime(date)
    c.setFirstDayOfWeek(Calendar.MONDAY)
    c.add(Calendar.WEEK_OF_YEAR,1)
    c.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY)
    c.set(Calendar.HOUR_OF_DAY,0)
    c.set(Calendar.MINUTE,0)
    c.set(Calendar.SECOND,0)
    c.set(Calendar.MILLISECOND,0)

    c.add(Calendar.SECOND,0)

    c.getTimeInMillis - 1
  }

  def getCurWeekStart(date:Date) = {
    val c = Calendar.getInstance()
    c.setTime(date)
    c.setFirstDayOfWeek(Calendar.MONDAY)
    c.add(Calendar.WEEK_OF_YEAR,0)
    c.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY)
    c.set(Calendar.HOUR_OF_DAY,0)
    c.set(Calendar.MINUTE,0)
    c.set(Calendar.SECOND,0)
    c.set(Calendar.MILLISECOND,0)

    c.add(Calendar.SECOND,0)

    c.getTimeInMillis
  }

  def getCurMonthEnd(date:Date) = {
    val c = Calendar.getInstance()
    c.setTime(date)
    c.add(Calendar.MONTH,1)
    c.set(Calendar.DAY_OF_MONTH,1)
    c.set(Calendar.HOUR_OF_DAY,0)
    c.set(Calendar.MINUTE,0)
    c.set(Calendar.SECOND,0)
    c.set(Calendar.MILLISECOND,0)
    c.add(Calendar.SECOND,0)
    c.getTimeInMillis - 1
  }

  def getCurMonthStart(date:Date) = {
    val c = Calendar.getInstance()
    c.setTime(date)
    c.add(Calendar.MONTH,0)
    c.set(Calendar.DAY_OF_MONTH,1)
    c.set(Calendar.HOUR_OF_DAY,0)
    c.set(Calendar.MINUTE,0)
    c.set(Calendar.SECOND,0)
    c.set(Calendar.MILLISECOND,0)
    c.add(Calendar.SECOND,0)
    c.getTimeInMillis
  }

  def getCurDayEnd(date:Date) = {
    val c = Calendar.getInstance()
    c.setTime(date)
    c.add(Calendar.DAY_OF_YEAR,1)
    c.set(Calendar.HOUR_OF_DAY,0)
    c.clear(Calendar.MINUTE)
    c.clear(Calendar.SECOND)
    c.clear(Calendar.MILLISECOND)
    c.add(Calendar.HOUR,0)
    c.add(Calendar.MINUTE,0)
    c.add(Calendar.SECOND,0)
    c.add(Calendar.MILLISECOND,0)
    c.getTimeInMillis - 1
  }

  def getCurDayStart(date:Date) = {
    val c = Calendar.getInstance()
    c.setTime(date)
    c.add(Calendar.DAY_OF_YEAR,0)
    c.set(Calendar.HOUR_OF_DAY,0)
    c.clear(Calendar.MINUTE)
    c.clear(Calendar.SECOND)
    c.clear(Calendar.MILLISECOND)
    c.add(Calendar.HOUR,0)
    c.add(Calendar.MINUTE,0)
    c.add(Calendar.SECOND,0)
    c.add(Calendar.MILLISECOND,0)
    c.getTimeInMillis
  }

  def getMonthStart(start:Long) = {
    val c = Calendar.getInstance()
    c.setTime(new Date(start))
    c.set(Calendar.DAY_OF_MONTH, 2);
    c.set(Calendar.HOUR_OF_DAY, 0);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND,0);
    c.set(Calendar.MILLISECOND, 0);
    c.add(Calendar.SECOND,0)
    c.getTimeInMillis
  }

  def getWeekStart(start:Long) = {
    val c = Calendar.getInstance()
    c.setTime(new Date(start))
    c.setFirstDayOfWeek(Calendar.MONDAY)
    c.set(Calendar.DAY_OF_WEEK,Calendar.TUESDAY)
    c.set(Calendar.HOUR_OF_DAY, 0);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND,0);
    c.set(Calendar.MILLISECOND, 0);
    c.add(Calendar.SECOND,0)
    c.getTimeInMillis
  }

  def getCurMinute(date:String) = {
    fmt_yyyyMMddHHmm.parseDateTime(date).withSecond(0).getMillis
  }

  def getCurHour(date:String) = {
    fmt_yyyyMMddHH.parseDateTime(date).withMinute(0).withSecond(0).getMillis
  }

  def getCurDate(date:String) = {
    fmt_yyyyMMdd.parseDateTime(date).withHour(0).withMinute(0).withSecond(0).getMillis
  }

  def getCurWeek(date:String) = {
    fmt_yyyyWW.parseDateTime(date).withHour(0).withMinute(0).withSecond(0).getMillis
  }

  def getCurMonth(date:String) = {
    fmt_yyyyMM.parseDateTime(date).withDay(1).withHour(0).withMinute(0).withSecond(0).getMillis
  }

  def plusDaysOfEnd(date:String,day:Int) = {
    fmt_yyyyMMdd.parseDateTime(date).plusDays(day).withHour(23).withMinute(59).withSecond(59).getMillis + 999
  }

  def plusHoursOfEnd(date:String,hour:Int) = {
    fmt_yyyyMMddHH.parseDateTime(date).plusDays(hour).withMinute(59).withSecond(59).getMillis + 999
  }

  def plusMinutesOfEnd(date:String,minute:Int) = {
    fmt_yyyyMMddHHmm.parseDateTime(date).plusDays(minute).withSecond(59).getMillis + 999
  }

  def sinaDate2TimeStamp(date: String): Long = {
    new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(date).getTime
  }

  def ntesDate2TimeStamp(date: String): Long = {
    new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(date).getTime
  }

  def date2TimeStampMMddHHss(date:String):Long = {
    try{
      val d = date.split(" ")(0)
      val t = date.split(" ")(1)
      new DateTime().withMonthOfYear(d.split("-")(0).toInt).withDayOfMonth(d.split("-")(1).toInt)
        .withHourOfDay(t.split(":")(0).toInt).withMinuteOfHour(t.split(":")(1).toInt)
        .withSecondOfMinute(0).withMillisOfSecond(0).getMillis
    }catch {
      case e:Exception =>
        throw e
    }

  }

  def dateMMddYY2TimeStamp(date:String):Long = {
    new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(date).getTime

  }

  def todayBegin():Long ={
    new DateTime().withTimeAtStartOfDay().getMillis
  }

  def minusMonth(m:Int):Long ={
    new DateTime().minusMonths(m).withTimeAtStartOfDay().getMillis
  }

  def minusHours(m:Int):Long ={
    new DateTime().minusHours(m).getMillis
  }

  def TimeStamp2Date(timeStamp:Long) = {
    val sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // This is the time format you want to convert to
    sdf.format(new Date(timeStamp)) // Timestamp into time
  }

  def getLatest5Minute(date:String) :String = {
    var result = ""
    for(i <- 0 to 55 by 5){
      if(date.takeRight(2).toInt >= i && date.takeRight(2).toInt < i+5){
        result = if(i < 10) "0" + i.toString else i.toString
      }
    }
    date.dropRight(2) + result
  }
}
