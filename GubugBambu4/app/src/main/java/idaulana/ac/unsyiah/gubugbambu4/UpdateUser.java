package idaulana.ac.unsyiah.gubugbambu4;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
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

public class UpdateUser extends AppCompatActivity {

    TextView email, fullname, username, password, telepon;
    SharedPreference session;
    ImageView postingFoto;
    Retrofit retrofit;
    static int TAKE_PICTURE = 1;
    Bitmap bitmap;
    String picturepath, tanggalUpload, StringNamaFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        session = new SharedPreference(this);

        postingFoto = (ImageView) findViewById(R.id.fotoPosting);
        fullname = (TextView) findViewById(R.id.fullname);
        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);
        telepon = (TextView) findViewById(R.id.telepon);

        postingFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFoto();
            }
        });


        Button signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullname.getText().toString().equals("") || username.getText().toString().equals("")
                        || password.getText().toString().equals("") || telepon.getText().toString().equals("")) {
                    Toast.makeText(UpdateUser.this, "Tidak Boleh Ada Data yang Kosong", Toast.LENGTH_SHORT).show();
                } else {

                    StringNamaFoto = formatNamaFotoUserUpload();

                    File file = new File(picturepath);
                    RequestBody reqFile = RequestBody.create(MediaType.parse("*/*"), file);

                    MultipartBody.Part body = MultipartBody.Part.createFormData("file", StringNamaFoto, reqFile);
                    RequestBody name = RequestBody.create(MediaType.parse("text/plain"), file.getName());

                    retrofit = new RetrofitHelper().retrofitBuilder();
                    WebService webService = retrofit.create(WebService.class);

                    Call<ResponePOJO> call = webService.uploadFoto(
                            body,
                            name
                    );
                    call.enqueue(new Callback<ResponePOJO>() {
                        @Override
                        public void onResponse(Call<ResponePOJO> call, Response<ResponePOJO> response) {
                            if (response.body().kode == 1) {
                                WebService webserviceUploadFoto = retrofit.create(WebService.class);
                                Call<ResponePOJO> callUploadFoto = webserviceUploadFoto.updateUser(
                                        session.getID(),
                                        fullname.getText().toString(),
                                        username.getText().toString(),
                                        password.getText().toString(),
                                        telepon.getText().toString(),
                                        StringNamaFoto
                                );
                                callUploadFoto.enqueue(new Callback<ResponePOJO>() {
                                    @Override
                                    public void onResponse(Call<ResponePOJO> call, Response<ResponePOJO> response) {
                                        Toast.makeText(UpdateUser.this, response.body().pesan, Toast.LENGTH_SHORT).show();
                                        if(response.body().kode==1){
                                            session.setImage(StringNamaFoto);
                                            WebService ubahFoto = retrofit.create(WebService.class);
                                            Call<ResponePOJO> gg = ubahFoto.updateFoto(
                                                    session.getID(),
                                                    session.getImage()
                                            );
                                            gg.enqueue(new Callback<ResponePOJO>() {
                                                @Override
                                                public void onResponse(Call<ResponePOJO> call, Response<ResponePOJO> response) {

                                                }

                                                @Override
                                                public void onFailure(Call<ResponePOJO> call, Throwable t) {
                                                    Toast.makeText(UpdateUser.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponePOJO> call, Throwable t) {
                                        Toast.makeText(UpdateUser.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponePOJO> call, Throwable t) {

                        }
                    });
                }
            }//onclickview
        });//onclick
    }

    private String formatNamaFotoUserUpload() {
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SS");
        Date myDate = new Date();
        tanggalUpload = timeStampFormat.format(myDate);
        return "TamitaSewa-" + session.getUsername() + "-" + tanggalUpload + "-" + session.getID() + ".jpg";
    }

    public void uploadFoto() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, TAKE_PICTURE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK && null != data) {
            Uri seletedimage = data.getData();

            String[] filepathcolum = {
                    MediaStore.Images.Media.DATA
            };

            Cursor cursor = getContentResolver().query(seletedimage, filepathcolum, null, null, null);
            cursor.moveToFirst();

            int columindex = cursor.getColumnIndex(filepathcolum[0]);
            picturepath = cursor.getString(columindex);
            cursor.close();

            bitmap = BitmapFactory.decodeFile(picturepath);
            postingFoto.setImageBitmap(bitmap);

        }
    }
}