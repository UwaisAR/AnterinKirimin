/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOP.Misc;

/**
 *
 * @author roni
 */
public class Pembayaran {
    private String noKartu, pin;
    private int saldo;

    public Pembayaran(String noKartu, String pin, int saldo) {
        this.noKartu = noKartu;
        this.pin = pin;
        this.saldo = saldo;
    }

    public void setNoKartu(String noKartu) {
        this.noKartu = noKartu;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getNoKartu() {
        return noKartu;
    }

    public String getPin() {
        return pin;
    }

    public int getSaldo() {
        return saldo;
    }
}
