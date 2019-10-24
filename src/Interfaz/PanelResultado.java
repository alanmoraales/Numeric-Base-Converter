package Interfaz;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
//import javax.swing.JTextPane;

public class PanelResultado extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JLabel labelResultado;
	
	/**
	 * Create the panel.
	 */
	public PanelResultado() {
		setToolTipText("");
		setBorder(new TitledBorder(null, "   Resultados", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(1, 1, 0, 0));
		
		labelResultado = new JLabel("");
		labelResultado.setHorizontalAlignment(SwingConstants.CENTER);
		add(labelResultado);

	}
	
	public static void setResultado(String resultado) {
		labelResultado.setText(resultado);
	}

}
