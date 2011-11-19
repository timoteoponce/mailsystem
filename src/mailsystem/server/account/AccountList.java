/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.server.account;

import java.util.HashSet;
import mailsystem.message.MailItem;
import mailsystem.client.MailClient;
import java.util.Set;
import mailsystem.User;

/**
 *
 * @author timoteo
 */
public class AccountList {

    private Set<User> validAccounts = new HashSet<User>();

    public boolean hasAccount(MailClient client) {
        for (User user : validAccounts) {
            if (client.isClientUser(user)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAccount(MailItem item) {
        for (User user : validAccounts) {
            if (item.isToUser(user)) {
                return true;
            }
        }
        return false;
    }

    public void add(User user) {
        validAccounts.add(user);
    }

    public boolean hasAccount(User otherUser) {
        for (User user : validAccounts) {
            if (otherUser.equals(user)) {
                return true;
            }
        }
        return false;
    }
}
