package kt.akimov.criminalintent.presentation.crimepager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import kt.akimov.criminalintent.domain.models.CrimeItem

import kt.akimov.criminalintent.presentation.crimeitem.CrimeFragment

class CrimeFragmentPagerAdapter(fragmentActivity: FragmentActivity):
        FragmentStateAdapter(fragmentActivity)  {

    private var crimesList: List<CrimeItem> = emptyList()

    fun setItems(items: List<CrimeItem>) {
        crimesList = items
        //notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return crimesList.size
    }

    override fun createFragment(position: Int): Fragment {
        var crime = crimesList[position]

        return CrimeFragment.newInstance(crime.id)
    }
}