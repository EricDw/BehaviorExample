package net.publicmethod.behavior

interface BehaviorScope {

    fun request(eventName: String, function: suspend () -> Unit)

}
