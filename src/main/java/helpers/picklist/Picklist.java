package helpers.picklist;

import java.util.EnumSet;

public abstract class Picklist {

    //Enum<E extends Enum<E>>

    public String apiName;
    public String name;

    public abstract String getValue();

    public abstract String getApiName();

//    public <T extends Enum<T>> T getByApiValue(SObject[] sObjects, Class<T> aClass, String label) {
//        for (T en : EnumSet.allOf(aClass)) {
//            if (en.name().equalsIgnoreCase(label)) {
//                return en;
//            }
//        }
//        return null;
//    }

//    public <T extends Picklist> T getByApiValue(Enum<?> aClass, String label) {
//        for (T en : EnumSet.allOf(aClass)) {
//            if (en.apiName.equalsIgnoreCase(label)) {
//                return en;
//            }
//        }
//        return null;
//    }
}
