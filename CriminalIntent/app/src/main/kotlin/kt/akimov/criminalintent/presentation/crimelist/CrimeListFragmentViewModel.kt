package kt.akimov.criminalintent.presentation.crimelist

import androidx.lifecycle.ViewModel
import kt.akimov.criminalintent.data.CrimeLab
import kt.akimov.criminalintent.domain.models.CrimeItem

class CrimeListFragmentViewModel : ViewModel() {

  fun getCrimes(): List<CrimeItem> {
    return CrimeLab.getCrimes()
  }

//  override fun onCleared() {
//    super.onCleared()
//  }
}