import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Set;

// Rafael Eduardo Ronchi Filho
public class Main {
    public static void main(String[] args) {
        String in= "obj/atletas.csv";
        String to = "obj/atletas.bin";

        try {
            ProvaSuficiencia.serializar(in, to);

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(to))) {
                Set<Atleta> atletasLidos = (Set<Atleta>) ois.readObject();
                System.out.println("obj:");
                for (Atleta a : atletasLidos) {
                    System.out.println("cpf" +a.getCpf());
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (EArquivoOrigemIncorreto e) {
        }
    }
}