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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FileName other = (FileName) obj;
        final String otherFileName = other.filename;
        if (this.filename == null) {
            return (other.filename != null);
        }
        return this.filename.equals(other.filename);
    }

    @Override
    public String toString() {
        return filename.toString();
    }
}
