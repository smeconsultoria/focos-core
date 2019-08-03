package br.com.sme.teste.mock;

import java.util.Locale;

public class Translate {
	public static final String _PORTUGUES = "PT";
	public static final String _INGLES = "EN";
	public static final String _ARABE = "AR";
	public static final String _HINDI = "HI";
	public static final int _POS_PORTUGUES = 0;
	public static final int _POS_INGLES = 1;
	public static final int _POS_ARABE = 2;
	public static final int _POS_HINDI = 3;
	private static final String _SEPARADOR = "\\|";

	public Translate() {
	}

	public static boolean isDirectionLanguageByRightToLeft(Locale locale) {
		return locale.getLanguage().toUpperCase().equals("AR");
	}

	public static String getDescriptionInLanguageByLocale(boolean flTranslate, String value, Locale locale) {
		String retorno = value;

		if (flTranslate) {
			String[] vetIdioma = value.split("\\|");
			if (vetIdioma.length > 1) {
				if (locale.getLanguage().toUpperCase().equals("EN")) {
					retorno = vetIdioma[1];
				} else if (locale.getLanguage().toUpperCase().equals("AR")) {
					if (vetIdioma.length < 3) {
						retorno = vetIdioma[1];
					} else {
						retorno = vetIdioma[2];
					}
				} else if (locale.getLanguage().toUpperCase().equals("HI")) {
					if (vetIdioma.length < 4) {
						retorno = vetIdioma[1];
					} else {
						retorno = vetIdioma[3];
					}
				} else {
					retorno = vetIdioma[0];
				}
			}
		}
		return retorno;
	}
}
