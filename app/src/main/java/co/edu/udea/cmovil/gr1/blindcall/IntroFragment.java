package co.edu.udea.cmovil.gr1.blindcall;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class IntroFragment extends Fragment implements View.OnClickListener {

    Button buttonIntro;

    public IntroFragment() {
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
        View view = inflater.inflate(R.layout.fragment_intro, container, false);

        buttonIntro = (Button) view.findViewById(R.id.buttonIntro);
        buttonIntro.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        getActivity().startActivity(new Intent(getActivity(), LLamadaActivity.class));
    }
}
