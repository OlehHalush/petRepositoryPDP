package helpers.api;

public class ApexCode {

    public static String birthdayReminderTaskBatch() {
        return "BirthdayTaskCreatorSchedule.execute(null);";
    }

    public static String marketingSupportTaskBatch() {
        return "Database.executebatch(new Core_MarketingSupportTaskJob());";
    }

    public static String supervisionTaskBatch() {
        return "Database.executeBatch(new Core_SupervisionTaskJob());";
    }

    public static String supervisionTaskByAgreementIdBatch(String contractAgreementId) {
        return "List<Id> contractIds = new List<Id>{ '" + contractAgreementId + "' }; \n" +
                "List<APXT_Redlining__Contract_Agreement__c> getContractById(List<Id> contractIds) {\n" +
                "    return [\n" +
                "            SELECT\n" +
                "                    Name,\n" +
                "                    Supervision_level__c,\n" +
                "                    Market__c,\n" +
                "                    CurrencyIsoCode,\n" +
                "                    OwnerId,\n" +
                "                    Contract_Id__c,\n" +
                "                    Volume_in_uom__c,\n" +
                "                    APXT_Redlining__Effective_Date__c,\n" +
                "                    APXT_Redlining__Account__r.Name, (\n" +
                "                    SELECT\n" +
                "                            Name,\n" +
                "                            Date__c,\n" +
                "                            Core_SupervisionDate__c,\n" +
                "                            Contract_Agreement__c,\n" +
                "                            Contract_activation__c,\n" +
                "                            Core_Origin__c,\n" +
                "                            Core_SupervisionLevel__c,\n" +
                "                            Contract_financial_support__c,\n" +
                "                            Contract_marketing_support__c,\n" +
                "                            Core_IsFutureVolumeSupervision__c,\n" +
                "                            Amount__c,\n" +
                "                            Core_HasPrePayment__c,\n" +
                "                            Core_OriginalDate__c\n" +
                "                    FROM Joint_business_plan__r\n" +
                "                    WHERE Core_ContractPayment__c = NULL\n" +
                "                            AND Core_SupervisionDate__c <= TODAY\n" +
                "                            AND (Status__c != 'Historical' AND Status__c != 'Inactive')\n" +
                "            ), (\n" +
                "                    SELECT\n" +
                "                            Account__r.Name,\n" +
                "                            Account__r.OwnerId,\n" +
                "                            Contract_Agreement__c,\n" +
                "                            Contract_Agreement__r.CurrencyIsoCode,\n" +
                "                            Contract_Agreement__r.OwnerId,\n" +
                "                            Contract_Agreement__r.Contract_Id__c,\n" +
                "                            RecordTypeId,\n" +
                "                            RecordType.DeveloperName\n" +
                "                    FROM Contracts_Agreement_Account__r\n" +
                "            )\n" +
                "            FROM APXT_Redlining__Contract_Agreement__c\n" +
                "            WHERE Id IN :contractIds\n" +
                "                    AND APXT_Redlining__Status__c = 'Active'\n" +
                "                    AND Id IN (\n" +
                "                            SELECT Contract_Agreement__c\n" +
                "                            FROM Joint_business_plan__c\n" +
                "                            WHERE Core_ContractPayment__c = NULL\n" +
                "                                    AND Core_SupervisionDate__c <= TODAY\n" +
                "                                    AND (Status__c != 'Historical' AND Status__c != 'Inactive')\n" +
                "                    )\n" +
                "\n" +
                "    ];\n" +
                "}\n" +
                "List<APXT_Redlining__Contract_Agreement__c> scope = getContractById(contractIds);\n" +
                "System.debug(LoggingLevel.INFO, 'scope ' + JSON.serializePretty(scope));\n" +
                "Core_SupervisionService.execute(SObjectUtils.mapSObjects(scope, new Core_SupervisionTaskJob.ExcludeOutOfDateRange()));";
    }
}
