/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeningOpticModel;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class transaksi extends koneksi {
       
    private String idt;
    private String idc;
    private String kodek;
    private String jenis;
    private String ukuran;
    private Date tanggal;
    private String harga;
    private String namak;

    public String getNamak() {
        return namak;
    }

    public void setNamak(String namak) {
        this.namak = namak;
    }

    public String getIdt() {
        return idt;
    }

    public void setIdt(String idt) {
        this.idt = idt;
    }

    public String getIdc() {
        return idc;
    }

    public void setIdc(String idc) {
        this.idc = idc;
    }

    public String getKodek() {
        return kodek;
    }

    public void setKodek(String kodek) {
        this.kodek = kodek;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }


    
   
    public boolean Simpandatatransaksi(transaksi datatr) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)koneksi.configDB();
        String sq="alter table datatransaksi auto_increment=0";
            java.sql.PreparedStatement pst=conn.prepareStatement(sq);
            pst.execute();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        String sql = "INSERT INTO datatransaksi (idt, idc, namak, kodek, jenis, ukuran, tanggal, harga) VALUES(?,?,?,?,?,?,?,?)";
        
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, datatr.getIdt());
            pstm.setString(2, datatr.getIdc());
            pstm.setString(3, datatr.getNamak());
            pstm.setString(4, datatr.getKodek());
            pstm.setString(5, datatr.getJenis());
            pstm.setString(6, datatr.getUkuran());
            pstm.setString(7, df.format(getTanggal()));
            pstm.setString(8, datatr.getHarga());
            pstm.execute();
            return true;
    
        }catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
     
        /**public boolean Updatedatatransaksi(transaksi datatr) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)koneksi.configDB();
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        String sql = "UPDATE datatransaksi SET idt=?, idc=?, kodek=?, jenis=?, ukuran=?, tanggal=?, harga=? WHERE idt=?";
    
        try{
            pstm = conn.prepareStatement(sql);
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, datatr.getIdt());
            pstm.setString(2, datatr.getIdc());
            pstm.setString(3, datatr.getKodek());
            pstm.setString(4, datatr.getJenis());
            pstm.setString(5, datatr.getUkuran());
            pstm.setString(6, df.format(getTanggal()));
            pstm.setString(7, datatr.getHarga());
            pstm.setString(8, datatr.getIdt());
            pstm.execute();
            return true;*/
            
    public boolean Updatedatatransaksi(transaksi datatr) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)koneksi.configDB();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        String sql = "UPDATE datatransaksi SET idt=?, idc=?, namak=?, kodek=?, jenis=?, ukuran=?, tanggal=?, harga=? WHERE idt=?";
    
        try{
            pstm = conn.prepareStatement(sql);
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, datatr.getIdt());
            pstm.setString(2, datatr.getIdc());
            pstm.setString(3, datatr.getNamak());
            pstm.setString(4, datatr.getKodek());
            pstm.setString(5, datatr.getJenis());
            pstm.setString(6, datatr.getUkuran());
            pstm.setString(7, df.format(getTanggal()));
            pstm.setString(8, datatr.getHarga());
            pstm.setString(9, datatr.getIdt());
            pstm.execute();
            return true;
            

        }catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean Hapusdatatransaksi(transaksi datatr) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)koneksi.configDB();
        
        String sql = "DELETE FROM datatransaksi WHERE idt=?";
        
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, datatr.getIdt());
            pstm.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
}



