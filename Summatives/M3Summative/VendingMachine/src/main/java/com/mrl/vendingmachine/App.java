/**
 * @author Maxwell R. Lafontant
 * @contact maxwell@maxwellRLafontant.com Date Created 1/3/21 Date Last Modified
 * 1/3/21
 */
package com.mrl.vendingmachine;

import com.mrl.vendingmachine.controller.VendingMachineController;
import com.mrl.vendingmachine.dao.VendingMachineDao;
import com.mrl.vendingmachine.dao.VendingMachineDaoFileImp1;
import com.mrl.vendingmachine.service.VendingMachineService;
import com.mrl.vendingmachine.service.VendingMachineServiceImp1;
import com.mrl.vendingmachine.ui.UserIO;
import com.mrl.vendingmachine.ui.UserIOConsoleImp1;
import com.mrl.vendingmachine.ui.VendingMachineView;

public class App {

    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImp1();
        VendingMachineView myView = new VendingMachineView(myIo);
        VendingMachineDao myDao = new VendingMachineDaoFileImp1();
        VendingMachineService myService = new VendingMachineServiceImp1();
        VendingMachineController controller = new VendingMachineController(myView, myDao, myService);
        controller.run();
    }
}
