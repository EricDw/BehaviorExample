package net.publicmethod.behavior

typealias Work = suspend () -> Unit

interface BehaviorScope {

    fun request(eventName: String, work: Work = {})

    fun waitFor(eventName: String, work: Work = {})

}
