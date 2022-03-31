package utils;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.search.SearchTerm;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class EmailUtils {

    private static final String OFFICE_365_HOST = "outlook.office365.com";
    public static final String CONGA_SIGN_INBOX_SUBFOLDER = "AQA_Conga_Sign";
    public static final String CONGA_SIGN_PAYMENT_INBOX_SUBFOLDER = "AQA_Conga_Sign_Payment";
    private static final String CONGA_SIGN_LINK_REGEX = "(?:[\\S\\s]*)(http\\S*\\/sign\\/\\w*)(?:[\\S\\s]*)";

    private static Session getImapOffice365session() {
        Properties properties = new Properties();
        properties.put("mail.imap.host", OFFICE_365_HOST);
        properties.put("mail.imap.port", 993);
        return Session.getInstance(properties);
    }

    private static SearchTerm searchTermForCongaSignMsg(List<String> bodyPartsToMatch, List<String> subjectPartsToMatch) {
        return new SearchTerm() {
            @Override
            public boolean match(Message message) {
                try {
                    String body = ((Multipart) message.getContent()).getBodyPart(0).getContent().toString();
                    String subject = message.getSubject();
                    if (body.matches(CONGA_SIGN_LINK_REGEX) &&
                            (bodyPartsToMatch == null || bodyPartsToMatch.stream().allMatch(body::contains)) &&
                            (subjectPartsToMatch == null || subjectPartsToMatch.stream().allMatch(subject::contains))) {
                        return true;
                    }
                } catch (MessagingException | IOException ex) {
                    ex.printStackTrace();
                }
                return false;
            }
        };
    }

    private static SearchTerm searchTermForMailWithAttachment() {
        return new SearchTerm() {
            @Override
            public boolean match(Message message) {
                try {
                    Multipart multiPart = (Multipart) message.getContent();
                    for (int i = 0; i < multiPart.getCount(); i++) {
                        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(i);
                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition()) && !part.getFileName().isEmpty()) {
                            return true;
                        }
                    }
                } catch (MessagingException | IOException ex) {
                    ex.printStackTrace();
                }
                return false;
            }
        };
    }

    public static String getCongaLinkToSignDocument(String subfolderName, String email, String password, List<String> bodyPartsToMatch, List<String> subjectPartsToMatch) {
        try {
            Store store = getImapOffice365session().getStore("imaps");
            store.connect(OFFICE_365_HOST, email, password);

            Folder folderInbox = store.getFolder("Inbox" + store.getDefaultFolder().getSeparator() + subfolderName);
            folderInbox.open(Folder.READ_ONLY);
            Message[] messages = folderInbox.search(searchTermForCongaSignMsg(bodyPartsToMatch, subjectPartsToMatch));
            String body = messages.length > 0 ? ((Multipart) messages[messages.length - 1].getContent()).getBodyPart(0).getContent().toString() : "";
            String link = body.replaceFirst(CONGA_SIGN_LINK_REGEX, "$1");

            folderInbox.close(false);
            store.close();

            if (link.isEmpty()) {
                throw new IllegalStateException("Failed to get Conga Sign link from message by following criteria:\n"
                        + "Subfolder: " + subfolderName + "\n"
                        + "Email: " + email + "\n"
                        + "Password: " + password + "\n"
                        + "Body message should contain: " + bodyPartsToMatch + "\n"
                        + "Subject should contain: " + subjectPartsToMatch);
            }
            return link;
        } catch (NoSuchProviderException e) {
            throw new IllegalStateException("No provider.\n" + e.getMessage());
        } catch (MessagingException e) {
            throw new IllegalStateException("Could not connect to the message store.\n" + e.getMessage());
        } catch (IOException e) {
            throw new IllegalStateException("Could not read a message.\n" + e.getMessage());
        }
    }

    public static void clearInboxSubfolder(String subfolderName, String email, String password) {
        try {
            Store store = getImapOffice365session().getStore("imaps");
            store.connect(OFFICE_365_HOST, email, password);

            Folder folder = store.getFolder("Inbox" + store.getDefaultFolder().getSeparator() + subfolderName);
            folder.open(Folder.READ_WRITE);
            Message[] messages = folder.getMessages();
            for (Message message : messages) {
                message.setFlag(Flags.Flag.DELETED, true);
            }
            folder.close(true);
            store.close();
        } catch (NoSuchProviderException e) {
            throw new IllegalStateException("No provider.\n" + e.getMessage());
        } catch (MessagingException e) {
            throw new IllegalStateException("Could not connect to the message store.\n" + e.getMessage());
        }
    }


    public static String getAttachmentName(String subfolderName, String email, String password) {
        String fileName = "";
        try {
            Store store = getImapOffice365session().getStore("imaps");
            store.connect(OFFICE_365_HOST, email, password);
            Folder folderInbox = store.getFolder("Inbox" + store.getDefaultFolder().getSeparator() + subfolderName);
            folderInbox.open(Folder.READ_ONLY);
            Message message = folderInbox.search(searchTermForMailWithAttachment())[0];
            Multipart multiPart = (Multipart) message.getContent();
            for (int i = 0; i < multiPart.getCount(); i++) {
                MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(i);
                if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                    fileName = part.getFileName();
                }
            }
            folderInbox.close(true);
            store.close();
            return fileName;
        } catch (NoSuchProviderException e) {
            throw new IllegalStateException("No provider.\n" + e.getMessage());
        } catch (MessagingException e) {
            throw new IllegalStateException("Could not connect to the message store.\n" + e.getMessage());
        } catch (IOException e) {
            throw new IllegalStateException("Could not read a message.\n" + e.getMessage());
        }
    }
}