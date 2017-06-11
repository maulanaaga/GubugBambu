package idaulana.ac.unsyiah.gubugbambu4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import idaulana.ac.unsyiah.gubugbambu4.POJO.ResponePOJO;


import idaulana.ac.unsyiah.gubugbambu4.RetrofitHelper.RetrofitHelper;
import idaulana.ac.unsyiah.gubugbambu4.Session.SharedPreference;
import idaulana.ac.unsyiah.gubugbambu4.Util.WebService;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListSewaanSaya extends AppCompatActivity {

    TextView namatoko, deksripsi, tanggal, telepon1, harga, alamat, fullname;
    ImageView foto, profil;
    SharedPreference session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sewaan_saya);

        session = new SharedPreference(this);

        Bundle extras = getIntent().getExtras();
        final String newString = extras.getString("ID");
        String newString1 = extras.getString("NAMA");
        String newString2 = extras.getString("DESKRIPSI");
        String newString3 = extras.getString("SEWA");
        String newString4 = extras.getString("TANGGAL");
        String newString5 = extras.getString("TELEPON");
        String newString6 = extras.getString("ALAMAT");
        String newString7 = extras.getString("FOTO");
        String newString8 = extras.getString("FOTOPROFIL");

        namatoko = (TextView) findViewById(R.id.nToko);
        deksripsi = (TextView) findViewById(R.id.deskripsiadapt);
        tanggal = (TextView) findViewById(R.id.textViewWaktu);
        telepon1 = (TextView) findViewById(R.id.textHp);
        harga = (TextView) findViewById(R.id.sewa);
        alamat = (TextView) findViewById(R.id.alamatrum);
        foto = (ImageView) findViewById(R.id.fotoRumah);
        profil = (ImageView) findViewById(R.id.imageView11);
        fullname = (TextView) findViewById(R.id.textView13);


        namatoko.setText(newString1);
        deksripsi.setText(newString2);
        harga.setText(newString3);
        tanggal.setText(newString4);
        telepon1.setText(newString5);
        alamat.setText(newString6);
        Bitmap bitmap = getImageBitmap(newString7);
        foto.setImageBitmap(bitmap);
        bitmap = getImageBitmap(newString8);
        profil.setImageBitmap(bitmap);
        fullname.setText(session.getFulname());
        Button btn = (Button) findViewById(R.id.delete);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = RetrofitHelper.retrofitBuilder();
                WebService webService= retrofit.create(WebService.class);
                Call<ResponePOJO> call= webService.deleteSewa(
                        newString
                );
                call.enqueue(new Callback<ResponePOJO>() {
                    @Override
                    public void onResponse(Call<ResponePOJO> call, Response<ResponePOJO> response) {
                        Toast.makeText(ListSewaanSaya.this, response.body().pesan, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponePOJO> call, Throwable t) {

                    }
                });
            }
        });

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
