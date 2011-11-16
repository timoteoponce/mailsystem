/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.server;

import mailsystem.message.MailItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mailsystem.Folder;
import mailsystem.FolderFinder;
import mailsystem.FolderIterator;
import mailsystem.FolderName;
import mailsystem.User;

/**
 *
 * @author timoteo
 */
public class UserFolders {

    private static final String INBOX_NAME = "inbox";
    private static final String QUEUE_NAME = "queue";
    private static final String TRASH_NAME = "trash";
    private List<Folder> folders;

    public UserFolders() {
        folders = new ArrayList<Folder>();
    }

    Folder addFolder(FolderName folderName) {
        Folder folder = null;
        if (findFolder(folderName) == null) {
            folder = new Folder(folderName);
            folders.add(folder);
        }
        return folder;
    }

    MailItemsNumber countItemsBelongingTo(FolderName folderName) {
        Folder folder = findFolder(folderName);
        return folder.countItems();
    }

    MailItem getItemAndRemove(FolderName folderName) {
        Folder folder = findFolder(folderName);
        return folder.getNextItemAndRemove();
    }

    void add(FolderName folderName, MailItem item) {
        Folder folder = findFolder(folderName);
        folder.add(item);
    }

    void delete(FolderName folderName, MailItem mailItem) {
        Folder folder = findFolder(folderName);
        folder.delete(mailItem);
    }

    boolean hasMailItem(FolderName folderName, MailItem mailItem) {
        Folder folder = findFolder(folderName);
        return folder.hasMailItem(mailItem);
    }

    private Folder findFolder(FolderName folderName) {
        return new FolderFinder(this).find(folderName);
    }

    public void iterateItems(FolderIterator iterator) {
        Iterator<Folder> it = folders.iterator();
        boolean keepGoing = true;
        while (it.hasNext() && keepGoing) {
            iterator.process(it.next());
            keepGoing = iterator.keepGoing();
        }
    }

    MailItemsNumber countQueuedItemsBelongingTo(User user) {
        return countItemsBelongingTo(new FolderName(QUEUE_NAME,user));
    }

    MailItem getQueuedItem(User user) {
        return getItemAndRemove(new FolderName(QUEUE_NAME,user));
    }

    void addQueueItem(User user,MailItem item) {
        add(new FolderName(QUEUE_NAME,user), item);
        add(new FolderName(INBOX_NAME,user), item);
    }

    void delete(User user,MailItem mailItem) {
        delete(new FolderName(INBOX_NAME,user), mailItem);
        add(new FolderName(TRASH_NAME,user), mailItem);
    }

    boolean hasMailItemInInbox(User user,MailItem mailItem) {
        return hasMailItem(new FolderName(INBOX_NAME,user), mailItem);
    }

    boolean hasMailItemInTrash(User user,MailItem mailItem) {
        return hasMailItem(new FolderName(TRASH_NAME,user), mailItem);
    }

    boolean hasFolder(FolderName folderName) {
        return findFolder(folderName) != null;
    }

    void deleteFolder(FolderName folderName) {
        Folder folder = findFolder(folderName);
        if (!isInbox(folder) && !isTrash(folder)) {
            folders.remove(folder);
        }
    }

    private boolean isInbox(Folder folder) {
        return folder.isNamed(new FolderName(INBOX_NAME,null));
    }

    private boolean isTrash(Folder folder) {
        return folder.isNamed(new FolderName(TRASH_NAME,null));
    }

    void moveMailItem(FolderName folderName, MailItem mailItem) {
        Folder folder = findFolder(folderName);
        if (folder == null) {
            folder = addFolder(folderName);
        }
        folder.add(mailItem);
        removeFromOtherFolders(folder, mailItem);
    }

    boolean hasMailInFolder(FolderName folderName, MailItem mailItem) {
        return hasMailItem(folderName, mailItem);
    }

    private void removeFromOtherFolders(Folder sourceFolder, MailItem mailItem) {
        for (Folder folder : folders) {
            deleteFromFolder(sourceFolder, folder, mailItem);
        }
    }

    private void deleteFromFolder(Folder sourceFolder, Folder folder, MailItem mailItem) {
        if (!sourceFolder.equals(folder) && !isInbox(folder)) {
            folder.delete(mailItem);
        }
    }

    void addFolders(User user) {
        folders.add(new Folder(new FolderName(INBOX_NAME, user)));
        folders.add(new Folder(new FolderName(QUEUE_NAME, user)));
        folders.add(new Folder(new FolderName(TRASH_NAME, user)));
    }
}
