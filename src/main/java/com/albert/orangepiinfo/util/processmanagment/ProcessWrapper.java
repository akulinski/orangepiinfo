package com.albert.orangepiinfo.util.processmanagment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessWrapper {

    private Process process;

    private String command;

    public ProcessWrapper(String command) {
        this.command = command;
    }

    private void createProcess() {

        try {
            this.process = Runtime.getRuntime().exec(this.command);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String processParser() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.process.getInputStream()));

        StringBuilder stringBuilder = new StringBuilder();

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }

        return stringBuilder.toString();
    }


    public String execCommand() throws IOException {

        createProcess();

        return processParser();
    }

}
