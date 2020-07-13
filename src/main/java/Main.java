import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import db.Data;
import db.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String fileSeparator = System.getProperty("file.separator");
        File csvFile = readFilePath();
        String csvFileDir = csvFile.getParent();
        String badDataFileName = csvFileDir + fileSeparator + "bad-data-" + System.currentTimeMillis() + ".csv";
        String logFileName = csvFileDir + fileSeparator + "log.csv";
        File badDataFile = new File(badDataFileName);
        CSVReader csvReader;
        CSVWriter csvWriter;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            badDataFile.createNewFile();
            transaction = session.beginTransaction();
            csvReader = new CSVReader(new FileReader(csvFile));
            csvWriter = new CSVWriter(new FileWriter(badDataFile));
            String[] csvLine;
            List<String[]> badDataList = new ArrayList<>();
            int receivedRowsCount = 0;
            int successfulRowsCount = 0;
            int failedRowsCount = 0;
            while ((csvLine = csvReader.readNext()) != null) {
                if (csvLine.length == 10) {
                    if (Arrays.stream(csvLine).anyMatch(String::isEmpty)) {
                        badDataList.add(csvLine);
                        failedRowsCount++;
                    } else {
                        Data data = createData(csvLine);
                        session.save(data);
                        successfulRowsCount++;
                    }
                    receivedRowsCount++;
                }
            }
            csvWriter.writeAll(badDataList, false);
            csvWriter.close();
            csvReader.close();
            transaction.commit();
            log(receivedRowsCount, successfulRowsCount, failedRowsCount, logFileName);
        } catch (IOException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    private static File readFilePath() {
        Scanner scanner = new Scanner(System.in);
        File csvFilePath = new File(scanner.nextLine());
        while (!csvFilePath.exists()) {
            csvFilePath = new File(scanner.nextLine());
        }
        return csvFilePath;
    }

    private static void log(int received, int success, int failed, String logFilePath) throws IOException {
        File logFile = new File(logFilePath);
        if (!logFile.exists()) {
            logFile.createNewFile();
        }
        CSVWriter csvWriter = new CSVWriter(new FileWriter(logFile));
        String[] logText = {
                received + " of records received",
                success + " of records successful",
                failed + " of records failed"};
        csvWriter.writeNext(logText);
        csvWriter.close();
    }

    private static Data createData(String[] line) {
        return new Data.Builder()
                .setFirstName(line[0])
                .setLastName(line[1])
                .setEmail(line[2])
                .setSex(line[3])
                .setImagePath(line[4])
                .setPayment(line[5])
                .setAmount(line[6])
                .setIsActive(line[7])
                .setIsSuccessful(line[8])
                .setCity(line[9])
                .build();
    }
}
