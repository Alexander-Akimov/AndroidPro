package com.akimov.geoquiz.presentation.main.view


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.akimov.geoquiz.R
import com.akimov.geoquiz.domain.models.Question
import com.akimov.geoquiz.presentation.cheat.view.CheatActivity
import com.akimov.geoquiz.presentation.common.BaseActivity
import com.akimov.geoquiz.presentation.main.MainActivityViewModel

import com.akimov.geoquiz.presentation.main.presenter.IMainPresenter
import com.akimov.geoquiz.presentation.main.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

  // private lateinit var mainPresenter: IMainPresenter

  //private val KEY_INDEX: String = "index"

  private lateinit var viewModel: MainActivityViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Get the ViewModel
    viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

//    var currentIndex = 0
//    if (savedInstanceState != null)
//      currentIndex = savedInstanceState.getInt(KEY_INDEX, 0)

//    mainPresenter = MainPresenter()
//    mainPresenter.setView(this)
//    mainPresenter.setCurrentIndex(currentIndex)
//    mainPresenter.updateQuestion()

    val questionObserver = Observer<Question> { newQustion ->
      question_text_view.text = newQustion.questionText
    }

    viewModel.currentQuestion.observe(this, questionObserver)

    viewModel.buttonsEnabled.observe(this, Observer<Boolean> {
      enableButtons(it)
    })

    viewModel.showAnswer.observe(this, Observer<Int> {
      showAnswer(it)
    })

    viewModel.showNextQuestion()

//    viewModel.viewState.observe(this, Observer<ViewState> {
//      updateState(it)
//    })

  }

  fun enableButtons(isEnabled: Boolean) {
    trueBtn.isEnabled = isEnabled
    falseBtn.isEnabled = isEnabled
  }

//  override fun onSaveInstanceState(outState: Bundle?) {
//    super.onSaveInstanceState(outState)
//    Log.d(TAG, "onSaveInstanceState")
//
//    outState?.putInt(KEY_INDEX, mainPresenter.getCurrentIndex())
//  }

  /*override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
    super.onRestoreInstanceState(savedInstanceState)
    Log.d(TAG, "onRestoreInstanceState")

    if (savedInstanceState != null)
      mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0)
    updateQuestion()
  }*/

//  fun showNewQuestion(question: Int) {
//    question_text_view.setText(question)
//  }


  private fun showAnswer(messageResId: Int) {
    Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
  }

  fun onTrueBtnClicked(view: View) {
    viewModel.answerSelected(true)
  }

  fun onFalseBtnClicked(view: View) {
    viewModel.answerSelected(false)
  }

  fun onNextClicked(view: View) {
    viewModel.showNextQuestion()
  }

  fun onPrevClicked(view: View) {
    viewModel.showPreviousQuestion()
  }

  fun onCheatBtnClicked(view: View) {
    //  mainPresenter.cheatBtnClicked()
  }

  fun showCheatScreen() {//start cheat activity
    val intent: Intent = CheatActivity.newIntent(this)
    startActivity(intent)
  }



//  private fun updateState(state: ViewState) {
//    trueBtn.isEnabled = state.buttonsEnabled
//    falseBtn.isEnabled = state.buttonsEnabled
//    cheat_button.isEnabled = state.allowCheat
//  }
}
