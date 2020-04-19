package com.example.gcy.Controller;

import com.example.gcy.ErrorCode.CustomizeErrorCode;
import com.example.gcy.pojo.Grade;
import com.example.gcy.responseDTO.ResultDTO;
import com.example.gcy.service.GradeService;
import com.example.gcy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher")
public class GradeController {


    @Autowired
    private GradeService gradeService;
    @Autowired
    private UserService userService;

    @GetMapping("/grade")
    public String grade_lisst(@PageableDefault(size = 6,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){

        model.addAttribute("pages", gradeService.listGrade(pageable));

        return "teacher/grade_list";
    }
    @GetMapping("/grade_edit/{id}")
    public String grade_edit(@PathVariable(name = "id")Long id, Model model){

        model.addAttribute("grade", gradeService.findByid(id));

        return "teacher/grade_edit";
    }
    @GetMapping("/grade_add")
    public String grade_add(Model model){

        model.addAttribute("stulist", userService.listUser());

        return "teacher/grade_add";
    }
    @PostMapping("/grade_add")
    @ResponseBody
    public ResultDTO grade_addp(Grade grade){

        gradeService.save(grade);
        return ResultDTO.okOf();
    }
    @PostMapping("/grade_edit")
    @ResponseBody
    public ResultDTO gridep(Grade grade){

        gradeService.updateGrade(grade.getId(), grade);

        return ResultDTO.okOf();
    }
    @PostMapping("/grade_del")
    @ResponseBody
    public ResultDTO gride_del(@RequestParam(name = "id")Long id){

        if (gradeService.deleteGrade(id)) {
            return ResultDTO.okOf();
        }

        return ResultDTO.errorOf(CustomizeErrorCode.DEL_ERROR);
    }
}
