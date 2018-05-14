
package menjacnica;

import java.util.LinkedList;

import menjacnica.sistemskeOperacije.SOGetCountries;

public class CurrencyLayerApiCommunication  {

	public static LinkedList<Zemlja> getCountries() throws Exception {
		return SOGetCountries.izvrsi();

	}

}