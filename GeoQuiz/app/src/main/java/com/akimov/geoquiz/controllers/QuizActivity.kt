package com.akimov.geoquiz.controllers

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.akimov.geoquiz.R

import com.akimov.geoquiz.presentation.main.presenter.IMainPresenter
import com.akimov.geoquiz.presentation.main.presenter.MainPresenter
import com.akimov.geoquiz.presentation.main.view.IMainView
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : BaseActivity(), IMainView {

  private lateinit var mainPresenter: IMainPresenter

  private val KEY_INDEX: String = "index"

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_quiz)

    var currentIndex = 0
    if (savedInstanceState != null)
      currentIndex = savedInstanceState.getInt(KEY_INDEX, 0)

    mainPresenter = MainPresenter()
    mainPresenter.setView(this)
    mainPresenter.setCurrentIndex(currentIndex)
    mainPresenter.updateQuestion()
  }

  override fun onSaveInstanceState(outState: Bundle?) {
    super.onSaveInstanceState(outState)
    Log.d(TAG, "onSaveInstanceState")

    outState?.putInt(KEY_INDEX, mainPresenter.getCurrentIndex())
  }

  /*override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
    super.onRestoreInstanceState(savedInstanceState)
    Log.d(TAG, "onRestoreInstanceState")

    if (savedInstanceState != null)
      mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0)
    updateQuestion()
  }*/

  override fun showNewQuestion(question: Int) {
    question_text_view.setText(question)
  }

  override fun enableButtons(isEnabled: Boolean) {
    trueBtn.isEnabled = isEnabled
    falseBtn.isEnabled = isEnabled
  }

  override fun showAnswer(messageResId: Int) {
    Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
  }

  fun onTrueBtnClicked(view: View) {
    mainPresenter.answerSelected(true)
  }

  fun onFalseBtnClicked(view: View) {
    mainPresenter.answerSelected(false)
  }

  fun onNextClicked(view: View) {
    mainPresenter.nextQuestion()
  }

  fun onPrevClicked(view: View) {
    mainPresenter.prevQuestion()
  }
}
