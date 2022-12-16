/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author roni
 */
public class DAOImplements {
    public interface DAOManageable<T> {
        void tambah(T t) throws SQLException;
        void hapus(T t) throws SQLException;
        void update(T t) throws SQLException;
        T getById(String id) throws SQLException;
        ArrayList<T> getAll() throws SQLException;
    }
    
    public interface DAOReadable<T> {
        ArrayList<T> getAll() throws SQLException;
    }
}

