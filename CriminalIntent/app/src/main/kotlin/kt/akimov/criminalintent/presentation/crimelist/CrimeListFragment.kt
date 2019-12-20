package kt.akimov.criminalintent.presentation.crimelist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.use.dagger.util.VerticalSpacingItemDecoration
import kg.dostek.criminalintent.R
import kotlinx.android.synthetic.main.fragment_crime_list.*
import kt.akimov.criminalintent.domain.models.CrimeItem
import kt.akimov.criminalintent.presentation.crimeitem.CrimeActivity

class CrimeListFragment : Fragment() {

    private var crimeAdapter = CrimeAdapter()

    private lateinit var viewModel: CrimeListFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_crime_list, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(CrimeListFragmentViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        setupCrimeRecyclerView()
        subscribeObservers()
        loadData()
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.currentPosition != -1)
            crimeAdapter.notifyItemChanged(viewModel.currentPosition)
        else
            crimeAdapter.notifyDataSetChanged()
    }

    private fun loadData() {
        viewModel.getCrimes()
    }

    private fun subscribeObservers() {
        val crimeObserver = Observer<List<CrimeItem>> {
            crimeAdapter.setItems(it)
        }
        viewModel.observableCrimeItems.observe(viewLifecycleOwner, crimeObserver)
    }

    private var onItemClick: (CrimeItem, Int) -> Unit = { crimeItem, position ->
        // Toast.makeText(activity, "${it.title} clicked!", Toast.LENGTH_SHORT).show()

        viewModel.setPosition(position)

        val intent = CrimeActivity.newIntent(activity?.baseContext, crimeItem.id)

        startActivity(intent)
    }


    private fun setupCrimeRecyclerView() {
        crimeAdapter.setHasStableIds(false)
        crimeAdapter.onItemClick = this.onItemClick
        crime_recycler_view.apply {
            setHasFixedSize(true)
            //  setItemViewCacheSize(20)
//        setDrawingCacheEnabled(true)
//        setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH)
            layoutManager = LinearLayoutManager(activity)

            val itemDecoration =
                    VerticalSpacingItemDecoration(15)// could also provide this as dependency
            addItemDecoration(itemDecoration)

            adapter = crimeAdapter
        }
        //isNestedScrollingEnabled = false
    }
}