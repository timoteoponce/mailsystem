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
public class ServerFacade implements Fetcher,Dispatcher{
    
    private ServerFetcher fetcher;
    private ServerDispatcher dispatcher;

    public ServerFacade() {
        UserFolders userFolders = new UserFolders();
        AccountList accountList = new AccountList();
        fetcher = new ServerFetcher(userFolders, accountList);
        dispatcher = new ServerDispatcher(userFolders, accountList);
    }
    

    public void createFolder(FolderName folderName) {
        fetcher.createFolder(folderName);
    }

    public void deleteFolder(FolderName folderName) {
        fetcher.deleteFolder(folderName);
    }

    public void deleteMailItem(User user, MailItem mailItem) {
        fetcher.deleteMailItem(user, mailItem);
    }

    public MailItem getNextMailItem(User user) {
        return fetcher.getNextMailItem(user);
    }

    public boolean hasFolder(FolderName folderName) {
        return fetcher.hasFolder(folderName);
    }

    public boolean hasMailInFolder(FolderName folderName, MailItem mailItem) {
        return fetcher.hasMailInFolder(folderName, mailItem);
    }

    public boolean hasMailInInbox(User user, MailItem mailItem) {
        return fetcher.hasMailInInbox(user, mailItem);
    }

    public boolean hasMailInTrash(User user, MailItem mailItem) {
        return fetcher.hasMailInTrash(user, mailItem);
    }

    public MailItemsNumber howManyMailItemsInQueue(User user) {
        return fetcher.howManyMailItemsInQueue(user);
    }

    public void moveMailItem(FolderName folderName, MailItem mailItem) {
        fetcher.moveMailItem(folderName, mailItem);
    }

    public void post(User user, MailItem item) throws NotAuthorizedException {
        dispatcher.post(user, item);
    }

    public void addAccount(User user) {
        fetcher.addAccount(user);
    }

    public void authenticate(User user) throws NotAuthorizedException {
        dispatcher.authenticate(user);
    }
    
}
