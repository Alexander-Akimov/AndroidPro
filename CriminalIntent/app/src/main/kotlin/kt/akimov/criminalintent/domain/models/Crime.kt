package kt.akimov.criminalintent.domain.models

import java.util.*
import java.util.UUID.randomUUID


//data class Crimew(
//    private var mID: UUID = UUID.randomUUID(),
//    private var mTitle: String,
//    private var mDate: Date = Date(),
//    private var mSolved: Boolean)

data class Crime(var title: String? = null,
                 var isSolved: Boolean = false) {

  var id: UUID = UUID.randomUUID()
  var date: Date? = null

  init {
    date = Date()
  }
}