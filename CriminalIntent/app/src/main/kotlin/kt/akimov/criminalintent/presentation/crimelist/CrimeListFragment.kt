package kt.akimov.criminalintent.presentation.crimelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.dostek.criminalintent.R
import kotlinx.android.synthetic.main.fragment_crime_list.*
import kt.akimov.criminalintent.domain.models.CrimeItem

class CrimeListFragment : Fragment() {

  private var crimeAdapter: CrimeAdapter

  init {
    crimeAdapter = CrimeAdapter()
  }

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
    loadData()
  }

  private fun loadData() {
    crimeAdapter.setItems(viewModel.getCrimes())
  }

  private var onItemClick: (CrimeItem) -> Unit = {
    Toast.makeText(activity, "${it.title} clicked!", Toast.LENGTH_LONG).show()
  }

  private fun setupCrimeRecyclerView() {
    crime_recycler_view.adapter = crimeAdapter
    crime_recycler_view.setHasFixedSize(true)
    crime_recycler_view.layoutManager = LinearLayoutManager(activity)

    crimeAdapter.onItemClick = this.onItemClick
  }
}