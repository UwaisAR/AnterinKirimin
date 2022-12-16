/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOP.Users;

import OOP.Misc.Gudang;
import java.util.ArrayList;
/**
 *
 * @author roni
 */
public class Admin {
    private final String username;
    private final String password;
    public ArrayList<Pengirim> userPengirim;
    public ArrayList<Kurir> userKurir;
    public ArrayList<Gudang> gudang;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUserPengirim(ArrayList<Pengirim> userPengirim) {
        this.userPengirim = userPengirim;
    }

    public void setUserKurir(ArrayList<Kurir> userKurir) {
        this.userKurir = userKurir;
    }

    public void setGudang(ArrayList<Gudang> gudang) {
        this.gudang = gudang;
    }

    public ArrayList<Pengirim> getUserPengirim() {
        return userPengirim;
    }

    public ArrayList<Kurir> getUserKurir() {
        return userKurir;
    }

    public ArrayList<Gudang> getGudang() {
        return gudang;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    
}
