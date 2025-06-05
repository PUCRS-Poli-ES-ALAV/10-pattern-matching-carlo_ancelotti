import java.util.Random;

public class problema2Rolling {

    private int iteracoes = 0;
    private int instrucoes = 0;

    private static final int R = 24; // base (can be 256 for full ASCII)
    private static final int Q = 100000049; // large prime number

    private long RM; // R^(M-1) % Q for use in removing leading digit

    private int search(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        // Precompute R^(M-1) % Q
        RM = 1;
        for (int i = 1; i <= M - 1; i++) {
            RM = (RM * R) % Q;
        }

        long patHash = hash(pat, M);
        instrucoes++; // hash of pattern

        long txtHash = hash(txt, M);

        for (int i = 0; i <= N - M; i++) {
            iteracoes++;

            // Check hash match
            if (patHash == txtHash) {
                // Optionally check actual strings to avoid false positives
                if (txt.substring(i, i + M).equals(pat)) {
                    return i;
                }
            }

            // Update rolling hash
            if (i < N - M) {
                txtHash = (txtHash + Q - RM * txt.charAt(i) % Q) % Q;
                txtHash = (txtHash * R + txt.charAt(i + M)) % Q;
                instrucoes += 5; // estimate
            }
        }

        return N;
    }

    private long hash(String key, int M) {
        long h = 0;
        for (int j = 0; j < M; j++) {
            h = (h * R + key.charAt(j)) % Q;
        }
        return h;
    }

    public static void main(String[] args) {
        problema2Rolling p = new problema2Rolling();

        int tamanhoTexto = 600_000;
        int tamanhoPadrao = 10;

        Random rnd = new Random();

        StringBuilder textoBuilder = new StringBuilder(tamanhoTexto);
        for (int i = 0; i < tamanhoTexto; i++) {
            textoBuilder.append((char) ('a' + rnd.nextInt(26)));
        }
        String texto = textoBuilder.toString();

        int pos = rnd.nextInt(tamanhoTexto - tamanhoPadrao);
        String padrao = "2542154521"; //texto.substring(pos, pos + tamanhoPadrao); // real pattern from text

        System.out.println("Buscando padrão no texto...");
        long inicio = System.currentTimeMillis();
        int resultado = p.search(padrao, texto);
        long fim = System.currentTimeMillis();

        if (resultado < texto.length()) {
            System.out.println("Padrão encontrado na posição: " + resultado);
        } else {
            System.out.println("Padrão não encontrado.");
        }

        System.out.println("Iterações do loop: " + p.iteracoes);
        System.out.println("Instruções estimadas: " + p.instrucoes);
        System.out.println("Tempo gasto (ms): " + (fim - inicio));
    }
}
