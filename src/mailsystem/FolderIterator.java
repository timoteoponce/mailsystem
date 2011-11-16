/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mailsystem;

/**
 *
 * @author timoteo
 */
public interface FolderIterator {

    void process(Folder next);

    boolean keepGoing();

}
