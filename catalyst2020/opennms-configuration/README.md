# OpenNMS Integration

In these examples, the standard OpenNMS Scriptd feature (https://wiki.opennms.org/wiki/Scriptd) has been used.

This folder contains both   [a simple HTTP post integration](../opennms-configuration/simple-cortex-integration) using Scriptd
 and [a TMF656 integration](../opennms-configuration/tmf-656-integration) also using Scriptd

In both cases the ApacheHTTPAsyncClient (https://hc.apache.org/httpcomponents-asyncclient-dev/index.html) has been leveraged to allow
 scriptd events to start long running interactions with the server in case the server is slow in responding.

