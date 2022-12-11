import java.io.Serializable;
import java.util.Random;

public class Id implements Serializable {
  public static void main(String[] args) {
  }

  public String alfanumerico() {
    Random ran = new Random();

    char[] banco = { 'a', 'b', 'c', 'x', 'y' };
    StringBuilder clave = new StringBuilder();

    for (int i = 1; i <= 5; i++) {
      char caracter = banco[ran.nextInt(5)];

      clave.append(caracter);
      clave.append(ran.nextInt(10));
    }

    return clave.toString();
  }

  public String numerico() {
    Random ran = new Random();

    StringBuilder clave = new StringBuilder();

    for (int i = 1; i <= 9; i++) {
      clave.append(ran.nextInt(10));
    }

    return clave.toString();
  }

}
