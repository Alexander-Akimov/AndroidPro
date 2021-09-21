package kt.akimov.criminalintent.presentation.crimeitem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kt.akimov.criminalintent.data.CrimeLab
import kt.akimov.criminalintent.domain.models.CrimeItem
import java.util.*

class CrimeFragmentViewModel : ViewModel() {

    private var mutableCrimeItem: MutableLiveData<CrimeItem> = MutableLiveData()
    var currentCrimeItem: LiveData<CrimeItem> = mutableCrimeItem
//  var currentCrimeItem: CrimeItem
//    get() = mutableCrimeItem.value

    init {
        mutableCrimeItem.value = CrimeItem()
    }

    fun setTitle(text: String) {
        mutableCrimeItem.value?.title = text
    }

    fun getDate(): Date? {
        return mutableCrimeItem.value?.date
    }

    fun setSolved(checked: Boolean) {
        mutableCrimeItem.value?.isSolved = checked
    }

    fun getData(crime: CrimeItem?) {
        mutableCrimeItem
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getCrimeItemById(crimeId: UUID?) {
        if (crimeId == null) return
        mutableCrimeItem.value = CrimeLab.getCrime(crimeId)
    }

}