/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.message;

import mailsystem.User;

/**
 *
 * @author timoteo
 */
public class Header {

    private User from;
    // The intended recipient.
    private User to;

    public Header(User from, User to) {
        this.from = from;
        this.to = to;
    }

    public boolean isTo(User user) {
        return to.equals(user);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Header other = (Header) obj;
        return compareTo(other) && compareFrom(other);

    }

    private boolean compareTo(Header header) {
        return from.equals(header.from);
    }

    private boolean compareFrom(Header header) {
        return (to.equals(header.to));
    }

    @Override
    public String toString() {
        return "From: " + from + "\n" + "To: " + to;
    }
}
