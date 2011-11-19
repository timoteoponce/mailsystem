/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.server.impl;

import mailsystem.server.account.AccountList;
import mailsystem.server.account.UserFolders;
import mailsystem.User;
import mailsystem.message.MailItem;
import mailsystem.server.Dispatcher;
import mailsystem.server.NotAuthorizedException;
import mailsystem.server.account.UserFoldersDecorator;

/**
 *
 * @author timoteo
 */
public class ServerDispatcher extends BaseServer implements Dispatcher {

    private UserFoldersDecorator userFolders; 
    
    public ServerDispatcher(UserFoldersDecorator userFolders, AccountList accountList) {
        super(userFolders, accountList);
        this.userFolders = userFolders;
    }

    /**
     * Add the given mail item to the message list.
     * @param item The mail item to be stored on the server.
     */
    @Override
    public void post(User user, MailItem item) throws NotAuthorizedException {
        authenticate(item);
        userFolders.addQueueItem(user, item);
    }
}
