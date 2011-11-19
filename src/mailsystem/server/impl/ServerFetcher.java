package mailsystem.server.impl;

import mailsystem.server.account.AccountList;
import mailsystem.server.account.UserFolders;
import mailsystem.FolderName;
import mailsystem.User;
import mailsystem.message.MailItem;
import mailsystem.server.Fetcher;
import mailsystem.server.account.MailItemsNumber;
import mailsystem.server.account.UserFoldersDecorator;

/**
 * A simple model of a mail server. The server is able to receive
 * mail items for storage, and deliver them to clients on demand.
 * @author David J. Barnes and Michael Kolling
 * @version 2006.03.30
 */
public class ServerFetcher extends BaseServer implements Fetcher {

    private UserFoldersDecorator userFolders;

    public ServerFetcher(UserFoldersDecorator userFolders, AccountList accountList) {
        super(userFolders, accountList);
        this.userFolders = userFolders;
    }

    /**
     * Return how many mail items are waiting for a user.
     * @param who The user to check for.
     * @return How many items are waiting.
     */
    @Override
    public MailItemsNumber howManyMailItemsInQueue(User user) {
        return userFolders.countQueuedItemsBelongingTo(user);
    }

    /**
     * Return the next mail item for a user or null if there
     * are none.
     * @param who The user requesting their next item.
     * @return The user's next item.
     */
    @Override
    public MailItem getNextMailItem(User user) {
        return userFolders.getQueuedItem(user);
    }

    @Override
    public void deleteMailItem(User user, MailItem mailItem) {
        userFolders.delete(user, mailItem);
    }

    @Override
    public boolean hasMailInInbox(User user, MailItem mailItem) {
        return userFolders.hasMailItemInInbox(user, mailItem);
    }

    @Override
    public boolean hasMailInTrash(User user, MailItem mailItem) {
        return userFolders.hasMailItemInTrash(user, mailItem);
    }

    @Override
    public void createFolder(FolderName folderName) {
        userFolders.addFolder(folderName);
    }

    @Override
    public boolean hasFolder(FolderName folderName) {
        return userFolders.hasFolder(folderName);
    }

    @Override
    public void deleteFolder(FolderName folderName) {
        userFolders.deleteFolder(folderName);
    }

    @Override
    public void moveMailItem(FolderName folderName, MailItem mailItem) {
        userFolders.moveMailItem(folderName, mailItem);
    }

    @Override
    public boolean hasMailInFolder(FolderName folderName, MailItem mailItem) {
        return userFolders.hasMailInFolder(folderName, mailItem);
    }
}
