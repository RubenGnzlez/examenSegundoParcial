package examenTest;

import dao.conection.Conection;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.logging.Logger;

public class ConectionTest {
    @Test
    public void conecctionSingletonTest(){
        try{
            Conection con = Conection.getInstance();
            Assert.assertFalse(con.getConn().isClosed());
        }catch (ClassNotFoundException | SQLException ex){
            Logger.getAnonymousLogger().warning("La conexión es invalida.");
        }
    }
}
