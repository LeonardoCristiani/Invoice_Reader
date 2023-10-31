package com.ant.test.progettofinale.invioce_file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.ant.test.progettofinale.entity.Company;
import com.ant.test.progettofinale.generated.DatiGeneraliDocumentoType;

public class FileSystem 
{
    public static String  check_Directory(String path , Company company , DatiGeneraliDocumentoType  data_info)
    {
        String ris = "";
        try{
            File x = new File(path);
            if (!x.exists())
            {
                Files.createDirectory(Paths.get(path));
                
                System.out.println("creato "+ path);
            }

            path += "/"+company.getName();
            x = new File(path);
            if(!x.exists())
            {
                Files.createDirectory(Paths.get(path));
            }
            path += "/"+ data_info.getData().getYear();
            x = new File(path);
            if(!x.exists())
            {
                Files.createDirectory(Paths.get(path));
            }
            path += "/"+ data_info.getData().getMonth();
            x = new File(path);
            if(!x.exists())
            {
                Files.createDirectory(Paths.get(path));
            }
            ris = path;
        }
        catch (IOException e )
        {
            e.printStackTrace();
        }

        return ris;
        
    }
}

