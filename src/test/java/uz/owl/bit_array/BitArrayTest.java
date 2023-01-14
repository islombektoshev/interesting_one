package uz.owl.bit_array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitArrayTest {


    @Test
    void testSetMethod() {
        BitArray arr = new BitArray(1);
        arr.set(63, true);
        assertTrue(arr.has(63));
        assertFalse(arr.has(62));
    }


    @Test
    void testSetFalseMethod() {
        BitArray arr = new BitArray(1);
        arr.set(63, true);
        arr.set(63, false);
        assertFalse(arr.has(63));
        assertFalse(arr.has(62));
    }

    @Test
    void testSetMethodWithBiggerSize() {
        BitArray arr = new BitArray(4);
        arr.set(63, true);
        arr.set(64, true);
        assertTrue(arr.has(63));
        assertTrue(arr.has(64));
        assertFalse(arr.has(62));
    }

    @Test
    void testSetFalseMethodWithBiggerSize() {
        BitArray arr = new BitArray(4);
        arr.set(63, true);
        arr.set(64, true);

        arr.set(63, false);
        arr.set(64, false);

        assertFalse(arr.has(63));
        assertFalse(arr.has(64));
        assertFalse(arr.has(62));

        arr.set(64, true);
        assertTrue(arr.has(64));
    }

    @Test()
    void testForError() {
        assertThrows(NegativeArraySizeException.class, () -> new BitArray(-1));

        {
            BitArray arr = new BitArray(1);
            assertThrows(IndexOutOfBoundsException.class, () -> arr.set(64, true));
        }

        {
            BitArray arr = new BitArray(1);
            assertThrows(IndexOutOfBoundsException.class, () -> arr.set(-1, false));
        }

        {
            BitArray arr = new BitArray(0);
            assertThrows(IndexOutOfBoundsException.class, () -> arr.set(0, false));
        }

        {
            BitArray arr = new BitArray(1);
            assertThrows(IndexOutOfBoundsException.class, () -> arr.has(64));
        }

        {
            BitArray arr = new BitArray(1);
            assertThrows(IndexOutOfBoundsException.class, () -> arr.has(-1));
        }
        {
            BitArray arr = new BitArray(0);
            assertThrows(IndexOutOfBoundsException.class, () -> arr.has(0));
        }

    }


    @Test
    void testMultiSetGet() {
        BitArray arr = new BitArray(2);


        arr.multiset(0, 0b1010101L, true);
        assertEquals(0b1010101L, arr.multiget(0));

        arr.multiset(0, 0b1010101L, false);
        assertEquals(0, arr.multiget(0));


        arr.multiset(1, 0b1010101L, true);
        arr.multiset(1, 0b1010000L, false);
        assertEquals(5L, arr.multiget(1));

        arr.multiset(1, 0b101L, false);
        assertEquals(0, arr.multiget(1));


    }

    @Test
    void testMultiSetWithHas() {
        BitArray arr = new BitArray(2);
        arr.multiset(0, 0b1010101L, true);
        assertTrue(arr.has(0));
        assertTrue(arr.has(2));
        assertTrue(arr.has(4));
        assertTrue(arr.has(6));
        assertFalse(arr.has(1));
        assertFalse(arr.has(3));
        assertFalse(arr.has(5));
        assertFalse(arr.has(7));

        arr.multiset(1, 0b1010101L, true);
        final int shift = 64;
        assertTrue(arr.has(shift + 0));
        assertTrue(arr.has(shift + 2));
        assertTrue(arr.has(shift + 4));
        assertTrue(arr.has(shift + 6));
        assertFalse(arr.has(shift + 1));
        assertFalse(arr.has(shift + 3));
        assertFalse(arr.has(shift + 5));
        assertFalse(arr.has(shift + 7));

        arr.multiset(0, 0b1010101L, false);
        arr.multiset(1, 0b1010101L, false);

        assertFalse(arr.has(0));
        assertFalse(arr.has(2));
        assertFalse(arr.has(shift + 4));
        assertFalse(arr.has(shift + 6));
    }


    @Test
    void testLength() {
        assertEquals(4 * 64, new BitArray(4).length());
        assertEquals(4, new BitArray(4).length64());
        assertEquals(0, new BitArray(0).length());
        assertEquals(0, new BitArray(0).length64());
    }
}
