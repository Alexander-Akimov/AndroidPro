package kt.akimov.criminalintent.presentation.crimelist

import androidx.fragment.app.Fragment
import kt.akimov.criminalintent.presentation.SingleFragmentActivity

class CrimeListActivity : SingleFragmentActivity() {
  override fun createFragment() = CrimeListFragment()
}