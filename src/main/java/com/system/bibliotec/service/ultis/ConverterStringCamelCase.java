package com.system.bibliotec.service.ultis;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

public class ConverterStringCamelCase {


    public static String convertStringToCamelCase(String text) {
        return StringUtils.remove(WordUtils.capitalizeFully(text, ' '), " ");
    }


}
