package idaulana.ac.unsyiah.gubugbambu4.Util;

import idaulana.ac.unsyiah.gubugbambu4.ListPostingPOJO.PostingPOJO;
import idaulana.ac.unsyiah.gubugbambu4.POJO.DataUserPOJO;
import idaulana.ac.unsyiah.gubugbambu4.POJO.ListPilihTokoPOJO;
import idaulana.ac.unsyiah.gubugbambu4.POJO.ResponePOJO;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Khalidrianda on 03/06/2017.
 */

public interface WebService {
    @FormUrlEncoded
    @POST(Konstanta.TAMBAH_USER)
    public Call<ResponePOJO> tambahUser(
            @Field("email") String email,
            @Field("fullname") String fullname,
            @Field("username") String username,
            @Field("password") String password,
            @Field("telepon") String telepon,
            @Field("foto") String foto
    );

    @FormUrlEncoded
    @POST(Konstanta.UPDATE_USER)
    public Call<ResponePOJO> updateUser(
            @Field("id") String id,
            @Field("fullname") String fullname,
            @Field("username") String username,
            @Field("password") String password,
            @Field("telepon") String telepon,
            @Field("foto") String foto
    );

    @FormUrlEncoded
    @POST(Konstanta.UPDATE_FOTO_TOKO)
    public Call<ResponePOJO> updateFoto(
            @Field("idPengguna") String idPengguna,
            @Field("namaFoto1") String namaFoto1
    );

    @FormUrlEncoded
    @POST(Konstanta.LOGIN_USER)
    public Call<ResponePOJO> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST(Konstanta.RESET_PASSWORD)
    public Call<ResponePOJO> resetPassword(
            @Field("email") String email,
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST(Konstanta.TAKE_DATA_USER)
    public Call<DataUserPOJO> dataUser(
            @Field("email") String email,
            @Field("password") String password
    );

    @Multipart
    @POST(Konstanta.UPLOAD_FOTO)
    public Call<ResponePOJO> uploadImage(
            @Part MultipartBody.Part images,
            @Part("file") RequestBody name
    );

    @Multipart
    @POST(Konstanta.UPLOAD_PROFIL)
    public Call<ResponePOJO> uploadFoto(
            @Part MultipartBody.Part images,
            @Part("file") RequestBody name
    );

    @FormUrlEncoded
    @POST(Konstanta.DESKRIPSI_UPLOAD_FOTO)
    public Call<ResponePOJO> uploadDataFoto(
            @Field("idPengguna") String idPengguna,
            @Field("namaFoto") String namaFoto,
            @Field("namaFoto1") String namaFoto1,
            @Field("jenisToko") String jenisToko,
            @Field("tanggal") String tanggal,
            @Field("namatoko") String namatoko,
            @Field("hargasewa") String hargasewa,
            @Field("deskripsi") String deskripsi,
            @Field("telepon") String telepon,
            @Field("alamat") String alamat

    );

    @GET(Konstanta.LIST_UPLOAD_FOTO)
    public Call<PostingPOJO> listPosting(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST(Konstanta.LIST_PILIH_TOKO)
    public Call<ListPilihTokoPOJO> listPilihToko(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST(Konstanta.DEKRIPSI)
    public Call<ListPilihTokoPOJO> dekripsi(
            @Field("namatoko") String namatoko
    );

    @FormUrlEncoded
    @POST(Konstanta.LIST_TOKO)
    public Call<ListPilihTokoPOJO> listToko(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST(Konstanta.LIST_RUMAH)
    public Call<ListPilihTokoPOJO> listRumah(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST(Konstanta.LIST_KOST)
    public Call<ListPilihTokoPOJO> listKost(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST(Konstanta.LIST_SEWAAN_SAYA)
    public Call<ListPilihTokoPOJO> listSewaanSaya(
            @Field("idPengguna") String idPengguna
    );

    @FormUrlEncoded
    @POST(Konstanta.DELETE_SEWA)
    public Call<ResponePOJO> deleteSewa(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST(Konstanta.LIST_PROFIL)
    public Call<PostingPOJO> listProfil(
            @Field("id") String id
    );
}
