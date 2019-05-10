package clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Interfaz extends JFrame {

	private JPanel contentPane;
	private JTextField txt_expresion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz frame = new Interfaz();
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
	public Interfaz() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 120);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		txt_expresion = new JTextField();
		txt_expresion.setText("{3*(7-6)-{3*7}-[6-3]}");
		txt_expresion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(txt_expresion, BorderLayout.NORTH);
		txt_expresion.setColumns(10);
		
		JButton Boton = new JButton("Validar Expresion");
		Boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ValidarExpresion()) {
					JOptionPane.showMessageDialog(null, "La Formula Esta Correcta!");
				} else {
					JOptionPane.showMessageDialog(null, "Formula Incorrecta. Controlar {} [] y ()");
				}
			}
		});
		contentPane.add(Boton, BorderLayout.CENTER);
	}

	public boolean ValidarExpresion() {
		Pila pila= new Pila();
		String cadena=txt_expresion.getText();
		for (int i = 0; i < cadena.length(); i++) {
			if (cadena.charAt(i) == '{' || cadena.charAt(i) == '[' || cadena.charAt(i) == '(') {
				pila.Insertar(cadena.charAt(i));
			} else {
				if (cadena.charAt(i) == '}') {
					if(pila.extraer()!='{') {
						return false;
					}
				} else {
					if (cadena.charAt(i) == ']') {
						if(pila.extraer()!='[') {
							return false;
						}
					} else {
						if (cadena.charAt(i) == ')') {
							if(pila.extraer()!='(') {
								return false;
							}
						}
					}
				}
			}
		}return pila.PilaVacia();
	}

}
