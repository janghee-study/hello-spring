package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") //정적
    public String hello(Model model){
        model.addAttribute( "data","hello");
        return "hello";
    }
    @GetMapping("hello-mvc") //동적mvc
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }
    @GetMapping("hello-string") //동적API
    @ResponseBody //바디부에 넣어줌 뷰이런게없음! #html로 내려주는것이 아님!
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }
    @GetMapping("hello-api")//동적API
    @ResponseBody
    public  Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); //ctrl+shift+enter
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
    }
}
