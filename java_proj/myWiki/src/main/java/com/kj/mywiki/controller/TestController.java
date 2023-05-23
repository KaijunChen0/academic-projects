package com.kj.mywiki.controller;

import com.kj.mywiki.domain.Test;
import com.kj.mywiki.service.TestService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // =@Controller + @ResponseBody(to return a string or json object), return string, that is return "hello".
//@Controller // return a webpage, in this case, return a webpage named "hello"
public class TestController {

    @Value("${test.hello:TEST}")
    private String testHello;

    @Resource
    private TestService testService;

    /*
    * HTTP regular 4 request ways: GET, POST, PUT, DELETE
    * "restful style"
    * GET: to send a request (@GetMapping only for get request)
    * POST: to add (@PostMapping only for post)
    * PUT: to modify (@PutMapping only for put)
    * DELETE: to delete (@DeleteMapping only for delete)
    * (@RequestMapping: support all above ways)
    */

//    @RequestMapping("/hello")
// http://127.0.0.1:8080/hello
//    public String hello(){return "Hello, welcome!";}
    @GetMapping("/hello")
    public String hello(){
        return "Hello, welcome!" + testHello;
    }

    @PostMapping ("/hello/post")
    public String helloPost(String name){
        return "Hello, welcome! Post, " + name;
    }

    @GetMapping("/test/list")
    public List<Test> list(){
        return testService.list();
    }
}
