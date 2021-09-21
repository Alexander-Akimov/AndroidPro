package kg.dostek.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Alexander on 29.12.2016.
 */

public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;
    //HashMap mCrimes = new HashMap<UUID, Crime>();

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
//        mCrimes = new HashMap<UUID, Crime>();
        mCrimes = new ArrayList<Crime>();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("CrimeItem #" + i);
            crime.setSolved(i % 2 == 0);// для каждого объекта
            //mCrimes.put(crime.getId(), crime);
            mCrimes.add(crime);
        }
    }

    public List<Crime> getCrimes() {
//        return new ArrayList<Crime>(mCrimes.values());
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        //return (Crime) mCrimes.get(id);
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }
}
