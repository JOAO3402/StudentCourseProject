package application.apptests.studenttests;

import model.Student;
import model.dao.DaoFactory;
import model.dao.StudentDao;

import java.util.Scanner;

public class FindByIdProgram {
    static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        StudentDao studentDao = DaoFactory.createStudentDao();

        System.out.println("FIND BY ID STUDENT TEST");
        System.out.print("Digite o Id do Aluno: ");
        Integer id = Integer.parseInt(sc.nextLine());
        Student student = studentDao.findById(id);
        System.out.println(student);
        System.out.println(":::TESTE FINALIZADO:::");
    }
}
