/* 
 * In this module mostly used to maintain all data of the Customers. 
 * Mostly, Record new work details,
 * Can update update exist record into among different records,
 * Can be deleted a record,
 * Can be searched any particular details of sales in day/month/year wise,
 * 
 * Important,
 * In this module cann't be saved other category of worker details.
 * Can be maintained Customer details. 
 */

package KDS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;
import java.text.SimpleDateFormat;

public class Customer_1 { // main class

	private JFrame CUFrame;
	private JTextField cid;
	private JTextField cn;
	private JTextField cm;
	private JTextField ca;
	private JTextField tb;
	private JTextField sba;
	private JTextField ta;
	private JTextField sid;
	private JTable table;
	Date date = new Date(0);
	Connection c;
	PreparedStatement pst;
	ResultSet rs;
	private JLabel dsid = new JLabel("");
	private JLabel dsn = new JLabel("");
	private JLabel dsd = new JLabel("");
	private JLabel dsmn = new JLabel("");
	private JLabel dstb = new JLabel("");
	private JLabel dsta = new JLabel("");
	private JLabel dsa = new JLabel("");
	private JLabel dssbp = new JLabel("");
	private JTextField cgmail;
	private JLabel dsgm = new JLabel("");
	private JLabel DelDatecheck;
    JDateChooser datenewRecord, SdateS, SdateE;
	public void customer() { // Main method
	//public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer_1 window = new Customer_1();
					window.CUFrame.setVisible(true);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}
	
	public Customer_1() { // Constructor of main method
		initialize();
		connect();
		table_load();
	}
	
	private void initialize() { // Initiate main frame.
		CUFrame = new JFrame();
		CUFrame.setBounds(100, 100, 1100, 550);
		CUFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CUFrame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(210, 105, 30));
		CUFrame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel(); // This is for name board of customer module.
		panel_1.setBackground(new Color(178, 34, 34));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 11, 1064, 42);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Customer");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 38));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBackground(SystemColor.menu);
		lblNewLabel_1_1.setBounds(350, 1, 281, 42);
		panel_1.add(lblNewLabel_1_1);
		
		JButton btnNewButton_1 = new JButton("HOME"); // Home button with activation.
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPageClone m = new MainPageClone();
				m.mainPageclone();
				CUFrame.dispose();
			}
		});
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_1.setBackground(new Color(255, 127, 80));
		btnNewButton_1.setBounds(965, 0, 99, 43);
		panel_1.add(btnNewButton_1);
		
		// This part is, to enter a new record into database components.
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(205, 133, 63));
		panel_2.setBorder(new TitledBorder(null, "New Records", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 64, 351, 253);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblId.setBackground(new Color(240, 240, 240));
		lblId.setBounds(10, 30, 46, 14);
		panel_2.add(lblId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblName.setBackground(SystemColor.menu);
		lblName.setBounds(10, 55, 46, 14);
		panel_2.add(lblName);
		
		JLabel lblMobileNo = new JLabel("Mobile No");
		lblMobileNo.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMobileNo.setBackground(SystemColor.menu);
		lblMobileNo.setBounds(10, 80, 72, 14);
		panel_2.add(lblMobileNo);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblAddress.setBackground(SystemColor.menu);
		lblAddress.setBounds(10, 105, 60, 14);
		panel_2.add(lblAddress);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblDate.setBackground(SystemColor.menu);
		lblDate.setBounds(10, 167, 46, 14);
		panel_2.add(lblDate);
		
		JLabel lblTotalBricks = new JLabel("Total Bricks");
		lblTotalBricks.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTotalBricks.setBackground(SystemColor.menu);
		lblTotalBricks.setBounds(10, 192, 92, 14);
		panel_2.add(lblTotalBricks);
		
		JLabel lblPriceOfBrick = new JLabel("Price of Brick");
		lblPriceOfBrick.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPriceOfBrick.setBackground(SystemColor.menu);
		lblPriceOfBrick.setBounds(202, 192, 98, 14);
		panel_2.add(lblPriceOfBrick);
		
		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTotalAmount.setBackground(SystemColor.menu);
		lblTotalAmount.setBounds(10, 217, 92, 14);
		panel_2.add(lblTotalAmount);
		
		cid = new JTextField(); // This process is, we we enter the ID of customer. if it's there. then the rest of details automatically will be filled.
		cid.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		cid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String Id=cid.getText();
				if(Id.startsWith("CU") && Id.length()==4)
				{
			     try {
						pst = c.prepareStatement("select * from Customer where ID=?");
						pst.setString(1, Id);
						rs = pst.executeQuery();
						if(rs.next())
						{
							String n=rs.getString(3); 
							String mn=rs.getString(4); 
							String ad=rs.getString(5); 
							String gm=rs.getString(6);
							cn.setText(n);
							cm.setText(mn);
							ca.setText(ad);
							cgmail.setText(gm);
						}
						
					} catch (SQLException e1) {e1.printStackTrace();}
			}
				else
				{
					cn.setText("");
					cm.setText("");
					ca.setText("");
					cgmail.setText("");
				}
			}
		});
		cid.setBounds(114, 28, 114, 20);
		panel_2.add(cid);
		cid.setColumns(10);
		
		cn = new JTextField();
		cn.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		cn.setColumns(10);
		cn.setBounds(114, 53, 114, 20);
		panel_2.add(cn);
		
		cm = new JTextField(); // this process is, to find the exist customer details by phone number.
		cm.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String m=cm.getText();
				if(cm.getText().matches("^[0-9]*$") && cm.getText().length()==10)
				{
			     try {
						pst = c.prepareStatement("select * from Customer where Contact_no=?");
						pst.setString(1, m);
						rs = pst.executeQuery();
						if(rs.next())
						{
							String n=rs.getString(3); 
							String id=rs.getString(2); 
							String ad=rs.getString(5); 
							String gm=rs.getString(6);
							cn.setText(n);
							cid.setText(id);
							ca.setText(ad);
							cgmail.setText(gm);
						}
						
					} catch (SQLException e1) {e1.printStackTrace();}
			 }
			}
		});
		cm.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		cm.setColumns(10);
		cm.setBounds(114, 78, 114, 20);
		panel_2.add(cm);
		
		ca = new JTextField();
		ca.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		ca.setColumns(10);
		ca.setBounds(114, 103, 228, 20);
		panel_2.add(ca);
		
		tb = new JTextField(); // Calculate total amount for bricks.
		tb.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String Tb=tb.getText();
				String Sba=sba.getText();
				
				int a=Integer.parseInt(Tb);
				double b=Double.parseDouble(Sba);
				
				double c=a*b;
				
			    String Ta=Double.toString(c);
			    ta.setText(Ta);
			}
		});
		tb.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tb.setColumns(10);
		tb.setBounds(112, 189, 80, 20);
		panel_2.add(tb);
		
		sba = new JTextField(); // if we change the price of a brick. The calculation should be reworked.
		sba.setText("10");
		sba.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sba.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String Tb=tb.getText();
				String Sba=sba.getText();
				
				int a=Integer.parseInt(Tb);
				double b=Double.parseDouble(Sba);
				
				double c=a*b;
				
			    String Ta=Double.toString(c);
			    ta.setText(Ta);
			}
		});
		sba.setColumns(10);
		sba.setBounds(296, 189, 46, 20);
		panel_2.add(sba);
		
		ta = new JTextField();
		ta.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		ta.setColumns(10);
		ta.setBounds(112, 214, 80, 20);
		panel_2.add(ta);
		
		JLabel lblGmail = new JLabel("Gmail");
		lblGmail.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblGmail.setBackground(SystemColor.menu);
		lblGmail.setBounds(10, 130, 60, 14);
		panel_2.add(lblGmail);
		
		cgmail = new JTextField();
		cgmail.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		cgmail.setColumns(10);
		cgmail.setBounds(114, 128, 228, 20);
		panel_2.add(cgmail);
		
		JDateChooser datenewRecord = new JDateChooser(date);
		datenewRecord.setDateFormatString("dd-MM-y");
		datenewRecord.setBackground(new Color(218, 165, 32));
		datenewRecord.setBounds(113, 159, 131, 20);
		panel_2.add(datenewRecord);
		
		JLabel DelDatecheck = new JLabel("");
		DelDatecheck.setFont(new Font("Times New Roman", Font.BOLD, 14));
		DelDatecheck.setBackground(SystemColor.menu);
		DelDatecheck.setBounds(254, 159, 88, 22);
		panel_2.add(DelDatecheck);
		
		// This part To search particular customer details based on day/month/year wise.
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(205, 133, 63));
		panel_3.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 362, 351, 84);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblId_1 = new JLabel("ID");
		lblId_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblId_1.setBackground(SystemColor.menu);
		lblId_1.setBounds(10, 25, 46, 14);
		panel_3.add(lblId_1);
		
		JLabel lblId_1_1 = new JLabel("Date");
		lblId_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblId_1_1.setBackground(SystemColor.menu);
		lblId_1_1.setBounds(143, 25, 46, 14);
		panel_3.add(lblId_1_1);
		
		sid = new JTextField();
		sid.setText("CU");
		sid.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sid.setColumns(10);
		sid.setBounds(42, 23, 80, 20);
		panel_3.add(sid);
		
		JLabel lblId_1_1_1 = new JLabel("Date");
		lblId_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblId_1_1_1.setBackground(SystemColor.menu);
		lblId_1_1_1.setBounds(143, 59, 46, 14);
		panel_3.add(lblId_1_1_1);
		
		JDateChooser SdateS = new JDateChooser(date);
		SdateS.setDateFormatString("dd-MM-y");
		SdateS.setBackground(new Color(218, 165, 32));
		SdateS.setBounds(213, 23, 117, 20);
		panel_3.add(SdateS);
		
		JDateChooser SdateE = new JDateChooser(date);
		SdateE.setBackground(new Color(218, 165, 32));
		SdateE.setDateFormatString("dd-MM-y");
		SdateE.setBounds(213, 54, 117, 20);
		panel_3.add(SdateE);
		
		// This part is, to display details of customer on appropriate place when we click the a record on table.
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(205, 133, 63));
		panel_4.setBorder(new TitledBorder(null, "Records", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(371, 64, 703, 278);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 683, 246);
		panel_4.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.BOLD, 14));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int i=table.getSelectedRow();
				String ID=table.getModel().getValueAt(i, 1).toString();
				String na=table.getModel().getValueAt(i, 2).toString();
				String CM=table.getModel().getValueAt(i, 3).toString();
				String CA=table.getModel().getValueAt(i, 4).toString();
				String gm=table.getModel().getValueAt(i, 5).toString();
				String dt=table.getModel().getValueAt(i, 6).toString();
				String TB=table.getModel().getValueAt(i, 7).toString();
				String SBA=table.getModel().getValueAt(i,8).toString();
				String TA=table.getModel().getValueAt(i, 9).toString();
				
				cid.setText(ID);
				cn.setText(na);
			    DelDatecheck.setText(dt);
			    tb.setText(TB);
				ta.setText(TA);
				cm.setText(CM);
				ca.setText(CA);
				sba.setText(SBA);
				cgmail.setText(gm);
				dsid.setText(ID);
				dsn.setText(na);
				dsd.setText(dt);
				dsmn.setText(CM);
				dstb.setText(TB);
				dsta.setText(TA);
				dssbp.setText(SBA);
				dsa.setText(CA);
				dsgm.setText(gm);
			}
		});
		scrollPane.setViewportView(table);
		
		// this part is, to save a new record on database.
		
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
			    String Na=cn.getText();
				String Id=cid.getText();
				String M=cm.getText();
				if(cid.getText().equals("")||cn.getText().equals("")||cm.getText().equals("")||ca.getText().equals("")||cgmail.getText().equals("")||tb.getText().equals("")||ta.getText().equals("")||sba.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, " Please fill complete Information");
				}
				else
				{
					if(Id.startsWith("CU") && cid.getText().length()==4)
					{
						if(cn.getText().matches("[a-zA-Z .]+"))
						{
						if(cm.getText().matches("^[0-9]*$") && cm.getText().length()==10 )
						{
							if(cgmail.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
							{	
				               String ID,Name,Dt,Tb,Ta,Sba,mn,ad,gm;
				               ID = cid.getText();
			               	   Name = cn.getText();
				               mn=cm.getText();
				               ad=ca.getText();
				               java.util.Date d1 = datenewRecord.getDate();
				               SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/dd");
				               Dt = formatter.format(d1);
				               Tb=tb.getText();
				               Sba=sba.getText();
				               Ta=ta.getText();
				               gm=cgmail.getText();
				                try {
				                   pst = c.prepareStatement("insert into customer(ID,CNAME,Contact_no,Address,Gmail,_Date,T_B,PofB,T_A)values(?,?,?,?,?,?,?,?,?)");
				                   pst.setString(1, ID);
				                   pst.setString(2, Name);
				                   pst.setString(3, mn);
				                   pst.setString(4, ad);
				                   pst.setString(5, gm);
				                   pst.setString(6, Dt);
				                   pst.setString(7, Tb);
				                   pst.setString(8, Sba);
				                   pst.setString(9, Ta);
				                   pst.executeUpdate();
				                   JOptionPane.showMessageDialog(null, " Detail Saved.");
				                   table_load();
				                   send("kdsbrickkiln2023@gmail.com","mgbisdsqtjkqwmnv ",gm);
				                   cid.setText("");
				                   cn.setText("");
			                       Clear();
			                       cid.requestFocus();
				                   }catch (SQLException e1)  {e1.printStackTrace();}
							}else {JOptionPane.showMessageDialog(null, " Invalid Gmail ID. ");}
						}else{JOptionPane.showMessageDialog(null, " Invalied Mobile no ");}
				}else {JOptionPane.showMessageDialog(null, " Please, Enter the correct name");}
			}else {JOptionPane.showMessageDialog(null, " Please, Enter the correct ID ");	}
		}					
	}
});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(245, 222, 179));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.setBounds(20, 328, 76, 23);
		panel.add(btnNewButton);
		
		// This part is, to update exist record on database.
		
		JButton btnUpdate = new JButton("Update");
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
				  String Na=cn.getText();
					String Id=cid.getText();
					String M=cm.getText();
				if(cid.getText().equals("")||cn.getText().equals("")||cm.getText().equals("")||ca.getText().equals("")||tb.getText().equals("")||ta.getText().equals("")||sba.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, " Please fill complete Information");
				}
				else
				{
					if(Id.startsWith("CU") && cid.getText().length()==4)
					{
						if(cn.getText().matches("[a-zA-Z .]+"))
						{
						if(cm.getText().matches("^[0-9]*$") && cm.getText().length()==10)
						{
							if(cgmail.getText().matches("^[a-zA-Z0-9_+&*-]+(?:\\."+"[a-zA-Z0-9_+&*-]+)*@"+"(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"))
							{
				                  String ID,Name,Dt,Tb,Ta,Sba,mn,ad,gm;
				                  ID = cid.getText();
				                  Name = cn.getText();
				                  mn=cm.getText();
				                  ad=ca.getText();
				                  Dt = DelDatecheck.getText();
				                  Tb=tb.getText();
				                  Sba=sba.getText();
				                  Ta=ta.getText();
				                  gm=cgmail.getText();
				                  try {
				                       pst = c.prepareStatement(" update customer set Contact_no=?, Address=? , T_B=?, PofB=? , T_A=? , Gmail=?, CNAME=?  where ID=? and _Date = ?  ");
				                       pst.setString(1, mn);
				                       pst.setString(2, ad);
				                       pst.setString(3, Tb);
				                       pst.setString(4, Sba);
				                       pst.setString(5, Ta);
				                       pst.setString(6, gm);
				                       pst.setString(7, Name);
				                       pst.setString(8, ID);
				                       pst.setString(9, Dt);
				                       pst.executeUpdate();
				                       JOptionPane.showMessageDialog(null, " Detail updated.");
				                       table_load();
				                       Clear();
				                       cid.requestFocus();
				                      }catch (SQLException e1){e1.printStackTrace();}
                                }else {JOptionPane.showMessageDialog(null, " Invalid Gmail ID ");}
							}else{JOptionPane.showMessageDialog(null, " Invalied Mobile no ");}
                       }else {JOptionPane.showMessageDialog(null, " Please, Enter the correct name ");}
                 }else {JOptionPane.showMessageDialog(null, " Please, Enter the correct ID ");	}
			}
		}
	});
		btnUpdate.setBackground(new Color(245, 222, 179));
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnUpdate.setBounds(118, 328, 94, 23);
		panel.add(btnUpdate);
		
		// This part is, to delete a record into the database.
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cid.getText().equals("")||cn.getText().equals("")||cm.getText().equals("")||ca.getText().equals("")||tb.getText().equals("")||ta.getText().equals("")||sba.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, " Please fill complete Information");
				}
				else
				{
				String ID,Name,Dt,Tb,Ta,Sba,mn,ad,gm;
				ID = cid.getText();
				Name = cn.getText();
				mn=cm.getText();
				ad=ca.getText();
				Dt = DelDatecheck.getText();
				Tb=tb.getText();
				Sba=sba.getText();
				Ta=ta.getText();
				gm=cgmail.getText();
				
				try {
	                    pst = c.prepareStatement("delete from customer where ID=? and CNAME=? and Contact_no=? and Address=? and _Date=? and T_B=? and PofB=? and T_A=? and Gmail=?");
				        pst.setString(1, ID);
				        pst.setString(2, Name);
				        pst.setString(3, mn);
				        pst.setString(4, ad);
			            pst.setString(5,Dt);
				        pst.setString(6, Tb);
				        pst.setString(7, Sba);
				        pst.setString(8, Ta);
				        pst.setString(9, gm);
				        pst.executeUpdate();
				        JOptionPane.showMessageDialog(null, "Detail deleted.");
				        table_load();
				        Clear(); 
				        cid.requestFocus();
				    }catch (SQLException e1) {e1.printStackTrace();}
				}
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnDelete.setBackground(new Color(245, 222, 179));
		btnDelete.setBounds(233, 328, 94, 23);
		panel.add(btnDelete);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Id=sid.getText();
				if(sid.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, " Please fill complete Information");
				}
				else
				{
					if(Id.startsWith("CU") && Id.length()==4) {
						 try {
								pst = c.prepareStatement("select * from customer where ID=?");
								pst.setString(1, Id);
								rs = pst.executeQuery();
								if(rs.next())
								{
									String ID,Dts,Dte;
									ID = sid.getText();
									java.util.Date d1 = SdateS.getDate();
									SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/dd");
									Dts = formatter.format(d1);
									java.util.Date d2 = SdateE.getDate();
									SimpleDateFormat formatter1 = new SimpleDateFormat("YYYY/MM/dd");
									Dte = formatter1.format(d2);
									if(Dts.compareTo(Dte)<0 || Dts.compareTo(Dte)==0)
									{
									try {
									pst = c.prepareStatement("Select * from customer where ID =? and _Date between ? and ? ");
									pst.setString(1, ID);
									pst.setString(2, Dts);
									pst.setString(3, Dte);
									rs = pst.executeQuery();
									table.setModel(DbUtils.resultSetToTableModel(rs)); 
									sid.setText("CU");
									SdateS.setDate(date);
									SdateE.setDate(date);
									sid.requestFocus();
									}catch (SQLException e1) {e1.printStackTrace();}
								}else {JOptionPane.showMessageDialog(null, Dts+ "Starting date higher than of Ending date. "+Dte);}
							} else {JOptionPane.showMessageDialog(null, Id+ " is Not Here. ");}
						 }catch (SQLException e1) {e1.printStackTrace();}
					}else {JOptionPane.showMessageDialog(null, " Please, Enter the correct ID");
				}
			}
		}
	});
		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnSearch.setBackground(new Color(245, 222, 179));
		btnSearch.setBounds(137, 455, 88, 23);
		panel.add(btnSearch);
		
		// This part is, to dispaly a details on application window.
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBackground(new Color(205, 133, 63));
		panel_5.setBounds(371, 347, 703, 109);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblId_9 = new JLabel("ID :");
		lblId_9.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblId_9.setBackground(SystemColor.menu);
		lblId_9.setBounds(10, 33, 33, 14);
		panel_5.add(lblId_9);
		
		JLabel lblName_1 = new JLabel("Name :");
		lblName_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblName_1.setBackground(SystemColor.menu);
		lblName_1.setBounds(200, 33, 46, 14);
		panel_5.add(lblName_1);
		
		JLabel lblDate_1 = new JLabel("Date :");
		lblDate_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblDate_1.setBackground(SystemColor.menu);
		lblDate_1.setBounds(387, 33, 46, 14);
		panel_5.add(lblDate_1);
		
		JLabel lblTotalBricks_1 = new JLabel("Total Bricks :");
		lblTotalBricks_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTotalBricks_1.setBackground(SystemColor.menu);
		lblTotalBricks_1.setBounds(10, 83, 91, 14);
		panel_5.add(lblTotalBricks_1);
		
		JLabel lblTotalAmount_1 = new JLabel("Total amount :");
		lblTotalAmount_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTotalAmount_1.setBackground(SystemColor.menu);
		lblTotalAmount_1.setBounds(200, 83, 91, 14);
		panel_5.add(lblTotalAmount_1);
		
		JLabel lblMobileNo_1 = new JLabel("Mobile No :");
		lblMobileNo_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMobileNo_1.setBackground(SystemColor.menu);
		lblMobileNo_1.setBounds(10, 58, 70, 14);
		panel_5.add(lblMobileNo_1);
		
		JLabel lblAddress_1 = new JLabel("Address :");
		lblAddress_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblAddress_1.setBackground(SystemColor.menu);
		lblAddress_1.setBounds(387, 83, 64, 14);
		panel_5.add(lblAddress_1);
		
		dsid.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsid.setBackground(SystemColor.menu);
		dsid.setBounds(37, 33, 52, 14);
		panel_5.add(dsid);
		
		dsn.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsn.setBackground(SystemColor.menu);
		dsn.setBounds(247, 33, 108, 14);
		panel_5.add(dsn);
		
		dsd.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsd.setBackground(SystemColor.menu);
		dsd.setBounds(431, 33, 81, 14);
		panel_5.add(dsd);
		
		dsmn.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsmn.setBackground(SystemColor.menu);
		dsmn.setBounds(83, 58, 98, 14);
		panel_5.add(dsmn);
		
		dstb.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dstb.setBackground(SystemColor.menu);
		dstb.setBounds(98, 83, 64, 14);
		panel_5.add(dstb);
		
		dsta.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsta.setBackground(SystemColor.menu);
		dsta.setBounds(293, 83, 75, 14);
		panel_5.add(dsta);
		
		dsa.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsa.setBackground(SystemColor.menu);
		dsa.setBounds(447, 83, 246, 14);
		panel_5.add(dsa);
		
		JLabel lblDate_1_1 = new JLabel("PofSB :");
		lblDate_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblDate_1_1.setBackground(SystemColor.menu);
		lblDate_1_1.setBounds(200, 58, 46, 14);
		panel_5.add(lblDate_1_1);
		
		dssbp.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dssbp.setBackground(SystemColor.menu);
		dssbp.setBounds(255, 58, 36, 14);
		panel_5.add(dssbp);
		
		JLabel lblDate_1_1_1 = new JLabel("Gmail :");
		lblDate_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblDate_1_1_1.setBackground(SystemColor.menu);
		lblDate_1_1_1.setBounds(387, 58, 46, 14);
		panel_5.add(lblDate_1_1_1);
		
		dsgm.setFont(new Font("Times New Roman", Font.BOLD, 14));
		dsgm.setBackground(SystemColor.menu);
		dsgm.setBounds(441, 58, 252, 14);
		panel_5.add(dsgm);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clear();
				table_load();
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnClear.setBackground(new Color(245, 222, 179));
		btnClear.setBounds(986, 467, 88, 23);
		panel.add(btnClear);
	}
	
	// this part is, to connect database with customer module.
	
	public void connect()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c=DriverManager.getConnection("jdbc:mysql://localhost:3306/kds", "root", "HWsp@524");
		}catch (ClassNotFoundException ex) {System.out.println(ex);}
		catch (SQLException ex) {System.out.println(ex);}
	}
	
	// This part is, when we perform each operation after the table should be reloaded on application
	
	 public void table_load()
	 {
	     try{
	         pst = c.prepareStatement("select * from customer");
	         rs = pst.executeQuery();
	         table.setModel(DbUtils.resultSetToTableModel(rs));
	     }catch (SQLException e) {e.printStackTrace(); }
	} 
	 
	 // This part is, to send mail to customer about buying details.
	 public void send(String from,String password,String to){  
         //Get properties object    
         Properties props = new Properties();    
         props.put("mail.smtp.host", "smtp.gmail.com");    
         props.put("mail.smtp.socketFactory.port", "465");    
         props.put("mail.smtp.socketFactory.class",    
                   "javax.net.ssl.SSLSocketFactory");    
         props.put("mail.smtp.auth", "true");    
         props.put("mail.smtp.port", "465");    
         //get Session   
         Session session = Session.getDefaultInstance(props,    
          new javax.mail.Authenticator() {    
          protected PasswordAuthentication getPasswordAuthentication() {    
          return new PasswordAuthentication(from,password);  
          }    
         });    
         //compose message    
         try {    
          MimeMessage message = new MimeMessage(session);    
          message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
          message.setSubject(" From KDS Brickkiln Industry ");    
          message.setText("welcome "+cn.getText()+" \n"+"\n"+ " Your ID : "+cid.getText()+"\n"+" Total Bricks : "+tb.getText()+"\n"+" Price of single brick : "+sba.getText()+
	        		 "\n"+" Total amount : "+ta.getText());    
          //send message  
          Transport.send(message);    
          JOptionPane.showMessageDialog(null, " Mail Sent... ");
         } catch (MessagingException e) {throw new RuntimeException(e);}    
            
   }   
	 
	 public void Clear()
	 {
		 cid.setText("");
			cn.setText("");
			cm.setText("");
			ca.setText("");
			cgmail.setText("");
			tb.setText("");
			ta.setText("");
			sba.setText("10");
			sid.setText("CU");
			DelDatecheck.setText("");
			dsid.setText("");
			dsmn.setText("");
			dstb.setText("");
			dsn.setText("");
			dssbp.setText("");
			dsta.setText("");
			dsd.setText("");
			dsgm.setText("");
			dsa.setText("");
			datenewRecord.setDate(date);
			SdateS.setDate(date);
			SdateE.setDate(date);
	 }
}