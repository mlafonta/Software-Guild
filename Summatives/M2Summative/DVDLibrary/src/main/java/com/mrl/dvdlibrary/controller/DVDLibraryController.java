/*
 * @author Maxwell R. Lafontant
 * @email maxwell@maxwellRLafontant.com
 * Date created: 11/29/20
 * Date last modified: 11/29/20
 */
package com.mrl.dvdlibrary.controller;

import com.mrl.dvdlibrary.dao.DVDLibraryDao;
import com.mrl.dvdlibrary.dao.DVDLibraryDaoException;
import com.mrl.dvdlibrary.dto.DVD;
import com.mrl.dvdlibrary.ui.DVDLibraryView;
import java.util.List;

public class DVDLibraryController {
    
    private DVDLibraryView view;
    private DVDLibraryDao dao;
    
    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
    this.dao = dao;
    this.view = view;
    }
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try{
            while(keepGoing) {

                menuSelection = getMenuSelection();

                switch(menuSelection) {
                    case 1:
                        addDVD();
                        break;
                    case 2:
                        removeDVD();
                        break;
                    case 3:
                        editDVD();
                        break;
                    case 4:
                        listDVDs();
                        break;
                    case 5:
                        viewDVD();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (DVDLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }    
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void addDVD() 
     throws DVDLibraryDaoException {
        view.displayAddDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayAddSuccessBanner();
    }
    
    private void listDVDs() 
     throws DVDLibraryDaoException {
        view.displayDisplayDVDListBanner();
        List<DVD> dvdList = dao.listAllDVDs();
        view.displayDVDList(dvdList);
    }
    
    private void viewDVD() 
     throws DVDLibraryDaoException {
        view.displayViewDVDBanner();
        String title = view.getDVDTitle();
        DVD dvd = dao.viewDVD(title);
        view.viewDVD(dvd);   
    }
    
    private void removeDVD() 
     throws DVDLibraryDaoException {
        view.displayRemoveDVDBanner();
        String title = view.getDVDTitle();
        DVD removedDVD = dao.removeDVD(title);
        view.displayRemoveResult(removedDVD);
    }
    
    private void editDVD() 
     throws DVDLibraryDaoException {
        view.displayEditDVDBanner();
        String title = view.getDVDTitle();
        DVD dvd = dao.viewDVD(title);
        view.getEditedDVDInfo(dvd);
        if(dvd != null) {
        dao.editDVD(title, dvd);
        }
    }
    
    private void unknownCommand() {
    view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
