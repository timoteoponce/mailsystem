/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mailsystem;

/**
 *
 * @author timoteo
 */
public class User {
    private String name;

    public User(String name) {
        this.name=name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        String otherName = other.name;
        if (name == null) {
            return (otherName != null);
        }
        return name.equals(otherName);
    }


}
