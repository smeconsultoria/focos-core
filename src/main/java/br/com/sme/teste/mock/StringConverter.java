package br.com.sme.teste.mock;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class StringConverter {

    private static final char HEXCHAR[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final String HEXINDEX = "0123456789abcdef          ABCDEF";


    public static String string2zippedHex(String string) throws Exception {
        return byteToHex(string2zip(string));
    }

    public static String zippedHex2string(String string) throws Exception {
        return new String(zip2byte(hexToByte(string)));
    }

    /**
     * Method declaration
     *
     * @param s
     * @return
     */
    public static byte[] hexToByte(String s) {
        int l = s.length() / 2;
        byte data[] = new byte[l];
        int j = 0;

        for (int i = 0; i < l; i++) {
            char c = s.charAt(j++);
            int n, b;

            n = HEXINDEX.indexOf(c);
            b = (n & 0xf) << 4;
            c = s.charAt(j++);
            n = HEXINDEX.indexOf(c);
            b += (n & 0xf);
            data[i] = (byte) b;
        }

        return data;
    }

    /**
     * Method declaration
     *
     * @param b
     * @return
     */
    public static String byteToHex(byte b[]) {
        int len = b.length;
        StringBuffer s = new StringBuffer();

        for (int i = 0; i < len; i++) {
            int c = ((int) b[i]) & 0xff;

            s.append(HEXCHAR[c >> 4 & 0xf]);
            s.append(HEXCHAR[c & 0xf]);
        }

        return s.toString();
    }

    /**
     * Method declaration
     *
     * @param s
     * @return
     */
    public static String unicodeToHexString(String s) {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bout);

        try {
            out.writeUTF(s);
            out.close();
            bout.close();
        } catch (IOException e) {
            return null;
        }

        return byteToHex(bout.toByteArray());
    }

    /**
     * Method declaration
     *
     * @param s
     * @return
     */
    public static String hexStringToUnicode(String s) {

        byte[] b = hexToByte(s);
        ByteArrayInputStream bin = new ByteArrayInputStream(b);
        DataInputStream in = new DataInputStream(bin);

        try {
            return in.readUTF();
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Method declaration
     *
     * @param s
     * @return
     */
    public static String unicodeToAscii(String s) {
        if (s == null || s.equals("")) {
            return s;
        }

        int len = s.length();
        StringBuffer b = new StringBuffer(len);

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);

            if (c == '\\') {
                if (i < len - 1 && s.charAt(i + 1) == 'u') {
                    b.append(c);    // encode the \ as unicode, so 'u' is ignored
                    b.append("u005c");    // splited so the source code is not changed...
                } else {
                    b.append(c);
                }
            } else if ((c >= 0x0020) && (c <= 0x007f)) {
                b.append(c);    // this is 99%
            } else {
                b.append("\\u");
                b.append(HEXCHAR[(c >> 12) & 0xf]);
                b.append(HEXCHAR[(c >> 8) & 0xf]);
                b.append(HEXCHAR[(c >> 4) & 0xf]);
                b.append(HEXCHAR[c & 0xf]);
            }
        }
        return b.toString();
    }

    /**
     * Method declaration
     *
     * @param s
     * @return
     */
    public static String asciiToUnicode(String s) {
        if (s == null || s.indexOf("\\u") == -1) {
            return s;
        }

        int len = s.length();
        char b[] = new char[len];
        int j = 0;

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);

            if (c != '\\' || i == len - 1) {
                b[j++] = c;
            } else {
                c = s.charAt(++i);

                if (c != 'u' || i == len - 1) {
                    b[j++] = '\\';
                    b[j++] = c;
                } else {
                    int k = (HEXINDEX.indexOf(s.charAt(++i)) & 0xf) << 12;

                    k += (HEXINDEX.indexOf(s.charAt(++i)) & 0xf) << 8;
                    k += (HEXINDEX.indexOf(s.charAt(++i)) & 0xf) << 4;
                    k += (HEXINDEX.indexOf(s.charAt(++i)) & 0xf);
                    b[j++] = (char) k;
                }
            }
        }
        return new String(b, 0, j);
    }

    /**
     * Method declaration
     *
     * @param x
     * @return
     * @throws SQLException
     */
    public static String InputStreamToString(InputStream x) throws SQLException {
        InputStreamReader in = new InputStreamReader(x);
        StringWriter write = new StringWriter();
        int blocksize = 8 * 1024;    // todo: is this a good value?
        char buffer[] = new char[blocksize];

        try {
            while (true) {
                int l = in.read(buffer, 0, blocksize);

                if (l == -1) {
                    break;
                }
                write.write(buffer, 0, l);
            }

            write.close();
            x.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return write.toString();
    }

    public static String byteArrayToHexString(byte in[]) {
        byte ch = 0x00;
        int i = 0;
        if (in == null || in.length <= 0) {
            return null;
        }

        String pseudo[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        StringBuffer out = new StringBuffer(in.length * 2);
        while (i < in.length) {
            ch = (byte) (in[i] & 0xF0); // Strip off high nibble
            ch = (byte) (ch >>> 4);
            // shift the bits down
            ch = (byte) (ch & 0x0F);
            //must do this is high order bit is on!
            out.append(pseudo[(int) ch]);
            // convert the nibble to a String Character
            ch = (byte) (in[i] & 0x0F);
            // Strip off		low nibble
            out.append(pseudo[(int) ch]);
            // convert thenibble to a String Character
            i++;
        }
        String rslt = new String(out);
        return rslt;
    }

    public static byte[] string2zip(String string2zip) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        ZipOutputStream zos = new ZipOutputStream(bos);
        zos.setLevel(9);
        ZipEntry ze = new ZipEntry("a");

        zos.putNextEntry(ze);
        zos.write(string2zip.getBytes("ISO-8859-1"));

        zos.close();


//		zos.write(string2zip.getBytes());
//		zos.flush();
//		bos.flush();
        byte[] result = bos.toByteArray();
        bos.close();
        zos.close();

        return result;
    }

    public static byte[] zip2byte(byte[] zippedByteArray) throws Exception {
        ByteArrayInputStream bin = new ByteArrayInputStream(zippedByteArray);
        ZipInputStream zin = new ZipInputStream(bin);
        ZipEntry ze = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((ze = zin.getNextEntry()) != null) {
            // System.out.println("Unzipping " + ze.getName());
            for (int c = zin.read(); c != -1; c = zin.read()) {
                bos.write(c);
            }
            zin.closeEntry();
            bos.close();
        }
        zin.close();

        byte[] result = bos.toByteArray();
        bos.close();

        return result;
    }

    public static String hashMd5(String string) {
        return new String(gerarHash(string, "MD5"));
    }

    public static String hashMd5Hexa(String string) {
        return byteArrayToHexString(gerarHash(string, "MD5"));
    }

    public static byte[] gerarHash(String frase, String algoritmo) {
        try {
            MessageDigest md = MessageDigest.getInstance(algoritmo);
            md.update(frase.getBytes());
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
