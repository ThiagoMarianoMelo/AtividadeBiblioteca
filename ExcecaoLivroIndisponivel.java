import java.util.List;

public class ExcecaoLivroIndisponivel extends Exception{
    
    public ExcecaoLivroIndisponivel(List<Livro> livrosIndisponiveis){
        super("os seguintes livros estao indisponiveis: "+ livrosIndisponiveis.toString());
    }
}
