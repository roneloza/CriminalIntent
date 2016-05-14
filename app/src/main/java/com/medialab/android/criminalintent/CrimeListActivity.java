package com.medialab.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by roneloza on 5/12/16.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {

        return new CrimeListFragment();
    }
}
