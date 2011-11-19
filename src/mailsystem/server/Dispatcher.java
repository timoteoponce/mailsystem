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
public interface Dispatcher extends AuthenticationServer{

    /**
     * Add the given mail item to the message list.
     * @param item The mail item to be stored on the server.
     */
    void post(User user, MailItem item) throws NotAuthorizedException;
    
}
