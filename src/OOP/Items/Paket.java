/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOP.Items;

import OOP.Users.Pengirim;
import OOP.Users.Kurir;
import OOP.Misc.Gudang;
import OOP.Misc.Pembayaran;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 *
 * @author roni
 */
public class Paket implements Taxable{
    private String id, alamatTujuan, tipe, status;
    private ArrayList<Item> item;
    private LocalDate tanggalDibuat, tanggalSampai;
    private Pengirim pengirim;
    private Kurir kurir;
    private Gudang gudang;
    private int jarak;
    private Pembayaran pembayaran;

    public Paket(String id, LocalDate tanggalDibuat, String tipe, String status, int jarak, String alamatTujuan) {
        this.id = id;
        this.tanggalDibuat = tanggalDibuat;
        this.alamatTujuan = alamatTujuan;
        this.tipe = tipe;
        this.status = (status != null)?status:"WAITING FOR PAYMENT";
        this.jarak = jarak;
        this.item = new ArrayList<>();
    }
    
    public int idTipe(){
        if (tipe.equalsIgnoreCase("SLOW"))
            return 2;
        else if (tipe.equalsIgnoreCase("FAST"))
            return 1;
        else
            return 0;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getNoResi() {
        return idTipe()+String.format("%02d", tanggalDibuat.getDayOfMonth())+String.format("%02d", tanggalDibuat.getMonthValue())+tanggalDibuat.getYear()+String.format("%03d", Integer.parseInt(pengirim.getId()))+String.format("%03d", Integer.parseInt(id));
    }

    public void setItem(ArrayList<Item> item) {
        this.item = item;
    }

    public void setTanggalDibuat(LocalDate tanggalDibuat) {
        this.tanggalDibuat = LocalDate.now();
    }

    public void setTanggalSampai(LocalDate tanggalSampai) {
        this.tanggalSampai = tanggalSampai;
    }
    
    public void setAlamatTujuan(String alamatTujuan) {
        this.alamatTujuan = alamatTujuan;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setJarak(int jarak) {
        this.jarak = jarak;
    }

    public void setPengirim(Pengirim pengirim) {
        this.pengirim = pengirim;
    }

    public void setKurir(Kurir kurir) {
        this.kurir = kurir;
        if (kurir!=null) setStatus("PICKED UP BY COURIER: "+getKurir().getUsername()+" ["+getKurir().getKota()+"]");
    }

    public void setGudang(Gudang gudang) {
        this.gudang = gudang;
        if (gudang!=null) setStatus("PROCESSED IN WAREHOUSE ["+getGudang().getKota()+"]");
    }

    public void setPembayaran(Pembayaran pembayaran) {
        this.pembayaran = pembayaran;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Item> getItem() {
        return item;
    }

    public LocalDate getTanggalDibuat() {
        return tanggalDibuat;
    }

    public LocalDate getTanggalSampai() {
        return tanggalSampai;
    }
    
    public String getEstimasiSampai(){
        return "est. " + tanggalDibuat.plusDays(getEstimasi());
    }

    public int getJarak() {
        return jarak;
    }
    
    public String getAlamatTujuan() {
        return alamatTujuan;
    }

    public String getTipe() {
        return tipe;
    }

    public String getStatus() {
        return status;
    }

    public Pengirim getPengirim() {
        return pengirim;
    }

    public Kurir getKurir() {
        return kurir;
    }

    public Gudang getGudang() {
        return gudang;
    }

    public Pembayaran getPembayaran() {
        return pembayaran;
    }
    
    public int getEstimasi() {
        int hari;
        if(jarak<=100&&jarak>=0)
            hari = idTipe()*1;
        else if(jarak<=200)
            hari = idTipe()*2;
        else
            hari = idTipe()*3;
        
        return hari; // return estimasi satuan hari
    }
    
    public int hargaBarang(){
        double totalHarga = 0;
        
        for(Item x : item) totalHarga = totalHarga + x.getBerat();
        if(jarak<=100&&jarak>=0) totalHarga = totalHarga * 5000 * idTipe();
        else if(jarak<=200) totalHarga = totalHarga * 10000 * idTipe();
        else totalHarga = totalHarga * 15000 * idTipe();
        
        return (int)totalHarga; // return totalHarga yang sudah di convert ke integer
    }

    @Override
    public int hargaPajak() {
        return (int)(hargaBarang()*PAJAK);
    }
}
