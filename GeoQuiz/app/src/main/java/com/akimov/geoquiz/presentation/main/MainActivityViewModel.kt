package com.akimov.geoquiz.presentation.main


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akimov.geoquiz.domain.models.Question

import com.akimov.geoquiz.presentation.main.presenter.IMainPresenter
import com.akimov.geoquiz.presentation.main.presenter.MainPresenter
import com.akimov.geoquiz.presentation.main.view.IMainView
import com.akimov.geoquiz.presentation.main.view.ViewState

class MainActivityViewModel : ViewModel(), IMainView {

  private lateinit var mainPresenter: IMainPresenter

  private var mutableCurrentQuestion: MutableLiveData<Question> = MutableLiveData()
  var currentQuestion: LiveData<Question> = mutableCurrentQuestion

  private var mutableShowAnswer: MutableLiveData<Int> = MutableLiveData()
  val showAnswer: LiveData<Int> = mutableShowAnswer

//  private var mutableViewState: MutableLiveData<ViewState> = MutableLiveData()
//  val viewState: LiveData<ViewState> = mutableViewState

  private var mutableEnableButtons: MutableLiveData<Boolean> = MutableLiveData()
  val buttonsEnabled: LiveData<Boolean> = mutableEnableButtons

  init {
    mainPresenter = MainPresenter()
    mainPresenter.setView(this)
  }

  override fun showAnswer(messageResId: Int) {
    mutableShowAnswer.value = messageResId
  }

  override fun enableButtons(isEnabled: Boolean) {
    mutableEnableButtons.value = isEnabled
  }

  override fun showCheatScreen() {

  }

  override fun showQuestion(question: Question) {
    mutableCurrentQuestion.value = question
  }

  fun showPreviousQuestion() {
    mainPresenter.getPreviousQuestion()
  }

  fun showNextQuestion() {
    mainPresenter.getNextQuestion()
  }

  fun answerSelected(answer: Boolean) {
    mainPresenter.answerSelected(answer)
  }

  fun allowCheating() {

  }

}