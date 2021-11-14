package com.lxwei.csztvlivebroadcaster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lxwei.csztvlivebroadcaster.player.PlayerAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    val handle: SavedStateHandle,
    private val channelRepo: ChannelInfoRepo
) : ViewModel() {

    private var _playerAction = MutableLiveData<PlayerAction>(PlayerAction.EmptyAction)
    val playerAction: LiveData<PlayerAction> = _playerAction

    private lateinit var liveChannels: List<String>
    private var currentChannelIndex = 0

    init {
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                refresh()
                delay(TimeUnit.MINUTES.toMillis(20))
            }
        }
    }

    private suspend fun getLiveChannels(): List<String> {
        return channelRepo.getLiveChannels()
    }

    fun refresh() = runBlocking {
        liveChannels = getLiveChannels()
        _playerAction.postValue(PlayerAction.UpdateAction(liveChannels[currentChannelIndex]))
    }

    private fun update() {
        Timber.i("Updating liveChannels")
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
        if (::liveChannels.isInitialized) {
            update()
        }
    }
}