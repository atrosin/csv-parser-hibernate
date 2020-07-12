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
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File fullPath = new File(scanner.nextLine());
        String csvFileDir = fullPath.getParent();
        String badDataFileName = csvFileDir + FILE_SEPARATOR + "bad-data-" + System.currentTimeMillis() + ".csv";
        String logFileName = csvFileDir + FILE_SEPARATOR + "log.csv";
        File file = new File(badDataFileName);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            file.createNewFile();

            transaction = session.beginTransaction();

            CSVReader csvReader = new CSVReader(new FileReader(fullPath));
            CSVWriter csvWriter = new CSVWriter(new FileWriter(file));
            String[] line;
            List<String[]> list = new ArrayList<>();
            int receivedRowsCount = 0;
            int successfulRowsCount = 0;
            int failedRowsCount = 0;
            while ((line = csvReader.readNext()) != null) {
                if (line.length == 10) {
                    if (Arrays.stream(line).anyMatch(String::isEmpty)) {
                        list.add(line);
                        failedRowsCount++;
                    } else {
                        Data data = createData(line);
                        session.save(data);
                        successfulRowsCount++;
                    }
                    receivedRowsCount++;
                }
            }
            csvWriter.writeAll(list);
            csvWriter.close();
            csvReader.close();
            transaction.commit();
            log(receivedRowsCount, successfulRowsCount, failedRowsCount, logFileName);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    private static void log(int received, int success, int failed, String logFilePath) throws IOException {
        File logFile = new File(logFilePath);
        if (!logFile.exists()){
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
