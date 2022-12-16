/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOP.Users;

import OOP.Items.Paket;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author roni
 */
public class Kurir extends Akun{
    private String status, kota;
    private ArrayList<Paket> paket;

    public Kurir(String id, String username, String password, String noHp, String status, String kota) {
        super(id, username, password, noHp);
        this.status = (status != null)?status:"WAITING";
        this.kota = kota;
        this.paket = new ArrayList<>();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public void setPaket(ArrayList<Paket> paket) {
        this.paket = paket;
    }

    public String getStatus() {
        return status;
    }

    public String getKota() {
        return kota;
    }

    @Override
    public ArrayList<Paket> getPaket() {
        return paket;
    }
    
    public DefaultTableModel tabelPaket() throws SQLException{
        DefaultTableModel model = null;
        return model;
    }

    @Override
    public String getKode() {
        String digit = "";
        for (int i = 4; i > super.getId().length(); i--) {
            digit = digit + "0";
        }
        return "COR"+digit+super.getId();
    }
}
