import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class DateTimeExamples {
    public static void main(String[] args) throws InterruptedException {

        long startTime = System.currentTimeMillis();
        // Default DateTime
        System.out.println("Default DateTime: " + LocalDateTime.now());

        // Custom DateTime format
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Custom DateTime: " + LocalDateTime.now().format(customFormat));

        // ISO Date
        System.out.println("ISO Date: " + LocalDate.now());

        // Short Date Format
        DateTimeFormatter shortDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        System.out.println("Short Date: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        // Medium DateTime Format

        DateTimeFormatter mediumDateTime = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        System.out.println("Medium DateTime: " + LocalDateTime.now().format(mediumDateTime));

        // Unix Epoch Time (Milliseconds since 1970)
        System.out.println("Unix Epoch Time: " + Instant.now().toEpochMilli());

        // 24-hour Time Format
        DateTimeFormatter timeFormat24 = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("Time (24-hour): " + LocalTime.now().format(timeFormat24));

        // 12-hour Time Format with AM/PM
        DateTimeFormatter timeFormat12 = DateTimeFormatter.ofPattern("hh:mm:ss a");
        System.out.println("Time (12-hour): " + LocalTime.now().format(timeFormat12));


// Some code...
        Thread.sleep(2000);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Elapsed time in milliseconds: " + elapsedTime);
    }
}
