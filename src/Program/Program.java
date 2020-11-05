package Program; import assignment.Assignment; import db.DB; import java.sql.SQLException; import java.text.ParseException; import java.util.*;

public class Program {
    public static void main(String[] args) throws SQLException, ParseException {
        DB db = new DB(); Date date = new Date(System.currentTimeMillis()); Scanner scanner = new Scanner(System.in); String option;
        System.out.println("Suas tarefas:");
        do {    db.list(date);
                System.out.println("Escolha alguma tarefa para interagir\nOu 0 para sair e \"novo\" para criar uma nova tarefa"); option = scanner.nextLine();
                if (!option.toLowerCase().equals("0")) if (option.toLowerCase().equals("novo")) Assignment.createAssignment(db, scanner);
                else try { Assignment.assignmentWorkspace(db, scanner, option);} catch (Exception e) { System.out.println("Tarefa não encontrada!");
                }} while (!option.equals("0")); }
}
