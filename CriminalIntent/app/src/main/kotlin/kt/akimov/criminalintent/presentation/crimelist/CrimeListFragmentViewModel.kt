package kt.akimov.criminalintent.presentation.crimelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import kt.akimov.criminalintent.data.CrimeLab
import kt.akimov.criminalintent.domain.models.CrimeItem

class CrimeListFragmentViewModel : ViewModel() {

    private var items: MediatorLiveData<List<CrimeItem>> = MediatorLiveData()

    val observableCrimeItems: LiveData<List<CrimeItem>> = items

    fun getCrimes() {
        items.value = CrimeLab.getCrimes()//TODO: temporary solution
    }

//  override fun onCleared() {
//    super.onCleared()
//  }
}