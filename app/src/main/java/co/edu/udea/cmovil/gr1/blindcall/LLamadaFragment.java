package co.edu.udea.cmovil.gr1.blindcall;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.sql.SQLOutput;

public class LLamadaFragment extends Fragment implements View.OnClickListener {

    Button buttonEndCall;
    Button buttonLocation;
    Button button3;

    public LLamadaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_llamada, container, false);
        buttonEndCall = (Button) view.findViewById(R.id.buttonEndCall);
        buttonLocation = (Button) view.findViewById(R.id.buttonLocation);

        buttonEndCall.setOnClickListener(this);
        buttonLocation.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        System.out.println(v.getId());
        if(v.getId() == buttonEndCall.getId()){
            getActivity().startActivity(new Intent(getActivity(), IntroActivity.class));
        } else if (v.getId() == buttonLocation.getId()){
            Toast.makeText(getActivity(), "Envio posición", Toast.LENGTH_LONG).show();

        }
    }
}
