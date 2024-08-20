package it.scopped.duels.utility.strings;

import org.bukkit.Color;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Strings {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0");

    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static String formatNumber(long value) {
        if (value < 1000L)
            return String.valueOf(value);

        String[] units = {"", "k", "M", "B", "T", "Q"};
        int unitIndex = 0;
        double displayValue = value;

        while (displayValue >= 1000.0D && unitIndex < units.length - 1) {
            displayValue /= 1000.0D;
            unitIndex++;
        }

        long roundedValue = Math.round(displayValue);
        return roundedValue + units[unitIndex];
    }


    public static Color getColor(String value) {
        String[] rgb = value.split(";");
        return Color.fromRGB(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
    }

    public static String getCurrentDateFormatted() {
        return LocalDateTime.now(ZoneId.of("Europe/Rome"))
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public static String timer(long end) {
        return DECIMAL_FORMAT.format(Math.max(0.0D, (end - System.currentTimeMillis()) / 1000.0D));
    }

    public static String parseStringTime(long seconds) {
        long[] values = {seconds / 86400L, seconds % 86400L / 3600L, seconds % 3600L / 60L, seconds % 60L};
        String[] labels = {"d ", "h ", "m ", "s"};
        StringBuilder formattedTime = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            if (values[i] > 0L || !formattedTime.isEmpty())
                formattedTime.append(values[i]).append(labels[i]);
        }
        return formattedTime.toString();
    }

    public static long parseTimeString(String timeString) {
        long seconds = 0L;
        for (String part : timeString.split("\\s+"))
            seconds += Integer.parseInt(part.substring(0, part.length() - 1)) * (
                    part.endsWith("d") ? 86400L : (part.endsWith("h") ? 3600L : (part.endsWith("m") ? 60L : 1L)));
        return seconds;
    }
}