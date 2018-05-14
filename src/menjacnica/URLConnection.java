package menjacnica;

import menjacnica.sistemskeOperacije.SOGetContent;

public class URLConnection {

	public static String getContent(String url) throws Exception {
		return SOGetContent.izvrsi(url);
	}
}
