package idaulana.ac.unsyiah.gubugbambu4;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import idaulana.ac.unsyiah.gubugbambu4.Adapter.ListAdapterPilihToko;
import idaulana.ac.unsyiah.gubugbambu4.POJO.ListPilihTokoPOJO;


import idaulana.ac.unsyiah.gubugbambu4.RetrofitHelper.RetrofitHelper;
import idaulana.ac.unsyiah.gubugbambu4.Session.SharedPreference;
import idaulana.ac.unsyiah.gubugbambu4.Util.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class RumahFragment extends Fragment {
    private SharedPreference session;
    private GridView lv;
    ListPilihTokoPOJO data;

    public RumahFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_rumah,container,false);

        lv = (GridView) v.findViewById(R.id.grid_view2);
        session = new SharedPreference(getContext());
        Retrofit retrofit = RetrofitHelper.retrofitBuilder();
        WebService webService = retrofit.create(WebService.class);
        Call<ListPilihTokoPOJO> cal = webService.listRumah(
                session.getID()
        );
        cal.enqueue(new Callback<ListPilihTokoPOJO>() {
            @Override
            public void onResponse(Call<ListPilihTokoPOJO> call, Response<ListPilihTokoPOJO> response) {
                data = response.body();
                ListAdapterPilihToko ls = new ListAdapterPilihToko(getContext(), data);
                lv.setAdapter(ls);
            }

            @Override
            public void onFailure(Call<ListPilihTokoPOJO> call, Throwable t) {

            }
        });

        return v;
    }

}
