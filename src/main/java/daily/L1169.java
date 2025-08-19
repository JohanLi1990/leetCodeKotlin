package daily;

import java.util.*;

public class L1169 {

    public List<String> invalidTransactions(String[] transactions) {
        List<String> ans = new ArrayList<>();
        var map = new HashMap<String, TreeSet<Transaction>>();

        for (String trans : transactions) {
            String[] details = trans.split(",");
            map.putIfAbsent(details[0], new TreeSet<>((a, b) -> b.time - a.time));
            var pq = map.get(details[0]);
            var curTrans = new Transaction(Integer.parseInt(details[2]), Integer.parseInt(details[1]), details[3], trans);
            boolean isCurLegal = true;
            var iterator = pq.iterator();
            while (iterator.hasNext()) {
                var preTrans = iterator.next();
                if (Math.abs(preTrans.time - curTrans.time) <= 60 && !preTrans.city.equals(curTrans.city)) {
                    if (!preTrans.isAdded) {
                        ans.add(preTrans.original);
                        preTrans.isAdded = true;
                    }
                    isCurLegal = false;
                } else {
                    break;
                }
            }
            if (curTrans.amount > 1000) {
                isCurLegal = false;
            }
            if (!isCurLegal) {
                ans.add(trans);
                curTrans.isAdded = true;
            }

            pq.add(curTrans);
        }
        return ans;
    }

    static class Transaction {
        int amount;
        int time;
        String city;
        String original;
        boolean isAdded = false;

        Transaction(int amt, int time, String city, String original) {
            this.amount = amt;
            this.time = time;
            this.city = city;
            this.original = original;
        }
    }

    public static void main(String[] args) {
        var ans = new L1169();
        System.out.println(ans.invalidTransactions(new String[]{
                "alice,20,800,mtv","bob,50,1200,mtv","alice,20,800,mtv","alice,50,1200,mtv","alice,20,800,mtv","alice,50,100,beijing"
        }));
    }

}
