package mailsystem.client;

import mailsystem.message.Header;
import mailsystem.message.MailItem;
import mailsystem.message.MailMessage;
import mailsystem.server.NotAuthorizedException;
import mailsystem.User;
import mailsystem.server.AuthenticationServer;

/**
 * A class to model a simple email client. The client is run by a
 * particular user, and sends and retrieves mail via a particular server.
 * @author David J. Barnes and Michael Kolling
 * @version 2006.03.30
 */
public abstract class MailClient {
    // The server used for sending and receiving.

    private AuthenticationServer server;
    // The user running this client.
    private User user;

    /**
     * Create a mail client run by user and attached to the given server.
     */
    public MailClient(AuthenticationServer server, User user) throws NotAuthorizedException {
        this.server = server;
        this.user = user;
        connect();
    }

    void connect() throws NotAuthorizedException {
        server.authenticate(user);
    }

    public boolean isClientUser(User otherUser) {
        return user.equals(otherUser);
    }

    protected MailItem createMailItem(User to, MailMessage message) {
        Header header = new Header(user, to);
        return new MailItem(header, message);
    }
}
