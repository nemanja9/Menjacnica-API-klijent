package menjacnica.gui;

import java.awt.EventQueue;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import menjacnica.URLConnection;
import menjacnica.CurrencyLayerApiCommunication;
import menjacnica.Konverzije;
import menjacnica.Zemlja;
import menjacnica.gui.kontroler.GUIKontroler;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

public class MenjacnicaGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblIzValuteZemlje;
	private JLabel lblUValutuZemlje;
	public static JComboBox comboBox1;
	public static JComboBox comboBox2;
	private JLabel lblIznos;
	private JLabel label;
	public static JTextField textField1;
	public static JTextField textField2;
	private JButton btnKonvertuj;

	/**
	 * Create the frame.
	 */
	public MenjacnicaGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblIzValuteZemlje());
		contentPane.add(getLblUValutuZemlje());
		contentPane.add(getComboBox1());
		contentPane.add(getComboBox2());
		contentPane.add(getLblIznos());
		contentPane.add(getLabel());
		contentPane.add(getTextField1());
		contentPane.add(getTextField2());
		contentPane.add(getBtnKonvertuj());
		GUIKontroler.dodajZemlje(comboBox1);
		GUIKontroler.dodajZemlje(comboBox2);
	}

	private JLabel getLblIzValuteZemlje() {
		if (lblIzValuteZemlje == null) {
			lblIzValuteZemlje = new JLabel("Iz valute zemlje:");
			lblIzValuteZemlje.setBounds(26, 39, 125, 16);
		}
		return lblIzValuteZemlje;
	}

	private JLabel getLblUValutuZemlje() {
		if (lblUValutuZemlje == null) {
			lblUValutuZemlje = new JLabel("U valutu zemlje:");
			lblUValutuZemlje.setBounds(296, 39, 125, 16);
		}
		return lblUValutuZemlje;
	}

	private JComboBox getComboBox1() {
		if (comboBox1 == null) {
			comboBox1 = new JComboBox();
			comboBox1.setBounds(36, 67, 136, 27);
		}
		return comboBox1;
	}

	private JComboBox getComboBox2() {
		if (comboBox2 == null) {
			comboBox2 = new JComboBox();
			comboBox2.setBounds(296, 67, 148, 27);
		}
		return comboBox2;
	}

	private JLabel getLblIznos() {
		if (lblIznos == null) {
			lblIznos = new JLabel("Iznos:");
			lblIznos.setBounds(26, 126, 61, 16);
		}
		return lblIznos;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Iznos:");
			label.setBounds(308, 126, 61, 16);
		}
		return label;
	}

	private JTextField getTextField1() {
		if (textField1 == null) {
			textField1 = new JTextField();
			textField1.setBounds(26, 156, 130, 26);
			textField1.setColumns(10);
		}
		return textField1;
	}

	private JTextField getTextField2() {
		if (textField2 == null) {
			textField2 = new JTextField();
			textField2.setColumns(10);
			textField2.setBounds(276, 156, 130, 26);
		}
		return textField2;
	}

	private JButton getBtnKonvertuj() {
		if (btnKonvertuj == null) {
			btnKonvertuj = new JButton("Konvertuj");
			btnKonvertuj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIKontroler.izvrsiZamenu();

				}
			});

			btnKonvertuj.setBounds(151, 216, 117, 29);
		}
		return btnKonvertuj;
	}

}
