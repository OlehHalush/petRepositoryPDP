package com.customertimes.api;

import com.customertimes.picklist.Picklist;
import com.sforce.soap.partner.sobject.SObject;

import java.util.EnumSet;

public class Get {

//    public static <E extends Enum<E>> E getSome(SObject[] sObjects, Class<E> aClass, String label) {
//
//        for (E en : EnumSet.allOf(aClass)) {
//            if (en.name().equalsIgnoreCase(label)) {
//                return en;
//            }
//        }
//        return null;
//        return Optional.ofNullable(sObjects[0]).map(s -> aClass.getByApiValue(aClass, sObjects[0].getField(label).toString())).orElse(null);
//}

    public static <E extends Picklist> E getSome(SObject[] sObjects, Class<E> anEnum, String label) {
        for (E en : EnumSet.allOf(anEnum)) {
            if (en.apiName.equalsIgnoreCase(label)) {
                return en;
            }
        }
        return null;
    }
}
