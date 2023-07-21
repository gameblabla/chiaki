// SPDX-License-Identifier: LicenseRef-AGPL-3.0-only-OpenSSL

package com.gameblabla.chiaki.settings

import androidx.lifecycle.ViewModel
import com.gameblabla.chiaki.common.AppDatabase
import com.gameblabla.chiaki.common.Preferences
import com.gameblabla.chiaki.common.ext.toLiveData

class SettingsViewModel(val database: AppDatabase, val preferences: Preferences): ViewModel()
{
	val registeredHostsCount by lazy {
		database.registeredHostDao().count().toLiveData()
	}

	val bitrateAuto by lazy {
		preferences.bitrateAutoObservable.toLiveData()
	}
}
