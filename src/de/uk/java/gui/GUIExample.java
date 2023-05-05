package de.uk.java.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;

/**
 * @author Théo Bouveyron
 * One GUI-Class to rule them all. In dieser Klasse wird die grafische Nutzeroberfläche aufgesetzt.
 * Wir können unser Swing Fenster entweder in Code festlegen oder wir machen die gesamte GUI-Klasse zum Hauptfenster
 * Dafür müssen wir von der Klasse JFrame (ein Fenster) erben. 
 * Außerdem implementieren wir einen ActionListener. 
 * Damit können wir Aktionen, die innerhalb des Fensters passieren abfangen. (Dazu weiter unten mehr)
 */
public class GUIExample extends JFrame implements ActionListener{
	// Unser Hauptinhalt innerhalb des Fensters
	private JPanel panel;
	
	/***
	 * Default-Konstruktor wird überschrieben, damit alle nötigen Fenster-Komponente und -Eigenschaften aufgesetzt werden,
	 * sobald wir die GUI-Klasse instanzieren --> Also unsere Nutzeroberfläche starten
	 */
	public GUIExample() {
		// Hier wird das Standarddesign von Swing (Blau-weißer Gradient) mit dem Design des ausführenden Betriebssystem überschrieben
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		/**
		 * Diese Eigenschaften werden ihr so ziemlich in jeder Swing-Applikation finden
		 * Titel (Name des Fensters - oben links)
		 * setSize --> ohne wird das generierte Fenster ganz klein erscheinen. Hier wird die größe noch händisch eingegeben
		 * setLocationRelativeTo(null) --> Normalerweise wird ein Swingfenster in der linken oberen Ecke geöffnet. 
		 * Mit dem Ausdruck wird (basierend auf der Fenstergröße) die Position auf die Mitte des Bildschirms gesetzt
		 * 
		 * Denkt hier an die Reihenfolge. setSize sollte nicht nach setLocationRelativeTo aufgerufen werden, da das Fenster dadurch nicht mehr mittig ist.
		 */
		setTitle("Swing Application");
		setSize(640,480);
		setLocationRelativeTo(null);
		
		/**
		 * Was soll passieren, wenn das X in der rechten oberen Ecke des Fensters gedrückt wird
		 * Hier wird dann "quasi" System.exit(0) ausgeführt --> Programm beendet.
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Panel wird aufgesetzt und ihm wird ein Layout übergeben
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		/**
		 * Zwei Knöpfe werden erstellt. Diese zwei Knöpfe haben verschiedene Namen (und Schriftzügen)
		 */
		JButton button = new JButton("Quit");
		// Ausrichtung für das Layout des Panels
		button.setAlignmentX(CENTER_ALIGNMENT);
		// Welche Nachricht soll an den ActionListener geschickt werden, wenn der Knopf gedrückt wird.
		button.setActionCommand("quit");
		// Welcher ActionListener soll auf die Aktionen diesen Knopfes hören. Hier haben wir den ActionListener in der gleichen Klasse implementiert und können this benutzen.
		button.addActionListener(this);
		
		JButton button2 = new JButton("Ich bin ein Knopf^2");
		button2.setAlignmentX(CENTER_ALIGNMENT);
		button2.setActionCommand("Button2 pressed");
		button2.addActionListener(this);
		
		// Beide Knöpfe dem Panel hinzufügen
		panel.add(button2);
		panel.add(button);
		
		// Das Panel dem (Haupt-)Fenster hinzufügen.
		add(panel);
		
		// Als (möglichst immer) letzter Schritt wird das Fenster und all dessen Inhalte sichtbar gemacht.
		setVisible(true);
		
	}

	/**
	 * Die Methode, die vom ActionListener-Interface erwartet wird
	 * Hier werden die Aktionen der Knöpfe hingeschickt und verarbeitet
	 * ActionEvent ist im Allgemein irgendeine Aktion von einer Swing-Komponente
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/**
		 * Hiermit suchen wir uns die Action Commands der entsprechenden Aktion raus. 
		 * Bspw. die, die wir in den Zeilen 69 und 75 definiert haben.
		 * Wird also der ActionCommand "quit" gehört, können wir beispielsweise die Applikation beenden.
		 */
		switch (e.getActionCommand()) {
		case ("quit"):
			System.exit(0);
			break;
		case ("Button2 pressed"):
			System.out.println("Der zweite Knopf wurde gedrückt");
			break;
		default:
			System.out.println("Action not recognized");
			break;
	}
}
}
