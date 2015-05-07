package dz.esi.tdm.mainmodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class GoogleMapFragment extends Fragment {

    static final LatLng HAMBURG = new LatLng(38.331, -0.553);
    private GoogleMap map;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        /*map = ((SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.carte))
                .getMap();

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));

        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);*/

    }
}