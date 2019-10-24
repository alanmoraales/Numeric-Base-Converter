package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
//import java.util.Scanner;
//import java.awt.Image;

//import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

//import modelo.Conversor;
//import java.awt.Window.Type;

public class InterfazConversorBases extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	/*
	private static Scanner entradaString;
	private static Scanner entradaInt;
	*/
	//private static Conversor conversor;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//conversor = new Conversor(); // creamos el conversor.
				try {
					InterfazConversorBases frame = new InterfazConversorBases();
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
	public InterfazConversorBases() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InterfazConversorBases.class.getResource("/icono/intercambio.png")));
		setResizable(false);
		setTitle("Conversor de Bases");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 291);
		//setIconImage(new Image());
		//ImageIcon icono = new ImageIcon("icono/intercambio.png");
		//setIconImage(icono.getImage());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		PanelEntrada panelEntrada = new PanelEntrada();
		contentPane.add(panelEntrada, BorderLayout.NORTH);
		
		PanelResultado panelResultado = new PanelResultado();
		contentPane.add(panelResultado, BorderLayout.CENTER);
		
		PanelNotas panelNotas = new PanelNotas();
		contentPane.add(panelNotas, BorderLayout.SOUTH);
	}

}
