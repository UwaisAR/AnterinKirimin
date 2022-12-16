/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ConfigPackage.Config;
import DAO.DAO.*;
import DAO.DAOImplements.*;
import java.sql.SQLException;

/**
 *
 * @author roni
 */
public class Factory {
    private static DAOManageable pengirimDAO, kurirDAO, paketDAO, itemDAO, gudangDAO;
    private static DAOReadable adminDAO, pembayaranDAO;
    
    public static PengirimDAO getPengirimDAO() throws SQLException {
        if (pengirimDAO == null) {
            pengirimDAO = new DAO().new PengirimDAO(Config.configDataBase());
        }
        return (PengirimDAO) pengirimDAO;
    }
    
    public static KurirDAO getKurirDAO() throws SQLException {
        if (kurirDAO == null) {
            kurirDAO = new DAO().new KurirDAO(Config.configDataBase());
        }
        return (KurirDAO) kurirDAO;
    }
    
    public static GudangDAO getGudangDAO() throws SQLException {
        if (gudangDAO == null) {
            gudangDAO = new DAO().new GudangDAO(Config.configDataBase());
        }
        return (GudangDAO) gudangDAO;
    }
    
    public static PaketDAO getPaketDAO() throws SQLException {
        if (paketDAO == null) {
            paketDAO = new DAO().new PaketDAO(Config.configDataBase());
        }
        return (PaketDAO) paketDAO;
    }
    
    public static ItemDAO getItemDAO() throws SQLException {
        if (itemDAO == null) {
            itemDAO = new DAO().new ItemDAO(Config.configDataBase());
        }
        return (ItemDAO) itemDAO;
    }
    
    public static AdminDAO getAdminDAO() throws SQLException {
        if (adminDAO == null) {
            adminDAO = new DAO().new AdminDAO(Config.configDataBase());
        }
        return (AdminDAO) adminDAO;
    }
    
    public static PembayaranDAO getPembayaranDAO() throws SQLException {
        if (pembayaranDAO == null) {
            pembayaranDAO = new DAO().new PembayaranDAO(Config.configDataBase());
        }
        return (PembayaranDAO) pembayaranDAO;
    }
}
