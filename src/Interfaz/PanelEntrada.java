package Interfaz;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import modelo.Conversor;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
//import java.awt.event.InputMethodListener;
//import java.awt.event.InputMethodEvent;

public class PanelEntrada extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfNumero;
	private JComboBox<Integer> cbBase;
	private JComboBox<Integer> cbBaseObjetivo;

	/**
	 * Create the panel.
	 */
	public PanelEntrada() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "   Conversor de Bases", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new GridLayout(4, 2, 0, 0));
		
		JLabel lbNumero = new JLabel("   Introduce tu n\u00FAmero");
		lbNumero.setHorizontalAlignment(SwingConstants.LEFT);
		add(lbNumero);
		
		tfNumero = new JTextField();
		
		tfNumero.addKeyListener(new KeyAdapter() {
			@Override
			
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				// si la tecla no es alfanumerica, entonces no se escribe.
				if(Character.isDigit(tecla) == false && Character.isAlphabetic(tecla) == false) {
					e.consume();
				}
				
				//si la tecla es una letra, la escribe en mayuscula.
				//char tecla = e.getKeyChar();
				if(Character.isLetter(tecla)) {
					e.setKeyChar(Character.toUpperCase(tecla));
				}
				
				
				
				/*
				if(Character.isAlphabetic(tecla)) {
					String entrada = getNumero();
					
					if(entrada.isEmpty() == false) {
						// quitamos el ultimo caracter (tecla) de la cadena de entrada.
						entrada = entrada.substring(0,entrada.length()-1);
					}
					// convertimos el char a string y lo ponemos en mayuscula
					// se concatena con la subcadena de la entrada.
					entrada = entrada + String.valueOf(tecla).toUpperCase();
					tfNumero.setText(entrada); // ponemos la nueva entrada.
				}
				*/
			}
			
			
			@Override
			// si la tecla es alfabetica, se escribe en mayuscula.
			// solo se permite ingresar letras mayusculas.
			public void keyReleased(KeyEvent e) {
				// verificamos el numero introducido con la base.
				String numeroEntrada = getNumero();
				//String numeroEntrada = e.getText();
				int base = getBase();
				// si el número no es válido (no coincide con su base)
				if(numeroEsValido(numeroEntrada, base) == false) {
					PanelResultado.setResultado("El número introducido no coincide con su base.");
				}
				else {
					PanelResultado.setResultado("");
				}
				/*
				if(e.getSource() == tfNumero) {
					char tecla = e.getKeyChar();
					if(Character.isAlphabetic(tecla)) {
						String entrada = getNumero();
						tfNumero.setText("");
						if(entrada.isEmpty() == false) {
							// quitamos el ultimo caracter (tecla) de la cadena de entrada.
							entrada = entrada.substring(0,entrada.length()-1);
						}
						// convertimos el char a string y lo ponemos en mayuscula
						// se concatena con la subcadena de la entrada.
						entrada = entrada + String.valueOf(tecla).toUpperCase();
						tfNumero.setText(entrada); // ponemos la nueva entrada.
					}
				}
				*/
				
			}
			
		});
		add(tfNumero);
		tfNumero.setColumns(10);
		
		JLabel lblEscritoEnBase = new JLabel("   Escrito en base");
		lblEscritoEnBase.setHorizontalAlignment(SwingConstants.LEFT);
		add(lblEscritoEnBase);
		
		cbBase = new JComboBox<Integer>();
		cbBase.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String numeroEntrada = getNumero();
				//String numeroEntrada = e.getText();
				int base = getBase();
				// si el número no es válido (no coincide con su base)
				if(numeroEsValido(numeroEntrada, base) == false) {
					PanelResultado.setResultado("El número introducido no coincide con su base.");
				}
				else {
					PanelResultado.setResultado("");
				}
			}
		});
		cbBase.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {2, 3, 4,5,6,7,8,9,10,16}));
		cbBase.setMaximumRowCount(10);
		add(cbBase);
		
		JLabel lbBaseObjetivo = new JLabel("   Para convertir a base");
		lbBaseObjetivo.setHorizontalAlignment(SwingConstants.LEFT);
		add(lbBaseObjetivo);
		
		cbBaseObjetivo = new JComboBox<Integer>();
		cbBaseObjetivo.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {2,3,4,5,6,7,8,9,10,16}));
		cbBaseObjetivo.setMaximumRowCount(10);
		add(cbBaseObjetivo);
		
		JLabel label = new JLabel("");
		add(label);
		
		JButton btnConvertir = new JButton("Convertir");
		btnConvertir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// se lanza si el número es válido.
				if(numeroEsValido(getNumero(), getBase())) {
					String numero;
					int base, baseObjetivo; // para guardar entradas
					
					// para resultados
					float auxResultado;
					String resultado;
					
					//pedimos las entradas.
					numero = getNumero();
					base = getBase();
					baseObjetivo = getBaseObjetivo();
					
					auxResultado = Conversor.toDecimal(numero, base);
					resultado = Conversor.toBaseN(auxResultado, baseObjetivo);
					
					PanelResultado.setResultado(resultado);
				}
			}
		});
		add(btnConvertir);

	}
	
	
	public String getNumero() {
		return tfNumero.getText();
	}
	
	public int getBase() {
		return (int)cbBase.getSelectedItem();
	}
	
	public int getBaseObjetivo() {
		return (int)cbBaseObjetivo.getSelectedItem();
	}
	
	// método para verificar si la entrada coincide con la base.
	public boolean numeroEsValido(String numero, int base) {
		
		char[] hexadecimal = {'A','B','C','D','E','F'}; // CARACTERES ESPECIALES ACEPTADOR POR EL SISTEMA HEXADECIMAL
		char[] arrayNumero = numero.toCharArray(); // convertimos el numero a un arreglo de caracteres.
		int contador=0; 
		String auxString, auxString2; // para comparar.
		
		// si la base es diferente de 16.
		if(base != 16) {
			// para bases del 2-10.
			// desde 0 hasta la longitud del numero.
			for(int i=0; i<numero.length(); i++) {
				auxString = "" + arrayNumero[i];
				//System.out.println("" + numero.length());
				// numeros del 0 hasta base-1 (números aceptados por esa base)
				for(int j=0; j<base; j++){
					auxString2 = Integer.toString(j);
					if(auxString.equals(auxString2)) {
						contador++;
						//System.out.println("" + contador);
						break;
					}
				}	
			}
		}
		else {
			// contamos numeros
			// para base hexadecimal.
			// puede aceptar numeros del 1-9 y las letras A,B,C,D,E,F.
			for(int i=0; i<numero.length(); i++) {
				auxString = "" + arrayNumero[i];
				for(int j=0; j<=9; j++) {
					auxString2 = Integer.toString(j);
					if(auxString.equals(auxString2)) {
						contador++;
						break;
					}
				}
			}
			// contamos letras
			// si no coincide con alguno de los números, probamos con las letras.
			for(int i=0; i<numero.length(); i++) {
				for(int k=0; k<hexadecimal.length; k++) {
						if(arrayNumero[i] == hexadecimal[k]) {
							contador++;
							break;
						}
				}
			}
			
		}
		
		//System.out.println("" + contador);
		// si no todos los numeros coincidieron alguno de los numeros aceptados por la base.
		if(contador != numero.length()) {
			return false;
		}
		
		return true; // si todos coincidieron, entonces el número es válido.
	}
	

}
