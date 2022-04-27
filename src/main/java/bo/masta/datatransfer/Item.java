
package bo.masta.datatransfer;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 *
 * @author bobdee
 */

@XStreamAlias("item")
public class Item {
   @XStreamAsAttribute 
    private String article;
   @XStreamAsAttribute 
    private Boolean available;
   @XStreamAsAttribute 
    private Integer id;
   @XStreamAsAttribute 
    private String name;
   @XStreamAsAttribute 
    private Integer qty_free;
      
    
    
    public String getArticle() {
        return article;
    }
 
    public void setArticle(String article) {
        this.article = article;
    }
   
    public Boolean getAvailable() {
        return available;
    }
    
  
    public void setAvailable(Boolean available) {
        this.available = available;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getQty_free() {
        return qty_free;
    }

    public void setQty_free(Integer qty_free) {
        this.qty_free = qty_free;
    }
}
