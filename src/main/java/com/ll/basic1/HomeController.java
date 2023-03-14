package com.ll.basic1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //개발자가 스프링부트에게 말함.(아래 있는 HomeController는 컨트롤러임.)
public class HomeController {
    private int num;

    public HomeController() {
        num = -1;
    }

    @GetMapping("/home/main")
    //개발자가 스프링부트에게 말함.(이런 요청이 오면 아래 메서드를 실행)
    @ResponseBody
    //아래 메서드를 실행한 후 그 리턴값을 응답으로 삼으라고 말함.
    public String showMain() {
        return "안녕하세요.";
    }

    @GetMapping("/home/main2")
    //개발자가 스프링부트에게 말함.(이런 요청이 오면 아래 메서드를 실행)
    @ResponseBody
    //아래 메서드를 실행한 후 그 리턴값을 응답으로 삼으라고 말함.
    public String showMain2() {
        return "반갑습니다.";
    }

    @GetMapping("/home/main3")
    //개발자가 스프링부트에게 말함.(이런 요청이 오면 아래 메서드를 실행)
    @ResponseBody
    //아래 메서드를 실행한 후 그 리턴값을 응답으로 삼으라고 말함.
    public String showMain3() {
        return "즐거웠습니다.";
    }

    //int num = 0;
    @GetMapping("/home/increase")
    //개발자가 스프링부트에게 말함.(이런 요청이 오면 아래 메서드를 실행)
    @ResponseBody
    //아래 메서드를 실행한 후 그 리턴값을 응답으로 삼으라고 말함.
    public int showIncrease() { //리턴되는 int 값은 String 화 되어서 고객(브라우저)에세 전달됨.
        num++;
        return num;
    }

    @GetMapping("/home/plus")
    //개발자가 스프링부트에게 말함.(이런 요청이 오면 아래 메서드를 실행)
    @ResponseBody
    //아래 메서드를 실행한 후 그 리턴값을 응답으로 삼으라고 말함.
    //int a는 쿼리스트링에서 a의 파라미터 값을 얻은 후 정수화한 값이어야 함.
    public int showPlus(@RequestParam int a, int b) { //리턴되는 int 값은 String 화 되어서 고객(브라우저)에세 전달됨.
        return a + b;
    }
}
