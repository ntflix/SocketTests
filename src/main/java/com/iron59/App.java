package com.iron59;

import org.apache.commons.cli.*;

public class App 
{
    public static ServerInfo getInputs(String[] args) {
        // spaghetti code alert ⚠️

        Options options = new Options();

        Option server = OptionBuilder.withArgName("server").hasArg().withDescription("Server IP or hostname").create("server");
        server.setRequired(false);
        options.addOption(server);

        Option port = OptionBuilder.withArgName("port").hasArg().withDescription("Server port").create("port");
        port.setRequired(false);
        options.addOption(port);

        Option mode = OptionBuilder.withArgName("mode").hasArg().withDescription("Mode (client or server)").create("mode");
        mode.setRequired(true);
        options.addOption(mode);

        CommandLineParser parser = new GnuParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
            e.printStackTrace();
            throw new RuntimeException("This should never happen"); // lol
        }

        String serverValue = cmd.getOptionValue("server");
        String portValue = cmd.getOptionValue("port");
        String modeValue = cmd.getOptionValue("mode");

        if (modeValue.equals("server")) {
            if (portValue == null) {
                System.out.println("Port is required for server mode");
                formatter.printHelp("utility-name", options);
                throw new IllegalArgumentException("Port is required for server mode");
            }
            return new ServerInfo(null, Integer.parseInt(portValue), modeValue);
        } else if (modeValue.equals("client")) {
            if (serverValue == null || portValue == null) {
                System.out.println("Server and port are required for client mode");
                formatter.printHelp("utility-name", options);
                throw new IllegalArgumentException("Server and port are required for client mode");
            }
            return new ServerInfo(serverValue, Integer.parseInt(portValue), modeValue);
        } else {
            System.out.println("Mode must be either server or client");
            formatter.printHelp("utility-name", options);
            throw new IllegalArgumentException("Mode must be either server or client");
        }
    }

    public static void main( String[] args )
    {
        ServerInfo config = getInputs(args);
        System.out.println(config.mode);
        if (config.mode.equals("server")) {
            System.out.println("Starting server on port " + config.port);
            SocketServer.startServer(config.port);
        } else if (config.mode.equals("client")) {
            System.out.println("Starting client on server " + config.server + " port " + config.port);
            SocketClient.transmit(config.server, config.port);
        }
    }
}
