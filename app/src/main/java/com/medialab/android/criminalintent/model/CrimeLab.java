package com.medialab.android.criminalintent.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by roneloza on 5/12/16.
 */
public class CrimeLab {

    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;

    private CrimeLab(Context context) {

        this.mCrimes = new ArrayList<Crime>();

        for (int i = 0; i < 20; i++) {

            Crime crime = new Crime();
            crime.setTitle("Crime # " + i);
            crime.setSolved(i % 2 == 0);
            this.mCrimes.add(crime);
        }
    }

    public static CrimeLab get(Context context) {

        if (sCrimeLab == null) {

            sCrimeLab = new CrimeLab(context);
        }

        return sCrimeLab;
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID uid) {

        for (Crime crime: this.mCrimes) {

            if (crime.getId().equals(uid))
                return crime;
        }
        
        return null;
    }
}
