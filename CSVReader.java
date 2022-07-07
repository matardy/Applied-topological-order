import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {

    public String splitBy; // Bajo que caracter seran separados los datos
    public String Path; // Direccion del dococumento .csv
    public BufferedReader DataBuffered; // Buffer para guardar la data
    public int DataSize; // Numero de filas de la data

    // Constructor
    CSVReader(String SplitBy, String PathToFile) {
        this.splitBy = SplitBy;
        this.Path = PathToFile;
        int i = 0; // contador del temaaño
        String line; // String de lectura

        try {
            // Pasa el .csv al constructor del Buffer
            this.DataBuffered = new BufferedReader(new FileReader(PathToFile));
            while ((line = this.DataBuffered.readLine()) != null) {
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.DataSize = i - 1;
    }

    // Actualiza el buffer the la data
    void updateBuffer() {
        try {
            this.DataBuffered = new BufferedReader(new FileReader(this.Path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void printData() {

        String[] DataFrame;
        String line = "";

        try {
            while ((line = this.DataBuffered.readLine()) != null) {
                DataFrame = line.split(this.splitBy);
                System.out.println("Malla[Index= " + DataFrame[0] + " , " + "Materia= " + DataFrame[1] +
                        " , " + "Prerequisito= " + DataFrame[2] + " , " + "Codigo= " + DataFrame[3] + " , " + "Creditos= " +
                        DataFrame[4] + " , " + "Horas= " + DataFrame[5] + "]");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Retorna Estructura de datos <ArrayList>, en cada espacio guarda un Objeto del tipo Malla
    ArrayList<Malla> DataIntoArray() {

        ArrayList<Malla> Data = new ArrayList<Malla>();
        String line = "";
        String[] DataFrame;

        try {
            while ((line = this.DataBuffered.readLine()) != null) {
                DataFrame = line.split(this.splitBy);
                Data.add(new Malla(DataFrame[0], DataFrame[1], DataFrame[2], DataFrame[3], DataFrame[4], DataFrame[5]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Data;
    }

}