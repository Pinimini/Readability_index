class Problem {

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.print(args[i]);
            if (i % 2 == 0) {
                System.out.print("=");
            } else {
                System.out.println();
            }
        }
    }
}