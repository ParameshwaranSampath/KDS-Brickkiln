/*
 * This module is mostly used to calculate total how much amount have we spent for particular category and also person wise 
 * based on the day/month/year wise.
 * 
 * We can also calculate how much of bricks we made, how much load we have done,
 *  how much hours did driver work and also how much bricks we have sold.
 */

package KDS;

import java.awt.EventQueue;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

public class Cal { // main class

	protected static final Date SdateSC = null;
	private JFrame CFrame;
	private JTextField wid;
	private JTextField wn;
	private JTable table;
	Connection c;
	PreparedStatement pst;
	ResultSet rs;
	JLabel odc,nrc;
	Date date = new Date();
	private JComboBox wcb = new JComboBox();
	private JLabel lft = new JLabel("");

   
	public void calcu() {// Main method
	//public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cal window = new Cal();
					window.CFrame.setVisible(true);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}

	public Cal() { // Construction of main method
		initialize();
		connect();
	}

	private void initialize() { // Initiate of main frame.
		CFrame = new JFrame();
		CFrame.getContentPane().setBackground(new Color(210, 105, 30));
		CFrame.setBounds(100, 100, 1100, 550);
		CFrame.setResizable(false);
		CFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CFrame.getContentPane().setLayout(null);
		CFrame.setResizable(false);
		
		JPanel panel = new JPanel(); // name board of the calculator module
		panel.setBackground(new Color(165, 42, 42));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBounds(10, 11, 1064, 43);
		CFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Calculator");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 38));
		lblNewLabel.setBounds(376, 0, 272, 43);
		panel.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("HOME"); // Home button with activation.
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPageClone m = new MainPageClone();
				m.mainPageclone();
				CFrame.dispose();
			}
		});
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_1.setBackground(new Color(255, 127, 80));
		btnNewButton_1.setBounds(965, 0, 99, 43);
		panel.add(btnNewButton_1);
		
		// this part is, to calculate all process category wise. 
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(205, 133, 63));
		panel_1.setBorder(new TitledBorder(null, "Category", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 65, 348, 139);
		CFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Category");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(28, 32, 78, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Date");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(28, 61, 78, 14);
		panel_1.add(lblNewLabel_1_1);
		
		JComboBox ccb = new JComboBox();
		ccb.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		ccb.setBackground(new Color(255, 255, 255));
		ccb.setModel(new DefaultComboBoxModel(new String[] {"Brick_Worker", "Load_Worker", "Drivers", "Customer"}));
		ccb.setBounds(116, 29, 143, 22);
		panel_1.add(ccb);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Date");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(28, 97, 78, 14);
		panel_1.add(lblNewLabel_1_1_1);
		
		JDateChooser SdateSC = new JDateChooser(date);
		SdateSC.setDateFormatString("dd-MM-y");
		SdateSC.setBackground(new Color(218, 165, 32));
		SdateSC.setBounds(116, 61, 143, 20);
		panel_1.add(SdateSC);
		
		JDateChooser SdateEC = new JDateChooser(date);
		SdateEC.setDateFormatString("dd-MM-y");
		SdateEC.setBackground(new Color(218, 165, 32));
		SdateEC.setBounds(116, 91, 143, 20);
		panel_1.add(SdateEC);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(205, 133, 63));
		panel_2.setBorder(new TitledBorder(null, "Records", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(368, 65, 706, 213);
		CFrame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 686, 153);
		panel_2.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.BOLD, 14));
		scrollPane.setViewportView(table);
		
		JComboBox CC = new JComboBox();
		CC.setBackground(new Color(255, 255, 255));
		CC.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		CC.setModel(new DefaultComboBoxModel(new String[] {"Categories", "Workers"}));
		CC.setBounds(579, 14, 117, 22);
		panel_2.add(CC);
		
		JButton btnNewButton = new JButton("Calculate");
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
				int nr=table.getRowCount();
				System.out.println(nr);
				double total=0;
				if(CC.getSelectedItem().equals("Categories"))
				{	
				if(ccb.getSelectedItem().equals("Brick_Worker") )
				{
					int other,o_tot=0;
					double val;
				for(int i=0;i<nr;i++)
				{
					 val=Double.valueOf(table.getValueAt(i,8).toString());
					 other = Integer.valueOf(table.getValueAt(i, 7).toString());
					 o_tot+=other;
				     total+=val;
				}
				odc.setText("Total bricks : "+o_tot);
				}
				else if(ccb.getSelectedItem().equals("Load_Worker")) {
					int other,o_tot=0;
					for(int i=0;i<nr;i++)
					{
						double val=Double.valueOf(table.getValueAt(i,5).toString());
						other = Integer.valueOf(table.getValueAt(i, 4).toString());
						o_tot+=other;
						total+=val;
					}
					odc.setText("Total loads : "+o_tot);
					}
				else if(ccb.getSelectedItem().equals("Drivers")) {
					int other,o_tot=0;
					double val;
					for(int i=0;i<nr;i++)
					{
						val=Double.valueOf(table.getValueAt(i,5).toString());
						other = Integer.valueOf(table.getValueAt(i, 4).toString());
						o_tot+=other;
						total+=val;
					}
					odc.setText("Total Hours : "+o_tot);
				}
				else if(ccb.getSelectedItem().equals("Customer")) {
					int other,o_tot=0;
					for(int i=0;i<nr;i++)
					{
						double val=Double.valueOf(table.getValueAt(i,9).toString());
						other = Integer.valueOf(table.getValueAt(i, 7).toString());
						o_tot+=other;
						total+=val;
					}
					odc.setText("Total bricks : "+o_tot);
				}
				}
				
				if(CC.getSelectedItem().equals("Workers"))
				{
					 boolean a = wcb.getSelectedItem().equals("BW") && wid.getText().startsWith("BW");
					 boolean b =  wid.getText().startsWith("BW");
					 boolean c = wcb.getSelectedItem().equals("BW");
					 System.out.println(a);
					 System.out.println(b);
					 System.out.println(c);
				 if(wcb.getSelectedItem().equals("BW"))
				{
						int other,o_tot=0;
						double val;
					for(int i=0;i<nr;i++)
					{
						 val=Double.valueOf(table.getValueAt(i,8).toString());
						 other = Integer.valueOf(table.getValueAt(i, 7).toString());
						 o_tot=o_tot+other;
						 total=total+val;
					}
					odc.setText("Total bricks : "+o_tot);
				}
				else if( wcb.getSelectedItem().equals("LW") )
				{
					int other,o_tot=0;
					for(int i=0;i<nr;i++)
					{
						double val=Double.valueOf(table.getValueAt(i,6).toString());
						other = Integer.valueOf(table.getValueAt(i, 4).toString());
						o_tot+=other;
						total+=val;
					}
					odc.setText("Total Loads : "+o_tot);
				}
				else if(wcb.getSelectedItem().equals("DW"))
				{
					int other,o_tot=0;
					for(int i=0;i<nr;i++)
					{
						double val=Double.valueOf(table.getValueAt(i,5).toString());
						other = Integer.valueOf(table.getValueAt(i, 4).toString());
						o_tot+=other;
						total+=val;
					}
					odc.setText("Total Hours : "+o_tot);
				}
				}
				String tot=Double.toString(total);
			    lft.setText(" Total Amount : "+ tot);
			    nrc.setText("Rows : "+ nr);
			}
		});
		btnNewButton.setBackground(new Color(245, 222, 179));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.setBounds(889, 289, 153, 23);
		CFrame.getContentPane().add(btnNewButton);
		
		// This part is, to calculate all process based on employees wise.
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(205, 133, 63));
		panel_3.setBorder(new TitledBorder(null, "Calculate details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(368, 323, 706, 124);
		CFrame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		lft.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lft.setHorizontalAlignment(SwingConstants.CENTER);
		
		lft.setBounds(364, 25, 332, 31);
		panel_3.add(lft);
		
		nrc = new JLabel("");
		nrc.setHorizontalAlignment(SwingConstants.CENTER);
		nrc.setFont(new Font("Times New Roman", Font.BOLD, 18));
		nrc.setBounds(10, 25, 110, 17);
		panel_3.add(nrc);
		
		odc = new JLabel("");
		odc.setHorizontalAlignment(SwingConstants.CENTER);
		odc.setFont(new Font("Times New Roman", Font.BOLD, 25));
		odc.setBounds(364, 64, 332, 31);
		panel_3.add(odc);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(67, 316, 291, -38);
		CFrame.getContentPane().add(panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(205, 133, 63));
		panel_5.setBorder(new TitledBorder(null, "Worker", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(10, 265, 348, 182);
		CFrame.getContentPane().add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("Category");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(25, 39, 78, 14);
		panel_5.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("ID");
		lblNewLabel_1_2_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_2_1.setBounds(25, 64, 78, 14);
		panel_5.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Name");
		lblNewLabel_1_2_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_2_2.setBounds(25, 89, 78, 14);
		panel_5.add(lblNewLabel_1_2_2);
		
		JLabel lblNewLabel_1_2_3 = new JLabel("Start Date");
		lblNewLabel_1_2_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_2_3.setBounds(25, 114, 78, 14);
		panel_5.add(lblNewLabel_1_2_3);
		
		JLabel lblNewLabel_1_2_4 = new JLabel("End Date");
		lblNewLabel_1_2_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_2_4.setBounds(25, 139, 78, 14);
		panel_5.add(lblNewLabel_1_2_4);
		
		wid = new JTextField(); // When we enter the ID on field the name field should be filled automatically.
		wid.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		wid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				 String Id=wid.getText();
				 if(Id.startsWith("BW") && Id.length()==4 || Id.startsWith("LW") && Id.length()==4|| Id.startsWith("DW") && Id.length()==4)
				 {
		          try {
					pst = c.prepareStatement("select * from details where ID=?");
					pst.setString(1, Id);
					rs = pst.executeQuery();
					if(rs.next())
					{
						String n=rs.getString(3); 
						wn.setText(n);
					}
				} catch (SQLException e1) {e1.printStackTrace();}
				}else {wn.setText(""); }
			}
		});
		wid.setColumns(10);
		wid.setBounds(109, 62, 132, 20);
		panel_5.add(wid);
		
		wn = new JTextField();
		wn.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		wn.setColumns(10);
		wn.setBounds(109, 87, 132, 20);
		panel_5.add(wn);
		wcb.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		wcb.setBackground(new Color(255, 255, 255));
		wcb.setModel(new DefaultComboBoxModel(new String[] {"BW", "LW", "DW"}));
		wcb.setBounds(109, 36, 132, 22);
		panel_5.add(wcb);
		
		JDateChooser SdateSW = new JDateChooser(date);
		SdateSW.setDateFormatString("dd-MM-y");
		SdateSW.setBackground(new Color(218, 165, 32));
		SdateSW.setBounds(109, 114, 132, 20);
		panel_5.add(SdateSW);
		
		JDateChooser SdateEW = new JDateChooser(date);
		SdateEW.setBackground(new Color(218, 165, 32));
		SdateEW.setDateFormatString("dd-MM-y");
		SdateEW.setBounds(109, 139, 132, 20);
		panel_5.add(SdateEW);
		
		// This part is, to display all search details on table on application screen based on category wise.
		
		JButton btnNewButton_1_2_1 = new JButton("Search");
		btnNewButton_1_2_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				String Dts="",Dte="";
				Date d1 = SdateSC.getDate();
				SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/dd");
				Dts = formatter.format(d1);
				Date d2 = SdateEC.getDate();
				SimpleDateFormat formatter1 = new SimpleDateFormat("YYYY/MM/dd");
				Dte = formatter1.format(d2);
			    if(CC.getSelectedItem().equals("Categories"))
			    {
			    	if(ccb.getSelectedItem().equals("Brick_Worker"))
			    	{
			    		if(Dts.compareTo(Dte)<0 || Dts.compareTo(Dte)==0)
						{
						try {
							pst = c.prepareStatement("Select * from bw where _Date between ? and ? ");
							pst.setString(1, Dts);
							pst.setString(2, Dte);
							rs=pst.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));     
							Date date = new Date();
							SdateSC.setDate(date);
							Date date1 = new Date();
							SdateEC.setDate(date1);
						} catch (SQLException e1) {e1.printStackTrace();}
						}else{JOptionPane.showMessageDialog(null, Dts+ "Starting date higher than of Ending date. "+Dte);}
			    	}
			    	else if(ccb.getSelectedItem().equals("Load_Worker"))
			    	{
			    		if(Dts.compareTo(Dte)<0 || Dts.compareTo(Dte)==0)
						{
						try {
							pst = c.prepareStatement("Select * from lw where _Date between ? and ? ");
							pst.setString(1, Dts);
							pst.setString(2, Dte);
							rs=pst.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));     
							Date date = new Date();
							SdateSC.setDate(date);
							Date date1 = new Date();
							SdateEC.setDate(date1);
						} catch (SQLException e1) {e1.printStackTrace();}
						}else{JOptionPane.showMessageDialog(null, Dts+ "Starting date higher than of Ending date. "+Dte);}
			    	}
			    	else if(ccb.getSelectedItem().equals("Drivers"))
			    	{
			    		if(Dts.compareTo(Dte)<0 || Dts.compareTo(Dte)==0)
						{
						try {
							pst = c.prepareStatement("Select * from dw where _Date between ? and ? ");
							pst.setString(1, Dts);
							pst.setString(2, Dte);
							rs=pst.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));     
							Date date = new Date();
							SdateSC.setDate(date);
							Date date1 = new Date();
							SdateEC.setDate(date1);
						} catch (SQLException e1) {e1.printStackTrace();}
						}else{JOptionPane.showMessageDialog(null, Dts+ "Starting date higher than of Ending date. "+Dte);}
			    	}
			    	else if(ccb.getSelectedItem().equals("Customer"))
			    	{
			    		if(Dts.compareTo(Dte)<0 || Dts.compareTo(Dte)==0)
						{
						try {
							pst = c.prepareStatement("Select * from customer where _Date between ? and ? ");
							pst.setString(1, Dts);
							pst.setString(2, Dte);
							rs=pst.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));     
							Date date = new Date();
							SdateSC.setDate(date);
							Date date1 = new Date();
							SdateEC.setDate(date1);
						} catch (SQLException e1) {e1.printStackTrace();}
						}else{JOptionPane.showMessageDialog(null, Dts+ "Starting date higher than of Ending date. "+Dte);}
			    	}
			    }else{JOptionPane.showMessageDialog(null," Please, First should enable the categories process.");}
			}
		});
		btnNewButton_1_2_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_1_2_1.setBackground(new Color(245, 222, 179));
		btnNewButton_1_2_1.setBounds(133, 215, 89, 23);
		CFrame.getContentPane().add(btnNewButton_1_2_1);
		
		// This part is, to display all search details on table on application screen based on employees wise. 
		
		JButton btnNewButton_1_2_1_1 = new JButton("Search");
		btnNewButton_1_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Dts="",Dte="", id="";
				Date d1 = SdateSW.getDate();
				SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/dd");
				Dts = formatter.format(d1);
				Date d2 = SdateEW.getDate();
				SimpleDateFormat formatter1 = new SimpleDateFormat("YYYY/MM/dd");
				Dte = formatter1.format(d2);
				id=wid.getText();
					if(wid.getText().equals("")||wn.getText().equals("")){JOptionPane.showMessageDialog(null," Please, Should fill the requried fields.");}
					else
					{
			    if(CC.getSelectedItem().equals("Workers"))
			    {
			    	if(wcb.getSelectedItem().equals("BW"))
			    	{
			    		if(Dts.compareTo(Dte)<0 || Dts.compareTo(Dte)==0)
						{
						try {
							pst = c.prepareStatement("Select * from bw where ID=? and _Date between ? and ? ");
							pst.setString(1, id);
							pst.setString(2, Dts);
							pst.setString(3, Dte);
							rs=pst.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));     
							Date date = new Date();
							SdateSC.setDate(date);
							Date date1 = new Date();
							SdateEC.setDate(date1);
							wid.setText("");
							wn.setText("");
						} catch (SQLException e1) {e1.printStackTrace();}
						}else{JOptionPane.showMessageDialog(null, Dts+ "Starting date higher than of Ending date. "+Dte);}
			    	}
			    	else if(wcb.getSelectedItem().equals("LW"))
			    	{
			    		if(Dts.compareTo(Dte)<0 || Dts.compareTo(Dte)==0)
						{
						try {
							pst = c.prepareStatement("Select * from lw where ID=? and _Date between ? and ? ");
							pst.setString(1, id);
							pst.setString(2, Dts);
							pst.setString(3, Dte);
							rs=pst.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));     
							Date date = new Date();
							SdateSC.setDate(date);
							Date date1 = new Date();
							SdateEC.setDate(date1);
							wid.setText("");
							wn.setText("");
						} catch (SQLException e1) {e1.printStackTrace();}
						}else{JOptionPane.showMessageDialog(null, Dts+ "Starting date higher than of Ending date. "+Dte);}
			    	}
			    	else if(wcb.getSelectedItem().equals("DW"))
			    	{
			    		if(Dts.compareTo(Dte)<0 || Dts.compareTo(Dte)==0)
						{
						try {
							pst = c.prepareStatement("Select * from dw where ID=? and _Date between ? and ? ");
							pst.setString(1, id);
							pst.setString(2, Dts);
							pst.setString(3, Dte);
							rs=pst.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));     
							Date date = new Date();
							SdateSC.setDate(date);
							Date date1 = new Date();
							SdateEC.setDate(date1);
							wid.setText("");
							wn.setText("");
						} catch (SQLException e1) {e1.printStackTrace();}
						}else{JOptionPane.showMessageDialog(null, Dts+ "Starting date higher than of Ending date. "+Dte);}
			    	}
			    }else{JOptionPane.showMessageDialog(null," Please, First should enable the worker process."); }
			}
			}
		});
		btnNewButton_1_2_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_1_2_1_1.setBackground(new Color(245, 222, 179));
		btnNewButton_1_2_1_1.setBounds(133, 458, 89, 23);
		CFrame.getContentPane().add(btnNewButton_1_2_1_1);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nrc.setText("");
				lft.setText("");
				odc.setText("");
				CC.setSelectedIndex(0);
				ccb.setSelectedIndex(0);
				wcb.setSelectedIndex(0);
				wid.setText("");
				wn.setText("");
				SdateSC.setDate(date);
				SdateEC.setDate(date);
				SdateSW.setDate(date);
				SdateEW.setDate(date);
				table.setModel(new DefaultTableModel());
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnClear.setBackground(new Color(245, 222, 179));
		btnClear.setBounds(929, 460, 113, 23);
		CFrame.getContentPane().add(btnClear);
	}

// this part is, to connect database with calculator module
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
}