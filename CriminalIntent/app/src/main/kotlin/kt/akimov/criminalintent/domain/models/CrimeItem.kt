package kt.akimov.criminalintent.domain.models

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import java.util.UUID.randomUUID


//data class Crime(
//    private var mID: UUID = UUID.randomUUID(),
//    private var mTitle: String,
//    private var mDate: Date = Date(),
//    private var mSolved: Boolean)

data class CrimeItem(var title: String? = null,
                     var isSolved: Boolean = false,
                     var requiresPolice: Boolean = false) {

    var id: UUID = randomUUID()
    var date: Date? = null

    var dateStr: String? = ""
        get() = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date)
        private set

    init {
        date = Date()
    }
}