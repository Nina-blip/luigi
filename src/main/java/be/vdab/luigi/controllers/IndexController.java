package be.vdab.luigi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalTime;

@Controller
@RequestMapping("/")
class IndexController {
    @GetMapping
    public ModelAndView index(){
        int uur = LocalTime.now().getHour();
        if (uur < 12){
            return new ModelAndView("index","boodschap","morgen");
        } else if (uur < 18){
            return new ModelAndView("index", "boodschap", "middag");
        } else {
            return new ModelAndView("index", "boodschap", "avond");
        }
    }
}
