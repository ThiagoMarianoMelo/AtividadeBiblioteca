
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca{


    //NÃO ESTA SENDO UTILIZADO COLECTIONS NESSE EXERCICIO POIS FOI DADO EM SALA DE AULA APÓS A REALIZAÇÃO DESTA ATIVIDADE!

    //Ler um arquivo txt contendo as informações dos LIVROS (Codigo;Titulo)
    //Ler um arquivo txt contendo as informações dos CLIENTES (Matricula;Nome)
    
    private Livro livros[] = new Livro[999]; 
    private int quantidadeLivros = 0;
    private Cliente clientes [] = new Cliente[999];
    private int quantidadeClientes = 0;
    public Emprestimo emprestimos[] = new Emprestimo[999];
    private int quantidadeEmprestimos = 0;
    
    public Biblioteca() throws IOException{

        livros = lerLivrosCadastrados();
        clientes = lerClientesCadastrados();

    }

    public synchronized Emprestimo cadastrarEmprestimo(Cliente cliente, Livro[] livrosDesejados) throws IOException, ExcecaoLivroIndisponivel{

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
            emprestimos[quantidadeEmprestimos] = new Emprestimo(cliente, livrosDesejados, dataAtual); //passa a data atual, isto é a data que foi feito o emprestimo
            Emprestimo emprestimoFeito = emprestimos[quantidadeEmprestimos];
            quantidadeEmprestimos++;

            for (Livro livro : livrosDesejados) {
                livro.setEmprestado(true);
            }

            return emprestimoFeito;

        }else{
            throw new ExcecaoLivroIndisponivel(livrosIndisponiveis);
        }

    }

    private boolean livroNaoDisponivel(Livro livro){

        return livro.isEmprestado();

    }

    public Livro[] lerLivrosCadastrados(){

        Livro[] livrosLidos = new Livro[999];

        String linha;
        try{

            BufferedReader br = inicializaLeitor("Livros.txt");


            linha = br.readLine(); 
            while(linha!=null){

                String codigo = linha.substring(0, linha.indexOf(';'));
		        String titulo = linha.substring(linha.lastIndexOf(';') + 1, linha.length());

                livrosLidos[quantidadeLivros] = new Livro(codigo, titulo); 

                quantidadeLivros++;

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

    public Cliente[] lerClientesCadastrados(){
        
        Cliente[] clientesLidos = new Cliente[999];

        String linha;

        try{

            BufferedReader br = inicializaLeitor("Clientes.txt");
            linha = br.readLine(); 
            while(linha!=null){

                String matricula = linha.substring(0, linha.indexOf(';'));
		        String nome = linha.substring(linha.lastIndexOf(';') + 1, linha.length());

                clientesLidos[quantidadeClientes] = new Cliente(matricula, nome); 

                quantidadeClientes++;

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

        Livro[] livrosDevolvidos = emprestimo.getLivrosEmprestimo();
        
        for (Livro livro : livrosDevolvidos) {
            livro.setEmprestado(false);
        }

    }

    public BufferedReader inicializaLeitor(String arquivoParaLer) throws FileNotFoundException{

        FileReader reader = new FileReader("./ArquivosTxt/"+arquivoParaLer);
		BufferedReader br = new BufferedReader(reader);

        return br;
    }

    public int getQuantidadeClientes() {
        return quantidadeClientes;
    }

    public int getQuantidadeLivros() {
        return quantidadeLivros;
    }

    public Cliente[] getClientes() {
        return clientes;
    }

    public Livro[] getLivros() {
        return livros;
    }

    public Emprestimo[] getEmprestimos() {
        return emprestimos;
    }

    public int getQuantidadeEmprestimos() {
        return quantidadeEmprestimos;
    }
}