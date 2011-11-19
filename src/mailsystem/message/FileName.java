/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.message;

/**
 *
 * @author timoteo
 */
public class FileName {

    private String filename = "";

    public FileName(String filename) {
        this.filename = filename;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        final FileName other = (FileName) obj;
        final String otherFileName = other.filename;
        if (this.filename == null) {
            return (otherFileName != null);
        }
        return filename.equals(otherFileName);
    }

    @Override
    public String toString() {
        return filename.toString();
    }
}
