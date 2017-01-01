package kg.dostek.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by lex on 12/29/16.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragemnt() {
        return new CrimeListFragment();
    }
}
