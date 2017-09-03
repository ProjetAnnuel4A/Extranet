package com.esgi.extranet;

import com.esgi.extranet.login.MailClient;
import edu.umd.cs.findbugs.classfile.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author timotheearnauld
 */
@CrossOrigin
@Controller
public class MappingController {
    @GetMapping(value={"/user/changePassword/view", ""})
    public String changeUserPassword(){
        return "changePassword";
    }

    /**
     * Mapping login
     */

    @GetMapping(value={"changePassword/view", ""})
    public String changePassword(){
        return "login/changePassword";
    }

    public static String newPassword(){return "redirect:newPassword";}

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

    @GetMapping(value={"student/marks/view", ""})
    public String studentmarks(){
        return "student/marks";
    }

    @GetMapping(value={"student/planning/view", ""})
    public String studentplanning(){
        return "student/planning";
    }

    @GetMapping(value={"student/surveys/viewSurveys/view", ""})
    public String studentViewsSurvey(){
        return "student/surveys/viewSurveys";
    }

    @GetMapping(value={"student/surveys/answerSurvey/view", ""})
    public String studentAnswerSurvey(){
        return "student/surveys/answerSurvey";
    }

    @GetMapping(value={"student/surveys/viewResults/view", ""})
    public String studentViewResults(){
        return "student/surveys/viewResults";
    }

    @GetMapping(value={"student/surveys/seeResponses/view", ""})
    public String studentSeeResponses(){
        return "student/surveys/seeResponses";
    }

    /**
     Mapping teacher
     */

    @GetMapping(value={"teacher/home", ""})
    public String teacherhome(){
        return "teacher/index";
    }

    @GetMapping(value={"teacher/addMark/view", ""})
    public String addMark(){
        return "teacher/addMark";
    }

    @GetMapping(value={"teacher/manageClassmate/view", ""})
    public String teachermanageClassmate(){
        return "teacher/manageClassmate";
    }

    @GetMapping(value={"teacher/surveys/addSurvey/view", ""})
    public String teacherAddSurvey(){
        return "teacher/surveys/addSurvey";
    }

    @GetMapping(value={"teacher/surveys/viewSurveys/view", ""})
    public String teacherViewSurveys(){
        return "teacher/surveys/viewSurveys";
    }

    @GetMapping(value={"teacher/surveys/seeSurvey/view", ""})
    public String teacherSeeSurvey(){
        return "teacher/surveys/seeSurvey";
    }

    @GetMapping(value={"teacher/surveys/manageSurvey/view", ""})
    public String teacherManageSurvey(){
        return "teacher/surveys/manageSurvey";
    }

    @GetMapping(value={"teacher/surveys/deleteSurvey/view", ""})
    public String teacherDeleteSurvey(){
        return "teacher/surveys/deleteSurvey";
    }

    @GetMapping(value={"teacher/surveys/viewStudentResults/view", ""})
    public String teacherViewStudentResults(){
        return "teacher/surveys/viewStudentResults";
    }

    @GetMapping(value={"teacher/surveys/seeStudentResponses/view", ""})
    public String teacherSeeStudentResponses(){
        return "teacher/surveys/seeStudentResponses";
    }
}
