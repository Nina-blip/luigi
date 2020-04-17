package be.vdab.luigi.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.validation.constraints.Email;
import java.io.Serializable;

@Component
@SessionScope
public class Identificatie implements Serializable {
    private static final long SerialVersionUID = 1L;
    @Email
    private String EmailAdres;

    public String getEmailAdres() {
        return EmailAdres;
    }

    public void setEmailAdres(String emailAdres) {
        EmailAdres = emailAdres;
    }
}
