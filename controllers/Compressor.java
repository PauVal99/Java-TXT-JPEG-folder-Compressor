package controllers;

import models.*;
import java.nio.file.Files;
import controllers.algorithms.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Compressor
{
    private UncompressedFile uncompressedFile;
    private Path destinationPath;
    private Algorithm algorithm;

    public Compressor(UncompressedFile uncompressedFile, String destination, String algorithmName)
    {
        this.uncompressedFile = uncompressedFile;
        this.destinationPath = Paths.get(destination);
        this.algorithm = this.setAlgorithm(algorithmName);
    }

    public void compress()
    {
        byte[] compressedBytes = algorithm.compress(new String(uncompressedFile.getContent()));
        this.createDestinationFile();
        String header = "name:"      + this.uncompressedFile.getName()          + "\n" +
                        "algorithm:" + this.algorithm.getName()                 + "\n"; 
        this.writeInDetiantionFile(header.getBytes());
        this.writeInDetiantionFile(compressedBytes);
        this.writeInDetiantionFile("\n".getBytes());
    }

    private void createDestinationFile()
    {   
        try{
            Files.createFile(this.destinationPath);}
        catch (IOException e){
            e.printStackTrace();}
    }

    private void writeInDetiantionFile(byte[] bytes)
    {   
        try{
            Files.write(this.destinationPath, bytes, StandardOpenOption.APPEND);}
        catch (IOException e){
            e.printStackTrace();}
    }

    private Algorithm setAlgorithm(String algorithmName)
    {
        if(algorithmName.equals("LZ78")) return new LZ78();
        else if(algorithmName.equals("LZSS")) return new LZSS();
        else if(algorithmName.equals("LZW")) return new LZW();
        else if(algorithmName.equals("JPEG")) return new JPEG();
        return new LZW();
    }
}