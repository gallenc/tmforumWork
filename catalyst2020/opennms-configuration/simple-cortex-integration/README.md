# Simple OpenNMS Integration with Cortex

This capability has been tested with OpenNMS Release 27.x It should work with other releases provided the OpenNMS API is not changed.

## Introduction

This is a very simple script which posts messages to cortex based on link down and link up events from nodes in OpenNMS. 

It uses OpenNMS scriptd [https://wiki.opennms.org/wiki/Scriptd](https://wiki.opennms.org/wiki/Scriptd) 

The script sets up an apache asynchronous http client to POST messages to cortex.
An asynchronous http client is used because the scriptd script expects scripts to return quickly from handling an event.
There is however no guarantee of a quick acknowledgement from cortex so the async client will spawn new requests for each event sent and wait up to 20 seconds for a reply to each message sent. 
In this example the successful replies are simply logged and not used by OpenNMS.

The following JSON messages are posted

### Example Link down message sent to Cortex

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

### Example Link up message sent to Cortex

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

## installation

Ensure OpenNMS is stopped before installation. 

```
sudo /opt/opennms/bin/opennms stop
```

Before installing you are advised to save an original copy of scriptd-configuration.xml so that if needed you can restore the configuration without this feature.
(Note that virgin configuration files are also held in /opt/opennms/share/etc-pristine if you need to restore anything)

```
sudo cp /opt/opennms/etc/scriptd-configuration.xml /opt/opennms/etc/scriptd-configuration.xml.old
sudo cp scriptd-configuration.xml /opt/opennms/etc/

sudo cp scriptedEventSPMForwarder.bsh /opt/opennms/etc/

```

You will need to change the url and username / password to access the cortex system. 
Change the following lines in [scriptd-configuration.xml](../simple-cortex-integration/scriptd-configuration.xml)
```
     /* cortex credentials */
      cortexUrl="https://org.domainname.cortex:10000/api/flow/startflowAsync";
      cortexUsername="username";
      cortexPassword="password";
```

Logs for this scriptd configuration can be seen by changing the OpenNMS log4j scriptd setting to DEBUG
 
```
# edit this file
/opt/opennms/etc/log4j2.xml

# change this line
<KeyValuePair key="scriptd" value="DEBUG" />

```

Scriptd Logs are in

```
/opt/opennms/logs/scriptd.log
```

Switching on DEBUG will enable you to see messages and any errors as they are posted.

To see logs as they occur use

```
tail -f /opt/opennms/logs/scriptd.log 
```

After installing the script, start OpenNMS using
```
sudo /opt/opennms/bin/opennms start
```

## Testing

If you are unable to generate link down and up events by genuine link down and up traps sent to OpenNMS ( possibly from equipment or an SNMP simulator)
it is possible to inject events directly to OpenNMS directly using the ReST api documented in the [OpenNMS 27 Developer Guide](https://docs.opennms.org/opennms/releases/27.0.2/guide-development/guide-development.html#_events).
See [using a Rester client to post link up and link down events](../simple-cortex-integration/testScripts/README.MD)





