import java.io.*;
import java.util.*;

public class FileManager {
    private static final String FILE_NAME = "riwayat_topup.txt";

    // Menyimpan data (Modul 5)
    public static void save(Transaction t) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(t.toFileString());
            bw.newLine();
        } catch (IOException e) { e.printStackTrace(); }
    }

    // Mengambil data dan Sorting (Modul 4)
    public static List<String[]> load() {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.split(","));
            }
        } catch (IOException e) { }
        return data;
    }

    // Fitur Hapus Riwayat (CRUD - Delete)
    public static void delete(int index) {
        List<String[]> allData = load();
        if (index >= 0 && index < allData.size()) {
            allData.remove(index);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
                for (String[] row : allData) {
                    bw.write(String.join(",", row));
                    bw.newLine();
                }
            } catch (IOException e) { e.printStackTrace(); }
        }
    }
}