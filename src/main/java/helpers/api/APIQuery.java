package com.customertimes.api;

import com.customertimes.config.AppUser;
import com.customertimes.config.Context;
import com.customertimes.config.Dictionary;
import com.customertimes.config.Parameter;
import com.customertimes.picklist.*;
import com.sforce.soap.partner.sobject.SObject;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.customertimes.api.WSC.query;

public class APIQuery {


    public static List<String> getAccountEventIds(String accountId) {
        SObject[] records = query("SELECT Id from Event WHERE AccountId = '" + accountId + "'");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<String> getUserEventIds(String userId) {
        SObject[] records = query("SELECT Id from Event WHERE OwnerId = '" + userId + "'");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<String> getAccountVisitIds(String accountId) {
        SObject[] records = query("SELECT Id from Visit__c WHERE Customer__c = '" + accountId + "' ORDER BY CreatedDate DESC");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<String> getUserVisitIds(String userId) {
        SObject[] records = query("SELECT Id from Visit__c WHERE OwnerId = '" + userId + "' ORDER BY CreatedDate DESC");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<String> getAccountVisitContactIds(String accountId) {
        SObject[] records = query("SELECT Id FROM VisitContact__c WHERE Visit__c IN " +
                "(SELECT Id FROM Visit__c WHERE Customer__c = '" + accountId + "')");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<String> getAccountTaskIds(String accountId) {
        SObject[] records = query("SELECT Id from Task WHERE AccountId = '" + accountId + "'");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<String> getAccountOpenTaskIds(String accountId) {
        SObject[] records = query("SELECT Id from Task WHERE AccountId = '" + accountId + "' AND Status = 'Open'");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static String getAccountTaskDueDate(String accountId, String subject) {
        try {
            SObject[] query = query("SELECT ActivityDate from Task WHERE AccountId = '" + accountId + "' AND Subject = '" + subject + "'");
            return query[0].getSObjectField("ActivityDate").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getTaskNameById(String taskId) {
        try {
            SObject[] query = query("SELECT Subject from Task WHERE Id = '" + taskId + "'");
            return query[0].getSObjectField("Subject").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static List<String> getUserTaskIds(String userId) {
        SObject[] records = query("SELECT Id from Task WHERE OwnerId = '" + userId + "'");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static String getAccountId(String accountName) {
        try {
            return query("SELECT Id FROM Account WHERE Name = '" + accountName + "'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static List<String> getLinkedWholesalersIds(final String accountId) {
        try {
            final SObject[] records = query("SELECT id, name, SFDC_Partner_Id__c FROM Account_Relation__c WHERE SFDC_Id__c = '" + accountId + "' " +
                    "AND Partner_Function__c = '" + PartnerFunction.WHOLESALER.getApiName() + "'");
            return (Arrays.stream(records).map(e -> e.getSObjectField("Id").toString()).collect(Collectors.toCollection(ArrayList::new)));
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    @Nullable
    public static String getContactId(String contactName) {
        try {
            return query("SELECT Id from Contact WHERE Name = '" + contactName + "'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContactBusinessPhoneByName(String contactName) {
        try {
            return query("SELECT Id, Phone from Contact WHERE Name = '" + contactName + "'")
                    [0].getSObjectField("Phone").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContactEmailByName(String contactName) {
        try {
            return query("SELECT Id, Email from Contact WHERE Name = '" + contactName + "'")
                    [0].getSObjectField("Email").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getUserDistrict(String userName) {
        try {
            return query("SELECT District__c FROM User WHERE Username = '" + userName + "'")
                    [0].getSObjectField("District__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getAccountDistrict(String accountName) {
        try {
            return query("SELECT District__c FROM Account WHERE Name = '" + accountName + "'")
                    [0].getSObjectField("District__c").toString();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return null;
        }
    }

    public static String getAccountActivitySegment(String accountName) {
        try {
            return query("SELECT Activity_Segment__c FROM Account WHERE Name = '" + accountName + "'")
                    [0].getSObjectField("Activity_Segment__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getAccountPosSegment(String accountName) {
        try {
            return query("SELECT PoS_Segment__c FROM Account WHERE Name = '" + accountName + "'")
                    [0].getSObjectField("PoS_Segment__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getAccountQualityScore(String accountName) {
        try {
            return query("SELECT Quality_Score__c FROM Account WHERE Name = '" + accountName + "'")
                    [0].getSObjectField("Quality_Score__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static List<String> getAccountPicturesIds(String accountName) {
        SObject[] records = query("SELECT ContentDocumentId FROM ContentDocumentLink WHERE LinkedEntityId  = '" + APIQuery.getAccountId(accountName) + "'");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("ContentDocumentId").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static boolean isContactMain(String contactName) {
        return Boolean.parseBoolean(query("SELECT Main_contact__c FROM Contact WHERE Name = '" + contactName + "'")[0].getSObjectField("Main_contact__c").toString());
    }

    public static List<String> getAccountContactIds(String accountId) {
        SObject[] records = query("SELECT Id FROM Contact WHERE AccountId = '" + accountId + "'");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static String getTerritoryIdByName(String territoryName) {
        try {
            return query("SELECT Id, Name FROM Territory__c WHERE Name = '" + territoryName + "'")[0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getAccountTeamMemberId(String accountId, String userId) {
        try {
            return query("SELECT Id FROM AccountTeamMember WHERE AccountId = '" + accountId + "' AND UserId = '" + userId + "'")[0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getUserId(String name) {
        try {
            return query("SELECT Id FROM User WHERE Name = '" + name + "'")[0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getUserIdByUsername(String username) {
        try {
            return query("SELECT Id FROM User WHERE Username = '" + username + "'")[0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getTerritoryAssignmentId(String accountId, String territoryId) {
        try {
            return WSC.query("SELECT Id FROM Account_Territory_Junction__c WHERE Account_Territory_Junction__c = '" + accountId + "' AND Territory_Junction__c = '" + territoryId + "'")[0]
                    .getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getVisitStatus(String visitId) {
        try {
            return query("SELECT Status__c FROM Visit__c WHERE Id = '" + visitId + "'")[0].getSObjectField("Status__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static VisitType getVisitType(String visitId) {
        try {
            return VisitType.getByApiName(query("SELECT Type__c FROM Visit__c WHERE Id = '" + visitId + "'")[0].getSObjectField("Type__c").toString());
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getAccountLatestVisitStartTime(String accountId) {
        try {
            return query("SELECT StartDate__c FROM Visit__c WHERE Customer__c = '" + accountId + "' ORDER BY CreatedDate DESC")[0].getSObjectField("StartDate__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getAccountLatestCompletedVisitStartTime(String accountId) {
        try {
            return query("SELECT StartDate__c FROM Visit__c WHERE Customer__c = '" + accountId + "' AND Status__c = 'Complete' ORDER BY End_visit__c DESC")[0].getSObjectField("StartDate__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getAccountLatestVisitNote(String accountId) {
        try {
            Object note = query("SELECT Note__c FROM Visit__c WHERE Customer__c = '" + accountId + "' AND Status__c = 'Complete' ORDER BY End_visit__c DESC")[0].getSObjectField("Note__c");
            return note != null ? note.toString() : "";
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getVisitOwnerId(String visitId) {
        try {
            return query("SELECT OwnerId FROM Visit__c WHERE Id = '" + visitId + "'")[0].getSObjectField("OwnerId").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getConceptsRecordTypeId(ConceptsRecordTypeDeveloperName conceptsRecordTypeDeveloperName) {
        try {
            return query("SELECT Id FROM RecordType WHERE DeveloperName = '" + conceptsRecordTypeDeveloperName.getApiName() + "' AND SobjectType = 'Concepts__c'")[0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getConceptsBrand(String conceptsId) {
        try {
            return query("SELECT Brand__c FROM Concepts__c WHERE Id = '" + conceptsId + "'")[0].getSObjectField("Brand__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getConceptsMarket(String conceptsId) {
        try {
            return query("SELECT Market__c FROM Concepts__c WHERE Id = '" + conceptsId + "'")[0].getSObjectField("Market__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static List<String> getConceptAliasesList(String recordType, String marketCode, String brandCode) {
        SObject[] records = query("SELECT Concept_Alias__c FROM Concepts__c WHERE Brand__c = '" + brandCode + "' AND Market__c = '" + marketCode + "' AND RecordTypeId = '" + recordType + "' AND isActive__c = true");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Concept_Alias__c").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static String getAccountLatestSurveyScore(String accountName) {
        try {
            return query("SELECT Survey_Score__c FROM Survey_Summary__c WHERE Account_Name__c LIKE '%" + accountName + "%' ORDER BY LastModifiedDate DESC")[0].getSObjectField("Survey_Score__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getVisitLatestSurveyScore(String visitId, String surveySummaryRecordTypeId) {
        try {
            return query("SELECT Survey_Score__c FROM Survey_Summary__c WHERE Visit__c = '" + visitId + "' AND RecordTypeId = '" + surveySummaryRecordTypeId + "'")[0].getSObjectField("Survey_Score__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getSurveySummaryRecordTypeId(SurveySummaryRecordTypeDeveloperName surveySummaryTypeName) {
        try {
            return query("SELECT Id FROM RecordType WHERE DeveloperName = '" + surveySummaryTypeName.getApiName() + "' AND SobjectType = 'Survey_Summary__c'")[0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getAccountLastSurveySummaryId(String accountId, String surveySummaryRecordTypeId) {
        try {
            return query("SELECT Id FROM Survey_Summary__c WHERE Customer__c = '" + accountId + "' AND RecordTypeId = '" + surveySummaryRecordTypeId + "' ORDER BY LastModifiedDate DESC")[0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getVisitIdBySurveySummaryId(String surveySummaryId) {
        try {
            return query("SELECT Visit__c FROM Survey_Summary__c WHERE Id = '" + surveySummaryId + "'")[0].getSObjectField("Visit__c").toString();
        } catch (ArrayIndexOutOfBoundsException | IllegalStateException e) {
            return null;
        }
    }

    public static List<String> getAccountSurveySummaryIds(String accountId) {
        SObject[] records = query("SELECT Id FROM Survey_Summary__c WHERE Customer__c = '" + accountId + "'");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<String> getAccountObjectiveIds(String accountId) {
        SObject[] records = query("SELECT Id FROM Objective__c WHERE Customer__c = '" + accountId + "'");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<String> getLinkedAccountIds(final String accountId) {
        try {
            final SObject[] records = query("SELECT id, SFDC_Partner_Id__c FROM Account_Relation__c WHERE SFDC_Id__c = '" + accountId + "' " +
                    "AND Partner_Function__c = '" + PartnerFunction.ACCOUNT.getApiName() + "'");
            return (Arrays.stream(records).map(e -> e.getSObjectField("Id").toString()).collect(Collectors.toCollection(ArrayList::new)));
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContactRecordTypeId() {
        try {
            switch (Parameter.RUN_MARKET.get()) {
                case "FR":
                    return query("SELECT Id FROM RecordType WHERE Name = 'France' AND SobjectType = 'Contact'")[0].getSObjectField("Id").toString();
                case "PT":
                    return query("SELECT Id FROM RecordType WHERE Name = 'Portugal' AND SobjectType = 'Contact'")[0].getSObjectField("Id").toString();
                case "AU":
                    return query("SELECT Id FROM RecordType WHERE Name = 'Austria' AND SobjectType = 'Contact'")[0].getSObjectField("Id").toString();
                case "SW":
                    return query("SELECT Id FROM RecordType WHERE Name = 'Switzerland' AND SobjectType = 'Contact'")[0].getSObjectField("Id").toString();
                default:
                    throw new IllegalStateException("No implementation for " + Parameter.RUN_MARKET.get());
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getAccountCity(String accountName) {
        try {
            return query("SELECT ShippingCity FROM Account WHERE Name = '" + accountName + "'")
                    [0].getSObjectField("ShippingCity").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getAccountCountry(String accountName) {
        try {
            return query("SELECT Country__c FROM Account WHERE Name = '" + accountName + "'")
                    [0].getSObjectField("Country__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getAccountPostalCode(String accountName) {
        try {
            return query("SELECT ShippingPostalCode FROM Account WHERE Name = '" + accountName + "'")
                    [0].getSObjectField("ShippingPostalCode").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getAccountStreet(String accountName) {
        try {
            return query("SELECT ShippingStreet FROM Account WHERE Name = '" + accountName + "'")
                    [0].getSObjectField("ShippingStreet").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static List<String> getContactJobHistoryRecordsByContactId(String contactId) {
        try {
            final SObject[] records = query("SELECT Id FROM AccountContactRelation__c WHERE Contact__c = '" + contactId + "'");
            return (Arrays.stream(records).map(e -> e.getSObjectField("Id").toString()).collect(Collectors.toCollection(ArrayList::new)));
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContactJobHistoryRecordRoleByAccountIdAndContactId(String accountId, String contactId) {
        try {
            return query("SELECT Function__c FROM AccountContactRelation__c WHERE Account__c = '" + accountId + "' AND Contact__c = '" + contactId + "'")
                    [0].getSObjectField("Function__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContactJobHistoryRecordStartDateByAccountIdAndContactId(String accountId, String contactId) {
        try {
            return query("SELECT StartDate__c FROM AccountContactRelation__c WHERE Account__c = '" + accountId + "' AND Contact__c = '" + contactId + "'")
                    [0].getSObjectField("StartDate__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContactJobHistoryRecordEndDateByAccountIdAndContactId(String accountId, String contactId) {
        try {
            return query("SELECT EndDate__c FROM AccountContactRelation__c WHERE Account__c = '" + accountId + "' AND Contact__c = '" + contactId + "'")
                    [0].getSObjectField("EndDate__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getTerritoryAssignmentRuleByTerritoryName(String territoryName) {
        try {
            return query("SELECT assignment_Rules__c FROM Territory__c WHERE Name = '" + territoryName + "'")
                    [0].getSObjectField("assignment_Rules__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getLatestTerritoryHierarchyCreationApexJob() {
        return query("SELECT Id FROM AsyncApexJob WHERE " +
                "JobType = 'BatchApex' AND " +
                "ApexClassId = '" + getTerritoryHierarchyCreationApexClassId() + "' AND " +
                "CreatedById = '" + APIQuery.getUserIdByUsername(AppUser.ADMIN.getUsername()) + "' " +
                "ORDER BY CreatedDate DESC " +
                "LIMIT 1")[0].getSObjectField("Id").toString();
    }

    public static String getLatestMarketingSupportTaskCreationApexJob() {
        return query("SELECT Id FROM AsyncApexJob WHERE " +
                "JobType = 'BatchApex' AND " +
                "ApexClassId = '" + getMarketingSupportTaskApexClassId() + "' AND " +
                "CreatedById = '" + APIQuery.getUserIdByUsername(AppUser.ADMIN.getUsername()) + "' " +
                "ORDER BY CreatedDate DESC " +
                "LIMIT 1")[0].getSObjectField("Id").toString();
    }

    public static String getLatestSupervisionTaskCreationApexJob() {
        return query("SELECT Id FROM AsyncApexJob WHERE " +
                "JobType = 'BatchApex' AND " +
                "ApexClassId = '" + APIQuery.getSupervisionTaskApexClassId() + "' AND " +
                "CreatedById = '" + APIQuery.getUserIdByUsername(AppUser.ADMIN.getUsername()) + "' " +
                "ORDER BY CreatedDate DESC " +
                "LIMIT 1")[0].getSObjectField("Id").toString();
    }

    public static String getTerritoryHierarchyCreationApexClassId() {
        try {
            return query("SELECT Id FROM ApexClass WHERE Name = 'TerritoryHierarchyCreation_Batch'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getMarketingSupportTaskApexClassId() {
        try {
            return query("SELECT Id FROM ApexClass WHERE Name = 'Core_MarketingSupportTaskJob'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getSupervisionTaskApexClassId() {
        try {
            return query("SELECT Id FROM ApexClass WHERE Name = 'Core_SupervisionTaskJob'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getAccountSharingRecalculationApexClassId() {
        try {
            return query("SELECT Id FROM ApexClass WHERE Name = 'AccountSharingRecalculation_Batch'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getLatestAccountRecalculationApexJob() {
        return query("SELECT Id FROM AsyncApexJob WHERE " +
                "JobType = 'BatchApex' AND " +
                "ApexClassId = '" + getAccountSharingRecalculationApexClassId() + "' " +
                "ORDER BY CreatedDate DESC " +
                "LIMIT 1")[0].getSObjectField("Id").toString();
    }

    public static String getApexJobStatus(String jobId) {
        try {
            return query("SELECT Status FROM AsyncApexJob WHERE Id = '" + jobId + "'")
                    [0].getSObjectField("Status").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static List<String> getUserEventIdsByEventSubject(String subject) {
        SObject[] records = query("SELECT Id from Event WHERE Subject = '" + subject + "'");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static boolean isCurrentEnvSandbox() {
        try {
            return Boolean.parseBoolean(query("select Id, IsSandbox from Organization")
                    [0].getSObjectField("IsSandbox").toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalStateException("Cannot check if current environment is a sandbox");
        }
    }

    public static String getRecordTypeIdByAccountType(String accountType) {
        return query("SELECT RecordTypeId FROM Account WHERE Account_Type__c='" + accountType + "'")[0].getSObjectField("RecordTypeId").toString();
    }

    public static int getAccountQuantityByStatusTypeVisitStatusFilters(final String accountStatus, final String recordTypeId, final String visitStatus) {
        final SObject[] records = query("SELECT Id FROM AccountShare WHERE " +
                "Account.RecordType.Id = '" + recordTypeId + "' AND " +
                "Account.Account_Status__c = '" + accountStatus + "' AND " +
                "Account.Visit_Status__c = '" + visitStatus + "' AND " +
                "UserOrGroupId= '" + APIQuery.getUserIdByUsername(AppUser.getCurrentUser().getUsername()) + "' AND " +
                "AccountAccessLevel='Edit'");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new)).size();
    }

    public static int getQuantityOfAllAccountType() {
        final SObject[] records = query("SELECT Id FROM AccountShare WHERE (" +
                "Account.RecordType.Id='" + APIQuery.getRecordTypeIdByAccountType(AccountType.CUSTOMER.getApiName()) + "' OR " +
                "Account.RecordType.Id='" + APIQuery.getRecordTypeIdByAccountType(AccountType.PROSPECT.getApiName()) + "' OR " +
                "Account.RecordType.Id='" + APIQuery.getRecordTypeIdByAccountType(AccountType.WHOLESALER.getApiName()) + "' OR " +
                "Account.RecordType.Id='" + APIQuery.getRecordTypeIdByAccountType(AccountType.HEADOFFICE.getApiName()) + "') AND " +
                "UserOrGroupId='" + APIQuery.getUserIdByUsername(AppUser.getCurrentUser().getUsername()) + "' AND " +
                "AccountAccessLevel='Edit'");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new)).size();
    }

    public static String getLatestAccountNoteByAccountId(String accountId) {
        try {
            return query("SELECT Content_Note__c FROM Custom_Notes__c WHERE AccountId__c = '" + accountId + "' ORDER BY LastModifiedDate DESC")
                    [0].getSObjectField("Content_Note__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static List<String> getRelatedApprovalUserHierarchyList(String username) {
        SObject[] records = query("SELECT Core_ApprovalUserHierarchy__c FROM Core_UsersRelatedToApprovalUserHierarchy__c " +
                "WHERE Core_ApprovalUserHierarchy__r.Core_Market__c = '" + CountryCode.getByValue(Context.RUN_MARKET.name()).getApiName()
                + "' and Core_User__r.Username = '" + username + "'");
        return Arrays.stream(records)
                .map(e -> e.getSObjectField("Core_ApprovalUserHierarchy__c").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<String> getHierarchyUserListEntireAllApprovalHierarchyWhereUserRelated(String username) {
        SObject[] records = query("SELECT Id FROM Core_HierarchyUser__c WHERE Core_HierarchyUser__c IN " +
                "(SELECT Core_ApprovalUserHierarchy__c FROM Core_UsersRelatedToApprovalUserHierarchy__c " +
                "WHERE Core_ApprovalUserHierarchy__r.Core_Market__c = '" + CountryCode.getByValue(Context.RUN_MARKET.name()).getApiName() + "'" +
                " and Core_User__r.Username = '" + username + "')");
        return Arrays.stream(records)
                .map(e -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<String> getHierarchyUsersByContactId(String contactId) {
        SObject[] records = query("SELECT Id FROM Core_HierarchyUser__c WHERE Core_Contact__c = '" + contactId + "'");
        return Arrays.stream(records)
                .map(e -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<String> getContractAgreementIDsByAccountId(String accountId) {
        SObject[] records = query("SELECT Id FROM APXT_Redlining__Contract_Agreement__c WHERE APXT_Redlining__Account__c = '" + accountId + "'");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static String getContractManagementIdByContractId(String contractId) {
        try {
            return query("SELECT Contract_management__c FROM APXT_Redlining__Contract_Agreement__c WHERE Contract_Id__c = '" + contractId + "'")
                    [0].getSObjectField("Contract_management__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractAgreementIdByProposalName(String proposalName) {
        try {
            return query("SELECT Id FROM APXT_Redlining__Contract_Agreement__c WHERE Proposal_Name__c = '" + proposalName + "'")[0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractAgreementStatusByContractAgreementId(String contractAgreementId) {
        try {
            return query("SELECT APXT_Redlining__Status__c FROM APXT_Redlining__Contract_Agreement__c WHERE Id = '" + contractAgreementId + "'")[0].getSObjectField("APXT_Redlining__Status__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractIdByContractAgreementId(String contractAgreementId) {
        try {
            return query("SELECT Contract_Id__c FROM APXT_Redlining__Contract_Agreement__c WHERE Id = '" + contractAgreementId + "'")[0].getSObjectField("Contract_Id__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getNameFromUsername(String userName) {
        try {
            return query("SELECT Name FROM User WHERE Username = '" + userName + "'")
                    [0].getSObjectField("Name").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractNameByProposalName(String proposalName) {
        try {
            return query("SELECT Name FROM APXT_Redlining__Contract_Agreement__c WHERE Proposal_Name__c = '" + proposalName + "'")
                    [0].getSObjectField("Name").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getTaskIdByUserIdAndWhatId(String userId, String whatId) {
        try {
            return query("SELECT Id FROM Task WHERE OwnerId = '" + userId + "' AND WhatId = '" + whatId + "'")[0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getFirstNameByUsername(String userName) {
        try {
            return query("SELECT FirstName FROM User WHERE Username = '" + userName + "'")
                    [0].getSObjectField("FirstName").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getProposalDocumentLinkByProposalName(String proposalName) {
        try {
            return query("SELECT Proposal_file_link__c FROM APXT_Redlining__Contract_Agreement__c WHERE Proposal_Name__c = '" + proposalName + "'")
                    [0].getSObjectField("Proposal_file_link__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractDocumentLinkByProposalName(String proposalName) {
        try {
            return query("SELECT Contract_file_link__c FROM APXT_Redlining__Contract_Agreement__c WHERE Proposal_Name__c = '" + proposalName + "'")
                    [0].getSObjectField("Contract_file_link__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractAgreementRecordTypeIdByTemplateName(String templateName) {
        try {
            return query("SELECT Id FROM RecordType WHERE Name = '" + templateName + "' AND SobjectType = 'APXT_Redlining__Contract_Agreement__c'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractManagementIdByName(String name) {
        try {
            return query("SELECT Id FROM Contract_management__c WHERE Name = '" + name + "'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getMarketByAppUser(AppUser appUser) {
        try {
            return query("SELECT Market__c FROM User WHERE Username = '" + appUser.getUsername() + "'")
                    [0].getSObjectField("Market__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractAgreementAccountRecordTypeIdByName(String name) {
        try {
            return query("SELECT Id FROM RecordType WHERE Name = '" + name + "' AND SobjectType = 'Contract_Agreement_Account__c'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getCustomerCommitmentConceptRecordTypeIdByName(String name) {
        try {
            return query("SELECT Id FROM RecordType WHERE Name = '" + name + "' AND SobjectType = 'Customer_Commitment_Concept__c'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractAgreementAccountByIds(String contractAgreementId, String accountId) {
        try {
            return query("SELECT Id FROM Contract_Agreement_Account__c WHERE Contract_Agreement__c = '" + contractAgreementId + "' AND Account__c = '" + accountId + "'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getBacardiSupportIdByName(String name) {
        try {
            return query("SELECT Id FROM Bacardi_support__c WHERE Name = '" + name + "'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static Boolean getImpactProfitabilityFlagByMarketingSupportName(String name) {
        try {
            return Boolean.parseBoolean(query("SELECT Impact_overall_profitability__c FROM Bacardi_support__c WHERE Name = '" + name + "'")
                    [0].getSObjectField("Impact_overall_profitability__c").toString());
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getBacardiSupportDescriptionByName(String name) {
        try {
            return query("SELECT Description__c FROM Bacardi_support__c WHERE Name = '" + name + "'")
                    [0].getSObjectField("Description__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static Double getFinancialSupportValueById(String financialSupportId) {
        try {
            return Double.parseDouble(query("SELECT Value__c FROM Contract_financial_support__c WHERE Id = '" + financialSupportId + "'")
                    [0].getSObjectField("Value__c").toString());
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractAgreementRebateNumberByContractAgreementId(String contractAgreementId) {
        try {
            return query("SELECT Name FROM Rebate_Agreement__c WHERE Contract_agreement__c = '" + contractAgreementId + "'")
                    [0].getSObjectField("Name").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractManagementRebateNumberByContractManagementTemplateName(String contractTemplateName) {
        try {
            return query("SELECT Core_RebateIDFixedRebate__c FROM Contract_management__c WHERE Name = '" + contractTemplateName + "'")
                    [0].getSObjectField("Core_RebateIDFixedRebate__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getRebateAgreementStatusByContractAgreementId(String contractAgreementId) {
        try {
            return query("SELECT Rebate_Agreement_Status__c FROM APXT_Redlining__Contract_Agreement__c WHERE Id = '" + contractAgreementId + "'")
                    [0].getSObjectField("Rebate_Agreement_Status__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getDealHistoryRecordIdByAction(String contractAgreementId, String dealHistoryAction) {
        try {
            return query("SELECT Id FROM Deal_history__c WHERE Contract_Agreement__c = '" + contractAgreementId + "' AND Action__c = '" + dealHistoryAction + "'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getDealHistoryNoteById(String dealHistoryId) {
        try {
            return query("SELECT Note__c FROM Deal_history__c WHERE Id = '" + dealHistoryId + "'")
                    [0].getSObjectField("Note__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getDealHistoryAuthorById(String dealHistoryId) {
        try {
            return query("SELECT Author__c FROM Deal_history__c WHERE Id = '" + dealHistoryId + "'")
                    [0].getSObjectField("Author__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getDealHistoryDateById(String dealHistoryId) {
        try {
            return query("SELECT Date__c FROM Deal_history__c WHERE Id = '" + dealHistoryId + "'")
                    [0].getSObjectField("Date__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static Map<String, String> getCongaSignRecipientWithStatusMapByContractAgreementId(String contractAgreementId) {
        SObject[] records = query("SELECT APXT_CongaSign__Email__c, APXT_CongaSign__Status__c " +
                "FROM APXT_CongaSign__Recipient__c " +
                "WHERE APXT_CongaSign__Transaction__c " +
                "IN (SELECT Id FROM APXT_CongaSign__Transaction__c WHERE Core_ContractAgreementId__c = '" + contractAgreementId + "')");
        return Arrays.stream(records)
                .collect(Collectors.toMap(
                        e -> e.getSObjectField("APXT_CongaSign__Email__c").toString(),
                        e -> e.getSObjectField("APXT_CongaSign__Status__c").toString()));
    }

    public static String getContractFileNameByContentVersionId(String id) {
        try {
            return query("SELECT PathOnClient FROM ContentVersion WHERE Id = '" + id + "'")
                    [0].getSObjectField("PathOnClient").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getBacardiSupportIdByItsName(String supportName) {
        try {
            return query("SELECT Id FROM Bacardi_support__c WHERE Name = '" + supportName + "'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static List<String> getListOfSuppliersByBacardiSupportId(String id) {
        SObject[] records = query("SELECT Name FROM Account WHERE Id IN (" +
                "SELECT Core_Account__c FROM Core_BacardiSupportSupplier__c WHERE Core_BacardiSupport__c = '" + id + "')");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Name").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static String getPremiumProductForContractManagementAndMarket(String recordType, String marketCode, String contractManagement) {
        try {
            return query("SELECT Concept_Alias__c FROM Concepts__c WHERE Premium__c = true and isActive__c = true"
                    + " and RecordTypeId = '" + recordType + "' and Market__c = '" + marketCode + "'"
                    + " and Id IN (SELECT Concept__c FROM Contract_management_concept__c WHERE Contract_management__c = '" + contractManagement + "')"
                    + " ORDER BY Brand_Description__c")
                    [0].getSObjectField("Concept_Alias__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getPremiumProductIdForContractManagementAndMarket(String recordType, String marketCode, String contractManagement) {
        try {
            return query("SELECT Id FROM Concepts__c WHERE Premium__c = true and isActive__c = true"
                    + " and RecordTypeId = '" + recordType + "' and Market__c = '" + marketCode + "'"
                    + " and Id IN (SELECT Concept__c FROM Contract_management_concept__c WHERE Contract_management__c = '" + contractManagement + "')"
                    + " ORDER BY Brand_Description__c")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getConceptNameByItsId(String conceptId) {
        try {
            return query("SELECT Concept_Alias__c FROM Concepts__c WHERE Id = '" + conceptId + "'")
                    [0].getSObjectField("Concept_Alias__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getConceptMaxDiscountByItsId(String conceptId) {
        try {
            return query("SELECT Max_Discount__c FROM Concepts__c WHERE Id = '" + conceptId + "'")
                    [0].getSObjectField("Max_Discount__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getConceptCogsByItsId(String conceptId) {
        try {
            return query("SELECT COGs__c FROM Concepts__c WHERE Id = '" + conceptId + "'")
                    [0].getSObjectField("COGs__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getCurrencyIsoCodeByContractManagementId(String contractManagementId) {
        try {
            return query("SELECT CurrencyIsoCode FROM Contract_management__c WHERE Id = '" + contractManagementId + "'")
                    [0].getSObjectField("CurrencyIsoCode").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractAgreementAppLocalIdByItsId(String contractAgreementId) {
        try {
            return query("SELECT App_Local_Id__c FROM APXT_Redlining__Contract_Agreement__c WHERE Id = '" + contractAgreementId + "'")
                    [0].getSObjectField("App_Local_Id__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getMarketingSupportAppLocalIdByItsId(String marketingSupportId) {
        try {
            return query("SELECT App_Local_Id__c FROM Contract_marketing_support__c WHERE Id = '" + marketingSupportId + "'")
                    [0].getSObjectField("App_Local_Id__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getFinancialSupportAppLocalIdByItsId(String financialSupportId) {
        try {
            return query("SELECT App_Local_Id__c FROM Contract_financial_support__c WHERE Id = '" + financialSupportId + "'")
                    [0].getSObjectField("App_Local_Id__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getFirstSupplierByBacardiSupportId(String bacardiSupportId) {
        try {
            return query("SELECT Id FROM Account WHERE Id IN (SELECT Core_Account__c FROM Core_BacardiSupportSupplier__c WHERE Core_BacardiSupport__c = '" + bacardiSupportId + "') ORDER By Name")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static List<String> getAccountVendorsRelationIdsByAccountId(final String accountId) {
        try {
            final SObject[] records = query("SELECT Id FROM Account_Relation__c WHERE SFDC_Id__c = '" + accountId + "' " +
                    "AND Partner_Function__c = '" + PartnerFunction.VENDOR.getApiName() + "'");
            return (Arrays.stream(records).map(e -> e.getSObjectField("Id").toString()).collect(Collectors.toCollection(ArrayList::new)));
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static List<String> getAccountVendorsIdsByAccountId(final String accountId) {
        try {
            final SObject[] records = query("SELECT SFDC_Partner_Id__c FROM Account_Relation__c WHERE SFDC_Id__c = '" + accountId + "' " +
                    "AND Partner_Function__c = '" + PartnerFunction.VENDOR.getApiName() + "'");
            return (Arrays.stream(records).map(e -> e.getSObjectField("SFDC_Partner_Id__c").toString()).collect(Collectors.toCollection(ArrayList::new)));
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static List<String> getContractAgreementVendorIdsByAccountId(final String accountId) {
        try {
            final SObject[] records = query("SELECT Vendor__c FROM APXT_Redlining__Contract_Agreement__c WHERE APXT_Redlining__Account__c = '" + accountId + "'");
            return (Arrays.stream(records).map(e -> e.getSObjectField("Vendor__c").toString()).collect(Collectors.toCollection(ArrayList::new)));
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getVendorRecordTypeId() {
        try {
            switch (Parameter.RUN_MARKET.get()) {
                case "FR":
                    return query("SELECT Id FROM RecordType WHERE Name = 'Vendor - France' AND SobjectType = 'Account'")[0].getSObjectField("Id").toString();
                case "PT":
                    return query("SELECT Id FROM RecordType WHERE Name = 'Vendor - Portugal' AND SobjectType = 'Account'")[0].getSObjectField("Id").toString();
                case "AU":
                    return query("SELECT Id FROM RecordType WHERE Name = 'Vendor - Austria' AND SobjectType = 'Account'")[0].getSObjectField("Id").toString();
                case "SW":
                    return query("SELECT Id FROM RecordType WHERE Name = 'Vendor - Switzerland' AND SobjectType = 'Account'")[0].getSObjectField("Id").toString();
                default:
                    throw new IllegalStateException("No implementation for " + Parameter.RUN_MARKET.get());
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractAgreementAccountIdByContractAgreementId(String contractAgreementId) {
        try {
            return query("SELECT Id FROM Contract_Agreement_Account__c WHERE Contract_Agreement__c = '" + contractAgreementId + "'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getCustomerCommitmentConceptIdByContractAgreementAccountIdAndConceptName(String contractAgreementAccountId, String conceptName) {
        try {
            return query("SELECT Id FROM Customer_Commitment_Concept__c WHERE Contract_Agreement_Account__c = '" + contractAgreementAccountId + "' and Concept_Name__c LIKE '%" + conceptName + "%'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getCustomerCommitmentConceptCommentByContractAgreementAccountIdAndConceptName(String contractAgreementAccountId, String conceptName) {
        try {
            return query("SELECT Comment__c FROM Customer_Commitment_Concept__c WHERE Contract_Agreement_Account__c = '" + contractAgreementAccountId + "' and Concept_Name__c LIKE '%" + conceptName + "%'")
                    [0].getSObjectField("Comment__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getCustomerCommitmentConceptVisibilityByContractAgreementAccountIdAndConceptName(String contractAgreementAccountId, String conceptName) {
        try {
            return query("SELECT Concept_visibility__c FROM Customer_Commitment_Concept__c WHERE Contract_Agreement_Account__c = '" + contractAgreementAccountId + "' and Concept_Name__c LIKE '%" + conceptName + "%'")
                    [0].getSObjectField("Concept_visibility__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getConceptNameByAlias(String conceptName) {
        try {
            return query("SELECT Name FROM Concepts__c WHERE Concept_Alias__c = '" + conceptName + "'")
                    [0].getSObjectField("Name").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractActivationDateByItsTitleAndAgreementId(String contractActivationTitle, String contractAgreementId) {
        try {
            return query("SELECT Date__c FROM Joint_business_plan__c WHERE Title__c = '" + contractActivationTitle +
                    "' and Contract_Agreement__c ='" + contractAgreementId + "'")
                    [0].getSObjectField("Date__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractActivationDescriptionByItsTitleAndAgreementId(String contractActivationTitle, String contractAgreementId) {
        try {
            return query("SELECT Description__c FROM Contract_activation__c WHERE Id IN " +
                    "(SELECT Contract_activation__c FROM Joint_business_plan__c WHERE Title__c = '" + contractActivationTitle +
                    "' and Contract_Agreement__c = '" + contractAgreementId + "')")
                    [0].getSObjectField("Description__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractJointBusinessPlanIdByTypeAndAgreementId(JointBusinessPlanType jbpType, String contractAgreementId) {
        try {
            return query("SELECT Id FROM Joint_business_plan__c WHERE Core_JBPType__c = '" + jbpType.getApiName() +
                    "' and Contract_Agreement__c ='" + contractAgreementId + "'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractJointBusinessPlanIdByTypeAndAgreementIdAmountAndDate(
            JointBusinessPlanType jointBusinessPlanType, String contractAgreementId, String amount, String date) {
        try {
            return query("SELECT Id FROM Joint_business_plan__c WHERE Core_JBPType__c = '" + jointBusinessPlanType.getApiName() +
                    "' and Contract_Agreement__c ='" + contractAgreementId + "' and Amount__c = " + amount + " and Date__c = " + date + "")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getDefaultValueByMarketingSupportName(String name) {
        try {
            return query("SELECT Default_value__c FROM Bacardi_support__c WHERE Name = '" + name + "'")
                    [0].getSObjectField("Default_value__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getMaxValueAllowedByMarketingSupportName(String name) {
        try {
            return query("SELECT Maximum_value_allowed__c FROM Bacardi_support__c WHERE Name = '" + name + "'")
                    [0].getSObjectField("Maximum_value_allowed__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getMarketingSupportSupplierIdByItsId(String marketingSupportId) {
        try {
            return query("SELECT Core_Supplier__c FROM Contract_marketing_support__c WHERE Id = '" + marketingSupportId + "'")
                    [0].getSObjectField("Core_Supplier__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getAccountNameByItsId(String accountId) {
        try {
            return query("SELECT Name FROM Account WHERE Id = '" + accountId + "'")
                    [0].getSObjectField("Name").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static List<String> getContractPaymentIdsByAgreementId(String agreementId) {
        SObject[] records = query("SELECT Id FROM Core_ContractPayment__c WHERE Core_ContractAgreement__c = '" + agreementId + "' ORDER BY SystemModstamp ASC");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<String> getContentDocumentIdsByLinkedEntityId(String linkedEntityId) {
        SObject[] records = query("SELECT ContentDocumentId FROM ContentDocumentLink WHERE LinkedEntityId = '" + linkedEntityId + "'");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("ContentDocumentId").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static String getLatestContentDocumentIdByLinkedEntityId(String linkedEntityId) {
        try {
            return query("SELECT ContentDocumentId FROM ContentDocumentLink WHERE LinkedEntityId = '" + linkedEntityId + "' ORDER BY SystemModstamp DESC")
                    [0].getSObjectField("ContentDocumentId").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContentVersionImageTypeForLatestFileByContentDocumentId(String contentDocumentId) {
        try {
            return query("SELECT Image_type__c FROM ContentVersion WHERE ContentDocumentId = '" + contentDocumentId + "' ORDER BY LastModifiedDate DESC")
                    [0].getSObjectField("Image_type__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContentDocumentIdByContentVersionId(String contentVersionId) {
        try {
            return query("SELECT ContentDocumentId FROM ContentVersion WHERE Id = '" + contentVersionId + "'")
                    [0].getSObjectField("ContentDocumentId").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getJointBusinessPlanStatusByItsId(String jointBusinessPlanId) {
        try {
            return query("SELECT Status__c FROM Joint_business_plan__c WHERE Id = '" + jointBusinessPlanId + "'")
                    [0].getSObjectField("Status__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getJointBusinessPlanOriginalDateByItsId(String jointBusinessPlanId) {
        try {
            return query("SELECT Core_OriginalDate__c FROM Joint_business_plan__c WHERE Id = '" + jointBusinessPlanId + "'")
                    [0].getSObjectField("Core_OriginalDate__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getJointBusinessPlanDateByItsId(String jointBusinessPlanId) {
        try {
            return query("SELECT Date__c FROM Joint_business_plan__c WHERE Id = '" + jointBusinessPlanId + "'")
                    [0].getSObjectField("Date__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getTaskStatusByJointBusinessPlanId(String jointBusinessPlanId) {
        try {
            SObject[] query = query("SELECT Status from Task WHERE WhatId = '" + jointBusinessPlanId + "'");
            return query[0].getSObjectField("Status").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static List<String> getConceptVolumeIdsByContractAgreementId(String contractAgreementId) {
        SObject[] records = query("SELECT Id FROM Core_ConceptVolume__c WHERE Core_SupervisionDetail__r." +
                "Core_Supervision__r." +
                "Core_ContractAgreementAccount__r." +
                "Contract_Agreement__c  = '" + contractAgreementId + "'");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static String getContractPaymentIdByContractAgreementId(String contractAgreementId) {
        try {
            return query("SELECT id FROM Core_ContractPayment__c WHERE Core_ContractAgreement__c = '" + contractAgreementId + "'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractPaymentPlannedValueByPaymentId(String contractPaymentId) {
        try {
            return query("SELECT Core_PlannedPayment__c FROM Core_ContractPayment__c WHERE id = '" + contractPaymentId + "'")
                    [0].getSObjectField("Core_PlannedPayment__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractPaymentNameByPaymentId(String contractPaymentId) {
        try {
            return query("SELECT Name FROM Core_ContractPayment__c WHERE id = '" + contractPaymentId + "'")
                    [0].getSObjectField("Name").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractPaymentApprovalFileNameByContractAgreementId(String contractAgreementId) {
        try {
            Object contentDocumentSObject = WSC.query("SELECT ContentDocument.Title FROM ContentDocumentLink WHERE LinkedEntityId IN " +
                    "(SELECT id FROM Core_ContractPayment__c WHERE Core_ContractAgreement__c = '" + contractAgreementId + "') " +
                    "ORDER BY SystemModstamp DESC")
                    [0].getSObjectField("ContentDocument");
            return ((SObject) contentDocumentSObject).getSObjectField("Title").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractPaymentStatusOfApprovalFieldByContractPaymentId(String contractPaymentId) {
        try {
            return query("SELECT Core_StatusOfApproval__c FROM Core_ContractPayment__c WHERE id = '" + contractPaymentId + "'")
                    [0].getSObjectField("Core_StatusOfApproval__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static Boolean getContractPaymentAutoApprovalCheckboxByContractPaymentId(String contractPaymentId) {
        try {
            return Boolean.parseBoolean(query("SELECT Core_AutoApproval__c FROM Core_ContractPayment__c WHERE id = '" + contractPaymentId + "'")
                    [0].getSObjectField("Core_AutoApproval__c").toString());
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static List<String> getNotificationsByUserId(String userId) {
        SObject[] records = query("SELECT id From Notification__c WHERE OwnerId = '" + userId + "'");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static String getContractPaymentStatusFieldByContractPaymentId(String contractPaymentId) {
        try {
            return query("SELECT Core_Status__c FROM Core_ContractPayment__c WHERE id = '" + contractPaymentId + "'")
                    [0].getSObjectField("Core_Status__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractPaymentNameByAgreementId(String agreementId) {
        try {
            return query("SELECT Name FROM Core_ContractPayment__c WHERE Core_ContractAgreement__c = '" + agreementId + "'")
                    [0].getSObjectField("Name").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractAgreementApprovalStatusByAgreementId(String agreementId) {
        try {
            return query("SELECT Core_Status__c FROM Core_ContractAgreementApproval__c WHERE Core_ContractAgreement__c = '" + agreementId + "'")
                    [0].getSObjectField("Core_Status__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractAgreementApproverStatusByAgreementId(String agreementId) {
        try {
            return query("SELECT Core_Status__c FROM Core_ContractAgreementApprovers__c WHERE Core_ContractAgreementApproval__r.Core_ContractAgreement__c = '" + agreementId + "'")
                    [0].getSObjectField("Core_Status__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getInvoiceIdByContractPaymentId(String contractPaymentId) {
        try {
            return query("SELECT Id FROM Invoice__c WHERE Core_ContractPayment__c = '" + contractPaymentId + "'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static List<String> getInvoiceIdsByAgreementIdForContractAgreementAccount(String agreementId, String contractAgreementAccountId) {
        // TODO Refactor this method to use an appropriate filtering logic
        SObject[] records = query("SELECT Id FROM Invoice__c WHERE Core_ContractAgreementAccount__c = '" + contractAgreementAccountId + "' and Core_ContractPayment__c IN (SELECT Id FROM Core_ContractPayment__c WHERE Core_ContractAgreement__c ='" + agreementId + "')");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static String getInvoiceTotalAmountByInvoiceId(String invoiceId) {
        try {
            return query("SELECT Amount_Total_excl_Taxes__c FROM Invoice__c WHERE Id = '" + invoiceId + "'")
                    [0].getSObjectField("Amount_Total_excl_Taxes__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getInvoiceStatusByInvoiceId(String invoiceId) {
        try {
            return query("SELECT Status__c FROM Invoice__c WHERE id ='" + invoiceId + "'")
                    [0].getSObjectField("Status__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getUserEmailByUserName(String userName) {
        try {
            return query("SELECT Email FROM User WHERE Username = '" + userName + "'")
                    [0].getSObjectField("Email").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getTaskStatusByUserIdAndWhatId(String userId, String whatId) {
        try {
            SObject[] query = query("SELECT Status from Task WHERE OwnerId = '" + userId + "' And WhatId = '" + whatId + "'");
            return query[0].getSObjectField("Status").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static List<String> getTasksIdListByWhatId(String whatId) {
        SObject[] records = query("SELECT Id from Task WHERE WhatId = '" + whatId + "'");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static String getTaskActionsValueByUserId(String userId) {
        try {
            SObject[] query = query("SELECT Actions_value__c from Task WHERE OwnerId = '" + userId + "'");
            return query[0].getSObjectField("Actions_value__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getProcessInstanceIdByPaymentId(String paymentId) {
        try {
            return query("SELECT id FROM ProcessInstance WHERE TargetObjectId = '" + paymentId + "'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getProcessInstanceStepIdByProcessInstanceIdAndStatus(String processInstanceId, String stepStatus) {
        try {
            return query("SELECT id FROM ProcessInstanceStep WHERE ProcessInstanceId = '" + processInstanceId + "' AND StepStatus = '" + stepStatus + "'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getProcessStepActorIdByStepId(String processInstanceStepId) {
        try {
            return query("SELECT ActorId FROM ProcessInstanceStep WHERE Id = '" + processInstanceStepId + "'")
                    [0].getSObjectField("ActorId").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContactFirstNameByContactId(String contactId) {
        try {
            return query("SELECT FirstName FROM Contact WHERE Id = '" + contactId + "'")
                    [0].getSObjectField("FirstName").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContactLastNameByContactId(String contactId) {
        try {
            return query("SELECT LastName FROM Contact WHERE Id = '" + contactId + "'")
                    [0].getSObjectField("LastName").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContactTitleByContactId(String contactId) {
        try {
            return query("SELECT Title__c FROM Contact WHERE Id = '" + contactId + "'")
                    [0].getSObjectField("Title__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContactFunctionByContactId(String contactId) {
        try {
            return query("SELECT Function__c FROM Contact WHERE Id = '" + contactId + "'")
                    [0].getSObjectField("Function__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContactBirthdateByContactId(String contactId) {
        try {
            return query("SELECT Birthdate FROM Contact WHERE Id = '" + contactId + "'")
                    [0].getSObjectField("Birthdate").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContactMobilePhoneByContactId(String contactId) {
        try {
            return query("SELECT MobilePhone FROM Contact WHERE Id = '" + contactId + "'")
                    [0].getSObjectField("MobilePhone").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContactPhoneByContactId(String contactId) {
        try {
            return query("SELECT Phone FROM Contact WHERE Id = '" + contactId + "'")
                    [0].getSObjectField("Phone").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContactEmailByContactId(String contactId) {
        try {
            return query("SELECT Email FROM Contact WHERE Id = '" + contactId + "'")
                    [0].getSObjectField("Email").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContactLanguageByContactId(String contactId) {
        try {
            return query("SELECT Language__c FROM Contact WHERE Id = '" + contactId + "'")
                    [0].getSObjectField("Language__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContactHobbyByContactId(String contactId) {
        try {
            return query("SELECT Hobby__c FROM Contact WHERE Id = '" + contactId + "'")
                    [0].getSObjectField("Hobby__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getSupervisionIdByPaymentId(String paymentId) {
        try {
            return query("SELECT Id FROM Core_Supervision__c WHERE Core_ContractPayment__c  = '" + paymentId + "'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getOpenPaymentApprovalTaskIdByPaymentId(String paymentId) {
        try {
            return query("SELECT Id from Task WHERE WhatId = '" + paymentId + "'"
                    + "and Subject like '%"
                    + Dictionary.translate("New payment validation request for Contract").replace("'", "\\'")
                    + "%' and Status = 'Open' ")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getWholesalerIdByContractAgreementId(String contractAgreementId) {
        try {
            return query("SELECT Id FROM Contract_Agreement_Account__c WHERE Contract_Agreement__c = '"
                    + contractAgreementId
                    + "' AND RecordType.Name = 'Wholesaler'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractManagementDaysInAdvanceByContractManagementId(String ContractManagementId) {
        try {
            return query("SELECT Core_NOfDaysInAdvanceST__c FROM Contract_management__c WHERE Id = '" + ContractManagementId + "'")
                    [0].getSObjectField("Core_NOfDaysInAdvanceST__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static Double getContractManagementPaymentPercentThresholdByContractManagementId(String contractManagementId) {
        try {
            return Double.parseDouble(query("SELECT Core_PaymentThreshold__c FROM Contract_management__c WHERE Id = '" + contractManagementId + "'")
                    [0].getSObjectField("Core_PaymentThreshold__c").toString());
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getSupervisionStatusByContractAgreementId(String contractAgreementId) {
        try {
            return query("SELECT Core_Status__c FROM Core_Supervision__c " +
                    "WHERE Core_ContractAgreementAccount__r.Contract_Agreement__r.Id = '" + contractAgreementId + "'")
                    [0].getSObjectField("Core_Status__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getSupervisionIdByContractAgreementId(String contractAgreementId) {
        try {
            return query("SELECT Id FROM Core_Supervision__c " +
                    "WHERE Core_ContractAgreementAccount__r.Contract_Agreement__r.Id = '" + contractAgreementId + "'")
                    [0].getSObjectField("Id").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static List<String> getContractDealHistoryIdsByAgreementId(String contractAgreementId) {
        SObject[] records = query("SELECT Id FROM Deal_history__c WHERE Contract_Agreement__c ='" + contractAgreementId + "'");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static String getSupervisionStatusByPaymentId(String paymentId) {
        try {
            SObject[] query = query("SELECT Core_Status__c from Core_Supervision__c WHERE Core_ContractPayment__c = '" + paymentId + "'");
            return query[0].getSObjectField("Core_Status__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static List<String> getSupervisionIdsByContractAgreementAccountId(String contractAgreementAccountId) {
        SObject[] records = query("SELECT Id from Core_Supervision__c WHERE Core_ContractAgreementAccount__c = '" + contractAgreementAccountId + "'");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static Boolean getPouringCheckboxBySupervisionId(String supervisionId) {
        try {
            return Boolean.parseBoolean(WSC.query("SELECT Core_Pouring__c FROM Core_SupervisionDetail__c WHERE Core_Supervision__c = '" + supervisionId + "'")[0].getSObjectField("Core_Pouring__c").toString());
        } catch (NullPointerException | ArrayIndexOutOfBoundsException var2) {
            return null;
        }
    }

    public static Boolean getBackBarCheckboxBySupervisionId(String supervisionId) {
        try {
            return Boolean.parseBoolean(WSC.query("SELECT Core_BackBar__c FROM Core_SupervisionDetail__c WHERE Core_Supervision__c = '" + supervisionId + "'")[0].getSObjectField("Core_BackBar__c").toString());
        } catch (NullPointerException | ArrayIndexOutOfBoundsException var2) {
            return null;
        }
    }

    public static Boolean getOnMenuCheckboxBySupervisionId(String supervisionId) {
        try {
            return Boolean.parseBoolean(WSC.query("SELECT Core_OnMenu__c FROM Core_SupervisionDetail__c WHERE Core_Supervision__c = '" + supervisionId + "'")[0].getSObjectField("Core_OnMenu__c").toString());
        } catch (NullPointerException | ArrayIndexOutOfBoundsException var2) {
            return null;
        }
    }

    public static Boolean getDistributionCheckboxBySupervisionId(String supervisionId) {
        try {
            return Boolean.parseBoolean(WSC.query("SELECT Core_Distribution__c FROM Core_SupervisionDetail__c WHERE Core_Supervision__c = '" + supervisionId + "'")[0].getSObjectField("Core_Distribution__c").toString());
        } catch (NullPointerException | ArrayIndexOutOfBoundsException var2) {
            return null;
        }
    }

    public static String getSupervisedVolumesAmountBySupervisionId(String supervisionId) {
        try {
            return WSC.query("SELECT Core_IncreasedVolume__c FROM Core_SupervisionDetail__c WHERE Core_Supervision__c = '" + supervisionId + "'")[0].getSObjectField("Core_IncreasedVolume__c").toString();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException var2) {
            return null;
        }
    }

    public static List<String> getContentDocumentIDsFromContentVersionByUserName(String userName) {
        SObject[] records = query(
                "SELECT ContentDocumentId " +
                        "FROM ContentVersion " +
                        "WHERE CreatedBy.Username ='" + userName + "' " +
                        "AND Object_Type__c NOT IN ('Account') " +
                        "ORDER BY CreatedDate DESC " +
                        "limit 200 ");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("ContentDocumentId").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static String getContractManagementVerbalDealsThresholdByContractManagementId(String contractManagementId) {
        try {
            return query("SELECT Core_VerbalDealsThreshold__c FROM Contract_management__c WHERE Id = '" + contractManagementId + "'")
                    [0].getSObjectField("Core_VerbalDealsThreshold__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static Boolean getContractManagementAllowAddendumByContractManagementId(String contractManagementId) {
        try {
            return Boolean.parseBoolean(query("SELECT AllowAddendums__c FROM Contract_management__c WHERE Id = '"
                    + contractManagementId + "'")[0].getSObjectField("AllowAddendums__c").toString());
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static Double getContractManagementRenewalAvailableDuringDaysByContractManagementId(String contractManagementId) {
        try {
            return Double.parseDouble(query("SELECT RenewalAvailableDays__c FROM Contract_management__c WHERE id ='" +
                    contractManagementId + "'")[0].getSObjectField("RenewalAvailableDays__c").toString());
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static List<String> getCustomNotesIDsByAccountName(String accountName) {
        SObject[] records = query(
                "SELECT Id FROM Custom_Notes__c WHERE AccountId__r.Name ='" + accountName + "' " +
                        "ORDER BY CreatedDate DESC LIMIT 200");
        return Arrays.stream(records)
                .map((e) -> e.getSObjectField("Id").toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static String getVendorAppLocalIdByAccountId(String accountId) {
        try {
            SObject vendor = (SObject) query("SELECT SFDC_Partner_Id__r.App_Local_Id__c FROM Account_Relation__c WHERE SAP_Number__c != NULL AND " +
                    "SAP_Partner_Number__c != NULL AND Partner_Function__c = 'VE' AND SFDC_Id__c = '" + accountId + "'")
                    [0].getSObjectField("SFDC_Partner_Id__r");
            return vendor.getSObjectField("App_Local_Id__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static Boolean getFinancialSupportCanChangeDescriptionByFinancialSupportId(String financialSupportId) {
        try {
            return Boolean.parseBoolean(query("SELECT Can_change_description__c FROM Bacardi_support__c WHERE Id = '"
                    + financialSupportId + "'")[0].getSObjectField("Can_change_description__c").toString());
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractFinancialSupportDescriptionTextByBacardiSupportNameAndContractAgreementId(String bacardiSupportName, String contactAgreementId) {
        try {
            return query("SELECT Description__c FROM Contract_financial_support__c WHERE Title__c = '"
                    + bacardiSupportName + "' AND Contract_Agreement__c = '" + contactAgreementId + "'")[0].getSObjectField("Description__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getApprovalAccountStatusByAccountName(String accountName) {
        try {
            return query("SELECT Approval_Status__c FROM Account WHERE name = '" + accountName + "'")
                    [0].getSObjectField("Approval_status__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getAccountStatusByAccountName(String accountName) {
        try {
            return query("SELECT Account_Status__c FROM Account WHERE name = '" + accountName + "'")
                    [0].getSObjectField("Account_Status__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }


    public static String getDefaultValueByFinancialSupportName(String bacardiSupportName) {
        try {
            return query("SELECT Default_value__c FROM Bacardi_support__c WHERE Name = '" + bacardiSupportName + "'")
                    [0].getSObjectField("Default_value__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static Boolean getFinancialSupportCanChangeDefaultValueByFinancialSupportId(String financialSupportId) {
        try {
            return Boolean.parseBoolean(query("SELECT Can_change_Default_value__c FROM Bacardi_support__c WHERE Id = '"
                    + financialSupportId + "'")[0].getSObjectField("Can_change_Default_value__c").toString());
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public static String getContractFinancialSupportAmountValueByBacardiSupportNameAndContractAgreementId(String bacardiSupportName, String contractAgreementId) {
        try {
            return query("SELECT Value__c FROM Contract_financial_support__c WHERE Title__c = '"
                    + bacardiSupportName + "' AND Contract_Agreement__c = '" + contractAgreementId + "'")[0].getSObjectField("Value__c").toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }
}