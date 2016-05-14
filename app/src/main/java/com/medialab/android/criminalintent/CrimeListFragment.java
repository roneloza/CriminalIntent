package com.medialab.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.medialab.android.criminalintent.model.Crime;
import com.medialab.android.criminalintent.model.CrimeLab;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by roneloza on 5/12/16.
 */
public class CrimeListFragment extends Fragment implements View.OnClickListener {

    private class CrimeHolder extends RecyclerView.ViewHolder {

        private int mRow;

        private TextView mTitleTextiew;
        private TextView mDateTextiew;
        private CheckBox mSolvedCheckBox;

        public CrimeHolder(View itemView) {

            super(itemView);
            mTitleTextiew = (TextView) itemView.findViewById(R.id.list_item_crime_crime_title_text_view);
            mDateTextiew = (TextView) itemView.findViewById(R.id.list_item_crime_crime_date_text_view);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }

        public void bindCrime(int position, Crime crime) {

            mRow = position;
            mTitleTextiew.setText(crime.getTitle());
            mDateTextiew.setText(crime.formatDate());
            mSolvedCheckBox.setChecked(crime.isSolved());
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        //region - Members

        private Map<Object, CrimeHolder> mHolders;
        private List<Crime> mCrimes;

        public List<Crime> getCrimes() {
            return mCrimes;
        }

        public Map<Object, CrimeHolder> getHolders() {
            return mHolders;
        }
        //endregion

        public CrimeAdapter(List<Crime> crimes) {

            mCrimes = crimes;
            mHolders = new HashMap<>();
        }

        @Override
        public int getItemCount() {

            return mCrimes.size();
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false);

            CrimeHolder crimeHolder = new CrimeHolder(view);

            mHolders.put(view.hashCode(), crimeHolder);

            view.setOnClickListener(CrimeListFragment.this);

            return crimeHolder;
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {

            Crime crime = mCrimes.get(position);
            holder.bindCrime(position, crime);
        }
    }

    //region - Members

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    //endregion

    //region - UI

    @Override
    public void onClick(View v) {

        CrimeHolder crimeHolder = mAdapter.getHolders().get(v.hashCode());

        if (crimeHolder != null) {

            Crime crime = mAdapter.getCrimes().get(crimeHolder.mRow);

            Intent crimeIntent = CrimeActivity.newIntent(getActivity(), crime.getId());
            startActivity(crimeIntent);
        }
    }

    //endregion
    //region Life Cycle

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        wireUpWidgets(view);
        updateUI();

        return view;
    }

    //endregion

    private void wireUpWidgets(View view) {

        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void updateUI() {

        CrimeLab crimeLab = CrimeLab.get(getActivity());
        mAdapter = new CrimeAdapter(crimeLab.getCrimes());
        mCrimeRecyclerView.setAdapter(mAdapter);
    }
}
