package dz.esi.tdm.mainmodule;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class NavigationDrawerFragment extends Fragment {

    public static final String PREF_FILE_NAME = "Preferences";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private ActionBarDrawerToggle mDrawerToggle;

    private boolean mUserLearnedDrawer = true;
    private boolean mFromSavedInstance;


    public NavigationDrawerFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if (savedInstanceState != null) {
            mFromSavedInstance = true;
        }

    }

    @Override
    public void onStart() {
        super.onStart();

        Resources res = getActivity().getResources();

        String[] navOptions = res.getStringArray(R.array.nav_option_main);
        int[] icons = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

        ListView principalNavigationList = (ListView) getActivity().findViewById(R.id.principal_nav_list);

        ListAdapter navAdapter = new NavigationAdapter(getActivity(), navOptions, icons);
        principalNavigationList.setAdapter(navAdapter);
        principalNavigationList.setDivider(null);

        principalNavigationList.setScrollContainer(false);

        principalNavigationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DrawerLayout drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
                //android.app.Fragment fragment;

            /*    if (position == 0) {
//                    fragment = new RechercheVolFragment();

                } else if(position==1) {
                    fragment = new ListVolFragment();
                } else if(position==2) {
                    fragment = new NotificationFragment();
                } else {*/
                    drawerLayout.closeDrawers();
                    //return;
              // }

              /*  FragmentManager fragmentManager = NavigationDrawerFragment.this.getActivity().getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                drawerLayout.closeDrawers();*/
            }
        });


        String[] secondNavOptions = res.getStringArray(R.array.nav_option_second);
        int[] secondIcons = {R.mipmap.ic_launcher};


        ListView secondaryNavigationList = (ListView) getActivity().findViewById(R.id.secondary_nav_list);

        ListAdapter secondNavAdapter = new NavigationAdapter(getActivity(), secondNavOptions, secondIcons);
        secondaryNavigationList.setAdapter(secondNavAdapter);
        secondaryNavigationList.setDivider(null);
        secondaryNavigationList.setScrollContainer(false);


        secondaryNavigationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DrawerLayout drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
                //android.app.Fragment fragment;

                drawerLayout.closeDrawers();
                //return;


                //FragmentManager fragmentManager = NavigationDrawerFragment.this.getActivity().getFragmentManager();
                //fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                //drawerLayout.closeDrawers();
            }
        });

    }


    private class NavigationAdapter extends ArrayAdapter<String> {

        private Context context;
        private int[] icons;

        public NavigationAdapter(Context context, String[] values, int[] icons) {
            super(context, R.layout.list_navigation_item, values);
            this.context = context;
            this.icons = icons;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_navigation_item, null);
            }
            String option_name = getItem(position);
            TextView txView = (TextView) convertView.findViewById(R.id.nav_option_text);
            txView.setText(option_name);

            ImageView imgView = (ImageView) convertView.findViewById(R.id.nav_option_icon);
            imgView.setImageResource(icons[position]);
            return convertView;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        View containerView = getActivity().findViewById(fragmentId);
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, true + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
        if (!mUserLearnedDrawer && !mFromSavedInstance) {
            drawerLayout.openDrawer(containerView);
        }
        drawerLayout.setDrawerListener(mDrawerToggle);
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

}
