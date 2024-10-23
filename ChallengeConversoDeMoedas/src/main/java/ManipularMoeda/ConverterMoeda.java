package ManipularMoeda;

import Conexao.APIConexao;
import Modelos.Moeda;
import Modelos.MoedaRecord;

import java.text.NumberFormat;
import java.util.Locale;

public class ConverterMoeda {
    public void convert(Moeda moedaBase,
                        Moeda converterPara,
                        float valorParaSerConvertido,
                        String apiKey
    ){
        APIConexao apiConexao = new APIConexao(apiKey);
        MoedaRecord moedaRecord = apiConexao.buscarMoeda(moedaBase, converterPara);

        System.out.printf("\tConverter de:%s\t\nPara %s",
                moedaBase,
                converterPara
        );
        System.out.printf("\n\t%s %s valem %s %s.",
                formatarNumero(valorParaSerConvertido),
                moedaBase.getCodigoMoeda(),
                formatarNumero(valorParaSerConvertido * moedaRecord.conversion_rates()),
                converterPara.getCodigoMoeda()
        );
    }

    private String formatarNumero(double numero){
        NumberFormat formatar = NumberFormat.getInstance(Locale.of("pt", "BR"));
        return String.format("%s", formatar.format(numero));
    }
}
