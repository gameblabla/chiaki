// SPDX-License-Identifier: LicenseRef-AGPL-3.0-only-OpenSSL

package com.gameblabla.chiaki.settings

import androidx.lifecycle.ViewModel
import com.gameblabla.chiaki.common.AppDatabase
import com.gameblabla.chiaki.common.RegisteredHost
import com.gameblabla.chiaki.common.ext.toLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class SettingsRegisteredHostsViewModel(val database: AppDatabase): ViewModel()
{
	private val disposable = CompositeDisposable()

	val registeredHosts by lazy {
		database.registeredHostDao().getAll().toLiveData()
	}

	fun deleteHost(host: RegisteredHost)
	{
		database.registeredHostDao()
			.delete(host)
			.subscribeOn(Schedulers.io())
			.subscribe()
			.addTo(disposable)
	}

	override fun onCleared()
	{
		super.onCleared()
		disposable.dispose()
	}
}
