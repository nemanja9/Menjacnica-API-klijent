package menjacnica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
import menjacnica.Zemlja;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenjacnicaGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblIzValuteZemlje;
	private JLabel lblUValutuZemlje;
	private JComboBox comboBox1;
	private JComboBox comboBox2;
	private JLabel lblIznos;
	private JLabel label;
	private JTextField textField1;
	private JTextField textField2;
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
		contentPane.add(getTextField1());
		contentPane.add(getTextField2());
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
					Zemlja z1 = null, z2 = null;
					for (int i = 0; i < zemlje.size(); i++) {
						if (comboBox1.getSelectedItem().equals(zemlje.get(i).getName())) {
							z1 = zemlje.get(i);
						}
						if (comboBox2.getSelectedItem().equals(zemlje.get(i).getName())) {
							z2 = zemlje.get(i);
						}
					}
					String s = z1.getCurrencyId() + "_";
					s += z2.getCurrencyId();
					String pros = s;
					s = "http://free.currencyconverterapi.com/api/v3/convert?q=" + pros;
					try {
						s = URLConnection.getContent(s);
						JsonParser p = new JsonParser();
						JsonObject obj = p.parse(s).getAsJsonObject();
						Gson g = new GsonBuilder().create();
						int count = g.fromJson(obj.getAsJsonObject("query").getAsJsonPrimitive("count"), int.class);
						if (count == 0) {
							JOptionPane.showMessageDialog(null, "Ne postoji transakcija", "Greska",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						double odnos = g.fromJson(
								obj.getAsJsonObject("results").getAsJsonObject(pros).getAsJsonPrimitive("val"),
								double.class);
						Double d = new Double(odnos * Double.parseDouble(textField1.getText()));
						textField2.setText(d.toString());

					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});

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
