package application.apptests.studenttests;

import model.dao.DaoFactory;
import model.dao.StudentDao;

import java.util.Scanner;

public class DeleteByIdProgram {
    static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        StudentDao studentDao = DaoFactory.createStudentDao();

        System.out.println("DELETE BY ID STUDENT TEST");
        System.out.print("Digite o Id do estudante: ");
        Integer id = Integer.parseInt(sc.nextLine());
        studentDao.deleteById(id);
        System.out.println(":::TESTE FINALIZADO:::");
    }
}
