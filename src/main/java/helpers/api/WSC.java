package helpers.api;

import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.DeleteResult;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.SaveResult;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
import helpers.config2test.AppUser;
import helpers.config2test.Parameter;

import java.util.Arrays;
import java.util.List;

public class WSC {

    public static String create(SObject sObject) {
        try {
            SaveResult[] saveResults = WSC.getAPIConnection().create(new SObject[]{sObject});
            if (saveResults[0].getErrors().length > 0) {
                throw new IllegalStateException("Create SObject Error: " + Arrays.toString(saveResults[0].getErrors()));
            }
            return saveResults[0].getId();
        } catch (ConnectionException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String create(SObject sObject, AppUser appUser) {
        AppUser previousUser = AppUser.getCurrentUser();
        AppUser.switchToUser(appUser);
        String recordId = create(sObject);
        AppUser.switchToUser(previousUser);
        return recordId;
    }

    public static void update(SObject sObject) {
        try {
            SaveResult[] saveResults = WSC.getAPIConnection().update(new SObject[]{sObject});
            if (saveResults[0].getErrors().length > 0) {
                throw new IllegalStateException("Update SObject Error: " + Arrays.toString(saveResults[0].getErrors()));
            }
        } catch (ConnectionException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void update(SObject sObject, AppUser appUser) {
        AppUser previousUser = AppUser.getCurrentUser();
        AppUser.switchToUser(appUser);
        update(sObject);
        AppUser.switchToUser(previousUser);
    }

    public static void delete(String id) {
        try {
            DeleteResult[] saveResults = WSC.getAPIConnection().delete(new String[]{id});
            if (saveResults[0].getErrors().length > 0) {
                throw new IllegalStateException("Delete by Id Error: " + Arrays.toString(saveResults[0].getErrors()));
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        } catch (ConnectionException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void delete(String id, AppUser appUser) {
        AppUser previousUser = AppUser.getCurrentUser();
        AppUser.switchToUser(appUser);
        delete(id);
        AppUser.switchToUser(previousUser);
    }

    public static void delete(List<String> ids) {
        try {
            DeleteResult[] saveResults = WSC.getAPIConnection().delete(ids.toArray(new String[0]));
            if (saveResults[0].getErrors().length > 0) {
                throw new IllegalStateException("Delete by Ids Error: " + Arrays.toString(saveResults[0].getErrors()));
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException ignored) {
        } catch (ConnectionException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void delete(List<String> ids, AppUser appUser) {
        AppUser previousUser = AppUser.getCurrentUser();
        AppUser.switchToUser(appUser);
        delete(ids);
        AppUser.switchToUser(previousUser);
    }

    public static SObject[] query(String query) {
        try {
            return WSC.getAPIConnection().query(query).getRecords();
        } catch (ConnectionException e) {
            throw new IllegalStateException(e);
        }
    }

    private static PartnerConnection getAPIConnection() {
        ConnectorConfig connectorConfig = new ConnectorConfig();
        connectorConfig.setUsername(AppUser.getCurrentUser().getUsername());
        connectorConfig.setPassword(AppUser.getCurrentUser().getPassword());
        connectorConfig.setAuthEndpoint(Parameter.ORG_URL.get() + "/services/Soap/u/48.0");
        try {
            return Connector.newConnection(connectorConfig);
        } catch (ConnectionException e) {
            throw new IllegalStateException(e);
        }
    }
}