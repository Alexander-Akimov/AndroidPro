package com.akimov.geoquiz.presentation.main.view

import com.akimov.geoquiz.domain.models.Question

/**
 * Created by lex on 9/1/18.
 */
interface IMainView {
  fun showQuestion(question: Question)
  fun enableButtons(isEnabled: Boolean)
  var isCheater: Boolean //Instead fun showCheatScreen()
  var checkResult: Int //Instead fun setAnswerCheckResult(messageResId: Int)//Instead showAnswer(messageResId: Int)
}