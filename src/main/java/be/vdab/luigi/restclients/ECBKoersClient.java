package be.vdab.luigi.restclients;

import be.vdab.luigi.exceptions.KoersClientException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

@Component
@Order(2)
class ECBKoersClient implements KoersClient {
    private final URL url;

    ECBKoersClient() {
        try {
            this.url = new URL("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
        } catch (MalformedURLException ex) {
            throw new KoersClientException("ECB URL is verkeerd.", ex);
        }
    }

    @Override
    public BigDecimal getDollarKoers() {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try (InputStream stream = url.openStream()) {
            XMLStreamReader reader = factory.createXMLStreamReader(stream);
            try {
                while (reader.hasNext()) {
                    if (reader.next() == XMLStreamConstants.START_ELEMENT && "USD".equals(reader.getAttributeValue(null, "currency"))) {
                        return new BigDecimal(reader.getAttributeValue(null, "rate"));
                    }
                }
                throw new KoersClientException("XML van ECB bevat geen USD.");
            } finally {
                reader.close();
            }
        } catch (IOException | NumberFormatException | XMLStreamException ex) {
            throw new KoersClientException("Kan koers niet lezen via ECB.", ex);
        }
    }
}
