package junit;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.FamilyRequestDAO;
import com.example.aroundtheworld.dao.queries.SimpleQueries;
import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.FamilyPreferences;
import com.example.aroundtheworld.model.FamilyRequest;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestFamilyRequestDAO {

      /* SARA MALASPINA
         Il seguente test verifica che dopo l'aggiunta di una nuova richiesta per una famiglia, chiamando un
         metodo del DAO che restituisce il numero di richieste per quella famiglia, questo venga incrementato di 1 */

    @Test
    void testNewRequest(){
        Connection connection;
        ResultSet resultSet;
        int count = 0;
        int newCount = 0;

        try {
            connection = ConnectionDB.getConnection();
            resultSet = SimpleQueries.getNumberOfRequests(connection, 1);
            if (!resultSet.first()) {
                throw new NotFoundException("No family found");
            }
            resultSet.first();
            count = resultSet.getInt("requests");


            FamilyRequest familyRequest = new FamilyRequest("Rome", "2024-01-10", "2024-01-20", 0, 1, 5, 0);
            familyRequest.setIdFamily(1);
            familyRequest.setCompatibility(80);

            FamilyPreferences familyPreferences = new FamilyPreferences();
            familyPreferences.setHouse("shared");
            familyPreferences.setFood(0, 0);
            familyPreferences.setHobbies(1, 0, 0, 0, 1, 1, 1);
            familyRequest.setFamilyPreferences(familyPreferences);

            FamilyRequestDAO.newRequest(familyRequest);

            resultSet = SimpleQueries.getNumberOfRequests(connection, 1);
            if (!resultSet.first()) {
                throw new NotFoundException("No family found");
            }
            resultSet.first();
            newCount = resultSet.getInt("requests");
        } catch (Exception ignore) {
        }

        assertEquals(count+1, newCount); //il test ha successo in quanto la richiesta viene aggiunta correttamente
    }
}
