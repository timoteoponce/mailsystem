/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.server;

import mailsystem.User;
import mailsystem.message.MailItem;

/**
 *
 * @author timoteo
 */
public class ServerDispatcher extends BaseServer implements Dispatcher {

    public ServerDispatcher(UserFolders userFolders, AccountList accountList) {
        super(userFolders, accountList);
    }

    /**
     * Add the given mail item to the message list.
     * @param item The mail item to be stored on the server.
     */
    @Override
    public void post(User user, MailItem item) throws NotAuthorizedException {
        authenticate(item);
        getUserFolders().addQueueItem(user, item);
    }
}
