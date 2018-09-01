package com.akimov.geoquiz.presentation.presenter

import com.akimov.geoquiz.presentation.view.IMainView

/**
 * Created by lex on 9/1/18.
 */
interface IMainPresenter {
  fun setView(view: IMainView)
  fun updateQuestion()
  fun setCurrentIndex(currentIndex: Int)
  fun getCurrentIndex(): Int
  fun answerSelected(answer: Boolean)
  fun prevQuestion()
  fun nextQuestion()
}