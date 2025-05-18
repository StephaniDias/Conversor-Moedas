import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConversorMoedas {

    private static final String API_KEY = "CHAVEAPI";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String opcao;

        do {
            exibirMenu();
            opcao = scanner.nextLine();

            if (!opcao.equals("7")) {
                processarOpcao(opcao, scanner);
            } else {
                System.out.println("Encerrando o programa. Até logo!");
            }

        } while (!opcao.equals("7"));

        scanner.close();
    }

    public static void exibirMenu() {
        System.out.println("\n--- CONVERSOR DE MOEDAS ---");
        System.out.println("1. Dólar (USD) → Real (BRL)");
        System.out.println("2. Euro (EUR) → Real (BRL)");
        System.out.println("3. Libra (GBP) → Real (BRL)");
        System.out.println("4. Real (BRL) → Dólar (USD)");
        System.out.println("5. Real (BRL) → Euro (EUR)");
        System.out.println("6. Real (BRL) → Libra (GBP)");
        System.out.println("7. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void processarOpcao(String opcao, Scanner scanner) {
        String base = "", destino = "";

        switch (opcao) {
            case "1": base = "USD"; destino = "BRL"; break;
            case "2": base = "EUR"; destino = "BRL"; break;
            case "3": base = "GBP"; destino = "BRL"; break;
            case "4": base = "BRL"; destino = "USD"; break;
            case "5": base = "BRL"; destino = "EUR"; break;
            case "6": base = "BRL"; destino = "GBP"; break;
            default:
                System.out.println("Opção inválida.");
                return;
        }

        System.out.print("Digite o valor em " + base + ": ");
        double valor;
        try {
            valor = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido.");
            return;
        }

        double taxa = obterTaxaComHttpResponse(base, destino);
        if (taxa > 0) {
            double convertido = valor * taxa;
            System.out.printf(">> %.2f %s = %.2f %s (Taxa: %.4f)\n",
                    valor, base, convertido, destino, taxa);
        } else {
            System.out.println("Erro ao obter a taxa de conversão.");
        }
    }

    public static double obterTaxaComHttpResponse(String base, String destino) {
        try {
            String url = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + base;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Gson gson = new Gson();
                JsonObject json = gson.fromJson(response.body(), JsonObject.class);
                JsonObject rates = json.getAsJsonObject("conversion_rates");

                if (rates.has(destino)) {
                    return rates.get(destino).getAsDouble();
                } else {
                    System.out.println("Moeda destino não encontrada.");
                    return -1;
                }
            } else {
                System.out.println("Erro na requisição: HTTP " + response.statusCode());
                System.out.println("Mensagem da API: " + response.body());
                return -1;
            }

        } catch (Exception e) {
            System.out.println("Erro ao acessar a API: " + e.getMessage());
            return -1;
        }
    }
}
