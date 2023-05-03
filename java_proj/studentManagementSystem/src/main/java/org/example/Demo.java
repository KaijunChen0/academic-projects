package org.example;

import com.google.gson.Gson;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        Gson gson = new Gson();

        Student student = new Student(10101, "Jack");
        Student student1 = new Student(10222, "Tony");
        List<Student> students = new ArrayList<Student>();
        students.add(student);
        students.add(student1);

        System.out.println(gson.toJson(student));
        System.out.println(gson.toJson(students));
    }
}
