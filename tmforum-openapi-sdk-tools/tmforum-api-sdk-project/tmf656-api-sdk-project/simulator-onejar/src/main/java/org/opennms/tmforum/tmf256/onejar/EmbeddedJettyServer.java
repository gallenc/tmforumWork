package org.opennms.tmforum.tmf256.onejar;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URI;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

// see https://stackoverflow.com/questions/2458440/executable-war-file-that-starts-jetty-without-maven

public final class EmbeddedJettyServer {
	public static void main(String[] args) throws Exception {
		
		int port = Integer.parseInt(System.getProperty("port", "8080"));
        Server server = new Server(port);

        ProtectionDomain domain = EmbeddedJettyServer.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();
        System.out.println("location: "+location.toExternalForm());

        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setDescriptor(location.toExternalForm() + "!/webapp-war/WEB-INF/web.xml");
        webapp.setServer(server);
        webapp.setWar(location.toExternalForm()+"!/webapp-war");

        // (Optional) Set the directory the war will extract to.
        // If not set, java.io.tmpdir will be used, which can cause problems
        // if the temp directory gets cleaned periodically.
        // Your build scripts should remove this directory between deployments
        webapp.setTempDirectory(new File("/path/to/webapp-directory"));

        server.setHandler(webapp);
        server.start();
        server.join();
		
		
//		int port = Integer.parseInt(System.getProperty("port", "8080"));
//		Server server = new Server(port);
//
//		/// ProtectionDomain domain = EmbeddedJettyServer.class.getProtectionDomain();
//		// URI location = domain.getCodeSource().getLocation().toURI();
//
//		ProtectionDomain domain = EmbeddedJettyServer.class.getProtectionDomain();
//		URL location = domain.getCodeSource().getLocation();
//		System.out.println("location is : " + location.getPath());
//		
//		String path = location.getPath();
//		
//		String filePath = path.substring(0, path.indexOf("!"));
//		
//		//String filePath = location.getPath().replaceAll("file:/", ""); 
//        System.out.println("Modified filepath - " + filePath);
//
//        URL fileURL = new File(filePath).toURI().toURL();
//        String url = fileURL.getPath();

		// WebAppContext webapp = new WebAppContext();
		// webapp.setContextPath("/");
		// webapp.setDescriptor(location.toExternalForm() + "/WEB-INF/web.xml");
		// webapp.setServer(server);
		// webapp.setWar(location.toExternalForm());

		//String s = location.toExternalForm();
		//System.out.println("s is : " + s);

		// String s =
		// EmbeddedJettyServer.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		// System.out.println("s is : "+s);

//		File jarFile = new File(url);
//		System.out.println("jarFile is : " + jarFile.getAbsolutePath());
//
//		String actualFile = jarFile.getParentFile().getAbsolutePath() + File.separator + "Name_Of_Jar_File.jar";
//
//		System.out.println("actulaFilePath is : " + actualFile);
//		final JarFile jar = new JarFile(actualFile);
//		final Enumeration<JarEntry> entries = jar.entries(); // gives ALL entries in jar
//		System.out.println("Reading entries in jar file ");
//		while (entries.hasMoreElements()) {
//			JarEntry jarEntry = entries.nextElement();
//			final String name = jarEntry.getName();
//			if (name.startsWith("Might Specify a folder name you are searching for")) { // filter according to the path
//				System.out.println("file name is " + name);
//				System.out.println("is directory : " + jarEntry.isDirectory());
//				File scriptsFile = new File(name);
//				System.out.println("file names are : " + scriptsFile.getAbsolutePath());
//
//			}
//		}
//		jar.close();

//		File dir = new File(location);
//		System.out.println("**************** dir "+ dir );
//		JarFile jar = new JarFile(dir);
//		Enumeration<? extends JarEntry> enumeration = jar.entries();
//		while (enumeration.hasMoreElements()) {
//			
//			ZipEntry zipEntry = enumeration.nextElement();
//			 // Relative path of file into the jar.
//            String filename = zipEntry.getName();
//			
//			System.out.println("**************** libDirectory "+ filename );
//		}
//		

//		String s = location.toExternalForm();
//		
//		String parentJar = location.toExternalForm().substring(0, s.indexOf("!")).replace("jar:", "");
//		System.out.println("************ parentJar "+ parentJar );
//		JarFile jar = new JarFile(parentJar);
//		Enumeration<? extends JarEntry> enumeration = jar.entries();
//		while (enumeration.hasMoreElements()) {
//			
//			ZipEntry zipEntry = enumeration.nextElement();
//			 // Relative path of file into the jar.
//            String filename = zipEntry.getName();
//			
//			System.out.println("**************** libDirectory "+ filename );
//		}

		// String libDirectory = location.toExternalForm().substring(0, s.indexOf("!"))
		// + "!/main/";
		// System.out.println("**************** libDirectory "+libDirectory );
		// jar:file:/C:/devel/gitrepos/tmforumWork/tmforum-openapi-sdk-tools
		// tmforum-api-sdk-project/tmf656-api-sdk-project/simulator-onejar
		// target/tmf656-simulator-onejar-onejar.jar!/main/tmf656-simulator-onejar-0.0.1-SNAPSHOT.jar

		// File dir = new File(libDirectory);

		// File[] matches = dir.listFiles();
		// System.out.println("File matche: " +matches);

//		File[] matches = dir.listFiles(new FilenameFilter() {
//			public boolean accept(File dir, String name) {
//				return name.endsWith(".war");
//			}
//		});

//		File war = matches[0];
//		String warLocation = war.getAbsolutePath();
//		System.out.println("War File Loading: " + warLocation);
//
//		WebAppContext webapp = new WebAppContext();
//
//		webapp.setContextPath("/");
//		// webapp.setDescriptor(location.toExternalForm() + "/WEB-INF/web.xml");
//		webapp.setServer(server);

//		webapp.setWar(warLocation);

		// (Optional) Set the directory the war will extract to.
		// If not set, java.io.tmpdir will be used, which can cause problems
		// if the temp directory gets cleaned periodically.
		// Your build scripts should remove this directory between deployments
		// webapp.setTempDirectory(new File("/path/to/webapp-directory"));
		// File tmp = webapp.getTempDirectory();
		// System.out.println("**************** " + ((tmp == null) ? null :
		// tmp.getAbsolutePath()));

//		server.setHandler(webapp);
//		server.start();
//		server.join();
	}
}