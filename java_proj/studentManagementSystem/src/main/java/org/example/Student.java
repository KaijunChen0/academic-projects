package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

// @Data: convert to string, like __repr__ in Python
// @AllArgsConstructor: generate a constructor with parameters
@Data
@AllArgsConstructor
public class Student {
    private long id;
    private String name;
}
