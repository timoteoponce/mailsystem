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
        return executeAction(new MailClientAction<MailItem>() {

            public MailItem execute(User user, MailServer server) throws NotAuthorizedException {
                return server.getNextMailItem(user);
            }
        });
    }

    public void deleteMailItem(final MailItem mailItem) throws NotAuthorizedException {
        executeAction(new MailClientVoidAction() {

            public void execute(User user, MailServer server) throws NotAuthorizedException {
                server.deleteMailItem(user, mailItem);
            }
        });
    }

    public boolean hasMailInInbox(final MailItem mailItem) throws NotAuthorizedException {
        return executeAction(new MailClientAction<Boolean>() {

            public Boolean execute(User user, MailServer server) throws NotAuthorizedException {
                return server.hasMailInInbox(user, mailItem);
            }
        });
    }

    public boolean hasMailTrash(final MailItem mailItem) throws NotAuthorizedException {
        return executeAction(new MailClientAction<Boolean>() {

            public Boolean execute(User user, MailServer server) throws NotAuthorizedException {
                return server.hasMailInTrash(user, mailItem);
            }
        });

    }

    public void createFolder(final FolderName folderName) throws NotAuthorizedException {
        executeAction(new MailClientVoidAction() {

            public void execute(User user, MailServer server) throws NotAuthorizedException {
                server.createFolder(folderName);
            }
        });
    }

    public boolean hasFolder(final FolderName folderName) throws NotAuthorizedException {
        return executeAction(new MailClientAction<Boolean>() {

            public Boolean execute(User user, MailServer server) throws NotAuthorizedException {
                return server.hasFolder(folderName);
            }
        });
    }

    public void deleteFolder(final FolderName folderName) throws NotAuthorizedException {
        executeAction(new MailClientVoidAction() {

            public void execute(User user, MailServer server) throws NotAuthorizedException {
                server.deleteFolder(folderName);
            }
        });
    }

    public void moveMailItem(final FolderName folderName, final MailItem mailItem) throws NotAuthorizedException {
        executeAction(new MailClientVoidAction() {

            public void execute(User user, MailServer server) throws NotAuthorizedException {
                server.moveMailItem(folderName, mailItem);
            }
        });
    }

    public boolean hasMailInFolder(final FolderName folderName, final MailItem mailItem) throws NotAuthorizedException {
        return executeAction(new MailClientAction<Boolean>() {

            public Boolean execute(User user, MailServer server) throws NotAuthorizedException {
                return server.hasMailInFolder(folderName, mailItem);
            }
        });
    }
}
