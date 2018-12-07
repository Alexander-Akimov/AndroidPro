package kt.akimov.criminalintent.presentation

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import kg.dostek.criminalintent.R


class CrimeActivity : FragmentActivity() {


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fragment)

    val fm = supportFragmentManager
    var fragment = fm.findFragmentById(R.id.fragmentContainer)

    if (fragment == null) {
      fragment = CrimeFragment()
      fm.beginTransaction()
          .add(R.id.fragmentContainer, fragment)
          .commit()
    }
  }
}