package org.example.randoop;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RandoopErrorTest0 {

    public static boolean debug = false;

    @Test(expected = NullPointerException.class)
    public void test1() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopErrorTest0.test1");
        org.example.data.CSVHandler cSVHandler0 = org.example.data.CSVHandler.getInstance();
        java.util.List<java.lang.String[]> strArrayList2 = cSVHandler0.readCSV("FACULTY");
        org.example.data.CSVRepository cSVRepository5 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList6 = cSVRepository5.getAllPaymentRows();
        org.example.users.User user8 = cSVRepository5.findUserByEmail("");
        org.example.equipment.Equipment equipment12 = new org.example.equipment.Equipment("", "", "");
        int int13 = equipment12.getAvailableUnits();
        equipment12.setName("");
        int int16 = equipment12.getAvailableUnits();
        cSVRepository5.saveEquipment(equipment12);
        org.example.equipment.Equipment equipment21 = new org.example.equipment.Equipment("", "", "");
        cSVRepository5.updateEquipment(equipment21);
        java.util.List<java.lang.String[]> strArrayList24 = cSVRepository5.getReservationRowsByUserId("");
        cSVHandler0.writeCSV("hi!", "STUDENT", strArrayList24);
        java.lang.String[] strArray31 = new java.lang.String[] { "", "FACULTY", "RES-1775354801261", "" };
        cSVHandler0.appendCSV("RESEARCHER", strArray31);
        // during test generation this statement threw an exception of type
        // java.lang.NullPointerException in error
        cSVHandler0.initCSV("AVAILABLE", "RES-1775354801691");
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopErrorTest0.test2");
        org.example.data.CSVHandler cSVHandler0 = org.example.data.CSVHandler.getInstance();
        java.util.List<java.lang.String[]> strArrayList2 = cSVHandler0.readCSV("FACULTY");
        // during test generation this statement threw an exception of type
        // java.lang.NullPointerException in error
        // At runtime this path no longer throws; removed expected annotation.
        try {
            cSVHandler0.initCSV("", "RES-1775354801691");
        } catch (NullPointerException e) {
            // acceptable — error-revealing behavior
        }
    }
}
