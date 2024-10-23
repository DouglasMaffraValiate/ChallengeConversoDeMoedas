package Modelos;

import java.util.Scanner;

import ManipularMoeda.ConverterMoeda;
import ManipularMoeda.PesquisarMoeda;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);

    private float obterQuantidadeASerConvertida() {
        System.out.println("\n\tDigite a quantidade a ser convertida: ");
        return scanner.nextFloat();
    }

    public void rodarAplicacao(String apikey) {
        boolean continuar = true;
        while (continuar){
            escolher(apikey);
            System.out.println("\n\tDigite S para sair: ");
            continuar = obterString().toLowerCase().equals("s") ? false : true;
        }
        escolher(apikey);
    }

    private String menu() {
        return ConsoleColors.BLACK_BACKGROUND + """
                \n\tDIGITE 1 PARA CONVERTER DE REAL PARA DOLAR.
                \tDIGITE 2 PARA CONVERTER DE REAL PARA PESOS ARGENTINOS.
                \tDIGITE 3 PARA CONVERTER QUALQUER MOEDA.
                """ + ConsoleColors.RESET;
    }

    private String menuTipoPesquisa() {
        return ConsoleColors.BLACK_BACKGROUND + """
                \n\tDIGITE 1 PARA PESQUISAR PELO CÓDIGO DA MOEDA.
                \tDIGITE 2 PARA PESQUISAR PELO NOME DA MOEDA.
                \tDIGITE 3 PARA PESQUISAR PELO PAIS DA MOEDA.
                """ + ConsoleColors.RESET;
    }

    private byte obterOpcao() {
        return scanner.nextByte();
    }

    private String obterString() {
        return scanner.nextLine();
    }

    private void escolher(String apiKey) {
        ConverterMoeda conversor = new ConverterMoeda();
        byte opcao;
        System.out.println(menu());
        opcao = obterOpcao();
        scanner.nextLine();
        switch (opcao) {
            case 1:
                conversor.convert(Moeda.BRL, Moeda.USD, obterQuantidadeASerConvertida(), apiKey);
                break;
            case 2:
                conversor.convert(Moeda.BRL, Moeda.ARS, obterQuantidadeASerConvertida(), apiKey);
                break;
            case 3:
                escolherMetodoDePesquisa(apiKey);
                break;
            default:
                System.out.println("\n\tOPÇÃO INVALIDA SAINDO!");
                break;
        }
    }

    private void escolherMetodoDePesquisa(String apiKey) {
        PesquisarMoeda pesquisarMoeda = new PesquisarMoeda();
        ConverterMoeda conversor = new ConverterMoeda();
        byte opcao;
        System.out.println(menuTipoPesquisa());
        opcao = obterOpcao();
        scanner.nextLine();
        Moeda moedaPadrao, moedaParaConverter;
        switch (opcao) {
            case 1:
                System.out.println("\nDigite o código da moeda padrão: ");
                moedaPadrao = pesquisarMoeda.peloCodigo(obterString());

                System.out.println("\nDigite o código para qual moeda quer converter: ");
                moedaParaConverter = pesquisarMoeda.peloCodigo(obterString());

                if (moedaPadrao != null && moedaParaConverter != null) {
                    conversor.convert(moedaPadrao, moedaParaConverter, obterQuantidadeASerConvertida(), apiKey);
                } else {
                    System.out.println("Uma ou ambas as moedas não foram encontradas.");
                }
                break;
            case 2:
                System.out.println("\nDigite o nome da moeda padrão: ");
                moedaPadrao = pesquisarMoeda.peloNomeMoeda(obterString());

                System.out.println("\nDigite o nome para qual moeda quer converter: ");
                moedaParaConverter = pesquisarMoeda.peloNomeMoeda(obterString());

                if (moedaPadrao != null && moedaParaConverter != null) {
                    conversor.convert(moedaPadrao, moedaParaConverter, obterQuantidadeASerConvertida(), apiKey);
                } else {
                    System.out.println("Uma ou ambas as moedas não foram encontradas.");
                }
                break;
            case 3:
                System.out.println("\nDigite o país da moeda padrão: ");
                moedaPadrao = pesquisarMoeda.peloPaisMoeda(obterString());

                System.out.println("\nDigite o país para qual moeda quer converter: ");
                moedaParaConverter = pesquisarMoeda.peloPaisMoeda(obterString());

                if (moedaPadrao != null && moedaParaConverter != null) {
                    conversor.convert(moedaPadrao, moedaParaConverter, obterQuantidadeASerConvertida(), apiKey);
                } else {
                    System.out.println("Uma ou ambas as moedas não foram encontradas.");
                }
                break;
            default:
                System.out.println("\n\tOPÇÃO INVALIDA SAINDO!");
                break;
        }
    }
}
