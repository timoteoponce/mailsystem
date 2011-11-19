/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.server.account;

import mailsystem.Folder;
import mailsystem.FolderIterator;
import mailsystem.message.MailItem;

/**
 *
 * @author timoteo
 */
public class DeleteFolderItemIterator implements FolderIterator {

    private final UserFoldersDecorator userFolders;
    private final Folder sourceFolder;
    private final MailItem mailItem;

    public DeleteFolderItemIterator(UserFoldersDecorator userFolders, Folder sourceFolder, MailItem mailItem) {
        this.userFolders = userFolders;
        this.sourceFolder = sourceFolder;
        this.mailItem = mailItem;
    }

    public void process(Folder folder) {
        userFolders.deleteFromFolder(sourceFolder, folder, mailItem);
    }

    public boolean keepGoing() {
        return true;
    }
}
