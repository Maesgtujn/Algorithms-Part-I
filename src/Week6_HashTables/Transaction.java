package Week6_HashTables;

import edu.princeton.cs.algs4.Date;

public final class Transaction implements Comparable<Transaction>{
    private final String who;
    private final Date when;
    private final double amount;


    public Transaction(String who, Date when, double amount) {
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    public boolean equals(Object y){
        return true;
    }

    public int hashCode(){
        int hash = 17;
        hash = 31*hash + who.hashCode();
        hash = 31*hash + when.hashCode();
        hash = 31*hash + ((Double) amount).hashCode();
        return hash;
    }

    @Override
    public int compareTo(Transaction o) {
        return 0;
    }
}
