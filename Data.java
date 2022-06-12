import java.io.Serializable;

public class Data implements Serializable{

    private int dia;
    private int mes;
    private int ano;


    //ESTA CLASSE FOI DESENVOLVIDA MESES ANTES DO SISTEMA DE BIBLIOTECA, APENAS ALGUMAS ALTERACOES FORAM FEITAS PARA FUNCIONAR DENTRO DO SISTEMA BIBLIOTECA
    //POREM CONTINUA COM DIVERSOS ERROS DE CONSISTENCIA, ACOPLAMENTO E COESAO, SENDO ELES INDIFERENTES PARA O SISTEMA EM QUESTAO!
    

    public Data(int dia, int mes,int ano){
        
       if(testeData(dia,mes,ano)){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
       }
       
    }
    private boolean testeData(int dia,int mes,int ano){
        if(dia <= 31 && dia > 0 && mes <= 12 && mes > 0 ){
            if(mes == 2){
                if( dia > 29){
                    return false;
                }else if( dia == 29 && ano%4 != 0){
                    return false;
                }else{
                    return true;
                }
            }
            if(  mes == 4 || mes == 6   || mes == 9 || mes == 11 ){
                if( dia <= 30 ){
                    return true;
                }
                return false;
            }
            return true;
        }else{
            return false;
        }
    }
    public Data adcionaDias(int Adcionar){

        int dia,mes,ano;
        Data dataAtualizada;

        dia = this.dia+Adcionar;
        mes = this.mes;
        ano = this.ano;

        if(testeData(dia,mes,ano)){
            dataAtualizada = new Data(dia, mes, ano);
            return(dataAtualizada);
        }else {
            while(!testeData(dia,mes,ano)){
                if(mes == 2){
                    if(ano%4 == 0){
                        dia = dia - 29;
                        mes = mes + 1;
                        if(mes == 13){
                            mes = mes - 12;
                            ano = ano + 1;
                        }
                    }else{
                        dia = dia - 28;
                        mes = mes + 1;
                        if(mes == 13){
                            mes = mes - 12;
                            ano = ano + 1;
                        }
                    }
                }else if(mes == 4 || mes == 6   || mes == 9 || mes == 11){

                        dia = dia - 30;
                        mes = mes + 1;
                        if(mes == 13){
                            mes = mes - 12;
                            ano = ano + 1;
                        }

                }else{
                        dia = dia - 31;
                        mes = mes + 1;
                        if(mes == 13){
                            mes = mes - 12;
                            ano = ano + 1;
                        }
                }
            }
             dataAtualizada = new Data(dia, mes, ano);
            return(dataAtualizada);         
        }


    }
    public int comparaData(Data Data1){

        int ano1 = Data1.getAno();
        int mes1 = Data1.getMes();
        int dia1 = Data1.getDia();

        if(ano1 == getAno()){
            if(mes1 == getMes()){
                if(dia1 == getDia()){
                    return 0;
                }else{
                    if(dia1 > getDia()){
                        return -1;
                    }else if( getDia() > dia1){
                        return 1;}
                    }
            }else{
                if(mes1 > getMes()){
                    return -1;
                }else if(getMes() > mes1){
                    return 1;}
                }
        }else{
            if(ano1 > getAno()){
                return -1;
            }else if( getAno() > ano1){
                return 1;}
            }
        return 0;
    }

    public void imprimeData(){
        if(dia<10 && mes<10){
            System.out.print("0"+this.dia+"/0"+this.mes+"/"+this.ano);
        }else if(dia<10){
            System.out.print("0"+this.dia+"/"+this.mes+"/"+this.ano);
        }else if(mes<10){
            System.out.print(""+this.dia+"/0"+this.mes+"/"+this.ano);
        }else{
            System.out.print(""+this.dia+"/"+this.mes+"/"+this.ano);
        }
    }

    public String toString(){

        String data ="";

        if(dia<10 && mes<10){
            data = "0"+this.dia+"/0"+this.mes+"/"+this.ano;
        }else if(dia<10){
            data = "0"+this.dia+"/"+this.mes+"/"+this.ano;
        }else if(mes<10){
            data = ""+this.dia+"/0"+this.mes+"/"+this.ano;
        }else{
            data = ""+this.dia+"/"+this.mes+"/"+this.ano;
        }

        return data;
    }


    public int getAno() {
        return ano;
    }
    public int getDia() {
        return dia;
    }
    public int getMes() {
        return mes;
    }

}