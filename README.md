### Projet Upup 

this project has been made to tested/play with the UPNP protocol.
I'm more interested in the device which supports DIAL (Discovery and Launch www.dial-multiscreen.org).

DIAL is a simple protocol that second-screen devices can use to discover and launch apps on first-screen devices. You my test have use the Youtube app on my chromecast and on my smart TV

For the fun, I wrote an application that launches the Nyan Cat video on all devices, and supporting the protocol DIAL and have the YouTube app

### Version
1.0

### Test
to test :
mvn compile test-compile
mvn exec:java -Dexec.mainClass="com.xseillier.upnp.NynaCatLauncher" -Dexec.classpathScope="test"