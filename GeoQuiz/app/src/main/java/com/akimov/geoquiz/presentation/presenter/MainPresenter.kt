package com.akimov.geoquiz.presentation.presenter

import com.akimov.geoquiz.R
import com.akimov.geoquiz.models.Question
import com.akimov.geoquiz.presentation.view.IMainView

/**
 * Created by lex on 9/1/18.
 */
class MainPresenter : IMainPresenter {

  private lateinit var mainView: IMainView

  private var mCurrentIndex = 0

  private val mQuestionBank = listOf(
      Question(R.string.question_australia, true),
      Question(R.string.question_oceans, true),
      Question(R.string.question_mideast, false),
      Question(R.string.question_africa, false),
      Question(R.string.question_americas, true),
      Question(R.string.question_asia, true)
  )

  override fun setView(view: IMainView) {
    this.mainView = view
  }

  override fun updateQuestion() {
    val question = mQuestionBank[mCurrentIndex].textResId
    mainView.showNewQuestion(question)
    mainView.enableButtons(true)
  }

  override fun setCurrentIndex(currentIndex: Int) {
    this.mCurrentIndex = currentIndex
  }

  override fun getCurrentIndex(): Int {
    return this.mCurrentIndex
  }

  override fun prevQuestion() {
    if (mCurrentIndex == 0)
      mCurrentIndex = mQuestionBank.size - 1
    else
      mCurrentIndex -= 1
    updateQuestion()
  }

  override fun nextQuestion() {
    mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size
    updateQuestion()
  }

  override fun answerSelected(answer: Boolean) {
    checkAnswer(answer)
    mainView.enableButtons(false)
  }

  private fun checkAnswer(userPressedTrue: Boolean) {
    val answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue

    val messageResId = if (userPressedTrue == answerIsTrue) R.string.correct_toast else R.string.incorrect_toast
    mainView.showAnswer(messageResId)
  }
}