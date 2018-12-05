package kg.dostek.criminalintent;

import androidx.fragment.app.Fragment;

/**
 * Created by lex on 12/29/16.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
