public class Worker {

    public static void work(TransportTask task) {
        OpornPlanFinder.find(task);
        PotentialMethodFinder potentialMethodFinder = new PotentialMethodFinder(task);
        potentialMethodFinder.find();
    }

    public static void printMatr(TransportTask task) {
        for (Cell[] cellArr : task.matr) {
            for (Cell cell : cellArr) {
                System.out.print((cell.basis ? cell.value : 0) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
