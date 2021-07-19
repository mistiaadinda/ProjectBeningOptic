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
/**
 *
 * @author Hp
 */
public class kacamata extends koneksi {
    private String kodekacamata ;
    private String namakacamata ;
    private String warna ;

    public String getKodekacamata() {
        return kodekacamata;
    }

    public void setKodekacamata(String kodekacamata) {
        this.kodekacamata = kodekacamata;
    }

    public String getNamakacamata() {
        return namakacamata;
    }

    public void setNamakacamata(String namakacamata) {
        this.namakacamata = namakacamata;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }
    

    public boolean Simpandatakacamata(kacamata datakc) throws SQLException {
        PreparedStatement pstm=null ;
        Connection conn=(Connection)koneksi.configDB();
        
        String SQL="INSERT INTO datakacamata (kodek, namak, warna) VALUES(?,?,?)";
        
        try{
            pstm=conn.prepareStatement(SQL);
            pstm.setString(1, datakc.getKodekacamata());
            pstm.setString(2, datakc.getNamakacamata());
            pstm.setString(3, datakc.getWarna());
            pstm.execute();
            return true;
        }
        catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean Updatedatakacamata(kacamata datakc) throws SQLException {
        PreparedStatement pstm=null ;
        Connection conn=(Connection)koneksi.configDB();
        
        String SQL="UPDATE datakacamata SET kodek=?, namak=?, warna=? WHERE kodek=?";
        
        try{
            pstm=conn.prepareStatement(SQL);
            pstm=conn.prepareStatement(SQL);
            pstm.setString(1, datakc.getKodekacamata());
            pstm.setString(2, datakc.getNamakacamata());
            pstm.setString(3, datakc.getWarna());
            pstm.setString(4, datakc.getKodekacamata());
            pstm.execute();
            return true;
        }
        catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean Hapusdatakacamata(kacamata datakc) throws SQLException {
        PreparedStatement pstm=null ;
        Connection conn=(Connection)koneksi.configDB();
        
        String SQL="DELETE FROM datakacamata where kodek=?";
        
        try{
            pstm=conn.prepareStatement(SQL);
            pstm.setString(1, datakc.getKodekacamata());
            pstm.execute();
            return true;
        }
        catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }  
}
