package com.github.debop.javatimes

import mu.KLogging
import org.amshove.kluent.shouldBeLessOrEqualTo
import org.amshove.kluent.shouldBeTrue
import org.amshove.kluent.shouldEqualTo
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.ZonedDateTime

class TemporalIntervalChunkTest : AbstractJavaTimesTest() {

    companion object : KLogging()

    @Test
    fun `chunk interval in year`() {
        val start = nowZonedDateTime().startOfYear()
        val endExclusive = start + 5.yearPeriod()
        val interval = start..endExclusive

        val chunks = interval.chunkYear(4).toList()

        chunks.forEachIndexed { index, chunk ->
            logger.debug { "chunks[$index] = $chunk" }
            assertTrue(chunk.size <= 4)
            assertTrue(chunk.first() in interval)
            assertTrue(chunk.last() in interval)
        }

        chunks.size shouldEqualTo 2
        chunks[0].size shouldEqualTo 4
        chunks[1].size shouldEqualTo 1

        assertThrows<IllegalArgumentException> {
            interval.chunkYear(0)
        }
        assertThrows<IllegalArgumentException> {
            interval.chunkYear(-1)
        }
    }

    @Test
    fun `chunk year and aggregate`() {
        val start = nowZonedDateTime().startOfYear()
        val endExclusive = start + 5.yearPeriod()
        val interval = start..endExclusive

        val chunks = interval.chunkYear(3)
            .map { years -> years.first()..years.last() }
            .toList()

        chunks.size shouldEqualTo 2
        chunks.all { it in interval }.shouldBeTrue()
    }

    @Test
    fun `chunk interval in month`() {
        val start = nowZonedDateTime().startOfMonth()
        val endExclusive = start + 5.monthPeriod()
        val interval = start..endExclusive

        val chunks = interval.chunkMonth(4).toList()

        chunks.forEachIndexed { index, chunk ->
            logger.debug { "chunks[$index] = $chunk" }
            chunk.size shouldBeLessOrEqualTo 4
            assertTrue { chunk.first() in interval }
            assertTrue { chunk.last() in interval }
        }

        chunks.size shouldEqualTo 2
        chunks[0].size shouldEqualTo 4
        chunks[1].size shouldEqualTo 1

        assertThrows<IllegalArgumentException> {
            interval.chunkMonth(0)
        }
        assertThrows<IllegalArgumentException> {
            interval.chunkMonth(-1)
        }
    }

    @Test
    fun `chunk month and aggregate`() {
        val start = nowZonedDateTime().startOfMonth()
        val endExclusive = start + 5.monthPeriod()

        val interval = start..endExclusive

        val chunks = interval.chunkMonth(3)
            .map { months -> months.first()..months.last() }
            .toList()

        chunks.size shouldEqualTo 2
        chunks.all { it in interval }.shouldBeTrue()
    }

    @Test
    fun `chunk interval in week`() {
        val start = nowZonedDateTime().startOfWeek()
        val endExclusive = start + 5.weekPeriod()
        val interval = start..endExclusive

        val chunks = interval.chunkWeek(4).toList()

        chunks.forEachIndexed { index, chunk ->
            logger.debug { "chunks[$index] = $chunk" }
            chunk.size shouldBeLessOrEqualTo 4
            assertTrue { chunk.first() in interval }
            assertTrue { chunk.last() in interval }
        }

        chunks.size shouldEqualTo 2
        chunks[0].size shouldEqualTo 4
        chunks[1].size shouldEqualTo 1

        assertThrows<IllegalArgumentException> {
            interval.chunkWeek(0)
        }
        assertThrows<IllegalArgumentException> {
            interval.chunkWeek(-1)
        }
    }

    @Test
    fun `chunk week and aggregate`() {
        val start: ZonedDateTime = nowZonedDateTime().startOfWeek()
        val endExclusive: ZonedDateTime = start + 5.weekPeriod()

        val interval: ReadableTemporalInterval = start..endExclusive

        val chunks: List<ReadableTemporalInterval> = interval.chunkWeek(3)
            .map { weeks -> weeks.first()..weeks.last() }
            .toList()

        chunks.size shouldEqualTo 2
        chunks.all { it in interval }.shouldBeTrue()
    }

    @Test
    fun `chunk interval in day`() {
        val start = nowZonedDateTime().startOfDay()
        val endExclusive = start + 5.days()
        val interval = start..endExclusive

        val chunks = interval.chunkDay(4).toList()

        chunks.forEachIndexed { index, chunk ->
            logger.debug { "chunks[$index] = $chunk" }
            chunk.size shouldBeLessOrEqualTo 4
            assertTrue { chunk.first() in interval }
            assertTrue { chunk.last() in interval }
        }

        chunks.size shouldEqualTo 2
        chunks[0].size shouldEqualTo 4
        chunks[1].size shouldEqualTo 1

        assertThrows<IllegalArgumentException> {
            interval.chunkDay(0)
        }
        assertThrows<IllegalArgumentException> {
            interval.chunkDay(-1)
        }
    }

    @Test
    fun `chunk day and aggregate`() {
        val start = nowZonedDateTime().startOfDay()
        val endExclusive = start + 5.days()

        val interval = start..endExclusive

        val chunks = interval.chunkDay(3)
            .map { days -> days.first()..days.last() }
            .toList()

        chunks.size shouldEqualTo 2
        chunks.all { it in interval }.shouldBeTrue()
    }

    @Test
    fun `chunk interval in hour`() {
        val start = nowZonedDateTime().startOfHour()
        val endExclusive = start + 5.hours()
        val interval = start..endExclusive

        val chunks = interval.chunkHour(4).toList()

        chunks.forEachIndexed { index, chunk ->
            logger.debug { "chunks[$index] = $chunk" }
            chunk.size shouldBeLessOrEqualTo 4
            assertTrue { chunk.first() in interval }
            assertTrue { chunk.last() in interval }
        }

        chunks.size shouldEqualTo 2
        chunks[0].size shouldEqualTo 4
        chunks[1].size shouldEqualTo 1

        assertThrows<IllegalArgumentException> {
            interval.chunkHour(0)
        }
        assertThrows<IllegalArgumentException> {
            interval.chunkHour(-1)
        }
    }

    @Test
    fun `chunk hour and aggregate`() {
        val start = nowZonedDateTime().startOfHour()
        val endExclusive = start + 5.hours()

        val interval = start..endExclusive

        val chunks = interval.chunkHour(3)
            .map { hours -> hours.first()..hours.last() }
            .toList()

        chunks.size shouldEqualTo 2
        chunks.all { it in interval }.shouldBeTrue()
    }

    @Test
    fun `chunk interval in minute`() {
        val start = nowZonedDateTime().startOfMinute()
        val endExclusive = start + 5.minutes()
        val interval = start..endExclusive

        val chunks = interval.chunkMinute(4).toList()

        chunks.forEachIndexed { index, chunk ->
            logger.debug { "chunks[$index] = $chunk" }
            chunk.size shouldBeLessOrEqualTo 4
            assertTrue { chunk.first() in interval }
            assertTrue { chunk.last() in interval }
        }

        chunks.size shouldEqualTo 2
        chunks[0].size shouldEqualTo 4
        chunks[1].size shouldEqualTo 1

        assertThrows<IllegalArgumentException> {
            interval.chunkMinute(0)
        }
        assertThrows<IllegalArgumentException> {
            interval.chunkMinute(-1)
        }
    }

    @Test
    fun `chunk minute and aggregate`() {
        val start = nowZonedDateTime().startOfMinute()
        val endExclusive = start + 5.minutes()

        val interval = start..endExclusive

        val chunks = interval.chunkMinute(3)
            .map { minutes -> minutes.first()..minutes.last() }
            .toList()

        chunks.size shouldEqualTo 2
        chunks.all { it in interval }.shouldBeTrue()
    }

    @Test
    fun `chunk interval in second`() {
        val start = nowZonedDateTime().startOfSecond()
        val endExclusive = start + 5.seconds()
        val interval = start..endExclusive

        val chunks = interval.chunkSecond(4).toList()

        chunks.forEachIndexed { index, chunk ->
            logger.debug { "chunks[$index] = $chunk" }
            chunk.size shouldBeLessOrEqualTo 4
            assertTrue { chunk.first() in interval }
            assertTrue { chunk.last() in interval }
        }

        chunks.size shouldEqualTo 2
        chunks[0].size shouldEqualTo 4
        chunks[1].size shouldEqualTo 1

        assertThrows<IllegalArgumentException> {
            interval.chunkSecond(0)
        }
        assertThrows<IllegalArgumentException> {
            interval.chunkSecond(-1)
        }
    }

    @Test
    fun `chunk second and aggregate`() {
        val start = nowZonedDateTime().startOfSecond()
        val endExclusive = start + 5.seconds()

        val interval = start..endExclusive

        val chunks = interval.chunkSecond(3)
            .map { seconds -> seconds.first()..seconds.last() }
            .toList()

        chunks.size shouldEqualTo 2
        chunks.all { it in interval }.shouldBeTrue()
    }

    @Test
    fun `chunk interval in millsecond`() {
        val start = nowZonedDateTime().startOfMillis()
        val endExclusive = start + 5.millis()
        val interval = start..endExclusive
        logger.debug { "interval=$interval" }

        val chunks = interval.chunkMilli(4).toList()

        chunks.forEachIndexed { index, chunk ->
            logger.debug { "chunks[$index] = $chunk" }
            chunk.size shouldBeLessOrEqualTo 4
            assertTrue { chunk.first() in interval }
            assertTrue { chunk.last() in interval }
        }

        chunks.size shouldEqualTo 2
        chunks[0].size shouldEqualTo 4
        chunks[1].size shouldEqualTo 1

        assertThrows<IllegalArgumentException> {
            interval.chunkMilli(0)
        }
        assertThrows<IllegalArgumentException> {
            interval.chunkMilli(-1)
        }
    }

    @Test
    fun `chunk millis and aggregate`() {
        val start = nowZonedDateTime().startOfMillis()
        val endExclusive = start + 5.millis()

        val interval = start..endExclusive

        val chunks = interval.chunkMilli(3)
            .map { millis -> millis.first()..millis.last() }
            .toList()

        chunks.size shouldEqualTo 2

        chunks.all { it in interval }.shouldBeTrue()
    }
}