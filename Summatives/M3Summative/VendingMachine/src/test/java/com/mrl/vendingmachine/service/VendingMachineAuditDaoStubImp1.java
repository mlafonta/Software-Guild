/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.vendingmachine.service;

import com.mrl.vendingmachine.dao.VendingMachineAuditDao;
import com.mrl.vendingmachine.dao.VendingMachinePersistenceException;

/**
 *
 * @author flafo
 */
public class VendingMachineAuditDaoStubImp1 implements VendingMachineAuditDao {

    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
        //do nothing
    }

}
