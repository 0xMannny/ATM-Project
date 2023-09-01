import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Creating a text File using FileWriter
class FileWriterReader
{
    public File writeFileMethod(String content, String fileName) throws IOException {
        File file =new File(fileName);
        file.createNewFile();
        FileWriter writer = new FileWriter(file);

        writer.write(content);
        writer.flush();
        writer.close();
        return file;
    }

    public String readFileMethod(File file) throws IOException {
        String output = "";
        try ( final Scanner scanner = new Scanner(file); ) {
            while ( scanner.hasNextLine() ) {
                String line = scanner.nextLine();
                output += "\n" + line;
            }
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        }
        return output;
    }
}