import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JTextPane;

public class db_records extends JFrame {

	private JPanel contentPane;
	private JTextField txtbx_id;
	private JTextField txtbx_name;
	private JTextField txtbx_rfid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					db_records frame = new db_records();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public db_records() {
		setTitle("Add To DB");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 309, 162);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Emp ID:");
		label.setBounds(10, 11, 46, 14);
		contentPane.add(label);
		
		txtbx_id = new JTextField();
		txtbx_id.setColumns(10);
		txtbx_id.setBounds(99, 8, 70, 20);
		contentPane.add(txtbx_id);
		
		txtbx_name = new JTextField();
		txtbx_name.setColumns(10);
		txtbx_name.setBounds(99, 38, 147, 20);
		contentPane.add(txtbx_name);
		
		JLabel label_1 = new JLabel("Emp Name:");
		label_1.setBounds(10, 40, 76, 17);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("RFID:");
		label_2.setBounds(10, 70, 61, 17);
		contentPane.add(label_2);
		
		txtbx_rfid = new JTextField();
		txtbx_rfid.setColumns(10);
		txtbx_rfid.setBounds(99, 68, 147, 20);
		contentPane.add(txtbx_rfid);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String emp_id = txtbx_id.getText();
				String emp_name = txtbx_name.getText();
				String emp_rfid = txtbx_rfid.getText();
				
				try {
					Class.forName("org.postgresql.Driver");
					Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","root");
					PreparedStatement stmt = con.prepareStatement("INSERT INTO \"MDC\".emp " + 
																  "( emp_id, emp_name, emp_rfid)" + 
														          "VALUES ("+emp_id+",'"+emp_name+"','"+emp_rfid+"')");
					int Rs = stmt.executeUpdate();
					ResultSet rs = stmt.getGeneratedKeys();
					JOptionPane.showMessageDialog(null, emp_id+" "+emp_name+" "+emp_rfid + " was added to the MDC DB");
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnAdd.setBounds(128, 102, 89, 23);
		contentPane.add(btnAdd);
	}
}
