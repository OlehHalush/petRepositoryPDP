package file;

import java.io.*;

public class ReadingFile {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/java/file/example.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter("writer_example.txt"));
        writer.write("Hello");
        writer.write("\nOleh");
        writer.write("\nIt's");
        writer.write("\nMe");
        writer.close();
    }
}
