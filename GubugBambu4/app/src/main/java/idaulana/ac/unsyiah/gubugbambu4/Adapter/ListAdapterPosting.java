package idaulana.ac.unsyiah.gubugbambu4.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import idaulana.ac.unsyiah.gubugbambu4.ListPostingPOJO.ListPostingPOJO;
import idaulana.ac.unsyiah.gubugbambu4.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class ListAdapterPosting extends BaseAdapter {
    private Context cnt;
    private ListPostingPOJO datas;


    public ListAdapterPosting(Context cnt, ListPostingPOJO data) {
        this.cnt = cnt;
        this.datas = data;
    }

    @Override
    public int getCount() {
        return datas.data.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        LayoutInflater inflat = (LayoutInflater) cnt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = convertView;
        if(v == null){
            v = inflat.inflate(R.layout.activity_list_tabed_home, parent, false);
        }

        /*TextView namaIkan = (TextView) v.findViewById(R.id.namaIkan);
        TextView jumlahStock = (TextView) v.findViewById(R.id.jumlahStock);
        TextView hargaIkan = (TextView) v.findViewById(R.id.hargaIkan);

        ImageView posting = (ImageView) v.findViewById(R.id.fotoPostingIkan);

        namaIkan.setText("Ikan "+datas.data.get(position).namatoko);
        jumlahStock.setText("Deskripsi :"+datas.data.get(position).deskripsi+"kg");
        hargaIkan.setText("Harga Sewa : Rp."+datas.data.get(position).hargasewa+",-/Tahun");

        Bitmap bitmap = getImageBitmap(Konstanta.BASE_URL_FOTO+datas.data.get(position).namaFoto);
        posting.setImageBitmap(bitmap);
*/
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