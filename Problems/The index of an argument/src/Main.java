class Problem {
    public static void main(String[] args) {
        boolean isExist = false;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("test")) {
                System.out.println(i);
                isExist = true;
            }
        }
        if (!isExist) {
            System.out.println(-1);
        }
    }
}