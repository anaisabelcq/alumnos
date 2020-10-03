package com.housestark;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Supplier;

public class DirectJava {
    /**
     * Realiza append el contenido de un String en un archivo
     * si el archivo NO existe lo crea, si existe lo sobreescribe
     * el encodign es siempre utf-8
     */
    public static void af(String path, String contents) {
        try {
            org.apache.commons.io.FileUtils.writeStringToFile(
                    new File(path),
                    contents,
                    "UTF-8",
                    true
            );
        }
        catch (Exception ex) {
            thr("No se pudo escribir: ", path);
        }
    }
    /**
     *
     * Muestra en pantalla, los elementos recibidos
     */
    public static void cat(String... params) {
        System.out.println(String.join(" ", params));
    }

    /**
     *
     * Incrementa un numero dentro de una variable de tipo String
     *
     */
    public static String inc(String n) {
        return tryExec( () -> {
            Long l = Long.parseLong(n);
            l++;
            return String.valueOf(l);
        }, "No se puede incrementar supuesto numero: ", n);
    }

    /**
     *
     * dados unos parametros de input, genera un String.join sin
     * separador
     */
    public static String jn(String... params) {
        return String.join("", params);
    }

    /**
     *
     * crea un directorio, si existe y tiene
     * archivos, tira exception
     */

    public static void mkDir(String... params) {
        String dir = jn(params);
        cat(dir);
        File f = new File(dir);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            return;
        }
    }

    public static void mv(String pathOrg, String pathDst) {
        // File (or directory) with old name
        File file1 = new File(pathOrg);
        File file2 = new File(pathDst);

        if (!file1.exists()) {
            thr(pathOrg, "no existe");
            return;
        }

        if (file2.exists()) {
            thr(pathDst, "existe");
            return;
        }

        boolean success = file1.renameTo(file2);
        thrIfTrue(success, "No pudo ser renombrado", pathOrg, pathDst);
    }

    /**
     * Convierte un valor entero a sha1checksum
     */

    public static String sha1(Integer valor) {
        byte[] losBytes = DigestUtils
                .getSha1Digest()
                .digest(valor.toString()
                        .getBytes(Charset.defaultCharset()));

        String hexString = new BigInteger(losBytes)
                .toString(16);
        return hexString;
    }

    /**
     * @param str un string que puede ser partido
     * @param sizeModulo cantidad de caracteres de cada modulo a partir
     * @return
     */

    public static String[] splitStr(String str, Integer sizeModulo) {
        thrIfTrue(str.length() % sizeModulo != 0, str, " no es divisible por ", ""+sizeModulo);
        Integer cantidadDeModulos = str.length() / sizeModulo;
        ArrayList<String> lstStr = new ArrayList<>();
        for (int x = 0; x < cantidadDeModulos; x++) {
            int base = (x * sizeModulo);
            String pieza = str.substring(base, base + sizeModulo);
            lstStr.add(pieza);
        }
        String[] arr = new String[lstStr.size()];
        return lstStr.toArray(arr);
    }

    /**
     * Realiza un throw RuntimeException
     * Utiliza composicion de mensajes sin separador
     */
    public static void thr(String... params) {
        String errMessage = "Atención se produjo un error";
        jn(errMessage, System.lineSeparator(), jn(params));
        throw new RuntimeException(jn(params));
    }

    /**
     * Realiza un throw RuntimeException si la condición es true
     */

    public static void thrIfTrue(boolean condicion, String... params) {
        String errMessage = "Atención se produjo un error";
        jn(errMessage, System.lineSeparator(), jn(params));
        if (condicion) {
            throw new RuntimeException(jn(params));
        }
    }

    /**
     *
     * Intenta ejecutar una funcion y de dar exception
     * arroja un RuntimeException con la leyenda prevista
     */
    public static <T> T tryExec(Supplier<T> fn, String... params) {
        try {
            return fn.get();
        }
        catch (Exception ex) {
            throw new RuntimeException(jn(params));
        }
    }

    /**
     * Escribe el contenido de un String en un archivo
     * si el archivo NO existe lo crea, si existe lo sobreescribe
     * el encodign es siempre utf-8
     */
    public static void wf(String path, String contents) {
        try {
            mkDir(path);
        }
        catch (Exception ex) {
            thr("No se pudo crear el directorio asicoado", path);
        }

        try {
            org.apache.commons.io.FileUtils.writeStringToFile(
                    new File(path),
                    contents,
                    "UTF-8"
            );
        }
        catch (Exception ex) {
            thr("No se pudo escribir: ", path);
        }
    }
}
