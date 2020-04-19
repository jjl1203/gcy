package com.example.gcy.Controller;

import com.example.gcy.ErrorCode.CustomizeErrorCode;
import com.example.gcy.pojo.Grade;
import com.example.gcy.pojo.LabTime;
import com.example.gcy.pojo.User;
import com.example.gcy.responseDTO.ResultDTO;
import com.example.gcy.service.GradeService;
import com.example.gcy.service.LabTimeService;
import com.example.gcy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private UserService userService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private LabTimeService labTimeService;

    @GetMapping("/login")
    public String loginView(){

        return "student/login";
    }
    @GetMapping("/index")
    public String indexView(){



        return "student/index";
    }
    @GetMapping("/grade")
    public String gradeView(HttpServletRequest request, Model model){

        User u = (User)request.getSession().getAttribute("user_stu");
        if (u==null) {
            model.addAttribute("msg", ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN));
            return "error";
        }
        List<Grade> grades = gradeService.findByUser(userService.findById(u.getId()));
        model.addAttribute("gs", grades);
        return "student/grade_list";
    }
    @GetMapping("/register")
    public String registerView(){

        return "student/register";
    }
    @GetMapping("/stu_add")
    public String stu_addView(){

        return "student/register";
    }
    @GetMapping("/stu_edit")
    public String stu_editView(HttpServletRequest request, Model model){

        User u = (User)request.getSession().getAttribute("user_stu");
        if (u==null) {
            model.addAttribute("msg", ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN));
            return "error";
        }
        model.addAttribute("stu", userService.findById(u.getId()));
        return "student/stu_edit";
    }
    @GetMapping("/stu_pass")
    public String stu_passView(HttpServletRequest request, Model model){

        User u = (User)request.getSession().getAttribute("user_stu");
        if (u==null) {
            model.addAttribute("msg", ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN));
            return "error";
        }
        model.addAttribute("stu", userService.findById(u.getId()));
        return "student/stu_pass";
    }
    @PostMapping("/stu_edit")
    @ResponseBody
    public ResultDTO stu_editp(User user){

        User u = userService.updateUser(user.getId(), user);
        if (u==null) {
            return ResultDTO.errorOf(CustomizeErrorCode.REGISTER_UPDATE_ERROR);
        }
        return ResultDTO.okOf();
    }

    @PostMapping("/stu_pass")
    @ResponseBody
    public ResultDTO stu_passp(User user){

        User u = userService.updateUser(user.getId(), user);
        if (u==null) {
            return ResultDTO.errorOf(CustomizeErrorCode.REGISTER_UPDATE_ERROR);
        }
        return ResultDTO.okOf();
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultDTO login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password,
                        HttpServletRequest request){


        if (username.equals("") || password.equals("")) {
            return ResultDTO.errorOf(CustomizeErrorCode.LOGIN_NULL);
        }
        User u = userService.findByUsernameAndPassword(username, password);
        if (u==null) {
            return ResultDTO.errorOf(CustomizeErrorCode.LOGIN_ERROR);
        }
        request.getSession().setAttribute("user_stu", u);
        return ResultDTO.okOf();
    }

    @PostMapping("/register")
    @ResponseBody
    public ResultDTO register(User user){

        User stu = userService.findByUsername(user.getUsername());
        System.out.println(stu);
        if (stu!=null) {
            return ResultDTO.errorOf(CustomizeErrorCode.REGISTER_USER_EXIST);
        }
        if (user.getNickname().equals("")) {
            return ResultDTO.errorOf(CustomizeErrorCode.REGISTER_NICK_NULL);
        }
        if (user.getUsername().equals("")) {
            return ResultDTO.errorOf(CustomizeErrorCode.REGISTER_USERNAME_NULL);
        }
        if (user.getPassword().equals("")) {
            return ResultDTO.errorOf(CustomizeErrorCode.REGISTER_PASSWORD_NULL);
        }
        User save = userService.save(user);
        if (save==null) {
            return ResultDTO.errorOf(CustomizeErrorCode.REGISTER_ERROR);
        }
        return ResultDTO.okOf();
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){

        request.getSession().removeAttribute("user_stu");
        return "index";
    }
    @GetMapping("/lab_start")
    public String lab_start(HttpServletRequest request, Model model){

        Date date = new Date();
        long time = date.getTime();
        LabTime lab = labTimeService.findById(1l);
        long time1 = lab.getStartTime().getTime();
        long time2 = lab.getEndTime().getTime();
        if (time>time1 && time<time2) {

            return "student/lab_start";
        }
        model.addAttribute("labs", labTimeService.labTimeList());

        return "teacher/lab_time";
    }


}
