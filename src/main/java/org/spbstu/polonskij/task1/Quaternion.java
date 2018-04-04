package org.spbstu.polonskij.task1;

public class Quaternion {
    private final double a;
    private final double b;
    private final double c;
    private final double d;

    public Quaternion() {
        a = b = c = d = 0;
    }

    public Quaternion(double a) {
        this.a = a;
        b = c = d = 0;
    }

    public Quaternion( double b, double c, double d) {
        a = 0;
        this.b = b;
        this.c = c;
        this.d = d;
    }


    public Quaternion(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }


    public Quaternion conjugation() {
        return new Quaternion(a, -b, -c, -d);
    }

    /**
     * 
     * @return
     */
    public double abs() {
        return Math.sqrt(a * a + b * b + c * c + d * d);
    }

    public Quaternion norm() {
        return new Quaternion(a * a, b * b, c * c, d * d);
    }

    private boolean isZero() {
        return a == 0 && b == 0 && c == 0 && d == 0;
    }

    private Quaternion inverse() {
        return divideNum(conjugation(), Math.pow(abs(), 2d));
    }

    public Quaternion sum(Quaternion other) {
        return new Quaternion(a + other.a, b + other.b, c + other.c, d + other.d);
    }

    public Quaternion sub(Quaternion other) {
        return new Quaternion(a - other.a, b - other.b, c - other.c, d - other.d);
    }

    public Quaternion multNum(double num) {
        return new Quaternion(a * num, b * num, c * num, d * num);
    }

    public Quaternion mult(Quaternion other) {
        return new Quaternion(a * other.a - b * other.b - c * other.c - d * other.d,
                a * other.b + b * other.a + c * other.d - d * other.c,
                a * other.c - b * other.d + c * other.a + d * other.b,
                a * other.d + b * other.c - c * other.b + d * other.a);
    }

    public static Quaternion divideNum(Quaternion first, double num) {
        if (num == 0) throw new ArithmeticException("Division by zero");
        return new Quaternion(first.a / num, first.b / num, first.c / num, first.d / num);
    }

    public static Quaternion divide(Quaternion first, Quaternion second) {
        if (second.isZero()) throw new ArithmeticException("Division by zero");
        return first.mult(second.inverse());
    }

    public Quaternion scalarPart() {
        return new Quaternion(a);
    }

    public Quaternion vectorPart() {
        return new Quaternion(b, c, d);
    }

    public static Quaternion getQuaternion(Vector vector, double angle) {
        Vector unitVector = vector.normalize();
        double a = Math.cos(angle / 2);
        double b = unitVector.getX() * Math.sin(angle / 2);
        double c = unitVector.getY() * Math.sin(angle / 2);
        double d = unitVector.getZ() * Math.sin(angle / 2);
        return new Quaternion(a, b, c, d);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quaternion that = (Quaternion) o;

        if (Double.compare(that.a, a) != 0) return false;
        if (Double.compare(that.b, b) != 0) return false;
        if (Double.compare(that.c, c) != 0) return false;
        return Double.compare(that.d, d) == 0;
    }

    @Override
    public int hashCode() {
        int result;

        long temp;
        temp = Double.doubleToLongBits(a);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(c);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(d);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return (a +
                ((b < 0) ? " - " : " + ") + Math.abs(b) + " * i" +
                ((c < 0) ? " - " : " + ") + Math.abs(c) + " * j" +
                ((d < 0) ? " - " : " + ") + Math.abs(d) + " * k");
    }
}

