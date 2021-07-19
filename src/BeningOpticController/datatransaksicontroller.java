/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeningOpticController;
import BeningOpticModel.kacamata;
import BeningOpticModel.transaksi;
import BeningOpticModel.koneksi;
import BeningOpticView.DataKacamataV;
import BeningOpticView.DataTransaksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.event.PopupMenuEvent;

/**
 *
 * @author User
 */
public class datatransaksicontroller implements ActionListener, MouseListener {
    private transaksi datatr;
    private DataTransaksi frmtr;
        
    public datatransaksicontroller (transaksi datatr, DataTransaksi frmtr){
        this.datatr = datatr;
        this.frmtr = frmtr;
        this.frmtr.simpan.addActionListener(this);
        this.frmtr.edit.addActionListener(this);
        this.frmtr.hapus.addActionListener(this);
        this.frmtr.tabeltransaksi.addMouseListener(this);
    }

    
    public void Kosongtransaksi(){
        frmtr.idt.setText(null); 
        frmtr.idc.getSelectedItem();
        frmtr.namak.setText(null); 
        frmtr.kodek.getSelectedItem();
        frmtr.jenis.getSelectedItem();
        frmtr.ukuran.setText(null);
        frmtr.tanggal.setDate(null);
        frmtr.harga.setText(null);
    }
    
     public void Tampiltransaksi(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Id Transaksi");
        model.addColumn("Id Customer");
        model.addColumn("Nama Customer");
        model.addColumn("Kode Kacamata");
        model.addColumn("Jenis Lensa");
        model.addColumn("Ukuran Lensa");
        model.addColumn("Tanggal Transaksi");
        model.addColumn("Harga");
        
        try{
            int no=1;
            String sql = "SELECT * FROM datatransaksi";
            java.sql.Connection conn = (Connection)koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            
            while(res.next()){
                model.addRow(new Object[]{
                 no++,
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
                res.getString(5),
                res.getString(6),
                res.getString(7),
                res.getString(8)
                });
                
            }
                frmtr.tabeltransaksi.setModel(model);
            
                  }catch(SQLException e){
                    System.out.println("Error " + e.getMessage());
                } 
        }
  
     /* public void id() {
        String sql = "Select idc FROM datacustomer";
        
        try {
            java.sql.Connection conn=(Connection)koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            while (res.next()) {
                this.frmtr.idc.addItem(res.getString(1));    
            }
        }
        catch(SQLException e){
            
        }
    }*/
         public void id(){
        try{
           String sql = "Select * From datacustomer";
           Connection conn=(Connection)koneksi.configDB();
           java.sql.Statement stm=conn.createStatement();
           ResultSet rs = stm.executeQuery(sql);
           
           while(rs.next()){
               frmtr.idc.addItem(rs.getString(1));
           }
           
           rs.last();
           int data = rs.getRow();
           rs.first();
           
        }catch(Exception e){

        }
         }
    public void pemesanan() {
        String sql = "Select kodek FROM datakacamata";
        
        try {
            java.sql.Connection conn=(Connection)koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            while (res.next()) {
                this.frmtr.kodek.addItem(res.getString(1));    
            }
        }
        catch(SQLException e){
            
        }
         }
          
          
          
       @Override
        public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==frmtr.tambah){
            Kosongtransaksi();
        }if(ae.getSource()== frmtr.simpan){
            datatr.setIdt(frmtr.idt.getText());
            datatr.setIdc((String) frmtr.idc.getSelectedItem());
            datatr.setNamak(frmtr.namak.getText());
            datatr.setKodek((String) frmtr.kodek.getSelectedItem());
            datatr.setJenis((String) frmtr.jenis.getSelectedItem());
            datatr.setUkuran(frmtr.ukuran.getText());
            datatr.setTanggal(frmtr.tanggal.getDate());
            datatr.setHarga(frmtr.harga.getText());
  
            try{
                if(datatr.Simpandatatransaksi(datatr)){
                    JOptionPane.showMessageDialog(null, "Simpan Data Baru Berhasil");
                    Kosongtransaksi();
                    Tampiltransaksi();
                }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }else if(ae.getSource()==frmtr.edit){
            datatr.setIdt(frmtr.idt.getText());
            datatr.setIdc((String) frmtr.idc.getSelectedItem());
            datatr.setNamak(frmtr.namak.getText());
            datatr.setKodek((String) frmtr.kodek.getSelectedItem());
            datatr.setJenis((String) frmtr.jenis.getSelectedItem());
            datatr.setUkuran(frmtr.ukuran.getText());
            datatr.setTanggal(frmtr.tanggal.getDate());
            datatr.setHarga(frmtr.harga.getText());
            
            try{
                if(datatr.Updatedatatransaksi(datatr)){
                    JOptionPane.showMessageDialog(null, "Update Data Baru Berhasil");
                    Kosongtransaksi();
                    Tampiltransaksi();
                }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }else{
            datatr.setIdt(frmtr.idt.getText());
            try{
                if(datatr.Hapusdatatransaksi(datatr)){
                    JOptionPane.showMessageDialog(null, "Hapus Data Transaksi Berhasil");
                    Kosongtransaksi(); 
                    Tampiltransaksi();
                }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
        @Override
        public void mouseClicked(MouseEvent me){
            if(me.getSource()==frmtr.tabeltransaksi){
        
                
                int baris = frmtr.tabeltransaksi.rowAtPoint(me.getPoint());
                String idt= frmtr.tabeltransaksi.getValueAt(baris, 1).toString();
                frmtr.idt.setText(idt);
                String idc=frmtr.tabeltransaksi.getValueAt(baris, 2).toString();
                frmtr.idc.setSelectedItem(idc);
                String namak= frmtr.tabeltransaksi.getValueAt(baris, 3).toString();
                frmtr.namak.setText(namak);
                String kodek=frmtr.tabeltransaksi.getValueAt(baris, 4).toString();
                frmtr.kodek.setSelectedItem(kodek);
                String jenis=frmtr.tabeltransaksi.getValueAt(baris, 5).toString();
                frmtr.jenis.setSelectedItem(jenis);
                String ukuran= frmtr.tabeltransaksi.getValueAt(baris, 6).toString();
                frmtr.ukuran.setText(ukuran);
                 String tanggal=(String)frmtr.tabeltransaksi.getModel().getValueAt(baris, 7);
            try{
            SimpleDateFormat tgls = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date tanggals=tgls.parse(tanggal);
            frmtr.tanggal.setDate(tanggals);
            }catch(Exception ex){
                ex.printStackTrace();
            }
                String harga= frmtr.tabeltransaksi.getValueAt(baris, 8).toString();
                frmtr.harga.setText(harga);
            }
        }
               
        @Override
        public void mousePressed(MouseEvent me){
            
        }
        @Override
        public void mouseReleased(MouseEvent me){
            
        }
        @Override
        public void mouseEntered(MouseEvent me){
            
        }
        @Override
        public void mouseExited(MouseEvent me){                 
        }
}

