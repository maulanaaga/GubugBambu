package idaulana.ac.unsyiah.gubugbambu4.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import idaulana.ac.unsyiah.gubugbambu4.POJO.ListPilihTokoPOJO;
import idaulana.ac.unsyiah.gubugbambu4.R;
import idaulana.ac.unsyiah.gubugbambu4.Util.Konstanta;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AdapterDeskripsi extends BaseAdapter {
    private Context cnt;
    private ListPilihTokoPOJO data;

    public AdapterDeskripsi(Context cnt, ListPilihTokoPOJO data) {

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
            v = inflat.inflate(R.layout.activity_list_tabed_home, parent, false);
        }

        ImageView iv = (ImageView) v.findViewById(R.id.fotoRumah);
        TextView deskripsi= (TextView) v.findViewById(R.id.deskripsiadapt);
        TextView tv = (TextView) v.findViewById(R.id.nToko);
        TextView tp = (TextView) v.findViewById(R.id.textHp);
        TextView hs = (TextView) v.findViewById(R.id.sewa);

        Bitmap bitmap = getImageBitmap(Konstanta.BASE_URL_FOTO+data.data.get(position).namaFoto);
        deskripsi.setText(data.data.get(position).deskripsi);
        tp.setText(data.data.get(position).telepon);
        hs.setText(data.data.get(position).hargasewa);
        tv.setText(data.data.get(position).namatoko);
        iv.setImageBitmap(bitmap);

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
