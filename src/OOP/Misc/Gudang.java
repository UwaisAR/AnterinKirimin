/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOP.Misc;

import OOP.Items.Paket;
import java.util.ArrayList;

/**
 *
 * @author roni
 */
public class Gudang {
    String id;
    ArrayList<Paket> paket;
    String kota;

    public Gudang(String id, String kota) {
        this.id = id;
        this.kota = kota;
        paket = new ArrayList<>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPaket(ArrayList<Paket> paket) {
        this.paket = paket;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Paket> getPaket() {
        return paket;
    }

    public String getKota() {
        return kota;
    }
    
    public String getKode() {
        String digit = "";
        for (int i = 4; i > getId().length(); i--) {
            digit = digit + "0";
        }
        return "WRH"+digit+getId();
    }
    
}
