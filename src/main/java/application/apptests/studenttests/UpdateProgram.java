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

public class UpdateProgram {
    static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        StudentDao studentDao = DaoFactory.createStudentDao();
        CourseDao courseDao = DaoFactory.createCourseDao();

        System.out.println("UPDATE STUDENT TEST");
        System.out.print("Digite o novo Id do curso: ");
        Integer cId = Integer.parseInt(sc.nextLine());
        Course course = courseDao.findById(cId);
        System.out.print("Digite o id do aluno: ");
        Integer id = Integer.parseInt(sc.nextLine());
        System.out.println("Digite os novos dados do aluno:");
        System.out.print("nome: ");
        String name = sc.nextLine();
        System.out.print("Data de nascimento: ");
        Date date = sdf.parse(sc.nextLine());
        System.out.print("Conceito Geral (CR): ");
        Double grade = Double.parseDouble(sc.nextLine());
        Student student = new Student(id, name, date, course, grade);
        studentDao.update(student);
        System.out.println(":::TESTE FINALIZADO:::");
    }
}
