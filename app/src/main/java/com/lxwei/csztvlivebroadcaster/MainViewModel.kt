package com.lxwei.csztvlivebroadcaster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    val handle: SavedStateHandle,
    private val channelRepo: ChannelInfoRepo
) : ViewModel() {

    private var _playerAction = MutableLiveData<PlayerAction>(PlayerAction.EmptyAction)
    val playerAction: LiveData<PlayerAction> = _playerAction

    private var liveChannels = getLiveChannels()
    private var currentChannelIndex = 0

    init {
        update()
    }

    private fun getLiveChannels() = runBlocking {
        channelRepo.getLiveChannels()
    }

    fun refresh() {
        liveChannels = getLiveChannels()
        update()
    }

    private fun update() {
        _playerAction.value = PlayerAction.UpdateAction(liveChannels[currentChannelIndex])
    }

    fun previousChannel() {
        currentChannelIndex -= 1
        if (currentChannelIndex < 0) currentChannelIndex = liveChannels.size - 1
        Timber.i("Prev channel $currentChannelIndex/${liveChannels.size}")
        update()
    }

    fun nextChannel() {
        currentChannelIndex += 1
        if (currentChannelIndex >= liveChannels.size) currentChannelIndex = 0
        Timber.i("Next channel: $currentChannelIndex/${liveChannels.size}")
        update()
    }

    fun stopPlayer() {
        _playerAction.value = PlayerAction.StopAction
    }

    fun resumePlayer() {
        update()
    }
}