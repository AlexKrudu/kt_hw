package expression;

import java.util.Objects;

public class Const implements CommonExpression {
    public Const(int value) {
        this.value = value;
    }
    public Const(double value){this.value = value;}

    @Override
    public String toString() {
        return String.valueOf(value);
    }
/*
    @Override
    public String toMiniString() {
        return Integer.toString(value);
}*/
    @Override
    public boolean equals(Object other) {
        if (other instanceof Const) {
            Const co = (Const) other;
            return this.value.doubleValue() == co.value.doubleValue();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return value.hashCode();
    }

    @Override
    public int evaluate(int x) {
        return value.intValue();
    }
    @Override
    public double evaluate(double x){
        return value.doubleValue();
    }
    @Override
    public int evaluate(int x, int y, int z){
        return value.intValue();
    }

    @Override
    public int getPriority() {
        return 0;
    }


    private final Number value;
}