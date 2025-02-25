import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class ProvaSuficiencia {

    public static void serializar(String arqOrigem, String arqDestino) throws EArquivoOrigemIncorreto {
        Set<Atleta> atletas = new TreeSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arqOrigem))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(",");

                String nome = campos[0].trim();
                String cpf = campos[1].trim();
                float altura = Float.parseFloat(campos[2].trim());
                float peso = Float.parseFloat(campos[3].trim());

                String rua = String.valueOf((campos.length > 4));
                String numero = String.valueOf((campos.length > 5));
                String complemento = String.valueOf((campos.length > 6));
                String cep = String.valueOf((campos.length > 7));
                String cidade = String.valueOf((campos.length > 8));
                String estado = String.valueOf((campos.length > 9));

                Endereco endereco = new Endereco(rua, numero, complemento, cep, cidade, estado);

                Atleta atleta = new Atleta(nome, cpf, altura, peso, endereco);
                atletas.add(atleta);
            }
        }  catch (IOException e) {
            throw new EArquivoOrigemIncorreto("Erro de leitura: " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new EArquivoOrigemIncorreto("Erro de formato numérico: " + e.getMessage());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arqDestino))) {
            oos.writeObject(atletas);
        } catch (IOException e) {
            System.err.println("Erro ao serializar atletas: " + e.getMessage());
        }
    }
}
