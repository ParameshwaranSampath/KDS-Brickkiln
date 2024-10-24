/* 
 * This is module same as log in module of home page but only one different is, in this there is no any authentications 
 * enabled the application main option. that's one always enables
 * 
 * The reason for when i enter the home from other module apart from main home page. that should be enabled without any authentication 
 * option
 * 
 */
package KDS;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.naming.InitialContext;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;

public class MainPageClone{ // Main class name

	private JFrame HomeFrame;
	private JTextField un;
	private JPasswordField pw;
	private JLabel wm = new JLabel();
	private JPanel pbw;
	private JPanel plw;
	private JPanel pd;
	private JPanel pc;
	private JPanel pcal;
	private String PW_Change = "";
	private JTextField txtHello;
	
	public void mainPageclone() { // Main method name
	//public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPageClone window = new MainPageClone();
					window.HomeFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainPageClone() {// Construction of main method
		initialize();
	}

	private void initialize() {
		
		HomeFrame = new JFrame();
		HomeFrame.setBackground(new Color(210, 105, 30));
		HomeFrame.setBounds(100, 100, 1100, 550);
		HomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		HomeFrame.getContentPane().setLayout(null);
		HomeFrame.setResizable(false);
	
		 // The main option process 
		 
		JPanel menu = new JPanel();
		menu.setBackground(new Color(165, 42, 42));
		menu.setBorder(new LineBorder(new Color(128, 0, 0), 2));
		menu.setBounds(0, 0, 352, 522);
		HomeFrame.getContentPane().add(menu);
		menu.setLayout(null);
		
		JLabel T1 = new JLabel("K D S"); // The company name board
		T1.setFont(new Font("Times New Roman", Font.BOLD, 43));
		T1.setHorizontalAlignment(SwingConstants.CENTER);
		T1.setBounds(96, 27, 143, 42);
		menu.add(T1);
		
		JLabel lblNewLabel = new JLabel("Brick klin Industry");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 33));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 80, 314, 47);
		menu.add(lblNewLabel);
		
		pbw = new JPanel(); // Brick worker
		pbw.setLayout(null);
		pbw.setBackground(new Color(205, 133, 63));
		pbw.setBounds(0, 164, 352, 47);
		
		JLabel bwl = new JLabel("Brick Workers");
		bwl.setBounds(70, 0, 180, 47);
		bwl.setHorizontalAlignment(SwingConstants.CENTER);
		bwl.setFont(new Font("Times New Roman", Font.BOLD, 23));
		pbw.add(bwl);
		menu.add(pbw);
		
		pbw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pbw.setBackground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pbw.setBackground(new Color(205, 133, 63));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pbw.setBackground(new Color(255, 255, 255));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pbw.setBackground(new Color(205, 133, 63));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				BW_1 b=new BW_1();
				b.brickworker();
				HomeFrame.dispose();
			}
		});
		
		plw = new JPanel(); // load worker
		plw.setLayout(null);
		plw.setBackground(new Color(205, 133, 63));
		plw.setBounds(0, 210, 352, 47);
				
		JLabel lwl = new JLabel("Load Workers");
		lwl.setHorizontalAlignment(SwingConstants.CENTER);
		lwl.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lwl.setBounds(71, 0, 180, 47);
		plw.add(lwl);
		menu.add(plw);
		
		plw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				plw.setBackground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				plw.setBackground(new Color(205, 133, 63));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				plw.setBackground(new Color(255, 255, 255));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				plw.setBackground(new Color(205, 133, 63));
			}
			@Override
		 	public void mouseClicked(MouseEvent e) {
		 		LW_1 l=new LW_1();
		 		l.loadworker();
		 		HomeFrame.dispose();
		 	}
		});
		
	    pd = new JPanel(); // Drivers
		pd.setLayout(null);
		pd.setBackground(new Color(205, 133, 63));
		pd.setBounds(0, 257, 352, 47);
		
		JLabel dl = new JLabel("Drivers");
		dl.setHorizontalAlignment(SwingConstants.CENTER);
		dl.setFont(new Font("Times New Roman", Font.BOLD, 23));
		dl.setBounds(90, 0, 136, 47);
		pd.add(dl);
		menu.add(pd);
		
		pd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pd.setBackground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pd.setBackground(new Color(205, 133, 63));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pd.setBackground(new Color(255, 255, 255));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pd.setBackground(new Color(205, 133, 63));
			}
			@Override
	    	public void mouseClicked(MouseEvent e) {
	    		DW_1 d=new DW_1();
	    		d.Divers();
	    		HomeFrame.dispose();
	    	}
		});
		
		pc = new JPanel(); // Customer
		pc.setLayout(null);
		pc.setBackground(new Color(205, 133, 63));
		pc.setBounds(0, 304, 352, 47);
		
		JLabel cl = new JLabel("Customer");
		cl.setHorizontalAlignment(SwingConstants.CENTER);
		cl.setFont(new Font("Times New Roman", Font.BOLD, 23));
		cl.setBounds(95, 0, 136, 47);
		pc.add(cl);
		menu.add(pc);
		
		pc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pc.setBackground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pc.setBackground(new Color(205, 133, 63));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pc.setBackground(new Color(255, 255, 255));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pc.setBackground(new Color(205, 133, 63));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Customer_1 c=new Customer_1();
				c.customer();
				HomeFrame.dispose();
			}
		});
		
		pcal = new JPanel(); // Calculator
		pcal.setLayout(null);
		pcal.setBackground(new Color(205, 133, 63));
		pcal.setBounds(0, 351, 352, 47);
		
		JLabel call = new JLabel("Calculator");
		call.setHorizontalAlignment(SwingConstants.CENTER);
		call.setFont(new Font("Times New Roman", Font.BOLD, 23));
		call.setBounds(97, 0, 136, 47);
		pcal.add(call);
		menu.add(pcal);
		
		pcal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pcal.setBackground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pcal.setBackground(new Color(205, 133, 63));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pcal.setBackground(new Color(255, 255, 255));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pcal.setBackground(new Color(205, 133, 63));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Cal c=new Cal();
				c.calcu();
				HomeFrame.dispose();
			}
		});
	
		JPanel pdet = new JPanel(); // Details
		pdet.setLayout(null);
		pdet.setBackground(new Color(205, 133, 63));
		pdet.setBounds(0, 398, 352, 47);
		menu.add(pdet);
		
		JLabel lblDetails = new JLabel("Details");
		lblDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetails.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblDetails.setBounds(97, 0, 136, 47);
		pdet.add(lblDetails);
		
		pdet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pdet.setBackground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pdet.setBackground(new Color(205, 133, 63));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pdet.setBackground(new Color(255, 255, 255));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pdet.setBackground(new Color(205, 133, 63));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Details_1 c=new Details_1();
				c.del();
				HomeFrame.dispose();
			}
		});
		
		JButton btnNewButton_1 = new JButton("Logout"); // Log out button with process
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeFrame.dispose();
			}
		});
		btnNewButton_1.setBackground(new Color(240, 230, 140));
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_1.setBounds(102, 466, 99, 23);
		menu.add(btnNewButton_1);

		// The admin login process part
		
		JPanel Logpanel = new JPanel();
		Logpanel.setBackground(new Color(210, 105, 30));
		Logpanel.setBounds(352, 0, 732, 511);
		HomeFrame.getContentPane().add(Logpanel);
		Logpanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(165, 42, 42));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBounds(234, 82, 298, 286);
		Logpanel.add(panel);
		panel.setLayout(null);
		
		JLabel al = new JLabel("Admin Login"); // Admin name board
		al.setBounds(65, 5, 168, 35);
		panel.add(al);
		al.setHorizontalAlignment(SwingConstants.CENTER);
		al.setFont(new Font("Times New Roman", Font.BOLD, 30));
		
		JPanel Unp = new JPanel(); // User name part
		Unp.setBounds(37, 61, 236, 42);
		panel.add(Unp);
		Unp.setBackground(new Color(255, 255, 255));
		Unp.setLayout(null);
		
		un = new JTextField();
		un.setFont(new Font("Times New Roman", Font.BOLD, 15));
		un.setBorder(null);
		un.setForeground(new Color(0, 0, 0));
		un.setText("Username");
		un.setBounds(10, 11, 120, 20);
		Unp.add(un);
		un.setColumns(10);
		
		JPanel Pwp = new JPanel(); // Password part
		Pwp.setBounds(37, 114, 236, 42);
		panel.add(Pwp);
		Pwp.setBackground(new Color(255, 255, 255));
		Pwp.setLayout(null);
		
		pw = new JPasswordField();
		pw.setFont(new Font("Times New Roman", Font.BOLD, 15));
		pw.setEchoChar((char)0);
		pw.setBorder(null);
		pw.setText("Password");
		pw.setToolTipText("");
		pw.setBounds(10, 11, 118, 20);
		Pwp.add(pw);
		wm.setBounds(37, 175, 236, 36);
		panel.add(wm);
		wm.setForeground(new Color(255, 255, 255));
		
		JPanel LI = new JPanel(); // Login button process
		LI.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(un.getText().equals("admin") && pw.getText().equals(PW_Change) || pw.getText().equals("Admin1234"))
					
				{
					wm.setText("");
					JOptionPane.showMessageDialog(null, "Login Successfully..");
				}
				else
				{
					wm.setText(" Username and password didn't match!");
				}
			   if(un.getText().equals("")||un.getText().equals("Username")||pw.getText().equals("")||pw.getText().equals("Password"))
				{
					wm.setText(" Please input all requirements!");
				}
			   pw.setText("Password");
			   un.setText("Username");
			}
		});
		LI.setBounds(37, 233, 236, 42);
		panel.add(LI);
		LI.setLayout(null);
		LI.setBackground(new Color(244, 164, 96));
		
		JLabel ll = new JLabel("LOG IN"); // Login button
		ll.setFont(new Font("Times New Roman", Font.BOLD, 18));
		ll.setHorizontalAlignment(SwingConstants.CENTER);
		ll.setBounds(61, 0, 114, 43);
		LI.add(ll);
		
		JButton btnNewButton = new JButton("Password change"); // The password change process part
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setText("Update");
				if(btnNewButton.getText().equals("Update"))
				{
					if(!txtHello.getText().equals(""))
					{
						String getChangePW = txtHello.getText();
						PW_Change = getChangePW;
						JOptionPane.showMessageDialog(null, "Password has been updated successfully...");
						btnNewButton.setText("Password change");
						txtHello.setText("");
					}
				}
			}
		});
		btnNewButton.setBackground(new Color(165, 42, 42));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnNewButton.setBounds(553, 11, 177, 23);
		Logpanel.add(btnNewButton);
		
		txtHello = new JTextField();
		txtHello.setText("");
		txtHello.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtHello.setBounds(553, 43, 177, 20);
		Logpanel.add(txtHello);
		txtHello.setColumns(10);
					  	
		pw.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				 if(pw.getText().equals("Password"))
				  {
					  pw.setEchoChar('.');
					  pw.setText("");
				  }
				  else
				  {
					  pw.selectAll();
				  }
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(pw.getText().equals(""))
				{
					pw.setText("Password");
					pw.setEchoChar((char)0);
				}
			}
		});
		un.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
			  if(un.getText().equals("Username"))
			  {
				  un.setText("");
			  }
			  else
			  {
				  un.selectAll();
			  }
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(un.getText().equals(""))
				{
					un.setText("Username");
				}
			}
		});
		
	}
	}