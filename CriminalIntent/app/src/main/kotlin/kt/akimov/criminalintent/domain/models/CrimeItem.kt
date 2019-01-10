package kt.akimov.criminalintent.domain.models

import java.util.*
import java.util.UUID.randomUUID


//data class Crimew(
//    private var mID: UUID = UUID.randomUUID(),
//    private var mTitle: String,
//    private var mDate: Date = Date(),
//    private var mSolved: Boolean)

data class CrimeItem(var title: String? = null,
                     var isSolved: Boolean = false,
                     var requiresPolice: Boolean = false) {

  var id: UUID = UUID.randomUUID()
  var date: Date? = null

  init {
    date = Date()
  }
}