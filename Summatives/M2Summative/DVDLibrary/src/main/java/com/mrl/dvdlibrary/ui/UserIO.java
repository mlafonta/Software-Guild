/*
 * @author Maxwell R. Lafontant
 * @email maxwell@maxwellRLafontant.com
 * Date created: 11/29/20
 * Date last modified: 11/29/20
 */
package com.mrl.dvdlibrary.ui;

public interface UserIO {
    void print(String msg);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    String readString(String prompt);
}
