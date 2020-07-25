# Scriptd based Opennms Service problem API integration

## installation
copy the files in this folder (as root)  to the following locations, replacing opennms.bsm.events.xml and scriptd-configuration.xml which already exist. 


```
sudo cp scriptd-configuration.xml /opt/opennms/etc/
sudo cp scriptedApacheHttpAsyncClient.bsh /opt/opennms/etc/
sudo cp scriptedEventSPMForwarder.bsh /opt/opennms/etc/
sudo cp opennms.bsm.events.xml /opt/opennms/etc/events/

```

## setting usernames / passwords and urls

To use basic authentication, usernames and passwords should be set in the scriptd-configuration.xml file by changing the variables "username", "password".
if username is null or empty (i.e "") then no basic authentication is used for the given url.

Both http and https URLs can be used.

```
/* set urls and credentials for spm hosts UrlCredential(String url, String username, String password) */
      url1= urlCredential("http://tmf656-test1.centralus.cloudapp.azure.com:8080/tmf656-spm-simulator-war", "username", "password" );
```


```
UsernamePasswordCredentials creds = new UsernamePasswordCredentials("username", "password");
```

Once you have set the URL and usernames and passwords then Restart OpenNMS. 
Wait for OpenNMS to stop properly before restarting
```
\opt\opennms\bin\opennms stop
\opt\opennms\bin\opennms start
```
You can check the status using 
```
\opt\opennms\bin\opennms -v status
```

Logs for scriptd can be seen by firstly changing the log4j scriptd setting to DEBUG 
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
