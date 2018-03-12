package task;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QuaternionTest {

    Quaternion quaternion;

    @Test
    public void createEmpty() {
        quaternion = new Quaternion();
        assertEquals("0.0 + 0.0 * i + 0.0 * j + 0.0 * k", quaternion.toString());
    }

    @Test
    public void getSopr() throws Exception {
        quaternion = new Quaternion(0.4, 0.1, 0.7, 1.5);
        assertEquals(new Quaternion(0.4, -0.1, -0.7, -1.5), quaternion.sopr());
        quaternion = new Quaternion(1, 2, 3, 4);
        assertEquals(new Quaternion(1, -2, -3, -4), quaternion.sopr());
        quaternion = new Quaternion(32.3, -56.789, 147.23, 37.5673);
        assertEquals(new Quaternion(32.3, 56.789, -147.23, -37.5673), quaternion.sopr());
    }

    @Test
    public void getAbs() throws Exception {
        quaternion = new Quaternion(3, 1, -2, -4);
        assertEquals(5.477225575051661, quaternion.abs(), 1e-10);
        quaternion = new Quaternion(2.3, 4.1, 5.7, -1);
        assertEquals(7.455870170543476, quaternion.abs(), 1e-10);
        quaternion = new Quaternion(1.456, 0, 0, -4.2341);
        assertEquals(4.477447800924093, quaternion.abs(), 1e-10);
    }

    @Test
    public void getNorm() throws Exception {
        quaternion = new Quaternion(1, 2, 3, 4);
        assertEquals(new Quaternion(1, 4, 9, 16), quaternion.norm());
        quaternion = new Quaternion(2.4, -6.7, 4, 9.2);
        assertEquals(new Quaternion(5.76, 44.89, 16, 84.63999999999999), quaternion.norm());
        quaternion = new Quaternion(26.45, -63.196, -19.3, -56.56);
        assertEquals(new Quaternion(699.6025, 3993.734416, 372.49, 3199.0336), quaternion.norm());
    }

    @Test
    public void getSum() throws Exception {
        assertEquals(new Quaternion(5, 5, 5, 5),
                Quaternion.sum(new Quaternion(3, -1, 4, 2), new Quaternion(2, 6, 1, 3)));
        assertEquals(new Quaternion(5.4, 5, 6.7, 5.6),
                Quaternion.sum(new Quaternion(3.1, -1.2, 4.9, 2.5), new Quaternion(2.3, 6.2, 1.8, 3.1)));
        assertEquals(new Quaternion(8.7731, 856.2336, 84.745805, 143.31),
                Quaternion.sum(new Quaternion(45.3413, 189.567, 57.346805, 200),
                        new Quaternion(-36.5682, 666.6666, 27.399, -56.69)));
    }

    @Test
    public void getAntiSum() throws Exception {
        assertEquals(new Quaternion(1, -11, 2, 7),
                Quaternion.antiSum(new Quaternion(2, -5, 6, 4), new Quaternion(1, 6, 4, -3)));
        assertEquals(new Quaternion(35.96, -120.19999999999999, 6.000000000000001, -1.3000000000000043),
                Quaternion.antiSum(new Quaternion(23.56, -56.8, 10.3, 33.3),
                        new Quaternion(-12.4, 63.4, 4.3, 34.6)));
        assertEquals(new Quaternion(181.8475, 124.9618, 42.3334, -45.96),
                Quaternion.antiSum(new Quaternion(25.4675, 189.34, 65.7894, -49.52),
                        new Quaternion(-156.38, 64.3782, 23.456, -3.56)));
    }

    @Test
    public void getMultNum() throws Exception {
        assertEquals(new Quaternion(4, -10, 12, 8),
                Quaternion.multNum(new Quaternion(2, -5, 6, 4), 2));
        assertEquals(new Quaternion(9, -20.52, 21.96, 12.96),
                Quaternion.multNum(new Quaternion(2.5, -5.7, 6.1, 3.6), 3.6));
        assertEquals(new Quaternion(698.4631499999999, 523.947347, 1985.4065, -930.14152),
                Quaternion.multNum(new Quaternion(24.45, 18.341, 69.5, -32.56), 28.567));
    }

    @Test
    public void getMult() throws Exception {
        assertEquals(new Quaternion(-28, 4, 6, 8),
                Quaternion.mult(new Quaternion(1, 2, 3, 4), new Quaternion(1, 2, 3, 4)));
        assertEquals(new Quaternion(-539.21, -364.78000000000003, 132.76, -834.27),
                Quaternion.mult(new Quaternion(14.8, -44.6, 22.6, 5.3),
                        new Quaternion(2.5, -3.6, 19.4, -4.3)));
        assertEquals(new Quaternion(-5487.588562, 623.7615599999999, 198.04689199999996, 375.5940453999995),
                Quaternion.mult(new Quaternion(13.564, 78.2918, 22.4, -5.64),
                        new Quaternion(-3.4, 62.59, 23.153, -4)));
    }

    @Test
    public void getDivideNum() throws Exception {
        assertEquals(new Quaternion(1, 1, 1, 1),
                Quaternion.divideNum(new Quaternion(7, 7, 7, 7), 7));
        assertEquals(new Quaternion(50, -37, 98, 49),
                Quaternion.divideNum(new Quaternion(200, -148, 392, 196), 4));
        try {
            Quaternion.divideNum(new Quaternion(30, 19, 2, 7), 0);
            assertTrue(false);
        } catch (ArithmeticException e) {
            assertTrue(true);
        }
    }

    @Test
    public void getParts() throws Exception {
        quaternion = new Quaternion(1, 2, 3, 4);
        assertEquals("u = 1.0, v = 2.0 * i + 3.0 * j + 4.0 * k", quaternion.getParts());
        quaternion = new Quaternion(1.5, -2.378, 3.56, 4.894);
        assertEquals("u = 1.5, v = -2.378 * i + 3.56 * j + 4.894 * k", quaternion.getParts());
        quaternion = new Quaternion(23.563, -286.3571, 356.0586, -4.58493);
        assertEquals("u = 23.563, v = -286.3571 * i + 356.0586 * j - 4.58493 * k", quaternion.getParts());
    }

    Vector vector;
    Double angle;

    @Test
    public void getQuaternion() throws Exception {
        vector = new Vector(1, 0, 0);
        angle = 0.0;
        assertEquals(new Quaternion(1, 0, 0, 0),
                Quaternion.getQuaternion(vector, angle));
        vector = new Vector(2, -3, 7);
        angle = 0.5;
        assertEquals(new Quaternion(0.9689124217106447, 0.06284066849134874,
                        -0.0942610027370231, 0.21994233971972058),
                Quaternion.getQuaternion(vector, angle));
    }

}