package bo.masta.datatransfer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author bobdee
 */
public class Bootsrap {
    
   public static void main(String[] args) {

      ApplicationContext context = new FileSystemXmlApplicationContext
            ("config/routes.xml");
   }
}