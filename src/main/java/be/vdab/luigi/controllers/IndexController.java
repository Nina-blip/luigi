package be.vdab.luigi.controllers;

import be.vdab.luigi.domain.Adres;
import be.vdab.luigi.domain.Persoon;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("/")
class IndexController {
    private String boodschap() {
        int uur = LocalTime.now().getHour();
        if (uur < 12) {
            return "morgen";
        } else if (uur < 18) {
            return "middag";
        } else {
            return "avond";
        }
    }

    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndview = new ModelAndView("index", "boodschap", boodschap());
        modelAndview.addObject("zaakvoerder", new Persoon("Luigi", "Peperone", 7, true,
                LocalDate.of(1966, 1, 31),
                new Adres("Grote Markt", "3","Oudenaarde", 9700)));
        return modelAndview;
    }
}
