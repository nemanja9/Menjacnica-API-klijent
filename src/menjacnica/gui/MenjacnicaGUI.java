package menjacnica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import menjacnica.CurrencyLayerApiCommunication;
import menjacnica.Zemlja;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class MenjacnicaGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblIzValuteZemlje;
	private JLabel lblUValutuZemlje;
	private JComboBox comboBox1;
	private JComboBox comboBox2;
	private JLabel lblIznos;
	private JLabel label;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnKonvertuj;
	LinkedList<Zemlja> zemlje = CurrencyLayerApiCommunication.getCountries();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenjacnicaGUI frame = new MenjacnicaGUI();
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
		contentPane.add(getTextField());
		contentPane.add(getTextField_1());
		contentPane.add(getBtnKonvertuj());
		dodajZemlje(comboBox1);
		dodajZemlje(comboBox2);
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
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(26, 156, 130, 26);
			textField.setColumns(10);
		}
		return textField;
	}
	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(276, 156, 130, 26);
		}
		return textField_1;
	}
	private JButton getBtnKonvertuj() {
		if (btnKonvertuj == null) {
			btnKonvertuj = new JButton("Konvertuj");
			btnKonvertuj.setBounds(151, 216, 117, 29);
		}
		return btnKonvertuj;
	}
	private void dodajZemlje(JComboBox zem) {
		for (int i = 0; i < zemlje.size(); i++) {
			zem.addItem(zemlje.get(i).getName());
		}
	}
}
