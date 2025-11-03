package com.example.m7cta.test

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * Extension functions for testing
 */

/**
 * Collects all emissions from a Flow in a test
 */
fun <T> Flow<T>.test(
    scope: TestScope,
    timeout: Duration = 5.seconds,
    validate: suspend (List<T>) -> Unit
) {
    scope.runTest(timeout = timeout) {
        val emissions = mutableListOf<T>()
        val job = launch(UnconfinedTestDispatcher(testScheduler)) {
            toList(emissions)
        }
        validate(emissions)
        job.cancel()
    }
}

/**
 * Asserts that a value is within a delta of an expected value
 */
fun assertEqualsWithDelta(expected: Double, actual: Double, delta: Double = 0.01, message: String = "") {
    val difference = kotlin.math.abs(expected - actual)
    if (difference > delta) {
        throw AssertionError(
            "${if (message.isNotEmpty()) "$message: " else ""}Expected $expected but was $actual (delta: $delta)"
        )
    }
}

/**
 * Asserts that a result is successful
 */
fun <T> Result<T>.assertSuccess(): T {
    if (isFailure) {
        throw AssertionError("Expected success but got failure: ${exceptionOrNull()?.message}")
    }
    return getOrThrow()
}

/**
 * Asserts that a result is a failure
 */
fun <T> Result<T>.assertFailure(): Throwable {
    if (isSuccess) {
        throw AssertionError("Expected failure but got success: ${getOrNull()}")
    }
    return exceptionOrNull()!!
}

/**
 * Asserts that a result is a failure with a specific message
 */
fun <T> Result<T>.assertFailureWithMessage(expectedMessage: String) {
    val exception = assertFailure()
    if (exception.message != expectedMessage) {
        throw AssertionError("Expected message '$expectedMessage' but got '${exception.message}'")
    }
}