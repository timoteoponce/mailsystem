/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem;

import mailsystem.server.impl.ServerFetcher;
import mailsystem.server.NotAuthorizedException;
import mailsystem.message.Header;
import mailsystem.message.MailItem;
import mailsystem.message.Content;
import mailsystem.message.MailMessage;
import mailsystem.message.FileName;
import java.io.IOException;
import junit.framework.Assert;
import mailsystem.client.MailClientReceiver;
import mailsystem.client.MailClientSender;
import mailsystem.server.impl.ServerFacade;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Miranda
 */
public class MailClientTest {

    final User user = new User("testuser");
    final User user2 = new User("testuser2");

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getNextMailItem method, of class MailClient.
     */
    @Test
    public void testGetNextMailItem() throws NotAuthorizedException {
        ServerFacade server = createServer();
        MailClientSender client1 = new MailClientSender(server, user);
        client1.sendMailItem(user2, new MailMessage(new Content("hola")));
        MailClientReceiver client2 = new MailClientReceiver(server, user2);
        MailItem nextMailItem = client2.getNextMailItem();
        Header header = new Header(user, user2);
        MailItem expectedItem = new MailItem(header, new MailMessage(new Content("hola")));
        Assert.assertEquals(nextMailItem, expectedItem);
    }

    /**
     * Test of printNextMailItem method, of class MailClient.
     */
    @Test
    public void testPrintNextMailItem() throws NotAuthorizedException {
        ServerFacade server = createServer();
        MailClientSender client1 = new MailClientSender(server, user);
        client1.sendMailItem(user2, new MailMessage(new Content("hola")));
        client1.sendMailItem(user2, new MailMessage(new Content("hola2")));
        MailClientReceiver client2 = new MailClientReceiver(server, user2);
        client2.getNextMailItem();
        MailItem nextMailItem = client2.getNextMailItem();
        MailItem expectedItem = new MailItem(new Header(user, user2), new MailMessage(new Content("hola2")));
        Assert.assertEquals(nextMailItem, expectedItem);
    }

    /**
     * Test of sendMailItem method, of class MailClient.
     */
    @Test
    public void testSendMailItem() throws NotAuthorizedException {
        ServerFacade server = createServer();
        MailClientSender client1 = new MailClientSender(server, user);
        client1.sendMailItem(user2, new MailMessage(new Content("hola")));
        MailClientReceiver client2 = new MailClientReceiver(server, user2);
        MailItem nextMailItem = client2.getNextMailItem();
        MailItem expectedItem = new MailItem(new Header(user, user2), new MailMessage(new Content("hola")));
        Assert.assertEquals(nextMailItem, expectedItem);
    }

    @Test
    public void testSendAttach() throws NotAuthorizedException, IOException {
        ServerFacade server = createServer();
        MailClientSender client1 = new MailClientSender(server, user);
        client1.sendMailItem(user2, new MailMessage(new Content("hola"), new FileName("README.TXT")));
        MailClientReceiver client2 = new MailClientReceiver(server, user2);
        MailItem nextMailItem = client2.getNextMailItem();
        MailItem expectedItem = new MailItem(new Header(user, user2), new MailMessage(new Content("hola"), new FileName("README.TXT")));
        Assert.assertEquals(nextMailItem, expectedItem);
    }

    @Test(expected = IOException.class)
    public void testSendInvaliAttach() throws NotAuthorizedException, IOException {
        ServerFacade server = createServer();
        MailClientSender client1 = new MailClientSender(server, user);
        client1.sendMailItem(user2, new MailMessage(new Content("hola"), new FileName("README2.TXT")));
    }

    @Test
    public void testDeleteMail() throws NotAuthorizedException, IOException {
        ServerFacade server = createServer();
        MailClientSender client1 = new MailClientSender(server, user);
        MailClientReceiver client2 = new MailClientReceiver(server, user2);
        client1.sendMailItem(user2, new MailMessage(new Content("hola")));
        MailItem mailItem = client2.getNextMailItem();
        client2.deleteMailItem(mailItem);
        Assert.assertFalse(client2.hasMailInInbox(mailItem));
        Assert.assertTrue(client2.hasMailTrash(mailItem));
    }

    @Test
    public void testNewFolder() throws NotAuthorizedException, IOException {
        ServerFacade server = createServer();
        MailClientReceiver client1 = new MailClientReceiver(server, user);
        client1.createFolder(new FolderName("folder", user));
        Assert.assertTrue(client1.hasFolder(new FolderName("folder", user)));
    }

    @Test
    public void testDeleteFolder() throws NotAuthorizedException, IOException {
        ServerFacade server = createServer();
        MailClientReceiver client1 = new MailClientReceiver(server, user);
        client1.createFolder(new FolderName("folder", user));
        Assert.assertTrue(client1.hasFolder(new FolderName("folder", user)));
        client1.deleteFolder(new FolderName("folder", user));
        Assert.assertFalse(client1.hasFolder(new FolderName("folder", user)));
    }

    @Test
    public void testCreateDeleteInbox() throws NotAuthorizedException, IOException {
        ServerFacade server = createServer();
        MailClientReceiver client1 = new MailClientReceiver(server, user);
        client1.createFolder(new FolderName("inbox", user));
        Assert.assertTrue(client1.hasFolder(new FolderName("inbox", user)));
        client1.deleteFolder(new FolderName("inbox", user));
        Assert.assertTrue(client1.hasFolder(new FolderName("inbox", user)));
    }

    @Test
    public void testMoveMailItemFromInbox() throws NotAuthorizedException, IOException {
        ServerFacade server = createServer();
        MailClientSender client1 = new MailClientSender(server, user);
        MailClientReceiver client2 = new MailClientReceiver(server, user2);
        client1.sendMailItem(user2, new MailMessage(new Content("hola")));
        MailItem mailItem = client2.getNextMailItem();
        client2.moveMailItem(new FolderName("newFolder", user2), mailItem);
        Assert.assertTrue(client2.hasMailInInbox(mailItem));
        Assert.assertTrue(client2.hasMailInFolder(new FolderName("newFolder", user2), mailItem));
    }

    @Test
    public void testMoveMailItemFromTrash() throws NotAuthorizedException, IOException {
        ServerFacade server = createServer();
        MailClientSender client1 = new MailClientSender(server, user);
        MailClientReceiver client2 = new MailClientReceiver(server, user2);
        client1.sendMailItem(user2, new MailMessage(new Content("hola")));
        MailItem mailItem = client2.getNextMailItem();
        client2.deleteMailItem(mailItem);
        client2.moveMailItem(new FolderName("newFolder", user2), mailItem);
        Assert.assertFalse(client2.hasMailInInbox(mailItem));
        Assert.assertFalse(client2.hasMailTrash(mailItem));
        Assert.assertTrue(client2.hasMailInFolder(new FolderName("newFolder", user2), mailItem));
    }

    @Test
    public void testMoveMailItemBetweenFolders() throws NotAuthorizedException, IOException {
        ServerFacade server = createServer();
        MailClientSender client1 = new MailClientSender(server, user);
        MailClientReceiver client2 = new MailClientReceiver(server, user2);
        client1.sendMailItem(user2, new MailMessage(new Content("hola")));
        MailItem mailItem = client2.getNextMailItem();
        client2.moveMailItem(new FolderName("newFolder", user2), mailItem);
        client2.moveMailItem(new FolderName("newFolder2", user2), mailItem);
        Assert.assertTrue(client2.hasMailInInbox(mailItem));
        Assert.assertFalse(client2.hasMailInFolder(new FolderName("newFolder", user2), mailItem));
        Assert.assertTrue(client2.hasMailInFolder(new FolderName("newFolder2", user2), mailItem));
    }

    private ServerFacade createServer() {
        ServerFacade server = new ServerFacade();
        server.addAccount(user);
        server.addAccount(user2);
        return server;
    }
}
