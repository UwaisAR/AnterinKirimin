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
abstract public class  Akun{
    private String id;
    private String username;
    private String password;
    private String noHp;

    public Akun(String id, String username, String password, String noHp) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.noHp = noHp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNoHp() {
        return noHp;
    }
    
    public abstract String getKode();
    
    public abstract ArrayList<Paket> getPaket();
}
