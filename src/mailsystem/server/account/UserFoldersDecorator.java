/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.server.account;

import mailsystem.Folder;
import mailsystem.FolderName;
import mailsystem.User;
import mailsystem.message.MailItem;

/**
 *
 * @author timoteo
 */
public class UserFoldersDecorator extends UserFolders {

    static final String INBOX_NAME = "inbox";
    static final String QUEUE_NAME = "queue";
    static final String TRASH_NAME = "trash";

    public MailItemsNumber countQueuedItemsBelongingTo(User user) {
        return countItemsBelongingTo(new FolderName(QUEUE_NAME, user));
    }

    public void addQueueItem(User user, MailItem item) {
        add(new FolderName(QUEUE_NAME, user), item);
        add(new FolderName(INBOX_NAME, user), item);
    }

    public void delete(User user, MailItem mailItem) {
        delete(new FolderName(INBOX_NAME, user), mailItem);
        add(new FolderName(TRASH_NAME, user), mailItem);
    }

    public MailItem getQueuedItem(User user) {
        return getItemAndRemove(new FolderName(QUEUE_NAME, user));
    }

    public boolean hasMailItemInInbox(User user, MailItem mailItem) {
        return hasMailItem(new FolderName(INBOX_NAME, user), mailItem);
    }

    public boolean hasMailItemInTrash(User user, MailItem mailItem) {
        return hasMailItem(new FolderName(TRASH_NAME, user), mailItem);
    }

    private boolean isInbox(Folder folder) {
        return folder.isNamed(new FolderName(INBOX_NAME, null));
    }

    private boolean isTrash(Folder folder) {
        return folder.isNamed(new FolderName(TRASH_NAME, null));
    }

    public void moveMailItem(FolderName folderName, MailItem mailItem) {
        Folder folder = findFolder(folderName);
        if (folder == null) {
            folder = addFolder(folderName);
        }
        folder.add(mailItem);
        removeFromOtherFolders(folder, mailItem);
    }

    private void removeFromOtherFolders(Folder sourceFolder, MailItem mailItem) {
        DeleteFolderItemIterator iterator = new DeleteFolderItemIterator(this, sourceFolder, mailItem);
        iterateItems(iterator);
    }

    void deleteFromFolder(Folder sourceFolder, Folder folder, MailItem mailItem) {
        if (!sourceFolder.equals(folder) && !isInbox(folder)) {
            folder.delete(mailItem);
        }
    }

    public void deleteFolder(FolderName folderName) {
        Folder folder = findFolder(folderName);
        if (!isInbox(folder) && !isTrash(folder)) {
            delete(folder);
        }
    }

    public void addUserFolders(User user) {
        addFolder(new FolderName(INBOX_NAME, user));
        addFolder(new FolderName(QUEUE_NAME, user));
        addFolder(new FolderName(TRASH_NAME, user));
    }
}
