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

    public void calcularProbabilidad() {
        try (Connection con = Conexion.getConnection()) {

            CallableStatement cs = con.prepareCall("{call SP_CALCULAR_PROBABILIDAD}");
            cs.execute();

        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String[]> listar() {
        List<String[]> lista = new ArrayList<>();

        try (Connection con = Conexion.getConnection()) {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM EVENTOS");

            while (rs.next()) {
                lista.add(new String[]{
                        rs.getString("ID"),
                        rs.getString("NOMBRE_EVENTO"),
                        rs.getString("FRECUENCIA"),
                        rs.getString("PROBABILIDAD")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
