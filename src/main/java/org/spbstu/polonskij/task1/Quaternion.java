package org.spbstu.polonskij.task1;

public class Quaternion {
    private final double a;
    private final double b;
    private final double c;
    private final double d;

    public Quaternion() {
        a = b = c = d = 0;
    }

    public Quaternion(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * @return conjugate quaternion
     */
    public Quaternion conjugation() {
        return new Quaternion(a, -b, -c, -d);
    }

    /**
     * @return quaternion module
     */
    public double abs() {
        return Math.sqrt(a * a + b * b + c * c + d * d);
    }

    /**
     * @return normalized quaternion
     */
    public double norm() {
        return a * a + b * b + c * c + d * d;
    }

    /**
     * @return true or false, if the quaternion 0
     */
    private boolean isZero() {
        return a == 0 && b == 0 && c == 0 && d == 0;
    }

    /**
     * @return
     */
    private Quaternion inverse() {
        return conjugation().divideNum(Math.pow(abs(), 2d));
    }

    /**
     * @param other quaternion to addition
     * @return returns quaternion from two combined quaternion
     */
    public Quaternion sum(Quaternion other) {
        return new Quaternion(a + other.a, b + other.b, c + other.c, d + other.d);
    }

    /**
     * @param other quaternion to subtraction
     * @return a quaternion of two subtracted quaternions
     */
    public Quaternion sub(Quaternion other) {
        return new Quaternion(a - other.a, b - other.b, c - other.c, d - other.d);
    }

    /**
     * @param num double to multiplication
     * @return quaternion multiplied by a number
     */
    public Quaternion multNum(double num) {
        return new Quaternion(a * num, b * num, c * num, d * num);
    }

    /**
     * @param other quaternion to multiplication
     * @return quaternion of two multiplied quaternions
     */
    public Quaternion mult(Quaternion other) {
        return new Quaternion(a * other.a - b * other.b - c * other.c - d * other.d,
                a * other.b + b * other.a + c * other.d - d * other.c,
                a * other.c - b * other.d + c * other.a + d * other.b,
                a * other.d + b * other.c - c * other.b + d * other.a);
    }

    /**
     * @param num double
     * @return quaternion divided by a number
     */
    public Quaternion divideNum(double num) {
        if (num == 0) throw new ArithmeticException("Division by zero");
        return new Quaternion(a / num, b / num, c / num, d / num);
    }

    /**
     * @param other quaternion to divide
     * @return the quaternion obtained by dividing two quaternions
     */
    public Quaternion divide(Quaternion other) {
        if (other.isZero()) throw new ArithmeticException("Division by zero");
        return mult(other.inverse());
    }

    /**
     * @return scalar part of quaternion
     */
    public double scalarPart() {
        return a;
    }

    /**
     * @return vector part of quaternion
     */
    public Vector vectorPart() {
        return new Vector(b, c, d);
    }

    /**
     * @param vector vector
     * @param angle  double
     * @return the quaternion obtained from the given axes and the rotation angle
     */
    public static Quaternion getQuaternion(Vector vector, double angle) {
        Vector unitVector = vector.normalize();
        double a = Math.cos(angle / 2);
        double b = unitVector.getX() * Math.sin(angle / 2);
        double c = unitVector.getY() * Math.sin(angle / 2);
        double d = unitVector.getZ() * Math.sin(angle / 2);
        return new Quaternion(a, b, c, d);
    }

    /**
     * @return angle of quaternion
     */
    public static double angle(Quaternion a){
        return a.scalarPart();
    }

    /**
     * @return axis of quaternion
     */
    public static Vector axis(Quaternion a){
        return a.vectorPart();
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

