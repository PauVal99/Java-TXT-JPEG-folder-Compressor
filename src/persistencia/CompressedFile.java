package src.persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import src.persistencia.File;

public class CompressedFile extends File
{
    private String name, algorithm;

    public CompressedFile(String pathName)
    {
        super(pathName);
        this.readAttributes();
    }

    private void readAttributes()
    {
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.getPath()));
            int read = 0;
            for(int i=0; i<2; i++){
                String line = bufferedReader.readLine();
                read = read + (line+"\n").getBytes().length;
                String[] split = line.split(":");
                if(split[0].equals("name")) this.name = split[1];
                else if(split[0].equals("algorithm")) this.algorithm = split[1];
            }
            this.fileInputStream.skip(read);
            bufferedReader.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getOriginalName()
    {
        return name;
    }

    public String getAlgorithm()
    {
        return algorithm;   
    }

    public static final long serialVersionUID = 1L;
}