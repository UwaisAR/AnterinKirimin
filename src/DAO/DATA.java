/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import OOP.Users.Akun;
import OOP.Users.Pengirim;
import OOP.Users.Kurir;
import OOP.Users.Admin;
import static DAO.Factory.*;
import OOP.Items.Item;
import OOP.Items.Paket;
import OOP.Misc.Gudang;
import OOP.Misc.Pembayaran;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author roni
 */
public class DATA {
    public static ArrayList<Akun> listAkun;
    public static ArrayList<Gudang> listGudang;
    public static ArrayList<Paket> listPaket;
    public static ArrayList<Item> listItem;
    public static Admin admin;
    public static ArrayList<Pembayaran> payment;
    
    public static Object setObjectFromDATA(Object obj) throws SQLException{
        loadDATA();
        if (obj instanceof Item) return (Item)DATA.listItem.stream().filter((t) -> t.getId().equals(((Item) obj).getId())).findFirst().orElse(null);
        else if (obj instanceof Paket) return (Paket)listPaket.stream().filter((t) -> t.getId().equals(((Paket) obj).getId())).findFirst().orElse(null);
        else if (obj instanceof Pengirim) return (Pengirim)listAkun.stream().filter((t) -> t.getId().equals(((Pengirim) obj).getId())).findFirst().orElse(null);
        else if (obj instanceof Kurir) return (Kurir)listAkun.stream().filter((t) -> t.getId().equals(((Kurir) obj).getId())).findFirst().orElse(null);
        else if (obj instanceof Gudang) return (Gudang)listGudang.stream().filter((t) -> t.getId().equals(((Gudang) obj).getId())).findFirst().orElse(null);
        else return obj;
    }
    
    public static Object authAccount(String username, String password){
        return listAkun.stream().filter((t) -> t.getUsername().equals(username)&&t.getPassword().equals(password)).findFirst().orElse(null);
    }
    
    public static void hapusDATA(Object obj) throws SQLException{
        if (obj instanceof Item){ 
            getItemDAO().hapus(getItemDAO().getById(((Item) obj).getId()));
        } else if (obj instanceof Paket){ 
            for (Item itm : ((Paket) obj).getItem()) {
                getItemDAO().hapus(getItemDAO().getById(itm.getId()));
            }
            getPaketDAO().hapus((Paket) obj);
        } else if (obj instanceof Pengirim){ 
            for (Paket pkg : ((Pengirim) obj).getPaket()) {
                for (Item itm : pkg.getItem()) {
                    getItemDAO().hapus(getItemDAO().getById(itm.getId()));
                }
                getPaketDAO().hapus(getPaketDAO().getById(pkg.getId()));
            }
            getPengirimDAO().hapus((Akun) obj);
        } else if (obj instanceof Kurir){ 
            getKurirDAO().hapus((Akun) obj);
        } else if (obj instanceof Gudang){ 
            getGudangDAO().hapus((Gudang) obj);
        }
        loadDATA();
    }
    
    public static void updateDATA(Object obj) throws SQLException {
        if (obj instanceof Item) getItemDAO().update((Item) obj);
        else if (obj instanceof Paket) getPaketDAO().update((Paket) obj);
        else if (obj instanceof Pengirim) getPengirimDAO().update((Pengirim) obj);
        else if (obj instanceof Kurir) getKurirDAO().update((Kurir) obj);
        else if (obj instanceof Gudang) getGudangDAO().update((Gudang) obj);
        loadDATA();
    }
    
    public static void addDATA(Object obj) throws SQLException {
        if (obj instanceof Item) getItemDAO().tambah((Item) obj);
        else if (obj instanceof Paket) getPaketDAO().tambah((Paket) obj);
        else if (obj instanceof Pengirim) getPengirimDAO().tambah((Akun) obj);
        else if (obj instanceof Kurir) getKurirDAO().tambah((Akun) obj);
        else if (obj instanceof Gudang) getGudangDAO().tambah((Gudang) obj);
        loadDATA();
    }
    
    public static void loadDATA() throws SQLException {
        listItem = getItemDAO().getAll();
        
        listPaket = getPaketDAO().getAll();
        for (Paket pkg : listPaket) {
            pkg.setItem(listItem.stream().filter(
                        (t) -> (t.getPaket() == null ? pkg.getId() == null : t.getPaket().getId().equals(pkg.getId()))
                ).collect(Collectors.toCollection(ArrayList::new))
            );
        }

        listAkun = new ArrayList<>();
        listAkun.addAll(getPengirimDAO().getAll());
        listAkun.addAll(getKurirDAO().getAll());
        for (Akun acc : listAkun) {
            if (acc instanceof Pengirim){
                ((Pengirim)acc).setPaket(listPaket.stream().filter(
                        (t) -> (t.getPengirim() == null ? ((Pengirim)acc).getId() == null : t.getPengirim().getId().equals(((Pengirim)acc).getId()))
                    ).collect(Collectors.toCollection(ArrayList::new))
                );
            } else if (acc instanceof Kurir){
                ((Kurir)acc).setPaket(listPaket.stream().filter(
                        (t) -> (t.getKurir() == null ? ((Kurir)acc).getId() == null : t.getKurir().getId().equals(((Kurir)acc).getId()))
                    ).collect(Collectors.toCollection(ArrayList::new))
                );
            }
        }
        
        listGudang = getGudangDAO().getAll();
        for (Gudang wrh : listGudang) {
            wrh.setPaket(listPaket.stream().filter((t) -> (t.getGudang() == null ? wrh.getId() == null : t.getGudang().getId().equals(wrh.getId()))
                ).collect(Collectors.toCollection(ArrayList::new))
            );
        }
        
        admin = getAdminDAO().getAll().get(0);
        ArrayList<Pengirim> p = new ArrayList<>();
        ArrayList<Kurir> k = new ArrayList<>();
        admin.setGudang(listGudang);
        for (Akun acc : listAkun) {
            if (acc instanceof Pengirim){
                p.add((Pengirim) acc);
            } else if (acc instanceof Kurir){
                k.add((Kurir) acc);
            }
        }
        admin.setUserPengirim(p);
        admin.setUserKurir(k);
        
        payment = getPembayaranDAO().getAll();
    }
}
