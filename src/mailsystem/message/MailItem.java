package mailsystem.message;

import java.io.PrintStream;
import mailsystem.User;

/**
 * A class to model a simple mail item. The item has sender and recipient
 * addresses and a message string.
 * @author David J. Barnes and Michael Kolling
 * @version 2006.03.30
 */
public class MailItem {
    // The sender of the item.

    private Header header;
    // The text of the message.
    private MailMessage message;

    /**
     * Create a mail item from sender to the given recipient,
     * containing the given message.
     * @param from The sender of this item.
     * @param to The intended recipient of this item.
     * @param message The text of the message to be sent.
     */
    public MailItem(Header header, MailMessage message) {
        this.header = header;
        this.message = message;
    }

    public boolean isTo(User user) {
        return header.isTo(user);
    }

    /**
     * Print this mail message to the text terminal.
     */
    public void print() {
        println(header.toString());
        println(message.toString());
    }

    private void println(String message) {
        PrintStream stream = System.out;
        stream.print(message);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MailItem other = (MailItem) obj;
        return compareHeader(other.header) && compareMessage(other.message);
    }

    private boolean compareHeader(Header otherHeader) {
        return header.equals(otherHeader);
    }

    private boolean compareMessage(MailMessage otherMessage) {
        return message.equals(otherMessage);
    }

    public boolean isToUser(User user) {
        return header.isTo(user);
    }
}
