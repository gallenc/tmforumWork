/* Scripted SPM Event Forwarder - original class */
/* Author: Craig Gallen */
/* Version : 1.0 */

package org.opennms.test.scriptd.scriptdtest.client.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.opennms.netmgt.events.api.EventIpcManagerFactory;
import org.opennms.netmgt.events.api.model.IEvent;
import org.opennms.netmgt.model.OnmsNode;
import org.opennms.netmgt.model.events.EventBuilder;
import org.opennms.netmgt.xml.event.AlarmData;
import org.opennms.netmgt.xml.event.Event;
import org.opennms.netmgt.xml.event.Logmsg;

public class ScriptedEventSPMForwarder extends MessageHandler {
    static final Logger log = LoggerFactory.getLogger(ScriptedEventSPMForwarder.class);

    /* Standard OpenNMS BSM events */
    static final String SERVICE_PROBLEM = "uei.opennms.org/bsm/serviceProblem";
    static final String SERVICE_OPERATIONAL_STATUS_CHANGED = "uei.opennms.org/bsm/serviceOperationalStatusChanged";
    static final String SERVICE_PROBLEM_RESOLVED = "uei.opennms.org/bsm/serviceProblemResolved";

    /* New OpenNMS Service Problem Reply Event */
    static final String SERVICE_PROBLEM_REPLY_UEI = "uei.opennms.org/bsm/serviceProblemReply";

    /* New OpenNMS Service Problem Events */
    static final String SERVICE_PROBLEM_UEI = "uei.opennms.org/spm/serviceProblem";
    static final String SERVICE_PROBLEM_ATTRIBUTE_VALUE_CHANGE_UEI = "uei.opennms.org/spm/serviceProblemAttributeValueChange";
    static final String SERVICE_PROBLEM_INFORMATION_REQUIRED_UEI = "uei.opennms.org/spm/serviceProblemInformationRequired";
    static final String SERVICE_PROBLEM_STATE_CHANGE_UEI = "uei.opennms.org/spm/serviceProblemStateChange";

    /* TMF SPM Service Problem event types */
    static final String SERVICE_PROBLEM_CREATE_NOTIFICATION = "serviceProblemCreateNotification";
    static final String SERVICE_PROBLEM_ATTRIBUTE_VALUE_CHANGE_NOTIFICATION = "serviceProblemAttributeValueChangeNotification";
    static final String SERVICE_PROBLEM_INFORMATION_REQUIRED_NOTIFICATION = "serviceProblemInformationRequiredNotification";
    static final String SERVICE_PROBLEM_STATE_CHANGE_NOTIFICATION = "serviceProblemStateChangeNotification";

    ScriptedApacheHttpAsyncClient m_scriptedClient = null;

    List<UrlCredential> m_urlCredentials = new ArrayList();

    public void setUrlCredentials(List<UrlCredential> urlCredentials) {
        if (urlCredentials == null || urlCredentials.size() == 0) {
            log.error("UrlCredential[] urlCredentials is null or empty. Cannot send service problem");
            return;
        }
        for (UrlCredential urlCredential : urlCredentials) {
            String url = urlCredential.getUrl();
            try {
                URL u = new URL(url);
            } catch (MalformedURLException e) {
                log.error("UrlCredential[] urlCredentials malformed url=" + url);
            }
        }
        m_urlCredentials = urlCredentials;
    }

    public void setScriptedClient(ScriptedApacheHttpAsyncClient scriptedClient) {
        log.debug("scriptedEventSPMForwarder set scriptedClient " + m_scriptedClient);
        m_scriptedClient = scriptedClient;
    }

    public JSONObject createMinimalServiceProblem(String originatingSystem, String category, String priority,
            String description, String reason, String correlationId, String[] affectedServices) {

        JSONObject spm = new JSONObject();
        if (originatingSystem != null)
            spm.put("originatingSystem", originatingSystem);
        if (category != null)
            spm.put("category", category);
        if (priority != null)
            spm.put("priority", priority);
        if (description != null)
            spm.put("description", description);
        if (reason != null)
            spm.put("reason", reason);
        if (correlationId != null)
            spm.put("correlationId", correlationId);

        if (affectedServices != null) {
            JSONArray affectedService = new JSONArray();
            for (String service : affectedServices) {
                JSONObject jservice = new JSONObject();
                jservice.put("id", service);
                jservice.put("href", null);
                affectedService.add(jservice);
            }
            spm.put("affectedService", affectedService);
        }

        return spm;

    }

    public Event onmsEventFromServiceProblem(String uei, JSONObject serviceProblem) {

        String id = (String) serviceProblem.get("id");
        String correlationId = (String) serviceProblem.get("correlationId");
        String href = (String) serviceProblem.get("href");
        String source = "spm-interface";
        String reason = (String) serviceProblem.get("reason");

        EventBuilder eventBuilder = new EventBuilder(uei, source);
        /* this will add the initial correlation id to the event */
        eventBuilder.addParam("spmCorrelationId", correlationId);
        /* this will add the service problem id to the event */
        eventBuilder.addParam("spmID", id);
        /* this will add the service problem href to the event */
        eventBuilder.addParam("spmHREF", href);
        /* this will add the service problem reason to the event */
        eventBuilder.addParam("spmReason", reason);

        Event event = eventBuilder.getEvent();
        
        log.debug("onmsEventFromServiceProblem id:"+id+" correlationId:"+correlationId+" href:"+href+" source:"+source+" reason:"+reason+ " TO EVENT:"+event);

        return event;
    }

    public void handleEvent(IEvent ievent, OnmsNode node) {
        if (SERVICE_PROBLEM.equals(ievent.getUei())) {
            log.debug("handleEvent script received SERVICE_PROBLEM event:" + ievent + " node:" + node);
            updateServiceProblem(ievent);

        } else if (SERVICE_OPERATIONAL_STATUS_CHANGED.equals(ievent.getUei())) {
            log.debug("handleEvent script received SERVICE_OPERATIONAL_STATUS_CHANGED event:" + ievent + " node:" + node);

        } else if (SERVICE_PROBLEM_RESOLVED.equals(ievent.getUei())) {
            log.debug("handleEvent script received SERVICE_PROBLEM_RESOLVED event:" + ievent + " node:" + node);

        }

    }

    public void updateServiceProblem(IEvent ievent) {

        if (!SERVICE_PROBLEM.equals(ievent.getUei())) {
            return;
        }

        try {
            log.debug("updateServiceProblem script received immutable event:" + ievent);

            Event event = Event.copyFrom(ievent);
            Integer eventId = event.getDbid();
            AlarmData alarmData = (AlarmData) event.getAlarmData();
            String reductionKey = (alarmData == null) ? null : alarmData.getReductionKey();
            String description = event.getDescr();
            Logmsg logmsg = event.getLogmsg();
            String logmsgStr = (logmsg == null) ? null : logmsg.getContent();
            String uei = event.getUei();
            String businessServiceName = (event.getParm("businessServiceName") == null) ? "Undefined" : event.getParm("businessServiceName").getValue().getContent();
            String businessServiceId = (event.getParm("businessServiceId") == null) ? null : event.getParm("businessServiceId").getValue().getContent();
            String rootCause = (event.getParm("rootCause") == null) ? null : event.getParm("rootCause").getValue().getContent();

            /* may be in messages after reply update */
            String href = (event.getParm("spmHREF") == null) ? null : event.getParm("spmHREF").getValue().getContent();
            String id = (event.getParm("spmID") == null) ? null : event.getParm("spmID").getValue().getContent();

            /* only create new alarm if href not present */
            if (href == null) {
                log.debug("updateServiceProblem event has no spmHREF param - creating new service problem");

                String originatingSystem = "opennms";
                String category = " equipment";
                String priority = "1";
                String reason = logmsgStr;
                String correlationId = reductionKey;
                String[] affectedServices = { businessServiceName };

                /* create new service problem for each url */
                if (m_urlCredentials.size() == 0) {
                    log.warn("no baseUrls set. Cannot send service problem.");
                }

                /* send message to all registered spm servers */
                for (UrlCredential urlCredential : m_urlCredentials) {
                    String baseUrl = urlCredential.getUrl();
                    String username = urlCredential.getUsername();
                    String password = urlCredential.getPassword();

                    try {

                        JSONObject serviceProblem = createMinimalServiceProblem(originatingSystem, category, priority,
                                description, reason, correlationId, affectedServices);
                        log.debug("updateServiceProblem sending service problem : " + serviceProblem.toString());
                        String url = baseUrl + "/tmf-api/serviceProblemManagement/v3/serviceProblem";

                        /* post http request */
                        m_scriptedClient.postRequest(url, serviceProblem.toString(), username, password);

                    } catch (Exception e2) {
                        log.error("problem posting new service problem ", e2);
                    }

                }
            } else {
                log.debug("updateServiceProblem not creating new service problem as event has spmHREF=" + href);
            }

        } catch (Exception e) {
            log.debug("problem creating service problem", e);
        }

    }

    @Override
    public synchronized void handleIncomingMessage(JSONObject message) {

        log.debug("handleIncomingMessage called,  message=" + message);

        String messageSource = (String) message.get("messageSource");
        String requestMethod = (String) message.get("requestMethod");
        String requestHost = (String) message.get("requestHost");
        String requestPath = (String) message.get("requestPath");
        String status = (String) message.get("status");
        JSONObject jsonobject = (JSONObject) message.get("jsonobject");
        JSONArray jsonarray = (JSONArray) message.get("jsonarray");

        /* check if message comes as reply from a push message from asyncClyent */
        if ("asyncClient".equals(messageSource)) {

            /* possible spm sent messages */
            /* POST /tmf-api/serviceProblemManagement/v3/serviceProblem */
            /* PATCH /tmf-api/serviceProblemManagement/v3/serviceProblem/2 */
            /* DELETE /tmf-api/serviceProblemManagement/v3/serviceProblem/2 */

            if ("200".equals(status) || "201".equals(status) || "204".equals(status)) {
                /* find out what message replied to */

                /*
                 * check for reply to POST /tmf-api/serviceProblemManagement/v3/serviceProblem
                 */
                if (requestPath != null && requestPath.contains("/tmf-api/serviceProblemManagement/v3/serviceProblem")
                        && "POST".equals(requestMethod) && jsonobject != null) {

                    String uei = SERVICE_PROBLEM_REPLY_UEI;
                    Event event = onmsEventFromServiceProblem(uei, jsonobject);
                    log.debug("Persisting event to OpenNMS:" + event.toString());

                    try {
                        EventIpcManagerFactory.getIpcManager().sendNow(event);
                        log.debug("sent SERVICE_PROBLEM_REPLY event through ipcManager");
                    } catch (Throwable t) {
                        log.debug("problem sending event to OpenNMS:", t);
                    }

                } else {
                    /* just log reply from any other method or request */
                    log.debug("Unused reply from SPM interface: " + message.toString());
                }

            } else {
                /* create event for error reply */
                log.debug("Error Reply from SPM interface: " + message.toString());
            }

            /* check if message comes as input from httpServer */
        } else if ("httpServer".equals(messageSource)) {

            log.debug("Http server received message: " + message.toString());
            String spmEventType = null;
            JSONObject spmServiceProblem = null;
            String spmServiceProblemId = null;
            if (jsonobject != null) {
                spmEventType = (String) jsonobject.get("eventType");
                JSONObject spmEvent = (JSONObject) jsonobject.get("event");
                spmServiceProblem = (spmEvent == null) ? null : (JSONObject) spmEvent.get("serviceProblem");
                spmServiceProblemId = (spmServiceProblem == null) ? null : (String) spmServiceProblem.get("id");
            }
            if (spmEventType == null || spmServiceProblem == null || spmServiceProblemId == null) {
                log.debug("cannot recognise message as SPM event."
                        + " spmEventType: "+spmEventType
                        + " spmServiceProblemId: "+spmServiceProblemId
                        + " spmServiceProblem: "+spmServiceProblem
                        + " Message: " + message.toString());
            } else {
                String uei = null;
                Event event = null;
                
                switch (spmEventType) {
                /* TMF SPM Service Problem event types */
                case SERVICE_PROBLEM_CREATE_NOTIFICATION :
                    uei = SERVICE_PROBLEM_UEI;
                    event = onmsEventFromServiceProblem(uei, jsonobject);
                    log.debug("Persisting event to OpenNMS:" + event.toString());
                    try {
                        EventIpcManagerFactory.getIpcManager().sendNow(event);
                        log.debug("sent SERVICE_PROBLEM_CREATE_NOTIFICATION event through ipcManager");
                    } catch (Throwable t) {
                        log.debug("problem sending event to OpenNMS:", t);
                    }
                    break;
                case SERVICE_PROBLEM_STATE_CHANGE_NOTIFICATION :
                    uei = SERVICE_PROBLEM_STATE_CHANGE_UEI;
                    event = onmsEventFromServiceProblem(uei, jsonobject);
                    log.debug("Persisting event to OpenNMS:" + event.toString());
                    try {
                        EventIpcManagerFactory.getIpcManager().sendNow(event);
                        log.debug("sent SERVICE_PROBLEM_STATE_CHANGE_NOTIFICATION event through ipcManager");
                    } catch (Throwable t) {
                        log.debug("problem sending event to OpenNMS:", t);
                    }
                    break;
                case SERVICE_PROBLEM_ATTRIBUTE_VALUE_CHANGE_NOTIFICATION :
                    uei = SERVICE_PROBLEM_ATTRIBUTE_VALUE_CHANGE_UEI;
                    event = onmsEventFromServiceProblem(uei, jsonobject);
                    log.debug("Persisting event to OpenNMS:" + event.toString());
                    try {
                        EventIpcManagerFactory.getIpcManager().sendNow(event);
                        log.debug("sent SERVICE_PROBLEM_ATTRIBUTE_VALUE_CHANGE_NOTIFICATION event through ipcManager");
                    } catch (Throwable t) {
                        log.debug("problem sending event to OpenNMS:", t);
                    }
                    break;
                case SERVICE_PROBLEM_INFORMATION_REQUIRED_NOTIFICATION :
                    uei = SERVICE_PROBLEM_INFORMATION_REQUIRED_UEI;
                    event = onmsEventFromServiceProblem(uei, jsonobject);
                    log.debug("Persisting event to OpenNMS:" + event.toString());
                    try {
                        EventIpcManagerFactory.getIpcManager().sendNow(event);
                        log.debug("sent SERVICE_PROBLEM_INFORMATION_REQUIRED_NOTIFICATION event through ipcManager");
                    } catch (Throwable t) {
                        log.debug("problem sending event to OpenNMS:", t);
                    }
                    break;
                default:
                    log.debug("unknown SPM event type. spmEventType: " + spmEventType);
                }
            }

        } else {
            /* unknown message source */
            log.debug("message received from unknown internal source:" + messageSource + " message: "
                    + message.toString());
        }

    }
    
    /* returns this beanshell declaration so that its methods can be invoked */
    /* return this; */

}
