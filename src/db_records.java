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
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Canvas;

@SuppressWarnings({ "unused", "serial" })
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
		setTitle("Registration");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 307, 199);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Emp ID:");
		label.setBounds(24, 37, 46, 14);
		contentPane.add(label);
		
		txtbx_id = new JTextField();
		txtbx_id.setColumns(10);
		txtbx_id.setBounds(113, 34, 70, 20);
		contentPane.add(txtbx_id);
		
		txtbx_name = new JTextField();
		txtbx_name.setColumns(10);
		txtbx_name.setBounds(113, 64, 147, 20);
		contentPane.add(txtbx_name);
		
		JLabel label_1 = new JLabel("Emp Name:");
		label_1.setBounds(24, 66, 76, 17);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("RFID:");
		label_2.setBounds(24, 96, 61, 17);
		contentPane.add(label_2);
		
		txtbx_rfid = new JTextField();
		txtbx_rfid.setColumns(10);
		txtbx_rfid.setBounds(113, 94, 147, 20);
		contentPane.add(txtbx_rfid);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String emp_id = txtbx_id.getText();
				String emp_name = txtbx_name.getText();
				String emp_rfid = txtbx_rfid.getText();
				
			if (txtbx_id.getText().equals("") || txtbx_name.getText().equals("") || txtbx_rfid.getText().equals(""))
					{
				JOptionPane.showMessageDialog(null, "All fields must be filled");
					}
			else
			{
				try {
					Class.forName("org.postgresql.Driver");
					Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","root");
					PreparedStatement stmt = con.prepareStatement("INSERT INTO \"MDC\".emp " + 
																  "( emp_id, emp_name, emp_rfid, time_added)" + 
														          "VALUES ("+emp_id+",'"+emp_name+"','"+emp_rfid+"',now())");
					int Rs = stmt.executeUpdate();
					ResultSet rs = stmt.getGeneratedKeys();
					JOptionPane.showMessageDialog(null, emp_id+" "+emp_name+" "+emp_rfid + " was added to the MDC DB");
					txtbx_id.setText(null);
					txtbx_name.setText(null);
					txtbx_rfid.setText(null);
					 
					} catch(Exception ex)
						{
						JOptionPane.showMessageDialog(null, ex);
						}
				
			}
			
				
			}
		});
		btnAdd.setBounds(142, 128, 89, 23);
		contentPane.add(btnAdd);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtbx_id, txtbx_name, txtbx_rfid, btnAdd}));
	}
}
