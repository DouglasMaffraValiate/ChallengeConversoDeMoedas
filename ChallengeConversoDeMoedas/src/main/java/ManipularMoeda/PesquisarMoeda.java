package ManipularMoeda;

import Modelos.Moeda;

public class PesquisarMoeda {
    public Moeda peloCodigo(String codigo){
        for(Moeda moeda : Moeda.values()){
            if(moeda.getCodigoMoeda().equalsIgnoreCase(codigo.toUpperCase())){
                System.out.println(moeda);
                return moeda;
            }
        }
        System.out.printf("\n Não foi posivel encontra a moeda do codigo: %s\n", codigo.toUpperCase());
        return null;
    }

    public Moeda peloNomeMoeda(String nomeMoeda){
        for(Moeda moeda : Moeda.values()){
            if(moeda.getNomeMoeda().equalsIgnoreCase(nomeMoeda)){
                System.out.println(moeda);
                return moeda;
            }
        }
        System.out.printf("\n Não foi posivel encontra a moeda com o nome: %s\n", nomeMoeda.toUpperCase());
        return null;
    }

    public Moeda peloPaisMoeda(String paisMoeda){
        for(Moeda moeda : Moeda.values()){
            if(moeda.getPaisMoeda().equalsIgnoreCase(paisMoeda)){
                System.out.println(moeda);
                return moeda;
            }
        }
        System.out.printf("\n Não foi posivel encontra a moeda pelo pais: %s\n", paisMoeda.toUpperCase());
        return null;
    }
}
