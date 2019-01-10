package kt.akimov.criminalintent.presentation.crimeitem

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kg.dostek.criminalintent.R
import android.widget.CompoundButton.OnCheckedChangeListener
import kotlinx.android.synthetic.main.fragment_crime.*

class CrimeFragment : Fragment() {

  private lateinit var viewModel: CrimeFragmentViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // viewModel = ViewModelProviders.of(this).get(CrimeFragmentViewModel::class.java)
    viewModel = activity?.run {
      ViewModelProviders.of(this).get(CrimeFragmentViewModel::class.java)
    } ?: throw Exception("Invalid Activity")
    //    val crime = CrimeItem("", Date(123123))
    //    crime.id
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val v = inflater.inflate(R.layout.fragment_crime, container, false)
    return v
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    crimeTitleEditText.addTextChangedListener(object : TextWatcher {
      override fun afterTextChanged(s: Editable?) {}

      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        viewModel.setTitle(s.toString())
      }
    })

    crimeDateBtn.text = viewModel.getDate()?.toString() ?: ""
    crimeDateBtn.isEnabled = false

    crimeSolvedChBx.setOnCheckedChangeListener(object : OnCheckedChangeListener {
      override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        viewModel.setSolved(isChecked)
      }
    })
  }
}