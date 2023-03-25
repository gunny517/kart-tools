package jp.ceed.android.kart.karttools.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.ceed.android.kart.karttools.repository.InputValueRepository
import javax.inject.Inject

@HiltViewModel
class SpeedCalculatorFragmentViewModel @Inject constructor(
    var inputValueRepository: InputValueRepository
)  : ViewModel() {

    var inputRpm: MutableLiveData<String> = MutableLiveData()

    var inputDrive: MutableLiveData<String> = MutableLiveData()

    var inputDriven: MutableLiveData<String> = MutableLiveData()

    var inputCircumference: MutableLiveData<String> = MutableLiveData()

    var finalRatio: MutableLiveData<String> = MutableLiveData()

    var speed: MutableLiveData<String> = MutableLiveData()

    init {
        restoreSavedValues()
        setObservers()
    }

    private fun setObservers() {
        inputDrive.observeForever {
            calculateFinalRatio()
        }
        inputDriven.observeForever {
            calculateFinalRatio()
        }
        inputRpm.observeForever {
            calculateSpeed()
        }
    }

    fun onPause() {
        inputValueRepository.saveValue(InputValueRepository.KEY_DRIVE, inputDrive.value)
        inputValueRepository.saveValue(InputValueRepository.KEY_DRIVEN, inputDriven.value)
        inputValueRepository.saveValue(InputValueRepository.KEY_CIRCUMFERENCE, inputCircumference.value)
    }

    private fun restoreSavedValues() {
        inputDrive.value = inputValueRepository.readValue(InputValueRepository.KEY_DRIVE)
        inputDriven.value = inputValueRepository.readValue(InputValueRepository.KEY_DRIVEN)
        inputCircumference.value = inputValueRepository.readValue(InputValueRepository.KEY_CIRCUMFERENCE)
    }

    private fun calculateFinalRatio() {
        val inputDriveValue = inputDrive.value
        val inputDrivenValue = inputDriven.value
        if(inputDriveValue.isNullOrEmpty() || inputDrivenValue.isNullOrEmpty()) {
            finalRatio.value = ""
        } else {
            finalRatio.value = (inputDrivenValue.toFloat() / inputDriveValue.toFloat()).toString()
        }
    }

    private fun calculateSpeed() {
        val inputRpmValue = inputRpm.value
        val finalValue = finalRatio.value
        val circumference = inputCircumference.value
        if(inputRpmValue.isNullOrEmpty() || finalValue.isNullOrEmpty() || circumference.isNullOrEmpty()) {
            speed.value = ""
        } else {
            val rotation: Float = inputRpmValue.toFloat() / finalValue.toFloat()
            speed.value = ((circumference.toFloat() * rotation * 60f) / 1000000f).toString()
        }
    }

}