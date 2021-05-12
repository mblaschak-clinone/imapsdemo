package com.clinone.imapsdemo;


import java.util.Map;
import java.util.Properties;

import javax.mail.*;

import org.junit.Assert;
import org.junit.Test;



/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void connect() {
        Map<String, String> env = System.getenv();

        String host = "imap.gmail.com";
        String user = env.get("GMAIL_USER");
        String password = env.get("GMAIL_PASS");
        String protocol = "imaps";
        int port = 993;

        if (user == null) {
            System.out.println("missing gmail username environment variable GMAIL_USER");
        }
        if(password == null) {
            System.out.println("missing gmail password environment variable GMAIL_PASS");
        }

        Store store = null;

        Properties props = new Properties();
        props.setProperty("mail.mime.multipart.ignoreexistingboundaryparameter", "true");
        props.setProperty("mail.smtp.connectiontimeout", "150000");
        props.setProperty("mail.smtp.timeout", "150000");
        props.setProperty("mail.smtp.writetimeout", "150000");
        props.setProperty("mail.store.protocol", protocol);
        props.setProperty("mail.imap.user", user);
        props.setProperty("mail.imaps.starttls.enable", "true");
        props.setProperty("mail.imap.ssl.enable", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props);
        session.setDebug(true);
        if (store == null) {
            try {
                System.out.println("Get GMail Store");
                store = session.getStore(protocol);
            } catch (NoSuchProviderException e) {
                System.out.println("Getting Store is failed: " + e.getMessage());
            }
        } else {
            System.out.println("Store is already defined");
        }
        if (store != null) {
            try {
                System.out.println("connect to GMail store with Host:" + host + " port:" + port + " user:" + user
                        + " password:" + password + " and Store URL:" + store.getURLName());
                store.connect(host, port, user, password);
            } catch (MessagingException e) {
                System.out.println("Store connection is failed: " + e.getMessage());
                Assert.fail();
            }
            System.out.println("imapStore ---" + store);
        }
    }
}
