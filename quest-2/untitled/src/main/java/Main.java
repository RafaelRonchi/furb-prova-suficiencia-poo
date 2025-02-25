import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String origem = "app/atletas.csv";
        String destino = "app/atletas.bin";

        try {
            ProvaSuficiencia.serializar(origem, destino);
            System.out.println("Serialização concluída com sucesso em " + destino);

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(destino))) {
                @SuppressWarnings("unchecked")
                Set<Atleta> atletasLidos = (Set<Atleta>) ois.readObject();
                System.out.println("Objetos lidos do arquivo binário:");
                for (Atleta a : atletasLidos) {
                    System.out.println("CPF: " + a.getCpf());
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (EArquivoOrigemIncorreto e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}