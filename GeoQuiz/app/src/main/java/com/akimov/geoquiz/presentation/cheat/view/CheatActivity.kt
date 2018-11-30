package com.akimov.geoquiz.presentation.cheat.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akimov.geoquiz.R

class CheatActivity : AppCompatActivity() {


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_cheat)

  }

  companion object {
    //private val INTENT_
    fun newIntent(context: Context): Intent {
      val intent = Intent(context, CheatActivity::class.java)
      return intent
    }
  }

}
