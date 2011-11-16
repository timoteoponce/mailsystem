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
        fileBytes = new FileHandler().loadFile(filename);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Attach other = (Attach) obj;
        return fileBytes.equals(other.fileBytes) && filename.equals(other.filename);
    }
}
