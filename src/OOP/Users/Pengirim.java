/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOP.Users;

import OOP.Items.Paket;
import java.util.ArrayList;

/**
 *
 * @author roni
 */
public class Pengirim extends Akun{
    private String alamat;
    private ArrayList<Paket> paket;

    public Pengirim(String id, String username, String password, String noHp, String alamat) {
        super(id, username, password, noHp);
        this.alamat = alamat;
        this.paket = new ArrayList<>();
    }

    @Override
    public ArrayList<Paket> getPaket() {
        return paket;
    }

    public void setPaket(ArrayList<Paket> paket) {
        this.paket = paket;
    }
    
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }
    
    public void batalPaket(){
        if (paket.get(0).getStatus().equalsIgnoreCase("waiting")){
        }
    }
    
    @Override
    public String getKode() {
        String digit = "";
        for (int i = 4; i > super.getId().length(); i--) {
            digit = digit + "0";
        }
        return "SND"+digit+super.getId();
    }
}
