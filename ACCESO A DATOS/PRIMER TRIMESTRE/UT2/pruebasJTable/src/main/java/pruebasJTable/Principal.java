package pruebasJTable;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PRUEBAS JTABLE - RESULTSET METADATA");
		lblNewLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		lblNewLabel.setBounds(81, 11, 353, 57);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Ver empleados");
		btnNewButton.setBounds(51, 98, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Ver departamentos");
		btnNewButton_1.setBounds(250, 98, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
