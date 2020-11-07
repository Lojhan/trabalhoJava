package db; import java.sql.*; import java.text.*; import java.util.Date;

public class DB { public DB() throws SQLException {}
    private final Connection connection = DriverManager.getConnection("jdbc:sqlite:src/db/db.db"); private ResultSet result;
    public void list(Date now) throws SQLException, ParseException { result = connection.createStatement().executeQuery("select * from tarefas"); while (result.next()) { Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(result.getString("deadline"));
        System.out.println("\033[0;36m" + result.getInt("id") + "\033[0m" + ". " + result.getString("title") + (result.getInt("done") == 1 ? "\u001B[36m" + " - Concluído" + "\u001B[0m" :(!date.after(now) ? "\u001B[31m" + " - Atrasado" + "\u001B[0m" : "\u001B[32m" + " - Em dia" + "\u001B[0m" )) +"\n "  + "prazo: " + date + "\n "  ); } }
    public void selectAssignment(String id) throws SQLException, ParseException { result = connection.createStatement().executeQuery("select * from tarefas where id = " + id);
        System.out.println(result.getString("title") + ":\n " + "prazo: " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(result.getString("deadline")) +  "\n " + result.getString("text")); }
    public void delete(String id) throws SQLException { System.out.println(connection.createStatement().executeUpdate("delete from tarefas where id = " + id) == 1 ? "Removido" : "Houve um erro ao processar a operação"); }
    public void updateDone(String id) throws SQLException { System.out.println(connection.createStatement().executeUpdate("update tarefas set done = '1' where id = " + id) == 1 ? "Concluído" : "Houve um erro ao processar a operação");  }
    public void updateTitle(String id, String updatedTitle) throws SQLException { System.out.println(connection.createStatement().executeUpdate("update tarefas set title = '" + updatedTitle + "' where id = " + id) == 1 ? "Título atualizado com sucesso" : "Houve um erro ao processar a operação"); }
    public void updateContent(String id, String updatedContent) throws SQLException { System.out.println(connection.createStatement().executeUpdate("update tarefas set text = '" + updatedContent + "' where id = " + id) == 1 ? "Conteúdo atualizado com sucesso" : "Houve um erro ao processar a operação");  }
    public void updateDeadline(String id, String updatedDeadline) throws SQLException { System.out.println(connection.createStatement().executeUpdate("update tarefas set deadline = '" + updatedDeadline + "' where id = " + id) == 1 ? "Prazo atualizado com sucesso" : "Houve um erro ao processar a operação");  }
    public void createAssignment(String title, String content, String date) throws SQLException { System.out.println(connection.createStatement().executeUpdate("insert into tarefas (title, text, deadline) values ('" + title + "','" + content + "','" + date + "')") == 1 ? "Tarefa criada com sucesso" : "Houve um erro ao processar a operação"); }
}
