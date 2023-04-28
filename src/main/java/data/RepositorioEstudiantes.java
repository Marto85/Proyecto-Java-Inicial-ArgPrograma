package data;

import modelo.Estudiante;

import java.sql.*;

public class RepositorioEstudiantes {
    private Connection conexion;
    public RepositorioEstudiantes(Connection conexion){
        this.conexion = conexion;

    }
    public void agregarEstudiantes(Estudiante unEstudiante){
        try (Statement sentenciaInsert = conexion.createStatement()) {
            String insert = "insert into Estudiantes (numero,nombre,genero,especie,blod_status,id_casa) values(";
            insert += unEstudiante.getNumero() + ", ";
            insert += unEstudiante.getNombre() + ",";
            insert += unEstudiante.getGenero() + ",";
            insert += unEstudiante.getEspecie() + ",";
            insert += unEstudiante.getBlodStatus() + ",";
            insert += unEstudiante.getCasa().getIdCasa();
            insert += ")";
            sentenciaInsert.executeUpdate(insert);

        }
        catch (SQLException ex) {
            System.out.println("Error: en sentencia de creación de casa: \n" + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public Estudiante getEstudiantes(int numero){
        Estudiante result = null;
        try (Statement sentenciaConsulta = conexion.createStatement()) {
            String query = "select nombre from Estudiantes where numero = ";
            query += numero;
            ResultSet rs = sentenciaConsulta.executeQuery(query);

            while (rs.next())
            {
                numero = rs.getInt(1);
                String nombre = rs.getString(2);
                char genero = rs.getString(3).charAt(0);
                String especie = rs.getString(4);
                String blod_status = rs.getString(5);
                result = new Estudiante(numero,nombre,genero,especie,blod_status);

            }

        }
        catch (SQLException ex) {
            System.out.println("Error: en sentencia de creación de estudiante: \n" + ex.getMessage());
            ex.printStackTrace();
        }
        return result;

    }
}
