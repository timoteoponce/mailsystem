/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem;

import mailsystem.server.MailItemsNumber;
import mailsystem.server.ServerFetcher;
import mailsystem.server.NotAuthorizedException;
import mailsystem.message.Header;
import mailsystem.message.MailItem;
import mailsystem.message.Content;
import mailsystem.message.MailMessage;
import mailsystem.client.MailClientSender;
import mailsystem.server.ServerFacade;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author timoteo
 */
public class MailServerTest {

    final User user = new User("testuser");
    final User user2 = new User("testuser2");
    private User invalidUser = new User("invalidUser");

    @Test
    public void testMailItemsCountAtStartup() {
        ServerFacade server = createServer();
        Assert.assertEquals(server.howManyMailItemsInQueue(user), new MailItemsNumber(0));
    }

    @Test
    public void testSingleMailSendingCount() throws NotAuthorizedException {
        ServerFacade server = createServer();
        MailClientSender client1 = new MailClientSender(server, user);
        client1.sendMailItem(user2, new MailMessage(new Content("Hello")));
        Assert.assertEquals(server.howManyMailItemsInQueue(user2), new MailItemsNumber(1));
    }

    @Test
    public void testSingleMailSendingDequeueing() throws NotAuthorizedException {
        ServerFacade server = createServer();
        MailClientSender client1 = new MailClientSender(server, user);
        client1.sendMailItem(user2, new MailMessage(new Content("Hello")));
        MailItem nextMailItem = server.getNextMailItem(user2);
        MailItem expectedItem = new MailItem(new Header(user, user2), new MailMessage(new Content("Hello")));
        Assert.assertEquals(nextMailItem, expectedItem);
        Assert.assertEquals(server.howManyMailItemsInQueue(user2), new MailItemsNumber(0));
    }

    @Test
    public void testMultipleMailSendingCount() throws NotAuthorizedException {
        ServerFacade server = createServer();
        MailClientSender client1 = new MailClientSender(server, user);
        client1.sendMailItem(user2, new MailMessage(new Content("Hello1")));
        client1.sendMailItem(user2, new MailMessage(new Content("Hello2")));
        client1.sendMailItem(user2, new MailMessage(new Content("Hello3")));
        Assert.assertEquals(server.howManyMailItemsInQueue(user2), new MailItemsNumber(3));
    }

    @Test
    public void testMultipleMailSendingDequeueing() throws NotAuthorizedException {
        ServerFacade server = createServer();
        MailClientSender client1 = new MailClientSender(server, user);
        client1.sendMailItem(user2, new MailMessage(new Content("Hello1")));
        client1.sendMailItem(user2, new MailMessage(new Content("Hello2")));
        client1.sendMailItem(user2, new MailMessage(new Content("Hello3")));
        MailItem nextMailItem = server.getNextMailItem(user2);
        MailItem expectedItem = new MailItem(new Header(user, user2), new MailMessage(new Content("Hello1")));
        Assert.assertEquals(nextMailItem, expectedItem);
        Assert.assertEquals(server.howManyMailItemsInQueue(user2), new MailItemsNumber(2));
    }

    @Test
    public void testgetNextMailItemOnEntyMails() {
        ServerFacade server = createServer();
        MailItem nextMailItem = server.getNextMailItem(user2);
        Assert.assertEquals(nextMailItem, null);

    }

    @Test
    public void testgetNextMailItemOnValidMail() throws NotAuthorizedException {
        ServerFacade server = createServer();
        MailClientSender client1 = new MailClientSender(server, user);
        client1.sendMailItem(user2, new MailMessage(new Content("Hola")));
        MailItem nextMailItem = server.getNextMailItem(user2);
        Header expectedHeader = new Header(user, user2);
        MailItem expectedItem = new MailItem(expectedHeader, new MailMessage(new Content("Hola")));
        Assert.assertEquals(nextMailItem, expectedItem);
    }

    @Test
    public void testPostValidMail() throws NotAuthorizedException {
        ServerFacade server = createServer();
        Header header = new Header(user, user2);
        MailItem item = new MailItem(header, new MailMessage(new Content("Hello")));
        server.post(user,item);
        MailItem nextMailItem = server.getNextMailItem(user2);
        MailItem expectedItem = new MailItem(header, new MailMessage(new Content("Hello")));
        Assert.assertEquals(nextMailItem, expectedItem);
    }

    @Test(expected = NotAuthorizedException.class)
    public void testInvalidClient() throws NotAuthorizedException {
        ServerFacade server = createServer();
        new MailClientSender(server, invalidUser);
    }

    @Test
    public void testValidClient() throws NotAuthorizedException {
        ServerFacade server = createServer();
        new MailClientSender(server, user);
    }

    @Test
    public void testSendToValidClient() throws NotAuthorizedException {
        ServerFacade server = createServer();
        MailClientSender client = new MailClientSender(server, user);
        client.sendMailItem(user2, new MailMessage(new Content("hallo")));
    }

    @Test(expected=NotAuthorizedException.class)
    public void testSendToInvalidClient() throws NotAuthorizedException {
        ServerFacade server = createServer();
        MailClientSender client = new MailClientSender(server, user);
        client.sendMailItem(invalidUser, new MailMessage(new Content("hallo")));
    }

    private ServerFacade createServer() {
        ServerFacade server = new ServerFacade();
        server.addAccount(user);
        server.addAccount(user2);
        return server;
    }
}
