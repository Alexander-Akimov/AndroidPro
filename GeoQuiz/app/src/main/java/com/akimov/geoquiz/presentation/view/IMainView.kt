package com.akimov.geoquiz.presentation.view

/**
 * Created by lex on 9/1/18.
 */
interface IMainView {
  fun showNewQuestion(question: Int)
  fun enableButtons(isEnabled: Boolean)
  fun showAnswer(messageResId: Int)
}