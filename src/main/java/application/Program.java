package application;

import model.Course;
import model.Student;
import model.connection.BD;
import model.dao.CourseDao;
import model.dao.StudentDao;
import model.dao.imp.CourseDaoJDBC;
import model.dao.imp.StudentDaoJDBC;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Connection conn = BD.getConnection();

        CourseDao courseDao = new CourseDaoJDBC(conn);
        StudentDao studentDao = new StudentDaoJDBC(conn);

        int opcao;

        do {

            System.out.println("\n=========== SISTEMA ACADÊMICO ===========");
            System.out.println("--------------- CURSOS ------------------");
            System.out.println("1  - Buscar curso por ID");
            System.out.println("2  - Listar todos os cursos");
            System.out.println("3  - Inserir novo curso");
            System.out.println("4  - Atualizar curso existente");
            System.out.println("5  - Excluir curso por ID");
            System.out.println("--------------- ALUNOS ------------------");
            System.out.println("6  - Buscar aluno por ID");
            System.out.println("7  - Listar todos os alunos");
            System.out.println("8  - Listar alunos por curso");
            System.out.println("9  - Inserir novo aluno");
            System.out.println("10 - Atualizar aluno existente");
            System.out.println("11 - Excluir aluno por ID");
            System.out.println("0  - Encerrar programa");
            System.out.print("Escolha uma opção: ");

            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {

                // ================== COURSE ==================

                case 1:
                    System.out.print("Informe o ID do curso: ");
                    Integer idCourse = Integer.parseInt(sc.nextLine());
                    Course course = courseDao.findById(idCourse);
                    System.out.println();
                    System.out.println(course);
                    break;

                case 2:
                    List<Course> courses = courseDao.findAll();
                    System.out.println();
                    courses.forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Informe o nome do novo curso: ");
                    String name = sc.nextLine();
                    Course newCourse = new Course();
                    newCourse.setName(name);
                    courseDao.insert(newCourse);
                    break;

                case 4:
                    System.out.print("Informe o ID do curso a ser atualizado: ");
                    Integer updateId = Integer.parseInt(sc.nextLine());
                    System.out.print("Informe o novo nome do curso: ");
                    String newName = sc.nextLine();
                    Course updateCourse = new Course();
                    updateCourse.setId(updateId);
                    updateCourse.setName(newName);
                    courseDao.update(updateCourse);
                    break;

                case 5:
                    System.out.print("Informe o ID do curso a ser excluído: ");
                    Integer deleteId = Integer.parseInt(sc.nextLine());
                    courseDao.deleteById(deleteId);
                    break;

                // ================== STUDENT ==================

                case 6:
                    System.out.print("Informe o ID do aluno: ");
                    Integer idStudent = Integer.parseInt(sc.nextLine());
                    Student student = studentDao.findById(idStudent);
                    System.out.println();
                    System.out.println(student);
                    break;

                case 7:
                    List<Student> students = studentDao.findAll();
                    System.out.println();
                    students.forEach(System.out::println);
                    break;

                case 8:
                    System.out.print("Informe o ID do curso: ");
                    Integer courseId = Integer.parseInt(sc.nextLine());
                    List<Student> studentsByCourse = studentDao.findByCourse(courseId);
                    System.out.println();
                    studentsByCourse.forEach(System.out::println);
                    break;

                case 9:
                    try {
                        Student newStudent = new Student();

                        System.out.print("Nome do aluno: ");
                        newStudent.setName(sc.nextLine());

                        System.out.print("Data de nascimento (dd/MM/yyyy): ");
                        Date birthDate = sdf.parse(sc.nextLine());
                        newStudent.setBirthDate(birthDate);

                        System.out.print("Nota final: ");
                        newStudent.setGrade(Double.parseDouble(sc.nextLine()));

                        System.out.print("ID do curso: ");
                        Integer cId = Integer.parseInt(sc.nextLine());
                        Course courseRef = courseDao.findById(cId);

                        newStudent.setCourse(courseRef);

                        studentDao.insert(newStudent);
                        System.out.println();

                    } catch (Exception e) {
                        System.out.println("Erro ao inserir aluno: " + e.getMessage());
                    }
                    break;

                case 10:
                    try {
                        Student updateStudent = new Student();

                        System.out.print("ID do aluno: ");
                        updateStudent.setId(Integer.parseInt(sc.nextLine()));

                        System.out.print("Novo nome: ");
                        updateStudent.setName(sc.nextLine());

                        System.out.print("Nova data de nascimento (dd/MM/yyyy): ");
                        Date birthDate = sdf.parse(sc.nextLine());
                        updateStudent.setBirthDate(birthDate);

                        System.out.print("Nova nota: ");
                        updateStudent.setGrade(Double.parseDouble(sc.nextLine()));

                        System.out.print("Novo ID do curso: ");
                        Integer cId = Integer.parseInt(sc.nextLine());
                        Course courseRef = courseDao.findById(cId);

                        updateStudent.setCourse(courseRef);

                        studentDao.update(updateStudent);
                        System.out.println();

                    } catch (Exception e) {
                        System.out.println("Erro ao atualizar aluno: " + e.getMessage());
                    }
                    break;

                case 11:
                    System.out.print("Informe o ID do aluno a ser excluído: ");
                    Integer deleteStudentId = Integer.parseInt(sc.nextLine());
                    studentDao.deleteById(deleteStudentId);
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 0);

        BD.closeConnection(conn);
        sc.close();
    }
}
