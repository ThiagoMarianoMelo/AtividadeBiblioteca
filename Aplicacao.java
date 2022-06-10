import java.io.IOException;
import java.time.LocalDateTime;

//THIAGO MARIANO DE MELO /3°PERIODO/ ENG SOFTWARE/ PUC MINAS/ 2022

public class Aplicacao {

    public static void main(String []args){
        try{

            /********************************************** IMPORTANTE ***************************************************************************/
            //CASO QUEIRA TESTAR O CASO DE 2 USUARIOS ESCOLHEREM O MESMO LIVRO POR FAVOR ALTERAR A ESCOLHA DO LIVRO 2 PELO LIVRO 3 NA LINHA *57* //
            //ELE NÃO IRA CONSEGUIR REALIZAR O EMPRESTIMO E RECEBERA UMA EXCEPTION                                                               //
            //(LIVRO INDISPONIVEL APRESENTANDO ALEM DISSO TODOS OS LIVROS DO PEDIDO QUE ESTAVAM INDISPONIVEIS)                                   //    
            //NESSE CASO NÃO SERÁ EXIBIDO NADA DAQUELE PEDIDO NA TELA APENAS A MENSAGEM DA PROPRIA EXCEPTION                                     //
            /************************************************************************************************************************************/

            //NÃO ESTA SENDO UTILIZADO COLECTIONS NESSE EXERCICIO POIS FOI DADO EM SALA DE AULA APÓS A REALIZAÇÃO DESTA ATIVIDADE!
            //APENAS O QUE FOI FEITO PÓS ESSA AULA ESTA EM LIST

            LocalDateTime descobreDataAtual = LocalDateTime.now();

            Data dataAtual = new Data(descobreDataAtual.getDayOfMonth(), descobreDataAtual.getMonthValue(), descobreDataAtual.getYear());

            Biblioteca bibli = new Biblioteca();

            Cliente[] clientes = bibli.getClientes();

            Livro[] livros = bibli.getLivros();

            Thread user1 = new Thread(() ->{
                try {

                    Livro[] livrosDesejados1 = new Livro[1];

                    livrosDesejados1[0] = livros[3]; 

                    Emprestimo emprestimo1 = bibli.cadastrarEmprestimo(clientes[0], livrosDesejados1);

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

                    Livro[] livrosDesejados2 = new Livro[1];

                    livrosDesejados2[0] = livros[2];

                    Emprestimo emprestimo2 = bibli.cadastrarEmprestimo(clientes[1], livrosDesejados2);

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

                    Livro[] livrosDesejados3 = new Livro[1];

                    livrosDesejados3[0] = livros[1];

                    Emprestimo emprestimo3 = bibli.cadastrarEmprestimo(clientes[2], livrosDesejados3);

                    System.out.println("\nEMPRESTIMO 3 REALIZADO\n\n");

                    emprestimo3.renovarPrazoEmprestimo(dataAtual);

                    System.out.println("\nEMPRESTIMO 3 PRAZO RENOVADO\n\n");

                    bibli.fazerDevolucaoEmprestimo(emprestimo3);

                    System.out.println("\nEMPRESTIMO 3 DEVOLVIDO/FINALIZADO\n\n");
                    
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


