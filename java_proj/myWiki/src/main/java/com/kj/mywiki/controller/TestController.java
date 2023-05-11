package com.kj.mywiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // =@Controller + @ResponseBody(to return a string or json object), return string, that is return "hello".
//@Controller // return a webpage, in this case, return a webpage named "hello"
public class TestController {
    /*
    * HTTP regular request ways: GET, POST, PUT, DELETE
    * restful style
    * GET: to send a request
    * POST: to add
    * PUT: to modify
    * DELETE: to delete
    */
    @RequestMapping("/hello") // http://127.0.0.1:8080/hello
    public String hello(){
        return "Hello, welcome!";
    }
}
