/* 
 * In this module mostly used to maintain all data of the bricker worker of work. 
 * Mostly, Record new work details,
 * Can update update exist record into among different records,
 * Can be deleted a record,
 * Can be searched any particular details of works in day/month/year wise,
 * 
 * Important,
 * In this module cann't be saved other category of worker details and also customer details.
 * Can be maintained Brick work details. 
 */

package KDS;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

public class BW_1 { // Main class name

	private JFrame BWFrame;
	private JTextField id;
	private JTextField nl;
	private JTextField tb;
	private JTextField sba;
	private JTextField ta;
	private JTextField sid;
	Date date = new Date();
	Connection c;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table_1;
	private JTextField nb;
	private JTextField name;
	private JLabel dsid = new JLabel("");
	private JLabel dsn = new JLabel("");
	private JLabel dsnl = new JLabel("");
	private JLabel dsd = new JLabel("");
	private JLabel dsnb = new JLabel("");
	private JLabel dssbp = new JLabel("");
	private JLabel dstb = new JLabel("");
	private JLabel dsta = new JLabel("");
	JLabel DelDatecheck;
	JDateChooser datenewRecord, SdateS, SdateE; 
	
	public void brickworker() { // Main method name
   //public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BW_1 window = new BW_1();
					window.BWFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BW_1() { // Constructor of main class
		initialize();
		connect();
		table_load();
	}

	private void initialize() { // Initiate of main Frame
		BWFrame = new JFrame();
		BWFrame.setTitle("                 ");
		BWFrame.getContentPane().setBackground(new Color(210, 105, 30));
		BWFrame.setBackground(Color.WHITE);
		BWFrame.setBounds(100, 100, 1100, 550);
		BWFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BWFrame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel(); // Name borad of Brick workers
		panel.setBackground(new Color(178, 34, 34));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 1064, 43);
		BWFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Brick Workers");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 38));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(234, 0, 270, 38);
		panel.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("HOME"); // Home button with activation process
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPageClone m = new MainPageClone();
				m.mainPageclone();
				BWFrame.dispose();
			}
		});
		btnNewButton_1.setBackground(new Color(255, 127, 80));
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_1.setForeground(new Color(255, 0, 0));
		btnNewButton_1.setBounds(965, 0, 99, 43);
		panel.add(btnNewButton_1);
		
		// This part of process is, to record new details, update the exist record and delete the a record.
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(205, 133, 63));
		panel_1.setBorder(new TitledBorder(null, "New Record", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 65, 392, 221);
		BWFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(21, 32, 46, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Name");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(21, 63, 46, 14);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Date");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(21, 98, 46, 14);
		panel_1.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("No.of Lines");
		lblNewLabel_1_1_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_2.setBounds(21, 135, 96, 14);
		panel_1.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Price of Brick ");
		lblNewLabel_1_1_2_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_2_1.setBounds(228, 135, 96, 14);
		panel_1.add(lblNewLabel_1_1_2_1);
		
		JLabel lblNewLabel_1_1_2_2 = new JLabel("Total Bricks ");
		lblNewLabel_1_1_2_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_2_2.setBounds(21, 179, 96, 14);
		panel_1.add(lblNewLabel_1_1_2_2);
		
		JLabel lblNewLabel_1_1_2_3 = new JLabel("Total Amount");
		lblNewLabel_1_1_2_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_2_3.setBounds(228, 179, 96, 14);
		panel_1.add(lblNewLabel_1_1_2_3);
		
		id = new JTextField();
		id.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) { // This process is when we enter the ID of the employees the name of employee appear on name textfield 
			          String Id=id.getText();
			          if(Id.startsWith("BW") && id.getText().length()==4)
			          {
			          try {
						pst = c.prepareStatement("select * from details where ID=?");
						pst.setString(1, Id);
						rs = pst.executeQuery();
						if(rs.next())
						{
							String n=rs.getString(3); 
							name.setText(n);
						}
					      } catch (SQLException e1) { e1.printStackTrace(); }
			          }
			          else{ name.setText(""); }
			}
		});
		id.setColumns(10);
		id.setBounds(115, 30, 103, 20);
		panel_1.add(id);
		
		nl = new JTextField();
		nl.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		nl.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(nl.getText().equals(""))
				{
					nb.setText("");
					sba.setText("");
				}
			}
		});
		nl.setColumns(10);
		nl.setBounds(115, 133, 46, 20);
		panel_1.add(nl);
		
		tb = new JTextField();
		tb.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tb.setColumns(10);
		tb.setBounds(115, 177, 103, 20);
		panel_1.add(tb);
		
		sba = new JTextField();
		sba.setText("1");
		sba.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sba.addKeyListener(new KeyAdapter() { // This is process of calculate the number of bricks
			@Override
			public void keyReleased(KeyEvent e) {
				
				String TB=tb.getText();
				String SBA=sba.getText();
				
				int a1=Integer.parseInt(TB);
				int a2=Integer.parseInt(SBA);
				
				int c=a1*a2;
				
				String c1=Integer.toString(c);
				
				ta.setText(c1);
			}
		});
		sba.setColumns(10);
		sba.setBounds(321, 133, 53, 20);
		panel_1.add(sba);
		
		ta = new JTextField();
		ta.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		ta.setColumns(10);
		ta.setBounds(321, 177, 53, 20);
		panel_1.add(ta);
		
		nb = new JTextField();
		nb.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		nb.addKeyListener(new KeyAdapter() { // Process is, to calculate the total amount for total bricks
			@Override
			public void keyReleased(KeyEvent e) {
				if(nb.getText().equals(""))
				{
					sba.setText("");
				}
				String l=nl.getText();
				String b=nb.getText();
				
				int a1=Integer.parseInt(l);
				int a2=Integer.parseInt(b);
				
				int c=a1*a2;
				
				String c1=Integer.toString(c);
				
				tb.setText(c1);
				
				String TB=tb.getText();
				String SBA=sba.getText();
				
				int a_1=Integer.parseInt(TB);
				int a_2=Integer.parseInt(SBA);
				
				int _c=a1*a2;
				
				String c_1=Integer.toString(c);
				
				ta.setText(c_1);
			}
		});
		nb.setColumns(10);
		nb.setBounds(172, 133, 46, 20);
		panel_1.add(nb);
		
		name = new JTextField();
		name.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		name.setColumns(10);
		name.setBounds(115, 61, 103, 20);
		panel_1.add(name);
		
		JDateChooser datenewRecord = new JDateChooser(date);
		datenewRecord.getCalendarButton().setBackground(new Color(218, 165, 32));
		datenewRecord.setBounds(115, 98, 103, 20);
		panel_1.add(datenewRecord);
		
		JLabel DelDatecheck = new JLabel("");
		DelDatecheck.setFont(new Font("Times New Roman", Font.BOLD, 14));
		DelDatecheck.setBounds(229, 99, 111, 20);
		panel_1.add(DelDatecheck);
		
		// This part is, To display all records of employees from details on table
				
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(205, 133, 63));
		panel_2.setBorder(new TitledBorder(null, "Records", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(412, 65, 662, 300);
		BWFrame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 642, 267);
		panel_2.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		table_1.setBackground(new Color(255, 255, 255));
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=table_1.getSelectedRow();
				String ID=table_1.getModel().getValueAt(i, 1).toString();
				String na=table_1.getModel().getValueAt(i, 2).toString();
				String dt=table_1.getModel().getValueAt(i, 3).toString();
				String NL=table_1.getModel().getValueAt(i, 4).toString();
				String NB=table_1.getModel().getValueAt(i, 5).toString();
				String SBA=table_1.getModel().getValueAt(i,6).toString();
				String TB=table_1.getModel().getValueAt(i, 7).toString();
				String TA=table_1.getModel().getValueAt(i, 8).toString();
				id.setText(ID);
				name.setText(na);
				DelDatecheck.setText(dt);
			    tb.setText(TB);
				ta.setText(TA);
				nl.setText(NL);
				nb.setText(NB);
				sba.setText(SBA);
				dsid.setText(ID);
				dsn.setText(na);
				dsd.setText(dt);
				dsnl.setText(NL);
				dsnb.setText(NB);
				dssbp.setText(SBA);
				dstb.setText(TB);
				dsta.setText(TA);
			}
		});
		scrollPane.setViewportView(table_1);
		
		// This part is to search the details of the employees based on Day/month/ Year wise.
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(205, 133, 63));
		panel_3.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 377, 392, 86);
		BWFrame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("ID");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 26, 46, 14);
		panel_3.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Date");
		lblNewLabel_1_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_1_1.setBounds(179, 26, 46, 14);
		panel_3.add(lblNewLabel_1_1_1_1);
		
		sid = new JTextField();
		sid.setText("BW");
		sid.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sid.setColumns(10);
		sid.setBounds(39, 24, 113, 20);
		panel_3.add(sid);
		
		JDateChooser SdateS = new JDateChooser(date);
		SdateS.getCalendarButton().setBackground(new Color(218, 165, 32));
		SdateS.setBounds(222, 24, 113, 20);
		panel_3.add(SdateS);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Date");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_1_1_1.setBounds(179, 61, 46, 14);
		panel_3.add(lblNewLabel_1_1_1_1_1);
		
		JDateChooser SdateE = new JDateChooser(date);
		SdateE.getCalendarButton().setBackground(new Color(218, 165, 32));
		SdateE.setBounds(222, 55, 113, 20);
		panel_3.add(SdateE);
		
		// This process is update a record into database
		
		JButton btnNewButton_3 = new JButton("Update");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_3.setBackground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_3.setBackground(new Color(245, 222, 179));
			}
		});
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String Id=id.getText();
			String Na=name.getText();
				if(id.getText().equals("")||name.getText().equals("")||nl.getText().equals("")||nb.getText().equals("")||sba.getText().equals("")||ta.getText().equals("")||tb.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, " Please, first select a record");
				}
				else
				{
					if(Id.startsWith("BW") && Id.length()==4)
					{
						 try {
								pst = c.prepareStatement("select * from details where ID=? and _Name=?");
								pst.setString(1, Id);
								pst.setString(2, Na);
								rs = pst.executeQuery();
								if(rs.next())
								{
				                     String ID,Name,Dt,Tb,Ta,Nl,Nb,Sba;
				                     ID = id.getText();
				                     Name = name.getText();
				                     Date d1 = datenewRecord.getDate();
				                     SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/dd");
				                     Dt = formatter.format(d1);
				                     Tb=tb.getText();
				                     Ta=ta.getText();
				                     Nl=nl.getText();
				                     Nb=nb.getText();
				                     Sba=sba.getText();
				                     try {
				                          pst = c.prepareStatement("update bw set NofL=? , NofB=? , SBP=?, Total_Bricks=? , Total_amount=?, _DATE = ? where ID=? and BNAME=?");
				                          pst.setString(1, Nl);
				                          pst.setString(2, Nb);
				                          pst.setString(3, Sba);
				                          pst.setString(4, Tb);
				                          pst.setString(5, Ta);
				                          pst.setString(6, Dt);
				                          pst.setString(7, ID);
				                          pst.setString(8, Name);
				                          pst.executeUpdate();
				                          JOptionPane.showMessageDialog(null, " Detail Updated.");
				                          table_load();
				                          Clear();
				                          id.requestFocus();
				                         }
				                         catch (SQLException e1) { e1.printStackTrace();}
								}
				                else { JOptionPane.showMessageDialog(null, Id+ " is Not Here. ");}
		                  } catch (SQLException e1) { e1.printStackTrace();}
	              } else { JOptionPane.showMessageDialog(null, " Please, Enter the correct ID");}
			  }
			}
		});
		btnNewButton_3.setBackground(new Color(245, 222, 179));
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_3.setBounds(161, 332, 97, 23);
		BWFrame.getContentPane().add(btnNewButton_3);
		
		// this process is, To delete a record from database.
		
		JButton btnNewButton_4 = new JButton("Delete");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_4.setBackground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_4.setBackground(new Color(245, 222, 179));
			}
		});
		
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id.getText().equals("")||name.getText().equals("")||nl.getText().equals("")||nb.getText().equals("")||sba.getText().equals("")||ta.getText().equals("")||tb.getText().equals("")||DelDatecheck.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, " Please, first select a record");
				}
				else
				{
				String ID,Name,Dt,Tb,Ta,Nl,Nb,Sba;
				ID = id.getText();
				Name = name.getText();
				Dt = DelDatecheck.getText();
				Tb=tb.getText();
				Ta=ta.getText();
				Nl=nl.getText();
				Nb=nb.getText();
				Sba=sba.getText();
				try {
				pst = c.prepareStatement("delete from bw where ID=? and BNAME=? and _DATE=? and NofL=? and NofB=? and SBP=? and Total_Bricks=? and Total_amount=?");
				pst.setString(1, ID);
				pst.setString(2, Name);
				pst.setString(3, Dt);
				pst.setString(4, Nl);
				pst.setString(5, Nb);
				pst.setString(6, Sba);
				pst.setString(7, Tb);
				pst.setString(8, Ta);
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, " Detail deleted.");
				table_load();        
			    Clear();
				id.requestFocus();
				}catch (SQLException e1) {e1.printStackTrace();}
			  } 
			}
		});
		btnNewButton_4.setBackground(new Color(245, 222, 179));
		btnNewButton_4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_4.setBounds(292, 332, 89, 23);
		BWFrame.getContentPane().add(btnNewButton_4);
		
		// this process is, To a new details into the database.
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBounds(33, 332, 89, 23);
		BWFrame.getContentPane().add(btnNewButton);
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
				String Id=id.getText();
				String Na=name.getText();
				if(id.getText().equals("")||name.getText().equals("")||nl.getText().equals("")||nb.getText().equals("")||sba.getText().equals("")||ta.getText().equals("")||tb.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, " Please fill complete Information");
				}
				else
				{
					if(Id.startsWith("BW") && Id.length()==4)
					{
						 try {
								pst = c.prepareStatement("select * from details where ID=? and _Name=?");
								pst.setString(1, Id);
								pst.setString(2, Na);
								rs = pst.executeQuery();
								if(rs.next())
								{
									String ID,Name,Dt,Tb,Ta,Nl,Nb,Sba;
									ID = id.getText();
									Name = name.getText();
									Date d1 = datenewRecord.getDate();
									SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/dd");
									Dt = formatter.format(d1);
									Tb=tb.getText();
									Ta=ta.getText();
									Nl=nl.getText();
									Nb=nb.getText();
									Sba=sba.getText();
									try {
									pst = c.prepareStatement("insert into bw(ID,BNAME,_Date,NofL,NofB,SBP,Total_Bricks,Total_amount)values(?,?,?,?,?,?,?,?)");
									pst.setString(1, ID);
									pst.setString(2, Name);
									pst.setString(3, Dt);
									pst.setString(4, Nl);
									pst.setString(5, Nb);
									pst.setString(6, Sba);
									pst.setString(7, Tb);
									pst.setString(8, Ta);
									pst.executeUpdate();
									JOptionPane.showMessageDialog(null, " Detail Saved.");
									table_load();
									Clear();
									id.requestFocus();
									}catch (SQLException e1) { e1.printStackTrace();}
								} 
								else { JOptionPane.showMessageDialog(null, Id+ " is Not Here. ");}
						   }catch (SQLException e1) { e1.printStackTrace(); }
					}else { JOptionPane.showMessageDialog(null, " Please, Enter the correct ID");}
				}
			}
		});
		btnNewButton.setBackground(new Color(245, 222, 179));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		// This is process is, To search a detail from database.
		
		JButton btnNewButton_4_1 = new JButton("Search");
		btnNewButton_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Id=sid.getText();
				if(sid.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, " Please fill complete Information");
				}
				else
				{
					if(Id.startsWith("BW") && Id.length()==4) 
					{
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
									if(Dts.compareTo(Dte)<0 || Dts.compareTo(Dte)==0 )
									{
									try {
									pst = c.prepareStatement("Select * from bw where ID =? and _Date between ? and ? ");
									pst.setString(1, ID);
									pst.setString(2, Dts);
									pst.setString(3, Dte);
									rs=pst.executeQuery();
									table_1.setModel(DbUtils.resultSetToTableModel(rs));
									sid.setText("BW");
									SdateS.setDate(date);
									SdateE.setDate(date);
									sid.requestFocus();
									}catch (SQLException e1) { e1.printStackTrace();}
									}else {JOptionPane.showMessageDialog(null, Dts+ "Starting date higher than of Ending date. "+Dte);}
								} else {JOptionPane.showMessageDialog(null, Id+ " is Not Here. ");}
						 }catch (SQLException e1) { e1.printStackTrace();}
					}else {JOptionPane.showMessageDialog(null, " Please, Enter the correct ID");}
				}
			}
		});
		btnNewButton_4_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_4_1.setBackground(new Color(245, 222, 179));
		btnNewButton_4_1.setBounds(161, 474, 89, 23);
		BWFrame.getContentPane().add(btnNewButton_4_1);
		
		// This part is, To display the deatils on screen.
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBackground(new Color(205, 133, 63));
		panel_4.setBounds(412, 376, 662, 86);
		BWFrame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JLabel didl = new JLabel("ID :");
		didl.setFont(new Font("Times New Roman", Font.BOLD, 14));
		didl.setBounds(22, 23, 39, 14);
		panel_4.add(didl);
		
		JLabel dnl = new JLabel("Name :");
		dnl.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dnl.setBounds(22, 57, 51, 14);
		panel_4.add(dnl);
		
		JLabel ddl = new JLabel("Date :");
		ddl.setFont(new Font("Times New Roman", Font.BOLD, 14));
		ddl.setBounds(498, 23, 51, 14);
		panel_4.add(ddl);
		
		JLabel dnll = new JLabel("NofL :");
		dnll.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dnll.setBounds(176, 23, 54, 14);
		panel_4.add(dnll);
		
		JLabel dnbll = new JLabel("NofBSL :");
		dnbll.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dnbll.setBounds(176, 57, 72, 14);
		panel_4.add(dnbll);
		
		JLabel dsbpl = new JLabel("SBP :");
		dsbpl.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsbpl.setBounds(498, 57, 64, 14);
		panel_4.add(dsbpl);
		
		JLabel dtbl = new JLabel("Total Bricks :");
		dtbl.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dtbl.setBounds(331, 23, 96, 14);
		panel_4.add(dtbl);
		
		JLabel dtal = new JLabel("Total Amount :");
		dtal.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dtal.setBounds(331, 57, 96, 14);
		panel_4.add(dtal);
		
		dsid.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsid.setBounds(59, 24, 78, 14);
		panel_4.add(dsid);
		
		dsn.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsn.setBounds(69, 57, 89, 14);
		panel_4.add(dsn);
		
		dsd.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsd.setBounds(556, 23, 96, 14);
		panel_4.add(dsd);
		
		dsnl.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsnl.setBounds(225, 23, 72, 14);
		panel_4.add(dsnl);
		
		dsnb.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsnb.setBounds(255, 57, 51, 14);
		panel_4.add(dsnb);
		
		dssbp.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dssbp.setBounds(554, 57, 72, 14);
		panel_4.add(dssbp);
		
		dstb.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dstb.setBounds(434, 23, 54, 14);
		panel_4.add(dstb);
		
		dsta.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsta.setBounds(437, 57, 70, 14);
		panel_4.add(dsta);
		
		JButton btnNewButton_4_1_1 = new JButton("Clear");
		btnNewButton_4_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    Clear();
				table_load();
			}
		});
		btnNewButton_4_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_4_1_1.setBackground(new Color(245, 222, 179));
		btnNewButton_4_1_1.setBounds(963, 477, 89, 23);
		BWFrame.getContentPane().add(btnNewButton_4_1_1);
	}
	
     // This part is, To connect the database with this module.

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
	
	//this is part, To reload the database once done every process.
	
	 public void table_load()
	 {
	     try{
	         pst = c.prepareStatement("select * from bw");
	         rs = pst.executeQuery();
	         table_1.setModel(DbUtils.resultSetToTableModel(rs));
	     }
	     catch (SQLException e)
	     {
	     e.printStackTrace();
	     }
	}
	 
	 // This part is, to clear the every components once we will be done each process.
	 
	 public void Clear()
	 {
			id.setText("");
			name.setText("");
			nl.setText("");
			nb.setText("");
			sba.setText("1");
			tb.setText("");
			ta.setText("");
			sid.setText("BW");
			dsid.setText("");
			dsn.setText("");
			dsd.setText("");
			dsnl.setText("");
			dsnb.setText("");
			dssbp.setText("");
			dstb.setText("");
			dsta.setText("");
			DelDatecheck.setText("");
			datenewRecord.setDate(date);
			SdateS.setDate(date);
			SdateE.setDate(date);
	 }
}