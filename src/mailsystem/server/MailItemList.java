/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.server;

import mailsystem.message.MailItem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author timoteo
 */
public class MailItemList {

    private List<MailItem> items;

    public MailItemList() {
        items = new ArrayList<MailItem>();
    }

    public MailItemsNumber countItems() {
        return new MailItemsNumber(items.size());
    }

    public MailItem getNextItemAndRemove() {
        MailItem item = null;
        if (!items.isEmpty()) {
            item = items.get(0);
            items.remove(item);
        }
        return item;
    }

    public void add(MailItem item) {
        items.add(item);
    }

    public void delete(MailItem mailItem) {
        items.remove(mailItem);
    }

    public boolean hasMailItem(MailItem mailItem) {
        return (items.contains(mailItem));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        final MailItemList other = (MailItemList) obj;
        return this.items.equals(other.items);
    }

}
