package idaulana.ac.unsyiah.gubugbambu4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


import idaulana.ac.unsyiah.gubugbambu4.Session.SharedPreference;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPreference session;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new SharedPreference(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView.getMenu();
        if (!session.isLoggedIn()) {
            MenuItem item = menu.findItem(R.id.nav_tambah);
            item.setVisible(false);
            MenuItem item2 = menu.findItem(R.id.nav_logout);
            item2.setVisible(false);
        } /*else if (session.isLoggedIn()) {
            MenuItem item3 = menu.findItem(R.id.nav_login);
            item3.setVisible(false);
        }*/

        TextView facebookName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nama);
        TextView Email = (TextView) navigationView.getHeaderView(0).findViewById(R.id.textView);
        ImageView profilePictureView = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageView);
        facebookName.setText(session.getID());
        Email.setText(session.getFulname());
        /*
        profilePictureView.setVisibility(View.VISIBLE);
        Bitmap bitmap = getImageBitmap(Konstanta.BASE_URL_FOTO+session.getImage());
        profilePictureView.setImageBitmap(bitmap);*/

        /*profilePictureView.setProfileId();*/

        HomeFragment home = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, home, "Home");
        fragmentTransaction.commit();


        /*Call<PostingPOJO> call = webService.listProfil(
                session.getID()
        );
        call.enqueue(new Callback<PostingPOJO>() {
            @Override
            public void onResponse(Call<PostingPOJO> call, Response<PostingPOJO> response) {
                doto = response.body();
                AdapterProfil gg = new AdapterProfil(getApplicationContext(), doto);
                cc.setAdapter(gg);
            }

            @Override
            public void onFailure(Call<PostingPOJO> call, Throwable t) {

            }
        });*/

        /*final TextView facebookName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nama);
        TextView Email = (TextView) navigationView.getHeaderView(0).findViewById(R.id.textView);
        final ImageView profilePictureView = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageView);

        Call<PostingPOJO> foto = webService.listPosting(
                session.getID()
        );
        foto.enqueue(new Callback<PostingPOJO>() {
            @Override
            public void onResponse(Call<PostingPOJO> call, Response<PostingPOJO> response) {
                doto = response.body();
                AdapterProfil go = new AdapterProfil(getApplicationContext(),doto);

            }

            @Override
            public void onFailure(Call<PostingPOJO> call, Throwable t) {

            }
        });*/

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

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
        if (id == R.id.action_settings) {
            Intent i = new Intent(getApplicationContext(), UpdateUser.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            HomeFragment home = new HomeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, home, "Home");
            fragmentTransaction.commit();
        } /*else if (id == R.id.nav_toko) {
            TokoFragment toko = new TokoFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, toko, "Toko");
            fragmentTransaction.commit();
        }*/ /*else if (id == R.id.nav_rumah) {
            RumahFragment rumah = new RumahFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, rumah, "Rumah");
            fragmentTransaction.commit();

        }*/ else if (id == R.id.nav_map) {
            PetaFragment petaFragment = new PetaFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, petaFragment, "Kost");
            fragmentTransaction.commit();

        } else if (id == R.id.nav_kost) {
            KostFragment daftarFragment = new KostFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, daftarFragment, "Kost");
            fragmentTransaction.commit();

        }/* else if (id == R.id.nav_manage) {
            SewaanSayaFragment sewaanSayaFragment = new SewaanSayaFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, sewaanSayaFragment, "Sewaan Saya");
            fragmentTransaction.commit();

        } */ /*else if (id == R.id.nav_login) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);

        } */else if (id == R.id.nav_tambah) {
            Intent i = new Intent(getApplicationContext(), PostingActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_logout) {
            session = new SharedPreference(this);
            session.logoutUser();
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private Bitmap getImageBitmap(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
