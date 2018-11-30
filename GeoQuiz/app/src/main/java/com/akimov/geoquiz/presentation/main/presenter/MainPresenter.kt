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

  private val mQuestionBank = listOf(
      Question(1, "Canberra is the capital of Australia.", true),
      Question(2, "The Pacific Ocean is larger than the Atlantic Ocean.", true),
      Question(3, "The Suez Canal connects the Red Sea and the Indian Ocean.", false),
      Question(4, "The source of the Nile River is in Egypt.", false),
      Question(5, "The Amazon River is the longest river in the Americas.", true),
      Question(6, "Lake Baikal is the worls\\'s oldest and deepest freshwater lake.", true)
  )

  override fun setView(view: IMainView) {
    this.mainView = view
  }

  override fun updateQuestion() {
    mainView.showQuestion(mQuestionBank[mCurrentIndex])
    mainView.enableButtons(true)
  }

  override fun setCurrentIndex(currentIndex: Int) {
    this.mCurrentIndex = currentIndex
  }

  override fun getCurrentIndex(): Int {
    return this.mCurrentIndex
  }

  override fun getPreviousQuestion() {
    if (mCurrentIndex == 0)
      mCurrentIndex = mQuestionBank.size - 1
    else
      mCurrentIndex -= 1
    updateQuestion()
  }

  override fun getNextQuestion() {
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

  override fun cheatBtnClicked() {
    mainView.showCheatScreen()
  }

}