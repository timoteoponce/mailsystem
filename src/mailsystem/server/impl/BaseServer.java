/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.server.impl;

import mailsystem.server.account.AccountList;
import mailsystem.server.account.UserFolders;
import mailsystem.User;
import mailsystem.message.MailItem;
import mailsystem.server.AuthenticationServer;
import mailsystem.server.NotAuthorizedException;
import mailsystem.server.account.UserFoldersDecorator;

/**
 *
 * @author timoteo
 */
public class BaseServer implements AuthenticationServer {
    // on the server.

    private UserFoldersDecorator userFolders;
    private AccountList accountList;

    public BaseServer(UserFoldersDecorator userFolders, AccountList accountList) {
        this.userFolders = userFolders;
        this.accountList = accountList;
    }

    public void addAccount(User user) {
        accountList.add(user);
        userFolders.addUserFolders(user);
    }

    public void authenticate(User user) throws NotAuthorizedException {
        boolean hasAccount = accountList.hasAccount(user);
        validateAuthentication(hasAccount);
    }

    public void authenticate(MailItem item) throws NotAuthorizedException {
        boolean hasAccount = accountList.hasAccount(item);
        validateAuthentication(hasAccount);
    }

    private void validateAuthentication(boolean hasAccount) throws NotAuthorizedException {
        if (!hasAccount) {
            throw new NotAuthorizedException();
        }
    }
}
