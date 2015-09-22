package ru.blogspot.feomatr.view;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractView;
import ru.blogspot.feomatr.entity.Client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author iipolovinkin
 * @since 20.09.2015
 */
public class XmlClientView extends AbstractView {
    private static final Logger log = LoggerFactory.getLogger(XmlClientView.class);

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        XStream xStream = new XStream(new DomDriver());

        Client model = (Client) map.get("data");

        String xml = null;
        if (model != null) {
            xStream.omitField(Client.class, "owner");
            xml = xStream.toXML(model);
        } else {
            xStream.toXML(map);
        }

        response.getOutputStream().write(xml.getBytes());

//        if (StringUtils.isEmpty(getContentType())) {
//            setContentType("text/xml; charset=ISO-8859-1");
//        }
        setContentType("text/xml; charset=ISO-8859-1");
        response.setContentType(getContentType());

        logger.info("xml = " + xml);
    }
}
