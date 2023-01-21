package com.example.bootintro.web;

import com.example.bootintro.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Controller1 {
    List<User> people = new ArrayList<>();

    public Controller1() {
        User p = new User("Jan", "Novák", "123","jan@novak.cz"); // vytahnlo z app. log.
        User p2 = new User("Tomáš", "Nový","456","tomas@nova.cz");
        people.add(p);
        people.add(p2);
    }

    @GetMapping("/test1")
    public void test1(HttpServletResponse req, HttpServletResponse res) throws IOException {
        res.setHeader("Content-type", "text/html; charset=utf-8");
        res.getWriter().println("<b>Hello, Jindřich</b>");
    }

    @GetMapping("/test2")
    @ResponseBody
    public String test2() {
        return "Hello";
    }

    @GetMapping("/test3")
    @ResponseBody
    public User test3() {
        User p = new User("Jan", "Novák", "123","jan@novak.cz"); // vytahnlo z app. log.
        return p;
    }

    @GetMapping("/test4")
    public String test4(Model m) {
        m.addAttribute("people", people);
        m.addAttribute("newPerson", new User("", "","",""));
        //m.addAttribute("person", p);
        return "person-detail";
    }

    @PostMapping("/test4")
    public String test4post(Model m, @ModelAttribute User p) {
        people.add(p);
        return "redirect:/test4";
    }

}
