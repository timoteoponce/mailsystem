/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.message;

import java.io.IOException;
import mailsystem.FileHandler;

/**
 *
 * @author timoteo
 */
class Attach {

    private FileBytes fileBytes;
    private FileName filename;

    Attach(FileName attachFile) throws IOException {
        this.filename = attachFile;
        fileBytes = FileHandler.loadFile(filename);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        final Attach other = (Attach) obj;
        return fileBytes.equals(other.fileBytes) && filename.equals(other.filename);
    }
}
