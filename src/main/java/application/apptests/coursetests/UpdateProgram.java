package application.apptests.coursetests;

import model.Course;
import model.dao.CourseDao;
import model.dao.DaoFactory;

import java.util.Scanner;

public class UpdateProgram {
    static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        CourseDao courseDao = DaoFactory.createCourseDao();

        System.out.println("UPDATE COURSE TEST");
        System.out.print("Insira o ID do curso a ser atualizado: ");
        Integer id = Integer.parseInt(sc.nextLine());
        System.out.print("Digite o NOVO nome do curso: ");
        String name = sc.nextLine();
        Course course = new Course(id, name);
        courseDao.update(course);
        System.out.println(":::TESTE FINALIZADO:::");
    }
}
