package org.example;

import com.google.gson.Gson;
import org.example.mapper.StudentMapper;
import org.example.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SuppressWarnings("all")
@RestController
public class Controller {
    // @Autowired: auto map to Student from mysql data
    @Autowired
    StudentMapper studentMapper;
    // in http://localhost:8080, put /test after 8080, and restart Application
    // and refresh the webpage, we will get "TEST" displayed in the web.
    private Gson gson = new Gson();

    @GetMapping("/test")
    public String test(){
        List<Student> students = studentMapper.selectList(null);
        return gson.toJson(students);
    }
}
