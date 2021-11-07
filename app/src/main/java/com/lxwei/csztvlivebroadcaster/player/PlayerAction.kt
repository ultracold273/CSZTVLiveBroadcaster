package com.lxwei.csztvlivebroadcaster.player

/**
 * Player actions that a user may take when using the D-pad controller
 */
sealed class PlayerAction {
    object EmptyAction : PlayerAction()

    object StopAction : PlayerAction()

    data class UpdateAction(val url: String): PlayerAction()
}
