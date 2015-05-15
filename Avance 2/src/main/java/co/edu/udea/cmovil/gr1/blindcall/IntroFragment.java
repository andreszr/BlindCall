package co.edu.udea.cmovil.gr1.blindcall;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class IntroFragment extends Fragment {
    public String phone;
    Button buttonIntro;

    public IntroFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro, container, false);

        //buttonIntro.setOnClickListener(this);
        return view;
    }

    public void nuevaLLamada(String phone){
        Toast.makeText(getActivity(), "LLamando...", Toast.LENGTH_SHORT).show();
        //String url = "tel:3122782923";
        //Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));

        PhoneCallListener phoneListener = new PhoneCallListener();
        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneListener,PhoneStateListener.LISTEN_CALL_STATE);

        try{
            Intent intent =new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:3122782923"));
            startActivity(intent);
        }
        catch(ActivityNotFoundException e){
            Toast toast = Toast.makeText(getActivity(), "No puedes hacer la llamada", Toast.LENGTH_SHORT);
            toast.show();
        }
        //getActivity().startActivity(new Intent(getActivity(), LLamadaActivity.class));
    }


   /* @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), "LLamando...", Toast.LENGTH_SHORT).show();
        //String url = "tel:3122782923";
        //Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));

        PhoneCallListener phoneListener = new PhoneCallListener();
        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneListener,PhoneStateListener.LISTEN_CALL_STATE);

        try{
            Intent intent =new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:3122782923"));
            startActivity(intent);
        }
        catch(ActivityNotFoundException e){
            Toast toast = Toast.makeText(getActivity(), "No puedes hacer la llamada", Toast.LENGTH_SHORT);
            toast.show();
        }

        //getActivity().startActivity(new Intent(getActivity(), LLamadaActivity.class));
    }
*/
    //monitor phone call activities
    private class PhoneCallListener extends PhoneStateListener {

        private boolean isPhoneCalling = false;
        String LOG_TAG = "LOGGING 123";

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            if (TelephonyManager.CALL_STATE_RINGING == state) {
                // phone ringing
                Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
            }

            if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
                // active
                Log.i(LOG_TAG, "OFFHOOK");
                isPhoneCalling = true;
            }

            if (TelephonyManager.CALL_STATE_IDLE == state) {
                // run when class initial and phone call ended,
                // need detect flag from CALL_STATE_OFFHOOK
                Log.i(LOG_TAG, "IDLE");

                if (isPhoneCalling) {

                    Log.i(LOG_TAG, "restart app");

                    // restart app
                    Intent i = getActivity().getBaseContext().getPackageManager().getLaunchIntentForPackage(getActivity().getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);

                    isPhoneCalling = false;
                }

            }
        }
    }
}
