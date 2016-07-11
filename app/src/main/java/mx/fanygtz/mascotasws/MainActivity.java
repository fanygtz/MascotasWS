package mx.fanygtz.mascotasws;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import mx.fanygtz.mascotasws.adapter.PageAdapter;
import mx.fanygtz.mascotasws.fragments.MainFragment;
import mx.fanygtz.mascotasws.fragments.PerfilFragment;


public class MainActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myActionBar = (Toolbar) findViewById(R.id.myActionBar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setUpViewPager();
        if (myActionBar != null){
            setSupportActionBar(myActionBar);
        }
        /**/
    }
    private  ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MainFragment());
        fragments.add(new PerfilFragment());
        return fragments;
    }
    private  void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_bear);
    }

   /*
  */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.itemFavorito) {
            Intent intent = new Intent(MainActivity.this, FavoritaActivity.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.itemContacto){
            Intent intent = new Intent(MainActivity.this, ContactoActivity.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.itemAcercade){
            Intent intent = new Intent(MainActivity.this, BioDesarrollador.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.configCta){
            Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
            startActivity(intent);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
    
}

