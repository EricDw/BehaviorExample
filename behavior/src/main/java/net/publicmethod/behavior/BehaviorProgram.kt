package net.publicmethod.behavior


class BehaviorProgram(
) : (BehaviorScope.() -> Unit) -> Unit {

    override fun invoke(block: BehaviorScope.() -> Unit) {
        TODO("Not yet implemented")
    }
}