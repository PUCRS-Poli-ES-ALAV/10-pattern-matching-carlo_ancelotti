import java.util.Random;

public class problema2 {

    private int iteracoes = 0;  // contador de iterações do loop externo
    private int instrucoes = 0; // contador estimado de instruções dentro do loop

    private int search(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        long patHash = hash(pat, M);
        instrucoes++; // cálculo hash do padrão

        for (int i = 0; i <= N - M; i++) {
            iteracoes++;
            long txtHash = hash(txt.substring(i, i + M), M);
            instrucoes += M + 2; // estimando M operações na hash + 2 para if e atribuição
            
            if (patHash == txtHash)
                return i; // ocorrência? colisão?
        }
        return N; // nenhuma ocorrência
    }

    private long hash(String s, int M) {
        long h = 0;
        for (int j = 0; j < M; j++) {
            h = (h * 24 + s.charAt(j)) % 100000049;
        }
        return h;
    }

    public static void main(String[] args) {
        problema2 p = new problema2();

        // Gerar texto e padrão grandes
        int tamanhoTexto = 600_000;
        int tamanhoPadrao = 10;

        Random rnd = new Random();

        StringBuilder textoBuilder = new StringBuilder(tamanhoTexto);
        for (int i = 0; i < tamanhoTexto; i++) {
            textoBuilder.append((char) ('a' + rnd.nextInt(26))); // caracteres a-z
        }
        String texto = textoBuilder.toString();

        // Gerar padrão que existe no texto (pegando uma substring aleatória)
        int pos = rnd.nextInt(tamanhoTexto - tamanhoPadrao);
        String padrao = "adadasda00er0ia-reakdapkdpakpawkea[pkdakdwpkdpakwdpakwdpka[pdaks]]";

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
