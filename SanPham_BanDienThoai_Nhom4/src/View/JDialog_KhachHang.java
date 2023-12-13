/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import Extensions.Validation;
import Extensions.XImage;
import Model.DiaChi;
import Model.HangKhachHang;
import Model.KhachHang;
import Repository.HangKHDAO;
import Repository.KhachHangDAO;
import java.awt.Image;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class JDialog_KhachHang extends javax.swing.JDialog {

    /**
     * Creates new form JDialog_KhachHang
     */
    KhachHangDAO khDao = new KhachHangDAO();

    Validation validation = new Validation();

    DefaultTableModel modelTblKH = new DefaultTableModel();
    KhachHang kh = new KhachHang();
    int currentPage = 1;
    int totalRecords;
    int totalPages;

    public JDialog_KhachHang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
        setLocationRelativeTo(null);
        modelTblKH = (DefaultTableModel) tblCustomer.getModel();
        updatePagination();
    }
    
    void init() {
        setIconImage(XImage.getAppIcon().getImage());
        setTitle("Lịch sử mua hàng");
        setLocationRelativeTo(null);
    }

    private void loadCustomer(ArrayList<KhachHang> list) {
        modelTblKH.setRowCount(0);
        for (KhachHang kh : list) {
            Object dataKH[] = {kh.getMaKH(), kh.getHoTen(), kh.isGioiTinh() == true ? "Nam" : "Nữ", kh.getNgaySinh(), kh.getEmail(), kh.getSDT(),
            kh.getHangKhachHang().getTenHang()};
            modelTblKH.addRow(dataKH);
        }
    }

    private void search() {
        String inp = txtSearch.getText();
        if (khDao.search(inp) == null || inp == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng");
            updatePagination();
        } else {
            loadCustomer(khDao.search(inp));
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
        txtCity.setText("");
        txtDistrict.setText("");
        txtWard.setText("");
        txtDetailsAddress.setText("");
    }

    private void clearError() {
        errorName.setText("   ");
        errorEmail.setText("   ");
        errorPhoneNumber.setText("   ");
    }

    private ImageIcon resizeImage(ImageIcon imgIcon) {
        Image im = imgIcon.getImage();
        ImageIcon img = new ImageIcon(im.getScaledInstance(160, 194, im.SCALE_SMOOTH));
        return img;
    }

    private void chonAnh() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            XImage.save(file);
            ImageIcon icon = XImage.read(file.getName());
            lblImage.setIcon(resizeImage(icon));
            lblImage.setToolTipText(file.getName());
        }
    }

    private KhachHang getCustomer() {
        int count = 0;
        String name = txtName.getText();
        boolean gd = rdoMale.isSelected();
        java.util.Date date = txtBirthdate.getDate();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String birthdate = df.format(date);
        String email = txtEmail.getText();
        String sdt = txtPhoneNumber.getText();
        boolean st = rdoActive.isSelected();
        String description = txaDescription.getText();
        count = validation.checkNull(txtName, errorName)
                + validation.checkRegex(txtEmail, errorEmail, "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", "Định dạng email: example1@gmail.com")
                + validation.checkRegex(txtPhoneNumber, errorPhoneNumber, "^0[0-9]{9}", "Vui lòng nhập SDT 10 chữ số");
        HangKhachHang hang = new HangKHDAO().getByTen("Thành viên");
        if (count == 0) {
            KhachHang kh = new KhachHang(null, name, gd, Date.valueOf(birthdate), email, sdt, lblImage.getToolTipText(),
                    0, 0, description, st, hang);
            return kh;
        }
        return null;
    }

    private void insertDetailedAddress(String idKH, String idDC, String message1, String message2) {
        if (khDao.insertDCCT(idKH, idDC) > 0) {
            JOptionPane.showMessageDialog(this, message1);
            updatePagination();
        } else {
            JOptionPane.showMessageDialog(this, message2);
        }
    }

    private void insertCustomer() {
        KhachHang kh = getCustomer();
        Random rd = new Random();
        String currentTimeMillis = System.currentTimeMillis() + "";
        String idKH = "KH" + currentTimeMillis.substring(4) + rd.nextInt(1000) + "";

        String ctm = System.currentTimeMillis() + "";
        String idDC = "DC" + ctm.substring(6) + rd.nextInt(1000) + "";
        DiaChi dc = getAddress();

        if (kh != null) {
            kh.setMaKH(idKH);
            if (khDao.insert(kh) > 0) {
                if (dc != null) {
                    DiaChi o = khDao.getDC(dc);
                    if (o == null) {
                        dc.setMaDC(idDC);
                        if (khDao.insertDC(dc) > 0) {
                            insertDetailedAddress(idKH, dc.getMaDC(), "Thêm khách hàng thành công!", "Thêm khách hàng thất bại!");
                        }
                    } else {
                        insertDetailedAddress(idKH, o.getMaDC(), "Thêm khách hàng thành công!", "Thêm khách hàng thất bại!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Thêm khách hàng thất bại!" + getCustomer().getHangKhachHang().getMaHang() + getCustomer().getHangKhachHang().getTenHang());
//                System.out.println(getCustomer().getHangKhachHang().getMaHang());
            }
        }
    }

    private DiaChi getAddress() {
        String city = txtCity.getText();
        String district = txtDistrict.getText();
        String ward = txtWard.getText();
        String details = txtDetailsAddress.getText();
        DiaChi dc = new DiaChi(null, city, district, ward, details, null);
        return dc;
    }

    private void updatePagination() {
        int pageSize = Integer.parseInt((String) cboPageSize.getSelectedItem());
        totalRecords = khDao.getAll().size();
        totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        txtPageInfo.setText(currentPage + "/" + totalPages);
        btnPrevPage.setEnabled(currentPage > 1);
        btnNextPage.setEnabled(currentPage < totalPages);
        btnfirstPage.setEnabled(currentPage != 1 || totalPages > 1);
        ArrayList<KhachHang> list = khDao.paginate(currentPage, pageSize);
        loadCustomer(list);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        DSKH = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnfirstPage = new javax.swing.JButton();
        btnPrevPage = new javax.swing.JButton();
        btnNextPage = new javax.swing.JButton();
        btnLastPage = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        cboPageSize = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPageInfo = new javax.swing.JLabel();
        CTKH = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        Image = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();
        MaKH = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        Name = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        errorName = new javax.swing.JLabel();
        Gender = new javax.swing.JLabel();
        rdoMale = new javax.swing.JRadioButton();
        rdoFemale = new javax.swing.JRadioButton();
        Birthdate = new javax.swing.JLabel();
        txtBirthdate = new com.toedter.calendar.JDateChooser();
        Email = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        errorEmail = new javax.swing.JLabel();
        PhoneNumber = new javax.swing.JLabel();
        errorPhoneNumber = new javax.swing.JLabel();
        txtPhoneNumber = new javax.swing.JTextField();
        State = new javax.swing.JLabel();
        rdoActive = new javax.swing.JRadioButton();
        rdoInactive = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        scrDescription = new javax.swing.JScrollPane();
        txaDescription = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtCity = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDistrict = new javax.swing.JTextField();
        txtWard = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDetailsAddress = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Tìm Kiếm"));

        txtSearch.setText("Tìm KH theo tên, số điện thoại, email");

        btnSearch.setText("Tìm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txtSearch)
                .addGap(18, 18, 18)
                .addComponent(btnSearch)
                .addGap(46, 46, 46))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch)
                    .addComponent(btnSearch))
                .addGap(22, 22, 22))
        );

        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Họ Tên", "Giới Tính", "Ngày Sinh", "Email", "SĐT", "Hạng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCustomerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCustomer);

        jPanel4.setLayout(new java.awt.GridLayout(1, 4, 5, 0));

        btnfirstPage.setText("<<");
        btnfirstPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfirstPageActionPerformed(evt);
            }
        });
        jPanel4.add(btnfirstPage);

        btnPrevPage.setText("<");
        btnPrevPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevPageActionPerformed(evt);
            }
        });
        jPanel4.add(btnPrevPage);

        btnNextPage.setText(">");
        btnNextPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextPageActionPerformed(evt);
            }
        });
        jPanel4.add(btnNextPage);

        btnLastPage.setText(">>");
        btnLastPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastPageActionPerformed(evt);
            }
        });
        jPanel4.add(btnLastPage);

        cboPageSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "10", "25", "50", "100" }));
        cboPageSize.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPageSizeItemStateChanged(evt);
            }
        });

        jLabel2.setText("Hiện thị");

        jLabel3.setText("kết quả");

        txtPageInfo.setText("        ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboPageSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtPageInfo)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(cboPageSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPageInfo))
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout DSKHLayout = new javax.swing.GroupLayout(DSKH);
        DSKH.setLayout(DSKHLayout);
        DSKHLayout.setHorizontalGroup(
            DSKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DSKHLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(DSKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(DSKHLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1027, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        DSKHLayout.setVerticalGroup(
            DSKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DSKHLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DSKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(158, 158, 158))
        );

        jTabbedPane1.addTab("DS Khách Hàng", DSKH);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Thông tin cá nhân"));

        Image.setText("Ảnh đại diện:");

        lblImage.setText("     ");
        lblImage.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        lblImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImageMouseClicked(evt);
            }
        });

        MaKH.setText("Mã KH:");

        Name.setText("Họ tên:");

        errorName.setForeground(new java.awt.Color(255, 0, 0));
        errorName.setText("     ");

        Gender.setText("Giới tính:");

        rdoMale.setSelected(true);
        rdoMale.setText("Nam");

        rdoFemale.setText("Nữ");

        Birthdate.setText("Ngày sinh:");

        txtBirthdate.setDateFormatString("yyyy-MM-dd");

        Email.setText("Email:");

        errorEmail.setForeground(new java.awt.Color(255, 0, 0));
        errorEmail.setText("     ");

        PhoneNumber.setText("Số điện thoại:");

        errorPhoneNumber.setForeground(new java.awt.Color(255, 0, 0));
        errorPhoneNumber.setText("     ");

        State.setText("Trạng thái:");

        rdoActive.setSelected(true);
        rdoActive.setText("Còn hoạt động");

        rdoInactive.setText("Ngừng hoạt động");

        jLabel12.setText("Ghi chú:");

        txaDescription.setColumns(20);
        txaDescription.setRows(5);
        scrDescription.setViewportView(txaDescription);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Image)
                    .addComponent(MaKH)
                    .addComponent(Name)
                    .addComponent(Gender))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorName)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(rdoMale)
                        .addGap(18, 18, 18)
                        .addComponent(rdoFemale))
                    .addComponent(txtMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(txtName))
                .addGap(44, 44, 44)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Birthdate)
                    .addComponent(Email)
                    .addComponent(PhoneNumber)
                    .addComponent(State)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtEmail)
                    .addComponent(txtBirthdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errorEmail)
                            .addComponent(errorPhoneNumber)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(rdoActive)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoInactive)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Image)
                    .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBirthdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Birthdate))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Email)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(errorEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PhoneNumber)
                            .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(errorPhoneNumber)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaKH)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(State)
                    .addComponent(rdoActive)
                    .addComponent(rdoInactive))
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Name)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(errorName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Gender)
                            .addComponent(rdoMale)
                            .addComponent(rdoFemale)))
                    .addComponent(scrDescription, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 30, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Thông tin địa chỉ"));

        jLabel4.setText("Thành phố:");

        jLabel5.setText("Quận:");

        jLabel6.setText("Phường:");

        jLabel7.setText("Đường/Tòa nhà:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(txtDetailsAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(59, 59, 59)
                                .addComponent(txtWard, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(377, 377, 377))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtWard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDetailsAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setLayout(new java.awt.GridLayout(1, 3, 5, 5));

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel8.add(btnAdd);

        javax.swing.GroupLayout CTKHLayout = new javax.swing.GroupLayout(CTKH);
        CTKH.setLayout(CTKHLayout);
        CTKHLayout.setHorizontalGroup(
            CTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CTKHLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CTKHLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CTKHLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        CTKHLayout.setVerticalGroup(
            CTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CTKHLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jTabbedPane1.addTab("Thêm Khách Hàng", CTKH);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        search();
    }//GEN-LAST:event_btnSearchActionPerformed
    String ma;
    

    private void tblCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerMouseClicked
        int index = tblCustomer.getSelectedRow();
        ma = tblCustomer.getValueAt(index, 0).toString();
        this.dispose();
        JDialog_KhachHang();
    }//GEN-LAST:event_tblCustomerMouseClicked
    public void JDialog_KhachHang(){
        kh = khDao.getByID(ma);
    }
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

    private void lblImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageMouseClicked
        chonAnh();
    }//GEN-LAST:event_lblImageMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        insertCustomer();
    }//GEN-LAST:event_btnAddActionPerformed

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
            java.util.logging.Logger.getLogger(JDialog_KhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialog_KhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialog_KhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialog_KhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialog_KhachHang dialog = new JDialog_KhachHang(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel Birthdate;
    private javax.swing.JPanel CTKH;
    private javax.swing.JPanel DSKH;
    private javax.swing.JLabel Email;
    private javax.swing.JLabel Gender;
    private javax.swing.JLabel Image;
    private javax.swing.JLabel MaKH;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel PhoneNumber;
    private javax.swing.JLabel State;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnLastPage;
    private javax.swing.JButton btnNextPage;
    private javax.swing.JButton btnPrevPage;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnfirstPage;
    private javax.swing.JComboBox<String> cboPageSize;
    private javax.swing.JLabel errorEmail;
    private javax.swing.JLabel errorName;
    private javax.swing.JLabel errorPhoneNumber;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JRadioButton rdoActive;
    private javax.swing.JRadioButton rdoFemale;
    private javax.swing.JRadioButton rdoInactive;
    private javax.swing.JRadioButton rdoMale;
    private javax.swing.JScrollPane scrDescription;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTextArea txaDescription;
    private com.toedter.calendar.JDateChooser txtBirthdate;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtDetailsAddress;
    private javax.swing.JTextField txtDistrict;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtName;
    private javax.swing.JLabel txtPageInfo;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtWard;
    // End of variables declaration//GEN-END:variables
}
