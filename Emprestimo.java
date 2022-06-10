import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Emprestimo implements Serializable{

    private static int contadorEmprestimos = 0;

    private int ID;
    private Cliente cliente;
    private Livro[] livrosEmprestimo = new Livro[999];
    private Data prazoEmprestimo;

    

    Emprestimo(Cliente cliente,Livro[] livros , Data dataAtual) throws IOException{

        contadorEmprestimos++;
        ID = contadorEmprestimos;
        this.livrosEmprestimo = livros;
        this.cliente = cliente;
        prazoEmprestimo = descobreDataEmprestimo(dataAtual); //prazoEmprestimo = data atual do pedido+7 dias(prazo definido pelo professor)

        salvarEmprestimo(); //salvando emprestimo após cadastro do mesmo, no arquivo "./ArquviosBin/EmprestimosSalvos.bin"

        
    }

    public void setCliente(Cliente cliente) {

        this.cliente = cliente;

    }

    public void setLivrosEmprestimo(Livro[] livrosEmprestimo) {

        this.livrosEmprestimo = livrosEmprestimo;

    }

    public Data descobreDataEmprestimo(Data dataAtualEmprestimo) {

        return dataAtualEmprestimo.adcionaDias(7);

    }

    public void renovarPrazoEmprestimo(Data dataAtual){ //atualiza data de emprestimo
        this.prazoEmprestimo = dataAtual.adcionaDias(7);
    }

    public void exibirEmprestimo(){
        System.out.println("Emprestimo de "+ this.cliente.getNome());
        System.out.println("Livros emprestados: ");
        for (Livro livro : livrosEmprestimo){
            System.out.println("Titulo: "+livro.getTitulo()+" //// Codigo: "+livro.getCodigo());
        }
        System.out.print("Prazo emprestimo: ");
        prazoEmprestimo.imprimeData();

        System.out.println("\nID: "+this.ID);
    }

    public void salvarEmprestimo(){

        try{
            FileOutputStream file = new FileOutputStream("./ArquviosBin/EmprestimosSalvos.bin", true);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(this);

            out.close();
        }catch(IOException e){
            System.out.println("Erro na gravacao do emprestimo em formato binario");
        }

    }

    public Livro[] getLivrosEmprestimo(){
        return this.livrosEmprestimo;
    }

}
