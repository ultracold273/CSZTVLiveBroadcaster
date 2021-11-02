package com.lxwei.csztvlivebroadcaster.player

sealed class PlayerAction {
    object EmptyAction : PlayerAction()

    object StopAction : PlayerAction()

    data class UpdateAction(val url: String): PlayerAction()
}
