import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Archivo implements Serializable {
    public static void main(String[] args) throws Exception {
    }

    public String[] getCadenas(String path) {
        BufferedReader bReader = null;

        try {
            FileReader file = new FileReader(path);
            bReader = new BufferedReader(file);

            List<String> lista = new ArrayList<String>();

            String linea;

            while ((linea = bReader.readLine()) != null)
                lista.add(linea);

            String[] areglo = new String[lista.size()];

            lista.toArray(areglo);

            return areglo;
        } catch (FileNotFoundException e) {
            System.out.println("[!!] Ocurrió un error, el archivo '" + path + "' no existe. ");
        } catch (Exception e) {
            System.err.println("[!!] Ocurrió un error, no podemos leer el archivo. ");
        } finally {
            if (bReader == null)
                return null;

            try {
                bReader.close();
            } catch (Exception e) {
                System.out.println("[!!] Ocurrió un error: no podemos cerrar el archivo : " + path);
            }

        }

        return null;
    };

    public void setCadenas(String[] areglo, String path) {
        BufferedWriter bWriter = null;

        try {
            FileWriter file = new FileWriter(path);
            bWriter = new BufferedWriter(file);

            for (String string : areglo) {
                if (string == null)
                    break;
                bWriter.write(string);
                bWriter.newLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("[!!] Ocurrió un error: El archivo '" + path + "' no existe. ");
        } catch (Exception e) {
            System.err.println("[!!] Ocurrió un error: No podemos escribir el archivo : " + path);
        } finally {
            if (bWriter == null)
                return;

            try {
                bWriter.close();
            } catch (Exception e) {
                System.out.println("[!!] Ocurrió un error: no pudimos cerrar el archivo : " + path);
                System.out.println(e.getMessage());
            }

        }
    }

    public void setObjetos(Object[] objetos, String path) {
        ObjectOutputStream output = null;

        try {
            FileOutputStream fos = new FileOutputStream(path);
            output = new ObjectOutputStream(fos);

            for (Object objeto : objetos) {
                if (objeto == null)
                    break;

                output.writeObject(objeto);
            }

        } catch (FileNotFoundException e) {
            System.out.println("[!!] Ocurrió un error, el archivo '" + path + "' no existe. ");
        } catch (Exception e) {
            System.err.println("[!!] Ocurrió un error, no podemos escribir el archivo: " + path);
        }

        if (output == null)
            return;

        try {
            output.close();
        } catch (Exception e) {
            System.out.println("[!!] Ocurrió un error, no podemos cerrar el archivo : " + path);
        }

    }

    public Object[] getObjetos(String path) {
        ObjectInputStream input = null;
        List<Object> listaObjectos = new ArrayList<Object>();

        try {
            FileInputStream archivo = new FileInputStream(path);
            input = new ObjectInputStream(archivo);

            Object objecto;
            while ((objecto = input.readObject()) != null)
                listaObjectos.add(objecto);
        } catch (EOFException e) {

        } catch (FileNotFoundException e) {
            System.out.println("[!!] Ocurrió un error: El archivo '" + path + "' no existe. ");
        } catch (Exception e) {
            System.err.println("[!!] Ocurrió un error: No pudimos leer el archivo '" + path + "' .");
        }

        if (input == null)
            return new Object[0];

        try {
            input.close();
        } catch (Exception e) {
            System.out.println("[!!] Ocurrió un error: no podemos cerrar el archivo : " + path);
        }

        Object[] arregloObjectos = new Object[listaObjectos.size()];

        listaObjectos.toArray(arregloObjectos);

        return arregloObjectos;
    };
}
