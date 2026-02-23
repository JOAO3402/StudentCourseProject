package application.apptests.coursetests;

import model.Course;
import model.dao.CourseDao;
import model.dao.DaoFactory;
import java.util.Scanner;

public class InsertProgram {
    static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        CourseDao courseDao = DaoFactory.createCourseDao();

        System.out.println("INSERT COURSE TEST");
        System.out.print("Insira o nome do curso a ser inserido no Banco de Dados: ");
        String name = sc.nextLine();
        Course course = new Course(null, name);

        courseDao.insert(course);
        System.out.println(":::TESTE FINALIZADO:::");
    }
}
