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
 */

package com.github.debop.javatimes.ranges

import com.github.debop.javatimes.AbstractJavaTimesTest
import com.github.debop.javatimes.toDate
import com.github.debop.kodatimes.today
import org.junit.Test

import java.time.Duration
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DateRangeTest: AbstractJavaTimesTest() {

    @Test
    fun `simple creation`() {

        val start = today().toDate()
        val endInclusive = (start.toInstant() + Duration.ofDays(5)).toDate()
        val range = DateRange(start, endInclusive)

        assertEquals(start, range.start)
        assertEquals(endInclusive, range.endInclusive)

        assertEquals(start, range.first)
        assertEquals(endInclusive, range.last)

        assertEquals("$start..$endInclusive", range.toString())
    }

    @Test
    fun `empty range`() {

        val start = today().toDate()
        val endInclusive = (start.toInstant() - Duration.ofDays(1)).toDate()

        val range = DateRange(start, endInclusive)

        assertTrue { range.isEmpty() }
        assertEquals(DateRange.EMPTY, range)
    }
}