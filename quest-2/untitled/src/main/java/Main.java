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

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(destino))) {
                Set<Atleta> atletasLidos = (Set<Atleta>) ois.readObject();
                System.out.println("obj:");
                for (Atleta a : atletasLidos) {
                    System.out.println("cpf" + a.getCpf());
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (EArquivoOrigemIncorreto e) {
        }
    }
}