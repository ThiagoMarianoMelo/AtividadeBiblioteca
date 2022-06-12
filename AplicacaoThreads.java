import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//THIAGO MARIANO DE MELO /3°PERIODO/ ENG SOFTWARE/ PUC MINAS/ 2022

public class AplicacaoThreads {

    public static void main(String []args){
        try{

            /********************************************** IMPORTANTE ***************************************************************************/
            //CASO QUEIRA TESTAR O CASO DE 2 USUARIOS ESCOLHEREM O MESMO LIVRO POR FAVOR ALTERAR A ESCOLHA DO LIVRO 2 PELO LIVRO 3 NA LINHA *69* //
            //ELE NÃO IRA CONSEGUIR REALIZAR O EMPRESTIMO E RECEBERA UMA EXCEPTION                                                               //
            //(LIVRO INDISPONIVEL APRESENTANDO ALEM DISSO TODOS OS LIVROS DO PEDIDO QUE ESTAVAM INDISPONIVEIS)                                   //    
            //NESSE CASO NÃO SERÁ EXIBIDO NADA DAQUELE PEDIDO NA TELA APENAS A MENSAGEM DA PROPRIA EXCEPTION                                     //
            /************************************************************************************************************************************/

            //NÃO ESTA SENDO UTILIZADO COLECTIONS NESSE EXERCICIO POIS FOI DADO EM SALA DE AULA APÓS A REALIZAÇÃO DESTA ATIVIDADE!
            //APENAS O QUE FOI FEITO PÓS ESSA AULA ESTA EM LIST

            LocalDateTime descobreDataAtual = LocalDateTime.now();

            Data dataAtual = new Data(descobreDataAtual.getDayOfMonth(), descobreDataAtual.getMonthValue(), descobreDataAtual.getYear());

            Biblioteca bibli = new Biblioteca();

            List<Cliente> clientes = bibli.getClientes();

            List<Livro> livros = bibli.getLivros();


            System.out.println("\nEXEMPLO THREADS: \n");


            Thread user1 = new Thread(() ->{
                try {

                    List<Livro> livrosDesejados1 = new ArrayList<Livro>();

                    livrosDesejados1.add(livros.get(3));

                    Emprestimo emprestimo1 = bibli.cadastrarEmprestimo(clientes.get(1), livrosDesejados1);

                    System.out.println("\nEMPRESTIMO 1 REALIZADO\n\n");

                    emprestimo1.renovarPrazoEmprestimo(dataAtual);

                    System.out.println("\nEMPRESTIMO 1 PRAZO RENOVADO\n\n");
                
                    bibli.fazerDevolucaoEmprestimo(emprestimo1);

                    System.out.println("\nEMPRESTIMO 1 DEVOLVIDO/FINALIZADO\n\n");
                    
                } catch (IOException | ExcecaoLivroIndisponivel e) {
                   System.out.println(e);
                   Thread.interrupted();
                }
            });


            Thread user2 = new Thread(() ->{
                try {

                    List<Livro> livrosDesejados2 = new ArrayList<Livro>();
                    livrosDesejados2.add(livros.get(2));

                    Emprestimo emprestimo2 = bibli.cadastrarEmprestimo(clientes.get(2), livrosDesejados2);

                    System.out.println("\nEMPRESTIMO 2 REALIZADO\n\n");

                    emprestimo2.renovarPrazoEmprestimo(dataAtual);

                    System.out.println("\nEMPRESTIMO 2 PRAZO RENOVADO\n\n");
                
                    bibli.fazerDevolucaoEmprestimo(emprestimo2);

                    System.out.println("\nEMPRESTIMO 2 DEVOLVIDO/FINALIZADO\n\n");
                    
                } catch (IOException | ExcecaoLivroIndisponivel e) {
                    System.out.println(e);
                    Thread.interrupted();
                }
            });

            Thread user3 = new Thread(() ->{
                try {

                    List<Livro> livrosDesejados3 = new ArrayList<Livro>();

                    livrosDesejados3.add(livros.get(1));

                    Emprestimo emprestimo3 = bibli.cadastrarEmprestimo(clientes.get(3), livrosDesejados3);

                    System.out.println("\nEMPRESTIMO 2 REALIZADO\n\n");

                    emprestimo3.renovarPrazoEmprestimo(dataAtual);

                    System.out.println("\nEMPRESTIMO 2 PRAZO RENOVADO\n\n");
                
                    bibli.fazerDevolucaoEmprestimo(emprestimo3);

                    System.out.println("\nEMPRESTIMO 2 DEVOLVIDO/FINALIZADO\n\n");
                    
                } catch (IOException | ExcecaoLivroIndisponivel e) {
                    System.out.println(e);
                    Thread.interrupted();
                }
            });


            user1.start();
            
            user2.start();

            user3.start();
            

        }catch(IOException e ){

            System.out.println(e);

        }

    }
}


