package helpers.config2test;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final String DEFAULT_CONTRACT_TYPE = "AQA_CONTRACT_FIXED_REBATE";
    public static final List<String> VALID_CONTRACT_PARAMETERS = Arrays.asList(
            "isVerbalDeal",
            "invoicingLevel",
            "contractType"
    );
}
