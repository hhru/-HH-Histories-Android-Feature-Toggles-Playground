package ru.hh.android.debug_panel.domain

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import ru.hh.android.core.experiments.models.ExperimentModel
import ru.hh.android.core.experiments.ExperimentsConstants
import toothpick.InjectConstructor


@InjectConstructor
internal class DebugExperimentsInteractor(
    applicationContext: Context,
) {

    private val prefsName: SharedPreferences by lazy {
        applicationContext.getSharedPreferences(
            ExperimentsConstants.PREFS_NAME,
            Context.MODE_PRIVATE
        )
    }


    @SuppressLint("ApplySharedPref")
    fun changeExperiment(experimentModel: ExperimentModel) {
        prefsName.edit()
            .putBoolean(experimentModel.key, experimentModel.isUserAffected.not())
            .commit()
    }

}