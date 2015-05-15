package co.edu.udea.cmovil.gr1.blindcall;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class LLamadaActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            LLamadaFragment fragment = new LLamadaFragment(); //
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(android.R.id.content, fragment, fragment.getClass().getSimpleName());
            fragmentTransaction.commit();
        }

    }
}
