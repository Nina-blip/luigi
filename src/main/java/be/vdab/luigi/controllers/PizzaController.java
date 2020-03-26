package be.vdab.luigi.controllers;

import be.vdab.luigi.domain.Pizza;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("pizzas")
class PizzaController {
    private final Pizza[] pizzas = {new Pizza(1, "Proscuitto", BigDecimal.valueOf(4), true),
            new Pizza(2, "Margherita", BigDecimal.valueOf(5), false),
            new Pizza(3, "Calzone", BigDecimal.valueOf(4), false)};

    private List<BigDecimal> uniekePrijzen() {
        return Arrays.stream(pizzas).map(pizza -> pizza.getPrijs())
                .distinct().sorted().collect(Collectors.toList());
    }

    private List<Pizza> pizzasMetPrijs(BigDecimal prijs) {
        return Arrays.stream(pizzas)
                .filter(pizza -> pizza.getPrijs().compareTo(prijs) == 0)
                .collect(Collectors.toList());
    }

    @GetMapping
    public ModelAndView pizzas() {
        return new ModelAndView("pizzas", "pizzas", pizzas);
    }

    @GetMapping("{id}")
    public ModelAndView pizza(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("pizza");
        Arrays.stream(pizzas).filter(pizza -> pizza.getId()== id).findFirst()
                .ifPresent(pizza -> modelAndView.addObject(pizza));
        return modelAndView;
    }

    @GetMapping("prijzen")
    public ModelAndView prijzen() {
        return new ModelAndView("prijzen", "prijzen", uniekePrijzen());
    }

    @GetMapping("prijzen/{prijs}")
    public ModelAndView pizzasMetEenPrijs(@PathVariable BigDecimal prijs) {
        ModelAndView modelAndView = new ModelAndView("prijzen","pizzas",pizzasMetPrijs(prijs));
        modelAndView.addObject("prijzen", uniekePrijzen());
        return modelAndView;
    }
}
