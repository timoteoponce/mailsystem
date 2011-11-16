/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mailsystem;

import mailsystem.server.UserFolders;

/**
 *
 * @author timoteo
 */
public class FolderFinder {

    private UserFolders userFolders;

    public FolderFinder(UserFolders userFolders) {
        this.userFolders = userFolders;
    }

    public Folder find(FolderName folderName){
        FolderFinderIterator iterator = new FolderFinderIterator(folderName);
        userFolders.iterateItems(iterator);
        return iterator.getResult();
    }

}
