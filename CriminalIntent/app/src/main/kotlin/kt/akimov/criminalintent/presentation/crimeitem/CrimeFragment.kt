package kt.akimov.criminalintent.presentation.crimeitem

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
    //Log.d(TAG, "---2 onCreate")
    super.onCreate(savedInstanceState)

    viewModel = activity?.run {
      ViewModelProviders.of(this).get(CrimeFragmentViewModel::class.java)
    } ?: throw Exception("Invalid Activity")
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    //Log.d(TAG, "---3 onCreateView")
    var v = inflater.inflate(R.layout.fragment_crime, container, false)
    return v;
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

  /*override fun onAttach(context: Context?) {
		super.onAttach(context)
		Log.d(TAG, "---1 OnAttach")
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		Log.d(TAG, "---4 onActivityCreated")
	}

	override fun onStart() {
		super.onStart()
		Log.d(TAG, "---5 onStart")
	}

	override fun onPause() {
		super.onPause()
		Log.d(TAG, "--- onPause")
	}

	override fun onStop() {
		super.onStop()
		Log.d(TAG, "--- onStop")
	}

	override fun onResume() {
		super.onResume()
		Log.d(TAG, "--- onResume")
	}

	override fun onDetach() {
		super.onDetach()
		Log.d(TAG, "--- onDetach")
	}

	override fun onDestroyView() {
		super.onDestroyView()
		Log.d(TAG, "--- onDestroyView")
	}

	override fun onDestroy() {
		super.onDestroy()
		Log.d(TAG, "--- onDestroy")
	}

	companion object {
		private var TAG: String = CrimeFragment::class.java.simpleName
	}*/
}