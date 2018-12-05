package com.akimov.geoquiz.presentation.main.view


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.akimov.geoquiz.R
import com.akimov.geoquiz.domain.models.Question
import com.akimov.geoquiz.presentation.cheat.view.CheatActivity
import com.akimov.geoquiz.presentation.common.BaseActivity
import com.akimov.geoquiz.presentation.main.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

  private lateinit var viewModel: MainActivityViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Get the ViewModel
    viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

    val questionObserver = Observer<Question> { newQustion ->
      question_text_view.text = newQustion.questionText
    }

    viewModel.currentQuestion.observe(this, questionObserver)

    viewModel.buttonsEnabled.observe(this, Observer<Boolean> {
      enableButtons(it)
    })
//    viewModel.viewState.observe(this, Observer<ViewState> {
//      updateState(it)
//    })
  }

  private fun showAnswer(messageResId: Int) {
    Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
  }

  fun onTrueBtnClicked(view: View) {
    viewModel.answerSelected(true)
    showAnswer(viewModel.checkResult)
  }

  fun onFalseBtnClicked(view: View) {
    viewModel.answerSelected(false)
    showAnswer(viewModel.checkResult)
  }

  fun onNextClicked(view: View) {
    viewModel.showNextQuestion()
  }

  fun onPrevClicked(view: View) {
    viewModel.showPreviousQuestion()
  }

  fun enableButtons(isEnabled: Boolean) {
    trueBtn.isEnabled = isEnabled
    falseBtn.isEnabled = isEnabled
    cheatBtn.isEnabled = isEnabled
  }

  fun onCheatBtnClicked(view: View) {//start cheat activity
    val intent: Intent = CheatActivity.newIntent(this, viewModel.answer)
    startActivityForResult(intent, Companion.REQUEST_CODE_CHEAT)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (resultCode != Activity.RESULT_OK) {
      return;
    }
    if (requestCode == REQUEST_CODE_CHEAT) {
      if (data == null) {
        return;
      }
      viewModel.isCheater = CheatActivity.wasAnswerShown(data)
    }
  }

  companion object {
    //private val KEY_INDEX: String = "index"
    private val REQUEST_CODE_CHEAT = 0
  }
//  fun showCheatScreen() {
//    val intent: Intent = CheatActivity.newIntent(this, viewModel.getAnswer() )
//    startActivity(intent)
//  }
}
