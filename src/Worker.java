public class Worker {

    public static void work(TransportTask task) {
        OpornPlanFinder.find(task);
        boolean result = PotentialMethodFinder.find(task);
        if (!result) {
            System.out.println("Задача не может быть решена (не составляется цикл при пересчете базиса)");
            System.exit(0);
        }
    }

    public static void printMatr(TransportTask task) {
        for (Cell[] cellArr : task.matr) {
            for (Cell cell : cellArr) {
                System.out.print(cell.value + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
