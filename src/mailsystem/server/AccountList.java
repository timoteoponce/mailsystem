/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.server;

import mailsystem.message.MailItem;
import mailsystem.client.MailClient;
import java.util.ArrayList;
import java.util.List;
import mailsystem.User;

/**
 *
 * @author timoteo
 */
public class AccountList {

    private List<User> validAccounts = new ArrayList<User>();

    boolean hasAccount(MailClient client) {
        for (User user : validAccounts) {
            if (client.isUser(user)) {
                return true;
            }
        }
        return false;
    }
    
    boolean hasAccount(MailItem item) {
        for (User user : validAccounts) {
            if (item.isToUser(user)) {
                return true;
            }
        }
        return false;
    }

    void add(User user) {
        validAccounts.add(user);
    }

    boolean hasAccount(User otherUser) {
        for (User user : validAccounts) {
            if (otherUser.equals(user)) {
                return true;
            }
        }
        return false;
    }
}
