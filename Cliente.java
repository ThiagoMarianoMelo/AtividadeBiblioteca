import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Cliente implements Serializable {
    private String matricula;
    private String nome;

    public Cliente(String matricula, String nome) throws IOException{

        setMatricula(matricula);
        setNome(nome);
        
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

}
