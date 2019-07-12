package com.example.demo.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SHA1Util {
    private static final char[] hexadecimal = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public SHA1Util() {
    }

    public static byte[] sha1(byte[] data) {
        if (data == null) {
            return null;
        } else {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA1");
                return md.digest(data);
            } catch (NoSuchAlgorithmException var2) {
                var2.printStackTrace();
                return null;
            }
        }
    }

    public static String getSHA1String(byte[] data) {
        byte[] b = sha1(data);
        if (b == null) {
            return null;
        } else {
            char[] buffer = new char[b.length * 2];

            for(int i = 0; i < b.length; ++i) {
                int low = b[i] & 15;
                int high = (b[i] & 240) >> 4;
                buffer[i * 2] = hexadecimal[high];
                buffer[i * 2 + 1] = hexadecimal[low];
            }

            return new String(buffer);
        }
    }

    public static String getSHA1String(String data) {
        return data == null ? null : getSHA1String(data.getBytes());
    }

    public static String getSHA1StringFromUtf8(String data) {
        if (data == null) {
            return null;
        } else {
            byte[] z = null;

            try {
                z = data.getBytes("utf-8");
            } catch (UnsupportedEncodingException var3) {
            }

            if (z != null && z.length >= 3 && z[0] == -17 && z[1] == -69 && z[2] == -65) {
                int off = 3;
                z = Arrays.copyOfRange(z, off, z.length);
            }

            return getSHA1String(z);
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i <= 10; ++i) {
            long s = System.currentTimeMillis();
            System.out.println(getSHA1StringFromUtf8("ok" + i));
            System.out.println(System.currentTimeMillis() - s);
        }

        System.out.println(getSHA1StringFromUtf8("123456rttxAdminkey"));
        System.out.println(getSHA1StringFromUtf8("123456zyxrAdminkey"));
    }
}

