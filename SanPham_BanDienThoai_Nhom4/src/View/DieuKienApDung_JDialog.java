package View;

import Extensions.XImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import Model.model_dieukienapdung;
import Repository.qldieukienapdung;
import javax.swing.JOptionPane;

public class DieuKienApDung_JDialog extends javax.swing.JDialog {

    qldieukienapdung ql = new qldieukienapdung();

    public DieuKienApDung_JDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        init();
        loadtable((ArrayList<model_dieukienapdung>) ql.selectAll());
        load_combobox();
        if (cbb_loaidj.getSelectedItem().toString().equalsIgnoreCase("Giá tiền")) {
            txt_gtr.setVisible(true);
            cbb_sl.setVisible(false);
            cbb_sl.setValue(0);
        } else {
            txt_gtr.setVisible(false);
            cbb_sl.setVisible(true);
            txt_gtr.setText("");
        }
    }

    void init() {
        setIconImage(XImage.getAppIcon().getImage());
        setTitle("ĐIều kiện áp dụng");
        setLocationRelativeTo(null);
    }

    public void load_combobox() {
        ArrayList<String> list_makh = new ArrayList<>();
        for (String x : ql.select_mahang()) {
            list_makh.add(x);
        }
        DefaultComboBoxModel<String> model_kh = new DefaultComboBoxModel<>(list_makh.toArray(String[]::new));
        cbb_makh.setModel(model_kh);
    }

    public void loadtable(ArrayList<model_dieukienapdung> list) {
        DefaultTableModel dtm = (DefaultTableModel) tbl_showhang.getModel();
        dtm.setRowCount(0);
        for (model_dieukienapdung x : list) {
            String gtr = x.getGtrsl() + x.getSl();
            String gtr_new = gtr.replace("null", "");
            dtm.addRow(new Object[]{
                x.getMadk(),
                x.getLoaidk(),
                x.getHang(),
                gtr_new,
                x.getMakh(),});

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_showhang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_gtr = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbb_makh = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbb_loaidj = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbb_sl = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tbl_showhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "MãDK", "Loại điều kiện", "Hạng", "Giá trị", "Mã Hạng"
            }
        ));
        tbl_showhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_showhangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_showhang);

        jLabel1.setText("Số tiền");

        jLabel3.setText("Mã hạng");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Điều kiện áp dụng");

        jLabel2.setText("Loại điều kiện");

        cbb_loaidj.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giá tiền", "Số Lượng" }));
        cbb_loaidj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_loaidjActionPerformed(evt);
            }
        });

        jLabel5.setText("Số lượng");

        cbb_sl.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 2));

        jButton3.setText("Xóa");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton4.setText("Làm mới");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setText("Sửa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_gtr, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_sl, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbb_loaidj, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbb_makh, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbb_loaidj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbb_makh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_gtr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbb_sl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 5, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            if (check_themdkgg()) {
                int row = tbl_showhang.getSelectedRow();
                String madk = tbl_showhang.getValueAt(row, 0).toString();
                String loaidk = cbb_loaidj.getSelectedItem().toString();
                String gtr;
                if (cbb_loaidj.getSelectedItem().toString().equalsIgnoreCase("Giá tiền")) {
                    gtr = txt_gtr.getText();
                    cbb_sl.setValue(0);
                } else {
                    gtr = cbb_sl.getValue().toString();
                }
                String makh = cbb_makh.getSelectedItem().toString();
                if (loaidk.equalsIgnoreCase("Giá tiền")) {
                    model_dieukienapdung dkad = new model_dieukienapdung(madk, gtr, null, loaidk, makh, null, null);
                    ql.update(dkad);
                } else if (loaidk.equalsIgnoreCase("Số Lượng")) {
                    model_dieukienapdung dkad = new model_dieukienapdung(madk, null, gtr, loaidk, makh, null, null);
                    ql.update(dkad);
                } else {
                    model_dieukienapdung dkad = new model_dieukienapdung(madk, null, null, loaidk, makh, null, null);
                    ql.update(dkad);
                }
                loadtable((ArrayList<model_dieukienapdung>) ql.selectAll());
                JOptionPane.showMessageDialog(this, "sửa thành công");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "sửa thất bại");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        try {
            int row = tbl_showhang.getSelectedRow();
            String madk = tbl_showhang.getValueAt(row, 0).toString();
            ql.delete(madk);
            loadtable((ArrayList<model_dieukienapdung>) ql.selectAll());
            JOptionPane.showMessageDialog(this, "xóa thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "xóa thất bại");
        }
    }//GEN-LAST:event_jButton3MouseClicked
    public boolean check_themdkgg() {
        if (txt_gtr.getText().equalsIgnoreCase("") && cbb_sl.getValue().toString().equals("0")) {
            JOptionPane.showMessageDialog(this, "Hãy thêm giá trị điều kiện");
            return false;
        } else {
            return true;
        }
    }
    private void tbl_showhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_showhangMouseClicked
        // TODO add your handling code here:
        int row = tbl_showhang.getSelectedRow();
        txt_gtr.setText(tbl_showhang.getValueAt(row, 3).toString());
        cbb_makh.setSelectedItem(tbl_showhang.getValueAt(row, 4).toString());
        cbb_loaidj.setSelectedItem(tbl_showhang.getValueAt(row, 1).toString());
    }//GEN-LAST:event_tbl_showhangMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            if (check_themdkgg()) {
                List<model_dieukienapdung> l = ql.select_id();
                String id = l.get(0).getMadk();
                // Tách phần ký tự và phần số
                String alphaPart = id.replaceAll("[^A-Za-z]", ""); // Lấy phần ký tự
                String numericPart = id.replaceAll("[^0-9]", ""); // Lấy phần số

                // Tăng giá trị số lên 1
                int numericValue = Integer.parseInt(numericPart);
                numericValue++;

                // Tạo chuỗi mới
                String madk = alphaPart + String.format("%03d", numericValue);

                String gtr;
                if (cbb_loaidj.getSelectedItem().toString().equalsIgnoreCase("Giá tiền")) {
                    gtr = txt_gtr.getText();
                    cbb_sl.setValue(0);
                } else {
                    gtr = cbb_sl.getValue().toString();
                }
                String makh = cbb_makh.getSelectedItem().toString();
                String loaidk = cbb_loaidj.getSelectedItem().toString();
                if (loaidk.equalsIgnoreCase("Giá tiền")) {
                    model_dieukienapdung dkad = new model_dieukienapdung(madk, gtr, null, loaidk, makh, null, null);
                    ql.insert(dkad);
                } else if (loaidk.equalsIgnoreCase("Số Lượng")) {
                    model_dieukienapdung dkad = new model_dieukienapdung(madk, null, gtr, loaidk, makh, null, null);
                    ql.insert(dkad);
                } else {
                    model_dieukienapdung dkad = new model_dieukienapdung(madk, null, null, loaidk, makh, null, null);
                    ql.insert(dkad);
                }
                loadtable((ArrayList<model_dieukienapdung>) ql.selectAll());
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        cbb_loaidj.setSelectedIndex(0);
        cbb_makh.setSelectedIndex(0);
        txt_gtr.setText("");
        cbb_sl.setValue(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cbb_loaidjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_loaidjActionPerformed
        // TODO add your handling code here:
        if (cbb_loaidj.getSelectedItem().toString().equalsIgnoreCase("Giá tiền")) {
            txt_gtr.setVisible(true);
            cbb_sl.setVisible(false);
            cbb_sl.setValue(0);
        } else {
            txt_gtr.setVisible(false);
            cbb_sl.setVisible(true);
            txt_gtr.setText("");
        }
    }//GEN-LAST:event_cbb_loaidjActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DieuKienApDung_JDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DieuKienApDung_JDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DieuKienApDung_JDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DieuKienApDung_JDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DieuKienApDung_JDialog dialog = new DieuKienApDung_JDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbb_loaidj;
    private javax.swing.JComboBox<String> cbb_makh;
    private javax.swing.JSpinner cbb_sl;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_showhang;
    private javax.swing.JTextField txt_gtr;
    // End of variables declaration//GEN-END:variables
}
