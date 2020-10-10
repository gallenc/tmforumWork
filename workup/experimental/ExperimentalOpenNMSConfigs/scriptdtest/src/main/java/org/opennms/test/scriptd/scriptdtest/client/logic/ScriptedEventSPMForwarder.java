/* Scripted SPM Event Forwarder - original class */
/* Author: Craig Gallen */
/* Version : 1.8 */

package org.opennms.test.scriptd.scriptdtest.client.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.opennms.netmgt.events.api.EventIpcManagerFactory;
import org.opennms.netmgt.events.api.model.IEvent;
import org.opennms.netmgt.model.OnmsNode;
import org.opennms.netmgt.model.events.EventBuilder;
import org.opennms.netmgt.xml.event.AlarmData;
import org.opennms.netmgt.xml.event.Event;
import org.opennms.netmgt.xml.event.Logmsg;

import org.opennms.core.spring.BeanUtils;
import org.opennms.netmgt.dao.api.AlarmDao;
import org.opennms.netmgt.model.OnmsAlarm;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class ScriptedEventSPMForwarder extends MessageHandler {
    static final Logger log = LoggerFactory.getLogger(ScriptedEventSPMForwarder.class);

    /* Standard OpenNMS BSM events */
    static final String BSM_SERVICE_PROBLEM_UEI = "uei.opennms.org/bsm/serviceProblem";
    static final String BSM_SERVICE_OPERATIONAL_STATUS_CHANGED_UEI = "uei.opennms.org/bsm/serviceOperationalStatusChanged";
    static final String BSM_SERVICE_PROBLEM_RESOLVED_UEI = "uei.opennms.org/bsm/serviceProblemResolved";

    /* New OpenNMS Service Problem Reply Event */
    static final String SERVICE_PROBLEM_REPLY_UEI = "uei.opennms.org/tmf656spm/serviceProblemReply";

    /* New OpenNMS Service Problem Events */
    static final String SERVICE_PROBLEM_UEI = "uei.opennms.org/tmf656spm/serviceProblem";
    static final String SERVICE_PROBLEM_ATTRIBUTE_VALUE_CHANGE_UEI = "uei.opennms.org/tmf656spm/serviceProblemAttributeValueChange";
    static final String SERVICE_PROBLEM_INFORMATION_REQUIRED_UEI = "uei.opennms.org/tmf656spm/serviceProblemInformationRequired";
    static final String SERVICE_PROBLEM_STATE_CHANGE_UEI = "uei.opennms.org/tmf656spm/serviceProblemStateChange";
    static final String SERVICE_PROBLEM_CLOSED_CANCELLED_OR_DELETED_UEI ="uei.opennms.org/tmf656spm/serviceProblemClosedCancelledOrDeleted";

    /* TMF SPM Service Problem event types */
    static final String SERVICE_PROBLEM_CREATE_NOTIFICATION = "ServiceProblemCreateNotification";
    static final String SERVICE_PROBLEM_ATTRIBUTE_VALUE_CHANGE_NOTIFICATION = "ServiceProblemAttributeValueChangeNotification";
    static final String SERVICE_PROBLEM_INFORMATION_REQUIRED_NOTIFICATION = "ServiceProblemInformationRequiredNotification";
    static final String SERVICE_PROBLEM_STATE_CHANGE_NOTIFICATION = "ServiceProblemStateChangeNotification";

    private ScriptedApacheHttpAsyncClient m_scriptedClient = null;

    private List<UrlCredential> m_urlCredentials = new ArrayList();

    private List<UrlCredential> m_notificationCredentials = new ArrayList();

    private String m_thisOriginatingSystem = "opennms-notset";
    
    private String m_thisOriginatorParty ="originator-party-notset";

	private String m_resourceInventoryManagementBaseHrefUrl = "http://localhost:8080/tmf-api/resourceInventoryManagement/v3/resource/";
    
    private String m_serviceInventoryManagementBaseHrefUrl ="http://localhost:8080/tmf-api/serviceInventoryManagement/v3/service/";
    
    private String m_partyManagementBaseHrefUrl = "http://localhost:8080/tmf-api/partyManagement/v3/party/";

	/* URL, Registered Listener */
    private Map<String, String> m_registered_listeners = Collections.synchronizedMap(new HashMap<String, String>());

    private ScheduledExecutorService m_scheduledExecutorService = null;

    public void setUrlCredentials(List<UrlCredential> urlCredentials) {
        if (urlCredentials == null || urlCredentials.size() == 0) {
            log.error("urlCredentials is null or empty. Cannot send service problem");
            return;
        }
        for (UrlCredential urlCredential : urlCredentials) {
            String url = urlCredential.getUrl();
            try {
                URL u = new URL(url);
            } catch (MalformedURLException e) {
                log.error("UrlCredential[] urlCredentials malformed url=" + url);
                return;
            }
        }
        m_urlCredentials = urlCredentials;
    }

    public void setThisOriginatingSystem(String thisOriginatingSystem) {
        m_thisOriginatingSystem = thisOriginatingSystem;
    }

    public void setThisOriginatorParty(String thisOriginatorParty) {
		m_thisOriginatorParty = thisOriginatorParty;
	}

    public void setResourceInventoryManagementBaseHrefUrl(String resourceInventoryManagementBaseHrefUrl) {
		m_resourceInventoryManagementBaseHrefUrl = resourceInventoryManagementBaseHrefUrl;
	}

	public void setServiceInventoryManagementBaseHrefUrl(String serviceInventoryManagementBaseHrefUrl) {
		m_serviceInventoryManagementBaseHrefUrl = serviceInventoryManagementBaseHrefUrl;
	}

	public void setPartyManagementBaseHrefUrl(String partyManagementBaseHrefUrl) {
		m_partyManagementBaseHrefUrl = partyManagementBaseHrefUrl;
	}

    public void setScriptedClient(ScriptedApacheHttpAsyncClient scriptedClient) {
        log.debug("scriptedEventSPMForwarder set scriptedClient " + m_scriptedClient);
        m_scriptedClient = scriptedClient;
    }

    public JSONObject createMinimalServiceProblem(String originatingSystem, String category, String priority,
    		String description, String reason, String correlationId, String[] affectedServices, String[] affectedResources) {

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
                jservice.put("href", m_serviceInventoryManagementBaseHrefUrl+service);
                affectedService.add(jservice);
            }
            spm.put("affectedService", affectedService);
        }
        
        if (affectedResources != null) {
            JSONArray affectedResource = new JSONArray();
            for (String resource : affectedResources) {
                JSONObject jresource = new JSONObject();
                jresource.put("id", resource);
                jresource.put("href", m_resourceInventoryManagementBaseHrefUrl+resource);
                affectedResource.add(jresource);
            }
            spm.put("affectedResource", affectedResource);
        }
        
        JSONObject joriginatorParty = new JSONObject();
        joriginatorParty.put("id", m_thisOriginatorParty);
        joriginatorParty.put("href", m_partyManagementBaseHrefUrl+m_thisOriginatorParty);
        spm.put("originatorParty", joriginatorParty);
        
        return spm;
    }

    public Event onmsEventFromServiceProblem(String uei, JSONObject serviceProblem) {

        String id = (String) serviceProblem.get("id");
        String correlationId = (String) serviceProblem.get("correlationId");
        String href = (String) serviceProblem.get("href");
        String source = "spm-interface";
        String reason = (String) serviceProblem.get("reason");
        String originatingSystem = (String) serviceProblem.get("originatingSystem");
        String status = (String) serviceProblem.get("status");
        String priority = (serviceProblem.get("priority") == null) ? null : serviceProblem.get("priority").toString();
        String statusChangeReason = (serviceProblem.get("statusChangeReason") == null) ? null : serviceProblem.get("statusChangeReason").toString();

        EventBuilder eventBuilder = new EventBuilder(uei, source);

        JSONArray affectedService = (JSONArray) serviceProblem.get("affectedService");
        if (affectedService != null) {
            String spmAffectedServicesJson = affectedService.toString();
            StringBuilder spmAffectedServicesHtml = new StringBuilder();
            for (Object svc : affectedService) {
                JSONObject jservice = (JSONObject) svc;
                String svcid = (String) jservice.get("id");
                String svchref = (String) jservice.get("href");
                spmAffectedServicesHtml.append("<a href=\"" + svchref + "\">" + svcid + "</a> ");
            }

            /* this will add a json version of affected services to the event */
            eventBuilder.addParam("spmAffectedServicesJson", spmAffectedServicesJson);

            /* this will add an html version of affected services to the event */
            eventBuilder.addParam("spmAffectedServicesHtml", spmAffectedServicesHtml.toString());

        }
        
        JSONArray affectedResource = (JSONArray) serviceProblem.get("affectedResource");
        if (affectedResource != null) {
            String spmAffectedResourcesJson = affectedResource.toString();
            StringBuilder spmAffectedResourcesHtml = new StringBuilder();
            for (Object res : affectedResource) {
                JSONObject jresource = (JSONObject) res;
                String resid = (String) jresource.get("id");
                String reshref = (String) jresource.get("href");
                spmAffectedResourcesHtml.append("<a href=\"" + reshref + "\">" + resid + "</a> ");
            }

            /* this will add a json version of affected resources to the event */
            eventBuilder.addParam("spmAffectedResourcesJson", spmAffectedResourcesJson);

            /* this will add an html version of affected resources to the event */
            eventBuilder.addParam("spmAffectedResourcesHtml", spmAffectedResourcesHtml.toString());

        }

        eventBuilder.setSeverity("Warning");

        /* this will add the initial correlation id to the event */
        eventBuilder.addParam("spmCorrelationId", correlationId);
        /* this will add the service problem id to the event */
        eventBuilder.addParam("spmID", id);
        /* this will add the service problem href to the event */
        eventBuilder.addParam("spmHREF", href);
        /* this will add the service problem reason to the event */
        eventBuilder.addParam("spmReason", reason);
        /* this will add the service problem originatingSystem to the event */
        eventBuilder.addParam("spmOriginatingSystem", originatingSystem);
        /* this will add the service problem status to the event */
        eventBuilder.addParam("spmStatus", status);
        /* this will add the service problem status change reason to the event */
        eventBuilder.addParam("spmStatusChangeReason", statusChangeReason);
        /* this will add the service problem priority to the event */
        eventBuilder.addParam("spmPriority", priority);

        Event event = eventBuilder.getEvent();

        log.debug("onmsEventFromServiceProblem id:"+id
                + " correlationId:"+correlationId
                + " href:"+href
                + " source:"+source
                + " reason:"+reason
                + " originatingSystem:"+originatingSystem
                + " affectedService:"+affectedService
                + " affectedResource:"+affectedResource
                + " TO EVENT:"+event);
        
        return event;
    }

    public void handleEvent(IEvent ievent, OnmsNode node) {
        if (BSM_SERVICE_PROBLEM_UEI.equals(ievent.getUei())) {
            log.debug("handleEvent script received SERVICE_PROBLEM event:" + ievent + " node:" + node);
            createServiceProblem(ievent);

        } else if (BSM_SERVICE_OPERATIONAL_STATUS_CHANGED_UEI.equals(ievent.getUei())) {
            log.debug("handleEvent script received SERVICE_OPERATIONAL_STATUS_CHANGED event:" + ievent + " node:" + node);

        } else if (BSM_SERVICE_PROBLEM_RESOLVED_UEI.equals(ievent.getUei())) {
            log.debug("handleEvent script received SERVICE_PROBLEM_RESOLVED event:" + ievent + " node:" + node);
            resolveServiceProblem(ievent);
        }

    }
    
    /* note this only works for single href */
    public void resolveServiceProblem(IEvent ievent) {
        log.debug("resolveServiceProblem script received immutable event:" + ievent);
        Event event = Event.copyFrom(ievent);
        String spmHREF = (event.getParm("spmHREF") == null) ? null : event.getParm("spmHREF").getValue().getContent();
        String spmID = (event.getParm("spmID") == null) ? null : event.getParm("spmID").getValue().getContent();
        
        if ( spmHREF == null ) {
        	log.debug("resolveServiceProblem cannot resolve serviceProblem as event has no spmHREF");
        	return;
        }
        
        try {
        	log.debug("resolveServiceProblem trying to resolve problem with spmHREF="+spmHREF);

        	/* patch service problem for spmHREF using correct credentials */
            if (m_urlCredentials.size() == 0) {
                log.warn("no baseUrls set. Cannot send service problem patch.");
            }

            /* send patch to all spm server matching spmHREF */
            for (UrlCredential urlCredential : m_urlCredentials) {
                String baseUrl = urlCredential.getUrl();
                String username = urlCredential.getUsername();
                String password = urlCredential.getPassword();
                
                if(spmHREF!=null && spmHREF.contains(baseUrl)) try {
                	
                	JSONObject serviceProblemPatch = new JSONObject();
                	serviceProblemPatch.put("id", spmID);
                	serviceProblemPatch.put("href", spmHREF);
                	serviceProblemPatch.put("status", "Resolved");
                	serviceProblemPatch.put("statusChangeReason", "service problem resolved in OpenNMS");

                    log.debug("resolveServiceProblem resolving service problem HREF : " + spmHREF);
                    String url = baseUrl + "/tmf-api/serviceProblemManagement/v3/serviceProblem";

                    /* patch http request */
                    m_scriptedClient.patchRequest(url, serviceProblemPatch.toString(), username, password);

                } catch (Exception e2) {
                    log.error("problem patching service problem ", e2);
                }
            }
         } catch (Exception e) {
            log.debug("resolveServiceProblem problem patching service problem", e);
         }
    }

    public void createServiceProblem(IEvent ievent) {

        try {
            log.debug("createServiceProblem script received immutable event:" + ievent);

            Event event = Event.copyFrom(ievent);
            Integer eventId = event.getDbid();
            AlarmData alarmData = (AlarmData) event.getAlarmData();
            String reductionKey = (alarmData == null) ? null : alarmData.getReductionKey();
            String description = event.getDescr();
            String severity = event.getSeverity();
            Logmsg logmsg = event.getLogmsg();
            String logmsgStr = (logmsg == null) ? null : logmsg.getContent();
            String uei = event.getUei();
            String businessServiceName = (event.getParm("businessServiceName") == null) ? "Undefined" : event.getParm("businessServiceName").getValue().getContent();
            String businessServiceId = (event.getParm("businessServiceId") == null) ? null : event.getParm("businessServiceId").getValue().getContent();
            String rootCause = (event.getParm("rootCause") == null) ? null : event.getParm("rootCause").getValue().getContent();

            /* spmAffectedServices must be set by bsm business service attributes as comma separated variables with no spaces */
            /* spmAffectedResources must be set by bsm business service attributes as comma separated variables with no spaces */
            /* if spmAffectedServices or spmAffectedResources are not set we will not process event */
            String spmAffectedServices = (event.getParm("spmAffectedServices") == null) ? null : event.getParm("spmAffectedServices").getValue().getContent();
            String[] affectedServices = ( (spmAffectedServices==null) ? new String[] {} : spmAffectedServices.split(","));
            log.debug("createServiceProblem spmAffectedServices="+spmAffectedServices);

            String spmAffectedResources = (event.getParm("spmAffectedResources") == null) ? null : event.getParm("spmAffectedResources").getValue().getContent();
            String[] affectedResources = ( (spmAffectedResources==null) ? new String[] {} : spmAffectedResources.split(","));
            log.debug("createServiceProblem spmAffectedResources="+spmAffectedResources);

            /* may be in events if created by an incoming message */
            String spmHREF = (event.getParm("spmHREF") == null) ? null : event.getParm("spmHREF").getValue().getContent();
            String spmID = (event.getParm("spmID") == null) ? null : event.getParm("spmID").getValue().getContent();
            String spmOriginatingSystem = (event.getParm("spmOriginatingSystem") == null) ? null : event.getParm("spmOriginatingSystem").getValue().getContent();
            String spmStatus = (event.getParm("spmStatus") == null) ? null : event.getParm("spmStatus").getValue().getContent();
            String spmPriority = (event.getParm("spmPriority") == null) ? null : event.getParm("spmPriority").getValue().getContent();

            /* only create new service problem if href not present and affectedServices or affectedResources defined */
            if ( spmHREF != null ) {
            	log.debug("createServiceProblem not creating new service problem as event has spmHREF=" + spmHREF);
            	return;
            }

            if ( affectedServices.length == 0 && affectedResources.length == 0 ) {
            	log.debug("createServiceProblem not creating new service problem as spmAffectedServices or spmAffectedResources not defined in bsm business service");
                return;
            } else {
                log.debug("createServiceProblem event has no spmHREF param and spmAffectedServices or spmAffectedResources are defined - creating new service problem");

                String originatingSystem = m_thisOriginatingSystem;
                String category = "equipment";
                String priority = "1";
                String reason = logmsgStr;
                String correlationId = reductionKey;

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

                        JSONObject serviceProblem = createMinimalServiceProblem(originatingSystem, category, priority, description, reason, correlationId,
                                affectedServices, affectedResources);
                        log.debug("createServiceProblem sending service problem : " + serviceProblem.toString());
                        String url = baseUrl + "/tmf-api/serviceProblemManagement/v3/serviceProblem";

                        /* post http request */
                        m_scriptedClient.postRequest(url, serviceProblem.toString(), username, password);

                    } catch (Exception e2) {
                        log.error("createServiceProblem problem posting new service problem ", e2);
                    }

                }
            } 

        } catch (Exception e) {
            log.debug("updateServiceProblem problem creating service problem", e);
        }

    }

    @Override
    public synchronized void handleIncomingMessage(JSONObject message) {

    	log.debug("handleIncomingMessage called,  message=" + message);

    	String messageSource = (String) message.get("messageSource");
    	String requestMethod = (String) message.get("requestMethod");
    	String requestHost = (String) message.get("requestHost");
    	String requestPath = (String) message.get("requestPath");
    	String requestRawUrl = (String) message.get("requestRawUrl");
    	String status = (message.get("status") == null) ? null : message.get("status").toString();
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

    			/* check for reply to register for events */
    			if (requestPath != null && requestPath.contains("/tmf-api/serviceProblemManagement/v3/hub") && "POST".equals(requestMethod)
    					&& jsonobject != null) {

    				log.debug("successfully registered for messages: " + requestPath + " reply:" + jsonobject.toString() + " requestRawUrl=" + requestRawUrl);
    				String id = (String) jsonobject.get("id");
    				String url = requestRawUrl.substring(0, requestRawUrl.indexOf("/tmf-api/serviceProblemManagement/v3/hub"));
    				log.debug("registering listener for : url=" + url + " id=" + id);
    				m_registered_listeners.put(url, id);
    			}

    			/* check for reply to create service problem POST /tmf-api/serviceProblemManagement/v3/serviceProblem */
    			else if (requestPath != null && requestPath.contains("/tmf-api/serviceProblemManagement/v3/serviceProblem") && "POST".equals(requestMethod)
    					&& jsonobject != null) {

    				String uei = SERVICE_PROBLEM_REPLY_UEI;
    				Event event = onmsEventFromServiceProblem(uei, jsonobject);

    				/* update alarm with same correlationId as spm with spm details in event */
    				if (event.getParm("spmCorrelationId")!=null) {
                        Map details = new HashMap();
    					
    					String reductionKey = event.getParm("spmCorrelationId").getValue().toString();
    					details.put("spmCorrelationId",reductionKey);
    					
    					if (event.getParm("spmID")!=null) {
    						details.put("spmID", event.getParm("spmID").getValue().toString());
    					}
    					if (event.getParm("spmHREF")!=null) {
    						details.put("spmHREF", event.getParm("spmHREF").getValue().toString());
    					}
    					log.debug("ServiceProblem Reply : updating OpenNMS alarm details for reductionKey ="+reductionKey);
    	                try {
    						updateAlarmDetails(reductionKey, details);
   	                    } catch (Throwable t) {
    						log.debug("problem updatingAlarmDetails", t);
   	                    }
    				}

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
    		String spmOriginatingSystem = null;
    		String spmStatus = null;
    		Long spmPriority = null;
    		if (jsonobject != null) {
    			try {
    				spmEventType = (String) jsonobject.get("eventType");
    				JSONObject spmEvent = (JSONObject) jsonobject.get("event");
    				spmServiceProblem = (spmEvent == null) ? null : (JSONObject) spmEvent.get("serviceProblem");
    				spmServiceProblemId = (spmServiceProblem == null) ? null : (String) spmServiceProblem.get("id");
    				spmOriginatingSystem = (spmServiceProblem == null) ? null : (String) spmServiceProblem.get("originatingSystem");
    				spmStatus = (spmServiceProblem == null) ? null : (String) spmServiceProblem.get("status");
    				spmPriority = (spmServiceProblem == null) ? null : (Long) spmServiceProblem.get("priority");
    			} catch (Exception e) {
    				log.error("problem reading message posted to httpServer", e);
    			}
    		}
    		if (spmEventType == null || spmServiceProblem == null || spmServiceProblemId == null) {
    			log.debug("cannot recognise message as SPM event."
    					+ " spmEventType: " + spmEventType 
    					+ " spmServiceProblemId: " + spmServiceProblemId
    					+ " spmServiceProblem: " + spmServiceProblem 
    					+ " Message: " + message.toString());
    		} else {

    			String uei = null;
    			Event event = null;

    			switch (spmEventType) {
    			/* TMF SPM Service Problem event types */
    			case SERVICE_PROBLEM_CREATE_NOTIFICATION:
    				if (m_thisOriginatingSystem.equals(spmOriginatingSystem)) {
    					log.debug("not handling spm create notification which carries a create from our own originatingSystem=" + spmOriginatingSystem);
    					break;
    				}
    				uei = SERVICE_PROBLEM_UEI;
    				event = onmsEventFromServiceProblem(uei, spmServiceProblem);
    				log.debug("Persisting event to OpenNMS:" + event.toString());
    				try {
    					EventIpcManagerFactory.getIpcManager().sendNow(event);
    					log.debug("sent SERVICE_PROBLEM_CREATE_NOTIFICATION event through ipcManager");
    				} catch (Throwable t) {
    					log.debug("problem sending event to OpenNMS:", t);
    				}
    				break;
    			case SERVICE_PROBLEM_STATE_CHANGE_NOTIFICATION:
    				// check if state is now Closed or Cancelled
    				if("Closed".equals(spmStatus) || "Cancelled".equals(spmStatus) ) {
    					uei = SERVICE_PROBLEM_CLOSED_CANCELLED_OR_DELETED_UEI;
    					event = onmsEventFromServiceProblem(uei, spmServiceProblem);
    					event.setSeverity("Cleared");
    				} else {
    					uei = SERVICE_PROBLEM_STATE_CHANGE_UEI;
    					event = onmsEventFromServiceProblem(uei, spmServiceProblem);
    				}

    				log.debug("Persisting event to OpenNMS:" + event.toString());
    				try {
    					EventIpcManagerFactory.getIpcManager().sendNow(event);
    					log.debug("received SERVICE_PROBLEM_STATE_CHANGE_NOTIFICATION sent "+uei+" event through ipcManager");
    				} catch (Throwable t) {
    					log.debug("problem sending event to OpenNMS:", t);
    				}
    				break;
    			case SERVICE_PROBLEM_ATTRIBUTE_VALUE_CHANGE_NOTIFICATION:
    				// check if state is now Closed or Cancelled
    				if("Closed".equals(spmStatus) || "Cancelled".equals(spmStatus) ) {
    					uei = SERVICE_PROBLEM_CLOSED_CANCELLED_OR_DELETED_UEI;
    					event = onmsEventFromServiceProblem(uei, spmServiceProblem);
    					event.setSeverity("Cleared");
    				} else {
    					uei = SERVICE_PROBLEM_ATTRIBUTE_VALUE_CHANGE_UEI;
    					event = onmsEventFromServiceProblem(uei, spmServiceProblem);
    				}

    				log.debug("Persisting event to OpenNMS:" + event.toString());
    				try {
    					EventIpcManagerFactory.getIpcManager().sendNow(event);
    					log.debug("received SERVICE_PROBLEM_ATTRIBUTE_VALUE_CHANGE_NOTIFICATION sent "+uei+" event through ipcManager");
    				} catch (Throwable t) {
    					log.debug("problem sending event to OpenNMS:", t);
    				}
    				break;
    			case SERVICE_PROBLEM_INFORMATION_REQUIRED_NOTIFICATION:
    				uei = SERVICE_PROBLEM_INFORMATION_REQUIRED_UEI;
    				event = onmsEventFromServiceProblem(uei, spmServiceProblem);
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
    		log.debug("message received from unknown internal source:" + messageSource + " message: " + message.toString());
    	}

    }

    public void startRegisterNotifications(List<UrlCredential> notificationCredentials, UrlCredential callbackCredential) {
        log.debug("starting notification registration process");
        if (notificationCredentials == null || notificationCredentials.size() == 0 || callbackCredential == null) {
            log.error("UrlCredential[] notificationCredentials is null or empty. Cannot register for notifications");
            return;
        }
        if (callbackCredential == null || callbackCredential.getUrl() == null) {
            log.error("callback credential is null or callback url is null. Cannot register for notifications");
            return;
        }
        try {
            URL u = new URL(callbackCredential.getUrl());
        } catch (MalformedURLException e) {
            log.error("callbackCredential.getUrl() malformed url=" + callbackCredential.getUrl());
            return;
        }
        for (UrlCredential notificationCredential : notificationCredentials) {
            String url = notificationCredential.getUrl();
            try {
                URL u = new URL(url);
            } catch (MalformedURLException e) {
                log.error("notificationCredential[] notificationCredential malformed url=" + url);
                return;
            }
        }

        m_notificationCredentials = notificationCredentials;

        /* run as daemon so that we definitely exit */
        ThreadFactory threadFactory = new ThreadFactory() {
            public Thread newThread(Runnable r) {
                Thread t = Executors.defaultThreadFactory().newThread(r);
                t.setDaemon(true);
                return t;
            }
        };

        m_scheduledExecutorService = Executors.newScheduledThreadPool(1, threadFactory);

        /* using runnable because of beanshell */
        Runnable ttask = new Runnable() {

            public void run() {
                log.debug("credentialTimer trying to register for notifications m_registered_listeners.size()=" + m_registered_listeners.size()
                        + " m_notificationCredentials.size()=" + m_notificationCredentials.size());
                if (m_registered_listeners.size() >= m_notificationCredentials.size()) {
                    log.debug("all notifications registered. Ending timer");
                    m_scheduledExecutorService.shutdown();
                    return;
                } else try {
                    log.debug("still unregisterd credentials. Trying registration");
                    for (UrlCredential urlCredential : m_notificationCredentials) {
                        String url = urlCredential.getUrl();
                        String query = urlCredential.getQuery();
                        if (!m_registered_listeners.containsKey(url)) {
                            log.debug("trying to register for notification: server url=" + url + " callbackUrl=" + callbackCredential.getUrl()
                                + " query=" + query);
                            try {
                                registerForNotifications(urlCredential, callbackCredential, query);
                            } catch (Exception ex) {
                                log.error("problem registering url=" + url + ", query=" + query + " for notifications", ex);
                            }
                        }
                    }
                } catch (Exception ex) {
                    log.error("problem registering for notifications", ex);
                }
            }
        };

        /* run every 30 seconds until all urls registered */
        m_scheduledExecutorService.scheduleAtFixedRate(ttask, 0, 30, TimeUnit.SECONDS);

    }

    public void stopRegisterNotifications() {
        log.debug("stopping notification registration process for " + m_notificationCredentials.size() + " credentials");
        if (m_scheduledExecutorService != null)
            m_scheduledExecutorService.shutdownNow();
        try {
            for (UrlCredential urlCredential : m_notificationCredentials) {
                String url = urlCredential.getUrl();
                String id = m_registered_listeners.get(url);
                if (id != null) {
                    log.debug("unregistering for notification: server url=" + url + " registration id=" + id);
                    try {
                        unRegisterForNotifications(urlCredential, id);
                    } catch (Exception ex) {
                        log.error("problem un-registering for notifications", ex);
                    }
                }
            }
            m_registered_listeners.clear();
        } catch (Exception ex) {
            log.error("problem un-registering for notifications", ex);
        }

    }

    private void unRegisterForNotifications(UrlCredential urlCredential, String id) {
        m_scriptedClient.deleteRequest(urlCredential.getUrl() + "/tmf-api/serviceProblemManagement/v3/hub" + "/" + id, urlCredential.getUsername(),
                urlCredential.getPassword());
    }

    private void registerForNotifications(UrlCredential urlCredential, UrlCredential callback, String query) {
        JSONObject hubRequest = new JSONObject();
        hubRequest.put("callback", callback.getUrl());
        hubRequest.put("query", query);
        m_scriptedClient.postRequest(urlCredential.getUrl() + "/tmf-api/serviceProblemManagement/v3/hub", hubRequest.toString(), 
        		urlCredential.getUsername(), urlCredential.getPassword());
    }
    
    public void updateAlarmDetails(String reductionKey, Map details) {
    	BeanFactoryReference bf = BeanUtils.getBeanFactory("daoContext");
    	final AlarmDao alarmDao = BeanUtils.getBean(bf,"alarmDao", AlarmDao.class);
    	TransactionTemplate transTemplate = BeanUtils.getBean(bf, "transactionTemplate",TransactionTemplate.class);

    	/* not using typed or anonymous callback because of beanshell */
    	TransactionCallbackWithoutResult transactionCallbackWithoutResult = new TransactionCallbackWithoutResult() {
    		
    		{
    		   log.debug("updateAlarmDetails created new transactionCallbackWithoutResult");
    		}
    		
    		@Override
    		public void doInTransactionWithoutResult(final TransactionStatus status) {
    			try { 
    				OnmsAlarm onmsAlarm = alarmDao.findByReductionKey(reductionKey);
    				if (onmsAlarm!=null) {
    					/* not using generics because not supported in beanshell */
    					Map alarmDetails = onmsAlarm.getDetails();
    					Iterator alarmDetailsIterator = alarmDetails.keySet().iterator();
    					while(alarmDetailsIterator.hasNext()) {
    						String detailKey = (String) alarmDetailsIterator.next();
    						String detailValue=(String) details.get(detailKey);
    						log.debug("updateAlarmDetails updating alarm with reductionKey="+reductionKey + " with new detail: detailKey="+detailKey+" detailValue="+detailValue);
    						alarmDetails.put(detailKey, detailValue);
    					}
    					alarmDao.update(onmsAlarm);
    					alarmDao.flush();
    					log.debug("updateAlarmDetails updated alarm with reductionKey="+reductionKey + " alarm.toString()="+onmsAlarm.toString());
    				} else {
    					log.debug("updateAlarmDetails cannot find alarm with reductionKey="+reductionKey );
    				}
    			} catch (RuntimeException e) {
    				log.error("updateAlarmDetails problem within transaction:",e);
    			}
    		}
    	};

    	try {
    		log.debug("updateAlarmDetails executing transactionCallbackWithoutResult");
    		transTemplate.execute(transactionCallbackWithoutResult);
    		log.debug("updateAlarmDetails return from transactionCallbackWithoutResult");
    	} catch (RuntimeException e) {
    		log.error("updateAlarmDetails problem calling transaction",e);
    	}

    }

    /* returns this beanshell declaration so that its methods can be invoked */
    /* return this; */

}
