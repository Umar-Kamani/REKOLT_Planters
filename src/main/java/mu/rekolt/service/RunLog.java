package mu.rekolt.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class RunLog {

    private static final String LOG_PATH = "output/run-log.txt";

    public static void append(int memberSectionCount) {
        try (FileWriter fw = new FileWriter(LOG_PATH, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(LocalDateTime.now() + " - season-report.docx generated, "
                    + memberSectionCount + " member sections.");
        } catch (IOException e) {
            System.out.println("Could not write to the run log. (" + e.getMessage() + ")");
        }
    }
}