package com.akimov.geoquiz.presentation.cheat.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.akimov.geoquiz.R
import kotlinx.android.synthetic.main.activity_cheat.*

class CheatActivity : AppCompatActivity() {

  private var mAnswerIsTrue: Boolean = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_cheat)

    mAnswerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
  }

  fun onShowAnswerBtnClicked(view: View) {
    answer_textView.setText(if (mAnswerIsTrue) R.string.true_button else R.string.false_button)
    setAnswerShowResult(true)
  }

  private fun setAnswerShowResult(isAnswerShown: Boolean) {
    val data = Intent()
    data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
    setResult(Activity.RESULT_OK, data)
  }

  companion object {
    private val EXTRA_ANSWER_IS_TRUE = "com.akimov.geoquiz.answer_is_true" //static final
    private val EXTRA_ANSWER_SHOWN = "com.akimov.geoquiz.answer_shown"
    //private val INTENT_
    fun newIntent(context: Context, answerIsTrue: Boolean): Intent {
      val intent = Intent(context, CheatActivity::class.java)
      intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
      return intent
    }

    fun wasAnswerShown(result: Intent): Boolean {
      return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false)
    }
  }
}
