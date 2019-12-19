package kt.akimov.criminalintent.presentation.crimeitem

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import kt.akimov.criminalintent.presentation.SingleFragmentActivity
import java.util.*


class CrimeActivity : SingleFragmentActivity() {

    override fun createFragment() : CrimeFragment {
        val crimeId = intent?.getSerializableExtra(EXTRA_CRIME_ID) as UUID
        return CrimeFragment.newInstance(crimeId)
    }

    companion object {
        private val EXTRA_CRIME_ID = "kt.akimov.criminalintent.crime_id"
        fun newIntent(packageContext: Context?, crimeId: UUID): Intent {
            val intent = Intent(packageContext, CrimeActivity::class.java)
            intent.putExtra(EXTRA_CRIME_ID, crimeId)
            return intent
        }
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		Log.d(TAG, "onCreate")
	}

	override fun onStart() {
		super.onStart()
		Log.d(TAG, "onStart")
	}

	override fun onResume() {
		super.onResume()
		Log.d(TAG, "onResume")
	}

	override fun onPause() {
		super.onPause()
		Log.d(TAG, "onPause")
	}

	override fun onStop() {
		super.onStop()
		Log.d(TAG, "onStop")
	}

	override fun onDestroy() {
		super.onDestroy()
		Log.d(TAG, "onDestroy")
	}

	override fun onRestart() {
		super.onRestart()
		Log.d(TAG, "onRestart")
	}

	companion object {
		private var TAG: String = CrimeActivity::class.java.simpleName
	}*/
}