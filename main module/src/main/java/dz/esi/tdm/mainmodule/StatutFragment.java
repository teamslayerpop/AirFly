package dz.esi.tdm.mainmodule;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StatutFragment extends Fragment {

    public static StatutFragment newInstance(int page) {
        StatutFragment fragment = new StatutFragment();
        return fragment;
    }

    public StatutFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statut, container, false);
        return view;
    }
}
