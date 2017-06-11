package idaulana.ac.unsyiah.gubugbambu4;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import idaulana.ac.unsyiah.gubugbambu4.POJO.ResponePOJO;

import idaulana.ac.unsyiah.gubugbambu4.RetrofitHelper.RetrofitHelper;
import idaulana.ac.unsyiah.gubugbambu4.Session.SharedPreference;
import idaulana.ac.unsyiah.gubugbambu4.Util.WebService;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PostingActivity extends AppCompatActivity {

    ImageView kirimFoto, postingFoto, postingFoto1, postingFoto2;
    static int TAKE_PICTURE = 1;
    Bitmap bitmap;
    String picturepath, tanggalUpload,StringNamaFoto, StringNamaFoto1, StringNamaFoto2;
    SharedPreference session;
    Retrofit retrofit;
    WebService webservie;
    EditText namatoko,hargasewa,deskripsi, telepon,alamat;
    private ActionBar.Tab buttonradio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);

        session = new SharedPreference(this);
        postingFoto= (ImageView) findViewById(R.id.fotoPosting);
        postingFoto1= (ImageView) findViewById(R.id.fotoPosting1);
        postingFoto2= (ImageView) findViewById(R.id.fotoPosting2);
        namatoko = (EditText) findViewById(R.id.namaToko);
        hargasewa = (EditText) findViewById(R.id.harga_sewa);
        deskripsi = (EditText) findViewById(R.id.deskripsitoko);
        telepon = (EditText) findViewById(R.id.noHp);
        alamat = (EditText) findViewById(R.id.alamat);


        postingFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFoto();
            }
        });


        final RadioGroup radio = (RadioGroup) findViewById(R.id.radio_button);


        Button posting = (Button) findViewById(R.id.posting);
        posting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (namatoko.getText().toString().equals("") || hargasewa.getText().toString().equals("")
                        || deskripsi.getText().toString().equals("") || telepon.getText().toString().equals("")
                        || alamat.getText().toString().equals("")) {
                    Toast.makeText(PostingActivity.this, "Tidak boleh ada data yang kosong", Toast.LENGTH_SHORT).show();
                }else{

                    int SelectedId = radio.getCheckedRadioButtonId();
                    final RadioButton buttonradio = (RadioButton) findViewById(SelectedId);

                    StringNamaFoto = formatNamaFotoUpload();

                    File file = new File(picturepath);
                    RequestBody reqFile = RequestBody.create(MediaType.parse("*/*"), file);

                    MultipartBody.Part body = MultipartBody.Part.createFormData("file", StringNamaFoto, reqFile);
                    RequestBody name = RequestBody.create(MediaType.parse("text/plain"), file.getName());

                    retrofit = RetrofitHelper.retrofitBuilder();
                    webservie = retrofit.create(WebService.class);
                    Call<ResponePOJO> call = webservie.uploadImage(
                            body,
                            name
                    );
                    call.enqueue(new Callback<ResponePOJO>() {

                        @Override
                        public void onResponse(Call<ResponePOJO> call, Response<ResponePOJO> response) {
                            if (response.body().kode == 1) {
                                Toast.makeText(PostingActivity.this, session.getID(), Toast.LENGTH_SHORT).show();
                                WebService webserviceUploadDataFoto = retrofit.create(WebService.class);
                                Call<ResponePOJO> callUploadDataFoto = webserviceUploadDataFoto.uploadDataFoto(
                                        session.getID(),
                                        StringNamaFoto,
                                        session.getImage(),
                                        buttonradio.getText().toString(),
                                        tanggalUpload,
                                        namatoko.getText().toString(),
                                        hargasewa.getText().toString(),
                                        deskripsi.getText().toString(),
                                        telepon.getText().toString(),
                                        alamat.getText().toString()
                                );
                                callUploadDataFoto.enqueue(new Callback<ResponePOJO>() {
                                    @Override
                                    public void onResponse(Call<ResponePOJO> call, Response<ResponePOJO> response) {

                                        Toast.makeText(PostingActivity.this, response.body().pesan, Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(Call<ResponePOJO> call, Throwable t) {
                                        Toast.makeText(PostingActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponePOJO> call, Throwable t) {
                            Toast.makeText(PostingActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }



    private String formatNamaFotoUpload() {
        int aNumber = (int) (100 * Math.random()) + 1;
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("dd-M-yyyy");
        Date myDate = new Date();
        tanggalUpload = timeStampFormat.format(myDate);
        return "GubugBambu-"+aNumber+"-"+ tanggalUpload +"-"+aNumber+".jpg";
    }

    public void uploadFoto() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
        startActivityForResult(i, TAKE_PICTURE);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode== TAKE_PICTURE && resultCode == RESULT_OK && null !=data) {
            Uri seletedimage = data.getData();

            String[] filepathcolum = {
                    MediaStore.Images.Media.DATA
            };

            Cursor cursor = getContentResolver().query(seletedimage, filepathcolum,null,null,null);
            cursor.moveToFirst();

            int columindex = cursor.getColumnIndex(filepathcolum[0]);
            picturepath = cursor.getString(columindex);
            cursor.close();

            bitmap = BitmapFactory.decodeFile(picturepath);
            bitmap.getScaledHeight(360);
            bitmap.getScaledWidth(360);
            postingFoto.setImageBitmap(bitmap);


        }

    }
}
