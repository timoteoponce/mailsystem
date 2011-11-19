/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem;

/**
 *
 * @author timoteo
 */
public class FolderName {

    private String name;
    private User user;

    public FolderName(String name, User user) {
        this.name = name;
        this.user = user;
    }

    @Override
    public boolean equals(Object obj) {
        final FolderName other = (FolderName) obj;
        String otherName = other.name;
        User otherUser = other.user;
        return name.equals(otherName) && compareUser(otherUser);
    }

    private boolean compareUser(User otherUser) {
        return user.equals(otherUser);
    }

    boolean isNamed(FolderName folderName) {
        return name.equals(folderName.name);
    }
}
