import java.util.InputMismatchException;
import java.util.Scanner;

import Classes.Aluno;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String opcao;
        do{
            System.out.println("\n==== MENU ====");
            System.out.println("1 - Inserir aluno");
            System.out.println("2 - Listar alunos");
            System.out.println("3 - Atualizar aluno");
            System.out.println("4 - Excluir aluno");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextLine();

            switch (opcao) {
                case "1" -> {
                    String nome, curso;
                    int idade;

                    while (true) {
                        System.out.print("Nome: ");
                        nome = sc.nextLine().trim();
                        if (!nome.isEmpty())
                            break;
                        System.out.println("Nome não pode estar vazio.");
                    }

                    while (true) {
                        System.out.print("Curso: ");
                        curso = sc.nextLine().trim();
                        if (!curso.isEmpty())
                            break;
                        System.out.println("Curso não pode estar vazio.");
                    }

                    while (true) {
                        System.out.print("Idade: ");
                        try {
                            idade = sc.nextInt();
                            sc.nextLine();
                            if (idade > 0 && idade < 120)
                                break;
                            System.out.println("Digite uma idade válida (1 a 119).");
                        } catch (InputMismatchException e) {
                            System.out.println("Digite um número inteiro válido.");
                            sc.nextLine();
                        }
                    }
                    
                    Aluno aluno = new Aluno();
                    
                    aluno.setNome(nome);
                    aluno.setCurso(curso);
                    aluno.setIdade(idade);
                    aluno.inserirAluno();
                    break;
                }
                case "2" -> Aluno.listarAlunos();
                case "3" -> {
                    int id, idade;
                    String nome, curso;

                    while (true) {
                        System.out.print("ID: ");
                        try {
                            id = sc.nextInt();
                            sc.nextLine();
                            if (id > 0)
                                break;
                            System.out.println("ID deve ser maior que zero.");
                        } catch (InputMismatchException e) {
                            System.out.println("Digite um número inteiro válido para o ID.");
                            sc.nextLine();
                        }
                    }

                    while (true) {
                        System.out.print("Novo nome: ");
                        nome = sc.nextLine().trim();
                        if (!nome.isEmpty())
                            break;
                        System.out.println("Nome não pode estar vazio.");
                    }

                    while (true) {
                        System.out.print("Novo curso: ");
                        curso = sc.nextLine().trim();
                        if (!curso.isEmpty())
                            break;
                        System.out.println("Curso não pode estar vazio.");
                    }

                    while (true) {
                        System.out.print("Nova idade: ");
                        try {
                            idade = sc.nextInt();
                            sc.nextLine();
                            if (idade > 0 && idade < 120)
                                break;
                            System.out.println("Digite uma idade válida (1 a 119).");
                        } catch (InputMismatchException e) {
                            System.out.println("Digite um número inteiro válido.");
                            sc.nextLine();
                        }
                    }

                    Aluno aluno = new Aluno();

                    aluno.setId(id);
                    aluno.setNome(nome);
                    aluno.setCurso(curso);
                    aluno.setIdade(idade);
                    aluno.atualizarAluno();
                }
                case "4" -> {
                    int id;

                    while (true) {
                        System.out.print("ID: ");
                        try {
                            id = sc.nextInt();
                            sc.nextLine();
                            if (id > 0)
                                break;
                            System.out.println("ID deve ser maior que zero.");
                        } catch (InputMismatchException e) {
                            System.out.println("Digite um número inteiro válido.");
                            sc.nextLine();
                        }
                    }

                    Aluno aluno = new Aluno();
                    aluno.setId(id);
                    aluno.excluirAluno();
                }
                case "0" -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida!");
            }

        }while (opcao.equals("0") == false);

        sc.close();
    }
}
