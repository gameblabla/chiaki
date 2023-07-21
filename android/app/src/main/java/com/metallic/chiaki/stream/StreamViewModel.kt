// SPDX-License-Identifier: LicenseRef-AGPL-3.0-only-OpenSSL

package com.gameblabla.chiaki.stream

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.gameblabla.chiaki.common.LogManager
import com.gameblabla.chiaki.session.StreamSession
import com.gameblabla.chiaki.common.Preferences
import com.gameblabla.chiaki.lib.*
import com.gameblabla.chiaki.session.StreamInput

class StreamViewModel(val application: Application, val connectInfo: ConnectInfo): ViewModel()
{
	val preferences = Preferences(application)
	val logManager = LogManager(application)

	private var _session: StreamSession? = null
	val input = StreamInput(application, preferences)
	val session = StreamSession(connectInfo, logManager, preferences.logVerbose, input)

	private var _onScreenControlsEnabled = MutableLiveData<Boolean>(preferences.onScreenControlsEnabled)
	val onScreenControlsEnabled: LiveData<Boolean> get() = _onScreenControlsEnabled

	private var _touchpadOnlyEnabled = MutableLiveData<Boolean>(preferences.touchpadOnlyEnabled)
	val touchpadOnlyEnabled: LiveData<Boolean> get() = _touchpadOnlyEnabled

	override fun onCleared()
	{
		super.onCleared()
		_session?.shutdown()
	}

	fun setOnScreenControlsEnabled(enabled: Boolean)
	{
		preferences.onScreenControlsEnabled = enabled
		_onScreenControlsEnabled.value = enabled
	}

	fun setTouchpadOnlyEnabled(enabled: Boolean)
	{
		preferences.touchpadOnlyEnabled = enabled
		_touchpadOnlyEnabled.value = enabled
	}
}
