package be.vdab.luigi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping("/")
class IndexController {
    @GetMapping
    public String index(){
        StringBuilder buffer = new StringBuilder("<!doctype html><html><title>Hallo</title><body>");
        int uur = LocalTime.now().getHour();
        if (uur < 12){
            buffer.append("Goedemorgen");
        } else if (uur < 18){
            buffer.append("Goedemiddag");
        } else {
            buffer.append("Goedeavond");
        }
        buffer.append("</body></html>");
        return buffer.toString();
    }
}
