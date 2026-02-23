package application.apptests.studenttests;

import model.Student;
import model.dao.DaoFactory;
import model.dao.StudentDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindAllProgram {
    static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        StudentDao studentDao = DaoFactory.createStudentDao();
        List<Student> list = new ArrayList<>();

        System.out.println("FIND ALL STUDENT TEST");
        list = studentDao.findAll();
        for(Student s: list){
            System.out.println(s);
        }
        System.out.println(":::TESTE FINALIZADO:::");
    }
}
