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

public class ResetPassword extends AppCompatActivity {
    TextView email, username, password, password1;
    SharedPreference session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        session = new SharedPreference(this);

        email= (TextView) findViewById(R.id.email);
        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);
        password1 = (TextView) findViewById(R.id.password1);
        Button reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals(password1.getText().toString())){
                    Retrofit retrofit = RetrofitHelper.retrofitBuilder();
                    WebService webService = retrofit.create(WebService.class);
                    Call<ResponePOJO> call = webService.resetPassword(
                            email.getText().toString(),
                            username.getText().toString(),
                            password.getText().toString()
                    );
                    call.enqueue(new Callback<ResponePOJO>() {
                        @Override
                        public void onResponse(Call<ResponePOJO> call, Response<ResponePOJO> response) {
                            Toast.makeText(ResetPassword.this, response.body().pesan, Toast.LENGTH_SHORT).show();
                            if((response.body().kode)==1){
                                getUserNamePreference();
                                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponePOJO> call, Throwable t) {

                        }
                    });
                }
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
