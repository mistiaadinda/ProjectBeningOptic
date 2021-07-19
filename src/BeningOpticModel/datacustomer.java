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
import javax.swing.JOptionPane;

public class datacustomer {
    
    private String id;
    private String nama;
    private String NoHp;
    private String Alamat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoHp() {
        return NoHp;
    }

    public void setNoHp(String NoHp) {
        this.NoHp = NoHp;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String Alamat) {
        this.Alamat = Alamat;
    }
    
     public boolean SimpanCustomer(datacustomer data) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)koneksi.configDB();
        
        String sql = "INSERT INTO datacustomer (idc, namac, nohpc, alamatc) VALUES(?,?,?,?)";
        
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, data.getId());
            pstm.setString(2, getNama());
            pstm.setString(3, getNoHp());
            pstm.setString(4, getAlamat());
            pstm.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean UpdateCustomer(datacustomer data) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)koneksi.configDB();
        
        String sql = "UPDATE datacustomer SET idc=?, namac=?, nohpc=?, alamatc=? WHERE idc=?";
    
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, data.getId());
            pstm.setString(2, data.getNama());
            pstm.setString(3, data.getNoHp());
            pstm.setString(4, data.getAlamat());
            pstm.setString(5, data.getId());
            pstm.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean HapusCustomer(datacustomer data) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)koneksi.configDB();
        
        String sql = "DELETE FROM datacustomer WHERE idc=?";
        
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, data.getId());
            pstm.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
}
