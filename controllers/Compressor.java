package controllers;

import models.File;
import java.nio.file.Files;
import controllers.algorithms.LZW;
import java.io.IOException;
import java.nio.file.Paths;

public class Compressor
{
    public void compressFile(File file)
    {
        try
        { 
            LZW lzw = new LZW();
            byte[] compressedBytes = lzw.compress(new String(file.getContent()));
            Files.createFile( Paths.get("C:\\Users\\Pau\\Desktop\\P1") );
            Files.write( Paths.get("C:\\Users\\Pau\\Desktop\\P1"),  compressedBytes);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}