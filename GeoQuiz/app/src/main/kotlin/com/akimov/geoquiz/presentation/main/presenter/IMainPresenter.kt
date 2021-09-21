package com.akimov.geoquiz.presentation.main.presenter

import com.akimov.geoquiz.domain.models.Question
import com.akimov.geoquiz.presentation.main.view.IMainView

/**
 * Created by lex on 9/1/18.
 */
interface IMainPresenter {
  fun setView(view: IMainView)

  fun renderQuestion()
  fun answerSelected(answer: Boolean)
  fun getNextQuestion()
  fun getPreviousQuestion()

  fun setData(allQuestions: List<Question>)
}