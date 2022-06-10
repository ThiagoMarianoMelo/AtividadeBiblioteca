import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Livro implements Serializable{
    private String codigo;
    private String titulo;
    private boolean emprestado;

    public Livro(String codigo, String titulo) throws IOException{

        setCodigo(codigo);
        setTitulo(titulo);

        salvarLivro(); //salvando Livro após cadastro do mesmo, no arquivo "./ArquivosBin/LivrosSalvos/.bin"
        
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void salvarLivro(){

        try{
            FileOutputStream file = new FileOutputStream("./ArquviosBin/LivrosSalvos.bin", true);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(this);

            out.close();
        }catch(IOException e){
            System.out.println("Erro na gravacao do Livro em formato binario");
        }
    }

    public boolean isEmprestado(){
        return emprestado;
    }

    public void setEmprestado(boolean emprestado){
        this.emprestado = emprestado;
    }

    @Override
    public String toString(){
        return this.getTitulo();
    }
}