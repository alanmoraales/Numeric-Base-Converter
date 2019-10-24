package Interfaz;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextPane;

public class PanelNotas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelNotas() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "   Notas", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextPane txtpnEsteProgramaSolo = new JTextPane();
		txtpnEsteProgramaSolo.setEditable(false);
		txtpnEsteProgramaSolo.setText("Este programa solo funciona con n\u00FAmeros enteros.");
		add(txtpnEsteProgramaSolo);

	}

}
