/**
 * Esta clase permite encapsular los datos de la malla curricular por fila.
 * Malla[Indice, materia, prerequisito, codigo, creditos, horas]
 * ..
 * ..
 * Para ser guardado en una estructura de datos.
 */

public class Malla {
    public String indice, materia, prerequisito, codigo, creditos, horas;

    //Constructor
    public Malla(String indice, String materia, String prerequisito, String codigo, String creditos, String horas) {
        this.indice = indice;
        this.materia = materia;
        this.prerequisito = prerequisito;
        this.codigo = codigo;
        this.creditos = creditos;
        this.horas = horas;
    }
}
