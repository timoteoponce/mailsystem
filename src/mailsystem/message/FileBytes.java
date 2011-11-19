/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.message;

import java.util.Arrays;

/**
 *
 * @author Miranda
 */
public class FileBytes {

    private byte[] attach;

    public FileBytes(byte[] n) {
        attach = n;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        final FileBytes other = (FileBytes) obj;
        byte[] otherAttach = other.attach;
        return Arrays.equals(attach, otherAttach);
    }
}
