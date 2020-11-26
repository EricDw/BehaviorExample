package net.publicmethod.behavior

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class BehaviorProgramTests {

    private lateinit var program: BehaviorProgram

    @BeforeEach
    fun setUp() {

        program = BehaviorProgram()

    }

    @Test
    fun `can make request events`() {

        val expected = "Happened"
        var actual = ""

        program {

            request("Print Event") {
                actual = expected

            }

        }

        Assertions.assertEquals(expected, actual)

    }

}