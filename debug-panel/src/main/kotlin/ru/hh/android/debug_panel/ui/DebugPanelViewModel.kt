package ru.hh.android.debug_panel.ui

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.processphoenix.ProcessPhoenix
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.hh.android.core.experiments.models.ExperimentModel
import ru.hh.android.debug_panel.domain.DebugExperimentsInteractor
import javax.inject.Inject


@HiltViewModel
internal class DebugPanelViewModel @Inject constructor(
    private val applicationContext: Context,
    private val debugExperimentsInteractor: DebugExperimentsInteractor,
) : ViewModel() {

    private val _experimentsSet = MutableLiveData<List<ExperimentModel>>(emptyList())
    val experimentsSet: LiveData<List<ExperimentModel>> = _experimentsSet


    init {
        reloadExperimentsList()
    }


    fun updateExperiment(experimentModel: ExperimentModel) {
        debugExperimentsInteractor.changeExperiment(experimentModel)
        reloadExperimentsList()
    }

    fun restartApp(activity: Activity) {
        ProcessPhoenix.triggerRebirth(
            activity,
            applicationContext.packageManager.getLaunchIntentForPackage(applicationContext.packageName)
        )
    }

    private fun reloadExperimentsList() {
        _experimentsSet.postValue(getAllExperiments())
    }

    private fun getAllExperiments(): List<ExperimentModel> {
        TODO("How to collect all experiments across the codebase?")
    }

}