package com.esgi.extranet;

import com.esgi.extranet.login.MailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author timotheearnauld
 */
@CrossOrigin
@Controller
public class AdminController {

    /**
     Mapping admin
     */

    @GetMapping(value={"admin/home", ""})
    public String adminhome(){
        return "admin/index";
    }

    @GetMapping(value={"admin/administration/view", ""})
    public String administration(){
        return "admin/administration";
    }

    @GetMapping(value={"admin/student/view", ""})
    public String student(){
        return "admin/student";
    }

    @GetMapping(value={"admin/teacher/view", ""})
    public String teacher(){
        return "admin/teacher";
    }

    @GetMapping(value={"admin/addClassmate/view", ""})
    public String addClassmate(){
        return "admin/admin/addClassmate";
    }

    @GetMapping(value={"admin/addCourse/view", ""})
    public String addCourse(){
        return "admin/admin/addCourse";
    }

    @GetMapping(value={"admin/deleteClassmate/view", ""})
    public String deleteClassmate(){
        return "admin/admin/deleteClassmate";
    }

    @GetMapping(value={"admin/displayClassmates/view", ""})
    public String displayClassmates(){
        return "admin/admin/displayClassmates";
    }

    @GetMapping(value={"admin/displayCourses/view", ""})
    public String displayCourses(){
        return "admin/admin/displayCourses";
    }

    @GetMapping(value={"admin/manageClassmate/view", ""})
    public String manageClassmate(){
        return "admin/admin/manageClassmate";
    }

    @GetMapping(value={"admin/manageClassmateCourses/view", ""})
    public String manageClassmateCourses(){
        return "admin/admin/manageClassmateCourses";
    }

    @GetMapping(value={"admin/manageTeachers/view", ""})
    public String manageTeachers(){
        return "admin/admin/manageTeachers";
    }

    @GetMapping(value={"admin/manageTeachersCourses/view", ""})
    public String manageTeachersCourses(){
        return "admin/admin/manageTeachersCourses";
    }

    @GetMapping(value={"admin/planning/view", ""})
    public String planning(){
        return "admin/admin/planning";
    }

    @GetMapping(value={"admin/removeCourse/view", ""})
    public String removeCourse(){
        return "admin/admin/removeCourse";
    }

    @GetMapping(value={"admin/updatePlanning/view", ""})
    public String updatePlanning(){
        return "admin/admin/updatePlanning";
    }

    @GetMapping(value={"admin/addStudent/view", ""})
    public String addStudent(){
        return "admin/student/addStudent";
    }

    @GetMapping(value={"admin/deleteStudent/view", ""})
    public String deleteStudent(){
        return "admin/student/deleteStudent";
    }

    @GetMapping(value={"admin/informationsStudent/view", ""})
    public String informationsStudent(){
        return "admin/student/informationsStudent";
    }

    @GetMapping(value={"admin/updateStudent/view", ""})
    public String updateStudent(){
        return "admin/student/updateStudent";
    }

    @GetMapping(value={"admin/addTeacher/view", ""})
    public String addTeacher(){
        return "admin/teacher/addTeacher";
    }

    @GetMapping(value={"admin/deleteTeacher/view", ""})
    public String deleteTeacher(){
        return "admin/teacher/deleteTeacher";
    }

    @GetMapping(value={"admin/informationsTeacher/view", ""})
    public String informationsTeacher(){
        return "admin/teacher/informationsTeacher";
    }

    @GetMapping(value={"admin/updateTeacher/view", ""})
    public String updateTeacher(){
        return "admin/teacher/updateTeacher";
    }

    /**
     Mapping student
     */

    @GetMapping(value={"student/home", ""})
    public String studenthome(){
        return "student/index";
    }

    /**
     Mapping teacher
     */

    @GetMapping(value={"teacher/home", ""})
    public String teacherhome(){
        return "teacher/index";
    }
}
