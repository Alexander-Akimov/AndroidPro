package kt.akimov.criminalintent.data

import kt.akimov.criminalintent.domain.models.CrimeItem
import java.util.*

object CrimeLab {
  private var crimeItems: MutableList<CrimeItem> = mutableListOf()
  var readOnlyView: List<CrimeItem> = crimeItems

  init {
    for (i in 0..99)
      crimeItems.add(CrimeItem("CrimeItem #$i", i % 2 == 0, i % 2 != 0))
  }

  fun getCrimes() = readOnlyView

  fun getCrime(id: UUID): CrimeItem? = crimeItems.first { it.id == id }
}