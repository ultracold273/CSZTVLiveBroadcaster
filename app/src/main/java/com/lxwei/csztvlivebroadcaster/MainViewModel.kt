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

    private var _currentUrl = MutableLiveData("")
    val currentUrl: LiveData<String> = _currentUrl

    private var _playerClose = MutableLiveData(false)
    val playerClose: LiveData<Boolean> = _playerClose

    private var liveChannels = getLiveChannels()
    private var currentChannelIndex = 0

    init {
        _currentUrl.value = liveChannels[currentChannelIndex]
    }

    private fun getLiveChannels() = runBlocking {
        channelRepo.getLiveChannels()
    }

    fun previousChannel() {
        currentChannelIndex -= 1
        if (currentChannelIndex < 0) currentChannelIndex = liveChannels.size - 1
        Timber.i("Prev channel $currentChannelIndex/${liveChannels.size}")
        _currentUrl.value = liveChannels[currentChannelIndex]
    }

    fun nextChannel() {
        currentChannelIndex += 1
        if (currentChannelIndex >= liveChannels.size) currentChannelIndex = 0
        Timber.i("Next channel: $currentChannelIndex/${liveChannels.size}")
        _currentUrl.value = liveChannels[currentChannelIndex]
    }

    fun stopPlayer() {
        _playerClose.value = true
    }

    fun resumePlayer() {
        _playerClose.value = false
    }
}