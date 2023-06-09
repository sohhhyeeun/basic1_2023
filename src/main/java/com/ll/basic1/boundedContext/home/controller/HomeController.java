package com.ll.basic1.boundedContext.home.controller;

import com.ll.basic1.boundedContext.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

@Controller //개발자가 스프링부트에게 말함.(아래 있는 HomeController는 컨트롤러임.)
public class HomeController {
    private int num;
    private final List<People> list;
    @Autowired
    private final MemberService memberService;

    public HomeController(MemberService memberService) {
        num = -1;
        list = new ArrayList<>();
        this.memberService = memberService;
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

    @GetMapping("/home/addPerson")
    //개발자가 스프링부트에게 말함.(이런 요청이 오면 아래 메서드를 실행)
    @ResponseBody
    //아래 메서드를 실행한 후 그 리턴값을 응답으로 삼으라고 말함.
    //int a는 쿼리스트링에서 a의 파라미터 값을 얻은 후 정수화한 값이어야 함.
    public String showAddPerson(@RequestParam String name, int age) {
        People p = new People(name, age);

        list.add(p);
        String s = p.getId() + "번 사람이 추가되었습니다.";

        return s;
    }

    @GetMapping("/home/people")
    //개발자가 스프링부트에게 말함.(이런 요청이 오면 아래 메서드를 실행)
    @ResponseBody
    //아래 메서드를 실행한 후 그 리턴값을 응답으로 삼으라고 말함.
    //int a는 쿼리스트링에서 a의 파라미터 값을 얻은 후 정수화한 값이어야 함.
    public List<People> ShowPeople() {
        return list;
    }

    @GetMapping("/home/deletePerson")
    @ResponseBody
    public String showDeletePerson(int id) {
        boolean removed = list.removeIf(person -> person.getId() == id);

        if (removed == false) {
            return "%d번 사람이 존재하지 않습니다.".formatted(id);
        }

        return "%d번 사람이 삭제되었습니다.".formatted(id);
    }

    @GetMapping("/home/modifyPerson")
    @ResponseBody
    public String showModifyPerson(int id, String name, int age) {
        People found = list
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        //boolean idExists = list.contains(id);

        if (found == null) {
            return "%d번 사람이 존재하지 않습니다.".formatted(id);
        }

        found.setName(name);
        found.setAge(age);
        return "%d번 사람이 수정되었습니다.".formatted(id);
    }

    @GetMapping("/home/cookie/increase")
    @ResponseBody
    public int showCookieIncrease(HttpServletRequest req, HttpServletResponse resp) throws IOException { // 리턴되는 int 값은 String 화 되어서 고객(브라우저)에게 전달된다.
        int countInCookie = 0;

        // 고객이 가져온 쿠폰에서 count 쿠폰을 찾고 그 쿠폰의 값을 가져온다.
        if (req.getCookies() != null) {
            countInCookie = Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals("count"))
                    .map(Cookie::getValue)
                    .mapToInt(Integer::parseInt)
                    .findFirst()
                    .orElse(0);
        }

        int newCountInCookie = countInCookie + 1;

        // 고객이 가져온 count 쿠폰값에 1을 더한 쿠폰을 만들어서 고객에게 보낸다.
        // 쉽게 말하면 브라우저(고객)에 저장되어 있는 count 쿠폰의 값을 1 증가시킨다.
        // 이렇게 브라우저의 쿠키값을 변경하면 재방문시에 스프링부트가 다시 그 값을 받게 되어 있다.
        resp.addCookie(new Cookie("count", newCountInCookie + ""));

        // 응답 본문
        return newCountInCookie;
    }
}

@AllArgsConstructor
@Getter
@ToString
class People {
    private int id;
    private static int lastId = 0;
    @Setter
    private String name;
    @Setter
    private int age;

    People(String name, int age) {
        this(++lastId, name, age);
    }
}
