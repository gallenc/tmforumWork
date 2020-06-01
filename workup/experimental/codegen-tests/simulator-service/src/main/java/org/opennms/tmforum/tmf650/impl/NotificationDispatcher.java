package org.opennms.tmforum.tmf650.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.opennms.tmforum.tmf650.model.AllSubscriptionStatistics;
import org.opennms.tmforum.tmf650.model.GenericEventSubscription;
import org.opennms.tmforum.tmf650.model.GenericEventSubscriptionInput;
import org.opennms.tmforum.tmf650.model.SubscriptionStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationDispatcher {
    private static Logger LOG = LoggerFactory.getLogger(NotificationDispatcher.class);

    private int timeout = 5000; // 5 seconds
    private long maxConsecutiveErrors = 3;

    private final AtomicLong messageCount = new AtomicLong();

    private Map<String, NotificationPoster> dispatcherMap = new ConcurrentHashMap<String, NotificationPoster>();

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public long getMaxConsecutiveErrors() {
        return maxConsecutiveErrors;
    }

    public void setMaxConsecutiveErrors(long maxConsecutiveErrors) {
        this.maxConsecutiveErrors = maxConsecutiveErrors;
    }

    public void unregisterListener(String subscriptionId) {
        LOG.debug("unregistering listener subscriptionId=" + subscriptionId);
        NotificationPoster notificationPoster = dispatcherMap.remove(subscriptionId);
        if (notificationPoster != null) {
            notificationPoster.shutdown();
        }
    }

    public synchronized GenericEventSubscription registerListener(GenericEventSubscriptionInput subscription) {
        GenericEventSubscription eventSubscription = new GenericEventSubscription();
        String callback = subscription.getCallback();
        String query = subscription.getQuery();

        String id = UUID.randomUUID().toString();

        URI targetUri;
        try {
            targetUri = new URI(callback);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("cannot parse callback " + callback, e);
        }

        LOG.debug("registering new listener subscriptionId=" + id + " callback=" + callback + "query=" + query);

        NotificationPoster notificationPoster = new GenericRestNotificationPoster(targetUri, timeout,
                maxConsecutiveErrors, query, id, this);

        dispatcherMap.put(id, notificationPoster);

        eventSubscription.setCallback(callback);
        eventSubscription.setId(id);
        eventSubscription.setQuery(query);
        return eventSubscription;

    }

    public void sendNotification(Object notification) {
        long count = messageCount.incrementAndGet();
        LOG.debug("sending notification (" + count + ") " + notification.toString());

        for (NotificationPoster notificationPoster : dispatcherMap.values()) {
            try {
                notificationPoster.postNotification(notification);
            } catch (Exception ex) {
                LOG.error("problem sending notification to subscription " + notificationPoster.getSubscriptionId(), ex);
            }
        }

    }

    public synchronized void shutdown() {
        LOG.debug("shutting down all subscriptions");
        for (NotificationPoster notificationPoster : dispatcherMap.values()) {
            notificationPoster.shutdown();
            dispatcherMap.remove(notificationPoster.getSubscriptionId());
        }
        dispatcherMap.clear();

    }
    
    public AllSubscriptionStatistics getAllSubscriptionStatistics() {
        AllSubscriptionStatistics allSubscriptionStatistics = new AllSubscriptionStatistics();
        
        for (NotificationPoster notificationPoster:dispatcherMap.values()) {
            SubscriptionStatistics stats = notificationPoster.getSubscriptionStatistics();
            allSubscriptionStatistics.getAllSubscriptionStatistics().add(stats);
        }

        return allSubscriptionStatistics;
    }

}
