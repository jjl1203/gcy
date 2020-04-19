package com.example.gcy.Controller;

import com.example.gcy.ErrorCode.CustomizeErrorCode;
import com.example.gcy.pojo.Teacher;
import com.example.gcy.pojo.User;
import com.example.gcy.responseDTO.ResultDTO;
import com.example.gcy.service.TeacherService;
import com.example.gcy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginView(){

        return "teacher/login";
    }
    @GetMapping("/register")
    public String registerView(){

        return "teacher/register";
    }

    @GetMapping("/index")
    public String indexView(){

        return "teacher/index";
    }
    @GetMapping("/studentList")
    public String studentView(@PageableDefault(size = 6,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){


        model.addAttribute("pages", userService.listUser(pageable));

        return "teacher/stu_list";
    }
    @GetMapping("/stu_add")
    public String student_addView(){

        return "teacher/stu_add";
    }
    @GetMapping("/stu_edit_pass/{id}")
    public String student_passView(@PathVariable(name = "id") Long id,Model model){

        model.addAttribute("stu", userService.findById(id));

        return "teacher/stu_pass";
    }


    @GetMapping("/teacher_edit")
    public String teacher_editView(HttpServletRequest request, Model model){

        Teacher t = (Teacher)request.getSession().getAttribute("user_teacher");
        if (t==null) {
            model.addAttribute("msg", ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN));
            return "error";
        }
        model.addAttribute("teacher", teacherService.findById(t.getId()));
        return "teacher/teacher_edit";
    }
    @GetMapping("/teacher_pass")
    public String teacher_passView(HttpServletRequest request, Model model){

        Teacher t = (Teacher)request.getSession().getAttribute("user_teacher");
        if (t==null) {
            model.addAttribute("msg", ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN));
            return "error";
        }
        model.addAttribute("teacher", teacherService.findById(t.getId()));
        return "teacher/teacher_pass";
    }
    @PostMapping("/teacher_edit")
    @ResponseBody
    public ResultDTO teacher_editp(Teacher teacher){

        Teacher t = teacherService.updateTeacher(teacher.getId(), teacher);
        if (t==null) {
            return ResultDTO.errorOf(CustomizeErrorCode.REGISTER_UPDATE_ERROR);
        }
        return ResultDTO.okOf();
    }

    @PostMapping("/teacher_pass")
    @ResponseBody
    public ResultDTO teacher_passp(Teacher teacher){

        Teacher t = teacherService.updateTeacher(teacher.getId(), teacher);
        if (t==null) {
            return ResultDTO.errorOf(CustomizeErrorCode.REGISTER_UPDATE_ERROR);
        }
        return ResultDTO.okOf();
    }

    @PostMapping("/stu_edit_pass")
    @ResponseBody
    public ResultDTO student_passView(User user){

        User u = userService.updateUser(user.getId(), user);
        if (u==null) {
            return ResultDTO.errorOf(CustomizeErrorCode.REGISTER_UPDATE_ERROR);
        }
        return ResultDTO.okOf();
    }

    @GetMapping("/stu_edit/{id}")
    public String student_editView(Model model,@PathVariable(name = "id")Long stuId){

        model.addAttribute("stu",userService.findById(stuId));
        return "teacher/stu_edit";
    }
    @PostMapping("/stu_edit")
    @ResponseBody
    public ResultDTO student_edit(User user){

        User u = userService.updateUser(user.getId(), user);
        if (u!=null) {
            return ResultDTO.okOf();
        }
        return ResultDTO.errorOf(CustomizeErrorCode.REGISTER_UPDATE_ERROR);
    }
    @PostMapping("/stu_del")
    @ResponseBody
    public ResultDTO student_edit(@RequestParam(name = "id") Long id){


        if (userService.deleteUser(id)) {
            return ResultDTO.okOf();
        }
        return ResultDTO.errorOf(CustomizeErrorCode.DEL_ERROR);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultDTO login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password,
                           HttpServletRequest request){

        if (username.equals("") || password.equals("")) {
            return ResultDTO.errorOf(CustomizeErrorCode.LOGIN_NULL);
        }
        Teacher t = teacherService.findByUsernameAndPassword(username, password);
        if (t==null) {
            return ResultDTO.errorOf(CustomizeErrorCode.LOGIN_ERROR);
        }
        request.getSession().setAttribute("user_teacher", t);
        return ResultDTO.okOf();
    }

    @PostMapping("/register")
    @ResponseBody
    public ResultDTO register(Teacher teacher){

        Teacher t = teacherService.findByUsername(teacher.getUsername());
        System.out.println(t);
        if (t!=null) {
            return ResultDTO.errorOf(CustomizeErrorCode.REGISTER_USER_EXIST);
        }
        if (teacher.getNickname().equals("")) {
            return ResultDTO.errorOf(CustomizeErrorCode.REGISTER_NICK_NULL);
        }
        if (teacher.getUsername().equals("")) {
            return ResultDTO.errorOf(CustomizeErrorCode.REGISTER_USERNAME_NULL);
        }
        if (teacher.getPassword().equals("")) {
            return ResultDTO.errorOf(CustomizeErrorCode.REGISTER_PASSWORD_NULL);
        }
        Teacher save = teacherService.save(teacher);
        if (save==null) {
            return ResultDTO.errorOf(CustomizeErrorCode.REGISTER_ERROR);
        }
        return ResultDTO.okOf();
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){

        request.getSession().removeAttribute("user_teacher");
        return "index";
    }
}
