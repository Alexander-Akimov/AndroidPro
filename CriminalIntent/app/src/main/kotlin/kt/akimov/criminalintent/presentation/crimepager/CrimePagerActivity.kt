package kt.akimov.criminalintent.presentation.crimepager

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import kg.dostek.criminalintent.R
import kt.akimov.criminalintent.data.CrimeLab
import kt.akimov.criminalintent.domain.models.CrimeItem
import kt.akimov.criminalintent.presentation.crimeitem.CrimeFragment
import java.util.*


class CrimePagerActivity : AppCompatActivity() {

    //lateinit var viewPager: ViewPager2
    lateinit var viewPager: ViewPager
    //private lateinit var pagerAdapter: FragmentStateAdapter
    private lateinit var viewModel: CrimePagerViewModel
    private lateinit var crimes: List<CrimeItem>

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

        val crimeId = intent?.getSerializableExtra(EXTRA_CRIME_ID) as UUID

        viewPager = findViewById(R.id.crime_view_pager)
        crimes = CrimeLab.getCrimes()
        //viewPager.setPageTransformer(ZoomOutPageTransformer())
        //mViewPager.setOffscreenPageLimit();

        val fragmentManager = supportFragmentManager
        // var localAdapter = CrimePagerAdapter(this)
        var localAdapter = object : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

            override fun getItem(position: Int): Fragment {
                var crime = crimes[position]

                return CrimeFragment.newInstance(crime.id)
            }

            override fun getCount(): Int {
                return crimes.size
            }
        }
        viewPager.adapter = localAdapter
        //viewPager.addOnPageChangeListener(ViewPager.OnPageChangeListener{})

        viewModel = ViewModelProviders.of(this).get(CrimePagerViewModel::class.java)

        // setupViewPager()
        // subscribeObservers()
        // loadData()

        for (i in crimes.indices) {
            if (crimes[i].id == crimeId) {
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

        //CrimeFragmentPagerAdapter(this)

        var localAdapter = CrimePagerAdapter(this)


        /*pagerAdapter = object : FragmentStateAdapter(this) {

            override fun getItemCount(): Int {
                return crimes.size
            }

            override fun createFragment(position: Int): Fragment {
                var crime = crimes[position]

                return CrimeFragment.newInstance(crime.id)
            }
        }*/

        // viewPager.adapter = localAdapter
    }

    private inner class CrimePagerAdapter(fa: AppCompatActivity) : FragmentStateAdapter(this) {

        override fun getItemCount(): Int {
            return crimes.size
        }

        override fun createFragment(position: Int): Fragment {
            var crime = crimes[position]

            return CrimeFragment.newInstance(crime.id)
        }
    }
}