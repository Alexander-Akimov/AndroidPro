package com.akimov.geoquiz.presentation.main.view

import com.akimov.geoquiz.domain.models.Question

/**
 * Created by lex on 9/1/18.
 */
interface IMainView {
  fun showQuestion(question: Question)
  fun enableButtons(isEnabled: Boolean)
  fun showAnswer(messageResId: Int)
  fun showCheatScreen()
}