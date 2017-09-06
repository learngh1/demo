package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @RequestMapping("/test")
    public String testRestMethod(@RequestParam(value="id", defaultValue="-1") String id) {
        return "testRestMethod(): id=" + id;
    }

    //TODO: implement
}
