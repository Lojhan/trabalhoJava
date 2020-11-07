package assignment; import db.DB; import java.sql.SQLException; import java.text.*; import java.util.*;

public class Assignment {

    public static void createAssignment(DB db, Scanner scanner) {
        try {   System.out.print("Defina um título: "); String title = scanner.nextLine();
            System.out.print("Defina um texto: "); String text = scanner.nextLine();
            System.out.println("Defina um prazo (yyyy-MM-dd hh:mm:ss): "); String date = scanner.nextLine();
            Date testDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date);
            db.createAssignment(title, text, date); } catch (Exception e){ System.out.println("Houve um erro ao criar a tarefa!"); }}

    public static void assignmentWorkspace(DB db, Scanner scanner, String assignment) throws SQLException, ParseException { String option = "";
        while (!option.equals("0")) { db.selectAssignment(assignment);
            System.out.println("Escolha uma opção:\n1. Excluir\n2. Alterar Título\n3. Alterar Texto\n4. Alterar prazo\n5. Definir tarefa como concluída\n0. Sair para a tela de tarefas");
            option = scanner.nextLine();
            switch (option) {   case "1" -> { db.delete(assignment); option = "0"; }
                case "2" -> changeTitle(db, scanner, assignment);
                case "3" -> changeText(db, scanner, assignment);
                case "4" -> changeDeadline(db, scanner, assignment);
                case "5" -> conclude(db, assignment);
                case "0" -> System.out.println("Voltando..."); }}}

    public static void changeTitle(DB db, Scanner scanner, String assignment) {
        try {   System.out.println("Defina o novo título:"); String title = scanner.nextLine(); System.out.println("alterando título...");
            db.updateTitle(assignment, title); } catch (Exception e){ System.out.println("Houve um erro ao atualizar o título!"); }}

    public static void changeText(DB db, Scanner scanner, String assignment) {
        try {   System.out.println("Defina o novo texto: "); String content = scanner.nextLine(); System.out.println("alterando texto...");
            db.updateContent(assignment, content); } catch (Exception e){ System.out.println("Houve um erro ao atualizar o texto!"); }}

    public static void changeDeadline(DB db, Scanner scanner, String assignment) {
        try {   System.out.println("Defina o novo prazo (yyyy-MM-dd hh:mm:ss): "); String deadline = scanner.nextLine();
                Date testDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(deadline); System.out.println("alterando prazo...");
                db.updateDeadline(assignment, deadline); } catch (Exception e){ System.out.println("Houve um erro ao atualizar o prazo!"); }}

    public static void conclude(DB db, String assignment) {
        try { db.updateDone(assignment); } catch (Exception e){ System.out.println("Houve um erro ao atualizar concluir a tarefa!"); }}
}
