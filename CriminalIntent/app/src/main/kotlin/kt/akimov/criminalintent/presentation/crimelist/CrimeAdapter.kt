package kt.akimov.criminalintent.presentation.crimelist

//import kotlinx.android.synthetic.main.list_item_crime.view.date
//import kotlinx.android.synthetic.main.list_item_crime.view.solved
//import kotlinx.android.synthetic.main.list_item_crime.view.title
//import kotlinx.android.synthetic.main.list_item_crime_police.view.*

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kg.dostek.criminalintent.R
import kt.akimov.criminalintent.domain.models.CrimeItem


class CrimeAdapter : RecyclerView.Adapter<CrimeAdapter.CrimeBaseViewHolder>() {

    var onItemClick: (CrimeItem, Int) -> Unit = { _, _ -> }
    private var crimesList: List<CrimeItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeBaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

//    val itemCrimeView =
//        when (viewType) {
//          CrimeViewType.RequiresPolice.value -> layoutInflater.inflate(R.layout.list_item_crime_police, parent, false)
//          else -> layoutInflater.inflate(R.layout.list_item_crime, parent, false)
//        }
//    return CrimeViewHolder(itemCrimeView).listen { position, type -> onItemClick.invoke(crimesList[position]) }

/*  return CrimeViewHolder(
		  layoutInflater.inflate(R.layout.list_item_crime, parent, false))
*/
        return when (viewType) {
            CrimeViewType.RequiresPolice.value -> CrimeRequiresPoliceViewHolder(
                    layoutInflater.inflate(R.layout.list_item_crime_police_constr_layout, parent, false))
            else -> CrimeViewHolder(
                    layoutInflater.inflate(R.layout.list_item_crime_constr_layout, parent, false))
        }.listen { position, _ -> onItemClick.invoke(crimesList[position], position) }
    }

    fun setItems(items: List<CrimeItem>) {
        //crimesList = null
        crimesList = items
        notifyDataSetChanged()
    }

    override fun getItemCount() = crimesList.size

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

    abstract class CrimeBaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindCrime(crimeItem: CrimeItem) {
            itemView.findViewById<TextView>(R.id.title).text = crimeItem.title
            itemView.findViewById<TextView>(R.id.date).text = crimeItem.dateStr
            itemView.findViewById<CheckBox>(R.id.solved).isChecked = crimeItem.isSolved
        }
    }

    class CrimeViewHolder(view: View) : CrimeBaseViewHolder(view) {}

    class CrimeRequiresPoliceViewHolder : CrimeBaseViewHolder {
        constructor(view: View) : super(view) {
            //Picasso.get().load(R.drawable.ic_call_police).into(itemView.police_img)
        }
    }

    enum class CrimeViewType(val value: Int) {
        Default(0),
        RequiresPolice(1)
    }
}