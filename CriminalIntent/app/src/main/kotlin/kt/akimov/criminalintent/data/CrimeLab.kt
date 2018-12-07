package kt.akimov.criminalintent.data

import kt.akimov.criminalintent.domain.models.Crime
import java.util.*

object CrimeLab {
  private var crimes: MutableList<Crime> = mutableListOf()
  var readOnlyView: List<Crime> = crimes

  init {
    for (i in 0..99)
      crimes.add(Crime("Crime #$i", i % 2 == 0))
  }

  fun getCrimes() = readOnlyView

  fun getCrime(id: UUID): Crime? = crimes.first { it.id == id }
}