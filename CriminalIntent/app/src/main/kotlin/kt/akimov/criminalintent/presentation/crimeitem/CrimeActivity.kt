package kt.akimov.criminalintent.presentation.crimeitem

import android.content.Context
import android.os.Bundle
import android.util.Log
import kt.akimov.criminalintent.presentation.SingleFragmentActivity


class CrimeActivity : SingleFragmentActivity() {
	override fun createFragment() = CrimeFragment()

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