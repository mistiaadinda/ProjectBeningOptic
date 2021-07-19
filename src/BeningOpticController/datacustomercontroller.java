/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeningOpticController;
import BeningOpticModel.koneksi;
import BeningOpticModel.datacustomer;
import BeningOpticView.DataCustomerV;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class datacustomercontroller implements ActionListener, MouseListener{
    private datacustomer data;
    private DataCustomerV frm;
        
    public datacustomercontroller (datacustomer data, DataCustomerV frm){
        this.data = data;
        this.frm = frm;
        this.frm.tambah.addActionListener(this);
        this.frm.simpan.addActionListener(this);
        this.frm.edit.addActionListener(this);
        this.frm.hapus.addActionListener(this);
        this.frm.tabelcustomer.addMouseListener(this);
    }
    
    public void KosongDataCustomer(){
        frm.txtid.setText(null); 
        frm.txtnama.setText(null);
        frm.txtnohp.setText(null);
        frm.txtalamat.setText(null);
    }
    
     public void TampilDataCustomer(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("id Customer");
        model.addColumn("Nama Customer");
        model.addColumn("No Hp");
        model.addColumn("Alamat");
        
        try{
            int no=1;
            String sql = "SELECT * FROM datacustomer";
            java.sql.Connection conn = (Connection)koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            
            while(res.next()){
                model.addRow(new Object[]{
                 no++,
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4)
                });
                
            }
                frm.tabelcustomer.setModel(model);
            
                  }catch(SQLException e){
                    System.out.println("Error " + e.getMessage());
                } 
        }
    
     @Override
        public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==frm.tambah){
            KosongDataCustomer();
        }else if(ae.getSource()== frm.simpan){
            data.setId(frm.txtid.getText());
            data.setNama(frm.txtnama.getText());
            data.setNoHp(frm.txtnohp.getText());
            data.setAlamat(frm.txtalamat.getText());
            
            try{
                if(data.SimpanCustomer(data)){
                    JOptionPane.showMessageDialog(null, "Simpan Data Baru Berhasil");
                    KosongDataCustomer();
                    TampilDataCustomer();
                }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }else if(ae.getSource()==frm.edit){
            data.setId(frm.txtid.getText());
            data.setNama(frm.txtnama.getText());
            data.setNoHp(frm.txtnohp.getText());
            data.setAlamat(frm.txtalamat.getText());
            
            try{
                if(data.UpdateCustomer(data)){
                    JOptionPane.showMessageDialog(null, "Update Data Baru Berhasil");
                    KosongDataCustomer();
                    TampilDataCustomer();
                }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }else{
            data.setId(frm.txtid.getText());
            try{
                if(data.HapusCustomer(data)){
                    JOptionPane.showMessageDialog(null, "Hapus Data Customer Berhasil");
                    KosongDataCustomer();
                    TampilDataCustomer();
                }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
        @Override
        public void mouseClicked(MouseEvent me){
            if(me.getSource()==frm.tabelcustomer){
        
                
                int baris = frm.tabelcustomer.rowAtPoint(me.getPoint());
                String id = frm.tabelcustomer.getValueAt(baris, 1).toString();
                frm.txtid.setText(id);
                String nama = frm.tabelcustomer.getValueAt(baris, 2).toString();
                frm.txtnama.setText(nama);
                String NoHp = frm.tabelcustomer.getValueAt(baris, 3).toString();
                frm.txtnohp.setText(NoHp);
                String Alamat = frm.tabelcustomer.getValueAt(baris, 4).toString();
                frm.txtalamat.setText(Alamat);
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
