package br.com.sme.teste.mock;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.util.Locale;


public class TranslateUtil {

    public static Locale getLocaleDefault() {
        return new Locale(Translate._PORTUGUES, "BR");
    }

    public static boolean isLocaleValid(Locale locale) {
        return locale != null
                && (locale.getLanguage().toUpperCase().equals(Translate._ARABE)
                || locale.getLanguage().toUpperCase().equals(Translate._PORTUGUES)
                || locale.getLanguage().toUpperCase().equals(Translate._HINDI) || locale.getLanguage().toUpperCase().equals(
                Translate._INGLES));
    }

    public static boolean isRtl(Locale locale) {
        return locale != null && locale.getLanguage().toUpperCase().equals(Translate._ARABE);
    }

    public static String getDescriptionByLocale(String value, Locale locale) {
        if (value == null) {
            return null;
        }
        return Translate.getDescriptionInLanguageByLocale(true, value, locale);
    }

    public static String getDescriptionByLocaleComplete(String value, Locale locale) {
        if (value == null) {
            return null;
        }
        return TranslateUtil.convertHtmlcodeToJava(TranslateUtil.getDescriptionByLocale(value, locale));
    }

    public static String getDescriptionUnicodeByLocale(String value, Locale locale) {
        if (value == null) {
            return null;
        }
        return TranslateUtil.convertJavaToUnicode(TranslateUtil.getDescriptionByLocaleComplete(value, locale));
    }

    public static String getDescriptionByLanguage(String value, String language, String country) {
        if (value == null) {
            return null;
        }
        Locale locale = new Locale(language, country);
        if (!isLocaleValid(locale)) {
            locale = getLocaleDefault();
        }
        return convertHtmlcodeToJava(TranslateUtil.getDescriptionByLocale(value, locale));
    }

    public static String convertHtmlcodeToJava(String value) {
        if (value == null) {
            return null;
        }
        return Jsoup.clean(value, Whitelist.none());
    }

    public static String convertUnicodeToJava(String value) {
        if (value == null) {
            return null;
        }
        return StringEscapeUtils.unescapeJava(value);
    }

    public static String convertJavaToUnicode(String value) {
        if (value == null) {
            return null;
        }
        return StringEscapeUtils.escapeJava(value);
    }
}
