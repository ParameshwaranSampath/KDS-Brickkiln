/* 
 * In this module mostly used to maintain all data of the Load worker of work. 
 * Mostly, Record new work details,
 * Can update update exist record into among different records,
 * Can be deleted a record,
 * Can be searched any particular details of works in day/month/year wise,
 * 
 * Important,
 * In this module cann't be saved other category of worker details and also customer details.
 * Can be maintained load work details. 
 */

package KDS;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.util.Date;
import java.util.logging.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

public class LW_1{ // main_class

	private JFrame LWFrame;
	private JTextField ia;
	private JTextField np;
	private JTextField nl1;
	private JTextField nl2;
	private JTextField uid;
	private JTextField un;
	private JTextField uta;
	private JTextField sid;
	private JTable table;
	Connection c=null;
	PreparedStatement pst;
	ResultSet rs;
	JDateChooser Udate, datenewRecord,SdateS,SdateE;
	Date date =new Date();
	private JTextField na;
	private JComboBox nwi = new JComboBox();
	private JTextField tamt;
	private JTextField ua;
	private JLabel dsid = new JLabel("");
	private JLabel dsn = new JLabel("");
	private JLabel dsta = new JLabel("");
	private JLabel dsd = new JLabel("");
	private JLabel dsa = new JLabel("");
	private JLabel DelDatecheck = new JLabel("");
	private JTextField TNOFL;
	
	public void loadworker() { // main method 
	//public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LW_1 window = new LW_1();
					window.LWFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LW_1() { // main class constructor name
		initialize();
		connect();
		table_load();
		updatecombo();
	}

	private void initialize() { // Initiate the Main frame
		
		LWFrame = new JFrame();
		LWFrame.getContentPane().setBackground(new Color(210, 105, 30));
		LWFrame.setBounds(100, 100, 1100, 550);
		LWFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LWFrame.getContentPane().setLayout(null);
		LWFrame.setResizable(false);
		
		JPanel panel = new JPanel(); // Name board of brick worker
		panel.setBackground(new Color(178, 34, 34));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 1064, 48);
		LWFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Load Workers");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 38));
		lblNewLabel.setBounds(374, 0, 249, 48);
		panel.add(lblNewLabel);
		
		JButton btnNewButton_1_1 = new JButton("HOME"); // Home button with activation
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPageClone m = new MainPageClone();
				m.mainPageclone();
				LWFrame.dispose();
			}
		});
		btnNewButton_1_1.setForeground(Color.RED);
		btnNewButton_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_1_1.setBackground(new Color(255, 127, 80));
		btnNewButton_1_1.setBounds(965, 0, 99, 48);
		panel.add(btnNewButton_1_1);
		
		// This part is insert the new records into database
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(205, 133, 63));
		panel_1.setBorder(new TitledBorder(null, "New Record", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 70, 535, 195);
		LWFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Date");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(34, 25, 46, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("No.of. Loads");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(34, 50, 90, 14);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("No.of.workers");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(34, 75, 90, 14);
		panel_1.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Amount");
		lblNewLabel_1_1_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_2.setBounds(34, 100, 68, 14);
		panel_1.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Workers");
		lblNewLabel_1_1_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_3.setBounds(34, 125, 90, 14);
		panel_1.add(lblNewLabel_1_1_3);
		
		ia = new JTextField();
		ia.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		ia.setBounds(134, 98, 118, 20);
		panel_1.add(ia);
		ia.setColumns(10);
		
		np = new JTextField(); // this part is, to calculate r person earn amount 
		np.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		np.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int a=nwi.getItemCount();
				String Np=np.getText();
				int b=Integer.parseInt(Np);
				if(b<=a)
				{
					String Nw=np.getText();
					String Tamt=tamt.getText();
					int n1=Integer.parseInt(Nw);
					int n2=Integer.parseInt(Tamt);
					double ta=n2/n1;
					String Ta=Double.toString(ta);
					ia.setText(Ta);	
				}
				else {JOptionPane.showMessageDialog(null," Please, Enter the correct total person");}
			}
		});
		np.setColumns(10);
		np.setBounds(134, 73, 118, 20);
		panel_1.add(np);
		
		nl1 = new JTextField();
		nl1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		nl1.setColumns(10);
		nl1.setBounds(134, 48, 40, 20);
		panel_1.add(nl1);
		
		nl2 = new JTextField(); // This part is, to calculate total amount of load value
		nl2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		nl2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String Nl1=nl1.getText();
				String Nl2=nl2.getText();
				int n1=Integer.parseInt(Nl1);
				int n2=Integer.parseInt(Nl2);
				int a=n1*550;
				int b=n2*275;
				int c1=a+b;
				String Ta=Integer.toString(c1);
				tamt.setText(Ta);
				
				int  tl=n1+n2;
				String T_L = Integer.toString(tl);
				TNOFL.setText(T_L);
			}
		});
		nl2.setColumns(10);
		nl2.setBounds(227, 48, 40, 20);
		panel_1.add(nl2);
		
		JLabel lblNewLabel_1_2 = new JLabel("550");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(184, 50, 33, 14);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("275");
		lblNewLabel_1_2_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_2_1.setBounds(273, 50, 33, 14);
		panel_1.add(lblNewLabel_1_2_1);
		nwi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		nwi.setBounds(134, 122, 118, 22);
				panel_1.add(nwi);
		
		JLabel lblNewLabel_1_1_3_1 = new JLabel("Name");
		lblNewLabel_1_1_3_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_3_1.setBounds(34, 156, 90, 14);
		panel_1.add(lblNewLabel_1_1_3_1);
		
		na = new JTextField();
		na.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		na.setColumns(10);
		na.setBounds(134, 155, 118, 20);
		panel_1.add(na);
		
		JLabel lblNewLabel_1_1_4 = new JLabel("Total Amount");
		lblNewLabel_1_1_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_4.setBounds(370, 51, 90, 14);
		panel_1.add(lblNewLabel_1_1_4);
		
		tamt = new JTextField();
		tamt.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tamt.setColumns(10);
		tamt.setBounds(470, 47, 55, 20);
		panel_1.add(tamt);
		
		JDateChooser datenewRecord = new JDateChooser(date);
		datenewRecord.setDateFormatString("dd-MM-y");
		datenewRecord.setBackground(new Color(218, 165, 32));
		datenewRecord.setBounds(134, 25, 118, 20);
		panel_1.add(datenewRecord);
		
		TNOFL = new JTextField();
		TNOFL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		TNOFL.setColumns(10);
		TNOFL.setBounds(305, 48, 55, 20);
		panel_1.add(TNOFL);
		
		// This process to display the details of all employees into table format
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(555, 171, 523, 129);
		LWFrame.getContentPane().add(panel_3);
		panel_3.setBackground(new Color(205, 133, 63));
		panel_3.setBorder(new TitledBorder(null, "Update", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setLayout(null);
		
		JLabel DelDatecheck = new JLabel("");
		DelDatecheck.setFont(new Font("Times New Roman", Font.BOLD, 14));
		DelDatecheck.setBounds(84, 84, 118, 17);
		panel_3.add(DelDatecheck);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(205, 133, 63));
		panel_2.setBorder(new TitledBorder(null, "Records", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 310, 535, 173);
		LWFrame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 515, 140);
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
				String TL=table.getModel().getValueAt(i, 4).toString();
				String TA=table.getModel().getValueAt(i, 5).toString();
				String IA=table.getModel().getValueAt(i, 6).toString();
				uid.setText(ID);
				un.setText(na);
				DelDatecheck.setText(dt);
				uta.setText(TA);
				ua.setText(IA);
				dsid.setText(ID);
				dsn.setText(na);
				dsd.setText(dt);
				dsta.setText(TA);
				dsa.setText(IA);
			}
		});
		scrollPane.setViewportView(table);
		
		// This part is, To save new records into database.
		
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
				String Id=nwi.getSelectedItem().toString();
				if(nl2.getText().equals("")||nl1.getText().equals("")||tamt.getText().equals("")||np.getText().equals("")||ia.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, " Please fill complete Information");
				}
				else
				{
					if(Id.startsWith("LW") && Id.length()==4)
					     {
							try {
									pst = c.prepareStatement("select * from details where ID=?");
									pst.setString(1, Id);
									rs = pst.executeQuery();
									if(rs.next())
									{
										String Na=rs.getString("_NAME");
										na.setText(Na);	
					                    String ID1,Name,Dt,Ia,Ta,Ntl;
					                    ID1 = nwi.getSelectedItem().toString();
					                    Name = na.getText();
					                    Date d1 = datenewRecord.getDate();
					                    SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/dd");
					                    Dt = formatter.format(d1);
					                    Ta=tamt.getText();
					                    Ia=ia.getText();
					                    Ntl=TNOFL.getText();
					                    try {
					                         pst = c.prepareStatement("insert into lw(ID,LNAME,_Date,NofLoad,Total_Amount,I_Amount)values(?,?,?,?,?,?)");
					                         pst.setString(1, ID1);
					                         pst.setString(2, Name);
					                         pst.setString(3, Dt);
					                         pst.setString(4, Ntl);
					                         pst.setString(5, Ta);
					                         pst.setString(6, Ia);
					                         pst.executeUpdate();
					                         JOptionPane.showMessageDialog(null, " Detail Saved.");
					                         table_load();
					                         nwi.removeItem(ID1);
					                         nwi.setSelectedIndex(0);
					                         na.setText("");
					                        }catch (SQLException e1) { e1.printStackTrace();}
									}else{JOptionPane.showMessageDialog(null, Id+ " is Not Here. ");}
					            }catch (SQLException e1) { e1.printStackTrace();}
							}else {JOptionPane.showMessageDialog(null, " Please, Enter the correct ID ");}					 
			     }
			}
		});
		btnNewButton.setBackground(new Color(245, 222, 179));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.setBounds(230, 276, 89, 23);
		LWFrame.getContentPane().add(btnNewButton);
		
		// This part is to update exist record details among different records into database.
		
		JButton btnNewButton_2_1 = new JButton("Update");
		btnNewButton_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_2_1.setBackground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_2_1.setBackground(new Color(245, 222, 179));
			}
		});
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Id=uid.getText();
			    String Na=un.getText();
				if(uid.getText().equals("")||un.getText().equals("")||uta.getText().equals("")||ua.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, " Please fill complete Information");
				}
				else
				{
					if(Id.startsWith("LW") && Id.length()==4) {
						 try {
								pst = c.prepareStatement("select * from details where ID=? and _NAME=?");
								pst.setString(1, Id);
								pst.setString(2, Na);
								rs = pst.executeQuery();
								if(rs.next())
								{
				                  String ID,Name,Dt,Hr,Ta,Ia,Ntl;
				                  ID = uid.getText();
				                  Name = un.getText();
				                  Dt = DelDatecheck.getText();
				                  Ta=uta.getText();
				                  Ia=ua.getText();
				                  Ntl=TNOFL.getText();
				                  try {
				                       pst = c.prepareStatement("update lw set I_Amount=? , Total_Amount=? where ID=? and LNAME=? and  _Date=?");
				                       pst.setString(1, Ia);
				                       pst.setString(2, Ta);
				                       pst.setString(3, ID);
				                       pst.setString(4, Name);
				                       pst.setString(5, Dt);
				                       pst.executeUpdate();
				                       JOptionPane.showMessageDialog(null, " Detail Updated. ");
				                       table_load();
				                       Clear();
				                       uid.requestFocus();
			                        }catch (SQLException e1) {e1.printStackTrace();}
								}else{JOptionPane.showMessageDialog(null, Id+ " is Not Here. ");}
				            } catch (SQLException e1) { e1.printStackTrace();}
					}else {JOptionPane.showMessageDialog(null, " Please, Enter the correct ID");}			
				}
			}
		});
		btnNewButton_2_1.setBackground(new Color(245, 222, 179));
		btnNewButton_2_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_2_1.setBounds(835, 311, 111, 23);
		LWFrame.getContentPane().add(btnNewButton_2_1);
		
		JButton btnNewButton_2_2 = new JButton("Delete");
		btnNewButton_2_2.setBackground(new Color(245, 222, 179));
		btnNewButton_2_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_2_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_2_2.setBackground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_2_2.setBackground(new Color(245, 222, 179));
			}
		});
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(uid.getText().equals("")||un.getText().equals("")||uta.getText().equals("")||ua.getText().equals("")||DelDatecheck.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, " Please, first select a record");
				}
				else
				{
				
				try {
					String ID,Name,Dt,Ta,Ua;
					ID = uid.getText();
					Name = un.getText();
					Dt = DelDatecheck.getText();
					Ta=uta.getText();
					Ua=ua.getText();
				    pst = c.prepareStatement("delete from lw where ID=? and LNAME=? and _Date=? and Total_Amount=? and I_Amount=? ");
				    pst.setString(1, ID);
				    pst.setString(2, Name);
			        pst.setString(3, Dt);
				    pst.setString(4, Ta);
				    pst.setString(5, Ua);
				    pst.executeUpdate();
				    JOptionPane.showMessageDialog(null, " Detail deleted.");
				    table_load();
		            Clear();
					uid.requestFocus();         
				}catch (SQLException e1){e1.printStackTrace();}
			  }
			}
		});
		btnNewButton_2_2.setBounds(685, 311, 89, 23);
		LWFrame.getContentPane().add(btnNewButton_2_2);
		
		JPanel panel_4 = new JPanel(); // This part is to search a particular details of employees based on Day/Month, Year wise.
		panel_4.setBackground(new Color(205, 133, 63));
		panel_4.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(551, 70, 523, 56);
		LWFrame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("ID");
		lblNewLabel_1_3_1.setBounds(29, 25, 72, 17);
		lblNewLabel_1_3_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		panel_4.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("Date");
		lblNewLabel_1_3_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_3_1_1.setBounds(226, 25, 72, 17);
		panel_4.add(lblNewLabel_1_3_1_1);
		
		sid = new JTextField();
		sid.setText("LW");
		sid.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sid.setColumns(10);
		sid.setBounds(80, 24, 118, 20);
		panel_4.add(sid);
		
		JDateChooser SdateS = new JDateChooser(date);
		SdateS.setDateFormatString("dd-MM-y");
		SdateS.setBackground(new Color(218, 165, 32));
		SdateS.setBounds(281, 22, 99, 20);
		panel_4.add(SdateS);
		
		JDateChooser SdateE = new JDateChooser(date);
		SdateE.setDateFormatString("dd-MM-y");
		SdateE.setBackground(new Color(218, 165, 32));
		SdateE.setBounds(401, 22, 99, 20);
		panel_4.add(SdateE);
		
        // This is process of search a details into database.
		
		JButton btnNewButton_2_1_1 = new JButton("Search");
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Id=sid.getText();
				if(sid.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, " Please fill complete Information");
				}
				else
				{
					if(Id.startsWith("LW") && Id.length()==4) {
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
									pst = c.prepareStatement("Select * from lw where ID =? and _Date between ? and ? ");
									pst.setString(1, ID);
									pst.setString(2, Dts);
									pst.setString(3, Dte);
									rs=pst.executeQuery();
									table.setModel(DbUtils.resultSetToTableModel(rs));   
									sid.setText("LW");
									Date date = new Date();
									SdateS.setDate(date);
									Date date1 = new Date();
									SdateE.setDate(date1);
									sid.requestFocus();
									}catch (SQLException e1){e1.printStackTrace();}
									}else {JOptionPane.showMessageDialog(null, Dts+ "Starting date higher than of Ending date. "+Dte);}
								} else {JOptionPane.showMessageDialog(null, Id+ " is Not Here. ");}
						    }catch (SQLException e1) {e1.printStackTrace();}
					}else {JOptionPane.showMessageDialog(null, " Please, Enter the correct ID");}
				}
			}
		});
		btnNewButton_2_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_2_1_1.setBackground(new Color(245, 222, 179));
		btnNewButton_2_1_1.setBounds(751, 137, 111, 23);
		LWFrame.getContentPane().add(btnNewButton_2_1_1);
		
	    // To display details on screen.
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBackground(new Color(205, 133, 63));
		panel_5.setBounds(555, 357, 523, 85);
		LWFrame.getContentPane().add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_1_1_4_1 = new JLabel("ID :");
		lblNewLabel_1_1_4_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_4_1.setBounds(10, 22, 28, 14);
		panel_5.add(lblNewLabel_1_1_4_1);
		
		JLabel lblNewLabel_1_1_4_2 = new JLabel("Name :");
		lblNewLabel_1_1_4_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_4_2.setBounds(213, 22, 50, 14);
		panel_5.add(lblNewLabel_1_1_4_2);
		
		JLabel lblNewLabel_1_1_4_3 = new JLabel("Date :");
		lblNewLabel_1_1_4_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_4_3.setBounds(377, 22, 38, 14);
		panel_5.add(lblNewLabel_1_1_4_3);
		
		JLabel lblNewLabel_1_1_4_4 = new JLabel("Total Amount :");
		lblNewLabel_1_1_4_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_4_4.setBounds(10, 47, 96, 14);
		panel_5.add(lblNewLabel_1_1_4_4);
		
		JLabel lblNewLabel_1_1_4_5 = new JLabel("Amount :");
		lblNewLabel_1_1_4_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_4_5.setBounds(213, 47, 59, 14);
		panel_5.add(lblNewLabel_1_1_4_5);
		
		dsid.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsid.setBounds(37, 23, 43, 14);
		panel_5.add(dsid);
		
		dsn.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsn.setBounds(262, 22, 89, 14);
		panel_5.add(dsn);
		
		dsd.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsd.setBounds(425, 22, 88, 14);
		panel_5.add(dsd);
		
		dsta.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsta.setBounds(111, 47, 69, 14);
		panel_5.add(dsta);
		
		dsa.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsa.setBounds(282, 47, 69, 14);
		panel_5.add(dsa);
		
		// This part is to update and delete a records from database components.
		
		JLabel lblNewLabel_1_3 = new JLabel("ID");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(28, 31, 46, 14);
		panel_3.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Name");
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_4.setBounds(251, 31, 46, 14);
		panel_3.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Date");
		lblNewLabel_1_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_5.setBounds(28, 58, 46, 14);
		panel_3.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("Total Amount");
		lblNewLabel_1_6.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_6.setBounds(251, 58, 94, 14);
		panel_3.add(lblNewLabel_1_6);
		
		uid = new JTextField();
		uid.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		uid.setColumns(10);
		uid.setBounds(84, 29, 118, 20);
		panel_3.add(uid);
		
		un = new JTextField();
		un.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		un.setColumns(10);
		un.setBounds(349, 29, 118, 20);
		panel_3.add(un);
		
		uta = new JTextField();
		uta.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		uta.setColumns(10);
		uta.setBounds(349, 56, 118, 20);
		panel_3.add(uta);
		
		JLabel lblNewLabel_1_6_1 = new JLabel("Amount");
		lblNewLabel_1_6_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_6_1.setBounds(251, 87, 64, 14);
		panel_3.add(lblNewLabel_1_6_1);
		
		ua = new JTextField();
		ua.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		ua.setColumns(10);
		ua.setBounds(349, 84, 118, 20);
		panel_3.add(ua);
		
		 Udate = new JDateChooser();
		Udate.setDateFormatString("dd-MM-y");
		Udate.setBackground(new Color(218, 165, 32));
		Udate.setBounds(84, 56, 118, 20);
		panel_3.add(Udate);
		
		JButton btnNewButton_2_2_1 = new JButton("Clear");
		btnNewButton_2_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    Clear();
				table_load();
			}
		});
		btnNewButton_2_2_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_2_2_1.setBackground(new Color(245, 222, 179));
		btnNewButton_2_2_1.setBounds(985, 460, 89, 23);
		LWFrame.getContentPane().add(btnNewButton_2_2_1);
	}
	
	// To connect database with this module
	
	public void connect()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c=DriverManager.getConnection("jdbc:mysql://localhost:3306/kds", "root", "HWsp@524");
		}
		catch (ClassNotFoundException ex) {
			System.out.println(ex);
		}
		catch (SQLException ex) {
			System.out.println(ex);
		}
	}
	
	// to reload database overtime once done the operation.
	
	 public void table_load()
	 {
	     try{
	         pst = c.prepareStatement("select * from lw");
	         rs = pst.executeQuery();
	         table.setModel(DbUtils.resultSetToTableModel(rs));
	     }catch (SQLException e) { e.printStackTrace(); }
	}
	 
	 // This part is, To add number of employees into the combo box of new record insert process.
	 
	 public void updatecombo()
	 {
		 String Id;
		 try{
	         pst = c.prepareStatement("select * from details where ID like 'LW%'");
	         rs = pst.executeQuery();
	         while(rs.next())
	         {
					nwi.addItem(rs.getString("ID"));
	         }
	     }catch (SQLException a1){a1.printStackTrace(); }
	 }
	 
	 public void Clear()
	 {
			nl1.setText("");
			nl2.setText("");
			TNOFL.setText("");
			tamt.setText("");
			np.setText("");
			ia.setText("");
			nwi.setSelectedIndex(0);
			na.setText("");
			sid.setText("LW");
			uid.setText("");
			un.setText("");
			uta.setText("");
			ua.setText("");
			dsid.setText("");
			dsn.setText("");
			dsd.setText("");
			dsta.setText("");
			dsa.setText("");
			datenewRecord.setDate(date);
			SdateS.setDate(date);
			SdateE.setDate(date);
			DelDatecheck.setText("");
	 }
}
