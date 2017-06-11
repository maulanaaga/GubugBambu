package idaulana.ac.unsyiah.gubugbambu4.Session;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    SharedPreferences pref;
    Context _context;
    SharedPreferences.Editor editor;
    int PRIVATE_MODE = 0;

    public SharedPreference(Context context){
        this._context = context;
        pref = _context.getSharedPreferences("MyPreference", PRIVATE_MODE);
        editor = pref.edit();

    }
    public void LoginSession(){

        editor.putBoolean("IsLogin", true);
        editor.commit();
    }

    public void setUserName(String name){
        editor.putString("Name", name);
        editor.commit();
    }
    public void setID(String id){
        editor.putString("ID", id);
        editor.commit();
    }
    public void setFullName(String fullName){
        editor.putString("Fullname", fullName);
        editor.commit();
    }

    public void namaToko(String namatoko){
        editor.putString("Nama Toko", namatoko);
        editor.commit();
    }

    public String getnamaToko() {
        String namatoko = pref.getString("Nama Toko", "");

        return namatoko;
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();

    }
    public boolean isLoggedIn(){

        return pref.getBoolean("IsLogin", false);
    }

    public String getUsername(){
        String name = pref.getString("Name","");

        return name;

    }
    public String getFulname(){
        String email = pref.getString("Fullname","");

        return email;

    }
    public String getID(){
        String id = pref.getString("ID","");

        return id;

    }

    public String getImage(){
        String foto =pref.getString("Image","");

        return foto;
    }

    public void setImage(String foto) {
        editor.putString("Image", foto);
        editor.commit();
    }
}
