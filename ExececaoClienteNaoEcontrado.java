public class ExececaoClienteNaoEcontrado extends Exception {
    String matriculaNaoEcontrada;

    public ExececaoClienteNaoEcontrado(String matriculaBuscada){
        super("A matricula: "+ matriculaBuscada +" nao foi encontrada nos registros!");
    }
}
