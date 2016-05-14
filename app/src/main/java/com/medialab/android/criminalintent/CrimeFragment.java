package com.medialab.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.medialab.android.criminalintent.model.Crime;
import com.medialab.android.criminalintent.model.CrimeLab;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * Created by roneloza on 5/9/16.
 */
public class CrimeFragment extends Fragment {

    private static final String PACKAGE = "com.medialab.android.criminalintent";
    private static final String ARG_CRIME_ID = PACKAGE + ".crime_id";

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    //region - Life Cycle

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);;
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_crime, container, false);
        wireUpAddLitenersWidgets(view);
        return view;
    }

    //endregion

    private void wireUpAddLitenersWidgets(View view) {

        mTitleField = (EditText) view.findViewById(R.id.crime_title);
        mDateButton = (Button) view.findViewById(R.id.crime_date);
        mSolvedCheckBox = (CheckBox) view.findViewById(R.id.crime_solved);

        mTitleField.setText(mCrime.getTitle());
        mDateButton.setEnabled(false);
        mDateButton.setText(mCrime.formatDate());
        mSolvedCheckBox.setChecked(mCrime.isSolved());

        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // setcrime solved property
                mCrime.setSolved(isChecked);
            }
        });

        this.mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                mCrime.setTitle(s.toString());
            }
        });
    }

    public static CrimeFragment newInstace(UUID crimeId) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public static Intent newIntent(Context packageContext, UUID crimeId) {

        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(ARG_CRIME_ID, crimeId);

        return intent;
    }
}
