/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.masta.datatransfer;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bobdee
 */

@XStreamAlias("root")
public class DataList {

    @XStreamImplicit(itemFieldName="item")
    private List<Item> items; 

    
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public DataList() {
        this.items = new ArrayList<Item>();
    }
    
}
