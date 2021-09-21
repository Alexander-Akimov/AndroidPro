package com.akimov.geoquiz.presentation.cheat.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.akimov.geoquiz.R
import com.akimov.geoquiz.presentation.cheat.CheatActivityViewModel
import kotlinx.android.synthetic.main.activity_cheat.*


class CheatActivity : AppCompatActivity() {

  private lateinit var cheatViewModel: CheatActivityViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_cheat)

    cheatViewModel = ViewModelProviders.of(this).get(CheatActivityViewModel::class.java)

    cheatViewModel.rightAnswer = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

    if (cheatViewModel.isAnswerShown) showRightAnswer()

    txtViewSdkVersion.text = resources.getString(R.string.sdk_version, Build.VERSION.SDK_INT)
  }

  fun showRightAnswer() {
    answer_textView.setText(cheatViewModel.getAnswer())
    setAnswerShowResult()
  }

  fun onShowAnswerBtnClicked(view: View) {
    showRightAnswer()
  }

  private fun setAnswerShowResult() {
    val data = Intent()
    data.putExtra(EXTRA_ANSWER_SHOWN, true)
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
