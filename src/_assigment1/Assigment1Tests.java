package _assigment1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class Assigment1Tests {

    private static Assigment1 _assigment1;

    @BeforeAll
    static void setUp () { _assigment1 = new Assigment1();}

    @Test
    void revertNumber_ReturnsRevertedNumber() {

        assertEquals(123, _assigment1.revertNumber(321));
        assertEquals(0, _assigment1.revertNumber(0));
        assertEquals(3441, _assigment1.revertNumber(1443));
        assertEquals(-245, _assigment1.revertNumber(-542));

    }

    @Test
     void insertionSort_ReturnsSortedArray() {

        assertArrayEquals(new int[]{1, 2, 4}, _assigment1.insertionSort(new int[]{2, 4, 1}));
        assertArrayEquals(new int[]{1, 1, 1, 1, 1, 1}, _assigment1.insertionSort(new int[]{1, 1, 1, 1, 1, 1}));
        assertArrayEquals(new int[]{-2, -1, 4, 5, 6}, _assigment1.insertionSort(new int[]{4, 6, -2, 5, -1}));

    }

    @Test
    void insertionSort_ThrowsNullPointerException() {

        assertThrows(NullPointerException.class, () -> _assigment1.insertionSort(null));

    }

    @Test
    void positiveDecToHex_ReturnsNonNegativeResults() {

        assertEquals("=0xB=", _assigment1.decToHex(11));
        assertEquals("=0xF=", _assigment1.decToHex(15));
        assertEquals("=0x10=", _assigment1.decToHex(16));
        assertEquals("=0x100=", _assigment1.decToHex(256));
        assertEquals("=0x0=", _assigment1.decToHex(0));

    }

    @Test
    void negativeDecToHex_ReturnsNegativeResults() {

        assertEquals("=1x2A=", _assigment1.decToHex(-42));
        assertEquals("=1x6F=", _assigment1.decToHex(-111));

    }

    @Test
    void findPrimes_ReturnsNotEmptyArrays() {

        assertArrayEquals(new int[]{2, 3, 5, 17, 53, 67, 431}, _assigment1.findPrimes(new int[][]{new int[]{1, 2, 3, 5, -6, 5, 32, 6, 2}, new int[]{53, 5, 6, 75, 2, -26, 2, 67, 431, 17}}));
        assertArrayEquals(new int[]{2, 3}, _assigment1.findPrimes(new int[][]{new int[]{1}, new int[]{2, 3, 6, -1, 9, 2, 8}}));

    }

    @Test
    void findPrimes_ReturnsEmptyArrays() {

        assertArrayEquals(new int[]{}, _assigment1.findPrimes(new int[][]{new int[]{0}, new int[]{-1, -5, 4, 8}}));
        assertArrayEquals(new int[]{}, _assigment1.findPrimes(new int[][]{new int[]{0, 6, 8, 10, 12}, new int[]{-1, -5, 4, 8, -10405}}));
    }

    @Test
    void findPrimes_ThrowsException() {

        assertThrows(NullPointerException.class, () -> _assigment1.findPrimes(null));

    }


    @Test
    void checkIsPrime_True() {

        assertTrue(_assigment1.isPrime(2));
        assertTrue(_assigment1.isPrime(3));
        assertTrue(_assigment1.isPrime(11));
        assertTrue(_assigment1.isPrime(431));
        assertTrue(_assigment1.isPrime(67));

    }

    @Test
    void checkIsPrime_False() {

        assertFalse(_assigment1.isPrime(1));
        assertFalse(_assigment1.isPrime(0));
        assertFalse(_assigment1.isPrime(4));
        assertFalse(_assigment1.isPrime(-1));
        assertFalse(_assigment1.isPrime(-54));

    }


    @Test
    void positiveDecToOct_ReturnsNonNegativeResults() {

        assertEquals("=0o13=", _assigment1.decToOct(11));
        assertEquals("=0o17=", _assigment1.decToOct(15));
        assertEquals("=0o20=", _assigment1.decToOct(16));
        assertEquals("=0o400=", _assigment1.decToOct(256));
        assertEquals("=0o0=", _assigment1.decToOct(0));

    }

    @Test
    void negativeDecToOct_ReturnsNegativeResults() {

        assertEquals("=1o52=", _assigment1.decToOct(-42));
        assertEquals("=1o157=", _assigment1.decToOct(-111));

    }


}