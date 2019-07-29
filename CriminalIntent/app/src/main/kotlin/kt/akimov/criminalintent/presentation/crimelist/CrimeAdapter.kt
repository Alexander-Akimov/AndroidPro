package kt.akimov.criminalintent.presentation.crimelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.dostek.criminalintent.R
import kotlinx.android.synthetic.main.list_item_crime.view.*
import kt.akimov.criminalintent.domain.models.CrimeItem

class CrimeAdapter : RecyclerView.Adapter<CrimeAdapter.CrimeBaseViewHolder>() {

  var onItemClick: (CrimeItem) -> Unit = {}
  private var crimesList: List<CrimeItem> = emptyList()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeBaseViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)

//    val itemCrimeView =
//        when (viewType) {
//          CrimeViewType.RequiresPolice.value -> layoutInflater.inflate(R.layout.list_item_crime_police, parent, false)
//          else -> layoutInflater.inflate(R.layout.list_item_crime, parent, false)
//        }
//    return CrimeViewHolder(itemCrimeView).listen { position, type -> onItemClick.invoke(crimesList[position]) }

    return when (viewType) {
      CrimeViewType.RequiresPolice.value -> CrimeViewHolder(
          layoutInflater.inflate(R.layout.list_item_crime_police, parent, false)
      ).listen { position, _ -> onItemClick.invoke(crimesList[position]) }
      else -> CrimeRequiresPoliceViewHolder(
          layoutInflater.inflate(R.layout.list_item_crime, parent, false)
      ).listen { position, _ -> onItemClick.invoke(crimesList[position]) }
    }
  }

  fun setItems(items: List<CrimeItem>) {
    //crimesList = null
    crimesList = items
  }

  override fun getItemCount(): Int {
    return crimesList.size
  }

  override fun onBindViewHolder(holder: CrimeBaseViewHolder, position: Int) {
    val crime = crimesList[position]
//holder.itemViewType
    holder.bindCrime(crime)//we can call overridden version of this method
  }

  override fun getItemViewType(position: Int): Int {
    val crime = crimesList[position]

    return if (crime.requiresPolice) CrimeViewType.RequiresPolice.value else CrimeViewType.Default.value
  }

  private fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
      event.invoke(adapterPosition, itemViewType)
    }
    return this
  }

  open class CrimeBaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindCrime(crimeItem: CrimeItem) {
      itemView.list_item_crime_title_text_view.text = crimeItem.title
      itemView.list_item_crime_date_text_view.text = crimeItem.date.toString()
      itemView.list_item_crime_solved_check_box.isChecked = crimeItem.isSolved
    }
  }

  class CrimeViewHolder(view: View) : CrimeBaseViewHolder(view) {}

  class CrimeRequiresPoliceViewHolder(view: View) : CrimeBaseViewHolder(view) {}

  enum class CrimeViewType(val value: Int) {
    Default(0),
    RequiresPolice(1)
  }
}