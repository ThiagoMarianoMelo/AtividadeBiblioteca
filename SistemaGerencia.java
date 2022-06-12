import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


//THIAGO MARIANO DE MELO /3°PERIODO/ ENG SOFTWARE/ PUC MINAS/ 2022/ FINALIZADO NO DIA 12/06/2022

public class SistemaGerencia {

    public static void main(String[]args){

        Scanner sc = new Scanner(System.in);

        try{

            //CONSIDERE A MATRICULA 1305129 DO CLIENTE: THIAGO MARIANO DE MELO CONSTADO NO ARQUIVO TXT PARA TESTAR


            Biblioteca biblioteca = new Biblioteca();

            Menu menu =  new Menu(biblioteca);

            List<Cliente> clientesBiblioteca =  biblioteca.getClientes();

            List<Livro> livrosBiblioteca =  biblioteca.getLivros();

            List<Livro> livrosEmprestimo1 = new ArrayList<Livro>();
            
            Data data1 =  new Data(22, 07, 2021);

            Data data2 =  new Data(12, 06, 2022);

            Data data3 =  new Data(17, 07, 2020);


            livrosEmprestimo1.add(livrosBiblioteca.get(0));
            livrosEmprestimo1.add(livrosBiblioteca.get(1));

            List<Livro> livrosEmprestimo2 = new ArrayList<Livro>();

            livrosEmprestimo2.add(livrosBiblioteca.get(2));

            List<Livro> livrosEmprestimo3 = new ArrayList<Livro>();

            livrosEmprestimo3.add(livrosBiblioteca.get(3));


            //IMPORTANTE: ESSE METODO FOI CRIADO APENAS PARA FACILITAR A CORREÇÃO E TESTES DO SISTEMA NAO DEVERA SER USADO NA IMPLEMENTACAO REAL!!!
            //NELE É POSSIVEL ESCOLHER UMA DATA PARA QUAL O EMPRESTIMO ESTA OCORRENDO SENDO POSSIVEL TESTAR OS METODOS DE MENU!!!
            biblioteca.cadastrarEmprestimoComDataFicticia(clientesBiblioteca.get(0), livrosEmprestimo1,data1);
            biblioteca.cadastrarEmprestimoComDataFicticia(clientesBiblioteca.get(0), livrosEmprestimo2,data2);
            biblioteca.cadastrarEmprestimoComDataFicticia(clientesBiblioteca.get(0), livrosEmprestimo3,data3);

            System.out.println("\nEscolha uma opcao\n");

            int opcao;

            do{
                System.out.println("Digite 1 para: Localizar um cliente pela matricula");
                System.out.println("Digite 2 para: Mostrar o emprestimo mais recente de um cliente");
                System.out.println("Digite 3 para: Mostrar todos emprestimos em atraso");
                System.out.println("Digite 4 para: Exibir quantos emprestimos foram feito no mes atual"); //MES ATUAL OU ULTIMO 30 DIAS?
                System.out.println("Digite 0 para: SAIR");
    
                opcao =  sc.nextInt();
    
                menu.realizarAcao(opcao);

                TimeUnit.SECONDS.sleep(4);

                System.out.println("\n\n");

            }while(opcao!=0);

        }catch(IOException | ExececaoClienteNaoEcontrado | ExcecaoLivroIndisponivel |InterruptedException | ExcecaoOpcaoInvalida e){

            System.out.println(e);

        }catch(Exception e){

            System.out.println("ALGUM ERRO INESPERADO OCORREU!");

        }finally{

            sc.close();
        }

    }

    public static class Menu{

        Scanner sc = new Scanner(System.in);

        public int opcaoAcao;
        private Biblioteca biblioteca;

        public Menu(Biblioteca biblioteca){
            setBiblioteca(biblioteca);
        }

        
        public void realizarAcao(int opcaoAcao) throws ExececaoClienteNaoEcontrado, ExcecaoOpcaoInvalida{
            setOpcaoAcao(opcaoAcao);

            switch (getOpcaoAcao()){
                case 1:
                    System.out.println("Digite a matricula do usuario: ");
                    String matricula1 = sc.nextLine();
                    System.out.println("\n");
                    System.out.println("DADOS:");
                    Cliente cliente1  = localizarCliente(matricula1);
                    gerarRelatorioGeralCliente(cliente1);
                    break;
                case 2:
                    System.out.println("Digite a matricula do usuario: ");
                    String matricula2 = sc.nextLine();
                    System.out.println("\n");
                    Cliente cliente2  = localizarCliente(matricula2);
                    gerarRelatorioUltimoEmprestimo(cliente2);
                    break;
                case 3:
                    gerarRelatorioEmprestimoEmAtraso();
                    break;
                case 4:
                    gerarRelatorioEmprestimosMes();
                    break;
                default:
                    throw new ExcecaoOpcaoInvalida(String.valueOf(getOpcaoAcao()));

            }

        }
        
        public void setOpcaoAcao(int opcaoAcao) {
            this.opcaoAcao = opcaoAcao;
        }

        public void setBiblioteca(Biblioteca biblioteca) {
            this.biblioteca = biblioteca;
        }

        public Biblioteca getBiblioteca() {
            return biblioteca;
        }

        public int getOpcaoAcao() {
            return opcaoAcao;
        }

        private Cliente localizarCliente(String matricula) throws ExececaoClienteNaoEcontrado{

            List<Cliente> clientesBiblioteca = getBiblioteca().getClientes();

            Cliente clienteEcontrado = null;
            
            for (Cliente cliente : clientesBiblioteca) {
                if(cliente.getMatricula().equals(matricula)){

                    clienteEcontrado = cliente;
                    
                }
            }

            if(clienteEcontrado != null){
                return clienteEcontrado;
            }else{
                throw new ExececaoClienteNaoEcontrado(matricula);
            }
        }


        private void gerarRelatorioGeralCliente(Cliente cliente){

            List<Emprestimo> emprestimosFeitoPeloCliente = cliente.getEmprestimosRealizados();

                System.out.println(cliente.toString()); 

                System.out.println("\nEmprestimos Realizadaos: \n");

                for (Emprestimo emprestimo : emprestimosFeitoPeloCliente) {
                    emprestimo.exibirEmprestimo();
                    }
                
        }

        private void gerarRelatorioUltimoEmprestimo(Cliente cliente){

            List<Emprestimo> emprestimosFeitoPeloCliente = cliente.getEmprestimosRealizados();

            System.out.println(cliente.toString()); 

            System.out.println("\nEmprestimo mais recente do cliente: \n");

            List<Emprestimo> emprestimosOrdenadosPorData = 
                emprestimosFeitoPeloCliente.stream().sorted((x,y) -> x.getDataEmprestimoRealizado().comparaData(y.getDataEmprestimoRealizado())).toList();

            //ordena os emprestimos por ordem e depois retorno com a maior data, isto é o mais recente e ultimo da lista

            Emprestimo emprestimoMaisRecente = emprestimosOrdenadosPorData.get(emprestimosOrdenadosPorData.size()-1);
            
            emprestimoMaisRecente.exibirEmprestimo();
        } 
        
        private void gerarRelatorioEmprestimoEmAtraso(){

            LocalDateTime descobreDataAtual = LocalDateTime.now();

            Data dataAtual = new Data(descobreDataAtual.getDayOfMonth(), descobreDataAtual.getMonthValue(), descobreDataAtual.getYear());//descobre data atual e passa para classe DATA 

            System.out.println("\nEmprestimos que estao em atraso: \n");

            List<Emprestimo> emprestimosBiblioteca = getBiblioteca().getEmprestimos();

            List<Emprestimo> emprestimosEmAtraso = emprestimosBiblioteca.stream().filter((emprestimo) -> emprestimo.getPrazoEmprestimo().comparaData(dataAtual) < 0 ).toList();

            for (Emprestimo emprestimo : emprestimosEmAtraso) {
                emprestimo.exibirEmprestimo();
            }
        }

        private void gerarRelatorioEmprestimosMes(){

            LocalDateTime descobreDataAtual = LocalDateTime.now();

            Data dataAtual = new Data(descobreDataAtual.getDayOfMonth(), descobreDataAtual.getMonthValue(), descobreDataAtual.getYear());//descobre data atual e passa para classe DATA 


            System.out.println("\nEmprestimos do mes atual: \n");

            List<Emprestimo> emprestimosBiblioteca = getBiblioteca().getEmprestimos();

            List<Emprestimo> emprestimosRealiadosNesseMes = 
            emprestimosBiblioteca.stream()
            .filter((e) -> e.getDataEmprestimoRealizado().getMes() == dataAtual.getMes() && e.getDataEmprestimoRealizado().getAno() == dataAtual.getAno()).toList();

            for (Emprestimo emprestimo : emprestimosRealiadosNesseMes) {
                emprestimo.exibirEmprestimo();
            }

        }
    }
}
