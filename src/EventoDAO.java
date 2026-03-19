import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    public void insertar(String nombre, int frecuencia) {
        try (Connection con = Conexion.getConnection()) {

            CallableStatement cs = con.prepareCall("{call SP_INSERT_EVENTO(?,?)}");
            cs.setString(1, nombre);
            cs.setInt(2, frecuencia);
            cs.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
