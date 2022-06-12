public class ExcecaoOpcaoInvalida extends Exception{
    private String opcaoInvalida;

    public ExcecaoOpcaoInvalida(String opcaoInvalida){
        super("A opcao: "+ opcaoInvalida +" nao é valida no contexto que esta sendo utilizada");
        this.opcaoInvalida = opcaoInvalida;
    }

    public String getOpcaoInvalida() {
        return opcaoInvalida;
    }

}
