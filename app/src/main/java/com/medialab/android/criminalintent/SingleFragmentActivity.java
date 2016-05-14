package com.medialab.android.criminalintent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

public abstract class SingleFragmentActivity extends FragmentActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        this.setFragment();
    }

    //endregion

    private void setFragment() {

        FragmentManager fm = getSupportFragmentManager();

        Fragment fg = fm.findFragmentById(R.id.fragment_container);

        if (fg == null) {

            fg = createFragment();

            fm.beginTransaction()
                    .add(R.id.fragment_container, fg)
                    .commit();
        }
    }
}
