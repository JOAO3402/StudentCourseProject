package application.apptests.studenttests;

import model.Course;
import model.Student;
import model.dao.CourseDao;
import model.dao.DaoFactory;
import model.dao.StudentDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InsertProgram {
    static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        CourseDao courseDao = DaoFactory.createCourseDao();
        StudentDao studentDao = DaoFactory.createStudentDao();

        System.out.println("INSERT STUDENT TEST");
        System.out.print("Insira o ID do Curso do estudante: ");
        Integer id = Integer.parseInt(sc.nextLine());
        Course course = courseDao.findById(id);
        System.out.print("Insira o nome do Aluno: ");
        String name = sc.nextLine();
        System.out.print("Insira a Data de nascimento do aluno: ");
        Date date = sdf.parse(sc.nextLine());
        System.out.print("Digite o Conceito Geral do aluno: ");
        Double grade = Double.parseDouble(sc.nextLine());
        Student student = new Student(null, name, date, course, grade);
        studentDao.insert(student);
        System.out.println(":::TESTE FINALIZADO:::");
    }
}
