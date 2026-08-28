package mu.rekolt.service;

import mu.rekolt.model.Delivery;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class DocumentGenerator {
    private static final String OUTPUT_PATH = "output/season-report.docx";

    public static void generateSeasonReport() {
        try (XWPFDocument document = new XWPFDocument()) {

            TreeSet<String> sortedMemberIds = new TreeSet<>(SeasonReporting.memberIds);
            double seasonTotal = 0.0;
            for (String memberId : sortedMemberIds) {
                seasonTotal += SeasonReporting.totalPaymentByMember.getOrDefault(memberId, 0.0);
            }

            writeSummarySection(document, sortedMemberIds);

            for (String memberId : sortedMemberIds) {
                List<Delivery> memberDeliveries = SeasonReporting.deliveriesByMember.get(memberId);
                double memberTotal = SeasonReporting.totalPaymentByMember.getOrDefault(memberId, 0.0);

                XWPFParagraph pageBreak = document.createParagraph();
                pageBreak.createRun().addBreak(BreakType.PAGE);

                writeMemberSection(document, memberId, memberDeliveries, memberTotal);
            }

            writeClosingSection(document, seasonTotal);

            try (FileOutputStream out = new FileOutputStream(OUTPUT_PATH)) {
                document.write(out);
            }

            RunLog.append(sortedMemberIds.size());
            System.out.println("Writing " + OUTPUT_PATH + " ... " + sortedMemberIds.size() + " member sections, done.");

        } catch (IOException e) {
            System.out.println("Could not write the season report. Check that the 'output' folder exists "
                    + "and is not open in another program, then try again. (" + e.getMessage() + ")");
        }
    }

    private static void writeSummarySection(XWPFDocument document, Set<String> sortedMemberIds) {
        heading(document, "Season Report", 16);

        heading(document, "Total payment per member (MUR)", 12);
        XWPFTable memberTable = document.createTable(sortedMemberIds.size() + 1, 2);
        setRow(memberTable, 0, "Member", "Total payment (MUR)");
        int row = 1;
        for (String memberId : sortedMemberIds) {
            double total = SeasonReporting.totalPaymentByMember.getOrDefault(memberId, 0.0);
            setRow(memberTable, row++, memberId, String.format("%,.2f", total));
        }

        heading(document, "Weekly volume grid (kg)", 12);
        XWPFTable gridTable = document.createTable(21, 5);
        setRow(gridTable, 0, "Week", "MZE", "BNS", "POT", "TEA");
        for (int week = 0; week < 20; week++) {
            setRow(gridTable, week + 1,
                    String.valueOf(week + 1),
                    String.format("%.1f", SeasonReporting.weeklyGrid[week][0]),
                    String.format("%.1f", SeasonReporting.weeklyGrid[week][1]),
                    String.format("%.1f", SeasonReporting.weeklyGrid[week][2]),
                    String.format("%.1f", SeasonReporting.weeklyGrid[week][3]));
        }

        heading(document, "Top five deliveries by value", 12);
        List<Delivery> top = SeasonReporting.topDeliveriesByValue(5);
        XWPFTable topTable = document.createTable(top.size() + 1, 6);
        setRow(topTable, 0, "Rank", "Delivery ID", "Member", "Produce", "Mass (kg)", "Net payable (MUR)");
        for (int i = 0; i < top.size(); i++) {
            Delivery d = top.get(i);
            setRow(topTable, i + 1,
                    String.valueOf(i + 1),
                    d.getDelivery_id(),
                    d.getMember_id(),
                    d.getProduce_code(),
                    String.format("%.1f", d.getProduce_mass()),
                    String.format("%,.2f", d.getNetPayableValue()));
        }
    }

    private static void heading(XWPFDocument document, String text, int fontSize) {
        XWPFParagraph para = document.createParagraph();
        XWPFRun run = para.createRun();
        run.setBold(true);
        run.setFontSize(fontSize);
        run.setText(text);
    }

    private static void writeMemberSection(XWPFDocument document, String memberId,
                                           List<Delivery> deliveries, double memberTotal) {

        String memberName = deliveries.isEmpty() ? "" : deliveries.getFirst().getMember_name();

        XWPFParagraph heading = document.createParagraph();
        XWPFRun headingRun = heading.createRun();
        headingRun.setBold(true);
        headingRun.setFontSize(14);
        headingRun.setText(memberId + " - " + memberName);

        XWPFTable table = document.createTable(deliveries.size() + 1, 5);
        setRow(table, 0, "Delivery ID", "Produce", "Mass (kg)", "Grade", "Net Payable (MUR)");

        double totalCommission = 0.0;
        double totalLevy = 0.0;

        for (int i = 0; i < deliveries.size(); i++) {
            Delivery d = deliveries.get(i);
            setRow(table, i + 1,
                    d.getDelivery_id(),
                    d.getProduce_code(),
                    String.format("%.1f", d.getProduce_mass()),
                    d.getGrade(),
                    String.format("%,.2f", d.getNetPayableValue()));
            totalCommission += d.getCommissionValue();
            totalLevy += d.getTransportLevyValue();
        }

        addLine(document, String.format("Commission: %,.2f MUR", totalCommission));
        addLine(document, String.format("Transport levy: %,.2f MUR", totalLevy));

        XWPFParagraph netPara = document.createParagraph();
        XWPFRun netRun = netPara.createRun();
        netRun.setBold(true);
        netRun.setText(String.format("NET PAYABLE: %,.2f MUR", memberTotal));

        addLine(document, " ");
        addLine(document, "Signature: _______________________________");
    }

    private static void writeClosingSection(XWPFDocument document, double seasonTotal) {
        XWPFParagraph pageBreak = document.createParagraph();
        pageBreak.createRun().addBreak(BreakType.PAGE);

        XWPFParagraph heading = document.createParagraph();
        XWPFRun headingRun = heading.createRun();
        headingRun.setBold(true);
        headingRun.setFontSize(14);
        headingRun.setText("Season totals");

        XWPFParagraph totalPara = document.createParagraph();
        XWPFRun totalRun = totalPara.createRun();
        totalRun.setBold(true);
        totalRun.setText(String.format("TOTAL PAID THIS SEASON: %,.2f MUR", seasonTotal));
    }

    private static void setRow(XWPFTable table, int rowIndex, String... values) {
        XWPFTableRow row = table.getRow(rowIndex);
        for (int col = 0; col < values.length; col++) {
            row.getCell(col).setText(values[col]);
        }
    }

    private static void addLine(XWPFDocument document, String text) {
        document.createParagraph().createRun().setText(text);
    }
}
