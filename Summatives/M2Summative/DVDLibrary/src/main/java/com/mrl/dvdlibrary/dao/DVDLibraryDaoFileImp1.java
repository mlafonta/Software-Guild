/*
 * @author Maxwell R. Lafontant
 * @email maxwell@maxwellRLafontant.com
 * Date created: 11/29/20
 * Date last modified: 11/29/20
 */
package com.mrl.dvdlibrary.dao;

import com.mrl.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DVDLibraryDaoFileImp1 implements DVDLibraryDao {
    
    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";
    
    private Map<String, DVD> dvds = new HashMap<>();
    
    @Override
    public DVD addDVD(String title, DVD dvd)
     throws DVDLibraryDaoException {
        loadLibrary();
        DVD newDVD = dvds.put(title, dvd);
        writeLibrary();
        return newDVD;
    }

    @Override
    public DVD removeDVD(String title) 
     throws DVDLibraryDaoException {
        loadLibrary();
        DVD removedDVD = dvds.remove(title);
        writeLibrary();
        return removedDVD;
    }

    @Override
    public DVD editDVD(String title, DVD dvd) 
     throws DVDLibraryDaoException {
        loadLibrary();
        DVD editedDVD = dvds.put(title, dvd);
        writeLibrary();
        return editedDVD;
    }

    @Override
    public List<DVD> listAllDVDs() 
     throws DVDLibraryDaoException {
        loadLibrary();
        return new ArrayList<DVD>(dvds.values());
    }

    @Override
    public DVD viewDVD(String title) 
     throws DVDLibraryDaoException {
        loadLibrary();
        return dvds.get(title);
    }
    
    private DVD unmarshallDVD(String dvdAsText) {
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String title = dvdTokens[0];
        DVD dvdFromFile = new DVD(title);
        dvdFromFile.setDate(dvdTokens[1]);
        dvdFromFile.setRating(dvdTokens[2]);
        dvdFromFile.setDirector(dvdTokens[3]);
        dvdFromFile.setStudio(dvdTokens[4]);
        dvdFromFile.setNotes(dvdTokens[5]);
        return dvdFromFile;
    }
    
    private void loadLibrary() throws DVDLibraryDaoException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(LIBRARY_FILE)));
        } catch(FileNotFoundException e) {
            throw new DVDLibraryDaoException("Could not load library data into memory.", e);
        }
        String currentLine;
        DVD currentDVD;
        while(scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentDVD = unmarshallDVD(currentLine);
            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        scanner.close();
    }
    
    private String marshallDVD(DVD aDVD) {
        String dvdAsText = aDVD.getTitle() + DELIMITER;
        dvdAsText += aDVD.getDate() + DELIMITER;
        dvdAsText += aDVD.getRating() + DELIMITER;
        dvdAsText += aDVD.getDirector() + DELIMITER;
        dvdAsText += aDVD.getStudio() + DELIMITER;
        dvdAsText += aDVD.getNotes();
        return dvdAsText;
    }
    
    private void writeLibrary() throws DVDLibraryDaoException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch(IOException e) {
            throw new DVDLibraryDaoException("Could not save library data.", e);
        }
        String dvdAsText;
        List<DVD> dvdList = this.listAllDVDs();
        for(DVD currentDVD : dvdList) {
            dvdAsText = marshallDVD(currentDVD);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }
}
