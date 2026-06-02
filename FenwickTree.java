class FenwickTree {

    int BIT[];
    int n;

    FenwickTree(int size) {
        n = size;
        BIT = new int[n + 1];
    }

    void update(int index, int value) {
        while (index <= n) {
            BIT[index] += value;
            index += index & (-index);
        }
    }

    int query(int index) {
        int sum = 0;

        while (index > 0) {
            sum += BIT[index];
            index -= index & (-index);
        }

        return sum;
    }

    int rangeQuery(int left, int right) {
        return query(right) - query(left - 1);
    }

    public static void main(String[] args) {

        int scores[] = {50, 75, 60, 90, 80};

        FenwickTree ft = new FenwickTree(scores.length);

        for (int i = 0; i < scores.length; i++) {
            ft.update(i + 1, scores[i]);
        }

        System.out.println("Original Scores:");

        for (int i = 0; i < scores.length; i++) {
            System.out.println("Player " + (i + 1) +
                    " = " + scores[i]);
        }

        System.out.println("\nTotal Score from Player 2 to Player 4:");
        System.out.println(ft.rangeQuery(2, 4));

        System.out.println("\nUpdating Player 3 Score by +40");

        ft.update(3, 40);
        scores[3 - 1] += 40;

        System.out.println("\nUpdated Scores:");

        for (int i = 0; i < scores.length; i++) {
            System.out.println("Player " + (i + 1) +
                    " = " + scores[i]);
        }

        System.out.println("\nUpdated Range Sum:");
        System.out.println(ft.rangeQuery(2, 4));
    }
}