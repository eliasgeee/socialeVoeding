package com.socialevoeding.util_datafactory

import java.security.SecureRandom
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.experimental.and

object DataFactory {
    fun randomString(length: Int = 10): String {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        val random = SecureRandom()
        val bytes = ByteArray(length)
        random.nextBytes(bytes)

        return (bytes.indices)
            .map { i ->
                charPool.get((bytes[i] and 0xFF.toByte() and (charPool.size - 1).toByte()).toInt())
            }.joinToString("")
    }

    fun randomInt(from: Int = 0, to: Int = 1000): Int {
        return (from until to).random()
    }

    fun randomLong(): Long {
        return randomInt().toLong()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun randomTime(): LocalTime {
        return LocalTime.of(
            randomHour(),
            randomMinute()
        )
    }

    fun randomDate(): LocalDate {
        val year = (1990 until 2018).random()
        val month = (1 until 12).random()
        val day = (1 until 29).random()

        return LocalDate.of(year, month, day)
    }

    fun randomHour(): Int {
        return randomInt(0, 23)
    }

    fun randomMinute(): Int {
        return randomInt(0, 59)
    }

    fun randomDateTime(): LocalDateTime {
        return LocalDateTime.of(
            randomDate(),
            randomTime()
        )
    }
}