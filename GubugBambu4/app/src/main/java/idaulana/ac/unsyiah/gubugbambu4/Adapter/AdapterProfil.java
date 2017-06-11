package idaulana.ac.unsyiah.gubugbambu4.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import idaulana.ac.unsyiah.gubugbambu4.ListSewaanSaya;
import idaulana.ac.unsyiah.gubugbambu4.POJO.ListPilihTokoPOJO;
import idaulana.ac.unsyiah.gubugbambu4.R;
import idaulana.ac.unsyiah.gubugbambu4.Util.Konstanta;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//belum dipakai
public class AdapterProfil extends BaseAdapter{
    private Context cnt;
    private ListPilihTokoPOJO data;

    public AdapterProfil(Context cnt, ListPilihTokoPOJO data) {

        this.cnt = cnt;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        LayoutInflater inflat = (LayoutInflater) cnt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = convertView;
        if(v == null){
            v = inflat.inflate(R.layout.activity_list_pilih_toko, parent, false);
        }

        CardView gg = (CardView) v.findViewById(R.id.cardView);
        ImageView iv = (ImageView) v.findViewById(R.id.foto_toko);
        TextView tv = (TextView) v.findViewById(R.id.nama_toko);

        Bitmap bitmap = getImageBitmap(Konstanta.BASE_URL_FOTO+data.data.get(position).namaFoto);
        tv.setText(data.data.get(position).namatoko);
        iv.setImageBitmap(bitmap);

        gg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cnt,ListSewaanSaya.class);
                i.putExtra("ID",data.data.get(position).id);
                i.putExtra("NAMA",data.data.get(position).namatoko);
                i.putExtra("DESKRIPSI",data.data.get(position).deskripsi);
                i.putExtra("TELEPON",data.data.get(position).telepon);
                i.putExtra("FOTO",Konstanta.BASE_URL_FOTO+data.data.get(position).namaFoto);
                i.putExtra("SEWA",data.data.get(position).hargasewa);
                i.putExtra("TANGGAL",data.data.get(position).tanggal);
                i.putExtra("FOTOPROFIL",Konstanta.BASE_URL_FOTO_PROFIL+data.data.get(position).namaFoto1);
                i.putExtra("ALAMAT",data.data.get(position).alamat);
                cnt.startActivity(i);
            }
        });

        return v;
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