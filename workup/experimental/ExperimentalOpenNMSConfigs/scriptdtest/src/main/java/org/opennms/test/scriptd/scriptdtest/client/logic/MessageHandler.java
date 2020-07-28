package org.opennms.test.scriptd.scriptdtest.client.logic;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageHandler {
    static final Logger log = LoggerFactory.getLogger(MessageHandler.class);
    
    public void handleIncomingMessage(JSONObject message) {
        log.debug("MessageHandler handling reply message: " + message);
    }

}
