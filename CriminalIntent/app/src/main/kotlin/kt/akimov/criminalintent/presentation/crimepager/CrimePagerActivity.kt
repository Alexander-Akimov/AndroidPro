package kt.akimov.criminalintent.presentation.crimepager

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import kg.dostek.criminalintent.R
import kt.akimov.criminalintent.data.CrimeLab
import kt.akimov.criminalintent.domain.models.CrimeItem
import kt.akimov.criminalintent.presentation.crimeitem.CrimeFragment
import java.util.*


class CrimePagerActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager2
    private lateinit var pagerAdapter: FragmentStateAdapter
    private lateinit var viewModel: CrimePagerViewModel

    companion object {
        private const val EXTRA_CRIME_ID = "kt.akimov.criminalintent.crime_id"
        fun newIntent(packageContext: Context?, crimeId: UUID): Intent {
            val intent = Intent(packageContext, CrimePagerActivity::class.java)
            intent.putExtra(EXTRA_CRIME_ID, crimeId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crime_pager)

        viewPager = findViewById(R.id.crime_view_pager)

        val crimeId = intent?.getSerializableExtra(EXTRA_CRIME_ID) as UUID

        viewModel = ViewModelProviders.of(this).get(CrimePagerViewModel::class.java)

        setupViewPager()
        // subscribeObservers()
        // loadData()


        for (i in CrimeLab.getCrimes().indices) {
            if (CrimeLab.getCrimes()[i].id == crimeId) {
                viewPager.currentItem = i
                break
            }
        }
    }

    private fun loadData() {
        viewModel.getCrimes()
    }

    private fun subscribeObservers() {
        val crimeObserver = Observer<List<CrimeItem>> {
          //  pagerAdapter.setItems(it)
        }
        viewModel.observableCrimeItems.observe(this, crimeObserver)
    }

    private fun setupViewPager() {
        var crimes = CrimeLab.getCrimes()

        pagerAdapter = //CrimeFragmentPagerAdapter(this)

                object : FragmentStateAdapter(this) {

                    override fun getItemCount(): Int {
                        return crimes.size
                    }

                    override fun createFragment(position: Int): Fragment {
                        var crime = crimes[position]

                        return CrimeFragment.newInstance(crime.id)
                    }
                }

        viewPager.adapter = pagerAdapter
    }
}