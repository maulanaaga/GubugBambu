package idaulana.ac.unsyiah.gubugbambu4.RetrofitHelper;

import idaulana.ac.unsyiah.gubugbambu4.Util.Konstanta;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitHelper {
    public static Retrofit retrofitBuilder(){

        return new Retrofit.Builder()
                .baseUrl(Konstanta.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
