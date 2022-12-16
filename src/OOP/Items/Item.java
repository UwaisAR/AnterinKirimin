package OOP.Items;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author roni
 */
public class Item{
    private String nama;
    private double berat;
    private double[] dimensi;
    private String id;
    private Paket paket;

    public Item(String id, String nama, double berat) {
        this.nama = nama;
        this.berat = berat;
        this.id = id;
        this.dimensi = new double[]{0,0,0};
    }
    
    public Item(String id, String nama, double[] dimensi) {
        this.nama = nama;
        this.dimensi = dimensi;
        this.id = id;
        this.berat = (dimensi[0]*dimensi[1]*dimensi[2])/6000.0;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void setPaket(Paket paket) {
        this.paket = paket;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setBerat(double berat) {
        this.berat = berat;
    }

    public void setDimensi(double[] dimensi) {
        this.dimensi = dimensi;
    }

    public String getNama() {
        return nama;
    }
    
    public double getBerat() {
        return berat;
    }
    
    public double[] getDimensi() {
        return dimensi;
    }

    public String getId() {
        return id;
    }

    public Paket getPaket() {
        return paket;
    }
}
