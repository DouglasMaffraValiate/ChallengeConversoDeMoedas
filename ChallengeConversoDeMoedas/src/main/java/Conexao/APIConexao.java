package Conexao;

import Modelos.Moeda;
import Modelos.MoedaRecord;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIConexao {
    private String apiKey;

    public APIConexao(String apiKey) {
        this.apiKey = apiKey;
    }

    private String criarURL (Moeda moeda){
        String apiURL = "https://v6.exchangerate-api.com/v6/";

        return String.format("%s%s/latest/%s",
                apiURL,
                apiKey,
                moeda.getCodigoMoeda()
        );
    }

    public MoedaRecord buscarMoeda(Moeda converterDe, Moeda converterPara){

        URI uri = URI.create(criarURL(converterDe));

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonObjectResponse = new Gson().fromJson(response.body(), JsonObject.class);
            JsonObject conversionRates = jsonObjectResponse.getAsJsonObject("conversion_rates");

            double taxaConvercao = conversionRates.get(converterPara.getCodigoMoeda()).getAsDouble();
            return new MoedaRecord(converterPara.getCodigoMoeda(), taxaConvercao);

        } catch (IOException | InterruptedException e) {
            System.out.printf("\nERRO: %s", e.getMessage());
        }
        return null;
    }
}
