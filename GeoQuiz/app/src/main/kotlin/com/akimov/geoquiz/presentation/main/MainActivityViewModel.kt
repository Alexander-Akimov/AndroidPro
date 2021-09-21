package com.akimov.geoquiz.presentation.main


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akimov.geoquiz.data.repositories.QuestionRepository
import com.akimov.geoquiz.domain.models.Question
import com.akimov.geoquiz.domain.repositories.IQuestionRepository

import com.akimov.geoquiz.presentation.main.presenter.IMainPresenter
import com.akimov.geoquiz.presentation.main.presenter.MainPresenter
import com.akimov.geoquiz.presentation.main.view.IMainView

class MainActivityViewModel : ViewModel(), IMainView {

  private lateinit var mainPresenter: IMainPresenter

//  private var mutableViewState: MutableLiveData<ViewState> = MutableLiveData()
//  val viewState: LiveData<ViewState> = mutableViewState

  private var mutableEnableButtons: MutableLiveData<Boolean> = MutableLiveData()
  val buttonsEnabled: LiveData<Boolean> = mutableEnableButtons

  private var mutableCurrentQuestion: MutableLiveData<Question> = MutableLiveData()
  var currentQuestion: LiveData<Question> = mutableCurrentQuestion

  private var mutableAnswerCheckResult: MutableLiveData<Int> = MutableLiveData()
  override var checkResult: Int//val checkResult: LiveData<Int> = mutableAnswerCheckResult
    get() = mutableAnswerCheckResult.value!!
    set(value) {
      mutableAnswerCheckResult.value = value
    }

  private var mutableIsCheater: MutableLiveData<Boolean> = MutableLiveData()
  override var isCheater: Boolean//private val isCheater: LiveData<Boolean> = mutableIsCheater
    get() = mutableIsCheater.value ?: false
    set(value) {
      mutableIsCheater.value = value
    }

  var answer: Boolean = false
    get() = currentQuestion.value?.isAnswerTrue ?: false

  init {
    mainPresenter = MainPresenter()
    mainPresenter.setView(this)
    mainPresenter.renderQuestion()
  }

  override fun enableButtons(isEnabled: Boolean) {
    mutableEnableButtons.value = isEnabled
  }

  override fun showQuestion(question: Question) {
    mutableCurrentQuestion.value = question
  }

  fun showPreviousQuestion() {
    mainPresenter.getPreviousQuestion()
    isCheater = false
  }

  fun showNextQuestion() {
    mainPresenter.getNextQuestion()
    isCheater = false
  }

  fun answerSelected(answer: Boolean) {
    mainPresenter.answerSelected(answer)
  }

  fun allowCheating() {

  }
}