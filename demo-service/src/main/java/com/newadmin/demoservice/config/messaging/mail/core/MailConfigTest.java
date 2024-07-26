package com.newadmin.demoservice.config.messaging.mail.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.util.Properties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MailConfigTest {

    private MailConfig mailConfig;

    @BeforeEach
    void setUp() {
        mailConfig = new MailConfig();
    }

    @Test
    void testDefaultValues() {
        assertEquals("smtp", mailConfig.getProtocol());
        assertEquals(StandardCharsets.UTF_8, mailConfig.getDefaultEncoding());
        assertFalse(mailConfig.isSslEnabled());
        assertNotNull(mailConfig.getProperties());
    }

    @Test
    void testSettersAndGetters() {
        mailConfig.setProtocol("imap");
        assertEquals("imap", mailConfig.getProtocol());

        mailConfig.setHost("smtp.example.com");
        assertEquals("smtp.example.com", mailConfig.getHost());

        mailConfig.setPort(587);
        assertEquals(587, mailConfig.getPort());

        mailConfig.setUsername("user");
        assertEquals("user", mailConfig.getUsername());

        mailConfig.setPassword("pass");
        assertEquals("pass", mailConfig.getPassword());

        mailConfig.setFrom("noreply@example.com");
        assertEquals("noreply@example.com", mailConfig.getFrom());

        mailConfig.setSslEnabled(true);
        assertTrue(mailConfig.isSslEnabled());

        mailConfig.setSslPort(465);
        assertEquals(465, mailConfig.getSslPort());

        mailConfig.setDefaultEncoding(StandardCharsets.ISO_8859_1);
        assertEquals(StandardCharsets.ISO_8859_1, mailConfig.getDefaultEncoding());
    }

    @Test
    void testToJavaMailProperties() {
        mailConfig.setFrom("noreply@example.com");
        mailConfig.setSslEnabled(true);
        mailConfig.setSslPort(465);

        Properties properties = mailConfig.toJavaMailProperties();

        assertEquals("noreply@example.com", properties.getProperty("mail.from"));
        assertEquals("true", properties.getProperty("mail.smtp.auth"));
        assertEquals("true", properties.getProperty("mail.smtp.ssl.enable"));
        assertEquals("465", properties.getProperty("mail.smtp.socketFactory.port"));
        assertEquals("javax.net.ssl.SSLSocketFactory",
            properties.getProperty("mail.smtp.socketFactory.class"));
        assertEquals("true", properties.getProperty("mail.smtp.ssl.checkserveridentity"));
    }

    @Test
    void testToJavaMailPropertiesWithoutSsl() {
        mailConfig.setFrom("noreply@example.com");

        Properties properties = mailConfig.toJavaMailProperties();

        assertEquals("noreply@example.com", properties.getProperty("mail.from"));
        assertEquals("true", properties.getProperty("mail.smtp.auth"));
        assertNull(properties.getProperty("mail.smtp.socketFactory.port"));
        assertNull(properties.getProperty("mail.smtp.socketFactory.class"));
        assertNull(properties.getProperty("mail.smtp.ssl.checkserveridentity"));
    }
}
