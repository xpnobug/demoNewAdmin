package com.newadmin.demoservice.DesignPattern.singletonPattern.loggers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserActivityTracker {

    private static final Logger logger = LogManager.getLogger(UserActivityTracker.class);

    public static void main(String[] args) {
        UserActivityTracker tracker = new UserActivityTracker();
        tracker.userLogin("john.doe");
        tracker.accessPage("john.doe", "homepage");
        tracker.userLogout("john.doe");
    }

    public void userLogin(String username) {
        logger.info("User {} logged in", username);
    }

    public void accessPage(String username, String page) {
        logger.info("User {} accessed {}", username, page);
    }

    public void userLogout(String username) {
        logger.info("User {} logged out", username);
    }
}
