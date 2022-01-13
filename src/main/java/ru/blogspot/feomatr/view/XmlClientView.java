package ru.blogspot.feomatr.view;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author iipolovinkin
 * @since 20.09.2015
 */
@Component
public class XmlClientView extends AbstractView {
    private static final Logger log = LoggerFactory.getLogger(XmlClientView.class);

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        XStream xStream = new XStream(new DomDriver());

        Object model = map.get("data");

        String xml = null;
        if (model != null) {
            xml = xStream.toXML(model);
        } else {
            xml = xStream.toXML(map);
        }

        byte[] bytes = xml.getBytes(StandardCharsets.UTF_8);
        response.getOutputStream().write(bytes);

        setContentType("binary/xml; charset=ISO-8859-1");
        response.setContentType(getContentType());
    }
}
