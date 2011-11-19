/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.server;

/**
 *
 * @author timoteo
 */
public class MailItemsNumber {

    private int number;

    public MailItemsNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        final MailItemsNumber other = (MailItemsNumber) obj;
        if (number != other.number) {
            return false;
        }
        return true;
    }
}
