package com.zmji.util.thread;

import java.util.Locale;
import java.util.MissingFormatArgumentException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author : zhongmou.ji
 * @date : 2022/1/28 11:36 上午
 **/
public class StringManager {

    private static int LOCALE_CACHE_SIZE = 10;

    private final ResourceBundle bundle;
    private final Locale locale;

    private StringManager(String packageName, Locale locale) {
        String bundleName = packageName + ".LocalStrings";
        ResourceBundle bnd = null;
        try {
            if (locale.getLanguage().equals(Locale.ENGLISH.getLanguage())) {
                locale = Locale.ROOT;
            }
            bnd = ResourceBundle.getBundle(bundleName, locale);
        } catch (MissingFormatArgumentException ex) {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            if (cl != null) {
                try {
                    bnd = ResourceBundle.getBundle(bundleName, locale, cl);
                } catch (MissingFormatArgumentException ex2) {
                    // Ignore
                }
            }
        }

        bundle = bnd;

        if (bundle != null) {
            Locale bundleLocale = bundle.getLocale();
            if (bundleLocale.equals(Locale.ROOT)) {
                this.locale = Locale.ENGLISH;
            } else {
                this.locale = bundleLocale;
            }
        } else {
            this.locale = null;
        }
    }

    public static StringManager getManager(String s) {
        return null;
    }

    public String getString(String key) {
        if (key == null) {
            String msg = "key may not have a null value";
            throw new IllegalArgumentException(msg);
        }

        String str = null;

        try {
            if (bundle != null) {
                str = bundle.getString(key);
            }
        } catch (MissingResourceException mre) {
            str = null;
        }

        return str;
    }
}
