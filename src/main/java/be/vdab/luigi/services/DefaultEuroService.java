package be.vdab.luigi.services;

import be.vdab.luigi.restclients.KoersClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
class DefaultEuroService implements EuroService {
    private final KoersClient koersclient;

    public DefaultEuroService(KoersClient koersclient) {
        this.koersclient = koersclient;
    }

    @Override
    public BigDecimal naarDollar(BigDecimal euro){
        return euro.multiply(koersclient.getDollarKoers()).setScale(2, RoundingMode.HALF_UP);
    }
}
