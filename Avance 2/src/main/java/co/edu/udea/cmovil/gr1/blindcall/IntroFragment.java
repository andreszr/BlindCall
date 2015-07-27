package co.edu.udea.cmovil.gr1.blindcall;


import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class IntroFragment extends Fragment{

    private TTSManager ttsManager = null;

    public IntroFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ttsManager = new TTSManager();
        ttsManager.init(getActivity());

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bienvenido();
            }
        }, 2000);
    }

    public void bienvenido(){
        ttsManager.initQueue("Bienvenido, acerca una tarjeta para hacer una llamada");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro, container, false);
        ttsManager.initQueue("Bienvenido");

        return view;
    }


    public void nuevaLLamada(final String phone){
        //String url = "tel:3122782923";
        //Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));

        final String arr[] = phone.split("");

        ttsManager.initQueue("LLamando al teléfono " + arr[1] + arr[2] + arr[3] + " " +
                 arr[4] + arr[5] + arr[6] + " " + arr[7] + arr[8] + " " +
                 arr[9] + arr[10] + " ");

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                PhoneCallListener phoneListener = new PhoneCallListener();
                TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
                telephonyManager.listen(phoneListener,PhoneStateListener.LISTEN_CALL_STATE);

                try{
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + arr[1] + arr[2] + arr[3] +
                            arr[4] + arr[5] + arr[6] + arr[7] + arr[8] +
                            arr[9] + arr[10]));
                    startActivity(intent);
                }
                catch(ActivityNotFoundException e){
                    Toast.makeText(getActivity(), "No puedes hacer la llamada", Toast.LENGTH_SHORT).show();
                    ttsManager.initQueue("No puedes hacer la llamada");
                }
            }
        }, 9000);

    }

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
                    Toast.makeText(getActivity(), "restart app", Toast.LENGTH_SHORT).show();
                    ttsManager.initQueue("Termino, la llamada");
                    // restart app
                    Intent i = getActivity().getBaseContext().getPackageManager().getLaunchIntentForPackage(getActivity().getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    isPhoneCalling = false;
                }
            }
        }
    }

    /**
     * Releases the resources used by the TextToSpeech engine.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        ttsManager.shutDown();
    }

    public void check(final String phone){
        ttsManager.initQueue("LLamando al teléfono: " + phone);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                check(phone);
            }
        }, 700000);
    }
}
