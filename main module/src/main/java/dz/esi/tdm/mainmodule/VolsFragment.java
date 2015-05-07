package dz.esi.tdm.mainmodule;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class VolsFragment extends Fragment implements MaterialTabListener {

    private MaterialTabHost tabHost;
    private ViewPager pager;
    private ViewPagerAdapter adapter = null;

    public VolsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_vols, container, false);
        tabHost = (MaterialTabHost) layout.findViewById(R.id.tab_vols);
        pager = (ViewPager) layout.findViewById(R.id.pager_vols);

        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager()
                , getResources().getStringArray(R.array.tab_option_vol), false);

        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
            }
        });
        for (int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(adapter.getPageTitle(i))
                            .setTabListener(this)
            );
        }

        return layout;
    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {
    }

    @Override
    public void onTabUnselected(MaterialTab tab) {
    }

    public static class VolTypeFragment extends Fragment {
        private ListView listVol;

        public static VolTypeFragment getInstance(Vol[] vols) {
            VolTypeFragment myFragment = new VolTypeFragment();
            Bundle args = new Bundle();
            args.putParcelableArray("vols", vols);
            myFragment.setArguments(args);
            return myFragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View layout = inflater.inflate(R.layout.fragment_vols_type, container, false);
            listVol = (ListView) layout.findViewById(R.id.list_vol);

            Bundle bundle = getArguments();
            if (bundle != null) {
                Vol[] vols = (Vol[]) bundle.getParcelableArray("vols");
                VolAdapter adapter = new VolAdapter(getActivity(), vols);
                listVol.setAdapter(adapter);
                listVol.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), DetailActivity.class);
                        startActivity(intent);
                    }
                });
            }
            return layout;
        }

        private class VolAdapter extends ArrayAdapter<Vol> {

            private Context context;

            public VolAdapter(Context context, Vol[] values) {
                super(context, R.layout.list_vol_item, values);
                this.context = context;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView == null) {
                    LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.list_vol_item, null);
                }
                Vol vol = getItem(position);
                TextView txView = (TextView) convertView.findViewById(R.id.destination);
                txView.setText(vol.getDestination());

                return convertView;
            }
        }
    }


}
