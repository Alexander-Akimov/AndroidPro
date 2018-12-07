package kt.akimov.criminalintent.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kt.akimov.criminalintent.domain.models.Crime
import java.util.*

class CrimeFragmentViewModel : ViewModel() {

  private var mutableCrime: MutableLiveData<Crime> = MutableLiveData()
  var currentCrime: LiveData<Crime> = mutableCrime
//  var currentCrime: Crime
//    get() = mutableCrime.value

  init {
    mutableCrime.value = Crime()
  }

  fun setTitle(text: String) {
    mutableCrime.value?.title = text
  }

  fun getDate(): Date? {
    return mutableCrime.value?.date
  }

  fun setSolved(checked: Boolean) {
    mutableCrime.value?.isSolved = checked
  }

}