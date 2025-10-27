package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Aluno {
    private int id;
    private String nome;
    private String curso;
    private int idade;

    private static final String URL = "jdbc:mysql://localhost:3306/projeto_java";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public Aluno(){

    }

    public Aluno(String nome, String curso, int idade){
        this.setNome(nome);
        this.setCurso(curso);
        this.setIdade(idade);
    }

    public Aluno(int id){
        this.setId(id);
    }

    public Aluno(int id, String nome, String curso, int idade){
        this.setId(id);
        this.setNome(nome);
        this.setCurso(curso);
        this.setIdade(idade);
    }

    public static Connection conectar() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public void setCurso(String curso){
        this.curso = curso;
    }

    public String getCurso(){
        return this.curso;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }

    public int getIdade(){
        return this.idade;
    }

    public void inserirAluno(){
        String sql = "INSERT INTO alunos (nome, curso, idade) VALUES (?, ?, ?)";

        try(Connection conexao = conectar(); PreparedStatement statement = conexao.prepareStatement((sql))){
            statement.setString(1, this.nome);
            statement.setString(2, this.curso);
            statement.setInt(3, this.idade);

            statement.executeUpdate();
            System.out.println("Aluno inserido com sucesso!");
        }catch (Exception e){
            System.out.println("Erro ao inserir aluno: " + e.getMessage());
        }
    }

    public static void listarAlunos(){
         String sql = "SELECT * FROM alunos";
        try (Connection conexao = conectar(); PreparedStatement statement = conexao.prepareStatement(sql);
            ResultSet rs = statement.executeQuery()) {
                
                boolean temAlunos = false;
                System.out.println("Lista de Alunos:");

                while (rs.next()) {
                    temAlunos = true;
                    System.out.printf("%d - %s - %s - %d anos%n", rs.getInt("id"),
                    rs.getString("nome"), rs.getString(("curso")),
                    rs.getInt("idade"));
                }

                if (!temAlunos) {
                    System.out.println("Nenhum aluno cadastrado.");
                }
        
        }catch (Exception e){
            System.out.println("Erro ao listar alunos: " + e.getMessage());
        }
    }

    public void atualizarAluno(){
        String sql = "UPDATE alunos SET nome=?, curso=?, idade=? WHERE id=?";
        try(Connection conexao = conectar(); PreparedStatement statement = conexao.prepareStatement((sql))){
            statement.setString(1, this.nome);
            statement.setString(2, this.curso);
            statement.setInt(3, this.idade);
            statement.setInt(4, this.id);

            int linhas = statement.executeUpdate();
            System.out.println(linhas > 0 ? "Atualizado com sucesso!" : "Aluno não encontrado.");
        }catch (Exception e){
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    public void excluirAluno(){
        String sql = "DELETE FROM alunos WHERE id=?";
        try(Connection conexao = conectar(); PreparedStatement statement = conexao.prepareStatement((sql))){
            statement.setInt(1, this.id);

            int linhas = statement.executeUpdate();
            System.out.println(linhas > 0 ? "Excluído com sucesso!" : "Aluno não encontrado.");
        }catch (Exception e){
            System.out.println("Erro ao excluir: " + e.getMessage());
        }
    }

}
