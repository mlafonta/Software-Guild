/*
 * @author Maxwell R. Lafontant
 * @email maxwell@maxwellRLafontant.com
 * Date created: 11/29/20
 * Date last modified: 11/29/20
 */
package com.mrl.dvdlibrary.dao;

import com.mrl.dvdlibrary.dto.DVD;
import java.util.List;

public interface DVDLibraryDao {
    
    /**
     * Adds the given DVD to the library and associates it with the given title.
     * If there is already a DVD associated with the given title it will return
     * that DVD object, otherwise it will return null;
     * 
     * @param title title with which the DVD is to be associated.
     * @param dvd DVD to be added to the library
     * @return the DVD associated with the given title, else null
     */
    DVD addDVD(String title, DVD dvd)
     throws DVDLibraryDaoException;
    
    /**
     * Removes from the library the DVD associated with the given title.
     * Returns the DVD object that is being removed or null if there is no DVD
     * associated with the given title
     * 
     * @param title title of the DVD to be removed
     * @return DVD object that was removed associated with the given title, else null
     */
    DVD removeDVD(String title)
     throws DVDLibraryDaoException;
    
    /**
     * Edits the given DVD in the library associated with the given title.
     * If there is already a DVD associated with the given title it will return
     * that DVD object, otherwise it will return null;
     * 
     * @param title title with which the DVD is associated.
     * @param dvd DVD to be edited in the library
     * @return the DVD associated with the given title, else null
     */
    DVD editDVD(String title, DVD dvd)
     throws DVDLibraryDaoException;    
    
    /**
     * Returns a List of all DVDs in the Library
     * 
     * @return List of all DVDs in the Library 
     */
    List<DVD> listAllDVDs()
     throws DVDLibraryDaoException;
    
    /**
     * Returns the DVD object associated with the given title.
     * Returns null if no such DVD exists.
     * 
     * @param title title of the DVD to retrieve
     * @return the DVD object associated with the give title, else null
     */
    DVD viewDVD(String title)
     throws DVDLibraryDaoException;
}
