# Simple OpenNMS Integration with Cortex

This is a a simple script which posts messages to cortex based on link down and link up events from nodes in OpenNMS. 

It uses OpenMMS scriptd [https://wiki.opennms.org/wiki/Scriptd](https://wiki.opennms.org/wiki/Scriptd) 

The script sets up an apache asynchronous http client to POST messages to cortex.
An asynchronous http client is used because the scriptd script expects scripts to return quickly from handling an event.
There is however no guarantee of a quick acknowledgement from cortex so the async client will spawn new requests for each event sent and wait up to 20 seconds for a reply to each message sent. 
In this example the successful replies are simply logged and not used.

The following JSON messages are posted

## Link down message

```

Messages sent to cortexA
POST /api/flow/startflowAsync HTTP/1.1
Host: 3.15.141.111:10000
Content-Type: application/json
Authorization: Basic xxx
{
  "NAME": "Catalyst-Link-Down",
  "ARGUMENTS": {
    "DEVICE-ID" : "192.168.102.231",
    "PORT-ID" : "Fa0/0"
  }
}
```
## Link up message

```
POST /api/flow/startflowAsync HTTP/1.1
Host: 3.15.141.111:10000
Content-Type: application/json
Authorization: Basic Q29ydGV4RmxvdzpDMHJ0M3hGbDB3
{
  "NAME": "Catalyst-Link-Up",
  "ARGUMENTS": {
    "DEVICE-ID" : "192.168.102.231",
    "PORT-ID" : "Fa0/0"
  }
}
```