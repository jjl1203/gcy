package com.example.gcy.Controller;

import com.example.gcy.ErrorCode.CustomizeErrorCode;
import com.example.gcy.pojo.LabTime;
import com.example.gcy.responseDTO.ResultDTO;
import com.example.gcy.service.LabTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/teacher")
public class LabTimeController {

    @Autowired
    private LabTimeService labTimeService;

    @GetMapping("/lab_time")
    public String indexView(Model model){

        model.addAttribute("labs", labTimeService.labTimeList());

        return "teacher/lab_time";

    }

    @GetMapping("/lab_time_edit")
    public String lab_time_editView(Model model){

        return "teacher/lab_time_edit";

    }

    @PostMapping("/lab_time_edit")
    @ResponseBody
    public ResultDTO lab_time_editp(@RequestParam(name = "startTime") String startTime,
                                    @RequestParam(name = "endTime")String endTime) throws ParseException {


        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date_startTime = sdf.parse(startTime);
        Date date_endTime = sdf.parse(endTime);

        LabTime labTime = new LabTime();
        labTime.setStartTime(date_startTime);
        labTime.setEndTime(date_endTime);
        LabTime save = labTimeService.updateLabTime(1L, labTime);
        if (save==null) {
            return ResultDTO.errorOf(CustomizeErrorCode.TIME_ERROR);
        }
        return ResultDTO.okOf();
    }
}
