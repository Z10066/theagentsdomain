package com.highwayac.com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ShellExecutor {

    public String executeCommands(String... commands) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(commands);

        StringBuffer output = new StringBuffer();
        Process process;

        try {
            process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if (exitVal != 0) {
                System.out.println("Error occurred");
                // Handle errors if required
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }

        return output.toString();
    }
}
