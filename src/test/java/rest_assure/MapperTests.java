package rest_assure;

import mapper_objects.User;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class MapperTests extends BaseClass {

    @Test
    public void unmarshallingUserWithoutMapper() {
        User user = RestAssured.get(BASE_URL + "/users/rest-assured")
                .as(User.class);

        assertEquals(user.getLogin(), "rest-assured");
        assertEquals(user.getId(), 19369327);
        assertEquals(user.getGravatar_id(), "");
        assertNull(user.getCompany());
        assertEquals(user.getPublicRepos(), 2);
    }

    @Test
    public void unmarshallingUserWithMapper() {
        User user = RestAssured.get(BASE_URL + "/users/rest-assured")
                .as(User.class, ObjectMapperType.JACKSON_2);

        assertEquals(user.getLogin(), "rest-assured");
        assertEquals(user.getId(), 19369327);
        assertEquals(user.getGravatar_id(), "");
        assertNull(user.getCompany());
        assertEquals(user.getPublicRepos(), 2);
    }
}
