/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.client;

import mailsystem.message.MailItem;
import mailsystem.message.MailMessage;
import mailsystem.server.NotAuthorizedException;
import mailsystem.User;
import mailsystem.server.Dispatcher;

/**
 *
 * @author timoteo
 */
public class MailClientSender extends MailClient {

    private Dispatcher server;
    // The user running this client.
    private User user;

    public MailClientSender(Dispatcher server, User user) throws NotAuthorizedException {
        super(server, user);
        this.server = server;
        this.user = user;
    }

    /**
     * Send the given message to the given recipient via
     * the attached mail server.
     * @param to The intended recipient.
     * @param message The text of the message to be sent.
     */
    public void sendMailItem(User to, MailMessage message) throws NotAuthorizedException {
        final MailItem item = super.createMailItem(to, message);
        server.post(user, item);
    }
}
