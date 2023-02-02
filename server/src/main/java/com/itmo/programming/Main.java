package com.itmo.programming;

import com.google.gson.JsonSyntaxException;
import com.itmo.programming.console.ArgumentQuestioner;
import com.itmo.programming.console.ConsoleInterface;
import com.itmo.programming.console.exceptions.InvalidInputException;
import com.itmo.programming.controller.collectionutils.CommandReceiver;
import com.itmo.programming.controller.collectionutils.PersonStorage;
import com.itmo.programming.controller.collectionutils.ReceiverService;
import com.itmo.programming.controller.fileworkers.JsonReader;
import com.itmo.programming.controller.fileworkers.exceptions.IdenticalValueIdException;
import com.itmo.programming.controller.command.CommandManager;
import com.itmo.programming.controller.command.InvokerCommand;
import com.itmo.programming.controller.command.Reply;
import com.itmo.programming.controller.command.exceptions.IncorrectNumberOfArgumentsException;
import com.itmo.programming.controller.command.exceptions.NoSuchCommandException;
import com.itmo.programming.model.Person;
import com.itmo.programming.server.ServerRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;

/**
 * Главный класс программы
 *
 * @author Iskandarov Shakhzodbek P3133
 */
public class Main {
    private static final String PS1 = "$ ";
    private static  String filePath = "";
    private static  int serverPort = 0;

    public static void main(String[] args) {
        if (validateInput(args)){
            ConsoleInterface consoleManager = new ConsoleInterface(true);
            ServerRunner serverRunner = new ServerRunner(filePath, new InetSocketAddress(serverPort), consoleManager);
            serverRunner.start();
        }
    }

    public static boolean validateInput(String [] args){
        if (args.length!=2){
            System.out.println("Использование java -jar jarname filePath port");
            return false;
        }
        try {
            serverPort = Integer.parseInt(args[1]);
        }catch (NumberFormatException e ){
            System.out.println("Порт должен быть представлен числом");
            return false;
        }
        filePath = args[0];    
        return true;
    }

}
