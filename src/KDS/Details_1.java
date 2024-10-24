/* 
 * In this module mostly used to maintain all data of the employees. 
 * Mostly, Record new work details,
 * Can update update exist record into among different records,
 * Can be deleted a record,
 * Can be searched any particular details of worker,
 * 
 * Important,
 * In this module cann't be saved other category of worker and Customer details.
 * Can be maintained employee details. 
 */

package KDS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import net.proteanit.sql.DbUtils;

import com.toedter.calendar.JDateChooser;

public class Details_1 { // Main class

	private JFrame DFrame;
	private JTable table;
	private JTextField nid;
	private JTextField nn;
	private JTextField nm;
	private JTextField nad;
	private JTextField sid;
	Date date = new Date();
	Connection c;
	PreparedStatement pst;
	ResultSet rs;
	private JLabel gfl = new JLabel("?");
	private JTextField nd;
	private JLabel nl,idl,gl,ml,aml,al,dl, DelDatecheck;
	JDateChooser datenewRecord;
	JComboBox ng,cc;
	
	public void del() { // Main method
	//public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Details_1 window = new Details_1();
					window.DFrame.setVisible(true);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}

	public Details_1() { // constructor of main class
		initialize();
		connect();
		table_load();
	}

	private void initialize() {//Initiate of main frame
		DFrame = new JFrame();
		DFrame.getContentPane().setBackground(new Color(210, 105, 30));
		DFrame.setBounds(100, 100, 1100, 550);
		DFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DFrame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel(); // Name board of Details module.
		panel.setBounds(10, 11, 1064, 43);
		panel.setBackground(new Color(165, 42, 42));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		DFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Details");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 38));
		lblNewLabel_2.setBounds(388, 0, 309, 43);
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("HOME"); // Home page button with activation. 
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPageClone m = new MainPageClone();
				m.mainPageclone();
				DFrame.dispose();
			}
		});
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_1.setBackground(new Color(255, 127, 80));
		btnNewButton_1.setBounds(965, 0, 99, 43);
		panel.add(btnNewButton_1);
		
		// this part is, to enter a new record on database.
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 65, 699, 167);
		panel_1.setBackground(new Color(205, 133, 63));
		panel_1.setBorder(new TitledBorder(null, "New Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		DFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(23, 28, 72, 20);
		panel_1.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblName.setBounds(23, 59, 72, 20);
		panel_1.add(lblName);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblGender.setBounds(23, 90, 72, 20);
		panel_1.add(lblGender);
		
		JLabel lblMobileNo = new JLabel("Mobile No");
		lblMobileNo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblMobileNo.setBounds(23, 125, 83, 20);
		panel_1.add(lblMobileNo);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblAddress.setBounds(348, 63, 72, 20);
		panel_1.add(lblAddress);
		
		JLabel lblDestination = new JLabel("Designation");
		lblDestination.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDestination.setBounds(348, 94, 90, 20);
		panel_1.add(lblDestination);
		
		JLabel lblAdvanceAmount = new JLabel("Date ");
		lblAdvanceAmount.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblAdvanceAmount.setBounds(348, 28, 113, 20);
		panel_1.add(lblAdvanceAmount);
		
		nid = new JTextField(); // When we enter category code the category field is automatically fill.
		nid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String Id=nid.getText();
				if(Id.startsWith("BW")) {nd.setText("Brick_Worker");}
				else if(Id.startsWith("LW")) {nd.setText("Load_Woker");}
				else if(Id.startsWith("DW")){nd.setText("Driver");}
				else {nd.setText("");}
			}
		});
		nid.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		nid.setBounds(133, 29, 113, 20);
		panel_1.add(nid);
		nid.setColumns(10);
		
		nn = new JTextField();
		nn.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		nn.setColumns(10);
		nn.setBounds(133, 60, 113, 20);
		panel_1.add(nn);
		
		nm = new JTextField();
		nm.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		nm.setColumns(10);
		nm.setBounds(133, 126, 113, 20);
		panel_1.add(nm);
		
		nad = new JTextField();
		nad.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		nad.setColumns(10);
		nad.setBounds(471, 60, 218, 20);
		panel_1.add(nad);
		
		JComboBox ng = new JComboBox();
		ng.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		ng.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		ng.setBounds(133, 90, 113, 22);
		panel_1.add(ng);
		
		nd = new JTextField();
		nd.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		nd.setColumns(10);
		nd.setBounds(471, 91, 113, 20);
		panel_1.add(nd);
		
		JDateChooser datenewRecord = new JDateChooser(date);
		datenewRecord.setDateFormatString("dd-MM-y");
		datenewRecord.setBackground(new Color(218, 165, 32));
		datenewRecord.setBounds(471, 28, 113, 20);
		panel_1.add(datenewRecord);
		
		JLabel DelDatecheck = new JLabel("");
		DelDatecheck.setFont(new Font("Times New Roman", Font.BOLD, 16));
		DelDatecheck.setBounds(594, 28, 95, 20);
		panel_1.add(DelDatecheck);
		
		// This part is, to display employees on application window when we click particular detail.
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(719, 230, 355, 226);
		panel_2.setBackground(new Color(205, 133, 63));
		panel_2.setBorder(new TitledBorder(null, "Detail", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		DFrame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblId = new JLabel("ID :");
		lblId.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblId.setBounds(10, 58, 38, 17);
		panel_2.add(lblId);
		
		JLabel lblNewLabel_3_1 = new JLabel("Name :");
		lblNewLabel_3_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(10, 30, 60, 17);
		panel_2.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Gender :");
		lblNewLabel_3_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3_2.setBounds(10, 86, 72, 17);
		panel_2.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_3 = new JLabel("Mobile No :");
		lblNewLabel_3_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3_3.setBounds(10, 114, 72, 17);
		panel_2.add(lblNewLabel_3_3);
		
		JLabel lblNewLabel_3_4 = new JLabel("Date : ");
		lblNewLabel_3_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3_4.setBounds(10, 142, 51, 17);
		panel_2.add(lblNewLabel_3_4);
		
		JLabel lblNewLabel_3_5 = new JLabel("Address :");
		lblNewLabel_3_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3_5.setBounds(10, 170, 72, 17);
		panel_2.add(lblNewLabel_3_5);
		
		JLabel lblNewLabel_3_6 = new JLabel("Designation :");
		lblNewLabel_3_6.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3_6.setBounds(10, 198, 82, 17);
		panel_2.add(lblNewLabel_3_6);
		
		JLabel idl = new JLabel("");
		idl.setFont(new Font("Times New Roman", Font.BOLD, 14));
		idl.setBounds(58, 58, 72, 17);
		panel_2.add(idl);
		
		JLabel gl = new JLabel("");
		gl.setFont(new Font("Times New Roman", Font.BOLD, 14));
		gl.setBounds(88, 86, 72, 17);
		panel_2.add(gl);
		
		JLabel ml = new JLabel("");
		ml.setFont(new Font("Times New Roman", Font.BOLD, 14));
		ml.setBounds(92, 114, 94, 17);
		panel_2.add(ml);
		
		JLabel aml = new JLabel("");
		aml.setFont(new Font("Times New Roman", Font.BOLD, 14));
		aml.setBounds(66, 142, 94, 17);
		panel_2.add(aml);
		
		JLabel al = new JLabel("");
		al.setFont(new Font("Times New Roman", Font.BOLD, 14));
		al.setBounds(78, 170, 242, 17);
		panel_2.add(al);
		
		JLabel dl = new JLabel("");
		dl.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dl.setBounds(99, 198, 118, 17);
		panel_2.add(dl);
		
		JLabel nl = new JLabel("");
		nl.setFont(new Font("Times New Roman", Font.BOLD, 14));
		nl.setBounds(80, 30, 94, 17);
		panel_2.add(nl);
		
		JLabel aal = new JLabel("");
		aal.setFont(new Font("Times New Roman", Font.BOLD, 14));
		aal.setBounds(285, 56, 60, 17);
		panel_2.add(aal);
		
		//This part is, when we a record in a table. those records are display appropriate field. 
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 274, 699, 226);
		panel_3.setBackground(new Color(205, 133, 63));
		panel_3.setBorder(new TitledBorder(null, "Records", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		DFrame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(240, 230, 140));
		scrollPane.setBounds(10, 25, 679, 200);
		panel_3.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.BOLD, 14));
		table.setBackground(new Color(255, 255, 255));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=table.getSelectedRow();
				String ID=table.getModel().getValueAt(i, 1).toString();
				String na=table.getModel().getValueAt(i, 2).toString();
				String gen=table.getModel().getValueAt(i, 3).toString();
				String mno=table.getModel().getValueAt(i, 4).toString();
				String dt=table.getModel().getValueAt(i, 5).toString();
				String ad=table.getModel().getValueAt(i, 6).toString();
				String job=table.getModel().getValueAt(i, 7).toString();
				nid.setText(ID);
				nn.setText(na);
				ng.setSelectedItem(gen);
				nm.setText(mno);
				DelDatecheck.setText(dt);
			    nad.setText(ad);
				nd.setText(job);
				idl.setText(ID);
				nl.setText(na);
				gl.setText(gen);
				ml.setText(mno);
				aml.setText(dt);
			    al.setText(ad);
				dl.setText(job);
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		// This part is, to save a new record on database.
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBounds(210, 240, 89, 23);
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
		btnNewButton.setBackground(new Color(245, 222, 179));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Id=nid.getText();
				if(nid.getText().equals("")||nd.getText().equals("")||nn.getText().equals("")||nm.getText().equals("")||nad.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, " Please fill complete Information");
				}
				else
				{
					if(nid.getText().length()==4 && Id.startsWith("DW")||nid.getText().length()==4 && Id.startsWith("LW")||nid.getText().length()==4 && Id.startsWith("BW"))
					{
						if(nn.getText().matches("[a-zA-Z.]+"))
						{
						if(nm.getText().matches("^[0-9]*$") && nm.getText().length()==10 )
						{
							 try {
									pst = c.prepareStatement("select * from details where ID=?");
									pst.setString(1, Id);
									rs = pst.executeQuery();
									if(rs.next()){JOptionPane.showMessageDialog(null, Id+ " is already exist !");}
									else {
											String ID,Name,gen,mno,Dt,ad,job;
											ID = nid.getText();
											Name = nn.getText();
											gen= ng.getSelectedItem().toString();
											mno=nm.getText();
											Date d1 = datenewRecord.getDate();
											SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/dd");
											Dt = formatter.format(d1);
											ad=nad.getText();
											job=nd.getText();
											try {
											pst = c.prepareStatement("insert into details(ID,_NAME,Gender, Contact_no,_Date, Address, Title)values(?,?,?,?,?,?,?)");
											pst.setString(1, ID);
											pst.setString(2, Name);
											pst.setString(3, gen);
											pst.setString(4, mno);
											pst.setString(5, Dt);
											pst.setString(6, ad);
											pst.setString(7, job);
											pst.executeUpdate();
											JOptionPane.showMessageDialog(null, " Detail Saved. ");
											table_load();        
										    Clear();
											nid.requestFocus();
											}catch (SQLException e1) {e1.printStackTrace();}
									    }
							       }catch (SQLException e1) {e1.printStackTrace();}
							 }else{JOptionPane.showMessageDialog(null, " Invalied Mobile No ");}
						}else{JOptionPane.showMessageDialog(null, " Please, Enter correct name ");}
					}else{JOptionPane.showMessageDialog(null, " Please, Enter the correct Format ID");}
				}	
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		DFrame.getContentPane().add(btnNewButton);
		
		// This part is, to update exist details on database.
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(357, 240, 102, 23);
		btnUpdate.setBackground(new Color(245, 222, 179));
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnUpdate.setBackground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnUpdate.setBackground(new Color(245, 222, 179));
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Id=nid.getText();
				if(nid.getText().equals("")||nn.getText().equals("")||nm.getText().equals("")||nad.getText().equals("")||nd.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, " Please, first select record.");
				}
				else
				{
					if(nn.getText().matches("[a-zA-Z.]+"))
					{
					if(nm.getText().matches("^[0-9]*$") &&  nm.getText().length()==10 )
					{
						 try {
								pst = c.prepareStatement("select * from details where ID=?");
								pst.setString(1, Id);
								rs = pst.executeQuery();
								if(rs.next())
								{
				                 String ID,Name,gen,mno,Dt,ad,job;
				                 ID = nid.getText();
				                 Name = nn.getText();
				                 gen= ng.getSelectedItem().toString();
				                 mno=nm.getText();
				                 Date d1 = datenewRecord.getDate();
				                 SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/dd");
				                 Dt = formatter.format(d1);
				                 ad=nad.getText();
				                 job=nd.getText();
				                 try {
				                      pst = c.prepareStatement("update details set _NAME=?, Gender=?, Contact_no=? ,_Date=? , Address=? , Title=? where ID=?");
				                      pst.setString(1, Name);
				                      pst.setString(2, gen);
				                      pst.setString(3, mno);
				                      pst.setString(4, Dt);
				                      pst.setString(5, ad);
				                      pst.setString(6, job);
				                      pst.setString(7, ID);
				                      pst.executeUpdate();
				                      JOptionPane.showMessageDialog(null, " Detail updated. ");
				                      table_load();
				                      Clear();
				                      nid.requestFocus();
				                      pst = c.prepareStatement("update  bw set BNAME=? where ID=?");
				                      pst.setString(1, Name);
				                      pst.setString(2, ID);
				                      pst.executeUpdate();
				                      pst = c.prepareStatement("update lw set LNAME=? where ID=?");
				                      pst.setString(1, Name);
				                      pst.setString(2, ID);
				                      pst.executeUpdate();
				                      pst = c.prepareStatement("update dw set DNAME=? where ID=?");
				                      pst.setString(1, Name);
				                      pst.setString(2, ID);
				                      pst.executeUpdate();
				                    }catch (SQLException e1) {e1.printStackTrace();}
								}else {JOptionPane.showMessageDialog(null, Id+" is not Here.");}
					       }catch (SQLException e1) {e1.printStackTrace();}
                      }else{JOptionPane.showMessageDialog(null, " Invalied Mobile No ");}
				}else {JOptionPane.showMessageDialog(null, " Please, Enter correct name ");}
			}
		}
	});
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 18));
		DFrame.getContentPane().add(btnUpdate);
		
		// This part is, to search details of an employees.
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(719, 65, 355, 57);
		panel_4.setBackground(new Color(205, 133, 63));
		panel_4.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		DFrame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ID\r\n");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 19));
		lblNewLabel_1.setBounds(10, 12, 34, 28);
		panel_4.add(lblNewLabel_1);
		
		sid = new JTextField();
		sid.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sid.setColumns(10);
		sid.setBounds(54, 17, 113, 20);
		panel_4.add(sid);
		
		JButton btnView = new JButton("Search");
		btnView.setBounds(203, 17, 89, 23);
		panel_4.add(btnView);
		btnView.setBackground(new Color(245, 222, 179));
		btnView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnView.setBackground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnView.setBackground(new Color(245, 222, 179));
			}
		});
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Id=sid.getText();
				if(sid.getText().equals("")){JOptionPane.showMessageDialog(null, " Please, fill the information");}
				else
				{
					 try {
							pst = c.prepareStatement("select * from details where ID=?");
							pst.setString(1, Id);
							rs = pst.executeQuery();
							if(rs.next())
							{
				             try{
						        String Sid=sid.getText();
			                    pst = c.prepareStatement("select * from details where ID=?");
			                    pst.setString(1, Sid);
			                    rs = pst.executeQuery();
			                    table.setModel(DbUtils.resultSetToTableModel(rs));
			                   }catch (SQLException a){a.printStackTrace();}
							}else {JOptionPane.showMessageDialog(null, Id+ " is Not Here. ");}
					     }catch (SQLException e1) {e1.printStackTrace();}
			    }
			}
		});
		btnView.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		// This part is, to count how employees are working in company. we can get male, female counting also.
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(205, 133, 63));
		panel_5.setBounds(719, 133, 355, 86);
		panel_5.setBorder(new TitledBorder(null, "Count", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		DFrame.getContentPane().add(panel_5);
		panel_5.setLayout(null);
		
		JComboBox cc = new JComboBox();
		cc.setModel(new DefaultComboBoxModel(new String[] {"BW", "LW", "DW", "ALL"}));
		cc.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		cc.setBounds(43, 26, 108, 24);
		panel_5.add(cc);
		
		JButton btnCount = new JButton("Count");
		btnCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ca=cc.getSelectedItem().toString();
				int nm=0,nf=0;
				String gen;
				
				if(ca.equals("BW"))
				{
					try {
					pst = c.prepareStatement("select * from details where ID like 'BW%' order by Gender ");
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e1) {e1.printStackTrace();}
				try {
					pst = c.prepareStatement("select * from details where ID like 'BW%' order by Gender ");
					rs = pst.executeQuery();
					while(rs.next())
					{  
						gen = rs.getString(4);
						if(gen.equalsIgnoreCase("Male")) {nm+=1;	}
						else {nf+=1;}
					} 
					gfl.setText(nm+" Males,    " + nf + " Females.");
				} catch (SQLException e1) {e1.printStackTrace();}
				}
				else if (ca.equals("LW"))
				{
					try {
						pst = c.prepareStatement("select * from details where ID like 'LW%' order by Gender ");
						rs = pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
					} catch (SQLException e1) {e1.printStackTrace();}
					try {
						pst = c.prepareStatement("select * from details where ID like 'LW%' order by Gender ");
						rs = pst.executeQuery();
						while(rs.next())
						{  
							gen = rs.getString(4);
							if(gen.equalsIgnoreCase("Male")) {nm+=1;	}
							else {nf+=1;}
						}
						gfl.setText(nm+" Males,   " + nf + " Females.");
					} catch (SQLException e1) {e1.printStackTrace();
				}
			}
				else if (ca.equals("DW"))
				{
					try {
						pst = c.prepareStatement("select * from details where ID like 'DW%' order by Gender ");
						rs = pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
						
					} catch (SQLException e1) {e1.printStackTrace();}
					try {
						pst = c.prepareStatement("select * from details where ID like 'DW%' order by Gender ");
						rs = pst.executeQuery();
						while(rs.next())
						{  
							gen = rs.getString(4);
							if(gen.equalsIgnoreCase("Male")) {nm+=1;	}
							else {nf+=1;}
						}
						gfl.setText(nm+" Males,   " + nf + " Females.");
					} catch (SQLException e1) {e1.printStackTrace();}
				}
				else 
				{
					try {
						pst = c.prepareStatement("select * from details order by Gender ");
						rs = pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
					} catch (SQLException e1) {e1.printStackTrace();}
					try {
						pst = c.prepareStatement("select * from details order by Gender");
						rs = pst.executeQuery();
						while(rs.next())
						{  
							gen = rs.getString(4);
							if(gen.equalsIgnoreCase("Male")) {nm+=1;	}
							else {nf+=1;}
						}
						gfl.setText(nm+" Males,    " + nf + " Females.");
					} catch (SQLException e1) {e1.printStackTrace();}
				}
			}
		});
		btnCount.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnCount.setBackground(new Color(245, 222, 179));
		btnCount.setBounds(189, 26, 89, 23);
		panel_5.add(btnCount);
		gfl.setBounds(25, 58, 287, 17);
		panel_5.add(gfl);
		gfl.setHorizontalAlignment(SwingConstants.CENTER);
		gfl.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clear();
				table_load();
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnClear.setBackground(new Color(245, 222, 179));
		btnClear.setBounds(985, 467, 89, 23);
		DFrame.getContentPane().add(btnClear);
	}
	
	// This part is, to connect database with details module.
	
	public void connect()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c=DriverManager.getConnection("jdbc:mysql://localhost:3306/kds", "root", "HWsp@524");
		}catch (ClassNotFoundException ex) {System.out.println(ex);}
		catch (SQLException ex) {System.out.println(ex);}
	}
	
	// When we perform each operation the databse should be reloaded on application.
	 public void table_load()
	 {
	     try{
	         pst = c.prepareStatement("select * from details");
	         rs = pst.executeQuery();
	         table.setModel(DbUtils.resultSetToTableModel(rs));
	     }catch (SQLException e) { e.printStackTrace(); }
	}
	 
	 public void Clear()
	 {
		 nid.setText("");
			nn.setText("");
			ng.setSelectedIndex(0);
			nm.setText("");
			nad.setText("");
			nd.setText("");
			DelDatecheck.setText("");
			sid.setText("");
			cc.setSelectedIndex(0);
			gfl.setText("");
			nl.setText("");
			idl.setText("");
			gl.setText("");
			ml.setText("");
			aml.setText("");
			al.setText("");
			dl.setText("");
			datenewRecord.setDate(date);
	 }
}