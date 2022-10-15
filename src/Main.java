import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Сколько поставщиков? ");
        int nPost = sc.nextInt();
        System.out.println("Сколько потребителей? ");
        int nPotr = sc.nextInt();

        System.out.println("Введите запасы поставщиков: ");
        int[] arrPost = new int[nPost];
        for (int i = 0; i < nPost; i++) {
            arrPost[i] = sc.nextInt();
        }
        System.out.println("Введите потребности потребителей: ");
        int[] arrPotr = new int[nPotr];
        for (int i = 0; i < nPotr; i++) {
            arrPotr[i] = sc.nextInt();
        }

        System.out.println("Введите матрицу " + nPost + "*" + nPotr + " с ценами перевозки");
        int[][] prices = new int[nPotr][nPost];
        for (int i = 0; i < nPotr; i++) {
            for (int j = 0; j < nPost; j++) {
                prices[i][j] = sc.nextInt();
            }
        }

        TransportTask task = new TransportTask(nPost, nPotr, arrPost, arrPotr, prices);
        Worker.work(task);
    }
}
