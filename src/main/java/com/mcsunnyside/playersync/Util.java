package com.mcsunnyside.playersync;

import org.jetbrains.annotations.NotNull;

public class Util {
    /**
     * Convert strArray to String. E.g "Foo, Bar"
     *
     * @param strArray Target array
     * @return str
     */
    @NotNull
    public static String array2String(@NotNull String[] strArray) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strArray.length; i++) {
            builder.append(strArray[i]);
            if (i + 1 != strArray.length) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }
}
