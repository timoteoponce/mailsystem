/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.server.account;

import mailsystem.message.MailItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mailsystem.Folder;
import mailsystem.FolderFinder;
import mailsystem.FolderIterator;
import mailsystem.FolderName;

/**
 *
 * @author timoteo
 */
public class UserFolders {
    
    private List<Folder> folders;
    
    protected UserFolders() {
        folders = new ArrayList<Folder>();
    }
    
    public Folder addFolder(FolderName folderName) {
        Folder folder = null;
        if (findFolder(folderName) == null) {
            folder = new Folder(folderName);
            folders.add(folder);
        }
        return folder;
    }
    
    public MailItemsNumber countItemsBelongingTo(FolderName folderName) {
        Folder folder = findFolder(folderName);
        return folder.countItems();
    }
    
    public void add(FolderName folderName, MailItem item) {
        Folder folder = findFolder(folderName);
        folder.add(item);
    }
    
    public void delete(FolderName folderName, MailItem mailItem) {
        Folder folder = findFolder(folderName);
        folder.delete(mailItem);
    }
    
    public boolean hasMailItem(FolderName folderName, MailItem mailItem) {
        Folder folder = findFolder(folderName);
        return folder.hasMailItem(mailItem);
    }
    
    Folder findFolder(FolderName folderName) {
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
    
    public MailItem getItemAndRemove(FolderName folderName) {
        Folder folder = findFolder(folderName);
        return folder.getNextItemAndRemove();
    }
    
    public boolean hasFolder(FolderName folderName) {
        return findFolder(folderName) != null;
    }
    
    public boolean hasMailInFolder(FolderName folderName, MailItem mailItem) {
        return hasMailItem(folderName, mailItem);
    }
    
    protected void delete(Folder folder) {
        folders.remove(folder);
    }
}
