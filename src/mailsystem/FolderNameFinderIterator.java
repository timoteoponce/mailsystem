/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem;

/**
 *
 * @author timoteo
 */
class FolderNameFinderIterator implements FolderIterator {

    private FolderName folderName;
    private Folder result;

    FolderNameFinderIterator(FolderName folderName) {
        this.folderName = folderName;
    }

    public void process(Folder folder) {
        if (folder.isNamed(folderName)) {
            result = folder;
        }
    }

    public boolean keepGoing() {
        return result == null;
    }

    Folder getResult() {
        return result;
    }
}
