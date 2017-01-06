package kg.dostek.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.UUID;


public class CrimeActivity extends SingleFragmentActivity {
    private static final String EXTRA_CRIME_ID = "kg.dostek.criminalintent.crime_id";
    public static final String EXTRA_VIEW_POSITION = "kg.dostek.criminalintent.view_position";

    public static Intent newIntent(Context packageContext, UUID crimeId, int position) {
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_VIEW_POSITION, position);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected Fragment createFragemnt() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);
        int position = getIntent().getIntExtra(CrimeActivity.EXTRA_VIEW_POSITION, -1);

        Intent intent = new Intent();
        intent.putExtra(EXTRA_VIEW_POSITION, position);
        setResult(RESULT_OK, intent);

        return CrimeFragment.newInstance(crimeId);
    }

    public static int getPosition(Intent data) {
        return data.getIntExtra(EXTRA_VIEW_POSITION, -1);
    }
}
