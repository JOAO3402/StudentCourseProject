package application.apptests.coursetests;

import model.Course;
import model.dao.CourseDao;
import model.dao.DaoFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindAllProgram {
    static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        List<Course> list = new ArrayList<>();
        CourseDao courseDao = DaoFactory.createCourseDao();

        System.out.println("FIND ALL COURSE TEST");
        list = courseDao.findAll();
        for(Course c: list){
            System.out.println(c);
        }
        System.out.println(":::TESTE FINALIZADO:::");
    }
}
