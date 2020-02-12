package expression;

import java.util.Objects;

public class Variable implements CommonExpression {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    /*
        @Override
        public String toMiniString() {
            return name;
        }
    */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Variable) {
            return this.name.equals(((Variable) other).name);
        } else {
            return false;
        }
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (this.name.equals("x")) {
            return x;
        }
        if (this.name.equals("y")) {
            return y;
        }
        return z;
    }

    @Override
    public int evaluate(int value) {
        return value;
    }

    @Override
    public double evaluate(double value) {
        return value;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}