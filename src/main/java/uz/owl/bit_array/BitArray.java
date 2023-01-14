package uz.owl.bit_array;


import java.io.Serializable;
import java.lang.annotation.Native;

/**
 * BitArray its utility to use set and gets bits with given index
 * BitArray uses long array to store bits
 *
 */
public class BitArray implements Serializable {
    private final long[] bits;
    @java.io.Serial
    private static final long serialVersionUID = 8821689949313635871L;



    public BitArray(int length64) {
        if (length64 < 0) throw new NegativeArraySizeException(length64 + "");
        bits = new long[length64];
    }

    public void set(long index, boolean value) {
        int index64 = (int) (index / 64);
        long indexUnder64 = index % 64;
        if (index64 > bits.length || index < 0) {
            throw new IndexOutOfBoundsException("Index %s out of bound for length %s".formatted(index, bits.length * 64L));
        }

        if (value) {
            bits[index64] = bits[index64] | (1L << indexUnder64);
        } else {
            bits[index64] = bits[index64] & ~(1L << indexUnder64);
        }
    }




    public void multiset(int index64, long indexes, boolean value) {
        if (index64 > bits.length || index64 < 0) {
            throw new IndexOutOfBoundsException("Index %s|64 out of bound for length %s|64".formatted(index64, bits.length));
        }
        if (value) {
            bits[index64] = bits[index64] | indexes;
        } else {
            bits[index64] = bits[index64] & ~indexes;
        }
    }

    public long multiget(int index64) {
        if (index64 > bits.length || index64 < 0) {
            throw new IndexOutOfBoundsException("Index %s|64 out of bound for length %s|64".formatted(index64, bits.length));
        }
        return bits[index64];
    }

    public boolean has(long index) {
        int index64 = (int) (index / 64);
        long indexUnder64 = index % 64;
        if (index64 > bits.length || index < 0) {
            throw new IndexOutOfBoundsException("Index %s out of bound for length %s".formatted(index, bits.length * 64L));
        }
        return (bits[index64] & (1L << indexUnder64)) != 0L;
    }

    public int length64() {
        return bits.length;
    }

    public long length() {
        return 64L * bits.length;
    }


}
