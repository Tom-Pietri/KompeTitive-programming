package com.tom.codejam.y2021.qualification

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class DummyTest {
    @Test
    fun `test it works`() {
        expectThat(itWorks()) {
            get { this }.isEqualTo("It works")
        }
    }
}
