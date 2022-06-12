
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca{


    //ADCIONADO USO DE COLECTIONS EM TODOS OS CASOS ONDE ESTAVA ANTERIORMENTE SENDO USADO VETOR!

    //Ler um arquivo txt contendo as informações dos LIVROS (Codigo;Titulo)
    //Ler um arquivo txt contendo as informações dos CLIENTES (Matricula;Nome)
    
    private List<Livro> livros = new ArrayList<Livro>(); 
    private List<Cliente> clientes = new ArrayList<Cliente>();
    public List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
    
    public Biblioteca() throws IOException{

        livros = lerLivrosCadastrados();
        clientes = lerClientesCadastrados();

    }

    public synchronized Emprestimo cadastrarEmprestimo(Cliente cliente, List<Livro> livrosDesejados) throws IOException, ExcecaoLivroIndisponivel{

        LocalDateTime descobreDataAtual = LocalDateTime.now();

        boolean livrosDisponiveis = true;

        List<Livro> livrosIndisponiveis = new ArrayList<Livro>();
        
        for (Livro livro : livrosDesejados) {
            if(livroNaoDisponivel(livro)){
                livrosDisponiveis = false;
                livrosIndisponiveis.add(livro);
            }
        }

        if(livrosDisponiveis){

            Data dataAtual = new Data(descobreDataAtual.getDayOfMonth(), descobreDataAtual.getMonthValue(), descobreDataAtual.getYear());//descobre data atual
            Emprestimo emprestimoCriado =  new Emprestimo(cliente, livrosDesejados,dataAtual); //passa a data atual, isto é a data que foi feito o emprestimo

            emprestimos.add(emprestimoCriado);
            cliente.cadastrarEmprestimoCliente(emprestimoCriado);

            for (Livro livro : livrosDesejados) {
                livro.setEmprestado(true);
            }

            return emprestimoCriado;

        }else{
            throw new ExcecaoLivroIndisponivel(livrosIndisponiveis);
        }

    }

    //IMPORTANTE: ESSE METODO FOI CRIADO APENAS PARA FACILITAR A CORREÇÃO E TESTES DO SISTEMA NAO DEVERA SER USADO NA IMPLEMENTACAO REAL!!!
    //NELE É POSSIVEL ESCOLHER UMA DATA PARA QUAL O EMPRESTIMO ESTA OCORRENDO SENDO POSSIVEL TESTAR OS METODOS DE MENU!!!
    public synchronized Emprestimo cadastrarEmprestimoComDataFicticia(Cliente cliente, List<Livro> livrosDesejados,Data data) throws IOException, ExcecaoLivroIndisponivel{

        LocalDateTime descobreDataAtual = LocalDateTime.now();

        boolean livrosDisponiveis = true;

        List<Livro> livrosIndisponiveis = new ArrayList<Livro>();
        
        for (Livro livro : livrosDesejados) {
            if(livroNaoDisponivel(livro)){
                livrosDisponiveis = false;
                livrosIndisponiveis.add(livro);
            }
        }

        if(livrosDisponiveis){

            Emprestimo emprestimoCriado =  new Emprestimo(cliente, livrosDesejados,data); //passa a data atual, isto é a data que foi feito o emprestimo

            emprestimos.add(emprestimoCriado);
            cliente.cadastrarEmprestimoCliente(emprestimoCriado);

            for (Livro livro : livrosDesejados) {
                livro.setEmprestado(true);
            }

            return emprestimoCriado;

        }else{
            throw new ExcecaoLivroIndisponivel(livrosIndisponiveis);
        }

    }

    private boolean livroNaoDisponivel(Livro livro){

        return livro.isEmprestado();

    }

    public List<Livro> lerLivrosCadastrados(){

        List<Livro> livrosLidos =  new ArrayList<Livro>();

        String linha;
        try{

            BufferedReader br = inicializaLeitor("Livros.txt");


            linha = br.readLine(); 

            while(linha!=null){

                String codigo = linha.substring(0, linha.indexOf(';'));
		        String titulo = linha.substring(linha.lastIndexOf(';') + 1, linha.length());

                Livro livroLido = new Livro(codigo, titulo); 

                livrosLidos.add(livroLido);

                linha = br.readLine();
            
        }
        
        br.close();

        }catch(FileNotFoundException e){
            System.out.print("Arquivo: Livros.Txt Não encontrado");
        }catch(IOException e){
            System.out.println("Erro na leitura do dados do arquivo de Livros");
        }


        return livrosLidos;

    }

    public List<Cliente> lerClientesCadastrados(){
        
        List<Cliente> clientesLidos =  new ArrayList<Cliente>();

        String linha;

        try{

            BufferedReader br = inicializaLeitor("Clientes.txt");
            linha = br.readLine(); 
            while(linha!=null){

                String matricula = linha.substring(0, linha.indexOf(';'));
		        String nome = linha.substring(linha.lastIndexOf(';') + 1, linha.length());

                Cliente clientesLido =  new Cliente(matricula, nome); 

                clientesLidos.add(clientesLido);

                linha = br.readLine();
            
        }
        
        br.close();

        }catch(FileNotFoundException e){
            System.out.print("Arquivo: Clientes.Txt Não encontrado");
        }catch(IOException e){
            System.out.println("Erro na leitura do dados do arquivo de Clientes");
        }

        return clientesLidos;
        
    }

    public void fazerDevolucaoEmprestimo(Emprestimo emprestimo){

        List<Livro> livrosDevolvidos = emprestimo.getLivrosEmprestimo();
        
        for (Livro livro : livrosDevolvidos) {
            livro.setEmprestado(false);
        }

        emprestimos.remove(emprestimo);
        Cliente clienteDoEmprestimo = emprestimo.getCliente();

        clienteDoEmprestimo.realizarDevolucaoCliente(emprestimo);
    }

    public BufferedReader inicializaLeitor(String arquivoParaLer) throws FileNotFoundException{

        FileReader reader = new FileReader("./ArquivosTxt/"+arquivoParaLer);
		BufferedReader br = new BufferedReader(reader);

        return br;
    }
    
    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public List<Livro> getLivros() {

        return livros;

    }

    
}