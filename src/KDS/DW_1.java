/* 
 * In this module mostly used to maintain all data of the Driver worker of work. 
 * Mostly, Record new work details,
 * Can update update exist record into among different records,
 * Can be deleted a record,
 * Can be searched any particular details of works in day/month/year wise,
 * 
 * Important,
 * In this module cann't be saved other category of worker details and also customer details.
 * Can be maintained Driver work details. 
 */

package KDS;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.border.TitledBorder;


import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

public class DW_1 { //Main class

	private JFrame DWFrame;
	private JTextField did;
	private JTextField dn;
	private JTextField ta;
	private JTextField sid;
	private JTable table;
	private JTextField hr;
	Date date = new Date();
	Connection c;
	PreparedStatement pst;
	ResultSet rs;
	private JLabel dsid = new JLabel("");
	private JLabel dsn = new JLabel("");
	private JLabel dsd = new JLabel("");
	private JLabel dsh = new JLabel("");
	private JLabel dsa = new JLabel("");
	private JLabel DelDatecheck;
	private JTextField Atf;
	JDateChooser datenewRecord,SdateS,SdateE;
	
	public void Divers() { // Main method
	//public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DW_1 window = new DW_1();
					window.DWFrame.setVisible(true);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}

	public DW_1() { // Constructor of main class
		initialize();
		connect();
		table_load();
	}

	private void initialize() { // Initiate of main frame.
		DWFrame = new JFrame();
		DWFrame.getContentPane().setBackground(new Color(210, 105, 30));
		DWFrame.setBounds(100, 100, 1100, 550);
		DWFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DWFrame.getContentPane().setLayout(null);
		DWFrame.setResizable(false);
		
		JPanel panel = new JPanel(); // name board of the Driver module
		panel.setBackground(new Color(178, 34, 34));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 1064, 47);
		DWFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Drivers");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 38));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(402, 0, 206, 47);
		panel.add(lblNewLabel);
		
		JButton btnNewButton_1_4 = new JButton("HOME");
		btnNewButton_1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPageClone m = new MainPageClone();
				m.mainPageclone();
				DWFrame.dispose();
			}
		});
		btnNewButton_1_4.setForeground(Color.RED);
		btnNewButton_1_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_1_4.setBackground(new Color(255, 127, 80));
		btnNewButton_1_4.setBounds(965, 0, 99, 47);
		panel.add(btnNewButton_1_4);
		
		// this part is, to enter a new components.
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(205, 133, 63));
		panel_1.setBorder(new TitledBorder(null, "New Record", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 69, 407, 205);
		DWFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(28, 31, 46, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Name");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(28, 63, 46, 14);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Date");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(28, 95, 46, 14);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Hour");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(28, 131, 46, 14);
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Total amount");
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_4.setBounds(28, 167, 104, 14);
		panel_1.add(lblNewLabel_1_4);
		
		did = new JTextField(); // When enter the ID of employees the name field automatically should fill. This is that process.
		did.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		did.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				 String Id=did.getText();
				 if(Id.startsWith("DW")&& did.getText().length()==4)
				 {
		          try
		          {
					pst = c.prepareStatement("select * from details where ID=?");
					pst.setString(1, Id);
					rs = pst.executeQuery();
					if(rs.next())
					{
						String n=rs.getString(3); 
						dn.setText(n);
					}
				} catch (SQLException e1) {e1.printStackTrace();}
				 }
				 else {dn.setText("");}
			}
		});
		did.setBounds(128, 29, 134, 20);
		panel_1.add(did);
		did.setColumns(10);
		
		dn = new JTextField();
		dn.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		dn.setColumns(10);
		dn.setBounds(128, 61, 134, 20);
		panel_1.add(dn);
		
		ta = new JTextField();
		ta.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		ta.setColumns(10);
		ta.setBounds(128, 165, 134, 20);
		panel_1.add(ta);
		
		hr = new JTextField(); // this process of calculate amount for work 
		hr.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		hr.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(Atf.getText().equals("")){ta.setText("");}
				else
				{
				String Hr=hr.getText();
				int h=Integer.parseInt(Hr);
				String atf = Atf.getText();
				int atf1 = Integer.parseInt(atf);
				double add = h*atf1;
				String a1=Double.toString(add);
				ta.setText(a1);
				}
			}
		});
		hr.setColumns(10);
		hr.setBounds(128, 129, 46, 20);
		panel_1.add(hr);
		
		JLabel lblNewLabel_1_6 = new JLabel("Hr");
		lblNewLabel_1_6.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_6.setBounds(184, 131, 46, 14);
		panel_1.add(lblNewLabel_1_6);
		
		Atf = new JTextField(); // When we change the value of Hour. That time the calculation process should be reactivated.
		Atf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(Atf.getText().equals("")){ta.setText("");}
				else
				{
				String Hr=hr.getText();
				int h=Integer.parseInt(Hr);
				String atf = Atf.getText();
				int atf1 = Integer.parseInt(atf);
				double add = h*atf1;
				String a1=Double.toString(add);
				ta.setText(a1);
				}
			}
		});
		Atf.setText("75");
		Atf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		Atf.setColumns(10);
		Atf.setBounds(236, 129, 46, 20);
		panel_1.add(Atf);
		
		JDateChooser datenewRecord = new JDateChooser(date);
		datenewRecord.setDateFormatString("dd-MM-y");
		datenewRecord.setBackground(new Color(218, 165, 32));
		datenewRecord.setBounds(128, 89, 134, 20);
		panel_1.add(datenewRecord);
		
		JLabel DelDatecheck = new JLabel("");
		DelDatecheck.setFont(new Font("Times New Roman", Font.BOLD, 14));
		DelDatecheck.setBounds(277, 89, 104, 21);
		panel_1.add(DelDatecheck);
		
		// This part is, to display details of all employees from the database.
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(205, 133, 63));
		panel_2.setBorder(new TitledBorder(null, "Records", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(427, 70, 647, 279);
		DWFrame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 627, 249);
		panel_2.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.BOLD, 14));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=table.getSelectedRow();
				String ID=table.getModel().getValueAt(i, 1).toString();
				String na=table.getModel().getValueAt(i, 2).toString();
				String dt=table.getModel().getValueAt(i, 3).toString();
				String Hr=table.getModel().getValueAt(i, 4).toString();
				String TA=table.getModel().getValueAt(i, 5).toString();
				did.setText(ID);
				dn.setText(na);
				DelDatecheck.setText(dt);
			    hr.setText(Hr);
				ta.setText(TA);
				dsid.setText(ID);
			    dsn.setText(na);
			    dsd.setText(dt);
			    dsh.setText(Hr);
			    dsa.setText(TA);
			}
		});
		scrollPane.setViewportView(table);
		
		// This part is, To save a record into database. 
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setBackground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setBackground(new Color(245, 222, 179));
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Id=did.getText();
				String Na=dn.getText();
				if(did.getText().equals("")||dn.getText().equals("")||hr.getText().equals("")||ta.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, " Please fill complete Information");
				}
				else
				{
					if(Id.startsWith("DW") && Id.length()==4) {
						 try {
								pst = c.prepareStatement("select * from details where ID=? and _NAME=?");
								pst.setString(1, Id);
								pst.setString(2, Na);
								rs = pst.executeQuery();
								if(rs.next())
								{
				                     String ID,Name,Dt,Hr,Ta;
				                     ID = did.getText();
				                     Name = dn.getText();
				                     Date d1 = datenewRecord.getDate();
				                     SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/dd");
				                     Dt = formatter.format(d1);
				                     Hr=hr.getText();
				                     Ta=ta.getText();
				                     try {
				                          pst = c.prepareStatement("insert into dw(ID,DName,_Date,_Hour,Total_Amount)values(?,?,?,?,?)");
				                          pst.setString(1, ID);
				                          pst.setString(2, Name);
				                          pst.setString(3, Dt);
				                          pst.setString(4, Hr);
				                          pst.setString(5, Ta);
				                          pst.executeUpdate();
				                          JOptionPane.showMessageDialog(null, " Detail saved. ");
			                              table_load();
				                          Clear();
                                          did.requestFocus();
				                        }catch (SQLException e1){e1.printStackTrace();}
								}else{JOptionPane.showMessageDialog(null, Id+ " is Not Here. ");}
				             }catch (SQLException e1) {e1.printStackTrace();}
					     }else {JOptionPane.showMessageDialog(null, " Please, Enter the correct ID ");}		
				}
			}
		});
		btnNewButton.setBackground(new Color(245, 222, 179));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.setBounds(28, 298, 89, 23);
		DWFrame.getContentPane().add(btnNewButton);
		
		// This part is, To update exist data into database.
		
		JButton btnNewButton_1_1 = new JButton("Update");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1_1.setBackground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1_1.setBackground(new Color(245, 222, 179));
			}
		});
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Id=did.getText();
				String Na=dn.getText();
				if(did.getText().equals("")||dn.getText().equals("")||hr.getText().equals("")||ta.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, " Please, first select a record");
				}
				else
				{
					if(Id.startsWith("DW") && Id.length()==4) {
						 try {
								pst = c.prepareStatement("select * from details where ID=? and _Name=?");
								pst.setString(1, Id);
								pst.setString(2, Na);
								rs = pst.executeQuery();
								if(rs.next())
								{
				                    try {
					                     String ID,Name,Dt,Hr,Ta;
					                     ID = did.getText();
					                     Name = dn.getText();
					                     Date d1 = datenewRecord.getDate();
					                     SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/dd");
					                     Dt = formatter.format(d1);
					                     Hr=hr.getText();
					                     Ta=ta.getText();
			       	                     pst = c.prepareStatement("update dw set _Hour=?, Total_Amount=?,DNAME=?,_Date=?  where ID=?");
				                         pst.setString(1, Hr);
				                         pst.setString(2, Ta);
				                         pst.setString(3, Name);
				                         pst.setString(4, Dt);
				                         pst.setString(5, ID);
				                         pst.executeUpdate();
				                         JOptionPane.showMessageDialog(null, " Detail updated.");
				                         table_load();
		                                 Clear();
					                     did.requestFocus(); 
			                        	}catch (SQLException e1) {e1.printStackTrace();}
								}else{JOptionPane.showMessageDialog(null, Id+ " is Not Here. ");}
						 }catch (SQLException e1) {e1.printStackTrace();}
					}else {JOptionPane.showMessageDialog(null, " Please, Enter the correct ID");}				
			    }
			}
		});
		btnNewButton_1_1.setBackground(new Color(245, 222, 179));
		btnNewButton_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_1_1.setBounds(156, 298, 104, 23);
		DWFrame.getContentPane().add(btnNewButton_1_1);
		
		// This part is, to delete a record from database.
		
		JButton btnNewButton_1_2 = new JButton("Delete");
		btnNewButton_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1_2.setBackground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1_2.setBackground(new Color(245, 222, 179));
			}
		});
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(did.getText().equals("")||dn.getText().equals("")||hr.getText().equals("")||ta.getText().equals("")||DelDatecheck.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, " Please, first select a record");
				}
				else
				{
				           try {
					            String ID,Name,Dt,Hr,Ta;
					            ID = did.getText();
					            Name = dn.getText();
					            Dt=DelDatecheck.getText();
					            Hr=hr.getText();
					            Ta=ta.getText();
				                pst = c.prepareStatement("delete from dw where ID=? and DNAME=? and _Date=? and _Hour=? and Total_Amount=? ");
				                pst.setString(1, ID);
				                pst.setString(2, Name);
				                pst.setString(3, Dt);
				                pst.setString(4, Hr);
				                pst.setString(5, Ta);
				                pst.executeUpdate();
			                 	JOptionPane.showMessageDialog(null, " Detail Deleted.");
				                table_load();
		                        Clear();
					            did.requestFocus();         
				               }catch (SQLException e1){e1.printStackTrace();}
		 	    }
			}
		});
		btnNewButton_1_2.setBackground(new Color(245, 222, 179));
		btnNewButton_1_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_1_2.setBounds(302, 298, 89, 23);
		DWFrame.getContentPane().add(btnNewButton_1_2);
		
		
		// This part is, to search a details of particular employees from database based on day/month/year wise
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(205, 133, 63));
		panel_3.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 349, 407, 99);
		DWFrame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1_5 = new JLabel("ID");
		lblNewLabel_1_5.setBounds(30, 27, 71, 17);
		lblNewLabel_1_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		panel_3.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_5_1 = new JLabel("Date");
		lblNewLabel_1_5_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_5_1.setBounds(168, 27, 42, 17);
		panel_3.add(lblNewLabel_1_5_1);
		
		sid = new JTextField();
		sid.setText("DW");
		sid.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sid.setColumns(10);
		sid.setBounds(60, 26, 94, 20);
		panel_3.add(sid);
		
		JLabel lblNewLabel_1_5_1_1 = new JLabel("Date");
		lblNewLabel_1_5_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_5_1_1.setBounds(168, 60, 42, 17);
		panel_3.add(lblNewLabel_1_5_1_1);
		
		JDateChooser SdateS = new JDateChooser(date);
		SdateS.setDateFormatString("dd-MM-y");
		SdateS.setBackground(new Color(218, 165, 32));
		SdateS.setBounds(220, 27, 119, 20);
		panel_3.add(SdateS);
		
		JDateChooser SdateE = new JDateChooser(date);
		SdateE.setDateFormatString("dd-MM-y");
		SdateE.setBackground(new Color(218, 165, 32));
		SdateE.setBounds(220, 60, 119, 20);
		panel_3.add(SdateE);
		
		// From here, the search is started.
		
		JButton btnNewButton_1_2_1 = new JButton("Search");
		btnNewButton_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Id=sid.getText();
				if(sid.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, " Please fill complete Information");
				}
				else
				{
					if(Id.startsWith("DW") && Id.length()==4) {
						 try {
								pst = c.prepareStatement("select * from details where ID=?");
								pst.setString(1, Id);
								rs = pst.executeQuery();
								if(rs.next())
								{
									String ID,Dts,Dte;
									ID = sid.getText();
									Date d1 = SdateS.getDate();
									SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/dd");
									Dts = formatter.format(d1);
									Date d2 = SdateE.getDate();
									SimpleDateFormat formatter1 = new SimpleDateFormat("YYYY/MM/dd");
									Dte = formatter1.format(d2);
									if(Dts.compareTo(Dte)<0 || Dts.compareTo(Dte)==0)
									{
									try {
									pst = c.prepareStatement("Select * from dw where ID =? and _Date between ? and ? ");
									pst.setString(1, ID);
									pst.setString(2, Dts);
									pst.setString(3, Dte);
									rs = pst.executeQuery();
									table.setModel(DbUtils.resultSetToTableModel(rs));
									Clear();
									sid.requestFocus();
									}catch (SQLException e1){e1.printStackTrace();}
									}else {JOptionPane.showMessageDialog(null, Dts+ "Starting date higher than of Ending date. "+Dte);}
								} else {JOptionPane.showMessageDialog(null, Id+ " is Not Here. ");}
						 }catch (SQLException e1) {e1.printStackTrace();}
					}else {JOptionPane.showMessageDialog(null, " Please, Enter the correct ID");}
				}
			}
		});
		btnNewButton_1_2_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_1_2_1.setBackground(new Color(245, 222, 179));
		btnNewButton_1_2_1.setBounds(157, 465, 89, 23);
		DWFrame.getContentPane().add(btnNewButton_1_2_1);
	
		// This part is, to display the details of an employees on application.
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBackground(new Color(205, 133, 63));
		panel_4.setBounds(427, 360, 647, 88);
		DWFrame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("ID :");
		lblNewLabel_1_4_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_4_1.setBounds(10, 23, 28, 14);
		panel_4.add(lblNewLabel_1_4_1);
		
		JLabel lblNewLabel_1_4_2 = new JLabel("Name :");
		lblNewLabel_1_4_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_4_2.setBounds(230, 24, 50, 14);
		panel_4.add(lblNewLabel_1_4_2);
		
		JLabel lblNewLabel_1_4_3 = new JLabel("Date :");
		lblNewLabel_1_4_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_4_3.setBounds(425, 24, 38, 14);
		panel_4.add(lblNewLabel_1_4_3);
		
		JLabel lblNewLabel_1_4_4 = new JLabel("Hour : ");
		lblNewLabel_1_4_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_4_4.setBounds(10, 48, 44, 14);
		panel_4.add(lblNewLabel_1_4_4);
		
		JLabel lblNewLabel_1_4_5 = new JLabel("Amount :");
		lblNewLabel_1_4_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_4_5.setBounds(230, 49, 64, 14);
		panel_4.add(lblNewLabel_1_4_5);
		
		JLabel lblNewLabel_1_4_6 = new JLabel("Hrs.");
		lblNewLabel_1_4_6.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_4_6.setBounds(75, 48, 28, 14);
		panel_4.add(lblNewLabel_1_4_6);
		
		dsid.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsid.setBounds(43, 24, 64, 14);
		panel_4.add(dsid);
		
		dsn.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsn.setBounds(280, 24, 110, 14);
		panel_4.add(dsn);
		
		dsd.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsd.setBounds(469, 24, 93, 14);
		panel_4.add(dsd);
		
		dsh.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsh.setBounds(53, 49, 20, 14);
		panel_4.add(dsh);
		
		dsa.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsa.setBounds(290, 49, 85, 14);
		panel_4.add(dsa);
		
		JLabel dsa_1 = new JLabel("");
		dsa_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsa_1.setBounds(508, 50, 85, 14);
		panel_4.add(dsa_1);
	
		JButton btnNewButton_1_2_1_1 = new JButton("Clear");
		btnNewButton_1_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    Clear();
				table_load();
			}
		});
		btnNewButton_1_2_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_1_2_1_1.setBackground(new Color(245, 222, 179));
		btnNewButton_1_2_1_1.setBounds(974, 459, 89, 23);
		DWFrame.getContentPane().add(btnNewButton_1_2_1_1);
	}
	
	// This part is, to connect the database with this module.
	public void connect()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c=DriverManager.getConnection("jdbc:mysql://localhost:3306/kds", "root", "HWsp@524");
		}catch (ClassNotFoundException ex) {System.out.println(ex);}
		catch (SQLException ex) {System.out.println(ex);}
	}
	
	// This part is, when we do each process after the database should be reloaded.
	 public void table_load()
	 {
	     try{
	         pst = c.prepareStatement("select * from dw");
	         rs = pst.executeQuery();
	         table.setModel(DbUtils.resultSetToTableModel(rs));
	     }catch (SQLException e) { e.printStackTrace(); }
	}
	 
	public void Clear()
	{
		did.setText("");
		dn.setText("");
		hr.setText("");
		Atf.setText("75");
		ta.setText("");
		sid.setText("DW");
		dsid.setText("");
		dsh.setText("");
		dsn.setText("");
		dsa.setText("");
		dsd.setText("");
		datenewRecord.setDate(date);
		SdateS.setDate(date);
		SdateE.setDate(date);
		DelDatecheck.setText("");
	}
}