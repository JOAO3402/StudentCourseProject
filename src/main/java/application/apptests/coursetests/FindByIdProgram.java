package application.apptests.coursetests;

import model.Course;
import model.dao.CourseDao;
import model.dao.DaoFactory;

import java.util.Scanner;

public class FindByIdProgram {
    static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        CourseDao courseDao = DaoFactory.createCourseDao();

        System.out.println("FIND BY ID TEST");
        System.out.print("Digite o Id do curso: ");
        Integer id = Integer.parseInt(sc.nextLine());
        Course course = courseDao.findById(id);
        System.out.println(course);
        System.out.println(":::TESTE FINALIZADO:::");
    }
}
