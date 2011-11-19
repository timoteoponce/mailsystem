/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem;

import mailsystem.server.account.MailItemsNumber;
import mailsystem.server.account.MailItemList;
import mailsystem.message.MailItem;

/**
 *
 * @author timoteo
 */
public class Folder {

    private FolderName name;
    private MailItemList itemList;

    public Folder(FolderName name) {
        this.name = name;
        this.itemList = new MailItemList();
    }

    public MailItemsNumber countItems() {
        return itemList.countItems();
    }

    public MailItem getNextItemAndRemove() {
        return itemList.getNextItemAndRemove();
    }

    public void add(MailItem item) {
        itemList.add(item);
    }

    public void delete(MailItem mailItem) {
        itemList.delete(mailItem);
    }

    public boolean hasMailItem(MailItem mailItem) {
        return itemList.hasMailItem(mailItem);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        final Folder other = (Folder) obj;
        return this.name.equals(other.name) && this.itemList.equals(other.itemList);
    }

    public boolean isNamed(FolderName folderName) {
        return name.isNamed(folderName);
    }
}
