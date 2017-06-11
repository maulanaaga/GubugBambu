package idaulana.ac.unsyiah.gubugbambu4;

import android.app.ProgressDialog;
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

public class LoginActivity extends AppCompatActivity {

    TextView email, password;
    SharedPreference session;
    String foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SharedPreference(this);

        if(session.isLoggedIn()){
            Intent ia = new Intent(getApplicationContext(),MainActivity.class);
            ia.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(ia);
            finish();
        }

        /*final TextView lupaPassword = (TextView) findViewById(R.id.lupapassword);
        lupaPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ResetPassword.class);
                startActivity(i);
            }
        });*/

        email = (TextView) findViewById(R.id.email);
        password = (TextView) findViewById(R.id.password);

        final TextView noLogin = (TextView) findViewById(R.id.noLogin);
        noLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        //Button login = (Button) findViewById(R.id.login);
        final TextView login = (TextView) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog pd = new ProgressDialog(v.getContext());
                pd.setMessage("Please wait ...");
                pd.show();

                Retrofit retrofit = RetrofitHelper.retrofitBuilder();
                WebService webservice = retrofit.create(WebService.class);
                retrofit2.Call<ResponePOJO> call = webservice.loginUser(
                        email.getText().toString(),
                        password.getText().toString()
                );

                call.enqueue(new Callback<ResponePOJO>() {
                    @Override
                    public void onResponse(retrofit2.Call<ResponePOJO> call, Response<ResponePOJO> response) {
                        Toast.makeText(LoginActivity.this, response.body().pesan, Toast.LENGTH_SHORT).show();
                        if((response.body().kode)==1){
                            pd.cancel();

                            getUserNamePreference();
                            Intent i = new Intent(getApplicationContext(),MainActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            finish();
                        }else {
                            pd.cancel();
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<ResponePOJO> call, Throwable t) {
                    }
                });
            }
        });
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
