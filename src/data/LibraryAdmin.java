package data;

import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class LibraryAdmin extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField; // textField_Search
	private JTable table1;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6; // textField_Search
	private JTable table2;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11; // textField_Search
	private JTable table3;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private Connect connect = new Connect();
	private ResultSet resultset;
	private Connection connection;

	private static final String header1[] = { "Mã sách", "Tên sách", "Tên tác giả", "Nhà xuất bản", "Số lượng" };
	static final DefaultTableModel model1 = new DefaultTableModel(header1, 0);

	private final static String header2[] = { "Mã độc giả", "Tên độc giả", "Số điện thoại", "Email" };
	static final DefaultTableModel model2 = new DefaultTableModel(header2, 0);

	private final static String header3[] = { "Mã phiếu mượn", "Mã độc giả", "Mã sách", "Ngày mượn", "Ngày trả",
			"Số lượng" };
	static final DefaultTableModel model3 = new DefaultTableModel(header3, 0);

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			new LibraryAdmin("Library Management");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LibraryAdmin(String name) throws HeadlessException {
		super(name);
		this.initalize();
	}

	private void loadDataTable1() {
		try {
			// CONNECT TO THE DATABASE
			connection = connect.getConnection();
			int number;
			Vector<String> row = null;
			// CREATE STATEMENT
			java.sql.Statement st = connection.createStatement();
			// GET DATA FROM TABLE 'SACH'
			resultset = st.executeQuery("Select * From SACH");
			ResultSetMetaData meta = resultset.getMetaData();
			number = meta.getColumnCount();
			model1.setRowCount(0);
			// SHOW DATA
			while (resultset.next()) {
				row = new Vector<String>();
				for (int i = 1; i <= number; i++)
					row.addElement(resultset.getString(i));
				model1.addRow(row);
				table1.setModel(model1);
			}
			// CLOSE THE CONNECTION
			st.close();
			resultset.close();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadDataTable2() {
		try {
			// CONNECT TO DATABASE
			connection = connect.getConnection();
			int number;
			Vector<String> row = null;
			// CREATE STATEMENT
			java.sql.Statement statement = connection.createStatement();
			// GET DATA FROM TABLE 'KHACH_HANG'
			resultset = statement.executeQuery("SELECT * FROM KHACH_HANG");
			ResultSetMetaData metadata = resultset.getMetaData();
			number = metadata.getColumnCount();
			model2.setRowCount(0);
			while (resultset.next()) {
				row = new Vector<String>();
				for (int i = 1; i <= number; i++)
					row.addElement(resultset.getString(i));
				model2.addRow(row);
				table2.setModel(model2);
			}
			// CLOSE CONNECTION
			statement.close();
			resultset.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadDataTable3() {
		try {
			// CONNECT TO THE DATABASE
			connection = connect.getConnection();
			int cols;
			Vector<String> row = null;
			// CRREATE THE STATEMENT (BRIDGE)
			java.sql.Statement statement = connection.createStatement();
			// GET DATA FROM THE TABLE 'PHIEU_MUON'
			ResultSet resultset = statement.executeQuery("Select * From PHIEU_MUON");
			ResultSetMetaData metadata = resultset.getMetaData();
			cols = metadata.getColumnCount();
			model3.setRowCount(0);

			while (resultset.next()) {
				row = new Vector<String>();
				for (int i = 1; i <= cols; i++) {
					row.addElement(resultset.getString(i));
				}
				model3.addRow(row);
				table3.setModel(model3);
			}
			// CLOSE THE CONNECTION
			statement.close();
			resultset.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearForm() {
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
		textField_7.setText("");
		textField_8.setText("");
		textField_9.setText("");
		textField_10.setText("");
		textField_12.setText("");
		textField_13.setText("");
		textField_14.setText("");
		textField_15.setText("");
		textField_16.setText("");
		textField_17.setText("");
	}

	private void initalize() {
		this.table1 = new JTable();
		this.table2 = new JTable();
		this.table3 = new JTable();
		
		table1.setAutoCreateRowSorter(true);
		table2.setAutoCreateRowSorter(true);
		table3.setAutoCreateRowSorter(true);

		Image iconGDadmin = new ImageIcon(this.getClass().getResource("/image/H.png")).getImage();

		this.setIconImage(iconGDadmin);
		this.setSize(1043, 682);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 21, 1027, 622);
		this.getContentPane().add(tabbedPane);

		// TODO: CREATE PANEL3 FOR TABBEDPANE
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		tabbedPane.addTab("SÁCH", new ImageIcon(LibraryAdmin.class.getResource("/image/D.png")), panel_1, null);
		panel_1.setLayout(null);

		JLabel lbl_Search1 = new JLabel("Tìm kiếm");
		lbl_Search1.setIcon(new ImageIcon(LibraryAdmin.class.getResource("/image/searchicon.png")));
		lbl_Search1.setBounds(10, 12, 104, 37);
		lbl_Search1.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(lbl_Search1);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(136, 11, 480, 38);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				connection = connect.getConnection(); // CONNECT TO THE DATABASE
				java.sql.Statement statement = null; // CREATE THE STATEMENT (BRIDGE)
				try {
					String sql = "SELECT * FROM SACH";
					if (textField.getText().length() > 0) {
						sql += " where Ma_sach like '%" + textField.getText() + "%' or Ten_Sach like '%"
								+ textField.getText() + "%' or Ten_Tac_gia like '%" + textField.getText()
								+ "%' or Nha_xb like '%" + textField.getText() + "%'";
					}
					statement = connection.createStatement();
					resultset = statement.executeQuery(sql);
					Vector<String> data = null;
					model1.setRowCount(0);
					while (resultset.next()) {
						data = new Vector<String>();
						data.add(resultset.getString("Ma_Sach"));
						data.add(resultset.getString("Ten_Sach"));
						data.add(resultset.getString("Ten_Tac_gia"));
						data.add(resultset.getString("Nha_xb"));
						data.add(resultset.getString("So_luong"));
						model1.addRow(data);
					}
					table1.setModel(model1);
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					try {
						statement.close();
						resultset.close();
						connection.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		});
		panel_1.add(textField);

		JLabel lbl_ListTitle1 = new JLabel("CÁC SÁCH HIỆN CÓ TẠI THƯ VIỆN");
		lbl_ListTitle1.setBounds(10, 78, 247, 25);
		lbl_ListTitle1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1.add(lbl_ListTitle1);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(10, 112, 606, 427);
		panel_1.add(scrollPane1);

		table1.setBackground(new Color(192, 192, 192));
		table1.setModel(model1);
		table1.getTableHeader().setReorderingAllowed(false);
		table1.getColumnModel().getColumn(0).setResizable(false);
		table1.getColumnModel().getColumn(0).setPreferredWidth(67);
		table1.getColumnModel().getColumn(1).setResizable(false);
		table1.getColumnModel().getColumn(1).setPreferredWidth(100);
		table1.getColumnModel().getColumn(2).setResizable(false);
		table1.getColumnModel().getColumn(2).setPreferredWidth(95);
		table1.getColumnModel().getColumn(3).setResizable(false);
		table1.getColumnModel().getColumn(3).setPreferredWidth(95);
		table1.getColumnModel().getColumn(4).setResizable(false);
		table1.getColumnModel().getColumn(4).setPreferredWidth(58);
		scrollPane1.setViewportView(table1);

		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				int click = table1.getSelectedRow();
				if (click >= 0) {
					textField_1.setText(table1.getValueAt(click, 0) + "");
					textField_2.setText(table1.getValueAt(click, 1) + "");
					textField_3.setText(table1.getValueAt(click, 2) + "");
					textField_4.setText(table1.getValueAt(click, 3) + "");
					textField_5.setText(table1.getValueAt(click, 4) + "");
				}
			}
		});

		JSeparator separator1 = new JSeparator();
		separator1.setBounds(0, 65, 1012, 2);
		panel_1.add(separator1);

		JLabel lbl1 = new JLabel("Mã sách");
		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl1.setBounds(651, 112, 99, 25);
		panel_1.add(lbl1);

		textField_1 = new JTextField();
		textField_1.setBounds(760, 116, 224, 30);
		panel_1.add(textField_1);
		textField_1.setColumns(10);

		JLabel lbl2 = new JLabel("Tên sách");
		lbl2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl2.setBounds(651, 177, 99, 25);
		panel_1.add(lbl2);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(760, 181, 224, 30);
		panel_1.add(textField_2);

		JLabel lbl3 = new JLabel("Nhà xuất bản");
		lbl3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl3.setBounds(651, 242, 99, 25);
		panel_1.add(lbl3);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(760, 246, 224, 30);
		panel_1.add(textField_3);

		JLabel lbl4 = new JLabel("Tên tác giả");
		lbl4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl4.setBounds(651, 307, 99, 25);
		panel_1.add(lbl4);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(760, 311, 224, 30);
		panel_1.add(textField_4);

		JLabel lbl5 = new JLabel("Số lượng");
		lbl5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl5.setBounds(651, 372, 99, 25);
		panel_1.add(lbl5);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(760, 376, 224, 30);
		panel_1.add(textField_5);

		JButton btn_Add1 = new JButton("THÊM", new ImageIcon(LibraryAdmin.class.getResource("/image/L.png")));
		btn_Add1.setBackground(new Color(64, 128, 128));
		btn_Add1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_Add1.setBounds(714, 439, 122, 38);
		btn_Add1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = connect.getConnection();
					Statement statement = connection.createStatement();
					ResultSet resultSet = null;

					if (textField_1.getText().equals("") || textField_2.getText().equals("")
							|| textField_3.getText().equals("") || textField_4.getText().equals("")
							|| textField_5.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Cần nhập dữ liệu", "Empty", JOptionPane.WARNING_MESSAGE);
					} else {
						StringBuffer stringBuffer = new StringBuffer();
						String sql_hasCheck = "Select Ma_Sach From SACH Where Ma_Sach ='" + textField_1.getText() + "'";
						resultSet = statement.executeQuery(sql_hasCheck);
						if (resultSet.next()) {
							stringBuffer.append("Already");
						}
						if (stringBuffer.length() > 0) {
							JOptionPane.showMessageDialog(null, "Mã sách đã tồn tại!", "Already",
									JOptionPane.WARNING_MESSAGE);
						} else {
							String sql_insert = "Insert into SACH values('" + textField_1.getText() + "','"
									+ textField_2.getText() + "','" + textField_3.getText() + "','"
									+ textField_4.getText() + "','" + textField_5.getText() + "')";
							int result = statement.executeUpdate(sql_insert);
							if (result > 0) {
								JOptionPane.showMessageDialog(null, "Thêm thành công!", "Success",
										JOptionPane.OK_OPTION);
								clearForm();

							}
						}
						model1.setRowCount(0);
						loadDataTable1();

						connection.close();
						statement.close();
						resultSet.close();
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Thêm thất bại", "Failed", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panel_1.add(btn_Add1);
//		new ImageIcon(GDadmin.class.getResource("/image/G.png"))

//		
		JButton btn_Delete1 = new JButton("XOÁ", new ImageIcon(LibraryAdmin.class.getResource("/image/G.png")));
		btn_Delete1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btn_Delete1.setBackground(new Color(64, 128, 128));
		btn_Delete1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_Delete1.setBounds(862, 439, 122, 38);
		btn_Delete1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = connect.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement("DELETE SACH WHERE Ma_Sach =?");
					preparedStatement.setString(1, table1.getValueAt(table1.getSelectedRow(), 0).toString());

					if (JOptionPane.showConfirmDialog(null, "Bạn có muốn xoá dòng này ?", "Cho phép xoá",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						int result = preparedStatement.executeUpdate();

						if (result > 0) {
							JOptionPane.showConfirmDialog(null, "Xoá thành công!", "Success",
									JOptionPane.PLAIN_MESSAGE);
							clearForm();
							model1.setRowCount(0);
							loadDataTable1();
						}
					}
					connection.close();
					preparedStatement.close();

				} catch (Exception ex) {
				}

			}
		});
		panel_1.add(btn_Delete1);

		JButton btn_Fix1 = new JButton("SỬA", new ImageIcon(LibraryAdmin.class.getResource("/image/K.png")));
		btn_Fix1.setBackground(new Color(64, 128, 128));
		btn_Fix1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_Fix1.setBounds(714, 509, 122, 38);
		btn_Fix1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = connect.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(
							"Update SACH set Ten_Sach=?, Ten_tac_gia=?, Nha_xb=?, So_luong=? Where Ma_Sach=?");
					preparedStatement.setString(5, textField_1.getText());
					preparedStatement.setString(1, textField_2.getText());
					preparedStatement.setString(2, textField_3.getText());
					preparedStatement.setString(3, textField_4.getText());
					preparedStatement.setString(4, textField_5.getText());
					int result = preparedStatement.executeUpdate();

					if (result > 0) {
						JOptionPane.showConfirmDialog(null, "Sửa thành công!", "Success", JOptionPane.CLOSED_OPTION);
						clearForm();
						model1.setRowCount(0);
						loadDataTable1();
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		panel_1.add(btn_Fix1);

		JButton btn_Exit1 = new JButton("THOÁT", new ImageIcon(LibraryAdmin.class.getResource("/image/I.png")));
		btn_Exit1.setBackground(new Color(64, 128, 128));
		btn_Exit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btn_Exit1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_Exit1.setBounds(862, 509, 122, 38);
		panel_1.add(btn_Exit1);

		// TODO: CREATE PANEL2 FOR TABBEDPANE
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(new Color(0, 0, 0));
		panel_2.setBackground(new Color(192, 192, 192));
		tabbedPane.addTab("ĐỘC GIẢ", new ImageIcon(LibraryAdmin.class.getResource("/image/C.png")), panel_2, null);
		panel_2.setLayout(null);

		JLabel lbl6 = new JLabel();
		lbl6.setText("Tìm kiếm");
		lbl6.setIcon(new ImageIcon(LibraryAdmin.class.getResource("/image/searchicon.png")));
		lbl6.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl6.setBounds(10, 12, 104, 37);
		panel_2.add(lbl6);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(136, 11, 480, 38);
		textField_6.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				Connection connection = connect.getConnection();
				Statement statement = null;
				ResultSet resultset = null;
				try {
					String sql = "Select * From KHACH_HANG";
					if (textField_6.getText().length() > 0) {
						sql = sql + " where Ma_Khach_hang like'%" + textField_6.getText()
								+ "%' or Ten_Khach_hang like '%" + textField_6.getText() + "%' or Phone like '%"
								+ textField_6.getText() + "%' or Email like '%" + textField_6.getText() + "%'";
					}

					statement = connection.createStatement();
					resultset = statement.executeQuery(sql);
					Vector<String> datarow = null;
					model2.setRowCount(0);
					while (resultset.next()) {
						datarow = new Vector<String>();
						datarow.add(resultset.getString("Ma_Khach_hang"));
						datarow.add(resultset.getString("Ten_Khach_hang"));
						datarow.add(resultset.getString("Phone"));
						datarow.add(resultset.getString("Email"));
						model2.addRow(datarow);
					}
					table2.setModel(model2);
				} catch (Exception ex) {
					ex.printStackTrace();

				} finally {
					try {
						statement.close();
						resultset.close();
						connection.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel_2.add(textField_6);

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(10, 112, 606, 427);
		panel_2.add(scrollPane2);

		table2.setBackground(new Color(192, 192, 192));
		table2.setModel(model2);
		table2.getTableHeader().setReorderingAllowed(false);
		table2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				int rs = table2.getSelectedRow();

				if (rs >= 0) {
					textField_7.setText(table2.getValueAt(rs, 0).toString());
					textField_8.setText(table2.getValueAt(rs, 1).toString());
					textField_9.setText(table2.getValueAt(rs, 2).toString());
					textField_10.setText(table2.getValueAt(rs, 3).toString());
				}

			}
		});

		table2.getColumnModel().getColumn(0).setPreferredWidth(30);
		table2.getColumnModel().getColumn(0).setResizable(false);
		table2.getColumnModel().getColumn(1).setPreferredWidth(100);
		table2.getColumnModel().getColumn(1).setResizable(false);
		table2.getColumnModel().getColumn(2).setPreferredWidth(45);
		table2.getColumnModel().getColumn(2).setResizable(false);
		table2.getColumnModel().getColumn(3).setPreferredWidth(145);
		table2.getColumnModel().getColumn(3).setResizable(false);
		scrollPane2.setViewportView(table2);

		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(760, 116, 224, 30);
		panel_2.add(textField_7);

		JLabel lbl7 = new JLabel("Mã độc giả");
		lbl7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl7.setBounds(651, 112, 99, 25);
		panel_2.add(lbl7);

		JLabel lbl8 = new JLabel("Tên độc giả");
		lbl8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl8.setBounds(651, 177, 99, 25);
		panel_2.add(lbl8);

		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(760, 181, 224, 30);
		panel_2.add(textField_8);

		JLabel lbl9 = new JLabel("Phone");
		lbl9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl9.setBounds(651, 242, 99, 25);
		panel_2.add(lbl9);

		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(760, 246, 224, 30);
		panel_2.add(textField_9);

		JLabel lbl10 = new JLabel("Email");
		lbl10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl10.setBounds(651, 307, 99, 25);
		panel_2.add(lbl10);

		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(760, 311, 224, 30);
		panel_2.add(textField_10);

		JButton btn_Add2 = new JButton("THÊM", new ImageIcon(LibraryAdmin.class.getResource("/image/L.png")));
		btn_Add2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = connect.getConnection();
				Statement statement = null;
				ResultSet resultset = null;
				try {
					if (textField_7.getText().equals("") || textField_8.getText().equals("")
							|| textField_9.getText().equals("") || textField_10.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Cần nhập đủ dữ liệu!", "Empty",
								JOptionPane.WARNING_MESSAGE);
					} else {
						StringBuffer stringBuffer = new StringBuffer();
						String sql_checkHas = "Select Ma_Khach_hang From KHAC_HANG Where Ma_Khach_hang = '"
								+ textField_7.getText() + "'";

						statement = connection.createStatement();
						resultset = statement.executeQuery(sql_checkHas);
						if (resultset.next()) {
							stringBuffer.append("Mã khách hàng đã tồn tại!");
						}
						if (stringBuffer.length() > 0) {
							JOptionPane.showMessageDialog(null, "Mã khách hàng đã tồn tại!", "Empty",
									JOptionPane.WARNING_MESSAGE);
						} else {
							String sql_insert = "Insert into KHACH_HANG values('" + textField_7.getText() + "',"
									+ textField_8.getText() + "','" + textField_9.getText() + ","
									+ textField_10.getText() + "')";
							statement = connection.createStatement();
							int result = statement.executeUpdate(sql_insert);

							if (result > 0) {
								JOptionPane.showMessageDialog(null, "Thêm thành công", "Success",
										JOptionPane.INFORMATION_MESSAGE);
								textField_7.setText("");
								textField_8.setText("");
								textField_9.setText("");
								textField_10.setText("");

								loadDataTable2();
							}
						}

					}
					connection.close();
					statement.close();
					resultset.close();

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Thêm thất bại", "Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btn_Add2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_Add2.setBackground(new Color(64, 128, 128));
		btn_Add2.setBounds(806, 380, 122, 38);
		panel_2.add(btn_Add2);

		JButton btn_Delete2 = new JButton("XOÁ", new ImageIcon(LibraryAdmin.class.getResource("/image/G.png")));
		btn_Delete2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_Delete2.setBackground(new Color(64, 128, 128));
		btn_Delete2.setBounds(806, 505, 122, 38);
		btn_Delete2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Connection connection = connect.getConnection();
				PreparedStatement statement = null;
				try {
					statement = connection.prepareStatement("Delete KHACH_HANG Where Ma_Khach_hang=?");
					statement.setString(1, table2.getValueAt(table2.getSelectedRow(), 0).toString());
					if (JOptionPane.showConfirmDialog(null, "Xóa độc giả?", "Cho phép xóa độc giả",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						statement.executeUpdate();

						textField_7.setText("");
						textField_8.setText("");
						textField_9.setText("");
						textField_10.setText("");

						model2.setRowCount(0);
						loadDataTable2();
					}
					statement.close();
					connection.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Xóa thất bại", "Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel_2.add(btn_Delete2);

		JButton btn_Fix2 = new JButton("SỬA", new ImageIcon(LibraryAdmin.class.getResource("/image/K.png")));
		btn_Fix2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_Fix2.setBackground(new Color(64, 128, 128));
		btn_Fix2.setBounds(806, 442, 122, 38);
		btn_Fix2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Connection connection = connect.getConnection();
				PreparedStatement statement = null;
				try {
					statement = connection.prepareStatement(
							"Update KHACH_HANG set Ten_Khach_hang=?,Phone=?,Email=? Where Ma_Khach_hang=?");
					statement.setString(4, textField_7.getText());
					statement.setString(1, textField_8.getText());
					statement.setString(2, textField_9.getText());
					statement.setString(3, textField_10.getText());

					int result = statement.executeUpdate();

					if (result > 0) {
						JOptionPane.showMessageDialog(null, "Sửa thành công", "Success",
								JOptionPane.INFORMATION_MESSAGE);
						textField_7.setText("");
						textField_8.setText("");
						textField_9.setText("");
						textField_10.setText("");
						model2.setRowCount(0);
						loadDataTable2();
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Sửa thất bại", "Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel_2.add(btn_Fix2);

		JLabel lbl_ListTitle2 = new JLabel("DANH SÁCH NGƯỜI MƯỢN");
		lbl_ListTitle2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_ListTitle2.setBounds(10, 78, 194, 25);
		panel_2.add(lbl_ListTitle2);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 65, 1012, 2);
		panel_2.add(separator_2);

		// TODO: CREATE PANEL3 FOR TABBEDPANE
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(192, 192, 192));
		tabbedPane.addTab("PHIẾU MƯỢN", new ImageIcon(LibraryAdmin.class.getResource("/image/E.png")), panel_3, null);
		panel_3.setLayout(null);

		JLabel lbl11 = new JLabel("Tìm kiếm");
		lbl11.setIcon(new ImageIcon(LibraryAdmin.class.getResource("/image/searchicon.png")));
		lbl11.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl11.setBounds(10, 12, 104, 37);
		panel_3.add(lbl11);

		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(136, 11, 480, 38);
		textField_11.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				try {
					Connection connection = connect.getConnection();
					Statement statement = connection.createStatement();
					ResultSet resultSet = null;
					String sql = "SELECT * FROM PHIEU_MUON";
					if (textField_11.getText().length() > 0) {
						sql = sql + " where Ma_Phieu_muon like '%" + textField_11.getText()
								+ "%' or Ma_Khach_hang like '%" + textField_11.getText() + "%' or Ma_Sach like '%"
								+ textField_11.getText() + "%' or So_luong like '%" + textField_11.getText() + "%'";
					}
					resultSet = statement.executeQuery(sql);
					Vector<String> dataRow = null;
					model3.setRowCount(0);
					while (resultSet.next()) {
						dataRow = new Vector<String>();
						dataRow.add(resultSet.getString("Ma_Phieu_muon"));
						dataRow.add(resultSet.getString("Ma_Khach_hang"));
						dataRow.add(resultSet.getString("Ma_Sach"));
						dataRow.add(resultSet.getDate("Ngay_muon").toString());
						dataRow.add(resultSet.getDate("Ngaytra").toString());
						dataRow.add(resultSet.getInt("So_luong") + "");

						model3.addRow(dataRow);
					}
					table3.setModel(model3);
					connection.close();
					statement.close();
					resultSet.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		panel_3.add(textField_11);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 112, 606, 427);
		panel_3.add(scrollPane_3);

		table3 = new JTable();
		table3.setBackground(new Color(192, 192, 192));
		table3.setModel(model3);
		table3.getTableHeader().setReorderingAllowed(false);
		table3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);

				int rs = table3.getSelectedRow();
				if (rs >= 0) {
					textField_12.setText(table3.getValueAt(rs, 0).toString());
					textField_13.setText(table3.getValueAt(rs, 1).toString());
					textField_14.setText(table3.getValueAt(rs, 2).toString());
					textField_15.setText(table3.getValueAt(rs, 3).toString());
					textField_16.setText(table3.getValueAt(rs, 4).toString());
					textField_17.setText(table3.getValueAt(rs, 5).toString());
				}

			}
		});
		scrollPane_3.setViewportView(table3);

		table3.getColumnModel().getColumn(0).setPreferredWidth(60);
		table3.getColumnModel().getColumn(0).setResizable(false);
		table3.getColumnModel().getColumn(1).setPreferredWidth(45);
		table3.getColumnModel().getColumn(1).setResizable(false);
		table3.getColumnModel().getColumn(2).setPreferredWidth(45);
		table3.getColumnModel().getColumn(2).setResizable(false);
		table3.getColumnModel().getColumn(3).setPreferredWidth(65);
		table3.getColumnModel().getColumn(3).setResizable(false);
		table3.getColumnModel().getColumn(4).setPreferredWidth(65);
		table3.getColumnModel().getColumn(4).setResizable(false);
		table3.getColumnModel().getColumn(5).setPreferredWidth(5);
		table3.getColumnModel().getColumn(5).setResizable(false);

		JLabel lbl_ListTitle3 = new JLabel("DANH SÁCH CÁC PHIẾU MƯỢN");
		lbl_ListTitle3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_ListTitle3.setBounds(10, 78, 247, 25);
		panel_3.add(lbl_ListTitle3);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 65, 1012, 2);
		panel_3.add(separator_3);

		JLabel lbl12 = new JLabel("Mã phiếu mượn");
		lbl12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl12.setBounds(651, 112, 102, 25);
		panel_3.add(lbl12);

		JLabel lbl13 = new JLabel("Mã độc giả");
		lbl13.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl13.setBounds(651, 177, 99, 25);
		panel_3.add(lbl13);

		JLabel lbl14 = new JLabel("Mã sách");
		lbl14.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl14.setBounds(651, 242, 99, 25);
		panel_3.add(lbl14);

		JLabel lbl15 = new JLabel("Ngày mượn");
		lbl15.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl15.setBounds(651, 307, 99, 25);
		panel_3.add(lbl15);

		JLabel lbl16 = new JLabel("Ngày trả");
		lbl16.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl16.setBounds(651, 372, 99, 25);
		panel_3.add(lbl16);

		JLabel lbl17 = new JLabel("Số lượng mượn");
		lbl17.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl17.setBounds(651, 436, 99, 28);
		panel_3.add(lbl17);

		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(760, 116, 224, 30);
		panel_3.add(textField_12);

		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(760, 181, 224, 30);
		panel_3.add(textField_13);

		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(760, 246, 224, 30);
		panel_3.add(textField_14);

		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(760, 311, 224, 30);
		panel_3.add(textField_15);

		textField_16 = new JTextField();
		textField_16.setColumns(10);
		textField_16.setBounds(760, 376, 224, 30);
		panel_3.add(textField_16);

		textField_17 = new JTextField();
		textField_17.setColumns(10);
		textField_17.setBounds(760, 439, 224, 30);
		panel_3.add(textField_17);

		JButton btn_Add3 = new JButton("THÊM", new ImageIcon(LibraryAdmin.class.getResource("/image/L.png")));
		btn_Add3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_Add3.setBackground(new Color(64, 128, 128));
		btn_Add3.setBounds(628, 501, 122, 38);
		btn_Add3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Connection connection = connect.getConnection();
				Statement statement = null;
				ResultSet resultset = null;
				try {
					if (textField_12.getText().equals("") || textField_13.getText().equals("")
							|| textField_14.getText().equals("") || textField_15.getText().equals("")
							|| textField_16.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Cần nhập đủ dữ liệu!", "Empty",
								JOptionPane.WARNING_MESSAGE);
					} else {
						StringBuffer stringBuffer = new StringBuffer();
						String sql_hascheck = "Select Ma_Phieu_Muon From PHIEU_MUON Where Ma_Phieu_muon = '"
								+ textField_12.getText() + "'";

						statement = connection.createStatement();
						resultset = statement.executeQuery(sql_hascheck);
						if (resultset.next()) {
							stringBuffer.append("Mã phiếu mượn đã tồn tại");
						}
						if (stringBuffer.length() > 0) {
							JOptionPane.showMessageDialog(null, "Mã phiếu đã tồn tại!", "Empty",
									JOptionPane.WARNING_MESSAGE);
						} else {
							String sql = "Insert into PHIEU_MUON values('" + textField_12.getText() + "','"
									+ textField_13.getText() + "','" + textField_14.getText() + "','"
									+ textField_15.getText() + "','" + textField_16.getText() + "','"
									+ textField_17.getText() + "')";
							int result = statement.executeUpdate(sql);

							if (result > 0) {
								JOptionPane.showMessageDialog(null, "Thêm thành công", "Success",
										JOptionPane.INFORMATION_MESSAGE);
								textField_12.setText("");
								textField_13.setText("");
								textField_14.setText("");
								textField_15.setText("");
								textField_16.setText("");
								textField_17.setText("");

								loadDataTable3();
							}
						}
						connection.close();
						statement.close();
						resultset.close();
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
		panel_3.add(btn_Add3);

		JButton btn_Delete3 = new JButton("XOÁ", new ImageIcon(LibraryAdmin.class.getResource("/image/G.png")));
		btn_Delete3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Connection connection = connect.getConnection();
					PreparedStatement preparedstatement = connection
							.prepareStatement("Delete PHIEU_MUON Where Ma_Phieu_Muon=?");
					preparedstatement.setString(1, table3.getValueAt(table3.getRowCount(), 0).toString());

					if (JOptionPane.showConfirmDialog(null, "Xóa phiếu?", "Cho phép xóa phiếu",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						preparedstatement.executeUpdate();
						textField_12.setText("");
						textField_13.setText("");
						textField_14.setText("");
						textField_15.setText("");
						textField_16.setText("");
						textField_17.setText("");

						model3.setRowCount(0);
						loadDataTable3();
					}
					connection.close();
					preparedstatement.close();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Xóa thất bại", "Failed", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btn_Delete3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_Delete3.setBackground(new Color(64, 128, 128));
		btn_Delete3.setBounds(894, 501, 122, 38);
		panel_3.add(btn_Delete3);

		JButton btn_Fix3 = new JButton("SỬA", new ImageIcon(LibraryAdmin.class.getResource("/image/K.png")));
		btn_Fix3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = connect.getConnection();
					PreparedStatement preparedstatement = connection.prepareStatement(
							"Update PHIEU_MUON set Ma_Khach_hang=?, Ma_Sach=?, Ngay_muon=?, Ngaytra=?, So_luong=? Where Ma_Phieu_muon=?");
					preparedstatement.setString(6, textField_12.getText());
					preparedstatement.setString(1, textField_13.getText());
					preparedstatement.setString(2, textField_14.getText());
					preparedstatement.setString(3, textField_15.getText());
					preparedstatement.setString(4, textField_16.getText());
					preparedstatement.setString(5, textField_17.getText());

					int result = preparedstatement.executeUpdate();
					if (result > 0) {
						JOptionPane.showMessageDialog(null, "Sửa thành công", "Success",
								JOptionPane.INFORMATION_MESSAGE);

						textField_12.setText("");
						textField_13.setText("");
						textField_14.setText("");
						textField_15.setText("");
						textField_16.setText("");
						textField_17.setText("");

						model3.setRowCount(0);
						loadDataTable3();
					}
					connection.close();
					preparedstatement.close();

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Sửa thất bại", "Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btn_Fix3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_Fix3.setBackground(new Color(64, 128, 128));
		btn_Fix3.setBounds(760, 501, 122, 38);
		panel_3.add(btn_Fix3);

		JLabel lbl_Title = new JLabel("QUẢN LÝ THƯ VIỆN");
		lbl_Title.setForeground(new Color(0, 128, 255));
		lbl_Title.setBackground(new Color(255, 255, 255));
		lbl_Title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Title.setFont(new Font("Segoe UI Semibold", Font.BOLD, 30));
		lbl_Title.setBounds(532, 11, 384, 49);
		this.getContentPane().add(lbl_Title);

		loadDataTable1();
		loadDataTable2();
		loadDataTable3();
		this.setVisible(true);
	}
}