package application.apptests.coursetests;

import model.dao.CourseDao;
import model.dao.DaoFactory;

import java.util.Scanner;

public class DeleteByIdProgram {
    static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        CourseDao courseDao = DaoFactory.createCourseDao();

        System.out.println("DELETE BY ID COURSE TEST");
        System.out.print("Digite o ID do curso a ser deletado: ");
        Integer id = Integer.parseInt(sc.nextLine());
        courseDao.deleteById(id);
        System.out.println(":::TESTE FINALIZADO:::");
    }
}
