package _assigment1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class Assigment1Tests {

    private static Assigment1 _assigment1;

    @BeforeAll
    static void setUp () {
        System.out.println("Start!");
        _assigment1 = new Assigment1();
    }

    @AfterEach
    void sayHi() {
        System.out.println("Hello!");
    }

    @AfterAll
    static void tearDown() {
        System.out.println("Finish");
        _assigment1 = null;
    }

    @ParameterizedTest
    @MethodSource("seedNumbersForRevertNumber")
    void revertNumber_ReturnsRevertedNumber(int param, int expected) {

        assertEquals(expected, _assigment1.revertNumber(param));

    }
    private static Stream<Arguments> seedNumbersForRevertNumber() {
        return Stream.of(
                Arguments.of(321,123),
                Arguments.of(0,0),
                Arguments.of(1443,3441),
                Arguments.of(-321,-123)
        );
    }


    @ParameterizedTest
    @MethodSource("seedArraysForInsertionSort")
     void insertionSort_ReturnsSortedArray(int[] param, int[] expected) {

        assertArrayEquals(expected, _assigment1.insertionSort(param));
    }

    private static Stream<Arguments> seedArraysForInsertionSort() {
        return Stream.of(
                Arguments.of(new int[]{2, 4, 1},new int[]{1, 2, 4}),
                Arguments.of(new int[]{1, 1, 1, 1, 1, 1},new int[]{1, 1, 1, 1, 1, 1}),
                Arguments.of(new int[]{4, 6, -2, 5, -1},new int[]{-2, -1, 4, 5, 6})
        );
    }

    @ParameterizedTest
    @MethodSource("seedDecToHexParams")
    void positiveDecToHex_ReturnsNonNegativeResults(int param, String expected) {

        assertEquals("=0xB=", _assigment1.decToHex(11));
        assertEquals("=0xF=", _assigment1.decToHex(15));
        assertEquals("=0x10=", _assigment1.decToHex(16));
        assertEquals("=0x100=", _assigment1.decToHex(256));
        assertEquals("=0x0=", _assigment1.decToHex(0));

    }

    private static Stream<Arguments> seedDecToHexParams() {
        return Stream.of(
                Arguments.of(11,"=0xB="),
                Arguments.of(15,"=0xF="),
                Arguments.of(16,"=0x10="),
                Arguments.of(256,"=0x100="),
                Arguments.of(0,"=0x0=")
        );
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


    @ParameterizedTest
    @ValueSource(ints = {2,3,11,431,67})
    void checkIsPrime_True(int param) {

        assertTrue(_assigment1.isPrime(param));
    }

    @ParameterizedTest
    @ValueSource(ints = {1,0,4,-1,-54,8})
    void checkIsPrime_False(int param) {

        assertFalse(_assigment1.isPrime(param));
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