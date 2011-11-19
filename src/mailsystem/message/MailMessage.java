/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.message;

import java.io.IOException;

/**
 *
 * @author timoteo
 */
public class MailMessage {

    private Content content;
    private Attach attach;

    public MailMessage(Content contentido) {
        this.content = contentido;
    }

    public MailMessage(Content content, FileName attachFile) throws IOException {
        this.content = content;
        this.attach = new Attach(attachFile);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        final MailMessage other = (MailMessage) obj;
        Content otherContent = other.content;        
        Attach otherAttach = other.attach;
        return content.equals(otherContent) && compareAttach(otherAttach);
    }

    @Override
    public String toString() {
        return "Message: " + content;
    }

    private boolean compareAttach(Attach otherAttach) {
        if (otherAttach != null && attach != null) {
            return attach.equals(otherAttach);
        }
        return (otherAttach == null && attach == null);
    }
}
