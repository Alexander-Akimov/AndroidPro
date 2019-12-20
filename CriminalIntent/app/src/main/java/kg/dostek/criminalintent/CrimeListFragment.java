package kg.dostek.criminalintent;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by lex on 12/30/16.
 */

public class CrimeListFragment extends Fragment {

    private static final int REQUEST_CRIME = 1;
    //ViewGroup
    //LinearLayout
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private int mPosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        ItemViewClick handle = (Crime cr, int position) -> {
            //Toast.makeText(getActivity(), mCrime.getTitle() + " clicked!",  Toast.LENGTH_SHORT).show();
            Intent intent = CrimeActivity.newIntent(getActivity(), cr.getId(), position);
            startActivityForResult(intent, REQUEST_CRIME);

//            Intent intent = CrimePagerActivity.newIntent(getActivity(), cr.getId());
//            startActivity(intent);
        };
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        if (mAdapter == null) {

            mAdapter = new CrimeAdapter(crimes, handle);
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else {

            mAdapter.notifyItemChanged(mPosition);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CRIME && resultCode == RESULT_OK) {
            mPosition = CrimeActivity.getPosition(data);// Обработка результата
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeViewHolder> {
        private List<Crime> mCrimes;
        private final ItemViewClick mItemClick;

        public CrimeAdapter(List<Crime> crimes, ItemViewClick itemClick) {
            mCrimes = crimes;
            mItemClick = itemClick;
        }

        @Override
        public CrimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false);

            CrimeViewHolder vh = new CrimeViewHolder(view);
            vh.itemView.setOnClickListener((View v) ->
                    mItemClick.handleClick(mCrimes.get(vh.getAdapterPosition()), vh.getAdapterPosition()));
            return vh;
        }

        @Override
        public void onBindViewHolder(CrimeViewHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }

    private class CrimeViewHolder extends RecyclerView.ViewHolder {
        private Crime mCrime;

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        public CrimeViewHolder(View itemView) {
            super(itemView);

            mTitleTextView = (TextView) itemView.findViewById(R.id.title);
            mDateTextView = (TextView) itemView.findViewById(R.id.date);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.solved);
        }

        public void bindCrime(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());
        }
    }
}
