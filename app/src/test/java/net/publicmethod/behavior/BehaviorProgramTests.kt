package net.publicmethod.behavior

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
internal class BehaviorProgramTests {

    private lateinit var program: BehaviorProgram

    private lateinit var dispatcher: TestCoroutineDispatcher

    private val scope = object : CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = Dispatchers.Main
    }

    @Before
    fun setUp() {

        dispatcher = TestCoroutineDispatcher()

        Dispatchers.setMain(dispatcher)

        program = BehaviorProgram(scope)

    }

    @Test
    fun `can make request events`() = runBlockingTest {

        val expected = "Happened"

        var actual = ""

        program {

            request("Print Event") {
                actual = expected

            }

        }

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `can waitFor events`() = runBlockingTest {

        val expected = "Happened"

        var actual = ""

        program {

            waitFor("Print Event") {

                actual = expected

            }

        }

        program {

            request("Print Event")

        }


        Assert.assertEquals(expected, actual)
    }

}