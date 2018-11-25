package com.konradpekala.hackyeah2018.ui.main

import android.content.Intent
import android.util.Log
import com.estimote.mustard.rx_goodness.rx_requirements_wizard.RequirementsWizardFactory
import com.konradpekala.hackyeah2018.data.repository.CardRepository
import com.konradpekala.hackyeah2018.ui.base.BasePresenter
import com.konradpekala.hackyeah2018.service.BeaconService

class MainPresenter<V: MainMvp.View>(view: V, val cardRepo: CardRepository): BasePresenter<V>(view),MainMvp.Presenter {

    override fun onEnableScanningClick() {
        RequirementsWizardFactory.createEstimoteRequirementsWizard().fulfillRequirements(
            view.getActivity(),
            onRequirementsFulfilled = {
                Log.d("Beacons","onRequirementsFulfilled")
                view.getActivity().startService(Intent(view.getActivity(),BeaconService::class.java))
            },
            onRequirementsMissing = {},
            onError = {}
        )
    }

    override fun start() {
        super.start()
    }

}