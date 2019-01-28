import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class rfid_reader extends JFrame {

	private JPanel contentPane;
	private JTextField txtbx_rfid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rfid_reader frame = new rfid_reader();
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
	
	public rfid_reader() {
		setResizable(false);
		setTitle("Mess Hall Entry");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 444, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtbx_rfid = new JTextField();
		txtbx_rfid.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtbx_rfid.setColumns(10);
		txtbx_rfid.setBounds(103, 105, 247, 38);
		contentPane.add(txtbx_rfid);
		
		JLabel label = new JLabel("RFID:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(199, 77, 61, 17);
		contentPane.add(label);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String rfid = txtbx_rfid.getText();
				if (rfid != ""  )
				{
					try {
						Class.forName("org.postgresql.Driver");
						Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","root");
						PreparedStatement stmt = con.prepareStatement("INSERT INTO \"MDC\".records " + 
																	  "(rfid, time)" + 
															          "VALUES ("+rfid+",now())");
						@SuppressWarnings("unused")
						int Rs = stmt.executeUpdate();
						@SuppressWarnings("unused")
						ResultSet rs = stmt.getGeneratedKeys();
						
						 txtbx_rfid.setText(null);
						 
						} catch(Exception ex)
							{
							JOptionPane.showMessageDialog(null, ex);
							}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "All fields must be filled");
				}
			}
		});
		btnEnter.setBounds(171, 154, 89, 23);
		contentPane.add(btnEnter);
	}
}
