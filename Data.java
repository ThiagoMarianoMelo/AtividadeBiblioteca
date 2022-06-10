import java.io.Serializable;

public class Data implements Serializable{

    private int dia;
    private int mes;
    private int ano;
    

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
    public String comparaData(int dia1,int mes1,int ano1,int dia2,int mes2,int ano2){

        String data1 = dia1+"/"+mes1+"/"+ano1;
        String data2 = dia2+"/"+mes2+"/"+ano2;

        if(ano1 == ano2){
            if(mes1 == mes2){
                if(dia1 == dia2){
                    return null;
                }else{
                    if(dia1 > dia2){
                        return data1;
                    }else if( dia2 > dia1){
                        return data2;}
                    }
            }else{
                if(mes1 > mes2){
                    return data1;
                }else if(mes2 > mes1){
                    return data2;}
                }
        }else{
            if(ano1 > ano2){
                return data1;
            }else if( ano2 > ano1){
                return data2;}
            }
        return null;
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
}