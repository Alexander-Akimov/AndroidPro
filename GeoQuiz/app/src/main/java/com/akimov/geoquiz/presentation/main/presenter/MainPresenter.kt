package com.akimov.geoquiz.presentation.main.presenter

import com.akimov.geoquiz.R
import com.akimov.geoquiz.domain.models.Question
import com.akimov.geoquiz.presentation.main.view.IMainView

/**
 * Created by lex on 9/1/18.
 */
class MainPresenter : IMainPresenter {

  private lateinit var mainView: IMainView

  private var mCurrentIndex = 0

  private lateinit var mQuestionList: List<Question>

  override fun setView(view: IMainView) {
    this.mainView = view
  }

  override fun renderQuestion() {
    mainView.showQuestion(mQuestionList[mCurrentIndex])
    mainView.enableButtons(true)
  }

  override fun getPreviousQuestion() {
    if (mCurrentIndex == 0)
      mCurrentIndex = mQuestionList.size - 1
    else
      mCurrentIndex -= 1
    renderQuestion()
  }

  override fun getNextQuestion() {
    mCurrentIndex = (mCurrentIndex + 1) % mQuestionList.size
    renderQuestion()
  }

  override fun answerSelected(answer: Boolean) {
    checkAnswer(answer)
    mainView.enableButtons(false)
  }

  private fun checkAnswer(userPressedTrue: Boolean) {
    val answerIsTrue = mQuestionList[mCurrentIndex].isAnswerTrue

    val messageResId = when {
      mainView.isCheater -> R.string.judgment_toast
      userPressedTrue == answerIsTrue -> R.string.correct_toast
      else -> R.string.incorrect_toast
    }
    mainView.checkResult = messageResId
  }

  override fun setData(allQuestions: List<Question>) {
    mQuestionList = allQuestions
  }
}