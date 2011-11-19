/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem;

import mailsystem.message.FileName;
import mailsystem.message.FileBytes;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author timoteo
 */
public class FileHandler {

    public static FileBytes loadFile(FileName filename) throws IOException {
        FileInputStream fileInput = new FileInputStream(filename.toString());
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            readBytes(fileInput, byteStream, buffer);
        } finally {
            fileInput.close();
        }
        return new FileBytes(byteStream.toByteArray());
    }

    private static void readBytes(FileInputStream fileInput, OutputStream byteStream, byte[] buffer) throws IOException {
        while (fileInput.read(buffer) > 0) {
            byteStream.write(buffer);
        }
    }
}
