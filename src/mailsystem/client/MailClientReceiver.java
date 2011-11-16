/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.client;

import mailsystem.FolderName;
import mailsystem.message.MailItem;
import mailsystem.server.MailServer;
import mailsystem.server.NotAuthorizedException;
import mailsystem.User;

/**
 *
 * @author timoteo
 */
public class MailClientReceiver extends MailClient {

    public MailClientReceiver(MailServer server, User user) throws NotAuthorizedException {
        super(server, user);
    }

    /**
     * Return the next mail item (if any) for this user.
     */
    public MailItem getNextMailItem() throws NotAuthorizedException {
        return getServer().getNextMailItem(getUser());
    }

    public void deleteMailItem(final MailItem mailItem) throws NotAuthorizedException {
        getServer().deleteMailItem(getUser(), mailItem);
    }

    public boolean hasMailInInbox(final MailItem mailItem) throws NotAuthorizedException {
        return getServer().hasMailInInbox(getUser(), mailItem);
    }

    public boolean hasMailTrash(final MailItem mailItem) throws NotAuthorizedException {
        return getServer().hasMailInTrash(getUser(), mailItem);
    }

    public void createFolder(final FolderName folderName) throws NotAuthorizedException {
        getServer().createFolder(folderName);
    }

    public boolean hasFolder(final FolderName folderName) throws NotAuthorizedException {
        return getServer().hasFolder(folderName);
    }

    public void deleteFolder(final FolderName folderName) throws NotAuthorizedException {
        getServer().deleteFolder(folderName);
    }

    public void moveMailItem(final FolderName folderName, final MailItem mailItem) throws NotAuthorizedException {
        getServer().moveMailItem(folderName, mailItem);
    }

    public boolean hasMailInFolder(final FolderName folderName, final MailItem mailItem) throws NotAuthorizedException {
        return getServer().hasMailInFolder(folderName, mailItem);
    }
}
