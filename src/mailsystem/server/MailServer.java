package mailsystem.server;

import mailsystem.FolderName;
import mailsystem.User;
import mailsystem.message.MailItem;
import mailsystem.client.MailClient;

/**
 * A simple model of a mail server. The server is able to receive
 * mail items for storage, and deliver them to clients on demand.
 * @author David J. Barnes and Michael Kolling
 * @version 2006.03.30
 */
public class MailServer {
    // Storage for the arbitrary number of mail items to be stored
    // on the server.

    private UserFolders userFolders;
    private AccountList accountList;

    /**
     * Construct a mail server.
     */
    public MailServer() {
        userFolders = new UserFolders();
        accountList = new AccountList();        
    }

    /**
     * Return how many mail items are waiting for a user.
     * @param who The user to check for.
     * @return How many items are waiting.
     */
    public MailItemsNumber howManyMailItemsInQueue(User user) {
        return userFolders.countQueuedItemsBelongingTo(user);
    }

    /**
     * Return the next mail item for a user or null if there
     * are none.
     * @param who The user requesting their next item.
     * @return The user's next item.
     */
    public MailItem getNextMailItem(User user) {
        return userFolders.getQueuedItem(user);
    }

    /**
     * Add the given mail item to the message list.
     * @param item The mail item to be stored on the server.
     */
    public void post(User user,MailItem item) throws NotAuthorizedException {
        boolean hasAccount = accountList.hasAccount(item);
        if (!hasAccount) {
            throw new NotAuthorizedException();
        }
        userFolders.addQueueItem(user,item);
    }

    public void authenticate(MailClient client) throws NotAuthorizedException {
        boolean hasAccount = accountList.hasAccount(client);
        if (!hasAccount) {
            throw new NotAuthorizedException();
        }
    }

    public void addAccount(User user) {
        accountList.add(user);
        userFolders.addFolders(user);
    }

    public void authenticate(User to) throws NotAuthorizedException {
        boolean hasAccount = accountList.hasAccount(to);
        if (!hasAccount) {
            throw new NotAuthorizedException();
        }
    }

    public void deleteMailItem(User user,MailItem mailItem) {
        userFolders.delete(user,mailItem);
    }

    public boolean hasMailInInbox(User user,MailItem mailItem) {
        return userFolders.hasMailItemInInbox(user,mailItem);
    }

    public boolean hasMailInTrash(User user,MailItem mailItem) {
        return userFolders.hasMailItemInTrash(user,mailItem);
    }

    public void createFolder(FolderName folderName) {
        userFolders.addFolder(folderName);
    }

    public boolean hasFolder(FolderName folderName) {
        return userFolders.hasFolder(folderName);
    }

    public void deleteFolder(FolderName folderName) {
        userFolders.deleteFolder(folderName);
    }

    public void moveMailItem(FolderName folderName, MailItem mailItem) {
        userFolders.moveMailItem(folderName,mailItem);
    }

    public boolean hasMailInFolder(FolderName folderName, MailItem mailItem) {
        return userFolders.hasMailInFolder(folderName,mailItem);
    }
}
