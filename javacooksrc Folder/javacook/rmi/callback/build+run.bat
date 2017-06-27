# Build the class files
javac -d . *.java
# Build the stubs for the Registrar
rmic  -d . com.darwinsys.callback.TickerServerImpl
# Build the stubs for the Client Agent
rmic  -d . com.darwinsys.client.ClientProgram
# detached: use "start" on M$ and delete it, use "&" at the end, on *NIX
# Run the RMI Registry
start rmiregistry
# Run the Registrar Server
start java ServerMain
# Run the client (in a different window)
start java com.darwinsys.client.ClientProgram
