package com.example.gcy.Controller;

import com.example.gcy.responseDTO.ResultDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/test")
public class TestController {


    @GetMapping("/time")
    @ResponseBody
    public ResultDTO test(){

        Date date = new Date();
        long time = date.getTime();
        System.out.println(time);
        return ResultDTO.okOf(time);
    }
}
