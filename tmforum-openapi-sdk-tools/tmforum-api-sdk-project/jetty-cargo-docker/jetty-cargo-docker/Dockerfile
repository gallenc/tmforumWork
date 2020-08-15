# Java, Jetty and Cargo
# uses official jetty docker https://hub.docker.com/_/jetty
# VERSION 0.0.1

MAINTAINER Craig Gallen "craig.gallen@solent.ac.uk"

FROM jetty:9.4.31-jdk11

# Install Cargo
ADD https://repo1.maven.org/maven2/org/codehaus/cargo/cargo-jetty-7-and-onwards-deployer/1.8.0/cargo-jetty-7-and-onwards-deployer-1.8.0.war /var/lib/jetty/webapps/cargo-jetty-7-and-onwards-deployer-1.8.0.war

# Run Jetty

USER jetty
EXPOSE 8080
ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["java","-jar","/usr/local/jetty/start.jar"]