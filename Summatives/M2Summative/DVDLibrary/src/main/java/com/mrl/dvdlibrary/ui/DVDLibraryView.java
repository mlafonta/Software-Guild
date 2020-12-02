/*
 * @author Maxwell R. Lafontant
 * @email maxwell@maxwellRLafontant.com
 * Date created: 11/29/20
 * Date last modified: 11/29/20
 */
package com.mrl.dvdlibrary.ui;

import com.mrl.dvdlibrary.dto.DVD;
import java.util.List;

public class DVDLibraryView {
    
    private UserIO io;
    
    public DVDLibraryView(UserIO io) {
        this.io = io;
    }
    
    public int printMenuAndGetSelection() {
            io.print("Main Menu");
            io.print("1. Add DVD");
            io.print("2. Remove DVD");
            io.print("3. Edit DVD");
            io.print("4. List all DVDs");
            io.print("5. View a DVD");
            io.print("6. Exit");
            return io.readInt("Please select from the above choices", 1, 6);
    }
    
    public DVD getNewDVDInfo() {
        String title = io.readString("Please enter the film's title");
        String date = io.readString("Please enter the film's release date");
        String rating = io.readString("Please enter the film's MPAA rating");
        String director = io.readString("Please enter the name of the film's director");
        String studio = io.readString("Please enter the film's production studio");
        String notes = io.readString("Please enter your personal rating and/or any pertinent additional information");
        DVD currentDVD = new DVD(title);
        currentDVD.setDate(date + " ");
        currentDVD.setRating(rating + " ");
        currentDVD.setDirector(director + " ");
        currentDVD.setStudio(studio + " ");
        currentDVD.setNotes(notes + " ");
        return currentDVD;
    }
    
    public void displayAddDVDBanner() {
        io.print("=== Add DVD ===");
    }
    
    public void displayAddSuccessBanner() {
        io.readString("DVD successfully added. Please hit enter to continue.");
    }
    
    public void displayDVDList(List<DVD> dvdList) {
        for(DVD currentDVD : dvdList) {
            io.print(currentDVD.getTitle());    
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayDisplayDVDListBanner() {
        io.print("=== List of All DVDs ===");
    }
    
    public void displayViewDVDBanner() {
        io.print("=== View DVD ===");
    }
    
    public String getDVDTitle() {
        return io.readString("Please enter the film's title.");
    }
    
    public void viewDVD(DVD dvd) {
        if(dvd != null) {
            io.print("Title: " + dvd.getTitle());
            io.print("Release date: " +  dvd.getDate());
            io.print("MPAA rating: " + dvd.getRating());
            io.print("Director: " + dvd.getDirector());
            io.print("Studio: " + dvd.getStudio());
            io.print("Personal rating and/or additional notes: " + dvd.getNotes());
        } else {
            io.print("DVD not in library.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayRemoveDVDBanner() {
        io.print("=== Remove DVD ===");
    }
    
    public void displayRemoveResult(DVD dvdRecord) {
        if(dvdRecord != null) {
            io.print("DVD successfully removed.");
        } else {
            io.print("DVD not in library.");
        }
        io.print("Please hit enter to continue.");
    }
    
    public void displayEditDVDBanner() {
        io.print("=== Edit DVD ===");
    }
    
    public DVD getEditedDVDInfo(DVD existingDVD) {
        if(existingDVD != null) {
        String date = io.readString("Please enter the film's release date");
        String rating = io.readString("Please enter the film's MPAA rating");
        String director = io.readString("Please enter the name of the film's director");
        String studio = io.readString("Please enter the film's production studio");
        String notes = io.readString("Please enter your personal rating and/or any pertinent additional information");
        existingDVD.setDate(date + " ");
        existingDVD.setRating(rating + " ");
        existingDVD.setDirector(director + " ");
        existingDVD.setStudio(studio + " ");
        existingDVD.setNotes(notes + " ");
        io.readString("DVD successfully edited. Please hit enter to continue.");
        } else {
            io.print("DVD not in library.");
        }
        return existingDVD;
    }
    
    public void displayExitBanner() {
    io.print("Thank you for utilising your DVD library");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    
}
