package idaulana.ac.unsyiah.gubugbambu4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


import idaulana.ac.unsyiah.gubugbambu4.Session.SharedPreference;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ListTabedHome extends AppCompatActivity {

    TextView namatoko, deksripsi, tanggal, telepon1, harga, alamat, fullname;
    ImageView foto, profil;
    SharedPreference session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tabed_home);

        session = new SharedPreference(this);

        Bundle extras = getIntent().getExtras();
        String newString = extras.getString("ID");
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
