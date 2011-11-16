/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.client;

import mailsystem.server.MailServer;
import mailsystem.server.NotAuthorizedException;
import mailsystem.User;

/**
 *
 * @author timoteo
 */
public interface MailClientVoidAction {

    void execute(User user, MailServer server) throws NotAuthorizedException;
}
