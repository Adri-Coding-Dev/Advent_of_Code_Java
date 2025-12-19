package utils_2015;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils_Day04 {
    public static long solvePart1(int year, int day) {
        String input = "ckczppom";
        int clave = 0;
        for (int i = 0; !comprobarHash(getMD5Hash(input + i), 5); i++) {
            clave++;
        }
        return clave;
    }

    public static long solvePart2(int year, int day) {
        String input = "ckczppom";
        int clave = 0;
        for (int i = 0; !comprobarHash(getMD5Hash(input + i), 6); i++) {
            clave++;
        }
        return clave;
    }

    public static boolean comprobarHash(String md5Hash, int numeroDeCeros) {
        for (int i = 0; i < numeroDeCeros; i++) {
            if (md5Hash.charAt(i) != '0') {
                return false;
            }
        }
        return true;
    }

    public static String getMD5Hash(String input) {
        try {
            // Get an MD5 digest object
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Compute the digest
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception if MD5 algorithm is not available
            e.printStackTrace();
            return null;
        }
    }
}
