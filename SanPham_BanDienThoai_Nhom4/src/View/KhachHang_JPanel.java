package View;

import Extensions.Validation;
import Model.DiaChi;
import Model.HangKhachHang;
import Model.KhachHang;
import Repository.HangKHDAO;
import Repository.KhachHangDAO;
import Extensions.XImage;
import Extensions.quyenDanhNhap;
import java.awt.Image;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class KhachHang_JPanel extends javax.swing.JPanel {

    DefaultTableModel modelCustomer = new DefaultTableModel();
    DefaultTableModel modelTblRank = new DefaultTableModel();
    DefaultComboBoxModel<DiaChi> modelAddress = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<HangKhachHang> modelRank = new DefaultComboBoxModel<>();

    KhachHangDAO kdao = new KhachHangDAO();
    HangKHDAO hdao = new HangKHDAO();

    Validation validation = new Validation();

    int currentPage = 1;
    int totalRecords;
    int totalPages;

    public KhachHang_JPanel() {
        initComponents();

        modelCustomer = (DefaultTableModel) tblCustomer.getModel();
        modelTblRank = (DefaultTableModel) tblRank.getModel();
        cboAddress.setModel((DefaultComboBoxModel) modelAddress);
        cboRank.setModel((DefaultComboBoxModel) modelRank);
        java.util.Date dNow = new java.util.Date();
        txtBirthdate.setDate(dNow);
        loadCboRank();
        updatePagination();
        if (tblCustomer.getRowCount() > 0) {
            tblCustomer.setRowSelectionInterval(0, 0);
            showCustomerDetails();
        }
        tabHangKH();
        txtMaKH.setEditable(false);
    }


    private void loadCustomer(ArrayList<KhachHang> list) {
        modelCustomer.setRowCount(0);
        for (KhachHang kh : list) {
            Object data[] = {kh.getMaKH(), kh.getHoTen(), kh.isGioiTinh() ? "Nam" : "Nữ", kh.getNgaySinh(), kh.getEmail(),
                kh.getSDT(), kh.getHangKhachHang().getTenHang()};
            modelCustomer.addRow(data);
        }
    }

    private void loadCboAddress(int index) {
        modelAddress.removeAllElements();
        String maKH = tblCustomer.getValueAt(index, 0).toString();
        ArrayList<DiaChi> list = kdao.getListDCByMaKH(maKH);
        for (DiaChi dc : list) {
            modelAddress.addElement(dc);
        }
    }

    private void loadCboRank() {
        modelRank.removeAllElements();
        ArrayList<HangKhachHang> list = hdao.getAll();
        for (HangKhachHang hang : list) {
            modelRank.addElement(hang);
        }
    }

    private void updatePagination() {
        int pageSize = Integer.parseInt((String) cboPageSize.getSelectedItem());
        totalRecords = kdao.getAll().size();
        totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        txtPageInfo.setText(currentPage + "/" + totalPages);
        btnPrevPage.setEnabled(currentPage > 1);
        btnNextPage.setEnabled(currentPage < totalPages);
        btnfirstPage.setEnabled(currentPage > 1 && totalPages > 1);
        btnLastPage.setEnabled(currentPage < totalPages && totalPages > 1);
        ArrayList<KhachHang> list = kdao.paginate(currentPage, pageSize);
        loadCustomer(list);
        if (tblCustomer.getRowCount() > 0) {
            tblCustomer.setRowSelectionInterval(0, 0);
            showCustomerDetails();
        }
    }

    private ImageIcon resizeImage(ImageIcon imgIcon) {
        Image im = imgIcon.getImage();
        ImageIcon img = new ImageIcon(im.getScaledInstance(160, 194, im.SCALE_SMOOTH));
        return img;
    }

    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            XImage.save(file);
            ImageIcon icon = XImage.read(file.getName());
            lblImage.setIcon(resizeImage(icon));
            lblImage.setToolTipText(file.getName());
        }
    }

    private void showCustomerDetails() {
        int index = tblCustomer.getSelectedRow();
        String maKH = tblCustomer.getValueAt(index, 0).toString();
        KhachHang kh = kdao.getByID(maKH);
        //Thông tin cá nhân
        if (kh.getAnhDaiDien() != null) {
            lblImage.setToolTipText(kh.getAnhDaiDien());
            lblImage.setIcon(resizeImage(XImage.read(kh.getAnhDaiDien())));
        }
        txtMaKH.setText(kh.getMaKH());
        txtName.setText(kh.getHoTen());
        rdoMale.setSelected(kh.isGioiTinh());
        txtBirthdate.setDate(kh.getNgaySinh());
        txtEmail.setText(kh.getEmail());
        txtPhoneNumber.setText(kh.getSDT());
        rdoActive.setSelected(kh.isTrangThai());
        loadCboAddress(index);
        txaDescription.setText(kh.getGhiChu());
        HangKhachHang hang = kh.getHangKhachHang();
        modelRank.setSelectedItem(hang);
        //Thông tin mua hàng
        lblOrders.setText(kh.getSoDonHang() + "");
        lblExpense.setText(BigDecimal.valueOf(kh.getGiaTriChiTieu()) + "");
        lblBoughtItem.setText(kdao.getSoSP(kh.getMaKH()) + "");
    }

    private KhachHang getCustomer() {
        int count = 0;
        String image = lblImage.getToolTipText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhoneNumber.getText();
        boolean gd = rdoMale.isSelected();
        boolean st = rdoActive.isSelected();
        String description = txaDescription.getText();
        java.util.Date date = txtBirthdate.getDate();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String birthdate = df.format(date);
        HangKhachHang hang = (HangKhachHang) modelRank.getSelectedItem();
        HangKhachHang h = hdao.getByTen(hang.getTenHang());
        count = validation.checkNull(txtName, errorName)
                + validation.checkRegex(txtEmail, errorEmail, "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", "Định dạng email: example1@gmail.com")
                + validation.checkRegex(txtPhoneNumber, errorPhoneNumber, "^0[0-9]{9}", "Vui lòng nhập SDT 10 chữ số");
        if (count == 0) {
            KhachHang kh = new KhachHang(null, name, gd, Date.valueOf(birthdate), email, phone, image,
                    0, 0, description, st, h);
            return kh;
        }
        return null;
    }

    private void insertCustomer() {
        KhachHang kh = getCustomer();
        Random rd = new Random();
        String currentTimeMillis = System.currentTimeMillis() + "";
        String maKH = "KH" + currentTimeMillis.substring(4) + rd.nextInt(1000) + "";
        try {
            if (kh != null) {
                kh.setMaKH(maKH);
                if (kdao.insert(kh) > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công!");
                    updatePagination();
                    if (tblCustomer.getRowCount() > 0) {
                        tblCustomer.setRowSelectionInterval(tblCustomer.getRowCount() - 1, tblCustomer.getRowCount() - 1);
                        showCustomerDetails();
                        errorEmail.setText("  ");
                        errorName.setText("  ");
                        errorPhoneNumber.setText("  ");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm khách hàng thất bại!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateCustomer() {
        KhachHang kh = getCustomer();
        String maKH = tblCustomer.getValueAt(tblCustomer.getSelectedRow(), 0) + "";
        try {
            if (kh != null) {
                kh.setMaKH(maKH);
                int result = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật?");
                if (result == JOptionPane.YES_OPTION) {
                    if (kdao.update(kh) > 0) {
                        JOptionPane.showMessageDialog(this, "Cập nhật khách hàng thành công!");
                        updatePagination();
                        if (tblCustomer.getRowCount() > 0) {
                            tblCustomer.setRowSelectionInterval(tblCustomer.getRowCount() - 1, tblCustomer.getRowCount() - 1);
                            showCustomerDetails();
                            errorEmail.setText("  ");
                            errorName.setText("  ");
                            errorPhoneNumber.setText("  ");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Cập nhật khách hàng thất bại!");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteCustomer() {
        // Kiểm tra quyền quản lý trước khi xóa sản phẩm
        if (!quyenDanhNhap.isManager("Quản lý")) {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền Xóa.");
            return;
        }
        int index = tblCustomer.getSelectedRow();
        String maKH = tblCustomer.getValueAt(index, 0) + "";
        int result = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa?");
        try {
            if (result == JOptionPane.YES_OPTION) {
                if (kdao.delete(maKH) > 0) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    updatePagination();
                    if (tblCustomer.getRowCount() > 0) {
                        tblCustomer.setRowSelectionInterval(tblCustomer.getRowCount() - 1, tblCustomer.getRowCount() - 1);
                        showCustomerDetails();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void search() {
        String inp = txtSearch.getText();
        if (inp == null) {
            updatePagination();
        } else {
            if (kdao.search(inp) != null) {
                loadCustomer(kdao.search(inp));
                if (tblCustomer.getRowCount() > 0) {
                    tblCustomer.setRowSelectionInterval(0, 0);
                    showCustomerDetails();
                }
            } else {
//                JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng!");
                updatePagination();
            }
        }
    }

    private void clearCustomer() {
        txtMaKH.setText("");
        txtName.setText("");
        rdoMale.setSelected(true);
        java.util.Date dNow = new java.util.Date();
        txtBirthdate.setDate(dNow);
        txtEmail.setText("");
        txtPhoneNumber.setText("");
        rdoActive.setSelected(true);
        txaDescription.setText("");
        lblBoughtItem.setText("");
        lblOrders.setText("");
        lblExpense.setText("");
        cboRank.setSelectedIndex(4);
        modelAddress.removeAllElements();
        lblImage.setToolTipText("");
        errorEmail.setText("  ");
        errorName.setText("  ");
        errorPhoneNumber.setText("  ");
    }

    private void first(JTable tbl) {
        tbl.setRowSelectionInterval(0, 0);
    }

    private void prev(JTable tbl) {
        if (tbl.getRowCount() > 0) {
            int index = tbl.getSelectedRow();
            try {
                int prev = index - 1;
                tbl.setRowSelectionInterval(prev, prev);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Bạn đang chọn dòng đầu tiên!");
            }
        }
    }

    private void next(JTable tbl) {
        if (tbl.getRowCount() > 0) {
            int index = tbl.getSelectedRow();
            try {
                int next = index + 1;
                tbl.setRowSelectionInterval(next, next);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Bạn đang chọn dòng cuối cùng!");
            }
        }
    }

    private void last(JTable tbl) {
        tbl.setRowSelectionInterval(tbl.getRowCount() - 1, tbl.getRowCount() - 1);
    }

    //tab Hạng Khách Hàng
    private void tabHangKH() {
        modelTblRank = (DefaultTableModel) tblRank.getModel();
        loadDataToTblHang();
        if (tblRank.getRowCount() > 0) {
            tblRank.setRowSelectionInterval(0, 0);
            showDetailsRank();
        }
    }

    private void loadDataToTblHang() {
        modelTblRank.setRowCount(0);
        ArrayList<HangKhachHang> list = hdao.getAll();
        for (HangKhachHang hang : list) {
            Object data[] = {hang.getMaHang(), hang.getTenHang(), hang.getSoDonHang(), (long) (hang.getGiaTriChiTieu())};
            modelTblRank.addRow(data);
        }
    }

    private void showDetailsRank() {
        int index = tblRank.getSelectedRow();
        txtIdRank.setText(tblRank.getValueAt(index, 0) + "");
        txtRankName.setText(tblRank.getValueAt(index, 1) + "");
        txtRankOrder.setText(tblRank.getValueAt(index, 2) + "");
        txtRankExpense.setText(tblRank.getValueAt(index, 3) + "");
    }

    private int checkOrder() {
        int order = 0;
        int count = 0;
        if (validation.checkNull(txtRankOrder, errorRankOrder) == 0) {
            try {
                order = Integer.parseInt(txtRankOrder.getText());
                if (order > 0) {
                    errorRankOrder.setText("  ");
                } else {
                    count++;
                    errorRankOrder.setText("Vui lòng nhập số đơn hàng > 0");
                }
            } catch (Exception e) {
                count++;
                errorRankOrder.setText("Vui lòng nhập số nguyên!");
            }
        }
        if (count == 0) {
            return order;
        }
        return -1;
    }

    private double checkExpense() {
        double expense = 0;
        int count = 0;
        if (validation.checkNull(txtRankExpense, errorRankExpense) == 0) {
            try {
                expense = Double.parseDouble(txtRankExpense.getText());
                if (expense > 0) {
                    errorRankExpense.setText("  ");
                } else {
                    count++;
                    errorRankExpense.setText("Vui lòng nhập giá trị chi tiêu > 0");
                }
            } catch (Exception e) {
                count++;
                errorRankExpense.setText("Vui lòng nhập số nguyên!");
            }
        }
        if (count == 0) {
            return expense;
        }
        return -1;
    }

    private HangKhachHang getRank() {
        int count = 0;
        int order = 0;
        double expense = 0;
        int id = Integer.parseInt(txtIdRank.getText());
        String name = txtRankName.getText();
        String dh = txtRankOrder.getText();
        String gt = txtRankExpense.getText();
        count = validation.checkNull(txtRankName, errorRankName);
        if (checkOrder() == -1) {
            count++;
        } else {
            order = Integer.parseInt(dh);
        }
        if (checkExpense() == -1) {
            count++;
        } else {
            expense = Double.parseDouble(gt);
        }
        if (count == 0) {
            HangKhachHang h = new HangKhachHang(id, name, order, expense);
            return h;
        }
        return null;
    }

    private void insertRank() {
        HangKhachHang h = getRank();
        if (h != null) {
            if (hdao.getByID(h.getMaHang()) != null) {
                JOptionPane.showMessageDialog(this, "Mã hạng đã tồn tại!");
            } else {
                if (hdao.insert(h) > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                    loadDataToTblHang();
                    tblRank.setRowSelectionInterval(0, 0);
                    showDetailsRank();
                    loadCboRank();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                }
            }
        }
    }

    private void updateRank() {
        HangKhachHang h = getRank();
        if (h != null) {
            int result = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật?");
            if (result == JOptionPane.YES_OPTION) {
                if (hdao.update(h) > 0) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                    loadDataToTblHang();
                    tblRank.setRowSelectionInterval(0, 0);
                    showDetailsRank();
                    loadCboRank();
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
                }
            }
        }
    }

    private void deleteRank() {
        // Kiểm tra quyền quản lý trước khi xóa sản phẩm
        if (!quyenDanhNhap.isManager("Quản lý")) {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền Xóa.");
            return;
        }
        int ma = Integer.parseInt(tblRank.getValueAt(tblRank.getSelectedRow(), 0) + "");
        int result = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa?");
        if (result == JOptionPane.YES_OPTION) {
            if (hdao.delete(ma) > 0) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                loadDataToTblHang();
                tblRank.setRowSelectionInterval(0, 0);
                showDetailsRank();
                loadCboRank();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!");
            }
        }
    }

    private void clearRank() {
        txtIdRank.setText("");
        txtRankName.setText("");
        txtRankOrder.setText("");
        txtRankExpense.setText("");
    }

    private void clearErrorRank() {
        errorRankName.setText("   ");
        errorRankOrder.setText("   ");
        errorRankExpense.setText("   ");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        QLKH = new javax.swing.JTabbedPane();
        KhachHang = new javax.swing.JPanel();
        DSKH = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        btnfirstPage = new javax.swing.JButton();
        btnPrevPage = new javax.swing.JButton();
        btnNextPage = new javax.swing.JButton();
        btnLastPage = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        cboPageSize = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPageInfo = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtStart = new com.toedter.calendar.JDateChooser();
        txtEnd = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        btnSearchDate = new javax.swing.JButton();
        TTCN = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        MaKH = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        Name = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        Gender = new javax.swing.JLabel();
        rdoMale = new javax.swing.JRadioButton();
        rdoFemale = new javax.swing.JRadioButton();
        Birthdate = new javax.swing.JLabel();
        txtBirthdate = new com.toedter.calendar.JDateChooser();
        Email = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        PhoneNumber = new javax.swing.JLabel();
        txtPhoneNumber = new javax.swing.JTextField();
        State = new javax.swing.JLabel();
        rdoActive = new javax.swing.JRadioButton();
        rdoInactive = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cboAddress = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaDescription = new javax.swing.JTextArea();
        errorName = new javax.swing.JLabel();
        errorEmail = new javax.swing.JLabel();
        errorPhoneNumber = new javax.swing.JLabel();
        TTMH = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lblOrders = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblExpense = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblBoughtItem = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cboRank = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cboDetails = new javax.swing.JComboBox<>();
        Edit = new javax.swing.JPanel();
        btnAddCustomer = new javax.swing.JButton();
        btnUpdateCustomer = new javax.swing.JButton();
        btnDeleteCustomer = new javax.swing.JButton();
        btnClearCustomer = new javax.swing.JButton();
        btnFirstCustomer = new javax.swing.JButton();
        btnPrevCustomer = new javax.swing.JButton();
        btnNextCustomer = new javax.swing.JButton();
        btnLastCustomer = new javax.swing.JButton();
        HangKH = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtIdRank = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtRankName = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtRankOrder = new javax.swing.JTextField();
        txtRankExpense = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        errorRankName = new javax.swing.JLabel();
        errorRankOrder = new javax.swing.JLabel();
        errorRankExpense = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        btnAddRank = new javax.swing.JButton();
        btnUpdateRank = new javax.swing.JButton();
        btnDeleteRank = new javax.swing.JButton();
        btnClearRank = new javax.swing.JButton();
        btnFirstRank = new javax.swing.JButton();
        btnPrevRank = new javax.swing.JButton();
        btnNextRank = new javax.swing.JButton();
        btnLastRank = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblRank = new javax.swing.JTable();

        DSKH.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách khách hàng"));

        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Họ Tên", "Giới Tính", "Ngày Sinh", "Email", "Số Điện Thoại", "Hạng Khách Hàng"
            }
        ));
        tblCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCustomerMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCustomer);

        jPanel8.setLayout(new java.awt.GridLayout(1, 4, 5, 0));

        btnfirstPage.setText("<<");
        btnfirstPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfirstPageActionPerformed(evt);
            }
        });
        jPanel8.add(btnfirstPage);

        btnPrevPage.setText("<");
        btnPrevPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevPageActionPerformed(evt);
            }
        });
        jPanel8.add(btnPrevPage);

        btnNextPage.setText(">");
        btnNextPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextPageActionPerformed(evt);
            }
        });
        jPanel8.add(btnNextPage);

        btnLastPage.setText(">>");
        btnLastPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastPageActionPerformed(evt);
            }
        });
        jPanel8.add(btnLastPage);

        cboPageSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "10", "25", "50", "100" }));
        cboPageSize.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPageSizeItemStateChanged(evt);
            }
        });

        jLabel3.setText("Hiện thị");

        jLabel4.setText("kết quả");

        txtPageInfo.setText("        ");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboPageSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(txtPageInfo)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(cboPageSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPageInfo))
                .addContainerGap())
        );

        txtSearch.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtSearch.setForeground(new java.awt.Color(51, 51, 51));
        txtSearch.setText("Tìm kiếm theo tên, email, số điện thoại");
        txtSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSearchMouseClicked(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });

        jLabel5.setText("Sinh nhật:");

        txtStart.setDateFormatString("yyyy-MM-dd");

        txtEnd.setDateFormatString("yyyy-MM-dd");

        jLabel6.setText(" - ");

        btnSearchDate.setText("Tìm");
        btnSearchDate.setMargin(new java.awt.Insets(2, 5, 2, 5));
        btnSearchDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchDateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DSKHLayout = new javax.swing.GroupLayout(DSKH);
        DSKH.setLayout(DSKHLayout);
        DSKHLayout.setHorizontalGroup(
            DSKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DSKHLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DSKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(DSKHLayout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 263, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStart, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearchDate)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DSKHLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        DSKHLayout.setVerticalGroup(
            DSKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DSKHLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(DSKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DSKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(txtStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(btnSearchDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DSKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        TTCN.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin cá nhân"));

        lblImage.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        MaKH.setText("Mã KH:");

        Name.setText("Họ tên:");

        Gender.setText("Giới tính:");

        buttonGroup1.add(rdoMale);
        rdoMale.setSelected(true);
        rdoMale.setText("Nam");

        buttonGroup1.add(rdoFemale);
        rdoFemale.setText("Nữ");

        Birthdate.setText("Ngày sinh:");

        txtBirthdate.setDateFormatString("yyyy-MM-dd");

        Email.setText("Email:");

        PhoneNumber.setText("Số điện thoại:");

        State.setText("Trạng thái:");

        buttonGroup2.add(rdoActive);
        rdoActive.setSelected(true);
        rdoActive.setText("Còn hoạt động");

        buttonGroup2.add(rdoInactive);
        rdoInactive.setText("Ngừng hoạt động");

        jLabel12.setText("Ghi chú:");

        jLabel1.setText("Địa chỉ:");

        jButton9.setText("Chọn ảnh");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        txaDescription.setColumns(20);
        txaDescription.setRows(4);
        jScrollPane3.setViewportView(txaDescription);

        errorName.setForeground(new java.awt.Color(255, 0, 0));
        errorName.setText("      ");

        errorEmail.setForeground(new java.awt.Color(255, 0, 0));
        errorEmail.setText("      ");

        errorPhoneNumber.setForeground(new java.awt.Color(255, 0, 0));
        errorPhoneNumber.setText("      ");

        javax.swing.GroupLayout TTCNLayout = new javax.swing.GroupLayout(TTCN);
        TTCN.setLayout(TTCNLayout);
        TTCNLayout.setHorizontalGroup(
            TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TTCNLayout.createSequentialGroup()
                .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TTCNLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TTCNLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jButton9)))
                .addGap(21, 21, 21)
                .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TTCNLayout.createSequentialGroup()
                        .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Name)
                            .addComponent(MaKH)
                            .addComponent(Gender))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TTCNLayout.createSequentialGroup()
                                .addComponent(rdoMale)
                                .addGap(35, 35, 35)
                                .addComponent(rdoFemale))
                            .addComponent(errorName)))
                    .addGroup(TTCNLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TTCNLayout.createSequentialGroup()
                                .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TTCNLayout.createSequentialGroup()
                                        .addComponent(Birthdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtBirthdate, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, TTCNLayout.createSequentialGroup()
                                            .addGap(53, 53, 53)
                                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(34, 34, 34)
                                .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(TTCNLayout.createSequentialGroup()
                                        .addGap(78, 78, 78)
                                        .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(errorPhoneNumber)
                                            .addGroup(TTCNLayout.createSequentialGroup()
                                                .addComponent(rdoActive)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                                .addComponent(rdoInactive)
                                                .addGap(4, 4, 4))))
                                    .addGroup(TTCNLayout.createSequentialGroup()
                                        .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(TTCNLayout.createSequentialGroup()
                                                .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(PhoneNumber, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(State, javax.swing.GroupLayout.Alignment.LEADING))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(TTCNLayout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(TTCNLayout.createSequentialGroup()
                                .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Email)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(errorEmail)
                                    .addGroup(TTCNLayout.createSequentialGroup()
                                        .addComponent(txtEmail)
                                        .addGap(342, 342, 342)))))))
                .addContainerGap())
        );
        TTCNLayout.setVerticalGroup(
            TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TTCNLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(TTCNLayout.createSequentialGroup()
                        .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TTCNLayout.createSequentialGroup()
                                .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(MaKH)
                                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Name)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2)
                                .addComponent(errorName)
                                .addGap(2, 2, 2)
                                .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Gender)
                                    .addComponent(rdoMale)
                                    .addComponent(rdoFemale)))
                            .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Email)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9)))
                    .addGroup(TTCNLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBirthdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Birthdate)))
                    .addGroup(TTCNLayout.createSequentialGroup()
                        .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PhoneNumber)
                            .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(errorPhoneNumber)
                        .addGap(2, 2, 2)
                        .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(State)
                            .addComponent(rdoInactive)
                            .addComponent(rdoActive))
                        .addGap(21, 21, 21)
                        .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jScrollPane3))))
                .addGap(2, 2, 2)
                .addComponent(errorEmail)
                .addGap(2, 2, 2)
                .addGroup(TTCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cboAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        TTMH.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin mua hàng"));

        jLabel8.setText("Số đơn hàng:");

        lblOrders.setText("   ");

        jLabel9.setText("Giá trị chi tiêu:");

        lblExpense.setText("      ");

        jLabel11.setText("Số SP đã mua:");

        lblBoughtItem.setText("   ");

        jLabel10.setText("Hạng KH:");

        jLabel2.setText("Xem chi tiết:");

        cboDetails.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "----Chọn----", "Lịch sử mua hàng", "Địa chỉ" }));
        cboDetails.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboDetailsItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout TTMHLayout = new javax.swing.GroupLayout(TTMH);
        TTMH.setLayout(TTMHLayout);
        TTMHLayout.setHorizontalGroup(
            TTMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TTMHLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TTMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TTMHLayout.createSequentialGroup()
                        .addGroup(TTMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addGroup(TTMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TTMHLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(lblOrders))
                            .addGroup(TTMHLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(lblExpense))
                            .addGroup(TTMHLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblBoughtItem))))
                    .addGroup(TTMHLayout.createSequentialGroup()
                        .addGroup(TTMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(TTMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboRank, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboDetails, 0, 148, Short.MAX_VALUE))))
                .addContainerGap())
        );
        TTMHLayout.setVerticalGroup(
            TTMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TTMHLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(TTMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblOrders))
                .addGap(30, 30, 30)
                .addGroup(TTMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblExpense))
                .addGap(30, 30, 30)
                .addGroup(TTMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblBoughtItem))
                .addGap(30, 30, 30)
                .addGroup(TTMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cboRank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(TTMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Edit.setLayout(new java.awt.GridLayout(1, 8, 5, 0));

        btnAddCustomer.setText("Thêm");
        btnAddCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCustomerActionPerformed(evt);
            }
        });
        Edit.add(btnAddCustomer);

        btnUpdateCustomer.setText("Sửa");
        btnUpdateCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCustomerActionPerformed(evt);
            }
        });
        Edit.add(btnUpdateCustomer);

        btnDeleteCustomer.setText("Xóa");
        btnDeleteCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCustomerActionPerformed(evt);
            }
        });
        Edit.add(btnDeleteCustomer);

        btnClearCustomer.setText("Mới");
        btnClearCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearCustomerActionPerformed(evt);
            }
        });
        Edit.add(btnClearCustomer);

        btnFirstCustomer.setText("<<");
        btnFirstCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstCustomerActionPerformed(evt);
            }
        });
        Edit.add(btnFirstCustomer);

        btnPrevCustomer.setText("<");
        btnPrevCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevCustomerActionPerformed(evt);
            }
        });
        Edit.add(btnPrevCustomer);

        btnNextCustomer.setText(">");
        btnNextCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextCustomerActionPerformed(evt);
            }
        });
        Edit.add(btnNextCustomer);

        btnLastCustomer.setText(">>");
        btnLastCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastCustomerActionPerformed(evt);
            }
        });
        Edit.add(btnLastCustomer);

        javax.swing.GroupLayout KhachHangLayout = new javax.swing.GroupLayout(KhachHang);
        KhachHang.setLayout(KhachHangLayout);
        KhachHangLayout.setHorizontalGroup(
            KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(KhachHangLayout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(Edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(243, Short.MAX_VALUE))
                    .addGroup(KhachHangLayout.createSequentialGroup()
                        .addGroup(KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(DSKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(KhachHangLayout.createSequentialGroup()
                                .addComponent(TTCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TTMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        KhachHangLayout.setVerticalGroup(
            KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KhachHangLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TTMH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TTCN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DSKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        QLKH.addTab("Khách Hàng", KhachHang);

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Thông tin hạng"));

        jLabel15.setText("Mã hạng:");

        jLabel16.setText("Tên hạng:");

        jLabel17.setText("Số đơn hàng:");

        jLabel18.setText("Giá trị chi tiêu:");

        errorRankName.setForeground(new java.awt.Color(255, 0, 0));
        errorRankName.setText("    ");

        errorRankOrder.setForeground(new java.awt.Color(255, 0, 0));
        errorRankOrder.setText("    ");

        errorRankExpense.setForeground(new java.awt.Color(255, 0, 0));
        errorRankExpense.setText("    ");

        jPanel15.setLayout(new java.awt.GridLayout(2, 4, 5, 5));

        btnAddRank.setText("Thêm");
        btnAddRank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRankActionPerformed(evt);
            }
        });
        jPanel15.add(btnAddRank);

        btnUpdateRank.setText("Cập nhật");
        btnUpdateRank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateRankActionPerformed(evt);
            }
        });
        jPanel15.add(btnUpdateRank);

        btnDeleteRank.setText("Xóa");
        btnDeleteRank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteRankActionPerformed(evt);
            }
        });
        jPanel15.add(btnDeleteRank);

        btnClearRank.setText("Mới");
        btnClearRank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearRankActionPerformed(evt);
            }
        });
        jPanel15.add(btnClearRank);

        btnFirstRank.setText("<<");
        btnFirstRank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstRankActionPerformed(evt);
            }
        });
        jPanel15.add(btnFirstRank);

        btnPrevRank.setText("<");
        btnPrevRank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevRankActionPerformed(evt);
            }
        });
        jPanel15.add(btnPrevRank);

        btnNextRank.setText(">");
        btnNextRank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextRankActionPerformed(evt);
            }
        });
        jPanel15.add(btnNextRank);

        btnLastRank.setText(">>");
        btnLastRank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastRankActionPerformed(evt);
            }
        });
        jPanel15.add(btnLastRank);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(31, 31, 31)
                        .addComponent(txtIdRank, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(31, 31, 31)
                        .addComponent(txtRankName, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errorRankName)
                            .addComponent(txtRankOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(errorRankOrder)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errorRankExpense)
                            .addComponent(txtRankExpense, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtIdRank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtRankName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(errorRankName)
                .addGap(40, 40, 40)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtRankOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(errorRankOrder)
                .addGap(40, 40, 40)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtRankExpense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorRankExpense)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tblRank.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hạng", "Tên hạng", "Số đơn hàng", "Giá trị chi tiêu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRank.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRankMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblRank);

        javax.swing.GroupLayout HangKHLayout = new javax.swing.GroupLayout(HangKH);
        HangKH.setLayout(HangKHLayout);
        HangKHLayout.setHorizontalGroup(
            HangKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HangKHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        HangKHLayout.setVerticalGroup(
            HangKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HangKHLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(HangKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE))
                .addContainerGap(123, Short.MAX_VALUE))
        );

        QLKH.addTab("Hạng Khách Hàng", HangKH);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(QLKH)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(QLKH, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerMouseClicked
        showCustomerDetails();
        errorEmail.setText("  ");
        errorName.setText("  ");
        errorPhoneNumber.setText("  ");
    }//GEN-LAST:event_tblCustomerMouseClicked

    private void btnfirstPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfirstPageActionPerformed
        currentPage = 1;
        updatePagination();
    }//GEN-LAST:event_btnfirstPageActionPerformed

    private void btnPrevPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevPageActionPerformed
        if (currentPage > 1) {
            currentPage--;
            updatePagination();
        }
    }//GEN-LAST:event_btnPrevPageActionPerformed

    private void btnNextPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextPageActionPerformed
        if (currentPage < totalPages) {
            currentPage++;
            updatePagination();
        }
    }//GEN-LAST:event_btnNextPageActionPerformed

    private void btnLastPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastPageActionPerformed
        int pageSize = Integer.parseInt((String) cboPageSize.getSelectedItem());
        totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        currentPage = totalPages;
        updatePagination();
    }//GEN-LAST:event_btnLastPageActionPerformed

    private void cboPageSizeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPageSizeItemStateChanged
        updatePagination();
    }//GEN-LAST:event_cboPageSizeItemStateChanged

    private void txtSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMouseClicked
        txtSearch.setText("");
    }//GEN-LAST:event_txtSearchMouseClicked

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        //        search();
    }//GEN-LAST:event_txtSearchKeyPressed

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        search();
    }//GEN-LAST:event_txtSearchKeyTyped

    private void btnSearchDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchDateActionPerformed
        java.util.Date start = txtStart.getDate();
        java.util.Date end = txtEnd.getDate();

        modelCustomer.setRowCount(0);
        for (KhachHang kh : kdao.getAll()) {
            if (start == null || end == null || start.after(end)) {
                return;
            }
            if (kh.getNgaySinh().getDate() >= (start.getDate()) && kh.getNgaySinh().getDate() <= end.getDate()
                    && kh.getNgaySinh().getMonth() >= start.getMonth() && kh.getNgaySinh().getMonth() <= end.getMonth()) {
                Object data[] = {kh.getMaKH(), kh.getHoTen(), kh.isGioiTinh() ? "Nam" : "Nữ", kh.getNgaySinh(), kh.getEmail(),
                    kh.getSDT(), kh.getHangKhachHang().getTenHang()};
                modelCustomer.addRow(data);
            }
        }
        if (tblCustomer.getRowCount() > 0) {
            tblCustomer.setRowSelectionInterval(0, 0);
            showCustomerDetails();
        }
    }//GEN-LAST:event_btnSearchDateActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        chooseImage();
    }//GEN-LAST:event_jButton9ActionPerformed

    private String previousSelectedItem = null;
    private void cboDetailsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboDetailsItemStateChanged
        int index = tblCustomer.getSelectedRow();
        String maKH = tblCustomer.getValueAt(index, 0).toString();
        KhachHang kh = kdao.getByID(maKH);

        // Lấy mục đang được chọn hiện tại
        String selectedItem = cboDetails.getSelectedItem().toString();

        // Kiểm tra xem mục đã thay đổi chưa
        if (!selectedItem.equals(previousSelectedItem)) {
            // Lưu lại mục được chọn để so sánh ở lần sau
            previousSelectedItem = selectedItem;

            // Thực hiện hành động tương ứng với mục được chọn
            if (selectedItem.equals("Địa chỉ")) {
                new DiaChiView(kh).setVisible(true);
            } else if (selectedItem.equals("Lịch sử mua hàng")) {
                new LichSuMuaHang(kh).setVisible(true);
            }
        }
    }//GEN-LAST:event_cboDetailsItemStateChanged

    private void btnAddCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCustomerActionPerformed
        insertCustomer();
    }//GEN-LAST:event_btnAddCustomerActionPerformed

    private void btnUpdateCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCustomerActionPerformed
        updateCustomer();
    }//GEN-LAST:event_btnUpdateCustomerActionPerformed

    private void btnDeleteCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCustomerActionPerformed
        deleteCustomer();
    }//GEN-LAST:event_btnDeleteCustomerActionPerformed

    private void btnClearCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearCustomerActionPerformed
        clearCustomer();
    }//GEN-LAST:event_btnClearCustomerActionPerformed

    private void btnFirstCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstCustomerActionPerformed
        first(tblCustomer);
        showCustomerDetails();
    }//GEN-LAST:event_btnFirstCustomerActionPerformed

    private void btnPrevCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevCustomerActionPerformed
        prev(tblCustomer);
        showCustomerDetails();
    }//GEN-LAST:event_btnPrevCustomerActionPerformed

    private void btnNextCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextCustomerActionPerformed
        next(tblCustomer);
        showCustomerDetails();
    }//GEN-LAST:event_btnNextCustomerActionPerformed

    private void btnLastCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastCustomerActionPerformed
        last(tblCustomer);
        showCustomerDetails();
    }//GEN-LAST:event_btnLastCustomerActionPerformed

    private void btnAddRankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRankActionPerformed
        insertRank();
    }//GEN-LAST:event_btnAddRankActionPerformed

    private void btnUpdateRankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateRankActionPerformed
        updateRank();
    }//GEN-LAST:event_btnUpdateRankActionPerformed

    private void btnDeleteRankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRankActionPerformed
        deleteRank();
    }//GEN-LAST:event_btnDeleteRankActionPerformed

    private void btnClearRankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearRankActionPerformed
        clearRank();
        clearErrorRank();
    }//GEN-LAST:event_btnClearRankActionPerformed

    private void btnFirstRankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstRankActionPerformed
        first(tblRank);
        showDetailsRank();
    }//GEN-LAST:event_btnFirstRankActionPerformed

    private void btnPrevRankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevRankActionPerformed
        prev(tblRank);
        showDetailsRank();
    }//GEN-LAST:event_btnPrevRankActionPerformed

    private void btnNextRankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextRankActionPerformed
        next(tblRank);
        showDetailsRank();
    }//GEN-LAST:event_btnNextRankActionPerformed

    private void btnLastRankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastRankActionPerformed
        last(tblRank);
        showDetailsRank();
    }//GEN-LAST:event_btnLastRankActionPerformed

    private void tblRankMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRankMouseClicked
        showDetailsRank();
        clearErrorRank();
    }//GEN-LAST:event_tblRankMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Birthdate;
    private javax.swing.JPanel DSKH;
    private javax.swing.JPanel Edit;
    private javax.swing.JLabel Email;
    private javax.swing.JLabel Gender;
    private javax.swing.JPanel HangKH;
    private javax.swing.JPanel KhachHang;
    private javax.swing.JLabel MaKH;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel PhoneNumber;
    private javax.swing.JTabbedPane QLKH;
    private javax.swing.JLabel State;
    private javax.swing.JPanel TTCN;
    private javax.swing.JPanel TTMH;
    private javax.swing.JButton btnAddCustomer;
    private javax.swing.JButton btnAddRank;
    private javax.swing.JButton btnClearCustomer;
    private javax.swing.JButton btnClearRank;
    private javax.swing.JButton btnDeleteCustomer;
    private javax.swing.JButton btnDeleteRank;
    private javax.swing.JButton btnFirstCustomer;
    private javax.swing.JButton btnFirstRank;
    private javax.swing.JButton btnLastCustomer;
    private javax.swing.JButton btnLastPage;
    private javax.swing.JButton btnLastRank;
    private javax.swing.JButton btnNextCustomer;
    private javax.swing.JButton btnNextPage;
    private javax.swing.JButton btnNextRank;
    private javax.swing.JButton btnPrevCustomer;
    private javax.swing.JButton btnPrevPage;
    private javax.swing.JButton btnPrevRank;
    private javax.swing.JButton btnSearchDate;
    private javax.swing.JButton btnUpdateCustomer;
    private javax.swing.JButton btnUpdateRank;
    private javax.swing.JButton btnfirstPage;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboAddress;
    private javax.swing.JComboBox<String> cboDetails;
    private javax.swing.JComboBox<String> cboPageSize;
    private javax.swing.JComboBox<String> cboRank;
    private javax.swing.JLabel errorEmail;
    private javax.swing.JLabel errorName;
    private javax.swing.JLabel errorPhoneNumber;
    private javax.swing.JLabel errorRankExpense;
    private javax.swing.JLabel errorRankName;
    private javax.swing.JLabel errorRankOrder;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblBoughtItem;
    private javax.swing.JLabel lblExpense;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblOrders;
    private javax.swing.JRadioButton rdoActive;
    private javax.swing.JRadioButton rdoFemale;
    private javax.swing.JRadioButton rdoInactive;
    private javax.swing.JRadioButton rdoMale;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTable tblRank;
    private javax.swing.JTextArea txaDescription;
    private com.toedter.calendar.JDateChooser txtBirthdate;
    private javax.swing.JTextField txtEmail;
    private com.toedter.calendar.JDateChooser txtEnd;
    private javax.swing.JTextField txtIdRank;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtName;
    private javax.swing.JLabel txtPageInfo;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtRankExpense;
    private javax.swing.JTextField txtRankName;
    private javax.swing.JTextField txtRankOrder;
    private javax.swing.JTextField txtSearch;
    private com.toedter.calendar.JDateChooser txtStart;
    // End of variables declaration//GEN-END:variables
}
