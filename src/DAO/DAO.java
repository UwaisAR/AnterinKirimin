/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import OOP.Users.Akun;
import OOP.Users.Pengirim;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import OOP.Users.Admin;
import OOP.Users.Kurir;
import DAO.DAOImplements.*;
import static DAO.Factory.*;
import OOP.Items.Item;
import OOP.Items.Paket;
import OOP.Misc.Gudang;
import OOP.Misc.Pembayaran;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

/**
 *
 * @author roni
 */
public class DAO{
    private final static String SQL_INSERT = "INSERT INTO TABLE_NAME VALUES VLS";
    private final static String SQL_UPDATE = "UPDATE TABLE_NAME SET COLUMN WHERE id=?";
    private final static String SQL_DELETE = "DELETE FROM TABLE_NAME WHERE id=?";
    private final static String SQL_GETBYID = "SELECT * FROM TABLE_NAME  WHERE id=?";
    private final static String SQL_GETALL = "SELECT * FROM TABLE_NAME ";
    
    private PreparedStatement statement;
    private ResultSet result;
    
    public class PengirimDAO implements DAOManageable<Akun>{
        private final Connection connection;
        public PengirimDAO(Connection connection) throws SQLException{
            this.connection = connection;
        }

        @Override
        public void tambah(Akun t) throws SQLException {
            try {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(SQL_INSERT.replace("TABLE_NAME", "pengirim").replace("VLS", "(?, ?, ?, ?, ?)"));
                statement.setString(1, (t.getId()==null)?null:t.getId());
                statement.setString(2, t.getUsername());
                statement.setString(3, t.getPassword());
                statement.setString(4, t.getNoHp());
                if (t instanceof Pengirim){
                    statement.setString(5, ((Pengirim) t).getAlamat());
                }
                statement.executeUpdate();
                t.setId((t.getId()==null)?getPengirimDAO().getAll().get(getPengirimDAO().getAll().size()-1).getId():t.getId());
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

        @Override
        public void update(Akun t) throws SQLException {
            try {
                if (t.getPaket()!=null) {
                    for (Paket paket : t.getPaket()) {
                        getPaketDAO().update(paket);
                    }
                }
                ((Pengirim)t).setPaket(getPaketDAO().getAll().stream().filter((o) -> 
                    (o.getPengirim() == null ? ((Pengirim)t).getId() == null : o.getPengirim().getId().equals(((Pengirim)t).getId()))
                ).collect(Collectors.toCollection(ArrayList::new)));
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(SQL_UPDATE.replace("TABLE_NAME", "pengirim").replace("COLUMN", "username=?, password=?, phone_number=?, address=?"));
                statement.setString(5, (t.getId()==null)?null:t.getId());
                statement.setString(1, t.getUsername());
                statement.setString(2, t.getPassword());
                statement.setString(3, t.getNoHp());
                if (t instanceof Pengirim){
                    statement.setString(4, ((Pengirim) t).getAlamat());
                }
                statement.executeUpdate(); 
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

        @Override
        public void hapus(Akun t) throws SQLException {
            try {
                statement = connection.prepareStatement(SQL_DELETE.replace("TABLE_NAME", "pengirim"));
                statement.setString(1, t.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

        @Override
        public Akun getById(String id) throws SQLException {
            try {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(SQL_GETBYID.replace("TABLE_NAME", "pengirim"));
                statement.setString(1, id);
                result = statement.executeQuery();
                Akun t = null;
                if (result.next()) {
                    t = new Pengirim(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
                }
                return t;
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

        @Override
        public ArrayList<Akun> getAll() throws SQLException {
            try {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(SQL_GETALL.replace("TABLE_NAME", "pengirim"));
                Akun t;
                result = statement.executeQuery();
                ArrayList<Akun> list = new ArrayList<>();   
                while (result.next()) {
                    t = new Pengirim(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
                    list.add(t);
                }
                return list;
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

    }
    
    public class KurirDAO implements DAOManageable<Akun>{
        private final Connection connection;
        public KurirDAO(Connection connection) throws SQLException{
            this.connection = connection;
        }

        @Override
        public void tambah(Akun t) throws SQLException {
            try {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(SQL_INSERT.replace("TABLE_NAME", "kurir").replace("VLS", "(?, ?, ?, ?, ?, ?)"));
                statement.setString(1, (t.getId()==null)?null:t.getId());
                statement.setString(2, t.getUsername());
                statement.setString(3, t.getPassword());
                statement.setString(4, t.getNoHp());
                if (t instanceof Kurir) {
                    statement.setString(5, ((Kurir) t).getKota());
                    statement.setString(6, ((Kurir) t).getStatus());
                }
                statement.executeUpdate();
                t.setId((t.getId()==null)?getKurirDAO().getAll().get(getKurirDAO().getAll().size()-1).getId():t.getId());
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

        @Override
        public void update(Akun t) throws SQLException {
            try {
                if (t.getPaket()!=null) {
                    for (Paket paket : t.getPaket()) {
                        getPaketDAO().update(paket);
                    }
                }
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(SQL_UPDATE.replace("TABLE_NAME", "kurir").replace("COLUMN", "username=?, password=?, phone_number=?, address=?, status=?"));
                statement.setString(6, (t.getId()==null)?null:t.getId());
                statement.setString(1, t.getUsername());
                statement.setString(2, t.getPassword());
                statement.setString(3, t.getNoHp());
                if (t instanceof Kurir) {
                    statement.setString(4, ((Kurir) t).getKota());
                    statement.setString(5, ((Kurir) t).getStatus());
                }
                statement.executeUpdate(); 
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

        @Override
        public void hapus(Akun t) throws SQLException {
            try {
                statement = connection.prepareStatement(SQL_DELETE.replace("TABLE_NAME", "kurir"));
                statement.setString(1, t.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

        @Override
        public Akun getById(String id) throws SQLException {
            try {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(SQL_GETBYID.replace("TABLE_NAME", "kurir"));
                statement.setString(1, id);
                result = statement.executeQuery();
                Akun t = null;
                if (result.next()) {
                    t = new Kurir(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(6), result.getString(5));
                }
                return t;
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

        @Override
        public ArrayList<Akun> getAll() throws SQLException {
            try {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(SQL_GETALL.replace("TABLE_NAME", "kurir"));
                Akun t;
                result = statement.executeQuery();
                ArrayList<Akun> list = new ArrayList<>();   
                while (result.next()) {
                    t = new Kurir(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(6), result.getString(5));
                    list.add(t);
                }
                return list;
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

    }
    
    public class GudangDAO implements DAOManageable<Gudang>{
        private final Connection connection;
        public GudangDAO(Connection connection) throws SQLException{
            this.connection = connection;
        }

        @Override
        public void tambah(Gudang t) throws SQLException {
            try {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(SQL_INSERT.replace("TABLE_NAME", "gudang").replace("VLS", "(?, ?)"));
                statement.setString(1, (t.getId()==null)?null:t.getId());
                statement.setString(2, t.getKota());
                statement.executeUpdate();
                t.setId((t.getId()==null)?getGudangDAO().getAll().get(getGudangDAO().getAll().size()-1).getId():t.getId());
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

        @Override
        public void update(Gudang t) throws SQLException {
            try {
                if (t.getPaket()!=null) {
                    for (Paket paket : t.getPaket()) {
                        getPaketDAO().update(paket);
                    }
                }
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(SQL_UPDATE.replace("TABLE_NAME", "gudang").replace("COLUMN", "city=?"));
                statement.setString(2, (t.getId()==null)?null:t.getId());
                statement.setString(1, t.getKota());
                statement.executeUpdate(); 
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

        @Override
        public void hapus(Gudang t) throws SQLException {
            try {
                statement = connection.prepareStatement(SQL_DELETE.replace("TABLE_NAME", "gudang"));
                statement.setString(1, t.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

        @Override
        public Gudang getById(String id) throws SQLException {
            try {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(SQL_GETBYID.replace("TABLE_NAME", "gudang"));
                statement.setString(1, id);
                result = statement.executeQuery();
                Gudang t = null;
                if (result.next()) {
                    t = new Gudang(result.getString(1), result.getString(2));
                }
                return t;
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

        @Override
        public ArrayList<Gudang> getAll() throws SQLException {
            try {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(SQL_GETALL.replace("TABLE_NAME", "gudang"));
                Gudang t;
                result = statement.executeQuery();
                ArrayList<Gudang> list = new ArrayList<>();   
                while (result.next()) {
                    t = new Gudang(result.getString(1), result.getString(2));
                    list.add(t);
                }
                return list;
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

    }
    
    public class PaketDAO implements DAOManageable<Paket>{
        private final Connection connection;
        public PaketDAO(Connection connection) throws SQLException{
            this.connection = connection;
        }

        @Override
        public void tambah(Paket t) throws SQLException {
            try {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(SQL_INSERT.replace("TABLE_NAME", "paket").replace("VLS", "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"));
                statement.setString(1, (t.getId()==null)?null:t.getId());
                statement.setString(2, t.getTanggalSampai()!=null?t.getTanggalSampai().toString():null);
                statement.setString(3, t.getTanggalDibuat().toString());
                statement.setString(4, t.getTipe());
                statement.setString(5, t.getStatus());
                statement.setString(6, String.valueOf(t.hargaBarang()+t.hargaPajak()));
                statement.setString(7, String.valueOf(t.getJarak()));
                statement.setString(8, t.getAlamatTujuan());
                statement.setString(9, (t.getKurir()!=null)?t.getKurir().getId():"0");
                statement.setString(10, (t.getPengirim()!=null)?t.getPengirim().getId():"0");
                statement.setString(11, (t.getGudang()!=null)?t.getGudang().getId():"0");
                statement.executeUpdate();
                t.setId((t.getId()==null)?getPaketDAO().getAll().get(getPaketDAO().getAll().size()-1).getId():t.getId());
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }
        
        @Override
        public void update(Paket t) throws SQLException {
            try {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(SQL_UPDATE.replace("TABLE_NAME", "paket").replace("COLUMN", "arrival_date=?, sent_date=?, tipe=?, status=?, price=?, distance=?, address=?, idcourier=?, idsender=?, idwarehouse=?"));
                statement.setString(11, (t.getId()==null)?null:t.getId());
                statement.setString(1, t.getTanggalSampai()!=null?t.getTanggalSampai().toString():null);
                statement.setString(2, t.getTanggalDibuat().toString());
                statement.setString(3, t.getTipe());
                statement.setString(4, t.getStatus());
                statement.setString(5, String.valueOf(t.hargaBarang()+t.hargaPajak()));
                statement.setString(6, String.valueOf(t.getJarak()));
                statement.setString(7, t.getAlamatTujuan());
                statement.setString(8, (t.getKurir()!=null)?t.getKurir().getId():"0");
                statement.setString(9, (t.getPengirim()!=null)?t.getPengirim().getId():"0");
                statement.setString(10, (t.getGudang()!=null)?t.getGudang().getId():"0");
                statement.executeUpdate();
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

        @Override
        public void hapus(Paket t) throws SQLException {
            try {
                statement = connection.prepareStatement(SQL_DELETE.replace("TABLE_NAME", "paket"));
                statement.setString(1, t.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

        @Override
        public Paket getById(String id) throws SQLException {
            try {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(SQL_GETBYID.replace("TABLE_NAME", "paket"));
                statement.setString(1, id);
                result = statement.executeQuery();
                Paket t = null;
                if (result.next()) {
                    t = new Paket(result.getString(1), LocalDate.parse(result.getString(3), DateTimeFormatter.ISO_DATE), result.getString(4), result.getString(5), result.getInt(7), result.getString(8));
                    t.setTanggalSampai((result.getString(2) != null)?LocalDate.parse(result.getString(2), DateTimeFormatter.ISO_DATE):null);
                    t.setPengirim((!result.getString(10).equals("0"))?(Pengirim)getPengirimDAO().getById(result.getString(10)):null);
                    t.setKurir((!result.getString(9).equals("0"))?(Kurir)getKurirDAO().getById(result.getString(9)):null);
                    t.setGudang((!result.getString(11).equals("0"))?getGudangDAO().getById(result.getString(11)):null);
                }
                return t;
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

        @Override
        public ArrayList<Paket> getAll() throws SQLException {
            try {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(SQL_GETALL.replace("TABLE_NAME", "paket"));
                result = statement.executeQuery();
                Paket t;
                ArrayList<Paket> list = new ArrayList<>(); 
                while (result.next()) {
                    t = new Paket(result.getString(1), LocalDate.parse(result.getString(3), DateTimeFormatter.ISO_DATE), result.getString(4), result.getString(5), result.getInt(7), result.getString(8));
                    t.setTanggalSampai(result.getString(2) != null?LocalDate.parse(result.getString(2), DateTimeFormatter.ISO_DATE):null);
                    t.setKurir(!result.getString(9).equals("0")?(Kurir)getKurirDAO().getById(result.getString(9)):null);
                    t.setPengirim(!result.getString(10).equals("0")?(Pengirim)getPengirimDAO().getById(result.getString(10)):null);
                    t.setGudang(!result.getString(11).equals("0")?getGudangDAO().getById(result.getString(11)):null);
                    list.add(t);
                }
                return list;
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

    }
    
    public class ItemDAO implements DAOManageable<Item>{
        private final Connection connection;
        
        public ItemDAO(Connection connection) throws SQLException{
            this.connection = connection;
        }

        @Override
        public void tambah(Item t) throws SQLException {
            try {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(SQL_INSERT.replace("TABLE_NAME", "item").replace("VLS", "(?, ?, ?, ?, ?, ?, ?)"));
                statement.setString(1, (t.getId()==null)?null:t.getId());
                statement.setString(2, t.getNama());
                statement.setDouble(3, t.getDimensi()[0]);
                statement.setDouble(4, t.getDimensi()[1]);
                statement.setDouble(5, t.getDimensi()[2]);
                statement.setDouble(6, t.getBerat());
                statement.setString(7, (t.getPaket()!=null)?String.valueOf(t.getPaket().getId()):"0");
                statement.executeUpdate();
                t.setId((t.getId()==null)?getItemDAO().getAll().get(getItemDAO().getAll().size()-1).getId():t.getId());
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

        @Override
        public void update(Item t) throws SQLException {
            try {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(SQL_UPDATE.replace("TABLE_NAME", "item").replace("COLUMN", "name=?, length=?, width=?, height=?, weight=?, idpaket=?"));
                statement.setString(7, (t.getId()==null)?null:t.getId());
                statement.setString(1, t.getNama());
                statement.setDouble(2, t.getDimensi()[0]);
                statement.setDouble(3, t.getDimensi()[1]);
                statement.setDouble(4, t.getDimensi()[2]);
                statement.setDouble(5, t.getBerat());
                statement.setString(6, (t.getPaket()!=null)?String.valueOf(t.getPaket().getId()):"0");
                statement.executeUpdate(); 
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

        @Override
        public void hapus(Item t) throws SQLException {
            try {
                statement = connection.prepareStatement(SQL_DELETE.replace("TABLE_NAME", "item"));
                statement.setString(1, t.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

        @Override
        public Item getById(String id) throws SQLException {
            try {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(SQL_GETBYID.replace("TABLE_NAME", "item"));
                statement.setString(1, id);
                result = statement.executeQuery();
                Item t = null;
                if (result.next()) {
                    t = new Item(result.getString(1), result.getString(2), result.getDouble(6));
                    t.setDimensi(new double[]{result.getDouble(3), result.getDouble(4), result.getDouble(5)});
                    t.setPaket(!result.getString(7).equals("0")?getPaketDAO().getById(result.getString(7)):null);
                }
                return t;
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

        @Override
        public ArrayList<Item> getAll() throws SQLException {
            try {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(SQL_GETALL.replace("TABLE_NAME", "item"));
                Item t;
                result = statement.executeQuery();
                ArrayList<Item> list = new ArrayList<>();   
                while (result.next()) {
                    t = new Item(result.getString(1), result.getString(2), result.getDouble(6));
                    t.setDimensi(new double[]{result.getDouble(3), result.getDouble(4), result.getDouble(5)});
                    t.setPaket(!result.getString(7).equals("0")?getPaketDAO().getById(result.getString(7)):null);
                    list.add(t);
                }
                return list;
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }

    }
    
    public class AdminDAO implements DAOReadable<Admin>{
        private final Connection connection;
        
        public AdminDAO(Connection connection) throws SQLException{
            this.connection = connection;
        }

        @Override
        public ArrayList<Admin> getAll() throws SQLException {
            try {
                Admin t;
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(SQL_GETALL.replace("TABLE_NAME", "admin"));
                result = statement.executeQuery();
                ArrayList<Admin> list = new ArrayList<>();   
                while (result.next()) {
                    t = new Admin(result.getString(1), result.getString(2));
                    list.add(t);
                }
                return list;
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }
        
        
    }
    
    public class PembayaranDAO implements DAOReadable<Pembayaran>{
        private final Connection connection;
        
        public PembayaranDAO(Connection connection) throws SQLException{
            this.connection = connection;
        }
        
        @Override
        public ArrayList<Pembayaran> getAll() throws SQLException {
            try {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(SQL_GETALL.replace("TABLE_NAME", "payment"));
                Pembayaran t;
                result = statement.executeQuery();
                ArrayList<Pembayaran> list = new ArrayList<>();   
                while (result.next()) {
                    t = new Pembayaran(result.getString(1), result.getString(2), result.getInt(3));
                    list.add(t);
                }
                return list;
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }
        
        public void updateBalance(String accNumber, int balance) throws SQLException {
            try {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement("UPDATE payment SET balance = ? WHERE payment.account_number = ?");
                statement.setInt(1, balance);
                statement.setString(2, accNumber);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    connection.setAutoCommit(true);
                    if (statement == null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }
    }
}
