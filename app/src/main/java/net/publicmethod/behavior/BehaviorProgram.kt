package net.publicmethod.behavior

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.launch


class BehaviorProgram(
    private val scope: CoroutineScope,
) : (BehaviorScope.() -> Unit) -> Unit {

    private sealed class Message() {

        data class Request(
            val eventName: String,
            val work: Work,
        ) : Message()

        data class WaitFor(
            val eventName: String,
            val work: Work,
        ) : Message()


    }

    @Suppress("EXPERIMENTAL_API_USAGE")
    private val processor = scope.actor<Message> {

        val waiters = mutableMapOf<String, MutableList<Work>>()

        for (message in this) {

            when (message) {

                is Message.Request -> {

                    val eventName = message.eventName

                    val workWaitingToBeDone = waiters[eventName]

                    waiters[eventName] = mutableListOf()

                    workWaitingToBeDone?.forEach {

                        launch {

                            it()

                        }

                    }

                    launch {

                        message.work()

                    }

                }

                is Message.WaitFor -> {

                    waiters.getOrPut(message.eventName) {

                        mutableListOf()

                    } += message.work

                }

            }

        }


    }

    private val behaviorScope: BehaviorScope = object : BehaviorScope {

        override fun request(eventName: String, work: Work) {

            scope.launch {

                processor.send(Message.Request(
                    eventName, work
                ))

            }

        }

        override fun waitFor(eventName: String, work: Work) {

            scope.launch {

                processor.send(Message.WaitFor(
                    eventName, work
                ))

            }

        }

    }

    override fun invoke(block: BehaviorScope.() -> Unit) {

        scope.launch {

            behaviorScope.block()

        }

    }

}