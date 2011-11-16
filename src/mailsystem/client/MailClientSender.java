/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.client;

import mailsystem.message.MailItem;
import mailsystem.message.MailMessage;
import mailsystem.server.MailServer;
import mailsystem.server.NotAuthorizedException;
import mailsystem.User;

/**
 *
 * @author timoteo
 */
public class MailClientSender extends MailClient {

    public MailClientSender(MailServer server, User user) throws NotAuthorizedException {
        super(server, user);
    }

    /**
     * Send the given message to the given recipient via
     * the attached mail server.
     * @param to The intended recipient.
     * @param message The text of the message to be sent.
     */
    public void sendMailItem(User to, MailMessage message) throws NotAuthorizedException {        
        final MailItem item = super.createMailItem(to, message);
        getServer().post(getUser(), item);
    }
}
