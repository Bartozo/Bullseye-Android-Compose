package com.bartozo.bullseye.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.abs
import kotlin.random.Random

class GameViewModel : ViewModel() {

    private var _round = MutableLiveData(1)
    var round: LiveData<Int> = _round

    private var _score = MutableLiveData(0)
    var score: LiveData<Int> = _score

    private var _target = MutableLiveData(Random.nextInt(0, 100))
    var target: LiveData<Int> = _target

    private var _sliderValue = MutableLiveData(0f)
    var sliderValue: LiveData<Float> = _sliderValue

    private val sliderTargetDifference: Int
        get() = abs(sliderValue.value!!.toInt() - target.value!!)

    fun updateSlider(sliderValue: Float) {
        _sliderValue.value = sliderValue
    }

    fun startNewGame() {
        _round.value = 1
        _score.value = 0
        resetSliderAndTarget()
    }

    fun startNewRound(round: Int, score: Int) {
        _round.value = round + 1
        _score.value = score + pointsForCurrentRound()
        resetSliderAndTarget()
    }

    fun alertTitle(): String {
        val title: String
        if (sliderTargetDifference == 0) {
            title = "Perfect!"
        } else if (sliderTargetDifference < 5) {
            title = "You almost had it!"
        } else if (sliderTargetDifference <= 10) {
            title = "Not bad."
        } else {
            title = "Are you even trying?"
        }

        return title
    }

    fun scoringMessage(): String {
        return "The slider's value is ${sliderValue.value!!.toInt()}.\n" +
                "The target value is ${target.value}.\n" +
                "You scored ${pointsForCurrentRound()} points this round."
    }

    private fun resetSliderAndTarget() {
        _sliderValue.value = Random.nextInt(0, 100).toFloat()
        _target.value = Random.nextInt(0, 100)
    }

    private fun pointsForCurrentRound(): Int {
        val maximumScore = 100
        val points: Int
        if (sliderTargetDifference == 0) {
            points = 200
        } else if (sliderTargetDifference == 1) {
            points = 150
        } else {
            points = maximumScore - sliderTargetDifference
        }

        return points
    }
}