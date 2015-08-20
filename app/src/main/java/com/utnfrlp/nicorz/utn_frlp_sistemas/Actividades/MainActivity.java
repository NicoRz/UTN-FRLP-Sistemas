package com.utnfrlp.nicorz.utn_frlp_sistemas.Actividades;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.utnfrlp.nicorz.utn_frlp_sistemas.R;
import com.utnfrlp.nicorz.utn_frlp_sistemas.menu.CursadaParaCursar;
import com.utnfrlp.nicorz.utn_frlp_sistemas.menu.FinalParaCursar;
import com.utnfrlp.nicorz.utn_frlp_sistemas.menu.ListaDeMaterias;
import com.utnfrlp.nicorz.utn_frlp_sistemas.menu.MiCarrera;
import com.utnfrlp.nicorz.utn_frlp_sistemas.menu.QueNecesitoParaCursar;
import com.utnfrlp.nicorz.utn_frlp_sistemas.menu.QueNecesitoParaFinal;

import com.utnfrlp.nicorz.utn_frlp_sistemas.Logging.L;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.DataBase;

public class MainActivity extends AppCompatActivity {
    DataBase db;
    Toolbar mToolbar;
    TabLayout mTabLayout;
    ViewPager mViewPager;
    MyPagerAdapter mAdapter;
    String [] carreras;
    private String[] tabs;

    @SuppressLint("InlinedApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main);

            InicializarCosas();
            CrearEventos();

        } catch (Exception e) {
            LogD("OnCreate",e.toString());
            Toast.makeText(getBaseContext(), "Error Inesperado", Toast.LENGTH_SHORT).show();
        }
    }

    private void InicializarCosas() {
        db= new DataBase(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        tabs = getResources().getStringArray(R.array.menu);
        carreras = getResources().getStringArray(R.array.carreras);
    }

    private void CrearEventos() {
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mAdapter.setTabs(tabs);

        mViewPager.setAdapter(mAdapter);
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(getApplicationContext(),Preferences.class);
                startActivityForResult(intent, 10);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {
        private String[] tabs;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ListaDeMaterias();
                case 1:
                    return new CursadaParaCursar();
                case 2:
                    return new QueNecesitoParaCursar();
                case 3:
                    return new FinalParaCursar();
                case 4:
                    return new QueNecesitoParaFinal();
                case 5:
                    return new MiCarrera();
                default:
                    return null;
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

        public void setTabs(String[] tabs) {
            this.tabs = tabs;
        }
    }

    public static void LogD(String nombreMetodo,String mensaje) {
        L.LogD("MainActivity", nombreMetodo + " " + mensaje);
    }
}
