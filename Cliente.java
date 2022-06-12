import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable {
    private String matricula;
    private String nome;
    private List<Emprestimo> emprestimosRealizados;

    public Cliente(String matricula, String nome) throws IOException{

        setMatricula(matricula);
        setNome(nome);
        emprestimosRealizados = new ArrayList<Emprestimo>();
        
        salvarCliente();//salvando Cliente após cadastro do mesmo, no arquivo "./ArquivosBin/ClientesSavlos/.bin"

    }   

    public void setMatricula(String matricula){
        this.matricula = matricula;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    public String getMatricula(){
        return matricula;
    }

    public void salvarCliente(){

        try{
            FileOutputStream file = new FileOutputStream("./ArquviosBin/ClientesSalvos.bin", true);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(this);

            out.close();
        }catch(IOException e){
            System.out.println("Erro na gravacao do cliente em formato binario");
        }
    }
    

    public void realizarDevolucaoCliente(Emprestimo emprestimo){
        emprestimosRealizados.remove(emprestimo);
    }   

    public void cadastrarEmprestimoCliente(Emprestimo emprestimo){
        emprestimosRealizados.add(emprestimo);
    }
    
    public List<Emprestimo> getEmprestimosRealizados() {
        return emprestimosRealizados;
    }



    @Override
    public String toString(){
        return "Matricula: "+getMatricula()+" Nome: "+getNome();
    }

}
