import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
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
	public main() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\asad.albadi\\eclipse-workspace\\mess_hall_rfid_reader\\lib\\50_database_server_rack_storage_data_add_insert-512.png"));
		setTitle("Main");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 317, 264);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRgst = new JButton("Register New User");
		btnRgst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db_records db_rd = new db_records();
				db_rd.setVisible(true);
			}
		});
		btnRgst.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRgst.setBounds(61, 74, 195, 31);
		contentPane.add(btnRgst);
		
		JButton btnRdr = new JButton("Star RFID Reader");
		btnRdr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rfid_reader rf_rdr = new rfid_reader();
				rf_rdr.setVisible(true);
			}
		});
		btnRdr.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRdr.setBounds(61, 116, 195, 31);
		contentPane.add(btnRdr);
	}

}
