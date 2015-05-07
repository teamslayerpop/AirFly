package dz.esi.tdm.mainmodule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Cherifi on 04/05/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private String[] tabs;
    private boolean detail;

    public ViewPagerAdapter(FragmentManager fm, String [] tabs, boolean detail) {
        super(fm);
        this.tabs = tabs;
        this.detail = detail;
    }

    @Override
    public Fragment getItem(int position) {
        if(!detail) {
            if(position == 0) {
                Vol[] vols = {
                        new Vol("2365AD","Canada"),
                        new Vol("52FR","France"),
                        new Vol("596DA","Turkie")
                };
                return VolsFragment.VolTypeFragment.getInstance(vols);

            } else {
                Vol[] vols = {
                        new Vol("478DQ","Maroc"),
                        new Vol("584DA","Tunisie")
                };
                return VolsFragment.VolTypeFragment.getInstance(vols);
            }
        } else {
            if(position == 0) {
                return new DetailFragment();
            } else if (position == 1) {
                return new StatutFragment();
            } else {
                return new GoogleMapFragment();
            }
        }
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
