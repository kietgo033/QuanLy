package net.ultis;

import java.io.*;

public class DataFileUtils {

    private static final int CHUNK_SIZE = 1024;
    private final int BUFFER_SIZE = 100;


    public static void saveToDickImage(File file, InputStream inStream) {
        FileOutputStream fileOut = null;

        try {
            fileOut = new FileOutputStream(file);

            byte[] buffer = new byte[CHUNK_SIZE];
            int bytesRead;
            int pos = 0;
            while ((bytesRead = inStream.read(buffer, 0, CHUNK_SIZE)) > 0) {
                pos += bytesRead;
                System.out.println(pos + " bytes (" + bytesRead + " bytes read)");
                fileOut.write(buffer, 0, bytesRead);
                if (bytesRead < 1024) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
