/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeningOpticController;
import BeningOpticModel.datacustomer;
import BeningOpticModel.koneksi;
import BeningOpticModel.kacamata;
import BeningOpticView.DataCustomerV;
import BeningOpticView.DataKacamataV;
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
/**
 *
 * @author User
 */
public class datakacamatacontroller implements ActionListener, MouseListener {
    private kacamata datakc;
    private DataKacamataV frmkc;
        
    public datakacamatacontroller (kacamata datakc, DataKacamataV frmkc){
        this.datakc = datakc;
        this.frmkc = frmkc;
        this.frmkc.tambah.addActionListener(this);
        this.frmkc.simpan.addActionListener(this);
        this.frmkc.edit.addActionListener(this);
        this.frmkc.hapus.addActionListener(this);
        this.frmkc.tabelkacamata.addMouseListener(this);
    }
    
    public void KosongDataKacamata(){
        frmkc.txtkodek.setText(null); 
        frmkc.txtnamak.setText(null);
        frmkc.warna.setText(null);
    }
    
     public void TampilDataKacamata(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Kode Kacamata");
        model.addColumn("Nama Kacamata");
        model.addColumn("Warna");
        
        try{
            int no=1;
            String sql = "SELECT * FROM datakacamata";
            java.sql.Connection conn = (Connection)koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            
            while(res.next()){
                model.addRow(new Object[]{
                 no++,
                res.getString(1),
                res.getString(2),
                res.getString(3)
                });
                
            }
                frmkc.tabelkacamata.setModel(model);
            
                  }catch(SQLException e){
                    System.out.println("Error " + e.getMessage());
                } 
        }
    
     @Override
        public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==frmkc.tambah){
            KosongDataKacamata();
        }else if(ae.getSource()== frmkc.simpan){
            datakc.setKodekacamata(frmkc.txtkodek.getText());
            datakc.setNamakacamata(frmkc.txtnamak.getText());
            datakc.setWarna(frmkc.warna.getText());
  
            try{
                if(datakc.Simpandatakacamata(datakc)){
                    JOptionPane.showMessageDialog(null, "Simpan Data Baru Berhasil");
                    KosongDataKacamata();
                    TampilDataKacamata();
                }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }else if(ae.getSource()==frmkc.edit){
            datakc.setKodekacamata(frmkc.txtkodek.getText());
            datakc.setNamakacamata(frmkc.txtnamak.getText());
            datakc.setWarna(frmkc.warna.getText());

            
            try{
                if(datakc.Updatedatakacamata(datakc)){
                    JOptionPane.showMessageDialog(null, "Update Data Baru Berhasil");
                    KosongDataKacamata();
                    TampilDataKacamata();
                }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }else{
            datakc.setKodekacamata(frmkc.txtkodek.getText());
            try{
                if(datakc.Hapusdatakacamata(datakc)){
                    JOptionPane.showMessageDialog(null, "Hapus Data Kacamata Berhasil");
                    KosongDataKacamata();
                    TampilDataKacamata();
                }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
        @Override
        public void mouseClicked(MouseEvent me){
            if(me.getSource()==frmkc.tabelkacamata){
        
                
                int baris = frmkc.tabelkacamata.rowAtPoint(me.getPoint());
                String kodekacamata= frmkc.tabelkacamata.getValueAt(baris, 1).toString();
                frmkc.txtkodek.setText(kodekacamata);
                String namakacamata = frmkc.tabelkacamata.getValueAt(baris, 2).toString();
                frmkc.txtnamak.setText(namakacamata);
                String warna=frmkc.tabelkacamata.getValueAt(baris, 3).toString();
                frmkc.warna.setText(namakacamata);
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
