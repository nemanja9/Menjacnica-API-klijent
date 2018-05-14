
package menjacnica;

import java.util.LinkedList;

import menjacnica.sistemskeOperacije.SOGetCountries;

public class CurrencyLayerApiCommunication  {

	public static LinkedList<Zemlja> getCountries()  {
		return SOGetCountries.izvrsi();

	}

}