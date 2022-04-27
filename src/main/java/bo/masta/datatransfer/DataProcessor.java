/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.masta.datatransfer;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.InputSource;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

import org.unbescape.html.HtmlEscape;
import org.unbescape.html.HtmlEscapeLevel;
import org.unbescape.html.HtmlEscapeType;

/**
 *
 * @author bobdee
 */
public class DataProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        String rawData = in.getBody(String.class);
        //TODO replace this hack with correct method to transform named entities to decimal
        String xmlData = rawData.replaceAll("&nbsp;", "")
                .replaceAll("&deg;", "&#176;")
                .replaceAll("&reg;", "&#174;")
                .replaceAll("&rdquo;", "&#8221;")
                .replaceAll("&trade;", "&#8482;");

        XStream xstream = new XStream(new DomDriver("UTF-8",
                new XmlFriendlyNameCoder("_-", "_")));

        xstream.autodetectAnnotations(true);
        xstream.alias("root", DataList.class);
        xstream.alias("item", Item.class);

        DataList data = (DataList) xstream.fromXML(xmlData);
        String whenString = "WHEN `partnersStore`.mapId= '";
        //FIXME change to stringBuffer
        String sqlBatchUpdate = "UPDATE `partnersStoreAmount`, `partnersStore` SET `partnersStoreAmount`.amount = CASE \n";

        String idList = new String();

        for (Item item : data.getItems()) {
            sqlBatchUpdate = sqlBatchUpdate + whenString + item.getId().toString() + "' THEN " + item.getQty_free() + " \n ";

            if (idList.isEmpty()) {
                idList = "('" + item.getId().toString();
            } else {
                idList = idList + "','" + item.getId().toString();
            }
        }
        idList = idList + "')";

        sqlBatchUpdate = sqlBatchUpdate + "END\n WHERE `partnersStoreAmount`.r_n=`partnersStore`.n AND `partnersStore`.mapId IN " + idList;

        exchange.getIn().setBody(sqlBatchUpdate);
    }

}
