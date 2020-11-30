# Scriptd based Opennms Service problem API integration

## installation

Ensure OpenNMS is stopped before installation. 

```
sudo \opt\opennms\bin\opennms stop
```


Before installing you are advised to save an original copy of scriptd-configuration.xml and opennms.bsm.events.xml so that if needed you can restore the configuration without this feature.
(Note that virgin configuration files are also held in /opt/opennms/share/etc-pristine if you need to restore anything)

```
sudo cp /opt/opennms/etc/scriptd-configuration.xml /opt/opennms/etc/scriptd-configuration.xml.old
sudo cp /opt/opennms/etc/events/opennms.bsm.events.xml /opt/opennms/etc/events/opennms.bsm.events.xml.old
```

copy the files in this folder (as root)  to the following locations, replacing opennms.bsm.events.xml and scriptd-configuration.xml which already exist. 


```
sudo cp scriptd-configuration.xml /opt/opennms/etc/
sudo cp scriptedApacheHttpAsyncClient.bsh /opt/opennms/etc/
sudo cp scriptedEventSPMForwarder.bsh /opt/opennms/etc/
sudo cp scriptedApacheHttpServer.bsh /opt/opennms/etc/
sudo cp opennms.bsm.events.xml /opt/opennms/etc/events/

```

## setting usernames / passwords and urls for connecting to SPM server

To use basic authentication, usernames and passwords should be set in the scriptd-configuration.xml file by changing the variables "username", "password".
if username is null or empty (i.e "") then no basic authentication is used for the given url.

Both http and https URLs can be used.

```
/* set urls and credentials for spm hosts UrlCredential(String url, String username, String password) */
      url1= urlCredential("http://tmf656-test1.centralus.cloudapp.azure.com:8080/tmf656-simulator-war", "username", "password" );
```
For the reply events, you need to set up the incoming HTTP server.
The port is set by changing the following line. 

```
      int port=8981;
```

Make sure your chosen port is opened in the firewall and doesn't clash with any other service.

```
sudo firewall-cmd --zone=public --add-port=8981/tcp --permanent
sudo firewall-cmd --reload
sudo firewall-cmd --list-all
```
The scripted http server handles all incoming requests to allowed urls by mapping them into a json message which is queued for processing. 
You must indicate the allowed target url's to which messages can be sent.
Do this by adding to or changing the allowedTargets array.
If you are running https, you need to give the absolute path to the key store in keyStoreFileLocation. 
If this is not set, https requests will not respond.

```
      String[] allowedTargets = {"/",
                "/opennms/tmf-api/serviceProblemManagement/v3/listener/serviceProblemAttributeValueChangeNotification",
                "/opennms/tmf-api/serviceProblemManagement/v3/listener/serviceProblemCreateNotification",
                "/opennms/tmf-api/serviceProblemManagement/v3/listener/serviceProblemInformationRequiredNotification",
                "/opennms/tmf-api/serviceProblemManagement/v3//listener/serviceProblemStateChangeNotification",
                "/generic-listener/notification"
                };
      String keyStoreFileLocation = null;
```

Once you have set the the above settings then Restart OpenNMS. 

(note that you can also use systemd to stop and start the opennms service but doing a direct call to the stop and start scripts as described here gives you more visibility when testing)

Wait for OpenNMS to stop properly before restarting.

```
sudo \opt\opennms\bin\opennms stop
sudo \opt\opennms\bin\opennms start
```

You can check the status using 

```
sudo \opt\opennms\bin\opennms -v status
```
You can also check opennms has really stopped by checking the processes using

```
sudo ps -aux | grep opennms
```

Logs for this scriptd configuration can be seen by changing the log4j scriptd setting to DEBUG
 
```
\opt\opennms\etc\log4j2.xml

<KeyValuePair key="scriptd"              value="DEBUG" />

```

Logs are in

```
\opt\opennms\logs\scriptd.log
```

To see logs as they occur use

```
tail -f \opt\opennms\logs\scriptd.log 
```

# setting up business service monitoring in OpenNMS
Any defined business service can generate a service problem.
However you must include spmAffectedResources or spmAffectedServices as attribute in definition

spmAffectedServices must be set by bsm business service attributes as comma separated variables with no spaces

spmAffectedResources must be set by bsm business service attributes as comma separated variables with no spaces

if spmAffectedServices or spmAffectedResources are not set we will not process event
