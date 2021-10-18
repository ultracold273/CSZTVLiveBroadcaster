package com.lxwei.csztvlivebroadcaster

sealed class PlayerAction {
    object EmptyAction : PlayerAction()

    object StopAction : PlayerAction()

    data class UpdateAction(val url: String): PlayerAction()
}
