import java.util.Scanner;
import java.util.ArrayList;

public class Jogo {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int opcao;

        do {
            System.out.println("==============================");
            System.out.println(" SUPER TRUNFO");
            System.out.println("==============================\n");
            System.out.println("OPÇOES:");
            System.out.println(" 1 - Jogar");
            System.out.println(" 2 - Ranking");
            System.out.println(" 3 - Sair");

            System.out.print("\nDigite uma opção:");
            opcao = in.nextInt();
            switch (opcao) {
                case 1:
                    Jogar();
                    break;
                case 2:
                    exibirRanking();
                    break;
            }
        } while (opcao != 3);

        System.out.println("\n[ PROGRAMA ENCERRADO ]");
        System.exit(0);
    }

    private static void Jogar() {
        Scanner in = new Scanner(System.in);
        int opcao, rodada;
        Carta carta;
        Jogador jogador;

        System.out.println("\n------------------------------");
        System.out.println(" JOGAR ");

        // Cria o Baralho
        Baralho mesa = new Baralho();
        mesa.GeraBaralho();
        mesa.Embaralhar();

        // Cria os Jogadores
        Jogadores jogadores = new Jogadores();

        // Distribui as Cartas para os Jogadores();
        jogadores.darCartas(mesa);

        System.out.println("\n##############################");
        System.out.println(" PARTIDA INICIADA");
        System.out.println("##############################");

        rodada = 1;
        jogador = jogadores.getJogadorByIndex(0);

        // Loop principal
        while (!jogadores.vencedorPartida()) {
            System.out.println("##############################");
            System.out.println("RODADA: " + rodada);
            System.out.println("------------------------------");

            // Printa o Jogador da vez
            System.out.println(jogador.toString());

            // Pega primeira carta do jogador da vez
            carta = jogador.getCartaByIndex(0);
            System.out.println(carta.toString());

            // Jogador define com qual atributo disputará
            do {
                System.out.println("\nSelecione o atributo para a disputa:");
                System.out.println(" 1 - Peso");
                System.out.println(" 2 - Cilindrada");
                System.out.println(" 3 - Velocidade");
                System.out.println(" 4 - Aceleração");
                System.out.println(" 5 - Potência");
                System.out.print("Opção: ");
                opcao = in.nextInt();
                if (opcao < 1 || opcao > 5) System.out.println("OPÇÃO INVÁLIDA!\n");
            } while (opcao < 1 || opcao > 5);

            // Executa a rodada e atualiza o jogador da vez
            jogador = jogadores.vencedorRodada(jogador, opcao);

            // Printa o nome do vencedor
            System.out.println("\n##############################");
            System.out.println(" VENCEDOR DA RODADA");
            System.out.println("------------------------------");
            System.out.println(jogador.toString());
            System.out.println("##############################");

            // Passa as cartas dos perdedores para o ganhador
            jogadores.recolheCartas(jogador);

            rodada++;
        }

        // FIM DA PARTIDA
        System.out.println("\n##############################");
        System.out.println(" VENCEDOR DA PARTIDA");
        System.out.println("------------------------------");
        System.out.println(jogador.getNome() + " | " + rodada + " Rodadas");
        System.out.println("##############################");
        
        // salva no RANKING
        Jogador vencedor = jogadores.jogadorVencedor();
        Ranking ranking = new Ranking();
        ranking.salvarVencedor(vencedor.getNome(), rodada);
    }

    private static void exibirRanking() {
        Ranking ranking = new Ranking();
        ranking.exibir();
    }
}
