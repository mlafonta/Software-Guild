/*
 * @author Maxwell R. Lafontant
 * @email maxwell@maxwellRLafontant.com
 * Date created: 11/29/20
 * Date last modified: 11/29/20
 */
package com.mrl.dvdlibrary;

import com.mrl.dvdlibrary.controller.DVDLibraryController;
import com.mrl.dvdlibrary.dao.DVDLibraryDao;
import com.mrl.dvdlibrary.dao.DVDLibraryDaoFileImp1;
import com.mrl.dvdlibrary.ui.DVDLibraryView;
import com.mrl.dvdlibrary.ui.UserIO;
import com.mrl.dvdlibrary.ui.UserIOConsoleImp1;

public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImp1();
        DVDLibraryView myView = new DVDLibraryView(myIo);
        DVDLibraryDao myDao = new DVDLibraryDaoFileImp1();
        DVDLibraryController controller = new DVDLibraryController(myDao, myView);
        controller.run();
    }
    
}
