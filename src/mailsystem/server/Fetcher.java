/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.server;

import mailsystem.FolderName;
import mailsystem.User;
import mailsystem.message.MailItem;

/**
 *
 * @author timoteo
 */
public interface Fetcher extends AuthenticationServer {

    void createFolder(FolderName folderName);

    void deleteFolder(FolderName folderName);

    void deleteMailItem(User user, MailItem mailItem);

    /**
     * Return the next mail item for a user or null if there
     * are none.
     * @param who The user requesting their next item.
     * @return The user's next item.
     */
    MailItem getNextMailItem(User user);

    boolean hasFolder(FolderName folderName);

    boolean hasMailInFolder(FolderName folderName, MailItem mailItem);

    boolean hasMailInInbox(User user, MailItem mailItem);

    boolean hasMailInTrash(User user, MailItem mailItem);

    /**
     * Return how many mail items are waiting for a user.
     * @param who The user to check for.
     * @return How many items are waiting.
     */
    MailItemsNumber howManyMailItemsInQueue(User user);

    void moveMailItem(FolderName folderName, MailItem mailItem);
}
