package com.medialab.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    private static final String EXTRA_PACKAGE = "com.medialab.android.criminalintent";
    private static final String EXTRA_CRIME_ID = EXTRA_PACKAGE + ".crime_id";

    @Override
    protected Fragment createFragment() {

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstace(crimeId);
    }

    //region - Life Cycle

    //endregion

    public static Intent newIntent(Context packageContext, UUID crimeId) {

        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);

        return intent;
    }
}
