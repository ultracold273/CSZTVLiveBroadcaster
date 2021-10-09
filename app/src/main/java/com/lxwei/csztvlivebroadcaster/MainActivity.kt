package com.lxwei.csztvlivebroadcaster

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.lxwei.csztvlivebroadcaster.ui.presentation.LivePlayer
import com.lxwei.csztvlivebroadcaster.ui.presentation.LivePlayerScreen
import com.lxwei.csztvlivebroadcaster.ui.theme.CSZTVLiveBroadcasterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CSZTVLiveBroadcasterTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    LivePlayerScreen(viewModel)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopPlayer()
    }

    override fun onResume() {
        super.onResume()
        viewModel.resumePlayer()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (event?.keyCode) {
            KeyEvent.KEYCODE_DPAD_UP -> {
                viewModel.previousChannel()
            }
            KeyEvent.KEYCODE_DPAD_DOWN -> {
                viewModel.nextChannel()
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}
