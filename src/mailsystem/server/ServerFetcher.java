package mailsystem.server;

import mailsystem.FolderName;
import mailsystem.User;
import mailsystem.message.MailItem;

/**
 * A simple model of a mail server. The server is able to receive
 * mail items for storage, and deliver them to clients on demand.
 * @author David J. Barnes and Michael Kolling
 * @version 2006.03.30
 */
public class ServerFetcher extends BaseServer implements Fetcher {

    public ServerFetcher(UserFolders userFolders, AccountList accountList) {
        super(userFolders, accountList);
    }

    /**
     * Return how many mail items are waiting for a user.
     * @param who The user to check for.
     * @return How many items are waiting.
     */
    @Override
    public MailItemsNumber howManyMailItemsInQueue(User user) {
        return getUserFolders().countQueuedItemsBelongingTo(user);
    }

    /**
     * Return the next mail item for a user or null if there
     * are none.
     * @param who The user requesting their next item.
     * @return The user's next item.
     */
    @Override
    public MailItem getNextMailItem(User user) {
        return getUserFolders().getQueuedItem(user);
    }

    @Override
    public void deleteMailItem(User user, MailItem mailItem) {
        getUserFolders().delete(user, mailItem);
    }

    @Override
    public boolean hasMailInInbox(User user, MailItem mailItem) {
        return getUserFolders().hasMailItemInInbox(user, mailItem);
    }

    @Override
    public boolean hasMailInTrash(User user, MailItem mailItem) {
        return getUserFolders().hasMailItemInTrash(user, mailItem);
    }

    @Override
    public void createFolder(FolderName folderName) {
        getUserFolders().addFolder(folderName);
    }

    @Override
    public boolean hasFolder(FolderName folderName) {
        return getUserFolders().hasFolder(folderName);
    }

    @Override
    public void deleteFolder(FolderName folderName) {
        getUserFolders().deleteFolder(folderName);
    }

    @Override
    public void moveMailItem(FolderName folderName, MailItem mailItem) {
        getUserFolders().moveMailItem(folderName, mailItem);
    }

    @Override
    public boolean hasMailInFolder(FolderName folderName, MailItem mailItem) {
        return getUserFolders().hasMailInFolder(folderName, mailItem);
    }
}
