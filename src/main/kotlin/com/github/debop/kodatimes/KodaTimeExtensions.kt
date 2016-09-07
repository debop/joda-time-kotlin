/*
 * Copyright (c) 2016. Sunghyouk Bae <sunghyouk.bae@gmail.com>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
@file:JvmName("KodaTimeExtensions")

package com.github.debop.kodatimes

import org.joda.time.*
import org.joda.time.base.AbstractInstant
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.ISODateTimeFormat
import java.sql.Timestamp
import java.util.*

val EPOCH = DateTime(0)

/** Convert [Date] to [DateTime] */
fun Date.toDateTime(): DateTime = DateTime(this.time)

/** Convert [Date] to [LocalDateTime] */
fun Date.toLocalDateTime(): LocalDateTime = LocalDateTime.fromDateFields(this)

/** Convert [Date] to [LocalDate] */
fun Date.toLocalDate(): LocalDate = LocalDate.fromDateFields(this)

/** Convert [Date] to [LocalTime] */
fun Date.toLocalTime(): LocalTime = LocalTime.fromDateFields(this)

/** Convert [Instant] to [DateTime] with TimeZone UTC */
fun AbstractInstant.dateTimeUTC(): DateTime = this.toDateTime(DateTimeZone.UTC)

/** Convert [Instant] to [MutableDateTime] with TimeZone UTC */
fun AbstractInstant.mutableDateTimeUTC(): MutableDateTime = this.toMutableDateTime(DateTimeZone.UTC)

/** Millsecond */
fun Int.millis(): DurationBuilder = DurationBuilder(Period.millis(this))

/** Seconds */
fun Int.seconds(): DurationBuilder = DurationBuilder(Period.seconds(this))

/** N Minutes */
fun Int.minutes(): DurationBuilder = DurationBuilder(Period.minutes(this))

/** N Hours */
fun Int.hours(): DurationBuilder = DurationBuilder(Period.hours(this))

/** Period in N days */
fun Int.days(): Period = Period.days(this)

/** Period in N weeks */
fun Int.weeks(): Period = Period.weeks(this)

/** Period in N months */
fun Int.months(): Period = Period.months(this)

/** Period in N years */
fun Int.years(): Period = Period.years(this)

/**  N times duration */
operator fun Int.times(builder: DurationBuilder): DurationBuilder = DurationBuilder(builder.period.multipliedBy(this))

/**  N times period */
operator fun Int.times(period: Period): Period = period.multipliedBy(this)


/** N millis duration */
fun Long.millis(): DurationBuilder = DurationBuilder(Period.millis(this.toInt()))

/** N seconds duration */
fun Long.seconds(): DurationBuilder = DurationBuilder(Period.seconds(this.toInt()))

/** N minutes duration */
fun Long.minutes(): DurationBuilder = DurationBuilder(Period.minutes(this.toInt()))

/** N hours duration */
fun Long.hours(): DurationBuilder = DurationBuilder(Period.hours(this.toInt()))

/** N days period */
fun Long.days(): Period = Period.days(this.toInt())

/** N weeks period */
fun Long.weeks(): Period = Period.weeks(this.toInt())

/** N months period */
fun Long.months(): Period = Period.months(this.toInt())

/** N years period */
fun Long.years(): Period = Period.years(this.toInt())

/**  N times duration */
operator fun Long.times(builder: DurationBuilder): DurationBuilder = DurationBuilder(builder.period.multipliedBy(this.toInt()))

/**  N times period  */
operator fun Long.times(period: Period): Period = period.multipliedBy(this.toInt())

/**
 * get [DateTimeFormatter] with specified pattern
 */
fun dateTimeFormat(pattern: String) = DateTimeFormat.forPattern(pattern)

/** Parse string to [DateTime] */
fun String.toDateTime(pattern: String? = null): DateTime? = try {
  if (pattern.isNullOrBlank()) DateTime(this)
  else DateTime.parse(this, dateTimeFormat(pattern!!))
} catch(ignored: Throwable) {
  null
}

/** Parse string to [Interval] */
fun String.toInterval(): Interval? = try {
  Interval.parse(this)
} catch(ignored: Throwable) {
  null
}

/** Parse string to [LocalDate] */
fun String.toLocalDate(pattern: String? = null): LocalDate? = try {
  if (pattern.isNullOrBlank()) LocalDate(this)
  else LocalDate.parse(this, dateTimeFormat(pattern!!))
} catch(ignored: Throwable) {
  null
}

/** Parse string to [LocalTime] */
fun String.toLocalTime(pattern: String? = null): LocalTime? = try {
  if (pattern.isNullOrBlank())
    LocalTime(this)
  else LocalTime.parse(this, dateTimeFormat(pattern!!))
} catch(ignored: Throwable) {
  null
}

/** Convert json text to [DateTime] */
fun dateTimeFromJson(json: String): DateTime = DateTime(json)

/** Build [DateTime] */
@JvmOverloads fun dateTimeOf(year: Int,
                             month: Int = 1,
                             day: Int = 1,
                             hours: Int = 0,
                             minutes: Int = 0,
                             seconds: Int = 0,
                             millis: Int = 0): DateTime =
    DateTime(year, month, day, hours, minutes, seconds, millis)

/** Start time of Day from this datetime */
fun DateTime.startOfDay(): DateTime = this.withTimeAtStartOfDay()

fun DateTime.startOfWeek(): DateTime = this.minusDays(this.dayOfWeek - DateTimeConstants.MONDAY).startOfDay()

/** Start time of Month from this datetime */
fun DateTime.startOfMonth(): DateTime = dateTimeOf(this.year, this.monthOfYear)

/** Start time of Year from this datetime */
fun DateTime.startOfYear(): DateTime = dateTimeOf(this.year)

/** DateTime `-` operator */
operator fun DateTime.minus(millis: Long): DateTime = this.minus(millis)

operator fun DateTime.minus(duration: ReadableDuration): DateTime = this.minus(duration)
operator fun DateTime.minus(period: ReadablePeriod): DateTime = this.minus(period)
operator fun DateTime.minus(builder: DurationBuilder): DateTime = this.minus(builder.period)


/** DateTime `+` operator */
operator fun DateTime.plus(millis: Long): DateTime = this.plus(millis)

operator fun DateTime.plus(duration: ReadableDuration): DateTime = this.plus(duration)
operator fun DateTime.plus(period: ReadablePeriod): DateTime = this.plus(period)
operator fun DateTime.plus(builder: DurationBuilder): DateTime = this.plus(builder.period)

/** next day */
fun DateTime.tomorrow(): DateTime = this.nextDay()

/** last day */
fun DateTime.yesterday(): DateTime = this.lastDay()

fun DateTime.nextSecond(): DateTime = this.plusSeconds(1)
fun DateTime.nextMinute(): DateTime = this.plusMinutes(1)
fun DateTime.nextHour(): DateTime = this.plusHours(1)
fun DateTime.nextDay(): DateTime = this.plusDays(1)
fun DateTime.nextWeek(): DateTime = this.plusWeeks(1)
fun DateTime.nextMonth(): DateTime = this.plusMonths(1)
fun DateTime.nextYear(): DateTime = this.plusYears(1)

fun DateTime.lastSecond(): DateTime = this.minusSeconds(1)
fun DateTime.lastMinute(): DateTime = this.minusMinutes(1)
fun DateTime.lastHour(): DateTime = this.minusHours(1)
fun DateTime.lastDay(): DateTime = this.minusDays(1)
fun DateTime.lastWeek(): DateTime = this.minusWeeks(1)
fun DateTime.lastMonth(): DateTime = this.minusMonths(1)
fun DateTime.lastYear(): DateTime = this.minusYears(1)

/** Convert [DateTime] to [Timestamp] */
fun DateTime.toTimestamp(): Timestamp = Timestamp(this.millis)

/** Convert [DateTime] timezone to UTC */
fun DateTime.asUtc(): DateTime = this.toDateTime(DateTimeZone.UTC)

/** Convert [DateTime] timezone to specified time zone. not specified to system default time zone */
@JvmOverloads fun DateTime.asLocal(tz: DateTimeZone = DateTimeZone.getDefault()): DateTime = this.toDateTime(tz)

/** Convert [DateTime] to ISO DateTime Format String (YYYY-MM-DD hh:nn:ss.zzz) */
fun DateTime.toIsoFormatString(): String = ISODateTimeFormat.dateTime().print(this)

/** Convert [DateTime] to ISO Date Format String (YYYY-MM-DD) */
fun DateTime.toIsoFormatDateString(): String = ISODateTimeFormat.date().print(this)

/** Convert [DateTime] to ISO Time Format String (hh:nn:ss.zzz) */
fun DateTime.toIsoFormatTimeString(): String = ISODateTimeFormat.time().print(this)

/** Convert [DateTime] to ISO Time Format String without millis (hh:nn:ss) */
fun DateTime.toIsoFormatTimeNoMillisString(): String = ISODateTimeFormat.timeNoMillis().print(this)

/** Convert [DateTime] to ISO DateTime Format String without millis (YYYY-MM-DD hh:nn:ss) */
fun DateTime.toIsoFormatHMSString(): String = ISODateTimeFormat.dateHourMinuteSecond().print(this)

fun DateTime.toTimestampZoneText(): TimestampZoneText = TimestampZoneText(this)

/** get minimum [DateTime] */
infix fun DateTime.min(that: DateTime): DateTime {
  return if (this.compareTo(that) < 0) this else that
}

/** get maximum [DateTime] */
infix fun DateTime.max(that: DateTime): DateTime {
  return if (this.compareTo(that) > 0) this else that
}

/** Get month interval in specified [DateTime] */
fun DateTime.monthInterval(): Interval {
  val start = this.withDayOfMonth(1).withTimeAtStartOfDay()
  return Interval(start, start + 1.months())
}

/** Get day interval in specified [DateTime] */
fun DateTime.dayInterval(): Interval {
  val start = this.startOfDay()
  return Interval(start, start + 1.days())
}

/** current [DateTime] */
fun now(): DateTime = DateTime.now()

/** next day of current [DateTime] */
fun tomorrow(): DateTime = now().tomorrow()

/** last day of current [DateTime] */
fun yesterday(): DateTime = now().yesterday()

fun nextSecond(): DateTime = now().plusSeconds(1)
fun nextMinute(): DateTime = now().plusMinutes(1)
fun nextHour(): DateTime = now().plusHours(1)
fun nextDay(): DateTime = now().plusDays(1)
fun nextWeek(): DateTime = now().plusWeeks(1)
fun nextMonth(): DateTime = now().plusMonths(1)
fun nextYear(): DateTime = now().plusYears(1)

fun lastSecond(): DateTime = now().minusSeconds(1)
fun lastMinute(): DateTime = now().minusMinutes(1)
fun lastHour(): DateTime = now().minusHours(1)
fun lastDay(): DateTime = now().minusDays(1)
fun lastWeek(): DateTime = now().minusWeeks(1)
fun lastMonth(): DateTime = now().minusMonths(1)
fun lastYear(): DateTime = now().minusYears(1)

/** `-` operator for [LocalDateTime] */
operator fun LocalDateTime.minus(builder: DurationBuilder): LocalDateTime = this.minus(builder.period)

/** `+` operator for [LocalDateTime] */
operator fun LocalDateTime.plus(builder: DurationBuilder): LocalDateTime = this.plus(builder.period)

/** `-` operator for [LocalDate] */
operator fun LocalDate.minus(builder: DurationBuilder): LocalDate = this.minus(builder.period)

/** `+` operator for [LocalDate] */
operator fun LocalDate.plus(builder: DurationBuilder): LocalDate = this.plus(builder.period)

/** `-` operator for [LocalTime] */
operator fun LocalTime.minus(builder: DurationBuilder): LocalTime = this.minus(builder.period)

/** `+` operator for [LocalTime] */
operator fun LocalTime.plus(builder: DurationBuilder): LocalTime = this.plus(builder.period)


/**
 * empty [Duration]
 */
val emptyDuration: Duration = Duration.ZERO

/** specified days duration */
fun standardDays(days: Long): Duration = Duration.standardDays(days)

/** specified hours duration */
fun standardHours(hours: Long): Duration = Duration.standardHours(hours)

/** specified minutes duration */
fun standardMinutes(minutes: Long): Duration = Duration.standardMinutes(minutes)

/** specified seconds duration */
fun standardSeconds(seconds: Long): Duration = Duration.standardSeconds(seconds)

fun Duration.days(): Long = this.standardDays
fun Duration.hours(): Long = this.standardHours
fun Duration.minutes(): Long = this.standardMinutes
fun Duration.seconds(): Long = this.standardSeconds

/** absolute duration */
fun Duration.abs(): Duration = if (this < emptyDuration) -this else this

/** get [DateTime] from current time + duration */
fun Duration.fromNow(): DateTime = now() + this

/** get [DateTime] from current time - duration */
fun Duration.agoNow(): DateTime = now() - this

/** get [DateTime] from EPOCH + duration */
fun Duration.afterEpoch(): DateTime = EPOCH + this

/** difference duration with other duration  */
fun Duration.diff(other: Duration): Duration = this - other

operator fun Duration.unaryMinus(): Duration = this.negated()
operator fun Duration.div(divisor: Long): Duration = this.dividedBy(divisor)
operator fun Duration.times(multiplicand: Long): Duration = this.multipliedBy(multiplicand)

fun Duration.isZero(): Boolean = this.millis == 0L

infix fun Duration.min(that: Duration): Duration {
  return if (this.compareTo(that) < 0) this else that
}

infix fun Duration.max(that: Duration): Duration {
  return if (this.compareTo(that) > 0) this else that
}


/** current time - specified period */
fun Period.ago(): DateTime = DateTime.now() - this

fun Period.later(): DateTime = DateTime.now() + this
fun Period.from(moment: DateTime): DateTime = moment + this
fun Period.before(moment: DateTime): DateTime = moment - this
fun Period.standardDuration(): Duration = this.toStandardDuration()

fun periodOfYears(y: Int): Period = Period.years(y)
fun periodOfMonths(m: Int): Period = Period.months(m)
fun periodOfWeek(w: Int): Period = Period.weeks(w)
fun periodOfDay(d: Int): Period = Period.days(d)
fun periodOfHours(h: Int): Period = Period.hours(h)
fun periodOfMinutes(m: Int): Period = Period.minutes(m)
fun periodOfSeconds(s: Int): Period = Period.seconds(s)
fun periodOfMillis(m: Int): Period = Period.millis(m)

operator fun Period.minus(period: ReadablePeriod): Period = this.minus(period)
operator fun Period.minus(builder: DurationBuilder): Period = this.minus(builder.period)
operator fun Period.plus(period: ReadablePeriod): Period = this.plus(period)
operator fun Period.plus(builder: DurationBuilder): Period = this.plus(builder.period)

operator fun Period.times(scalar: Int): Period = this.multipliedBy(scalar)

operator fun Instant.minus(millis: Long): Instant = this.minus(millis)
operator fun Instant.minus(duration: ReadableDuration): Instant = this.minus(duration)
operator fun Instant.minus(builder: DurationBuilder): Instant = this.minus(builder.period.toStandardDuration())

operator fun Instant.plus(millis: Long): Instant = this.plus(millis)
operator fun Instant.plus(duration: ReadableDuration): Instant = this.plus(duration)
operator fun Instant.plus(builder: DurationBuilder): Instant = this.plus(builder.period.toStandardDuration())

fun thisSecond(): Interval = now().secondOfMinute().toInterval()
fun thisMinute(): Interval = now().minuteOfHour().toInterval()
fun thisHour(): Interval = now().hourOfDay().toInterval()

//
// [ReadableInstant] .. [ReadableInstant] => [Interval]
//
/** get [Interval] from specified instant ~ other instant */
operator fun ReadableInstant.rangeTo(other: ReadableInstant): Interval = Interval(this, other)

fun ReadableInterval.millis(): Long = this.toDurationMillis()

infix fun ReadableInterval.step(instance: ReadablePeriod): Sequence<DateTime> {
  return generateSequence(start + instance) { it + instance }.takeWhile { it <= end }
}

/** 기간을 초 단위로 열거합니다. */
fun ReadableInterval.seconds(): Sequence<DateTime> {
  return generateSequence(start) { it.plusSeconds(1) }.takeWhile { it <= end }
}

/** 기간을 분 단위로 열거합니다. */
fun ReadableInterval.minutes(): Sequence<DateTime> {
  return generateSequence(start) { it.plusMinutes(1) }.takeWhile { it <= end }
}

/** 기간을 시간 단위로 열거합니다. */
fun ReadableInterval.hours(): Sequence<DateTime> {
  return generateSequence(start) { it.plusHours(1) }.takeWhile { it <= end }
}

/** 기간을 일 단위로 열거합니다. */
fun ReadableInterval.days(): Sequence<DateTime> {
  return generateSequence(start.startOfDay()) { it.plusDays(1) }.takeWhile { it <= end }
}

/** 기간을 주 단위로 열거합니다. */
fun ReadableInterval.weeks(): Sequence<DateTime> {
  return generateSequence(start.startOfWeek()) { it.plusWeeks(1) }.takeWhile { it <= end }
}

/** 기간을 월 단위로 열거합니다. */
fun ReadableInterval.months(): Sequence<DateTime> {
  return generateSequence(start.startOfMonth()) { it.plusMonths(1) }.takeWhile { it <= end }
}