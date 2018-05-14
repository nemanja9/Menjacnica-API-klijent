package menjacnica.gui.kontroler;

import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import menjacnica.CurrencyLayerApiCommunication;
import menjacnica.Konverzije;
import menjacnica.URLConnection;
import menjacnica.Zemlja;
import menjacnica.gui.MenjacnicaGUI;

public class GUIKontroler {

	public static LinkedList<Zemlja> zemlje = CurrencyLayerApiCommunication.getCountries();
	public static MenjacnicaGUI gp;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIKontroler.gp = new MenjacnicaGUI();
					gp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void izvrsiZamenu() {

		Zemlja z1 = null, z2 = null;
		for (int i = 0; i < zemlje.size(); i++) {
			if (MenjacnicaGUI.comboBox1.getSelectedItem().equals(zemlje.get(i).getName())) {
				z1 = zemlje.get(i);
			}
			if (MenjacnicaGUI.comboBox2.getSelectedItem().equals(zemlje.get(i).getName())) {
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
			
			Gson g = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd HH:mm:ss.S").create();
			int count = g.fromJson(obj.getAsJsonObject("query").getAsJsonPrimitive("count"), int.class);
			if (count == 0) {
				JOptionPane.showMessageDialog(null, "Ne postoji transakcija", "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			double odnos = g.fromJson(obj.getAsJsonObject("results").getAsJsonObject(pros).getAsJsonPrimitive("val"),
					double.class);
			Double d = 0.0;

			try {
				d = new Double(odnos * Double.parseDouble(MenjacnicaGUI.textField1.getText()));
			} catch (Exception e) {

				JOptionPane.showMessageDialog(gp, "Niste uneli broj", "Greska", JOptionPane.ERROR_MESSAGE);
			}
			MenjacnicaGUI.textField2.setText(d.toString());

			Konverzije k = new Konverzije();
			k.setDatum(new GregorianCalendar().getTime());
			k.setIzValuta(z1.getCurrencyId());
			k.setuValuta(z2.getCurrencyId());
			k.setKurs(odnos);

			String kon = g.toJson(k);
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("data/log.json", true)));
			writer.println(kon);
			writer.close();

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	public static void dodajZemlje(JComboBox zem) {
		for (int i = 0; i < zemlje.size(); i++) {
			zem.addItem(zemlje.get(i).getName());
		}
	}
}
