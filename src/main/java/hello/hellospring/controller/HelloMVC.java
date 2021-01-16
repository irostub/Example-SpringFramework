package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HelloMVC {

    @GetMapping("hello_mvc")
    public String HelloMvc(@RequestParam("text") String text, Model model){
        model.addAttribute("data", text);
        return "hello_mvc";
    }
}
