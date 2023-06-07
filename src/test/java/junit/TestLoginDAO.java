package junit;

import com.example.aroundtheworld.dao.LoginDAO;
import com.example.aroundtheworld.engineering.factory.LoginDAOFactory;
import com.example.aroundtheworld.model.UserProfile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestLoginDAO {

    /* SILVIA PERELLI
       Il seguente test verifica che venga ritornato il tipo di ruolo
       corretto quando viene effettuato il login con il profilo di una famiglia */

    @Test
    void testCheckUser(){
        LoginDAO loginDAO = LoginDAOFactory.getInstance().createLoginDAO();
        UserProfile userProfile = loginDAO.checkUser("cesaroni@gmail.com","123");

        assertEquals(2, userProfile.getRole()); //il test ha successo in quanto restituisce il ruolo 2, corrispondente alla famiglia
    }
}
