package kt.akimov.criminalintent.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kg.dostek.criminalintent.R

abstract class SingleFragmentActivity : AppCompatActivity() {//FragmentActivity()

  protected abstract fun createFragment(): Fragment

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fragment)

    val fm = supportFragmentManager
    var fragment = fm.findFragmentById(R.id.fragmentContainer)

    if (fragment == null) {
      fragment = createFragment()
      fm.beginTransaction()
          .add(R.id.fragmentContainer, fragment)
          .commit()
    }
  }
}


