/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.server;

import mailsystem.User;
import mailsystem.client.MailClient;
import mailsystem.message.MailItem;

/**
 *
 * @author timoteo
 */
public class BaseServer implements  AuthenticationServer{
    // on the server.

    private UserFolders userFolders;
    private AccountList accountList;

    public BaseServer(UserFolders userFolders, AccountList accountList) {
        this.userFolders = userFolders;
        this.accountList = accountList;
    }

//    public void authenticate(MailClient client) throws NotAuthorizedException {
//        boolean hasAccount = accountList.hasAccount(client);
//        validateAuthentication(hasAccount);
//    }

    public void addAccount(User user) {
        accountList.add(user);
        userFolders.addFolders(user);
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

    protected UserFolders getUserFolders() {
        return userFolders;
    }
}
