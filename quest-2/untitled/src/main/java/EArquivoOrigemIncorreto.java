import java.io.IOException;

class EArquivoOrigemIncorreto extends IOException {
    public EArquivoOrigemIncorreto(String mensagem) {
        super(mensagem);
    }
}