package idaulana.ac.unsyiah.gubugbambu4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import idaulana.ac.unsyiah.gubugbambu4.POJO.DataUserPOJO;
import idaulana.ac.unsyiah.gubugbambu4.POJO.ResponePOJO;

import idaulana.ac.unsyiah.gubugbambu4.RetrofitHelper.RetrofitHelper;
import idaulana.ac.unsyiah.gubugbambu4.Session.SharedPreference;
import idaulana.ac.unsyiah.gubugbambu4.Util.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpActivity extends AppCompatActivity {

    TextView email, fullname, username, password, telepon;
    SharedPreference session;
    String foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        session = new SharedPreference(this);
        foto = formatNamaFotoUpload();
        email = (TextView) findViewById(R.id.email);
        fullname = (TextView) findViewById(R.id.fullname);
        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);
        telepon = (TextView) findViewById(R.id.telepon);

        Button signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().equals("") || fullname.getText().toString().equals("") || username.getText().toString().equals("")
                        || password.getText().toString().equals("") || telepon.getText().toString().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Tidak Boleh Ada Data yang Kosong", Toast.LENGTH_SHORT).show();
                }else{

                Retrofit retrofit = new RetrofitHelper().retrofitBuilder();
                final WebService webService = retrofit.create(WebService.class);

                Call<ResponePOJO> call = webService.tambahUser(
                        email.getText().toString(),
                        fullname.getText().toString(),
                        username.getText().toString(),
                        password.getText().toString(),
                        telepon.getText().toString(),
                        foto
                );

                call.enqueue(new Callback<ResponePOJO>() {
                    @Override
                    public void onResponse(Call<ResponePOJO> call, Response<ResponePOJO> response) {
                        Call<ResponePOJO> login = webService.loginUser(
                                email.getText().toString(),
                                password.getText().toString()
                        );
                        login.enqueue(new Callback<ResponePOJO>() {
                            @Override
                            public void onResponse(Call<ResponePOJO> call, Response<ResponePOJO> response) {
                                if((response.body().kode)==1){
                                    getUserNamePreference();
                                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(i);
                                    finish();
                            }}

                            @Override
                            public void onFailure(Call<ResponePOJO> call, Throwable t) {

                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<ResponePOJO> call, Throwable t) {

                    }
                });
                }
            }//onclickview
        });//onclick
    }

    private String formatNamaFotoUpload() {
        return "logo"+".jpg";
    }

    private void getUserNamePreference() {

        Retrofit retrofit2 = RetrofitHelper.retrofitBuilder();
        WebService webservie2 = retrofit2.create(WebService.class);

        Call<DataUserPOJO> call = webservie2.dataUser(
                email.getText().toString(),
                password.getText().toString()
        );
        call.enqueue(new Callback<DataUserPOJO>() {
            @Override
            public void onResponse(Call<DataUserPOJO> call, Response<DataUserPOJO> response) {
                session.LoginSession();
                session.setUserName(response.body().username);
                session.setFullName(response.body().fullname);
                session.setID(response.body().id);
                session.setImage(response.body().foto);

            }

            @Override
            public void onFailure(Call<DataUserPOJO> call, Throwable t) {

            }
        });

    }

}
