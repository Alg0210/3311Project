package org.example.randoop;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RandoopRegressionTest0 {

    public static boolean debug = false;

    @Test
    public void test001() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test001");
        org.example.equipment.EquipmentStatus equipmentStatus0 = org.example.equipment.EquipmentStatus.IN_USE;
        org.junit.Assert.assertTrue("'" + equipmentStatus0 + "' != '" + org.example.equipment.EquipmentStatus.IN_USE + "'", equipmentStatus0.equals(org.example.equipment.EquipmentStatus.IN_USE));
    }

    @Test
    public void test002() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test002");
        org.example.auth.AuthService.logout();
    }

    @Test
    public void test003() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test003");
        org.example.reservation.ReservationManager reservationManager0 = null;
        org.example.reservation.Reservation reservation1 = null;
        org.example.reservation.ReservationAction reservationAction3 = new org.example.reservation.ReservationAction(reservationManager0, reservation1, "hi!");
        reservationAction3.undo();
    }

    @Test
    public void test004() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test004");
        org.example.users.User user0 = org.example.auth.AuthService.getCurrentUser();
        org.junit.Assert.assertNull(user0);
    }

    @Test
    public void test005() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test005");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation1 = null;
        // The following exception was thrown during execution in test generation
        try {
            reservationManager0.updateReservation(reservation1);
// flaky:             org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.example.reservation.Reservation.getReservationId()\" because \"reservation\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test006() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test006");
        org.example.equipment.EquipmentManager equipmentManager0 = null;
        org.example.equipment.Equipment equipment1 = null;
        org.example.equipment.EquipmentAction equipmentAction3 = new org.example.equipment.EquipmentAction(equipmentManager0, equipment1, "hi!");
        equipmentAction3.undo();
        // The following exception was thrown during execution in test generation
        try {
            equipmentAction3.execute();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.example.equipment.Equipment.getStatus()\" because \"this.equipment\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test007() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test007");
        org.example.reservation.ReservationStatus reservationStatus0 = org.example.reservation.ReservationStatus.EXTENDED;
        org.junit.Assert.assertTrue("'" + reservationStatus0 + "' != '" + org.example.reservation.ReservationStatus.EXTENDED + "'", reservationStatus0.equals(org.example.reservation.ReservationStatus.EXTENDED));
    }

    @Test
    public void test008() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test008");
        org.example.users.UserFactory userFactory0 = new org.example.users.UserFactory();
    }

    @Test
    public void test009() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test009");
        org.example.users.Guest guest6 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest6.setIdNumber("");
        guest6.setPassword("");
        java.lang.String str11 = guest6.getName();
        java.lang.String str12 = guest6.getPassword();
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "" + "'", str11, "");
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "" + "'", str12, "");
    }

    @Test
    public void test010() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test010");
        org.example.equipment.EquipmentManager equipmentManager0 = null;
        org.example.equipment.Equipment equipment1 = null;
        org.example.equipment.EquipmentAction equipmentAction3 = new org.example.equipment.EquipmentAction(equipmentManager0, equipment1, "hi!");
        // The following exception was thrown during execution in test generation
        try {
            equipmentAction3.execute();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.example.equipment.Equipment.getStatus()\" because \"this.equipment\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test011() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test011");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<org.example.equipment.Equipment> equipmentList1 = cSVRepository0.getAllEquipment();
        org.junit.Assert.assertNotNull(equipmentList1);
    }

    @Test
    public void test012() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test012");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("", "", "");
        java.lang.String str4 = equipment3.getName();
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "" + "'", str4, "");
    }

    @Test
    public void test013() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test013");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str6 = payment5.getReservationId();
        java.lang.String str7 = payment5.getReservationId();
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "hi!" + "'", str6, "hi!");
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "hi!" + "'", str7, "hi!");
    }

    @Test
    public void test014() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test014");
        org.example.reservation.ReservationStatus reservationStatus0 = org.example.reservation.ReservationStatus.CONFIRMED;
        org.junit.Assert.assertTrue("'" + reservationStatus0 + "' != '" + org.example.reservation.ReservationStatus.CONFIRMED + "'", reservationStatus0.equals(org.example.reservation.ReservationStatus.CONFIRMED));
    }

    @Test
    public void test015() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test015");
        org.example.users.Guest guest6 = new org.example.users.Guest("hi!", "", "", "", "hi!", "hi!");
    }

    @Test
    public void test016() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test016");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime6 = payment5.getTimestamp();
        payment5.setForfeited(true);
        org.junit.Assert.assertNotNull(localDateTime6);
    }

    @Test
    public void test017() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test017");
        org.example.equipment.EquipmentManager equipmentManager0 = null;
        org.example.equipment.Equipment equipment1 = null;
        org.example.equipment.EquipmentAction equipmentAction3 = new org.example.equipment.EquipmentAction(equipmentManager0, equipment1, "hi!");
        // The following exception was thrown during execution in test generation
        try {
            equipmentAction3.execute();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.example.equipment.Equipment.getStatus()\" because \"this.equipment\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test018() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test018");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.equipment.Equipment equipment5 = new org.example.equipment.Equipment("", "", "");
        int int6 = equipment5.getAvailableUnits();
        java.lang.String str7 = equipment5.getDescription();
        equipment5.setAvailableUnits((int) 'a');
        cSVRepository0.updateEquipment(equipment5);
        equipment5.setImagePath("hi!");
        java.lang.String str13 = equipment5.getProductStatistics();
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
    }

    @Test
    public void test019() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test019");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str6 = payment5.getReservationId();
        java.time.LocalDateTime localDateTime7 = payment5.getTimestamp();
        boolean boolean8 = payment5.isForfeited();
        payment5.setForfeited(false);
        java.lang.String str11 = payment5.getPaymentId();
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "hi!" + "'", str6, "hi!");
        org.junit.Assert.assertNotNull(localDateTime7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "" + "'", str11, "");
    }

    @Test
    public void test020() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test020");
        org.example.auth.AuthService authService0 = new org.example.auth.AuthService();
        boolean boolean7 = authService0.register("hi!", "", "hi!", "", "", "hi!");
        org.example.users.User user10 = authService0.login("GUEST", "hi!");
        boolean boolean12 = authService0.isApproved("");
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNull(user10);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
    }

    @Test
    public void test021() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test021");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str6 = payment5.getReservationId();
        java.time.LocalDateTime localDateTime7 = payment5.getTimestamp();
        boolean boolean8 = payment5.isForfeited();
        double double9 = payment5.getAmount();
        payment5.setForfeited(true);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "hi!" + "'", str6, "hi!");
        org.junit.Assert.assertNotNull(localDateTime7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + double9 + "' != '" + 10.0d + "'", double9 == 10.0d);
    }

    @Test
    public void test022() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test022");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.equipment.Equipment equipment5 = new org.example.equipment.Equipment("", "", "");
        int int6 = equipment5.getAvailableUnits();
        java.lang.String str7 = equipment5.getDescription();
        equipment5.setAvailableUnits((int) 'a');
        cSVRepository0.updateEquipment(equipment5);
        equipment5.setAvailableUnits((int) ' ');
        java.lang.String str13 = equipment5.getDescription();
        org.example.equipment.EquipmentStatus equipmentStatus14 = equipment5.getStatus();
        java.lang.String str15 = equipment5.getImagePath();
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
        org.junit.Assert.assertTrue("'" + equipmentStatus14 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus14.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "" + "'", str15, "");
    }

    @Test
    public void test023() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test023");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        org.example.users.Guest guest10 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest10.setIdNumber("");
        java.lang.String str13 = guest10.getDepartmentId();
        org.example.equipment.Equipment equipment17 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus18 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment17.setStatus(equipmentStatus18);
        org.example.payment.Payment payment25 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str26 = payment25.getReservationId();
        java.time.LocalDateTime localDateTime27 = payment25.getTimestamp();
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str34 = payment33.getReservationId();
        java.time.LocalDateTime localDateTime35 = payment33.getTimestamp();
        org.example.reservation.Reservation reservation36 = reservationManager0.createReservation((org.example.users.User) guest10, equipment17, localDateTime27, localDateTime35);
        org.example.users.Guest guest44 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str45 = guest44.getIdNumber();
        org.example.equipment.Equipment equipment49 = new org.example.equipment.Equipment("", "", "");
        org.example.payment.Payment payment55 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str56 = payment55.getReservationId();
        java.time.LocalDateTime localDateTime57 = payment55.getTimestamp();
        org.example.payment.Payment payment63 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime64 = payment63.getTimestamp();
        org.example.payment.Payment payment70 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime71 = payment70.getTimestamp();
        payment63.setTimestamp(localDateTime71);
        org.example.reservation.Reservation reservation74 = new org.example.reservation.Reservation("hi!", (org.example.users.User) guest44, equipment49, localDateTime57, localDateTime71, (double) (byte) -1);
        org.example.reservation.ReservationAction reservationAction76 = new org.example.reservation.ReservationAction(reservationManager0, reservation74, "GUEST");
        org.example.payment.Payment payment83 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str84 = payment83.getReservationId();
        java.time.LocalDateTime localDateTime85 = payment83.getTimestamp();
        reservationManager0.modifyReservation("RESEARCHER", localDateTime85);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!" + "'", str13, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus18 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus18.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hi!" + "'", str34, "hi!");
        org.junit.Assert.assertNotNull(localDateTime35);
        org.junit.Assert.assertNotNull(reservation36);
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "" + "'", str45, "");
        org.junit.Assert.assertEquals("'" + str56 + "' != '" + "hi!" + "'", str56, "hi!");
        org.junit.Assert.assertNotNull(localDateTime57);
        org.junit.Assert.assertNotNull(localDateTime64);
        org.junit.Assert.assertNotNull(localDateTime71);
        org.junit.Assert.assertEquals("'" + str84 + "' != '" + "hi!" + "'", str84, "hi!");
        org.junit.Assert.assertNotNull(localDateTime85);
    }

    @Test
    public void test024() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test024");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str6 = payment5.getReservationId();
        java.time.LocalDateTime localDateTime7 = payment5.getTimestamp();
        boolean boolean8 = payment5.isForfeited();
        double double9 = payment5.getAmount();
        java.time.LocalDateTime localDateTime10 = payment5.getTimestamp();
        payment5.setForfeited(false);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "hi!" + "'", str6, "hi!");
        org.junit.Assert.assertNotNull(localDateTime7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + double9 + "' != '" + 10.0d + "'", double9 == 10.0d);
        org.junit.Assert.assertNotNull(localDateTime10);
    }

    @Test
    public void test025() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test025");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.User user3 = cSVRepository0.findUserByEmail("");
        org.example.equipment.Equipment equipment7 = new org.example.equipment.Equipment("", "", "");
        int int8 = equipment7.getAvailableUnits();
        equipment7.setName("");
        int int11 = equipment7.getAvailableUnits();
        cSVRepository0.saveEquipment(equipment7);
        org.example.payment.Payment payment13 = null;
        // The following exception was thrown during execution in test generation
        try {
            cSVRepository0.savePayment(payment13);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.example.payment.Payment.getPaymentId()\" because \"payment\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArrayList1);
// flaky:         org.junit.Assert.assertNotNull(user3);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 1 + "'", int8 == 1);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 1 + "'", int11 == 1);
    }

    @Test
    public void test026() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test026");
        org.example.users.Researcher researcher6 = new org.example.users.Researcher("", "", "", "hi!", "hi!", "");
        java.lang.String str7 = researcher6.getUserType();
        java.lang.String str8 = researcher6.getUserType();
        java.lang.String str9 = researcher6.getUserType();
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "RESEARCHER" + "'", str7, "RESEARCHER");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "RESEARCHER" + "'", str8, "RESEARCHER");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "RESEARCHER" + "'", str9, "RESEARCHER");
    }

    @Test
    public void test027() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test027");
        org.example.users.Guest guest6 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest6.setIdNumber("");
        guest6.setPassword("");
        guest6.setName("GUEST");
    }

    @Test
    public void test028() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test028");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("RESEARCHER", "", (double) ' ', "FACULTY", true);
    }

    @Test
    public void test029() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test029");
        boolean boolean1 = org.example.auth.PasswordValidator.isValid("RESEARCHER");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test030() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test030");
        org.example.equipment.EquipmentManager equipmentManager0 = null;
        org.example.equipment.Equipment equipment1 = null;
        org.example.equipment.EquipmentAction equipmentAction3 = new org.example.equipment.EquipmentAction(equipmentManager0, equipment1, "hi!");
        equipmentAction3.undo();
        equipmentAction3.undo();
        equipmentAction3.undo();
    }

    @Test
    public void test031() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test031");
        org.example.reservation.ReservationManager reservationManager0 = null;
        org.example.reservation.Reservation reservation1 = null;
        org.example.reservation.ReservationAction reservationAction3 = new org.example.reservation.ReservationAction(reservationManager0, reservation1, "hi!");
        reservationAction3.undo();
    }

    @Test
    public void test032() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test032");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str6 = payment5.getReservationId();
        java.time.LocalDateTime localDateTime7 = payment5.getTimestamp();
        boolean boolean8 = payment5.isForfeited();
        org.example.payment.PaymentDecorator paymentDecorator11 = new org.example.payment.PaymentDecorator(payment5, "", "GUEST");
        boolean boolean12 = payment5.isDeposit();
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "hi!" + "'", str6, "hi!");
        org.junit.Assert.assertNotNull(localDateTime7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
    }

    @Test
    public void test033() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test033");
        org.example.users.Guest guest6 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest6.setIdNumber("");
        guest6.setPassword("");
        org.example.users.UserDecorator userDecorator12 = new org.example.users.UserDecorator((org.example.users.User) guest6, "");
        java.lang.String str13 = userDecorator12.getCertificationNumber();
        java.lang.String str14 = userDecorator12.getDecorationType();
        org.junit.Assert.assertNull(str13);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "" + "'", str14, "");
    }

    @Test
    public void test034() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test034");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        equipmentManager0.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList4 = equipmentManager0.getAllEquipment();
        equipmentManager0.update("STUDENT", "STUDENT");
        org.junit.Assert.assertNotNull(equipmentList4);
    }

    @Test
    public void test035() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test035");
        org.example.auth.AuthService authService0 = new org.example.auth.AuthService();
        boolean boolean7 = authService0.register("FACULTY", "FACULTY", "RESEARCHER", "STUDENT", "FACULTY", "FACULTY");
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test036() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test036");
        org.example.auth.AuthService authService0 = new org.example.auth.AuthService();
        org.example.users.User user3 = authService0.login("FACULTY", "RESEARCHER");
        org.junit.Assert.assertNull(user3);
    }

    @Test
    public void test037() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test037");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        equipmentManager0.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList4 = equipmentManager0.getAllEquipment();
        equipmentManager0.disableEquipment("");
        java.util.List<org.example.equipment.Equipment> equipmentList7 = equipmentManager0.getAvailableEquipment();
        org.example.reservation.ReservationManager reservationManager8 = new org.example.reservation.ReservationManager();
        reservationManager8.update("hi!", "");
        org.example.users.Guest guest18 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest18.setIdNumber("");
        java.lang.String str21 = guest18.getDepartmentId();
        org.example.equipment.Equipment equipment25 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus26 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment25.setStatus(equipmentStatus26);
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str34 = payment33.getReservationId();
        java.time.LocalDateTime localDateTime35 = payment33.getTimestamp();
        org.example.payment.Payment payment41 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str42 = payment41.getReservationId();
        java.time.LocalDateTime localDateTime43 = payment41.getTimestamp();
        org.example.reservation.Reservation reservation44 = reservationManager8.createReservation((org.example.users.User) guest18, equipment25, localDateTime35, localDateTime43);
        org.example.users.Guest guest52 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str53 = guest52.getIdNumber();
        org.example.equipment.Equipment equipment57 = new org.example.equipment.Equipment("", "", "");
        org.example.payment.Payment payment63 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str64 = payment63.getReservationId();
        java.time.LocalDateTime localDateTime65 = payment63.getTimestamp();
        org.example.payment.Payment payment71 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime72 = payment71.getTimestamp();
        org.example.payment.Payment payment78 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime79 = payment78.getTimestamp();
        payment71.setTimestamp(localDateTime79);
        org.example.reservation.Reservation reservation82 = new org.example.reservation.Reservation("hi!", (org.example.users.User) guest52, equipment57, localDateTime65, localDateTime79, (double) (byte) -1);
        org.example.reservation.ReservationAction reservationAction84 = new org.example.reservation.ReservationAction(reservationManager8, reservation82, "GUEST");
        org.example.equipment.Equipment equipment85 = reservation82.getEquipment();
        equipmentManager0.addEquipment(equipment85);
        org.example.equipment.EquipmentStatus equipmentStatus87 = equipment85.getStatus();
        org.junit.Assert.assertNotNull(equipmentList4);
        org.junit.Assert.assertNotNull(equipmentList7);
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "hi!" + "'", str21, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus26 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus26.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hi!" + "'", str34, "hi!");
        org.junit.Assert.assertNotNull(localDateTime35);
        org.junit.Assert.assertEquals("'" + str42 + "' != '" + "hi!" + "'", str42, "hi!");
        org.junit.Assert.assertNotNull(localDateTime43);
        org.junit.Assert.assertNotNull(reservation44);
        org.junit.Assert.assertEquals("'" + str53 + "' != '" + "" + "'", str53, "");
        org.junit.Assert.assertEquals("'" + str64 + "' != '" + "hi!" + "'", str64, "hi!");
        org.junit.Assert.assertNotNull(localDateTime65);
        org.junit.Assert.assertNotNull(localDateTime72);
        org.junit.Assert.assertNotNull(localDateTime79);
        org.junit.Assert.assertNotNull(equipment85);
        org.junit.Assert.assertTrue("'" + equipmentStatus87 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus87.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
    }

    @Test
    public void test038() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test038");
        org.example.payment.UserPricingStrategy userPricingStrategy1 = new org.example.payment.UserPricingStrategy("GUEST");
        double double3 = userPricingStrategy1.calculateTotal((-1));
        org.junit.Assert.assertTrue("'" + double3 + "' != '" + (-30.0d) + "'", double3 == (-30.0d));
    }

    @Test
    public void test039() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test039");
        org.example.auth.AuthService authService0 = new org.example.auth.AuthService();
        org.example.users.User user3 = authService0.login("", "STUDENT");
        org.junit.Assert.assertNull(user3);
    }

    @Test
    public void test040() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test040");
        boolean boolean1 = org.example.auth.PasswordValidator.isValid("RES-1775354801261");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test041() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test041");
        org.example.users.User user7 = org.example.users.UserFactory.createUser("RESEARCHER", "STUDENT", "RES-1775354801261", "", "RES-1775354801261", "RESEARCHER", "");
        org.junit.Assert.assertNotNull(user7);
    }

    @Test
    public void test042() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test042");
        org.example.users.Guest guest6 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest6.setIdNumber("");
        guest6.setUserId("");
        guest6.setName("hi!");
        guest6.setIdNumber("FACULTY");
        guest6.setEmail("RESEARCHER");
    }

    @Test
    public void test043() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test043");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("", "", "");
        int int4 = equipment3.getAvailableUnits();
        java.lang.String str5 = equipment3.getDescription();
        java.lang.String str6 = equipment3.getEquipmentId();
        org.example.equipment.Equipment equipment10 = new org.example.equipment.Equipment("", "", "");
        int int11 = equipment10.getAvailableUnits();
        java.lang.String str12 = equipment10.getDescription();
        org.example.equipment.EquipmentStatus equipmentStatus13 = equipment10.getStatus();
        equipment3.setStatus(equipmentStatus13);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 1 + "'", int4 == 1);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 1 + "'", int11 == 1);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "" + "'", str12, "");
        org.junit.Assert.assertTrue("'" + equipmentStatus13 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus13.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
    }

    @Test
    public void test044() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test044");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.equipment.Equipment equipment5 = new org.example.equipment.Equipment("", "", "");
        int int6 = equipment5.getAvailableUnits();
        java.lang.String str7 = equipment5.getDescription();
        equipment5.setAvailableUnits((int) 'a');
        cSVRepository0.updateEquipment(equipment5);
        equipment5.setAvailableUnits((int) ' ');
        java.lang.String str13 = equipment5.getDescription();
        int int14 = equipment5.getAvailableUnits();
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 32 + "'", int14 == 32);
    }

    @Test
    public void test045() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test045");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus4 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment3.setStatus(equipmentStatus4);
        equipment3.setName("");
        org.junit.Assert.assertTrue("'" + equipmentStatus4 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus4.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
    }

    @Test
    public void test046() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test046");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime6 = payment5.getTimestamp();
        org.example.payment.PaymentDecorator paymentDecorator9 = new org.example.payment.PaymentDecorator(payment5, "", "hi!");
        boolean boolean10 = paymentDecorator9.validate();
        org.junit.Assert.assertNotNull(localDateTime6);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
    }

    @Test
    public void test047() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test047");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        org.example.reservation.ReservationManager reservationManager2 = new org.example.reservation.ReservationManager();
        reservationManager2.update("hi!", "");
        org.example.users.Guest guest12 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest12.setIdNumber("");
        java.lang.String str15 = guest12.getDepartmentId();
        org.example.equipment.Equipment equipment19 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus20 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment19.setStatus(equipmentStatus20);
        org.example.payment.Payment payment27 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str28 = payment27.getReservationId();
        java.time.LocalDateTime localDateTime29 = payment27.getTimestamp();
        org.example.payment.Payment payment35 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str36 = payment35.getReservationId();
        java.time.LocalDateTime localDateTime37 = payment35.getTimestamp();
        org.example.reservation.Reservation reservation38 = reservationManager2.createReservation((org.example.users.User) guest12, equipment19, localDateTime29, localDateTime37);
        reservationManager0.modifyReservation("RES-1775354801261", localDateTime37);
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "hi!" + "'", str15, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus20 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus20.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str28 + "' != '" + "hi!" + "'", str28, "hi!");
        org.junit.Assert.assertNotNull(localDateTime29);
        org.junit.Assert.assertEquals("'" + str36 + "' != '" + "hi!" + "'", str36, "hi!");
        org.junit.Assert.assertNotNull(localDateTime37);
        org.junit.Assert.assertNotNull(reservation38);
    }

    @Test
    public void test048() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test048");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        equipmentManager0.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList4 = equipmentManager0.getAllEquipment();
        equipmentManager0.enableEquipment("hi!");
        org.example.equipment.Equipment equipment8 = equipmentManager0.getEquipmentById("hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList9 = equipmentManager0.getAllEquipment();
        org.junit.Assert.assertNotNull(equipmentList4);
        org.junit.Assert.assertNull(equipment8);
        org.junit.Assert.assertNotNull(equipmentList9);
    }

    @Test
    public void test049() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test049");
        org.example.reservation.ReservationStatus reservationStatus0 = org.example.reservation.ReservationStatus.PENDING;
        org.junit.Assert.assertTrue("'" + reservationStatus0 + "' != '" + org.example.reservation.ReservationStatus.PENDING + "'", reservationStatus0.equals(org.example.reservation.ReservationStatus.PENDING));
    }

    @Test
    public void test050() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test050");
        org.example.payment.UserPricingStrategy userPricingStrategy1 = new org.example.payment.UserPricingStrategy("");
        double double2 = userPricingStrategy1.calculateDeposit();
        double double3 = userPricingStrategy1.calculateDeposit();
        org.junit.Assert.assertTrue("'" + double2 + "' != '" + 0.0d + "'", double2 == 0.0d);
        org.junit.Assert.assertTrue("'" + double3 + "' != '" + 0.0d + "'", double3 == 0.0d);
    }

    @Test
    public void test051() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test051");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("STUDENT", "RESEARCHER", (double) (byte) -1, "RES-1775354801261", true);
    }

    @Test
    public void test052() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test052");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.User user3 = cSVRepository0.findUserByEmail("");
        org.example.users.User user5 = cSVRepository0.findUserById("RES-1775354801261");
        org.junit.Assert.assertNotNull(strArrayList1);
// flaky:         org.junit.Assert.assertNotNull(user3);
        org.junit.Assert.assertNull(user5);
    }

    @Test
    public void test053() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test053");
        org.example.users.Guest guest6 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str7 = guest6.getIdNumber();
        java.lang.String str8 = guest6.getEmail();
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
    }

    @Test
    public void test054() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test054");
        org.example.users.Guest guest6 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest6.setIdNumber("");
        guest6.setUserId("");
        org.example.users.UserDecorator userDecorator12 = new org.example.users.UserDecorator((org.example.users.User) guest6, "hi!");
        java.lang.String str13 = userDecorator12.getName();
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
    }

    @Test
    public void test055() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test055");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str6 = payment5.getReservationId();
        java.time.LocalDateTime localDateTime7 = payment5.getTimestamp();
        boolean boolean8 = payment5.isForfeited();
        org.example.payment.PaymentDecorator paymentDecorator11 = new org.example.payment.PaymentDecorator(payment5, "", "GUEST");
        org.example.payment.Payment payment12 = paymentDecorator11.process();
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "hi!" + "'", str6, "hi!");
        org.junit.Assert.assertNotNull(localDateTime7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNull(payment12);
    }

    @Test
    public void test056() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test056");
        org.example.equipment.EquipmentStatus equipmentStatus0 = org.example.equipment.EquipmentStatus.DISABLED;
        org.junit.Assert.assertTrue("'" + equipmentStatus0 + "' != '" + org.example.equipment.EquipmentStatus.DISABLED + "'", equipmentStatus0.equals(org.example.equipment.EquipmentStatus.DISABLED));
    }

    @Test
    public void test057() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test057");
        org.example.data.CSVHandler cSVHandler0 = org.example.data.CSVHandler.getInstance();
        org.example.data.CSVHandler cSVHandler3 = org.example.data.CSVHandler.getInstance();
        java.util.List<java.lang.String[]> strArrayList5 = cSVHandler3.readCSV("FACULTY");
        cSVHandler0.writeCSV("GUEST", "GUEST", strArrayList5);
        org.junit.Assert.assertNotNull(cSVHandler0);
        org.junit.Assert.assertNotNull(cSVHandler3);
        org.junit.Assert.assertNotNull(strArrayList5);
    }

    @Test
    public void test058() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test058");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.Guest guest8 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str9 = guest8.getUserType();
        cSVRepository0.updateUser((org.example.users.User) guest8);
        org.example.payment.Payment payment16 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime17 = payment16.getTimestamp();
        cSVRepository0.savePayment(payment16);
        java.lang.String str19 = payment16.getReservationId();
        boolean boolean20 = payment16.isDeposit();
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "GUEST" + "'", str9, "GUEST");
        org.junit.Assert.assertNotNull(localDateTime17);
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "hi!" + "'", str19, "hi!");
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
    }

    @Test
    public void test059() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test059");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.equipment.Equipment equipment5 = new org.example.equipment.Equipment("", "", "");
        int int6 = equipment5.getAvailableUnits();
        java.lang.String str7 = equipment5.getDescription();
        equipment5.setAvailableUnits((int) 'a');
        cSVRepository0.updateEquipment(equipment5);
        equipment5.setImagePath("hi!");
        java.lang.String str13 = equipment5.getDescription();
        org.example.equipment.Equipment equipment17 = new org.example.equipment.Equipment("", "", "");
        int int18 = equipment17.getAvailableUnits();
        java.lang.String str19 = equipment17.getDescription();
        equipment17.setAvailableUnits((int) 'a');
        equipment17.setName("");
        java.util.List<java.lang.String> strList24 = equipment17.getTags();
        equipment5.setTags(strList24);
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
        org.junit.Assert.assertTrue("'" + int18 + "' != '" + 1 + "'", int18 == 1);
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "" + "'", str19, "");
        org.junit.Assert.assertNotNull(strList24);
    }

    @Test
    public void test060() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test060");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        org.example.users.Guest guest10 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest10.setIdNumber("");
        java.lang.String str13 = guest10.getDepartmentId();
        org.example.equipment.Equipment equipment17 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus18 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment17.setStatus(equipmentStatus18);
        org.example.payment.Payment payment25 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str26 = payment25.getReservationId();
        java.time.LocalDateTime localDateTime27 = payment25.getTimestamp();
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str34 = payment33.getReservationId();
        java.time.LocalDateTime localDateTime35 = payment33.getTimestamp();
        org.example.reservation.Reservation reservation36 = reservationManager0.createReservation((org.example.users.User) guest10, equipment17, localDateTime27, localDateTime35);
        org.example.users.Guest guest44 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str45 = guest44.getIdNumber();
        org.example.equipment.Equipment equipment49 = new org.example.equipment.Equipment("", "", "");
        org.example.payment.Payment payment55 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str56 = payment55.getReservationId();
        java.time.LocalDateTime localDateTime57 = payment55.getTimestamp();
        org.example.payment.Payment payment63 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime64 = payment63.getTimestamp();
        org.example.payment.Payment payment70 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime71 = payment70.getTimestamp();
        payment63.setTimestamp(localDateTime71);
        org.example.reservation.Reservation reservation74 = new org.example.reservation.Reservation("hi!", (org.example.users.User) guest44, equipment49, localDateTime57, localDateTime71, (double) (byte) -1);
        org.example.reservation.ReservationAction reservationAction76 = new org.example.reservation.ReservationAction(reservationManager0, reservation74, "GUEST");
        reservationAction76.undo();
        reservationAction76.execute();
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!" + "'", str13, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus18 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus18.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hi!" + "'", str34, "hi!");
        org.junit.Assert.assertNotNull(localDateTime35);
        org.junit.Assert.assertNotNull(reservation36);
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "" + "'", str45, "");
        org.junit.Assert.assertEquals("'" + str56 + "' != '" + "hi!" + "'", str56, "hi!");
        org.junit.Assert.assertNotNull(localDateTime57);
        org.junit.Assert.assertNotNull(localDateTime64);
        org.junit.Assert.assertNotNull(localDateTime71);
    }

    @Test
    public void test061() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test061");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        org.example.users.Guest guest10 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest10.setIdNumber("");
        java.lang.String str13 = guest10.getDepartmentId();
        org.example.equipment.Equipment equipment17 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus18 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment17.setStatus(equipmentStatus18);
        org.example.payment.Payment payment25 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str26 = payment25.getReservationId();
        java.time.LocalDateTime localDateTime27 = payment25.getTimestamp();
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str34 = payment33.getReservationId();
        java.time.LocalDateTime localDateTime35 = payment33.getTimestamp();
        org.example.reservation.Reservation reservation36 = reservationManager0.createReservation((org.example.users.User) guest10, equipment17, localDateTime27, localDateTime35);
        long long37 = reservation36.getDurationHours();
        java.lang.String str38 = reservation36.getReservationId();
        org.example.users.User user39 = reservation36.getUser();
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!" + "'", str13, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus18 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus18.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hi!" + "'", str34, "hi!");
        org.junit.Assert.assertNotNull(localDateTime35);
        org.junit.Assert.assertNotNull(reservation36);
        org.junit.Assert.assertTrue("'" + long37 + "' != '" + 0L + "'", long37 == 0L);
// flaky:         org.junit.Assert.assertEquals("'" + str38 + "' != '" + "RES-1775354801691" + "'", str38, "RES-1775354801691");
        org.junit.Assert.assertNotNull(user39);
    }

    @Test
    public void test062() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test062");
        org.example.users.Guest guest6 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest6.setIdNumber("");
        guest6.setUserId("");
        org.example.users.UserDecorator userDecorator12 = new org.example.users.UserDecorator((org.example.users.User) guest6, "hi!");
        java.lang.String str13 = guest6.getEmail();
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
    }

    @Test
    public void test063() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test063");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation1 = null;
        java.time.LocalDateTime localDateTime3 = null;
        org.example.reservation.ReservationAction reservationAction4 = new org.example.reservation.ReservationAction(reservationManager0, reservation1, "", localDateTime3);
        java.util.List<java.lang.String[]> strArrayList5 = reservationManager0.getAllReservationRows();
        org.example.reservation.ReservationManager reservationManager6 = new org.example.reservation.ReservationManager();
        reservationManager6.update("hi!", "");
        org.example.users.Guest guest16 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest16.setIdNumber("");
        java.lang.String str19 = guest16.getDepartmentId();
        org.example.equipment.Equipment equipment23 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus24 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment23.setStatus(equipmentStatus24);
        org.example.payment.Payment payment31 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str32 = payment31.getReservationId();
        java.time.LocalDateTime localDateTime33 = payment31.getTimestamp();
        org.example.payment.Payment payment39 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str40 = payment39.getReservationId();
        java.time.LocalDateTime localDateTime41 = payment39.getTimestamp();
        org.example.reservation.Reservation reservation42 = reservationManager6.createReservation((org.example.users.User) guest16, equipment23, localDateTime33, localDateTime41);
        long long43 = reservation42.getDurationHours();
        org.example.payment.Payment payment46 = reservationManager0.processDeposit(reservation42, "", "FACULTY");
        reservationManager0.update("", "RESEARCHER");
        org.junit.Assert.assertNotNull(strArrayList5);
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "hi!" + "'", str19, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus24 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus24.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str32 + "' != '" + "hi!" + "'", str32, "hi!");
        org.junit.Assert.assertNotNull(localDateTime33);
        org.junit.Assert.assertEquals("'" + str40 + "' != '" + "hi!" + "'", str40, "hi!");
        org.junit.Assert.assertNotNull(localDateTime41);
        org.junit.Assert.assertNotNull(reservation42);
        org.junit.Assert.assertTrue("'" + long43 + "' != '" + 0L + "'", long43 == 0L);
        org.junit.Assert.assertNotNull(payment46);
    }

    @Test
    public void test064() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test064");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        org.example.users.Guest guest10 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest10.setIdNumber("");
        java.lang.String str13 = guest10.getDepartmentId();
        org.example.equipment.Equipment equipment17 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus18 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment17.setStatus(equipmentStatus18);
        org.example.payment.Payment payment25 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str26 = payment25.getReservationId();
        java.time.LocalDateTime localDateTime27 = payment25.getTimestamp();
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str34 = payment33.getReservationId();
        java.time.LocalDateTime localDateTime35 = payment33.getTimestamp();
        org.example.reservation.Reservation reservation36 = reservationManager0.createReservation((org.example.users.User) guest10, equipment17, localDateTime27, localDateTime35);
        org.example.reservation.ReservationManager reservationManager37 = new org.example.reservation.ReservationManager();
        reservationManager37.update("hi!", "");
        org.example.users.Guest guest47 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest47.setIdNumber("");
        java.lang.String str50 = guest47.getDepartmentId();
        org.example.equipment.Equipment equipment54 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus55 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment54.setStatus(equipmentStatus55);
        org.example.payment.Payment payment62 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str63 = payment62.getReservationId();
        java.time.LocalDateTime localDateTime64 = payment62.getTimestamp();
        org.example.payment.Payment payment70 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str71 = payment70.getReservationId();
        java.time.LocalDateTime localDateTime72 = payment70.getTimestamp();
        org.example.reservation.Reservation reservation73 = reservationManager37.createReservation((org.example.users.User) guest47, equipment54, localDateTime64, localDateTime72);
        reservationManager0.updateReservation(reservation73);
        org.example.reservation.Reservation reservation76 = reservationManager0.getReservationById("RES-1775354801691");
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!" + "'", str13, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus18 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus18.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hi!" + "'", str34, "hi!");
        org.junit.Assert.assertNotNull(localDateTime35);
        org.junit.Assert.assertNotNull(reservation36);
        org.junit.Assert.assertEquals("'" + str50 + "' != '" + "hi!" + "'", str50, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus55 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus55.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str63 + "' != '" + "hi!" + "'", str63, "hi!");
        org.junit.Assert.assertNotNull(localDateTime64);
        org.junit.Assert.assertEquals("'" + str71 + "' != '" + "hi!" + "'", str71, "hi!");
        org.junit.Assert.assertNotNull(localDateTime72);
        org.junit.Assert.assertNotNull(reservation73);
// flaky:         org.junit.Assert.assertNotNull(reservation76);
    }

    @Test
    public void test065() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test065");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation1 = null;
        java.time.LocalDateTime localDateTime3 = null;
        org.example.reservation.ReservationAction reservationAction4 = new org.example.reservation.ReservationAction(reservationManager0, reservation1, "", localDateTime3);
        java.util.List<java.lang.String[]> strArrayList5 = reservationManager0.getAllReservationRows();
        org.example.reservation.ReservationManager reservationManager6 = new org.example.reservation.ReservationManager();
        reservationManager6.update("hi!", "");
        org.example.users.Guest guest16 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest16.setIdNumber("");
        java.lang.String str19 = guest16.getDepartmentId();
        org.example.equipment.Equipment equipment23 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus24 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment23.setStatus(equipmentStatus24);
        org.example.payment.Payment payment31 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str32 = payment31.getReservationId();
        java.time.LocalDateTime localDateTime33 = payment31.getTimestamp();
        org.example.payment.Payment payment39 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str40 = payment39.getReservationId();
        java.time.LocalDateTime localDateTime41 = payment39.getTimestamp();
        org.example.reservation.Reservation reservation42 = reservationManager6.createReservation((org.example.users.User) guest16, equipment23, localDateTime33, localDateTime41);
        long long43 = reservation42.getDurationHours();
        org.example.payment.Payment payment46 = reservationManager0.processDeposit(reservation42, "", "FACULTY");
        org.example.payment.Payment payment52 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime53 = payment52.getTimestamp();
        org.example.payment.Payment payment59 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime60 = payment59.getTimestamp();
        payment52.setTimestamp(localDateTime60);
        boolean boolean62 = reservation42.arrivedOnTime(localDateTime60);
        java.time.LocalDateTime localDateTime63 = reservation42.getStartTime();
        org.junit.Assert.assertNotNull(strArrayList5);
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "hi!" + "'", str19, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus24 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus24.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str32 + "' != '" + "hi!" + "'", str32, "hi!");
        org.junit.Assert.assertNotNull(localDateTime33);
        org.junit.Assert.assertEquals("'" + str40 + "' != '" + "hi!" + "'", str40, "hi!");
        org.junit.Assert.assertNotNull(localDateTime41);
        org.junit.Assert.assertNotNull(reservation42);
        org.junit.Assert.assertTrue("'" + long43 + "' != '" + 0L + "'", long43 == 0L);
        org.junit.Assert.assertNotNull(payment46);
        org.junit.Assert.assertNotNull(localDateTime53);
        org.junit.Assert.assertNotNull(localDateTime60);
        org.junit.Assert.assertTrue("'" + boolean62 + "' != '" + true + "'", boolean62 == true);
        org.junit.Assert.assertNotNull(localDateTime63);
    }

    @Test
    public void test066() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test066");
        org.example.users.Faculty faculty6 = new org.example.users.Faculty("", "", "hi!", "hi!", "hi!", "hi!");
        java.lang.String str7 = faculty6.getUserType();
        java.lang.String str8 = faculty6.getEmail();
        java.lang.String str9 = faculty6.getName();
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "FACULTY" + "'", str7, "FACULTY");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "hi!" + "'", str8, "hi!");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
    }

    @Test
    public void test067() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test067");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("", "RESEARCHER", "RESEARCHER");
        java.lang.String str4 = equipment3.getProductStatistics();
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "" + "'", str4, "");
    }

    @Test
    public void test068() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test068");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        java.util.List<org.example.equipment.Equipment> equipmentList1 = equipmentManager0.getAllEquipment();
        org.example.equipment.EquipmentManager equipmentManager3 = new org.example.equipment.EquipmentManager();
        equipmentManager3.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList7 = equipmentManager3.getAllEquipment();
        equipmentManager3.disableEquipment("");
        org.example.equipment.EquipmentStatus equipmentStatus11 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipmentManager3.updateEquipmentStatus("hi!", equipmentStatus11);
        equipmentManager0.updateEquipmentStatus("hi!", equipmentStatus11);
        org.junit.Assert.assertNotNull(equipmentList1);
        org.junit.Assert.assertNotNull(equipmentList7);
        org.junit.Assert.assertTrue("'" + equipmentStatus11 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus11.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
    }

    @Test
    public void test069() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test069");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        org.example.users.Guest guest10 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest10.setIdNumber("");
        java.lang.String str13 = guest10.getDepartmentId();
        org.example.equipment.Equipment equipment17 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus18 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment17.setStatus(equipmentStatus18);
        org.example.payment.Payment payment25 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str26 = payment25.getReservationId();
        java.time.LocalDateTime localDateTime27 = payment25.getTimestamp();
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str34 = payment33.getReservationId();
        java.time.LocalDateTime localDateTime35 = payment33.getTimestamp();
        org.example.reservation.Reservation reservation36 = reservationManager0.createReservation((org.example.users.User) guest10, equipment17, localDateTime27, localDateTime35);
        org.example.reservation.ReservationManager reservationManager37 = new org.example.reservation.ReservationManager();
        reservationManager37.update("hi!", "");
        org.example.users.Guest guest47 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest47.setIdNumber("");
        java.lang.String str50 = guest47.getDepartmentId();
        org.example.equipment.Equipment equipment54 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus55 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment54.setStatus(equipmentStatus55);
        org.example.payment.Payment payment62 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str63 = payment62.getReservationId();
        java.time.LocalDateTime localDateTime64 = payment62.getTimestamp();
        org.example.payment.Payment payment70 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str71 = payment70.getReservationId();
        java.time.LocalDateTime localDateTime72 = payment70.getTimestamp();
        org.example.reservation.Reservation reservation73 = reservationManager37.createReservation((org.example.users.User) guest47, equipment54, localDateTime64, localDateTime72);
        reservationManager0.updateReservation(reservation73);
        reservationManager0.cancelReservation("hi!");
        reservationManager0.update("", "FACULTY");
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!" + "'", str13, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus18 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus18.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hi!" + "'", str34, "hi!");
        org.junit.Assert.assertNotNull(localDateTime35);
        org.junit.Assert.assertNotNull(reservation36);
        org.junit.Assert.assertEquals("'" + str50 + "' != '" + "hi!" + "'", str50, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus55 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus55.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str63 + "' != '" + "hi!" + "'", str63, "hi!");
        org.junit.Assert.assertNotNull(localDateTime64);
        org.junit.Assert.assertEquals("'" + str71 + "' != '" + "hi!" + "'", str71, "hi!");
        org.junit.Assert.assertNotNull(localDateTime72);
        org.junit.Assert.assertNotNull(reservation73);
    }

    @Test
    public void test070() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test070");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        reservationManager0.cancelReservation("");
        org.example.reservation.ReservationManager reservationManager7 = new org.example.reservation.ReservationManager();
        reservationManager7.update("hi!", "");
        org.example.users.Guest guest17 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest17.setIdNumber("");
        java.lang.String str20 = guest17.getDepartmentId();
        org.example.equipment.Equipment equipment24 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus25 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment24.setStatus(equipmentStatus25);
        org.example.payment.Payment payment32 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str33 = payment32.getReservationId();
        java.time.LocalDateTime localDateTime34 = payment32.getTimestamp();
        org.example.payment.Payment payment40 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str41 = payment40.getReservationId();
        java.time.LocalDateTime localDateTime42 = payment40.getTimestamp();
        org.example.reservation.Reservation reservation43 = reservationManager7.createReservation((org.example.users.User) guest17, equipment24, localDateTime34, localDateTime42);
        reservationManager0.modifyReservation("GUEST", localDateTime34);
        org.example.payment.Payment payment51 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str52 = payment51.getReservationId();
        java.time.LocalDateTime localDateTime53 = payment51.getTimestamp();
        boolean boolean54 = payment51.isForfeited();
        payment51.setForfeited(false);
        java.time.LocalDateTime localDateTime57 = payment51.getTimestamp();
        reservationManager0.modifyReservation("RES-1775354801261", localDateTime57);
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "hi!" + "'", str20, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus25 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus25.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "hi!" + "'", str33, "hi!");
        org.junit.Assert.assertNotNull(localDateTime34);
        org.junit.Assert.assertEquals("'" + str41 + "' != '" + "hi!" + "'", str41, "hi!");
        org.junit.Assert.assertNotNull(localDateTime42);
        org.junit.Assert.assertNotNull(reservation43);
        org.junit.Assert.assertEquals("'" + str52 + "' != '" + "hi!" + "'", str52, "hi!");
        org.junit.Assert.assertNotNull(localDateTime53);
        org.junit.Assert.assertTrue("'" + boolean54 + "' != '" + false + "'", boolean54 == false);
        org.junit.Assert.assertNotNull(localDateTime57);
    }

    @Test
    public void test071() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test071");
        org.example.users.Guest guest6 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest6.setIdNumber("");
        guest6.setPassword("");
        org.example.users.UserDecorator userDecorator12 = new org.example.users.UserDecorator((org.example.users.User) guest6, "");
        java.lang.Class<?> wildcardClass13 = guest6.getClass();
        org.junit.Assert.assertNotNull(wildcardClass13);
    }

    @Test
    public void test072() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test072");
        org.example.sensors.EquipmentSensor equipmentSensor1 = new org.example.sensors.EquipmentSensor("");
    }

    @Test
    public void test073() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test073");
        org.example.users.Guest guest7 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str8 = guest7.getIdNumber();
        org.example.equipment.Equipment equipment12 = new org.example.equipment.Equipment("", "", "");
        org.example.payment.Payment payment18 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str19 = payment18.getReservationId();
        java.time.LocalDateTime localDateTime20 = payment18.getTimestamp();
        org.example.payment.Payment payment26 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime27 = payment26.getTimestamp();
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime34 = payment33.getTimestamp();
        payment26.setTimestamp(localDateTime34);
        org.example.reservation.Reservation reservation37 = new org.example.reservation.Reservation("hi!", (org.example.users.User) guest7, equipment12, localDateTime20, localDateTime34, (double) (byte) -1);
        java.lang.String str38 = guest7.getEmail();
        java.lang.String str39 = guest7.getName();
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "hi!" + "'", str19, "hi!");
        org.junit.Assert.assertNotNull(localDateTime20);
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertNotNull(localDateTime34);
        org.junit.Assert.assertEquals("'" + str38 + "' != '" + "" + "'", str38, "");
        org.junit.Assert.assertEquals("'" + str39 + "' != '" + "" + "'", str39, "");
    }

    @Test
    public void test074() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test074");
        org.example.users.Faculty faculty6 = new org.example.users.Faculty("hi!", "AVAILABLE", "RESEARCHER", "RESEARCHER", "GUEST", "RES-1775354801691");
        java.lang.String str7 = faculty6.getUserType();
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "FACULTY" + "'", str7, "FACULTY");
    }

    @Test
    public void test075() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test075");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.equipment.Equipment equipment5 = new org.example.equipment.Equipment("", "", "");
        int int6 = equipment5.getAvailableUnits();
        java.lang.String str7 = equipment5.getDescription();
        equipment5.setAvailableUnits((int) 'a');
        cSVRepository0.updateEquipment(equipment5);
        equipment5.setImagePath("hi!");
        java.lang.String str13 = equipment5.getName();
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
    }

    @Test
    public void test076() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test076");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        org.example.users.Guest guest10 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest10.setIdNumber("");
        java.lang.String str13 = guest10.getDepartmentId();
        org.example.equipment.Equipment equipment17 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus18 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment17.setStatus(equipmentStatus18);
        org.example.payment.Payment payment25 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str26 = payment25.getReservationId();
        java.time.LocalDateTime localDateTime27 = payment25.getTimestamp();
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str34 = payment33.getReservationId();
        java.time.LocalDateTime localDateTime35 = payment33.getTimestamp();
        org.example.reservation.Reservation reservation36 = reservationManager0.createReservation((org.example.users.User) guest10, equipment17, localDateTime27, localDateTime35);
        org.example.users.Guest guest44 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str45 = guest44.getIdNumber();
        org.example.equipment.Equipment equipment49 = new org.example.equipment.Equipment("", "", "");
        org.example.payment.Payment payment55 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str56 = payment55.getReservationId();
        java.time.LocalDateTime localDateTime57 = payment55.getTimestamp();
        org.example.payment.Payment payment63 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime64 = payment63.getTimestamp();
        org.example.payment.Payment payment70 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime71 = payment70.getTimestamp();
        payment63.setTimestamp(localDateTime71);
        org.example.reservation.Reservation reservation74 = new org.example.reservation.Reservation("hi!", (org.example.users.User) guest44, equipment49, localDateTime57, localDateTime71, (double) (byte) -1);
        org.example.reservation.ReservationAction reservationAction76 = new org.example.reservation.ReservationAction(reservationManager0, reservation74, "GUEST");
        reservationAction76.undo();
        reservationAction76.undo();
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!" + "'", str13, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus18 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus18.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hi!" + "'", str34, "hi!");
        org.junit.Assert.assertNotNull(localDateTime35);
        org.junit.Assert.assertNotNull(reservation36);
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "" + "'", str45, "");
        org.junit.Assert.assertEquals("'" + str56 + "' != '" + "hi!" + "'", str56, "hi!");
        org.junit.Assert.assertNotNull(localDateTime57);
        org.junit.Assert.assertNotNull(localDateTime64);
        org.junit.Assert.assertNotNull(localDateTime71);
    }

    @Test
    public void test077() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test077");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation1 = null;
        java.time.LocalDateTime localDateTime3 = null;
        org.example.reservation.ReservationAction reservationAction4 = new org.example.reservation.ReservationAction(reservationManager0, reservation1, "", localDateTime3);
        java.util.List<java.lang.String[]> strArrayList5 = reservationManager0.getAllReservationRows();
        reservationManager0.update("hi!", "");
        org.junit.Assert.assertNotNull(strArrayList5);
    }

    @Test
    public void test078() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test078");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str6 = payment5.getReservationId();
        java.time.LocalDateTime localDateTime7 = payment5.getTimestamp();
        boolean boolean8 = payment5.isForfeited();
        org.example.payment.PaymentDecorator paymentDecorator11 = new org.example.payment.PaymentDecorator(payment5, "", "GUEST");
        boolean boolean12 = payment5.isForfeited();
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "hi!" + "'", str6, "hi!");
        org.junit.Assert.assertNotNull(localDateTime7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
    }

    @Test
    public void test079() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test079");
        org.example.payment.UserPricingStrategy userPricingStrategy1 = new org.example.payment.UserPricingStrategy("FACULTY");
        double double3 = userPricingStrategy1.calculateTotal((int) (short) 1);
        org.junit.Assert.assertTrue("'" + double3 + "' != '" + 15.0d + "'", double3 == 15.0d);
    }

    @Test
    public void test080() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test080");
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
        org.example.data.CSVRepository cSVRepository35 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList36 = cSVRepository35.getAllPaymentRows();
        org.example.users.User user38 = cSVRepository35.findUserByEmail("");
        org.example.equipment.Equipment equipment42 = new org.example.equipment.Equipment("", "", "");
        int int43 = equipment42.getAvailableUnits();
        equipment42.setName("");
        int int46 = equipment42.getAvailableUnits();
        cSVRepository35.saveEquipment(equipment42);
        org.example.equipment.Equipment equipment51 = new org.example.equipment.Equipment("", "", "");
        cSVRepository35.updateEquipment(equipment51);
        java.util.List<java.lang.String[]> strArrayList54 = cSVRepository35.getReservationRowsByUserId("");
        cSVHandler0.writeCSV("RES-1775354801261", "FACULTY", strArrayList54);
        org.junit.Assert.assertNotNull(cSVHandler0);
        org.junit.Assert.assertNotNull(strArrayList2);
        org.junit.Assert.assertNotNull(strArrayList6);
// flaky:         org.junit.Assert.assertNotNull(user8);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 1 + "'", int13 == 1);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 1 + "'", int16 == 1);
        org.junit.Assert.assertNotNull(strArrayList24);
        org.junit.Assert.assertNotNull(strArray31);
        org.junit.Assert.assertNotNull(strArrayList36);
// flaky:         org.junit.Assert.assertNotNull(user38);
        org.junit.Assert.assertTrue("'" + int43 + "' != '" + 1 + "'", int43 == 1);
        org.junit.Assert.assertTrue("'" + int46 + "' != '" + 1 + "'", int46 == 1);
        org.junit.Assert.assertNotNull(strArrayList54);
    }

    @Test
    public void test081() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test081");
        org.example.payment.UserPricingStrategy userPricingStrategy1 = new org.example.payment.UserPricingStrategy("");
        double double2 = userPricingStrategy1.calculateDeposit();
        double double3 = userPricingStrategy1.getHourlyRate();
        double double4 = userPricingStrategy1.getHourlyRate();
        org.junit.Assert.assertTrue("'" + double2 + "' != '" + 0.0d + "'", double2 == 0.0d);
        org.junit.Assert.assertTrue("'" + double3 + "' != '" + 0.0d + "'", double3 == 0.0d);
        org.junit.Assert.assertTrue("'" + double4 + "' != '" + 0.0d + "'", double4 == 0.0d);
    }

    @Test
    public void test082() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test082");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        java.util.List<java.lang.String[]> strArrayList3 = cSVRepository0.getPaymentRowsByReservationId("AVAILABLE");
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertNotNull(strArrayList3);
    }

    @Test
    public void test083() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test083");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.User user3 = cSVRepository0.findUserByEmail("");
        org.example.equipment.Equipment equipment7 = new org.example.equipment.Equipment("", "", "");
        int int8 = equipment7.getAvailableUnits();
        equipment7.setName("");
        int int11 = equipment7.getAvailableUnits();
        cSVRepository0.saveEquipment(equipment7);
        equipment7.setProductStatistics("AVAILABLE");
        org.junit.Assert.assertNotNull(strArrayList1);
// flaky:         org.junit.Assert.assertNotNull(user3);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 1 + "'", int8 == 1);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 1 + "'", int11 == 1);
    }

    @Test
    public void test084() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test084");
        org.example.users.Guest guest6 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest6.setIdNumber("");
        guest6.setPassword("");
        java.lang.String str11 = guest6.getUserType();
        java.lang.String str12 = guest6.getName();
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "GUEST" + "'", str11, "GUEST");
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "" + "'", str12, "");
    }

    @Test
    public void test085() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test085");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("RESEARCHER", "RESEARCHER", (double) (byte) 0, "AVAILABLE", true);
    }

    @Test
    public void test086() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test086");
        org.example.auth.AuthService authService0 = new org.example.auth.AuthService();
        org.example.users.User user3 = authService0.login("hi!", "");
        boolean boolean5 = authService0.isApproved("");
        org.junit.Assert.assertNull(user3);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test087() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test087");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str6 = payment5.getReservationId();
        java.time.LocalDateTime localDateTime7 = payment5.getTimestamp();
        boolean boolean8 = payment5.isForfeited();
        org.example.payment.PaymentDecorator paymentDecorator11 = new org.example.payment.PaymentDecorator(payment5, "", "GUEST");
        boolean boolean12 = paymentDecorator11.validate();
        boolean boolean13 = paymentDecorator11.validate();
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "hi!" + "'", str6, "hi!");
        org.junit.Assert.assertNotNull(localDateTime7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
    }

    @Test
    public void test088() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test088");
        org.example.users.Guest guest6 = new org.example.users.Guest("STUDENT", "STUDENT", "STUDENT", "AVAILABLE", "", "RESEARCHER");
    }

    @Test
    public void test089() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test089");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        org.example.reservation.ReservationManager reservationManager4 = new org.example.reservation.ReservationManager();
        reservationManager4.update("hi!", "");
        org.example.users.Guest guest14 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest14.setIdNumber("");
        java.lang.String str17 = guest14.getDepartmentId();
        org.example.equipment.Equipment equipment21 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus22 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment21.setStatus(equipmentStatus22);
        org.example.payment.Payment payment29 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str30 = payment29.getReservationId();
        java.time.LocalDateTime localDateTime31 = payment29.getTimestamp();
        org.example.payment.Payment payment37 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str38 = payment37.getReservationId();
        java.time.LocalDateTime localDateTime39 = payment37.getTimestamp();
        org.example.reservation.Reservation reservation40 = reservationManager4.createReservation((org.example.users.User) guest14, equipment21, localDateTime31, localDateTime39);
        org.example.users.Guest guest48 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str49 = guest48.getIdNumber();
        org.example.equipment.Equipment equipment53 = new org.example.equipment.Equipment("", "", "");
        org.example.payment.Payment payment59 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str60 = payment59.getReservationId();
        java.time.LocalDateTime localDateTime61 = payment59.getTimestamp();
        org.example.payment.Payment payment67 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime68 = payment67.getTimestamp();
        org.example.payment.Payment payment74 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime75 = payment74.getTimestamp();
        payment67.setTimestamp(localDateTime75);
        org.example.reservation.Reservation reservation78 = new org.example.reservation.Reservation("hi!", (org.example.users.User) guest48, equipment53, localDateTime61, localDateTime75, (double) (byte) -1);
        org.example.reservation.ReservationAction reservationAction80 = new org.example.reservation.ReservationAction(reservationManager4, reservation78, "GUEST");
        org.example.equipment.Equipment equipment81 = reservation78.getEquipment();
        java.time.LocalDateTime localDateTime82 = reservation78.getEndTime();
        org.example.payment.Payment payment89 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str90 = payment89.getReservationId();
        java.time.LocalDateTime localDateTime91 = payment89.getTimestamp();
        org.example.reservation.ReservationAction reservationAction92 = new org.example.reservation.ReservationAction(reservationManager0, reservation78, "AVAILABLE", localDateTime91);
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "hi!" + "'", str17, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus22 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus22.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str30 + "' != '" + "hi!" + "'", str30, "hi!");
        org.junit.Assert.assertNotNull(localDateTime31);
        org.junit.Assert.assertEquals("'" + str38 + "' != '" + "hi!" + "'", str38, "hi!");
        org.junit.Assert.assertNotNull(localDateTime39);
        org.junit.Assert.assertNotNull(reservation40);
        org.junit.Assert.assertEquals("'" + str49 + "' != '" + "" + "'", str49, "");
        org.junit.Assert.assertEquals("'" + str60 + "' != '" + "hi!" + "'", str60, "hi!");
        org.junit.Assert.assertNotNull(localDateTime61);
        org.junit.Assert.assertNotNull(localDateTime68);
        org.junit.Assert.assertNotNull(localDateTime75);
        org.junit.Assert.assertNotNull(equipment81);
        org.junit.Assert.assertNotNull(localDateTime82);
        org.junit.Assert.assertEquals("'" + str90 + "' != '" + "hi!" + "'", str90, "hi!");
        org.junit.Assert.assertNotNull(localDateTime91);
    }

    @Test
    public void test090() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test090");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("", "", "");
        int int4 = equipment3.getAvailableUnits();
        java.lang.String str5 = equipment3.getDescription();
        equipment3.setAvailableUnits((int) 'a');
        java.lang.String str8 = equipment3.getName();
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 1 + "'", int4 == 1);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
    }

    @Test
    public void test091() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test091");
        org.example.users.Student student6 = new org.example.users.Student("RES-1775354801691", "FACULTY", "FACULTY", "GUEST", "AVAILABLE", "FACULTY");
    }

    @Test
    public void test092() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test092");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        java.util.List<org.example.equipment.Equipment> equipmentList1 = equipmentManager0.getAllEquipment();
        org.example.equipment.EquipmentStatus equipmentStatus3 = null;
        equipmentManager0.updateEquipmentStatus("RESEARCHER", equipmentStatus3);
        org.junit.Assert.assertNotNull(equipmentList1);
    }

    @Test
    public void test093() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test093");
        org.example.reservation.ReservationManager reservationManager0 = null;
        org.example.reservation.Reservation reservation1 = null;
        org.example.reservation.ReservationAction reservationAction3 = new org.example.reservation.ReservationAction(reservationManager0, reservation1, "hi!");
        reservationAction3.execute();
        reservationAction3.execute();
    }

    @Test
    public void test094() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test094");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.Guest guest8 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str9 = guest8.getUserType();
        cSVRepository0.updateUser((org.example.users.User) guest8);
        org.example.payment.Payment payment16 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime17 = payment16.getTimestamp();
        org.example.payment.PaymentDecorator paymentDecorator20 = new org.example.payment.PaymentDecorator(payment16, "", "hi!");
        cSVRepository0.savePayment(payment16);
        java.util.List<org.example.users.User> userList22 = cSVRepository0.getAllUsers();
        java.util.List<java.lang.String[]> strArrayList24 = cSVRepository0.getReservationRowsByUserId("FACULTY");
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "GUEST" + "'", str9, "GUEST");
        org.junit.Assert.assertNotNull(localDateTime17);
        org.junit.Assert.assertNotNull(userList22);
        org.junit.Assert.assertNotNull(strArrayList24);
    }

    @Test
    public void test095() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test095");
        org.example.users.LabManager labManager5 = new org.example.users.LabManager("", "", "GUEST", "FACULTY", "STUDENT");
        java.lang.String str6 = labManager5.getManagerId();
        labManager5.setName("STUDENT");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "STUDENT" + "'", str6, "STUDENT");
    }

    @Test
    public void test096() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test096");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.Guest guest8 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str9 = guest8.getUserType();
        cSVRepository0.updateUser((org.example.users.User) guest8);
        org.example.payment.Payment payment16 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime17 = payment16.getTimestamp();
        cSVRepository0.savePayment(payment16);
        java.lang.String str19 = payment16.getReservationId();
        java.lang.String str20 = payment16.getPaymentMethod();
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "GUEST" + "'", str9, "GUEST");
        org.junit.Assert.assertNotNull(localDateTime17);
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "hi!" + "'", str19, "hi!");
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "hi!" + "'", str20, "hi!");
    }

    @Test
    public void test097() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test097");
        org.example.sensors.EquipmentSensor equipmentSensor1 = new org.example.sensors.EquipmentSensor("RESEARCHER");
        equipmentSensor1.notifyObservers("", "FACULTY");
        java.lang.String str5 = equipmentSensor1.getCurrentStatus();
        equipmentSensor1.notifyObservers("RESEARCHER", "RES-1775354801261");
        org.example.equipment.EquipmentManager equipmentManager9 = new org.example.equipment.EquipmentManager();
        equipmentManager9.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList13 = equipmentManager9.getAllEquipment();
        equipmentManager9.enableEquipment("hi!");
        org.example.equipment.Equipment equipment17 = equipmentManager9.getEquipmentById("hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList18 = equipmentManager9.getAvailableEquipment();
        java.util.List<org.example.equipment.Equipment> equipmentList19 = equipmentManager9.getAllEquipment();
        equipmentSensor1.addObserver((org.example.sensors.SensorObserver) equipmentManager9);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "AVAILABLE" + "'", str5, "AVAILABLE");
        org.junit.Assert.assertNotNull(equipmentList13);
        org.junit.Assert.assertNull(equipment17);
        org.junit.Assert.assertNotNull(equipmentList18);
        org.junit.Assert.assertNotNull(equipmentList19);
    }

    @Test
    public void test098() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test098");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.User user3 = cSVRepository0.findUserByEmail("");
        org.example.equipment.Equipment equipment7 = new org.example.equipment.Equipment("", "", "");
        int int8 = equipment7.getAvailableUnits();
        equipment7.setName("");
        int int11 = equipment7.getAvailableUnits();
        cSVRepository0.saveEquipment(equipment7);
        org.example.equipment.Equipment equipment16 = new org.example.equipment.Equipment("", "", "");
        int int17 = equipment16.getAvailableUnits();
        java.lang.String str18 = equipment16.getDescription();
        java.lang.String str19 = equipment16.getEquipmentId();
        cSVRepository0.updateEquipment(equipment16);
        java.lang.String str21 = equipment16.getName();
        org.junit.Assert.assertNotNull(strArrayList1);
// flaky:         org.junit.Assert.assertNotNull(user3);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 1 + "'", int8 == 1);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 1 + "'", int11 == 1);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + 1 + "'", int17 == 1);
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "" + "'", str18, "");
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "" + "'", str19, "");
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "" + "'", str21, "");
    }

    @Test
    public void test099() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test099");
        org.example.users.Faculty faculty6 = new org.example.users.Faculty("", "", "hi!", "hi!", "hi!", "hi!");
        faculty6.setIdNumber("GUEST");
    }

    @Test
    public void test100() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test100");
        org.example.reservation.ReservationStatus reservationStatus0 = org.example.reservation.ReservationStatus.COMPLETED;
        org.junit.Assert.assertTrue("'" + reservationStatus0 + "' != '" + org.example.reservation.ReservationStatus.COMPLETED + "'", reservationStatus0.equals(org.example.reservation.ReservationStatus.COMPLETED));
    }

    @Test
    public void test101() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test101");
        boolean boolean1 = org.example.auth.PasswordValidator.isValid("GUEST");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test102() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test102");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation1 = null;
        java.time.LocalDateTime localDateTime3 = null;
        org.example.reservation.ReservationAction reservationAction4 = new org.example.reservation.ReservationAction(reservationManager0, reservation1, "", localDateTime3);
        java.util.List<java.lang.String[]> strArrayList5 = reservationManager0.getAllReservationRows();
        org.example.reservation.ReservationManager reservationManager6 = new org.example.reservation.ReservationManager();
        reservationManager6.update("hi!", "");
        org.example.users.Guest guest16 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest16.setIdNumber("");
        java.lang.String str19 = guest16.getDepartmentId();
        org.example.equipment.Equipment equipment23 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus24 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment23.setStatus(equipmentStatus24);
        org.example.payment.Payment payment31 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str32 = payment31.getReservationId();
        java.time.LocalDateTime localDateTime33 = payment31.getTimestamp();
        org.example.payment.Payment payment39 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str40 = payment39.getReservationId();
        java.time.LocalDateTime localDateTime41 = payment39.getTimestamp();
        org.example.reservation.Reservation reservation42 = reservationManager6.createReservation((org.example.users.User) guest16, equipment23, localDateTime33, localDateTime41);
        long long43 = reservation42.getDurationHours();
        org.example.payment.Payment payment46 = reservationManager0.processDeposit(reservation42, "", "FACULTY");
        org.example.payment.Payment payment52 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime53 = payment52.getTimestamp();
        org.example.payment.Payment payment59 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime60 = payment59.getTimestamp();
        payment52.setTimestamp(localDateTime60);
        boolean boolean62 = reservation42.arrivedOnTime(localDateTime60);
        double double64 = reservation42.calculateTotal((double) 10L);
        double double66 = reservation42.calculateTotal(100.0d);
        org.junit.Assert.assertNotNull(strArrayList5);
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "hi!" + "'", str19, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus24 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus24.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str32 + "' != '" + "hi!" + "'", str32, "hi!");
        org.junit.Assert.assertNotNull(localDateTime33);
        org.junit.Assert.assertEquals("'" + str40 + "' != '" + "hi!" + "'", str40, "hi!");
        org.junit.Assert.assertNotNull(localDateTime41);
        org.junit.Assert.assertNotNull(reservation42);
        org.junit.Assert.assertTrue("'" + long43 + "' != '" + 0L + "'", long43 == 0L);
        org.junit.Assert.assertNotNull(payment46);
        org.junit.Assert.assertNotNull(localDateTime53);
        org.junit.Assert.assertNotNull(localDateTime60);
        org.junit.Assert.assertTrue("'" + boolean62 + "' != '" + true + "'", boolean62 == true);
        org.junit.Assert.assertTrue("'" + double64 + "' != '" + 0.0d + "'", double64 == 0.0d);
        org.junit.Assert.assertTrue("'" + double66 + "' != '" + 0.0d + "'", double66 == 0.0d);
    }

    @Test
    public void test103() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test103");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        org.example.users.Guest guest10 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest10.setIdNumber("");
        java.lang.String str13 = guest10.getDepartmentId();
        org.example.equipment.Equipment equipment17 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus18 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment17.setStatus(equipmentStatus18);
        org.example.payment.Payment payment25 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str26 = payment25.getReservationId();
        java.time.LocalDateTime localDateTime27 = payment25.getTimestamp();
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str34 = payment33.getReservationId();
        java.time.LocalDateTime localDateTime35 = payment33.getTimestamp();
        org.example.reservation.Reservation reservation36 = reservationManager0.createReservation((org.example.users.User) guest10, equipment17, localDateTime27, localDateTime35);
        org.example.payment.Payment payment43 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str44 = payment43.getReservationId();
        java.time.LocalDateTime localDateTime45 = payment43.getTimestamp();
        boolean boolean46 = payment43.isForfeited();
        double double47 = payment43.getAmount();
        java.time.LocalDateTime localDateTime48 = payment43.getTimestamp();
        reservationManager0.modifyReservation("AVAILABLE", localDateTime48);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!" + "'", str13, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus18 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus18.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hi!" + "'", str34, "hi!");
        org.junit.Assert.assertNotNull(localDateTime35);
        org.junit.Assert.assertNotNull(reservation36);
        org.junit.Assert.assertEquals("'" + str44 + "' != '" + "hi!" + "'", str44, "hi!");
        org.junit.Assert.assertNotNull(localDateTime45);
        org.junit.Assert.assertTrue("'" + boolean46 + "' != '" + false + "'", boolean46 == false);
        org.junit.Assert.assertTrue("'" + double47 + "' != '" + 10.0d + "'", double47 == 10.0d);
        org.junit.Assert.assertNotNull(localDateTime48);
    }

    @Test
    public void test104() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test104");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.Guest guest8 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str9 = guest8.getUserType();
        cSVRepository0.updateUser((org.example.users.User) guest8);
        org.example.payment.Payment payment16 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime17 = payment16.getTimestamp();
        org.example.payment.PaymentDecorator paymentDecorator20 = new org.example.payment.PaymentDecorator(payment16, "", "hi!");
        cSVRepository0.savePayment(payment16);
        org.example.users.User user23 = cSVRepository0.findUserById("FACULTY");
        java.util.List<org.example.equipment.Equipment> equipmentList24 = cSVRepository0.getAllEquipment();
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "GUEST" + "'", str9, "GUEST");
        org.junit.Assert.assertNotNull(localDateTime17);
        org.junit.Assert.assertNull(user23);
        org.junit.Assert.assertNotNull(equipmentList24);
    }

    @Test
    public void test105() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test105");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        equipmentManager0.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList4 = equipmentManager0.getAllEquipment();
        equipmentManager0.disableEquipment("");
        java.util.List<org.example.equipment.Equipment> equipmentList7 = equipmentManager0.getAvailableEquipment();
        equipmentManager0.update("", "hi!");
        org.example.equipment.Equipment equipment12 = equipmentManager0.getEquipmentById("hi!");
        org.junit.Assert.assertNotNull(equipmentList4);
        org.junit.Assert.assertNotNull(equipmentList7);
        org.junit.Assert.assertNull(equipment12);
    }

    @Test
    public void test106() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test106");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        java.util.List<org.example.equipment.Equipment> equipmentList1 = equipmentManager0.getAllEquipment();
        java.util.List<org.example.equipment.Equipment> equipmentList2 = equipmentManager0.getAvailableEquipment();
        org.junit.Assert.assertNotNull(equipmentList1);
        org.junit.Assert.assertNotNull(equipmentList2);
    }

    @Test
    public void test107() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test107");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("", "", "");
        int int4 = equipment3.getAvailableUnits();
        equipment3.setName("");
        java.lang.String str7 = equipment3.getName();
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 1 + "'", int4 == 1);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
    }

    @Test
    public void test108() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test108");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        org.example.users.Guest guest10 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest10.setIdNumber("");
        java.lang.String str13 = guest10.getDepartmentId();
        org.example.equipment.Equipment equipment17 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus18 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment17.setStatus(equipmentStatus18);
        org.example.payment.Payment payment25 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str26 = payment25.getReservationId();
        java.time.LocalDateTime localDateTime27 = payment25.getTimestamp();
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str34 = payment33.getReservationId();
        java.time.LocalDateTime localDateTime35 = payment33.getTimestamp();
        org.example.reservation.Reservation reservation36 = reservationManager0.createReservation((org.example.users.User) guest10, equipment17, localDateTime27, localDateTime35);
        org.example.users.Guest guest44 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str45 = guest44.getIdNumber();
        org.example.equipment.Equipment equipment49 = new org.example.equipment.Equipment("", "", "");
        org.example.payment.Payment payment55 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str56 = payment55.getReservationId();
        java.time.LocalDateTime localDateTime57 = payment55.getTimestamp();
        org.example.payment.Payment payment63 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime64 = payment63.getTimestamp();
        org.example.payment.Payment payment70 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime71 = payment70.getTimestamp();
        payment63.setTimestamp(localDateTime71);
        org.example.reservation.Reservation reservation74 = new org.example.reservation.Reservation("hi!", (org.example.users.User) guest44, equipment49, localDateTime57, localDateTime71, (double) (byte) -1);
        org.example.reservation.ReservationAction reservationAction76 = new org.example.reservation.ReservationAction(reservationManager0, reservation74, "GUEST");
        org.example.reservation.ReservationStatus reservationStatus77 = org.example.reservation.ReservationStatus.CANCELLED;
        reservation74.setStatus(reservationStatus77);
        java.time.LocalDateTime localDateTime79 = reservation74.getStartTime();
        java.time.LocalDateTime localDateTime80 = null;
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean81 = reservation74.arrivedOnTime(localDateTime80);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"java.time.LocalDateTime.isAfter(java.time.chrono.ChronoLocalDateTime)\" because \"arrivalTime\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!" + "'", str13, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus18 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus18.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hi!" + "'", str34, "hi!");
        org.junit.Assert.assertNotNull(localDateTime35);
        org.junit.Assert.assertNotNull(reservation36);
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "" + "'", str45, "");
        org.junit.Assert.assertEquals("'" + str56 + "' != '" + "hi!" + "'", str56, "hi!");
        org.junit.Assert.assertNotNull(localDateTime57);
        org.junit.Assert.assertNotNull(localDateTime64);
        org.junit.Assert.assertNotNull(localDateTime71);
        org.junit.Assert.assertTrue("'" + reservationStatus77 + "' != '" + org.example.reservation.ReservationStatus.CANCELLED + "'", reservationStatus77.equals(org.example.reservation.ReservationStatus.CANCELLED));
        org.junit.Assert.assertNotNull(localDateTime79);
    }

    @Test
    public void test109() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test109");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        org.example.users.Guest guest10 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest10.setIdNumber("");
        java.lang.String str13 = guest10.getDepartmentId();
        org.example.equipment.Equipment equipment17 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus18 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment17.setStatus(equipmentStatus18);
        org.example.payment.Payment payment25 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str26 = payment25.getReservationId();
        java.time.LocalDateTime localDateTime27 = payment25.getTimestamp();
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str34 = payment33.getReservationId();
        java.time.LocalDateTime localDateTime35 = payment33.getTimestamp();
        org.example.reservation.Reservation reservation36 = reservationManager0.createReservation((org.example.users.User) guest10, equipment17, localDateTime27, localDateTime35);
        org.example.users.Guest guest44 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str45 = guest44.getIdNumber();
        org.example.equipment.Equipment equipment49 = new org.example.equipment.Equipment("", "", "");
        org.example.payment.Payment payment55 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str56 = payment55.getReservationId();
        java.time.LocalDateTime localDateTime57 = payment55.getTimestamp();
        org.example.payment.Payment payment63 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime64 = payment63.getTimestamp();
        org.example.payment.Payment payment70 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime71 = payment70.getTimestamp();
        payment63.setTimestamp(localDateTime71);
        org.example.reservation.Reservation reservation74 = new org.example.reservation.Reservation("hi!", (org.example.users.User) guest44, equipment49, localDateTime57, localDateTime71, (double) (byte) -1);
        org.example.reservation.ReservationAction reservationAction76 = new org.example.reservation.ReservationAction(reservationManager0, reservation74, "GUEST");
        double double77 = reservation74.getDeposit();
        org.example.users.User user78 = reservation74.getUser();
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!" + "'", str13, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus18 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus18.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hi!" + "'", str34, "hi!");
        org.junit.Assert.assertNotNull(localDateTime35);
        org.junit.Assert.assertNotNull(reservation36);
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "" + "'", str45, "");
        org.junit.Assert.assertEquals("'" + str56 + "' != '" + "hi!" + "'", str56, "hi!");
        org.junit.Assert.assertNotNull(localDateTime57);
        org.junit.Assert.assertNotNull(localDateTime64);
        org.junit.Assert.assertNotNull(localDateTime71);
        org.junit.Assert.assertTrue("'" + double77 + "' != '" + (-1.0d) + "'", double77 == (-1.0d));
        org.junit.Assert.assertNotNull(user78);
    }

    @Test
    public void test110() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test110");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        org.example.users.Guest guest10 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest10.setIdNumber("");
        java.lang.String str13 = guest10.getDepartmentId();
        org.example.equipment.Equipment equipment17 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus18 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment17.setStatus(equipmentStatus18);
        org.example.payment.Payment payment25 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str26 = payment25.getReservationId();
        java.time.LocalDateTime localDateTime27 = payment25.getTimestamp();
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str34 = payment33.getReservationId();
        java.time.LocalDateTime localDateTime35 = payment33.getTimestamp();
        org.example.reservation.Reservation reservation36 = reservationManager0.createReservation((org.example.users.User) guest10, equipment17, localDateTime27, localDateTime35);
        org.example.payment.Payment payment42 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime43 = payment42.getTimestamp();
        reservation36.setEndTime(localDateTime43);
        org.example.equipment.Equipment equipment45 = reservation36.getEquipment();
        org.example.users.User user46 = reservation36.getUser();
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!" + "'", str13, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus18 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus18.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hi!" + "'", str34, "hi!");
        org.junit.Assert.assertNotNull(localDateTime35);
        org.junit.Assert.assertNotNull(reservation36);
        org.junit.Assert.assertNotNull(localDateTime43);
        org.junit.Assert.assertNotNull(equipment45);
        org.junit.Assert.assertNotNull(user46);
    }

    @Test
    public void test111() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test111");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        org.example.users.Guest guest10 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest10.setIdNumber("");
        java.lang.String str13 = guest10.getDepartmentId();
        org.example.equipment.Equipment equipment17 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus18 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment17.setStatus(equipmentStatus18);
        org.example.payment.Payment payment25 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str26 = payment25.getReservationId();
        java.time.LocalDateTime localDateTime27 = payment25.getTimestamp();
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str34 = payment33.getReservationId();
        java.time.LocalDateTime localDateTime35 = payment33.getTimestamp();
        org.example.reservation.Reservation reservation36 = reservationManager0.createReservation((org.example.users.User) guest10, equipment17, localDateTime27, localDateTime35);
        java.lang.String str37 = equipment17.toString();
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!" + "'", str13, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus18 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus18.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hi!" + "'", str34, "hi!");
        org.junit.Assert.assertNotNull(localDateTime35);
        org.junit.Assert.assertNotNull(reservation36);
        org.junit.Assert.assertEquals("'" + str37 + "' != '" + " -  () [AVAILABLE]" + "'", str37, " -  () [AVAILABLE]");
    }

    @Test
    public void test112() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test112");
        org.example.equipment.EquipmentStatus equipmentStatus0 = org.example.equipment.EquipmentStatus.MAINTENANCE;
        org.junit.Assert.assertTrue("'" + equipmentStatus0 + "' != '" + org.example.equipment.EquipmentStatus.MAINTENANCE + "'", equipmentStatus0.equals(org.example.equipment.EquipmentStatus.MAINTENANCE));
    }

    @Test
    public void test113() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test113");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.equipment.Equipment equipment5 = new org.example.equipment.Equipment("", "", "");
        int int6 = equipment5.getAvailableUnits();
        java.lang.String str7 = equipment5.getDescription();
        equipment5.setAvailableUnits((int) 'a');
        cSVRepository0.updateEquipment(equipment5);
        java.util.List<java.lang.String[]> strArrayList12 = cSVRepository0.getReservationRowsByUserId("hi!");
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
        org.junit.Assert.assertNotNull(strArrayList12);
    }

    @Test
    public void test114() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test114");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        equipmentManager0.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList4 = equipmentManager0.getAllEquipment();
        equipmentManager0.enableEquipment("hi!");
        org.example.equipment.Equipment equipment8 = equipmentManager0.getEquipmentById("hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList9 = equipmentManager0.getAvailableEquipment();
        equipmentManager0.disableEquipment("RES-1775354801691");
        org.example.equipment.Equipment equipment15 = new org.example.equipment.Equipment("", "", "");
        int int16 = equipment15.getAvailableUnits();
        java.lang.String str17 = equipment15.getDescription();
        equipment15.setAvailableUnits((int) 'a');
        equipment15.setName("");
        org.example.equipment.EquipmentAction equipmentAction23 = new org.example.equipment.EquipmentAction(equipmentManager0, equipment15, "RESEARCHER");
        org.junit.Assert.assertNotNull(equipmentList4);
        org.junit.Assert.assertNull(equipment8);
        org.junit.Assert.assertNotNull(equipmentList9);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 1 + "'", int16 == 1);
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "" + "'", str17, "");
    }

    @Test
    public void test115() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test115");
        org.example.users.Guest guest6 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest6.setIdNumber("");
        guest6.setUserId("");
        org.example.users.UserDecorator userDecorator12 = new org.example.users.UserDecorator((org.example.users.User) guest6, "hi!");
        boolean boolean13 = userDecorator12.isApproved();
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
    }

    @Test
    public void test116() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test116");
        org.example.equipment.EquipmentManager equipmentManager0 = null;
        org.example.equipment.Equipment equipment1 = null;
        org.example.equipment.EquipmentAction equipmentAction3 = new org.example.equipment.EquipmentAction(equipmentManager0, equipment1, "hi!");
        equipmentAction3.undo();
        equipmentAction3.undo();
        // The following exception was thrown during execution in test generation
        try {
            equipmentAction3.execute();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.example.equipment.Equipment.getStatus()\" because \"this.equipment\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test117() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test117");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("", "", "");
        int int4 = equipment3.getAvailableUnits();
        java.lang.String str5 = equipment3.getDescription();
        equipment3.setAvailableUnits((int) 'a');
        equipment3.setName("");
        java.util.List<java.lang.String> strList10 = equipment3.getTags();
        java.lang.String str11 = equipment3.getImagePath();
        java.util.List<java.lang.String> strList12 = equipment3.getTags();
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 1 + "'", int4 == 1);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertNotNull(strList10);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "" + "'", str11, "");
        org.junit.Assert.assertNotNull(strList12);
    }

    @Test
    public void test118() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test118");
        org.example.reservation.ReservationManager reservationManager0 = null;
        org.example.reservation.Reservation reservation1 = null;
        org.example.reservation.ReservationAction reservationAction3 = new org.example.reservation.ReservationAction(reservationManager0, reservation1, "");
        reservationAction3.undo();
        reservationAction3.undo();
        reservationAction3.execute();
    }

    @Test
    public void test119() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test119");
        // The following exception was thrown during execution in test generation
        try {
            org.example.users.User user7 = org.example.users.UserFactory.createUser("", "hi!", "hi!", " -  () [AVAILABLE]", "FACULTY", "RES-1775354801691", "");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Unknown user type: ");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test120() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test120");
        org.example.equipment.EquipmentManager equipmentManager0 = null;
        org.example.equipment.Equipment equipment1 = null;
        org.example.equipment.EquipmentAction equipmentAction3 = new org.example.equipment.EquipmentAction(equipmentManager0, equipment1, "hi!");
        equipmentAction3.undo();
        equipmentAction3.undo();
        // The following exception was thrown during execution in test generation
        try {
            equipmentAction3.execute();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.example.equipment.Equipment.getStatus()\" because \"this.equipment\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test121() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test121");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        org.example.users.Guest guest10 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest10.setIdNumber("");
        java.lang.String str13 = guest10.getDepartmentId();
        org.example.equipment.Equipment equipment17 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus18 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment17.setStatus(equipmentStatus18);
        org.example.payment.Payment payment25 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str26 = payment25.getReservationId();
        java.time.LocalDateTime localDateTime27 = payment25.getTimestamp();
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str34 = payment33.getReservationId();
        java.time.LocalDateTime localDateTime35 = payment33.getTimestamp();
        org.example.reservation.Reservation reservation36 = reservationManager0.createReservation((org.example.users.User) guest10, equipment17, localDateTime27, localDateTime35);
        org.example.users.Guest guest44 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str45 = guest44.getIdNumber();
        org.example.equipment.Equipment equipment49 = new org.example.equipment.Equipment("", "", "");
        org.example.payment.Payment payment55 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str56 = payment55.getReservationId();
        java.time.LocalDateTime localDateTime57 = payment55.getTimestamp();
        org.example.payment.Payment payment63 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime64 = payment63.getTimestamp();
        org.example.payment.Payment payment70 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime71 = payment70.getTimestamp();
        payment63.setTimestamp(localDateTime71);
        org.example.reservation.Reservation reservation74 = new org.example.reservation.Reservation("hi!", (org.example.users.User) guest44, equipment49, localDateTime57, localDateTime71, (double) (byte) -1);
        org.example.reservation.ReservationAction reservationAction76 = new org.example.reservation.ReservationAction(reservationManager0, reservation74, "GUEST");
        java.time.LocalDateTime localDateTime77 = reservation74.getStartTime();
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!" + "'", str13, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus18 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus18.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hi!" + "'", str34, "hi!");
        org.junit.Assert.assertNotNull(localDateTime35);
        org.junit.Assert.assertNotNull(reservation36);
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "" + "'", str45, "");
        org.junit.Assert.assertEquals("'" + str56 + "' != '" + "hi!" + "'", str56, "hi!");
        org.junit.Assert.assertNotNull(localDateTime57);
        org.junit.Assert.assertNotNull(localDateTime64);
        org.junit.Assert.assertNotNull(localDateTime71);
        org.junit.Assert.assertNotNull(localDateTime77);
    }

    @Test
    public void test122() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test122");
        org.example.users.User user7 = org.example.users.UserFactory.createUser("FACULTY", " -  () [AVAILABLE]", "RES-1775354801261", "GUEST", "FACULTY", "hi!", "RES-1775354801261");
        org.junit.Assert.assertNotNull(user7);
    }

    @Test
    public void test123() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test123");
        org.example.auth.AuthService authService0 = new org.example.auth.AuthService();
        org.example.users.User user3 = authService0.login("STUDENT", "RES-1775354801261");
        boolean boolean5 = authService0.isApproved("STUDENT");
        org.junit.Assert.assertNull(user3);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test124() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test124");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        org.example.users.Guest guest10 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest10.setIdNumber("");
        java.lang.String str13 = guest10.getDepartmentId();
        org.example.equipment.Equipment equipment17 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus18 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment17.setStatus(equipmentStatus18);
        org.example.payment.Payment payment25 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str26 = payment25.getReservationId();
        java.time.LocalDateTime localDateTime27 = payment25.getTimestamp();
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str34 = payment33.getReservationId();
        java.time.LocalDateTime localDateTime35 = payment33.getTimestamp();
        org.example.reservation.Reservation reservation36 = reservationManager0.createReservation((org.example.users.User) guest10, equipment17, localDateTime27, localDateTime35);
        org.example.users.Guest guest44 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str45 = guest44.getIdNumber();
        org.example.equipment.Equipment equipment49 = new org.example.equipment.Equipment("", "", "");
        org.example.payment.Payment payment55 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str56 = payment55.getReservationId();
        java.time.LocalDateTime localDateTime57 = payment55.getTimestamp();
        org.example.payment.Payment payment63 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime64 = payment63.getTimestamp();
        org.example.payment.Payment payment70 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime71 = payment70.getTimestamp();
        payment63.setTimestamp(localDateTime71);
        org.example.reservation.Reservation reservation74 = new org.example.reservation.Reservation("hi!", (org.example.users.User) guest44, equipment49, localDateTime57, localDateTime71, (double) (byte) -1);
        org.example.reservation.ReservationAction reservationAction76 = new org.example.reservation.ReservationAction(reservationManager0, reservation74, "GUEST");
        org.example.reservation.ReservationStatus reservationStatus77 = org.example.reservation.ReservationStatus.CANCELLED;
        reservation74.setStatus(reservationStatus77);
        java.time.LocalDateTime localDateTime79 = reservation74.getStartTime();
        org.example.payment.Payment payment85 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str86 = payment85.getReservationId();
        java.time.LocalDateTime localDateTime87 = payment85.getTimestamp();
        boolean boolean88 = payment85.isForfeited();
        payment85.setForfeited(false);
        java.time.LocalDateTime localDateTime91 = payment85.getTimestamp();
        reservation74.setEndTime(localDateTime91);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!" + "'", str13, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus18 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus18.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hi!" + "'", str34, "hi!");
        org.junit.Assert.assertNotNull(localDateTime35);
        org.junit.Assert.assertNotNull(reservation36);
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "" + "'", str45, "");
        org.junit.Assert.assertEquals("'" + str56 + "' != '" + "hi!" + "'", str56, "hi!");
        org.junit.Assert.assertNotNull(localDateTime57);
        org.junit.Assert.assertNotNull(localDateTime64);
        org.junit.Assert.assertNotNull(localDateTime71);
        org.junit.Assert.assertTrue("'" + reservationStatus77 + "' != '" + org.example.reservation.ReservationStatus.CANCELLED + "'", reservationStatus77.equals(org.example.reservation.ReservationStatus.CANCELLED));
        org.junit.Assert.assertNotNull(localDateTime79);
        org.junit.Assert.assertEquals("'" + str86 + "' != '" + "hi!" + "'", str86, "hi!");
        org.junit.Assert.assertNotNull(localDateTime87);
        org.junit.Assert.assertTrue("'" + boolean88 + "' != '" + false + "'", boolean88 == false);
        org.junit.Assert.assertNotNull(localDateTime91);
    }

    @Test
    public void test125() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test125");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str6 = payment5.getReservationId();
        java.time.LocalDateTime localDateTime7 = payment5.getTimestamp();
        boolean boolean8 = payment5.isForfeited();
        org.example.payment.PaymentDecorator paymentDecorator11 = new org.example.payment.PaymentDecorator(payment5, "", "GUEST");
        boolean boolean12 = paymentDecorator11.validate();
        java.lang.String str13 = paymentDecorator11.getPaymentMethod();
        java.lang.String str14 = paymentDecorator11.getPaymentMethod();
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "hi!" + "'", str6, "hi!");
        org.junit.Assert.assertNotNull(localDateTime7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "" + "'", str14, "");
    }

    @Test
    public void test126() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test126");
        org.example.users.Researcher researcher6 = new org.example.users.Researcher("", "", "", "hi!", "hi!", "");
        org.example.users.UserDecorator userDecorator8 = new org.example.users.UserDecorator((org.example.users.User) researcher6, "RES-1775354801261");
        java.lang.String str9 = userDecorator8.getUserType();
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "RESEARCHER" + "'", str9, "RESEARCHER");
    }

    @Test
    public void test127() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test127");
        org.example.users.Guest guest6 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest6.setIdNumber("");
        guest6.setUserId("");
        guest6.setName("hi!");
        java.lang.String str13 = guest6.getName();
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!" + "'", str13, "hi!");
    }

    @Test
    public void test128() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test128");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        org.example.users.Guest guest10 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest10.setIdNumber("");
        java.lang.String str13 = guest10.getDepartmentId();
        org.example.equipment.Equipment equipment17 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus18 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment17.setStatus(equipmentStatus18);
        org.example.payment.Payment payment25 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str26 = payment25.getReservationId();
        java.time.LocalDateTime localDateTime27 = payment25.getTimestamp();
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str34 = payment33.getReservationId();
        java.time.LocalDateTime localDateTime35 = payment33.getTimestamp();
        org.example.reservation.Reservation reservation36 = reservationManager0.createReservation((org.example.users.User) guest10, equipment17, localDateTime27, localDateTime35);
        org.example.reservation.Reservation reservation38 = reservationManager0.getReservationById("hi!");
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!" + "'", str13, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus18 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus18.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hi!" + "'", str34, "hi!");
        org.junit.Assert.assertNotNull(localDateTime35);
        org.junit.Assert.assertNotNull(reservation36);
        org.junit.Assert.assertNull(reservation38);
    }

    @Test
    public void test129() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test129");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        equipmentManager0.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList4 = equipmentManager0.getAllEquipment();
        equipmentManager0.enableEquipment("hi!");
        equipmentManager0.disableEquipment("GUEST");
        java.util.List<org.example.equipment.Equipment> equipmentList9 = equipmentManager0.getAvailableEquipment();
        org.junit.Assert.assertNotNull(equipmentList4);
        org.junit.Assert.assertNotNull(equipmentList9);
    }

    @Test
    public void test130() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test130");
        org.example.users.LabManager labManager5 = new org.example.users.LabManager("hi!", "RES-1775354801691", "RES-1775354801261", "GUEST", "RESEARCHER");
    }

    @Test
    public void test131() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test131");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str6 = payment5.getReservationId();
        java.time.LocalDateTime localDateTime7 = payment5.getTimestamp();
        boolean boolean8 = payment5.isForfeited();
        double double9 = payment5.getAmount();
        java.time.LocalDateTime localDateTime10 = payment5.getTimestamp();
        org.example.reservation.ReservationManager reservationManager11 = new org.example.reservation.ReservationManager();
        reservationManager11.update("hi!", "");
        org.example.users.Guest guest21 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest21.setIdNumber("");
        java.lang.String str24 = guest21.getDepartmentId();
        org.example.equipment.Equipment equipment28 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus29 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment28.setStatus(equipmentStatus29);
        org.example.payment.Payment payment36 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str37 = payment36.getReservationId();
        java.time.LocalDateTime localDateTime38 = payment36.getTimestamp();
        org.example.payment.Payment payment44 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str45 = payment44.getReservationId();
        java.time.LocalDateTime localDateTime46 = payment44.getTimestamp();
        org.example.reservation.Reservation reservation47 = reservationManager11.createReservation((org.example.users.User) guest21, equipment28, localDateTime38, localDateTime46);
        org.example.users.Guest guest55 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str56 = guest55.getIdNumber();
        org.example.equipment.Equipment equipment60 = new org.example.equipment.Equipment("", "", "");
        org.example.payment.Payment payment66 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str67 = payment66.getReservationId();
        java.time.LocalDateTime localDateTime68 = payment66.getTimestamp();
        org.example.payment.Payment payment74 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime75 = payment74.getTimestamp();
        org.example.payment.Payment payment81 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime82 = payment81.getTimestamp();
        payment74.setTimestamp(localDateTime82);
        org.example.reservation.Reservation reservation85 = new org.example.reservation.Reservation("hi!", (org.example.users.User) guest55, equipment60, localDateTime68, localDateTime82, (double) (byte) -1);
        org.example.reservation.ReservationAction reservationAction87 = new org.example.reservation.ReservationAction(reservationManager11, reservation85, "GUEST");
        org.example.equipment.Equipment equipment88 = reservation85.getEquipment();
        java.time.LocalDateTime localDateTime89 = reservation85.getEndTime();
        payment5.setTimestamp(localDateTime89);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "hi!" + "'", str6, "hi!");
        org.junit.Assert.assertNotNull(localDateTime7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + double9 + "' != '" + 10.0d + "'", double9 == 10.0d);
        org.junit.Assert.assertNotNull(localDateTime10);
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "hi!" + "'", str24, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus29 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus29.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str37 + "' != '" + "hi!" + "'", str37, "hi!");
        org.junit.Assert.assertNotNull(localDateTime38);
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "hi!" + "'", str45, "hi!");
        org.junit.Assert.assertNotNull(localDateTime46);
        org.junit.Assert.assertNotNull(reservation47);
        org.junit.Assert.assertEquals("'" + str56 + "' != '" + "" + "'", str56, "");
        org.junit.Assert.assertEquals("'" + str67 + "' != '" + "hi!" + "'", str67, "hi!");
        org.junit.Assert.assertNotNull(localDateTime68);
        org.junit.Assert.assertNotNull(localDateTime75);
        org.junit.Assert.assertNotNull(localDateTime82);
        org.junit.Assert.assertNotNull(equipment88);
        org.junit.Assert.assertNotNull(localDateTime89);
    }

    @Test
    public void test132() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test132");
        org.example.sensors.EquipmentSensor equipmentSensor1 = new org.example.sensors.EquipmentSensor("RESEARCHER");
        org.example.reservation.ReservationManager reservationManager2 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation3 = null;
        java.time.LocalDateTime localDateTime5 = null;
        org.example.reservation.ReservationAction reservationAction6 = new org.example.reservation.ReservationAction(reservationManager2, reservation3, "", localDateTime5);
        equipmentSensor1.removeObserver((org.example.sensors.SensorObserver) reservationManager2);
        equipmentSensor1.triggerUpdate("");
        org.example.equipment.EquipmentManager equipmentManager10 = new org.example.equipment.EquipmentManager();
        equipmentManager10.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList14 = equipmentManager10.getAllEquipment();
        equipmentManager10.setMaintenance("GUEST");
        equipmentSensor1.addObserver((org.example.sensors.SensorObserver) equipmentManager10);
        org.example.sensors.SensorObserver sensorObserver18 = null;
        equipmentSensor1.removeObserver(sensorObserver18);
        org.junit.Assert.assertNotNull(equipmentList14);
    }

    @Test
    public void test133() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test133");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("", "", "");
        int int4 = equipment3.getAvailableUnits();
        equipment3.setProductStatistics("");
        java.lang.String str7 = equipment3.getName();
        java.lang.String str8 = equipment3.getEquipmentId();
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 1 + "'", int4 == 1);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
    }

    @Test
    public void test134() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test134");
        org.example.sensors.EquipmentSensor equipmentSensor1 = new org.example.sensors.EquipmentSensor("RESEARCHER");
        org.example.reservation.ReservationManager reservationManager2 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation3 = null;
        java.time.LocalDateTime localDateTime5 = null;
        org.example.reservation.ReservationAction reservationAction6 = new org.example.reservation.ReservationAction(reservationManager2, reservation3, "", localDateTime5);
        java.util.List<java.lang.String[]> strArrayList7 = reservationManager2.getAllReservationRows();
        equipmentSensor1.addObserver((org.example.sensors.SensorObserver) reservationManager2);
        reservationManager2.update("FACULTY", "");
        org.example.reservation.ReservationManager reservationManager12 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation13 = null;
        java.time.LocalDateTime localDateTime15 = null;
        org.example.reservation.ReservationAction reservationAction16 = new org.example.reservation.ReservationAction(reservationManager12, reservation13, "", localDateTime15);
        java.util.List<java.lang.String[]> strArrayList17 = reservationManager12.getAllReservationRows();
        org.example.reservation.ReservationManager reservationManager18 = new org.example.reservation.ReservationManager();
        reservationManager18.update("hi!", "");
        org.example.users.Guest guest28 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest28.setIdNumber("");
        java.lang.String str31 = guest28.getDepartmentId();
        org.example.equipment.Equipment equipment35 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus36 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment35.setStatus(equipmentStatus36);
        org.example.payment.Payment payment43 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str44 = payment43.getReservationId();
        java.time.LocalDateTime localDateTime45 = payment43.getTimestamp();
        org.example.payment.Payment payment51 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str52 = payment51.getReservationId();
        java.time.LocalDateTime localDateTime53 = payment51.getTimestamp();
        org.example.reservation.Reservation reservation54 = reservationManager18.createReservation((org.example.users.User) guest28, equipment35, localDateTime45, localDateTime53);
        long long55 = reservation54.getDurationHours();
        org.example.payment.Payment payment58 = reservationManager12.processDeposit(reservation54, "", "FACULTY");
        org.example.payment.Payment payment64 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime65 = payment64.getTimestamp();
        org.example.payment.Payment payment71 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime72 = payment71.getTimestamp();
        payment64.setTimestamp(localDateTime72);
        boolean boolean74 = reservation54.arrivedOnTime(localDateTime72);
        org.example.payment.Payment payment77 = reservationManager2.processDeposit(reservation54, "", "AVAILABLE");
        java.lang.Class<?> wildcardClass78 = payment77.getClass();
        org.junit.Assert.assertNotNull(strArrayList7);
        org.junit.Assert.assertNotNull(strArrayList17);
        org.junit.Assert.assertEquals("'" + str31 + "' != '" + "hi!" + "'", str31, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus36 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus36.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str44 + "' != '" + "hi!" + "'", str44, "hi!");
        org.junit.Assert.assertNotNull(localDateTime45);
        org.junit.Assert.assertEquals("'" + str52 + "' != '" + "hi!" + "'", str52, "hi!");
        org.junit.Assert.assertNotNull(localDateTime53);
        org.junit.Assert.assertNotNull(reservation54);
        org.junit.Assert.assertTrue("'" + long55 + "' != '" + 0L + "'", long55 == 0L);
        org.junit.Assert.assertNotNull(payment58);
        org.junit.Assert.assertNotNull(localDateTime65);
        org.junit.Assert.assertNotNull(localDateTime72);
        org.junit.Assert.assertTrue("'" + boolean74 + "' != '" + true + "'", boolean74 == true);
        org.junit.Assert.assertNotNull(payment77);
        org.junit.Assert.assertNotNull(wildcardClass78);
    }

    @Test
    public void test135() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test135");
        org.example.payment.UserPricingStrategy userPricingStrategy1 = new org.example.payment.UserPricingStrategy("RESEARCHER");
    }

    @Test
    public void test136() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test136");
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
        java.lang.String[] strArray34 = null;
        // The following exception was thrown during execution in test generation
        try {
            cSVHandler0.appendCSV("STUDENT", strArray34);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot read the array length because \"elements\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(cSVHandler0);
        org.junit.Assert.assertNotNull(strArrayList2);
        org.junit.Assert.assertNotNull(strArrayList6);
// flaky:         org.junit.Assert.assertNotNull(user8);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 1 + "'", int13 == 1);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 1 + "'", int16 == 1);
        org.junit.Assert.assertNotNull(strArrayList24);
        org.junit.Assert.assertNotNull(strArray31);
    }

    @Test
    public void test137() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test137");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("RES-1775354801691", "RESEARCHER");
    }

    @Test
    public void test138() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test138");
        org.example.sensors.EquipmentSensor equipmentSensor1 = new org.example.sensors.EquipmentSensor("RESEARCHER");
        org.example.reservation.ReservationManager reservationManager2 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation3 = null;
        java.time.LocalDateTime localDateTime5 = null;
        org.example.reservation.ReservationAction reservationAction6 = new org.example.reservation.ReservationAction(reservationManager2, reservation3, "", localDateTime5);
        equipmentSensor1.removeObserver((org.example.sensors.SensorObserver) reservationManager2);
        org.example.equipment.EquipmentManager equipmentManager8 = new org.example.equipment.EquipmentManager();
        equipmentManager8.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList12 = equipmentManager8.getAllEquipment();
        equipmentManager8.setMaintenance("GUEST");
        java.util.List<org.example.equipment.Equipment> equipmentList15 = equipmentManager8.getAllEquipment();
        equipmentSensor1.removeObserver((org.example.sensors.SensorObserver) equipmentManager8);
        equipmentSensor1.notifyObservers("FACULTY", "RESEARCHER");
        org.junit.Assert.assertNotNull(equipmentList12);
        org.junit.Assert.assertNotNull(equipmentList15);
    }

    @Test
    public void test139() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test139");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.User user3 = cSVRepository0.findUserByEmail("");
        org.example.equipment.Equipment equipment7 = new org.example.equipment.Equipment("", "", "");
        int int8 = equipment7.getAvailableUnits();
        equipment7.setName("");
        int int11 = equipment7.getAvailableUnits();
        cSVRepository0.saveEquipment(equipment7);
        org.example.equipment.Equipment equipment16 = new org.example.equipment.Equipment("", "", "");
        cSVRepository0.updateEquipment(equipment16);
        java.util.List<java.lang.String[]> strArrayList19 = cSVRepository0.getReservationRowsByUserId("");
        org.example.payment.Payment payment25 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str26 = payment25.getReservationId();
        java.time.LocalDateTime localDateTime27 = payment25.getTimestamp();
        boolean boolean28 = payment25.isForfeited();
        double double29 = payment25.getAmount();
        java.time.LocalDateTime localDateTime30 = payment25.getTimestamp();
        cSVRepository0.savePayment(payment25);
        java.util.List<org.example.users.User> userList32 = cSVRepository0.getAllUsers();
        org.junit.Assert.assertNotNull(strArrayList1);
// flaky:         org.junit.Assert.assertNotNull(user3);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 1 + "'", int8 == 1);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 1 + "'", int11 == 1);
        org.junit.Assert.assertNotNull(strArrayList19);
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + false + "'", boolean28 == false);
        org.junit.Assert.assertTrue("'" + double29 + "' != '" + 10.0d + "'", double29 == 10.0d);
        org.junit.Assert.assertNotNull(localDateTime30);
        org.junit.Assert.assertNotNull(userList32);
    }

    @Test
    public void test140() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test140");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.User user3 = cSVRepository0.findUserByEmail("");
        org.example.equipment.Equipment equipment7 = new org.example.equipment.Equipment("", "", "");
        int int8 = equipment7.getAvailableUnits();
        equipment7.setName("");
        int int11 = equipment7.getAvailableUnits();
        cSVRepository0.saveEquipment(equipment7);
        org.example.equipment.Equipment equipment16 = new org.example.equipment.Equipment("", "", "");
        int int17 = equipment16.getAvailableUnits();
        java.lang.String str18 = equipment16.getDescription();
        java.lang.String str19 = equipment16.getEquipmentId();
        cSVRepository0.updateEquipment(equipment16);
        org.example.data.CSVRepository cSVRepository21 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList22 = cSVRepository21.getAllPaymentRows();
        org.example.equipment.Equipment equipment26 = new org.example.equipment.Equipment("", "", "");
        int int27 = equipment26.getAvailableUnits();
        java.lang.String str28 = equipment26.getDescription();
        equipment26.setAvailableUnits((int) 'a');
        cSVRepository21.updateEquipment(equipment26);
        equipment26.setImagePath("hi!");
        cSVRepository0.saveEquipment(equipment26);
        java.util.List<java.lang.String[]> strArrayList36 = cSVRepository0.getPaymentRowsByReservationId("");
        org.junit.Assert.assertNotNull(strArrayList1);
// flaky:         org.junit.Assert.assertNotNull(user3);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 1 + "'", int8 == 1);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 1 + "'", int11 == 1);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + 1 + "'", int17 == 1);
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "" + "'", str18, "");
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "" + "'", str19, "");
        org.junit.Assert.assertNotNull(strArrayList22);
        org.junit.Assert.assertTrue("'" + int27 + "' != '" + 1 + "'", int27 == 1);
        org.junit.Assert.assertEquals("'" + str28 + "' != '" + "" + "'", str28, "");
        org.junit.Assert.assertNotNull(strArrayList36);
    }

    @Test
    public void test141() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test141");
        org.example.users.Student student6 = new org.example.users.Student("hi!", "", "FACULTY", "STUDENT", "GUEST", "hi!");
        java.lang.String str7 = student6.getPassword();
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "STUDENT" + "'", str7, "STUDENT");
    }

    @Test
    public void test142() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test142");
        org.example.users.Student student6 = new org.example.users.Student("", "GUEST", "hi!", "hi!", "FACULTY", "");
        student6.setName(" -  () [AVAILABLE]");
    }

    @Test
    public void test143() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test143");
        org.example.users.Researcher researcher6 = new org.example.users.Researcher("", "", "", "hi!", "hi!", "");
        org.example.users.UserDecorator userDecorator8 = new org.example.users.UserDecorator((org.example.users.User) researcher6, "RES-1775354801261");
        java.lang.String str9 = userDecorator8.getApprovalStatus();
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "NOT_APPROVED" + "'", str9, "NOT_APPROVED");
    }

    @Test
    public void test144() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test144");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        equipmentManager0.update("hi!", "hi!");
        equipmentManager0.update("AVAILABLE", "STUDENT");
    }

    @Test
    public void test145() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test145");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        org.example.users.Guest guest10 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest10.setIdNumber("");
        java.lang.String str13 = guest10.getDepartmentId();
        org.example.equipment.Equipment equipment17 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus18 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment17.setStatus(equipmentStatus18);
        org.example.payment.Payment payment25 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str26 = payment25.getReservationId();
        java.time.LocalDateTime localDateTime27 = payment25.getTimestamp();
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str34 = payment33.getReservationId();
        java.time.LocalDateTime localDateTime35 = payment33.getTimestamp();
        org.example.reservation.Reservation reservation36 = reservationManager0.createReservation((org.example.users.User) guest10, equipment17, localDateTime27, localDateTime35);
        org.example.users.Guest guest44 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str45 = guest44.getIdNumber();
        org.example.equipment.Equipment equipment49 = new org.example.equipment.Equipment("", "", "");
        org.example.payment.Payment payment55 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str56 = payment55.getReservationId();
        java.time.LocalDateTime localDateTime57 = payment55.getTimestamp();
        org.example.payment.Payment payment63 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime64 = payment63.getTimestamp();
        org.example.payment.Payment payment70 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime71 = payment70.getTimestamp();
        payment63.setTimestamp(localDateTime71);
        org.example.reservation.Reservation reservation74 = new org.example.reservation.Reservation("hi!", (org.example.users.User) guest44, equipment49, localDateTime57, localDateTime71, (double) (byte) -1);
        org.example.reservation.ReservationAction reservationAction76 = new org.example.reservation.ReservationAction(reservationManager0, reservation74, "GUEST");
        org.example.equipment.Equipment equipment77 = reservation74.getEquipment();
        double double79 = reservation74.calculateTotal((double) 32);
        java.time.LocalDateTime localDateTime80 = reservation74.getStartTime();
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!" + "'", str13, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus18 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus18.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hi!" + "'", str34, "hi!");
        org.junit.Assert.assertNotNull(localDateTime35);
        org.junit.Assert.assertNotNull(reservation36);
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "" + "'", str45, "");
        org.junit.Assert.assertEquals("'" + str56 + "' != '" + "hi!" + "'", str56, "hi!");
        org.junit.Assert.assertNotNull(localDateTime57);
        org.junit.Assert.assertNotNull(localDateTime64);
        org.junit.Assert.assertNotNull(localDateTime71);
        org.junit.Assert.assertNotNull(equipment77);
        org.junit.Assert.assertTrue("'" + double79 + "' != '" + 0.0d + "'", double79 == 0.0d);
        org.junit.Assert.assertNotNull(localDateTime80);
    }

    @Test
    public void test146() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test146");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.Guest guest8 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str9 = guest8.getUserType();
        cSVRepository0.updateUser((org.example.users.User) guest8);
        org.example.payment.Payment payment16 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime17 = payment16.getTimestamp();
        org.example.payment.PaymentDecorator paymentDecorator20 = new org.example.payment.PaymentDecorator(payment16, "", "hi!");
        cSVRepository0.savePayment(payment16);
        org.example.users.User user23 = cSVRepository0.findUserById("FACULTY");
        org.example.data.CSVRepository cSVRepository24 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList25 = cSVRepository24.getAllPaymentRows();
        org.example.users.User user27 = cSVRepository24.findUserByEmail("");
        org.example.equipment.Equipment equipment31 = new org.example.equipment.Equipment("", "", "");
        int int32 = equipment31.getAvailableUnits();
        equipment31.setName("");
        int int35 = equipment31.getAvailableUnits();
        cSVRepository24.saveEquipment(equipment31);
        java.lang.String str37 = equipment31.getEquipmentId();
        cSVRepository0.updateEquipment(equipment31);
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "GUEST" + "'", str9, "GUEST");
        org.junit.Assert.assertNotNull(localDateTime17);
        org.junit.Assert.assertNull(user23);
        org.junit.Assert.assertNotNull(strArrayList25);
// flaky:         org.junit.Assert.assertNotNull(user27);
        org.junit.Assert.assertTrue("'" + int32 + "' != '" + 1 + "'", int32 == 1);
        org.junit.Assert.assertTrue("'" + int35 + "' != '" + 1 + "'", int35 == 1);
        org.junit.Assert.assertEquals("'" + str37 + "' != '" + "" + "'", str37, "");
    }

    @Test
    public void test147() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test147");
        org.example.data.CSVHandler cSVHandler0 = org.example.data.CSVHandler.getInstance();
        org.example.data.CSVRepository cSVRepository3 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList4 = cSVRepository3.getAllPaymentRows();
        org.example.users.User user6 = cSVRepository3.findUserByEmail("");
        org.example.equipment.Equipment equipment10 = new org.example.equipment.Equipment("", "", "");
        int int11 = equipment10.getAvailableUnits();
        equipment10.setName("");
        int int14 = equipment10.getAvailableUnits();
        cSVRepository3.saveEquipment(equipment10);
        org.example.equipment.Equipment equipment19 = new org.example.equipment.Equipment("", "", "");
        cSVRepository3.updateEquipment(equipment19);
        java.util.List<java.lang.String[]> strArrayList22 = cSVRepository3.getReservationRowsByUserId("");
        java.util.List<java.lang.String[]> strArrayList24 = cSVRepository3.getReservationRowsByUserId("RESEARCHER");
        cSVHandler0.writeCSV("RES-1775354801691", "AVAILABLE", strArrayList24);
        org.junit.Assert.assertNotNull(cSVHandler0);
        org.junit.Assert.assertNotNull(strArrayList4);
// flaky:         org.junit.Assert.assertNotNull(user6);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 1 + "'", int11 == 1);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 1 + "'", int14 == 1);
        org.junit.Assert.assertNotNull(strArrayList22);
        org.junit.Assert.assertNotNull(strArrayList24);
    }

    @Test
    public void test148() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test148");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        org.example.users.Guest guest10 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest10.setIdNumber("");
        java.lang.String str13 = guest10.getDepartmentId();
        org.example.equipment.Equipment equipment17 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus18 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment17.setStatus(equipmentStatus18);
        org.example.payment.Payment payment25 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str26 = payment25.getReservationId();
        java.time.LocalDateTime localDateTime27 = payment25.getTimestamp();
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str34 = payment33.getReservationId();
        java.time.LocalDateTime localDateTime35 = payment33.getTimestamp();
        org.example.reservation.Reservation reservation36 = reservationManager0.createReservation((org.example.users.User) guest10, equipment17, localDateTime27, localDateTime35);
        double double37 = reservation36.getDeposit();
        double double39 = reservation36.calculateTotal((double) ' ');
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!" + "'", str13, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus18 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus18.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hi!" + "'", str34, "hi!");
        org.junit.Assert.assertNotNull(localDateTime35);
        org.junit.Assert.assertNotNull(reservation36);
        org.junit.Assert.assertTrue("'" + double37 + "' != '" + 30.0d + "'", double37 == 30.0d);
        org.junit.Assert.assertTrue("'" + double39 + "' != '" + 0.0d + "'", double39 == 0.0d);
    }

    @Test
    public void test149() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test149");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.Guest guest8 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str9 = guest8.getUserType();
        cSVRepository0.updateUser((org.example.users.User) guest8);
        org.example.payment.Payment payment16 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime17 = payment16.getTimestamp();
        cSVRepository0.savePayment(payment16);
        org.example.reservation.ReservationManager reservationManager19 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation20 = null;
        java.time.LocalDateTime localDateTime22 = null;
        org.example.reservation.ReservationAction reservationAction23 = new org.example.reservation.ReservationAction(reservationManager19, reservation20, "", localDateTime22);
        java.util.List<java.lang.String[]> strArrayList24 = reservationManager19.getAllReservationRows();
        org.example.reservation.ReservationManager reservationManager25 = new org.example.reservation.ReservationManager();
        reservationManager25.update("hi!", "");
        org.example.users.Guest guest35 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest35.setIdNumber("");
        java.lang.String str38 = guest35.getDepartmentId();
        org.example.equipment.Equipment equipment42 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus43 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment42.setStatus(equipmentStatus43);
        org.example.payment.Payment payment50 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str51 = payment50.getReservationId();
        java.time.LocalDateTime localDateTime52 = payment50.getTimestamp();
        org.example.payment.Payment payment58 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str59 = payment58.getReservationId();
        java.time.LocalDateTime localDateTime60 = payment58.getTimestamp();
        org.example.reservation.Reservation reservation61 = reservationManager25.createReservation((org.example.users.User) guest35, equipment42, localDateTime52, localDateTime60);
        long long62 = reservation61.getDurationHours();
        org.example.payment.Payment payment65 = reservationManager19.processDeposit(reservation61, "", "FACULTY");
        org.example.payment.Payment payment71 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime72 = payment71.getTimestamp();
        org.example.payment.Payment payment78 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime79 = payment78.getTimestamp();
        payment71.setTimestamp(localDateTime79);
        boolean boolean81 = reservation61.arrivedOnTime(localDateTime79);
        cSVRepository0.saveReservation(reservation61);
        cSVRepository0.deleteUser("RES-1775354801691");
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "GUEST" + "'", str9, "GUEST");
        org.junit.Assert.assertNotNull(localDateTime17);
        org.junit.Assert.assertNotNull(strArrayList24);
        org.junit.Assert.assertEquals("'" + str38 + "' != '" + "hi!" + "'", str38, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus43 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus43.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str51 + "' != '" + "hi!" + "'", str51, "hi!");
        org.junit.Assert.assertNotNull(localDateTime52);
        org.junit.Assert.assertEquals("'" + str59 + "' != '" + "hi!" + "'", str59, "hi!");
        org.junit.Assert.assertNotNull(localDateTime60);
        org.junit.Assert.assertNotNull(reservation61);
        org.junit.Assert.assertTrue("'" + long62 + "' != '" + 0L + "'", long62 == 0L);
        org.junit.Assert.assertNotNull(payment65);
        org.junit.Assert.assertNotNull(localDateTime72);
        org.junit.Assert.assertNotNull(localDateTime79);
        org.junit.Assert.assertTrue("'" + boolean81 + "' != '" + true + "'", boolean81 == true);
    }

    @Test
    public void test150() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test150");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.Guest guest8 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str9 = guest8.getUserType();
        cSVRepository0.updateUser((org.example.users.User) guest8);
        org.example.payment.Payment payment16 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime17 = payment16.getTimestamp();
        org.example.payment.PaymentDecorator paymentDecorator20 = new org.example.payment.PaymentDecorator(payment16, "", "hi!");
        cSVRepository0.savePayment(payment16);
        java.util.List<org.example.users.User> userList22 = cSVRepository0.getAllUsers();
        java.util.List<org.example.users.User> userList23 = cSVRepository0.getAllUsers();
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "GUEST" + "'", str9, "GUEST");
        org.junit.Assert.assertNotNull(localDateTime17);
        org.junit.Assert.assertNotNull(userList22);
        org.junit.Assert.assertNotNull(userList23);
    }

    @Test
    public void test151() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test151");
        org.example.users.Researcher researcher6 = new org.example.users.Researcher("", "", "", "hi!", "hi!", "");
        java.lang.String str7 = researcher6.getUserType();
        java.lang.String str8 = researcher6.getName();
        researcher6.setPassword("STUDENT");
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "RESEARCHER" + "'", str7, "RESEARCHER");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
    }

    @Test
    public void test152() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test152");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        equipmentManager0.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList4 = equipmentManager0.getAllEquipment();
        equipmentManager0.enableEquipment("hi!");
        org.example.equipment.Equipment equipment8 = equipmentManager0.getEquipmentById("hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList9 = equipmentManager0.getAvailableEquipment();
        equipmentManager0.disableEquipment("RES-1775354801691");
        equipmentManager0.update(" -  () [AVAILABLE]", "AVAILABLE");
        org.junit.Assert.assertNotNull(equipmentList4);
        org.junit.Assert.assertNull(equipment8);
        org.junit.Assert.assertNotNull(equipmentList9);
    }

    @Test
    public void test153() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test153");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.equipment.Equipment equipment5 = new org.example.equipment.Equipment("", "", "");
        int int6 = equipment5.getAvailableUnits();
        java.lang.String str7 = equipment5.getDescription();
        equipment5.setAvailableUnits((int) 'a');
        cSVRepository0.updateEquipment(equipment5);
        equipment5.setAvailableUnits((int) ' ');
        java.lang.String str13 = equipment5.getDescription();
        org.example.equipment.EquipmentStatus equipmentStatus14 = equipment5.getStatus();
        java.lang.String str15 = equipment5.getName();
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
        org.junit.Assert.assertTrue("'" + equipmentStatus14 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus14.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "" + "'", str15, "");
    }

    @Test
    public void test154() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test154");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("", "", "");
        int int4 = equipment3.getAvailableUnits();
        java.lang.String str5 = equipment3.getDescription();
        org.example.equipment.EquipmentStatus equipmentStatus6 = equipment3.getStatus();
        equipment3.setImagePath("RES-1775354801261");
        java.lang.String str9 = equipment3.getProductStatistics();
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 1 + "'", int4 == 1);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertTrue("'" + equipmentStatus6 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus6.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
    }

    @Test
    public void test155() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test155");
        org.example.payment.UserPricingStrategy userPricingStrategy1 = new org.example.payment.UserPricingStrategy("");
        double double2 = userPricingStrategy1.calculateDeposit();
        double double4 = userPricingStrategy1.calculateTotal(1);
        org.junit.Assert.assertTrue("'" + double2 + "' != '" + 0.0d + "'", double2 == 0.0d);
        org.junit.Assert.assertTrue("'" + double4 + "' != '" + 0.0d + "'", double4 == 0.0d);
    }

    @Test
    public void test156() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test156");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.Guest guest8 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str9 = guest8.getUserType();
        cSVRepository0.updateUser((org.example.users.User) guest8);
        org.example.payment.Payment payment16 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime17 = payment16.getTimestamp();
        org.example.payment.PaymentDecorator paymentDecorator20 = new org.example.payment.PaymentDecorator(payment16, "", "hi!");
        cSVRepository0.savePayment(payment16);
        java.util.List<org.example.equipment.Equipment> equipmentList22 = cSVRepository0.getAllEquipment();
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "GUEST" + "'", str9, "GUEST");
        org.junit.Assert.assertNotNull(localDateTime17);
        org.junit.Assert.assertNotNull(equipmentList22);
    }

    @Test
    public void test157() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test157");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("", "", "");
        int int4 = equipment3.getAvailableUnits();
        java.lang.String str5 = equipment3.getName();
        equipment3.setImagePath("GUEST");
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 1 + "'", int4 == 1);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
    }

    @Test
    public void test158() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test158");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        org.example.users.Guest guest10 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest10.setIdNumber("");
        java.lang.String str13 = guest10.getDepartmentId();
        org.example.equipment.Equipment equipment17 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus18 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment17.setStatus(equipmentStatus18);
        org.example.payment.Payment payment25 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str26 = payment25.getReservationId();
        java.time.LocalDateTime localDateTime27 = payment25.getTimestamp();
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str34 = payment33.getReservationId();
        java.time.LocalDateTime localDateTime35 = payment33.getTimestamp();
        org.example.reservation.Reservation reservation36 = reservationManager0.createReservation((org.example.users.User) guest10, equipment17, localDateTime27, localDateTime35);
        org.example.users.Guest guest44 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str45 = guest44.getIdNumber();
        org.example.equipment.Equipment equipment49 = new org.example.equipment.Equipment("", "", "");
        org.example.payment.Payment payment55 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str56 = payment55.getReservationId();
        java.time.LocalDateTime localDateTime57 = payment55.getTimestamp();
        org.example.payment.Payment payment63 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime64 = payment63.getTimestamp();
        org.example.payment.Payment payment70 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime71 = payment70.getTimestamp();
        payment63.setTimestamp(localDateTime71);
        org.example.reservation.Reservation reservation74 = new org.example.reservation.Reservation("hi!", (org.example.users.User) guest44, equipment49, localDateTime57, localDateTime71, (double) (byte) -1);
        org.example.reservation.ReservationAction reservationAction76 = new org.example.reservation.ReservationAction(reservationManager0, reservation74, "GUEST");
        double double77 = reservation74.getDeposit();
        long long78 = reservation74.getDurationHours();
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!" + "'", str13, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus18 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus18.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hi!" + "'", str34, "hi!");
        org.junit.Assert.assertNotNull(localDateTime35);
        org.junit.Assert.assertNotNull(reservation36);
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "" + "'", str45, "");
        org.junit.Assert.assertEquals("'" + str56 + "' != '" + "hi!" + "'", str56, "hi!");
        org.junit.Assert.assertNotNull(localDateTime57);
        org.junit.Assert.assertNotNull(localDateTime64);
        org.junit.Assert.assertNotNull(localDateTime71);
        org.junit.Assert.assertTrue("'" + double77 + "' != '" + (-1.0d) + "'", double77 == (-1.0d));
        org.junit.Assert.assertTrue("'" + long78 + "' != '" + 0L + "'", long78 == 0L);
    }

    @Test
    public void test159() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test159");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("", "", "");
        int int4 = equipment3.getAvailableUnits();
        java.lang.String str5 = equipment3.getDescription();
        java.lang.String str6 = equipment3.getEquipmentId();
        equipment3.setAvailableUnits(0);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 1 + "'", int4 == 1);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
    }

    @Test
    public void test160() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test160");
        org.example.sensors.EquipmentSensor equipmentSensor1 = new org.example.sensors.EquipmentSensor("RESEARCHER");
        org.example.reservation.ReservationManager reservationManager2 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation3 = null;
        java.time.LocalDateTime localDateTime5 = null;
        org.example.reservation.ReservationAction reservationAction6 = new org.example.reservation.ReservationAction(reservationManager2, reservation3, "", localDateTime5);
        java.util.List<java.lang.String[]> strArrayList7 = reservationManager2.getAllReservationRows();
        equipmentSensor1.addObserver((org.example.sensors.SensorObserver) reservationManager2);
        equipmentSensor1.notifyObservers("AVAILABLE", "");
        org.example.sensors.EquipmentSensor equipmentSensor13 = new org.example.sensors.EquipmentSensor("RESEARCHER");
        org.example.reservation.ReservationManager reservationManager14 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation15 = null;
        java.time.LocalDateTime localDateTime17 = null;
        org.example.reservation.ReservationAction reservationAction18 = new org.example.reservation.ReservationAction(reservationManager14, reservation15, "", localDateTime17);
        java.util.List<java.lang.String[]> strArrayList19 = reservationManager14.getAllReservationRows();
        equipmentSensor13.addObserver((org.example.sensors.SensorObserver) reservationManager14);
        reservationManager14.update("FACULTY", "");
        org.example.reservation.ReservationManager reservationManager24 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation25 = null;
        java.time.LocalDateTime localDateTime27 = null;
        org.example.reservation.ReservationAction reservationAction28 = new org.example.reservation.ReservationAction(reservationManager24, reservation25, "", localDateTime27);
        java.util.List<java.lang.String[]> strArrayList29 = reservationManager24.getAllReservationRows();
        org.example.reservation.ReservationManager reservationManager30 = new org.example.reservation.ReservationManager();
        reservationManager30.update("hi!", "");
        org.example.users.Guest guest40 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest40.setIdNumber("");
        java.lang.String str43 = guest40.getDepartmentId();
        org.example.equipment.Equipment equipment47 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus48 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment47.setStatus(equipmentStatus48);
        org.example.payment.Payment payment55 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str56 = payment55.getReservationId();
        java.time.LocalDateTime localDateTime57 = payment55.getTimestamp();
        org.example.payment.Payment payment63 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str64 = payment63.getReservationId();
        java.time.LocalDateTime localDateTime65 = payment63.getTimestamp();
        org.example.reservation.Reservation reservation66 = reservationManager30.createReservation((org.example.users.User) guest40, equipment47, localDateTime57, localDateTime65);
        long long67 = reservation66.getDurationHours();
        org.example.payment.Payment payment70 = reservationManager24.processDeposit(reservation66, "", "FACULTY");
        org.example.payment.Payment payment76 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime77 = payment76.getTimestamp();
        org.example.payment.Payment payment83 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime84 = payment83.getTimestamp();
        payment76.setTimestamp(localDateTime84);
        boolean boolean86 = reservation66.arrivedOnTime(localDateTime84);
        org.example.payment.Payment payment89 = reservationManager14.processDeposit(reservation66, "", "AVAILABLE");
        equipmentSensor1.removeObserver((org.example.sensors.SensorObserver) reservationManager14);
        org.junit.Assert.assertNotNull(strArrayList7);
        org.junit.Assert.assertNotNull(strArrayList19);
        org.junit.Assert.assertNotNull(strArrayList29);
        org.junit.Assert.assertEquals("'" + str43 + "' != '" + "hi!" + "'", str43, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus48 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus48.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str56 + "' != '" + "hi!" + "'", str56, "hi!");
        org.junit.Assert.assertNotNull(localDateTime57);
        org.junit.Assert.assertEquals("'" + str64 + "' != '" + "hi!" + "'", str64, "hi!");
        org.junit.Assert.assertNotNull(localDateTime65);
        org.junit.Assert.assertNotNull(reservation66);
        org.junit.Assert.assertTrue("'" + long67 + "' != '" + 0L + "'", long67 == 0L);
        org.junit.Assert.assertNotNull(payment70);
        org.junit.Assert.assertNotNull(localDateTime77);
        org.junit.Assert.assertNotNull(localDateTime84);
        org.junit.Assert.assertTrue("'" + boolean86 + "' != '" + true + "'", boolean86 == true);
        org.junit.Assert.assertNotNull(payment89);
    }

    @Test
    public void test161() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test161");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        reservationManager0.cancelReservation("");
        reservationManager0.cancelReservation("RES-1775354801261");
    }

    @Test
    public void test162() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test162");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.Guest guest8 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str9 = guest8.getUserType();
        cSVRepository0.updateUser((org.example.users.User) guest8);
        org.example.payment.Payment payment16 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime17 = payment16.getTimestamp();
        org.example.payment.PaymentDecorator paymentDecorator20 = new org.example.payment.PaymentDecorator(payment16, "", "hi!");
        cSVRepository0.savePayment(payment16);
        org.example.payment.Payment payment27 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str28 = payment27.getReservationId();
        java.time.LocalDateTime localDateTime29 = payment27.getTimestamp();
        boolean boolean30 = payment27.isForfeited();
        double double31 = payment27.getAmount();
        boolean boolean32 = payment27.isDeposit();
        payment27.setForfeited(true);
        boolean boolean35 = payment27.isDeposit();
        cSVRepository0.savePayment(payment27);
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "GUEST" + "'", str9, "GUEST");
        org.junit.Assert.assertNotNull(localDateTime17);
        org.junit.Assert.assertEquals("'" + str28 + "' != '" + "hi!" + "'", str28, "hi!");
        org.junit.Assert.assertNotNull(localDateTime29);
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + false + "'", boolean30 == false);
        org.junit.Assert.assertTrue("'" + double31 + "' != '" + 10.0d + "'", double31 == 10.0d);
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + false + "'", boolean32 == false);
        org.junit.Assert.assertTrue("'" + boolean35 + "' != '" + false + "'", boolean35 == false);
    }

    @Test
    public void test163() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test163");
        org.example.payment.UserPricingStrategy userPricingStrategy1 = new org.example.payment.UserPricingStrategy("RES-1775354801691");
    }

    @Test
    public void test164() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test164");
        org.example.users.User user7 = org.example.users.UserFactory.createUser("FACULTY", "", "NOT_APPROVED", "RES-1775354801691", "hi!", "RESEARCHER", "STUDENT");
        org.junit.Assert.assertNotNull(user7);
    }

    @Test
    public void test165() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test165");
        org.example.users.Student student6 = new org.example.users.Student("", "GUEST", "hi!", "hi!", "FACULTY", "");
        java.lang.String str7 = student6.getUserType();
        student6.setUserId("hi!");
        java.lang.String str10 = student6.getUserId();
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "STUDENT" + "'", str7, "STUDENT");
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "hi!" + "'", str10, "hi!");
    }

    @Test
    public void test166() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test166");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        reservationManager0.cancelReservation("");
        org.example.reservation.ReservationManager reservationManager7 = new org.example.reservation.ReservationManager();
        reservationManager7.update("hi!", "");
        org.example.users.Guest guest17 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest17.setIdNumber("");
        java.lang.String str20 = guest17.getDepartmentId();
        org.example.equipment.Equipment equipment24 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus25 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment24.setStatus(equipmentStatus25);
        org.example.payment.Payment payment32 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str33 = payment32.getReservationId();
        java.time.LocalDateTime localDateTime34 = payment32.getTimestamp();
        org.example.payment.Payment payment40 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str41 = payment40.getReservationId();
        java.time.LocalDateTime localDateTime42 = payment40.getTimestamp();
        org.example.reservation.Reservation reservation43 = reservationManager7.createReservation((org.example.users.User) guest17, equipment24, localDateTime34, localDateTime42);
        reservationManager0.modifyReservation("GUEST", localDateTime34);
        org.example.reservation.ReservationManager reservationManager45 = new org.example.reservation.ReservationManager();
        reservationManager45.update("hi!", "");
        org.example.users.Guest guest55 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest55.setIdNumber("");
        java.lang.String str58 = guest55.getDepartmentId();
        org.example.equipment.Equipment equipment62 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus63 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment62.setStatus(equipmentStatus63);
        org.example.payment.Payment payment70 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str71 = payment70.getReservationId();
        java.time.LocalDateTime localDateTime72 = payment70.getTimestamp();
        org.example.payment.Payment payment78 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str79 = payment78.getReservationId();
        java.time.LocalDateTime localDateTime80 = payment78.getTimestamp();
        org.example.reservation.Reservation reservation81 = reservationManager45.createReservation((org.example.users.User) guest55, equipment62, localDateTime72, localDateTime80);
        double double82 = reservation81.getDeposit();
        org.example.payment.Payment payment85 = reservationManager0.processDeposit(reservation81, "FACULTY", " -  () [AVAILABLE]");
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "hi!" + "'", str20, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus25 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus25.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "hi!" + "'", str33, "hi!");
        org.junit.Assert.assertNotNull(localDateTime34);
        org.junit.Assert.assertEquals("'" + str41 + "' != '" + "hi!" + "'", str41, "hi!");
        org.junit.Assert.assertNotNull(localDateTime42);
        org.junit.Assert.assertNotNull(reservation43);
        org.junit.Assert.assertEquals("'" + str58 + "' != '" + "hi!" + "'", str58, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus63 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus63.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str71 + "' != '" + "hi!" + "'", str71, "hi!");
        org.junit.Assert.assertNotNull(localDateTime72);
        org.junit.Assert.assertEquals("'" + str79 + "' != '" + "hi!" + "'", str79, "hi!");
        org.junit.Assert.assertNotNull(localDateTime80);
        org.junit.Assert.assertNotNull(reservation81);
        org.junit.Assert.assertTrue("'" + double82 + "' != '" + 30.0d + "'", double82 == 30.0d);
        org.junit.Assert.assertNotNull(payment85);
    }

    @Test
    public void test167() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test167");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        java.util.List<org.example.equipment.Equipment> equipmentList1 = equipmentManager0.getAllEquipment();
        equipmentManager0.enableEquipment("GUEST");
        org.junit.Assert.assertNotNull(equipmentList1);
    }

    @Test
    public void test168() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test168");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("", "", "");
        int int4 = equipment3.getAvailableUnits();
        java.lang.String str5 = equipment3.getDescription();
        equipment3.setAvailableUnits((int) 'a');
        equipment3.setName("");
        java.util.List<java.lang.String> strList10 = equipment3.getTags();
        java.lang.String str11 = equipment3.getImagePath();
        java.lang.String str12 = equipment3.toString();
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 1 + "'", int4 == 1);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertNotNull(strList10);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "" + "'", str11, "");
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + " -  () [AVAILABLE]" + "'", str12, " -  () [AVAILABLE]");
    }

    @Test
    public void test169() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test169");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.User user3 = cSVRepository0.findUserByEmail("");
        org.example.equipment.Equipment equipment7 = new org.example.equipment.Equipment("", "", "");
        int int8 = equipment7.getAvailableUnits();
        equipment7.setName("");
        int int11 = equipment7.getAvailableUnits();
        cSVRepository0.saveEquipment(equipment7);
        org.example.equipment.Equipment equipment16 = new org.example.equipment.Equipment("", "", "");
        cSVRepository0.updateEquipment(equipment16);
        java.util.List<java.lang.String[]> strArrayList19 = cSVRepository0.getReservationRowsByUserId("");
        java.util.List<java.lang.String[]> strArrayList21 = cSVRepository0.getReservationRowsByUserId("RESEARCHER");
        org.example.equipment.EquipmentManager equipmentManager22 = new org.example.equipment.EquipmentManager();
        equipmentManager22.update("hi!", "hi!");
        org.example.equipment.Equipment equipment29 = new org.example.equipment.Equipment("", "", "");
        int int30 = equipment29.getAvailableUnits();
        java.lang.String str31 = equipment29.getDescription();
        equipment29.setAvailableUnits((int) 'a');
        equipmentManager22.updateEquipment(equipment29);
        cSVRepository0.saveEquipment(equipment29);
        org.junit.Assert.assertNotNull(strArrayList1);
// flaky:         org.junit.Assert.assertNotNull(user3);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 1 + "'", int8 == 1);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 1 + "'", int11 == 1);
        org.junit.Assert.assertNotNull(strArrayList19);
        org.junit.Assert.assertNotNull(strArrayList21);
        org.junit.Assert.assertTrue("'" + int30 + "' != '" + 1 + "'", int30 == 1);
        org.junit.Assert.assertEquals("'" + str31 + "' != '" + "" + "'", str31, "");
    }

    @Test
    public void test170() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test170");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        org.example.users.Guest guest10 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest10.setIdNumber("");
        java.lang.String str13 = guest10.getDepartmentId();
        org.example.equipment.Equipment equipment17 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus18 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment17.setStatus(equipmentStatus18);
        org.example.payment.Payment payment25 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str26 = payment25.getReservationId();
        java.time.LocalDateTime localDateTime27 = payment25.getTimestamp();
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str34 = payment33.getReservationId();
        java.time.LocalDateTime localDateTime35 = payment33.getTimestamp();
        org.example.reservation.Reservation reservation36 = reservationManager0.createReservation((org.example.users.User) guest10, equipment17, localDateTime27, localDateTime35);
        long long37 = reservation36.getDurationHours();
        org.example.equipment.Equipment equipment38 = reservation36.getEquipment();
        org.example.users.User user39 = reservation36.getUser();
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!" + "'", str13, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus18 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus18.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hi!" + "'", str34, "hi!");
        org.junit.Assert.assertNotNull(localDateTime35);
        org.junit.Assert.assertNotNull(reservation36);
        org.junit.Assert.assertTrue("'" + long37 + "' != '" + 0L + "'", long37 == 0L);
        org.junit.Assert.assertNotNull(equipment38);
        org.junit.Assert.assertNotNull(user39);
    }

    @Test
    public void test171() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test171");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.equipment.Equipment equipment5 = new org.example.equipment.Equipment("", "", "");
        int int6 = equipment5.getAvailableUnits();
        java.lang.String str7 = equipment5.getDescription();
        equipment5.setAvailableUnits((int) 'a');
        cSVRepository0.updateEquipment(equipment5);
        java.util.List<java.lang.String> strList11 = null;
        equipment5.setTags(strList11);
        java.lang.String str13 = equipment5.toString();
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + " -  () [AVAILABLE]" + "'", str13, " -  () [AVAILABLE]");
    }

    @Test
    public void test172() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test172");
        org.example.users.Researcher researcher6 = new org.example.users.Researcher("", "", "", "hi!", "hi!", "");
        org.example.users.UserDecorator userDecorator8 = new org.example.users.UserDecorator((org.example.users.User) researcher6, "RES-1775354801261");
        java.lang.String str9 = userDecorator8.getCertificationNumber();
        java.lang.String str10 = userDecorator8.getApprovalStatus();
        java.lang.String str11 = userDecorator8.getDecorationType();
        org.junit.Assert.assertNull(str9);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "NOT_APPROVED" + "'", str10, "NOT_APPROVED");
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "RES-1775354801261" + "'", str11, "RES-1775354801261");
    }

    @Test
    public void test173() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test173");
        org.example.users.User user7 = org.example.users.UserFactory.createUser("GUEST", "GUEST", " -  () [AVAILABLE]", "GUEST", " -  () [AVAILABLE]", "GUEST", "GUEST");
        org.junit.Assert.assertNotNull(user7);
    }

    @Test
    public void test174() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test174");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        equipmentManager0.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList4 = equipmentManager0.getAllEquipment();
        equipmentManager0.enableEquipment("hi!");
        org.example.equipment.Equipment equipment8 = equipmentManager0.getEquipmentById("hi!");
        equipmentManager0.disableEquipment("RESEARCHER");
        org.example.equipment.Equipment equipment12 = equipmentManager0.getEquipmentById("RESEARCHER");
        org.junit.Assert.assertNotNull(equipmentList4);
        org.junit.Assert.assertNull(equipment8);
        org.junit.Assert.assertNull(equipment12);
    }

    @Test
    public void test175() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test175");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        org.example.users.Guest guest10 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest10.setIdNumber("");
        java.lang.String str13 = guest10.getDepartmentId();
        org.example.equipment.Equipment equipment17 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus18 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment17.setStatus(equipmentStatus18);
        org.example.payment.Payment payment25 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str26 = payment25.getReservationId();
        java.time.LocalDateTime localDateTime27 = payment25.getTimestamp();
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str34 = payment33.getReservationId();
        java.time.LocalDateTime localDateTime35 = payment33.getTimestamp();
        org.example.reservation.Reservation reservation36 = reservationManager0.createReservation((org.example.users.User) guest10, equipment17, localDateTime27, localDateTime35);
        org.example.users.Guest guest44 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str45 = guest44.getIdNumber();
        org.example.equipment.Equipment equipment49 = new org.example.equipment.Equipment("", "", "");
        org.example.payment.Payment payment55 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str56 = payment55.getReservationId();
        java.time.LocalDateTime localDateTime57 = payment55.getTimestamp();
        org.example.payment.Payment payment63 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime64 = payment63.getTimestamp();
        org.example.payment.Payment payment70 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime71 = payment70.getTimestamp();
        payment63.setTimestamp(localDateTime71);
        org.example.reservation.Reservation reservation74 = new org.example.reservation.Reservation("hi!", (org.example.users.User) guest44, equipment49, localDateTime57, localDateTime71, (double) (byte) -1);
        org.example.reservation.ReservationAction reservationAction76 = new org.example.reservation.ReservationAction(reservationManager0, reservation74, "GUEST");
        reservationAction76.execute();
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!" + "'", str13, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus18 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus18.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hi!" + "'", str34, "hi!");
        org.junit.Assert.assertNotNull(localDateTime35);
        org.junit.Assert.assertNotNull(reservation36);
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "" + "'", str45, "");
        org.junit.Assert.assertEquals("'" + str56 + "' != '" + "hi!" + "'", str56, "hi!");
        org.junit.Assert.assertNotNull(localDateTime57);
        org.junit.Assert.assertNotNull(localDateTime64);
        org.junit.Assert.assertNotNull(localDateTime71);
    }

    @Test
    public void test176() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test176");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        equipmentManager0.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList4 = equipmentManager0.getAllEquipment();
        equipmentManager0.enableEquipment("hi!");
        equipmentManager0.disableEquipment("");
        org.example.equipment.Equipment equipment12 = new org.example.equipment.Equipment("", "", "");
        int int13 = equipment12.getAvailableUnits();
        org.example.equipment.EquipmentAction equipmentAction15 = new org.example.equipment.EquipmentAction(equipmentManager0, equipment12, "RES-1775354801691");
        equipment12.setImagePath("NOT_APPROVED");
        org.junit.Assert.assertNotNull(equipmentList4);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 1 + "'", int13 == 1);
    }

    @Test
    public void test177() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test177");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.User user3 = cSVRepository0.findUserByEmail("");
        org.example.equipment.Equipment equipment7 = new org.example.equipment.Equipment("", "", "");
        int int8 = equipment7.getAvailableUnits();
        equipment7.setName("");
        int int11 = equipment7.getAvailableUnits();
        cSVRepository0.saveEquipment(equipment7);
        org.example.equipment.Equipment equipment16 = new org.example.equipment.Equipment("", "", "");
        cSVRepository0.updateEquipment(equipment16);
        java.util.List<java.lang.String[]> strArrayList19 = cSVRepository0.getReservationRowsByUserId("");
        java.util.List<java.lang.String[]> strArrayList20 = cSVRepository0.getAllReservationRows();
        java.util.List<java.lang.String[]> strArrayList21 = cSVRepository0.getAllReservationRows();
        org.junit.Assert.assertNotNull(strArrayList1);
// flaky:         org.junit.Assert.assertNotNull(user3);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 1 + "'", int8 == 1);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 1 + "'", int11 == 1);
        org.junit.Assert.assertNotNull(strArrayList19);
        org.junit.Assert.assertNotNull(strArrayList20);
        org.junit.Assert.assertNotNull(strArrayList21);
    }

    @Test
    public void test178() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test178");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        equipmentManager0.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList4 = equipmentManager0.getAllEquipment();
        equipmentManager0.disableEquipment("");
        java.util.List<org.example.equipment.Equipment> equipmentList7 = equipmentManager0.getAvailableEquipment();
        org.example.reservation.ReservationManager reservationManager8 = new org.example.reservation.ReservationManager();
        reservationManager8.update("hi!", "");
        org.example.users.Guest guest18 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest18.setIdNumber("");
        java.lang.String str21 = guest18.getDepartmentId();
        org.example.equipment.Equipment equipment25 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus26 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment25.setStatus(equipmentStatus26);
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str34 = payment33.getReservationId();
        java.time.LocalDateTime localDateTime35 = payment33.getTimestamp();
        org.example.payment.Payment payment41 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str42 = payment41.getReservationId();
        java.time.LocalDateTime localDateTime43 = payment41.getTimestamp();
        org.example.reservation.Reservation reservation44 = reservationManager8.createReservation((org.example.users.User) guest18, equipment25, localDateTime35, localDateTime43);
        org.example.users.Guest guest52 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str53 = guest52.getIdNumber();
        org.example.equipment.Equipment equipment57 = new org.example.equipment.Equipment("", "", "");
        org.example.payment.Payment payment63 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str64 = payment63.getReservationId();
        java.time.LocalDateTime localDateTime65 = payment63.getTimestamp();
        org.example.payment.Payment payment71 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime72 = payment71.getTimestamp();
        org.example.payment.Payment payment78 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime79 = payment78.getTimestamp();
        payment71.setTimestamp(localDateTime79);
        org.example.reservation.Reservation reservation82 = new org.example.reservation.Reservation("hi!", (org.example.users.User) guest52, equipment57, localDateTime65, localDateTime79, (double) (byte) -1);
        org.example.reservation.ReservationAction reservationAction84 = new org.example.reservation.ReservationAction(reservationManager8, reservation82, "GUEST");
        org.example.equipment.Equipment equipment85 = reservation82.getEquipment();
        equipmentManager0.addEquipment(equipment85);
        equipmentManager0.enableEquipment("RESEARCHER");
        org.junit.Assert.assertNotNull(equipmentList4);
        org.junit.Assert.assertNotNull(equipmentList7);
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "hi!" + "'", str21, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus26 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus26.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hi!" + "'", str34, "hi!");
        org.junit.Assert.assertNotNull(localDateTime35);
        org.junit.Assert.assertEquals("'" + str42 + "' != '" + "hi!" + "'", str42, "hi!");
        org.junit.Assert.assertNotNull(localDateTime43);
        org.junit.Assert.assertNotNull(reservation44);
        org.junit.Assert.assertEquals("'" + str53 + "' != '" + "" + "'", str53, "");
        org.junit.Assert.assertEquals("'" + str64 + "' != '" + "hi!" + "'", str64, "hi!");
        org.junit.Assert.assertNotNull(localDateTime65);
        org.junit.Assert.assertNotNull(localDateTime72);
        org.junit.Assert.assertNotNull(localDateTime79);
        org.junit.Assert.assertNotNull(equipment85);
    }

    @Test
    public void test179() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test179");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("", "", "");
        int int4 = equipment3.getAvailableUnits();
        equipment3.setProductStatistics("");
        java.lang.String str7 = equipment3.getName();
        equipment3.setAvailableUnits((int) (short) 100);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 1 + "'", int4 == 1);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
    }

    @Test
    public void test180() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test180");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        reservationManager0.cancelReservation("");
        org.example.reservation.ReservationManager reservationManager7 = new org.example.reservation.ReservationManager();
        reservationManager7.update("hi!", "");
        org.example.users.Guest guest17 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest17.setIdNumber("");
        java.lang.String str20 = guest17.getDepartmentId();
        org.example.equipment.Equipment equipment24 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus25 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment24.setStatus(equipmentStatus25);
        org.example.payment.Payment payment32 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str33 = payment32.getReservationId();
        java.time.LocalDateTime localDateTime34 = payment32.getTimestamp();
        org.example.payment.Payment payment40 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str41 = payment40.getReservationId();
        java.time.LocalDateTime localDateTime42 = payment40.getTimestamp();
        org.example.reservation.Reservation reservation43 = reservationManager7.createReservation((org.example.users.User) guest17, equipment24, localDateTime34, localDateTime42);
        reservationManager0.modifyReservation("GUEST", localDateTime34);
        org.example.reservation.ReservationManager reservationManager45 = new org.example.reservation.ReservationManager();
        reservationManager45.update("hi!", "");
        org.example.users.Guest guest55 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest55.setIdNumber("");
        java.lang.String str58 = guest55.getDepartmentId();
        org.example.equipment.Equipment equipment62 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus63 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment62.setStatus(equipmentStatus63);
        org.example.payment.Payment payment70 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str71 = payment70.getReservationId();
        java.time.LocalDateTime localDateTime72 = payment70.getTimestamp();
        org.example.payment.Payment payment78 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str79 = payment78.getReservationId();
        java.time.LocalDateTime localDateTime80 = payment78.getTimestamp();
        org.example.reservation.Reservation reservation81 = reservationManager45.createReservation((org.example.users.User) guest55, equipment62, localDateTime72, localDateTime80);
        double double82 = reservation81.getDeposit();
        org.example.payment.Payment payment86 = reservationManager0.processFinalPayment(reservation81, "STUDENT", "RES-1775354801261", true);
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "hi!" + "'", str20, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus25 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus25.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "hi!" + "'", str33, "hi!");
        org.junit.Assert.assertNotNull(localDateTime34);
        org.junit.Assert.assertEquals("'" + str41 + "' != '" + "hi!" + "'", str41, "hi!");
        org.junit.Assert.assertNotNull(localDateTime42);
        org.junit.Assert.assertNotNull(reservation43);
        org.junit.Assert.assertEquals("'" + str58 + "' != '" + "hi!" + "'", str58, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus63 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus63.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str71 + "' != '" + "hi!" + "'", str71, "hi!");
        org.junit.Assert.assertNotNull(localDateTime72);
        org.junit.Assert.assertEquals("'" + str79 + "' != '" + "hi!" + "'", str79, "hi!");
        org.junit.Assert.assertNotNull(localDateTime80);
        org.junit.Assert.assertNotNull(reservation81);
        org.junit.Assert.assertTrue("'" + double82 + "' != '" + 30.0d + "'", double82 == 30.0d);
        org.junit.Assert.assertNotNull(payment86);
    }

    @Test
    public void test181() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test181");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str6 = payment5.getReservationId();
        java.time.LocalDateTime localDateTime7 = payment5.getTimestamp();
        boolean boolean8 = payment5.isForfeited();
        double double9 = payment5.getAmount();
        boolean boolean10 = payment5.isDeposit();
        payment5.setForfeited(true);
        boolean boolean13 = payment5.isDeposit();
        double double14 = payment5.getAmount();
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "hi!" + "'", str6, "hi!");
        org.junit.Assert.assertNotNull(localDateTime7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + double9 + "' != '" + 10.0d + "'", double9 == 10.0d);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertTrue("'" + double14 + "' != '" + 10.0d + "'", double14 == 10.0d);
    }

    @Test
    public void test182() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test182");
        org.example.sensors.EquipmentSensor equipmentSensor1 = new org.example.sensors.EquipmentSensor("RESEARCHER");
        equipmentSensor1.notifyObservers("", "FACULTY");
        java.lang.String str5 = equipmentSensor1.getCurrentStatus();
        equipmentSensor1.notifyObservers("RESEARCHER", "RES-1775354801261");
        java.lang.String str9 = equipmentSensor1.getEquipmentId();
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "AVAILABLE" + "'", str5, "AVAILABLE");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "RESEARCHER" + "'", str9, "RESEARCHER");
    }

    @Test
    public void test183() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test183");
        org.example.sensors.EquipmentSensor equipmentSensor1 = new org.example.sensors.EquipmentSensor("RESEARCHER");
        org.example.reservation.ReservationManager reservationManager2 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation3 = null;
        java.time.LocalDateTime localDateTime5 = null;
        org.example.reservation.ReservationAction reservationAction6 = new org.example.reservation.ReservationAction(reservationManager2, reservation3, "", localDateTime5);
        equipmentSensor1.removeObserver((org.example.sensors.SensorObserver) reservationManager2);
        equipmentSensor1.triggerUpdate("");
        org.example.equipment.EquipmentManager equipmentManager10 = new org.example.equipment.EquipmentManager();
        equipmentManager10.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList14 = equipmentManager10.getAllEquipment();
        equipmentManager10.enableEquipment("hi!");
        org.example.equipment.Equipment equipment18 = equipmentManager10.getEquipmentById("hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList19 = equipmentManager10.getAvailableEquipment();
        equipmentSensor1.addObserver((org.example.sensors.SensorObserver) equipmentManager10);
        org.junit.Assert.assertNotNull(equipmentList14);
        org.junit.Assert.assertNull(equipment18);
        org.junit.Assert.assertNotNull(equipmentList19);
    }

    @Test
    public void test184() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test184");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.User user3 = cSVRepository0.findUserByEmail("");
        java.util.List<org.example.users.User> userList4 = cSVRepository0.getAllUsers();
        java.util.List<org.example.users.User> userList5 = cSVRepository0.getAllUsers();
        org.example.reservation.ReservationManager reservationManager6 = new org.example.reservation.ReservationManager();
        reservationManager6.update("hi!", "");
        org.example.users.Guest guest16 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest16.setIdNumber("");
        java.lang.String str19 = guest16.getDepartmentId();
        org.example.equipment.Equipment equipment23 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus24 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment23.setStatus(equipmentStatus24);
        org.example.payment.Payment payment31 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str32 = payment31.getReservationId();
        java.time.LocalDateTime localDateTime33 = payment31.getTimestamp();
        org.example.payment.Payment payment39 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str40 = payment39.getReservationId();
        java.time.LocalDateTime localDateTime41 = payment39.getTimestamp();
        org.example.reservation.Reservation reservation42 = reservationManager6.createReservation((org.example.users.User) guest16, equipment23, localDateTime33, localDateTime41);
        org.example.users.Guest guest50 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str51 = guest50.getIdNumber();
        org.example.equipment.Equipment equipment55 = new org.example.equipment.Equipment("", "", "");
        org.example.payment.Payment payment61 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str62 = payment61.getReservationId();
        java.time.LocalDateTime localDateTime63 = payment61.getTimestamp();
        org.example.payment.Payment payment69 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime70 = payment69.getTimestamp();
        org.example.payment.Payment payment76 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime77 = payment76.getTimestamp();
        payment69.setTimestamp(localDateTime77);
        org.example.reservation.Reservation reservation80 = new org.example.reservation.Reservation("hi!", (org.example.users.User) guest50, equipment55, localDateTime63, localDateTime77, (double) (byte) -1);
        org.example.reservation.ReservationAction reservationAction82 = new org.example.reservation.ReservationAction(reservationManager6, reservation80, "GUEST");
        org.example.reservation.ReservationStatus reservationStatus83 = org.example.reservation.ReservationStatus.CANCELLED;
        reservation80.setStatus(reservationStatus83);
        cSVRepository0.updateReservation(reservation80);
        org.junit.Assert.assertNotNull(strArrayList1);
// flaky:         org.junit.Assert.assertNotNull(user3);
        org.junit.Assert.assertNotNull(userList4);
        org.junit.Assert.assertNotNull(userList5);
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "hi!" + "'", str19, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus24 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus24.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str32 + "' != '" + "hi!" + "'", str32, "hi!");
        org.junit.Assert.assertNotNull(localDateTime33);
        org.junit.Assert.assertEquals("'" + str40 + "' != '" + "hi!" + "'", str40, "hi!");
        org.junit.Assert.assertNotNull(localDateTime41);
        org.junit.Assert.assertNotNull(reservation42);
        org.junit.Assert.assertEquals("'" + str51 + "' != '" + "" + "'", str51, "");
        org.junit.Assert.assertEquals("'" + str62 + "' != '" + "hi!" + "'", str62, "hi!");
        org.junit.Assert.assertNotNull(localDateTime63);
        org.junit.Assert.assertNotNull(localDateTime70);
        org.junit.Assert.assertNotNull(localDateTime77);
        org.junit.Assert.assertTrue("'" + reservationStatus83 + "' != '" + org.example.reservation.ReservationStatus.CANCELLED + "'", reservationStatus83.equals(org.example.reservation.ReservationStatus.CANCELLED));
    }

    @Test
    public void test185() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test185");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.User user3 = cSVRepository0.findUserByEmail("");
        org.example.equipment.Equipment equipment7 = new org.example.equipment.Equipment("", "", "");
        int int8 = equipment7.getAvailableUnits();
        equipment7.setName("");
        int int11 = equipment7.getAvailableUnits();
        cSVRepository0.saveEquipment(equipment7);
        org.example.equipment.Equipment equipment16 = new org.example.equipment.Equipment("", "", "");
        cSVRepository0.updateEquipment(equipment16);
        org.example.equipment.Equipment equipment21 = new org.example.equipment.Equipment("", "", "");
        int int22 = equipment21.getAvailableUnits();
        java.lang.String str23 = equipment21.getDescription();
        java.lang.String str24 = equipment21.getEquipmentId();
        cSVRepository0.updateEquipment(equipment21);
        org.example.equipment.Equipment equipment29 = new org.example.equipment.Equipment("", "", "");
        int int30 = equipment29.getAvailableUnits();
        java.lang.String str31 = equipment29.getDescription();
        org.example.equipment.EquipmentStatus equipmentStatus32 = equipment29.getStatus();
        cSVRepository0.saveEquipment(equipment29);
        org.junit.Assert.assertNotNull(strArrayList1);
// flaky:         org.junit.Assert.assertNotNull(user3);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 1 + "'", int8 == 1);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 1 + "'", int11 == 1);
        org.junit.Assert.assertTrue("'" + int22 + "' != '" + 1 + "'", int22 == 1);
        org.junit.Assert.assertEquals("'" + str23 + "' != '" + "" + "'", str23, "");
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "" + "'", str24, "");
        org.junit.Assert.assertTrue("'" + int30 + "' != '" + 1 + "'", int30 == 1);
        org.junit.Assert.assertEquals("'" + str31 + "' != '" + "" + "'", str31, "");
        org.junit.Assert.assertTrue("'" + equipmentStatus32 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus32.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
    }

    @Test
    public void test186() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test186");
        org.example.sensors.EquipmentSensor equipmentSensor1 = new org.example.sensors.EquipmentSensor("RESEARCHER");
        org.example.reservation.ReservationManager reservationManager2 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation3 = null;
        java.time.LocalDateTime localDateTime5 = null;
        org.example.reservation.ReservationAction reservationAction6 = new org.example.reservation.ReservationAction(reservationManager2, reservation3, "", localDateTime5);
        java.util.List<java.lang.String[]> strArrayList7 = reservationManager2.getAllReservationRows();
        equipmentSensor1.addObserver((org.example.sensors.SensorObserver) reservationManager2);
        reservationManager2.update("FACULTY", "");
        org.example.reservation.ReservationManager reservationManager12 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation13 = null;
        java.time.LocalDateTime localDateTime15 = null;
        org.example.reservation.ReservationAction reservationAction16 = new org.example.reservation.ReservationAction(reservationManager12, reservation13, "", localDateTime15);
        java.util.List<java.lang.String[]> strArrayList17 = reservationManager12.getAllReservationRows();
        org.example.reservation.ReservationManager reservationManager18 = new org.example.reservation.ReservationManager();
        reservationManager18.update("hi!", "");
        org.example.users.Guest guest28 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest28.setIdNumber("");
        java.lang.String str31 = guest28.getDepartmentId();
        org.example.equipment.Equipment equipment35 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus36 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment35.setStatus(equipmentStatus36);
        org.example.payment.Payment payment43 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str44 = payment43.getReservationId();
        java.time.LocalDateTime localDateTime45 = payment43.getTimestamp();
        org.example.payment.Payment payment51 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str52 = payment51.getReservationId();
        java.time.LocalDateTime localDateTime53 = payment51.getTimestamp();
        org.example.reservation.Reservation reservation54 = reservationManager18.createReservation((org.example.users.User) guest28, equipment35, localDateTime45, localDateTime53);
        long long55 = reservation54.getDurationHours();
        org.example.payment.Payment payment58 = reservationManager12.processDeposit(reservation54, "", "FACULTY");
        org.example.payment.Payment payment64 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime65 = payment64.getTimestamp();
        org.example.payment.Payment payment71 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime72 = payment71.getTimestamp();
        payment64.setTimestamp(localDateTime72);
        boolean boolean74 = reservation54.arrivedOnTime(localDateTime72);
        org.example.payment.Payment payment77 = reservationManager2.processDeposit(reservation54, "", "AVAILABLE");
        java.util.List<java.lang.String[]> strArrayList78 = reservationManager2.getAllReservationRows();
        org.junit.Assert.assertNotNull(strArrayList7);
        org.junit.Assert.assertNotNull(strArrayList17);
        org.junit.Assert.assertEquals("'" + str31 + "' != '" + "hi!" + "'", str31, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus36 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus36.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str44 + "' != '" + "hi!" + "'", str44, "hi!");
        org.junit.Assert.assertNotNull(localDateTime45);
        org.junit.Assert.assertEquals("'" + str52 + "' != '" + "hi!" + "'", str52, "hi!");
        org.junit.Assert.assertNotNull(localDateTime53);
        org.junit.Assert.assertNotNull(reservation54);
        org.junit.Assert.assertTrue("'" + long55 + "' != '" + 0L + "'", long55 == 0L);
        org.junit.Assert.assertNotNull(payment58);
        org.junit.Assert.assertNotNull(localDateTime65);
        org.junit.Assert.assertNotNull(localDateTime72);
        org.junit.Assert.assertTrue("'" + boolean74 + "' != '" + true + "'", boolean74 == true);
        org.junit.Assert.assertNotNull(payment77);
        org.junit.Assert.assertNotNull(strArrayList78);
    }

    @Test
    public void test187() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test187");
        org.example.equipment.EquipmentManager equipmentManager0 = null;
        org.example.equipment.Equipment equipment1 = null;
        org.example.equipment.EquipmentAction equipmentAction3 = new org.example.equipment.EquipmentAction(equipmentManager0, equipment1, "hi!");
        equipmentAction3.undo();
        equipmentAction3.undo();
        equipmentAction3.undo();
        // The following exception was thrown during execution in test generation
        try {
            equipmentAction3.execute();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.example.equipment.Equipment.getStatus()\" because \"this.equipment\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test188() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test188");
        org.example.sensors.EquipmentSensor equipmentSensor1 = new org.example.sensors.EquipmentSensor("RESEARCHER");
        equipmentSensor1.notifyObservers("", "FACULTY");
        equipmentSensor1.notifyObservers("GUEST", "FACULTY");
        org.example.equipment.EquipmentManager equipmentManager8 = new org.example.equipment.EquipmentManager();
        equipmentManager8.update("hi!", "hi!");
        equipmentManager8.enableEquipment("RES-1775354801261");
        equipmentSensor1.removeObserver((org.example.sensors.SensorObserver) equipmentManager8);
    }

    @Test
    public void test189() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test189");
        org.example.users.Faculty faculty6 = new org.example.users.Faculty("", "", "hi!", "hi!", "hi!", "hi!");
        java.lang.String str7 = faculty6.getUserType();
        java.lang.String str8 = faculty6.getEmail();
        java.lang.String str9 = faculty6.getIdNumber();
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "FACULTY" + "'", str7, "FACULTY");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "hi!" + "'", str8, "hi!");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "hi!" + "'", str9, "hi!");
    }

    @Test
    public void test190() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test190");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        equipmentManager0.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList4 = equipmentManager0.getAllEquipment();
        equipmentManager0.enableEquipment("hi!");
        org.example.equipment.Equipment equipment8 = equipmentManager0.getEquipmentById("hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList9 = equipmentManager0.getAvailableEquipment();
        org.example.equipment.Equipment equipment11 = equipmentManager0.getEquipmentById("FACULTY");
        org.example.equipment.Equipment equipment15 = new org.example.equipment.Equipment("", "", "");
        int int16 = equipment15.getAvailableUnits();
        java.lang.String str17 = equipment15.getDescription();
        java.lang.String str18 = equipment15.getEquipmentId();
        equipmentManager0.updateEquipment(equipment15);
        equipment15.setProductStatistics("RESEARCHER");
        org.junit.Assert.assertNotNull(equipmentList4);
        org.junit.Assert.assertNull(equipment8);
        org.junit.Assert.assertNotNull(equipmentList9);
        org.junit.Assert.assertNull(equipment11);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 1 + "'", int16 == 1);
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "" + "'", str17, "");
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "" + "'", str18, "");
    }

    @Test
    public void test191() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test191");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        org.example.users.Guest guest10 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest10.setIdNumber("");
        java.lang.String str13 = guest10.getDepartmentId();
        org.example.equipment.Equipment equipment17 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus18 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment17.setStatus(equipmentStatus18);
        org.example.payment.Payment payment25 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str26 = payment25.getReservationId();
        java.time.LocalDateTime localDateTime27 = payment25.getTimestamp();
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str34 = payment33.getReservationId();
        java.time.LocalDateTime localDateTime35 = payment33.getTimestamp();
        org.example.reservation.Reservation reservation36 = reservationManager0.createReservation((org.example.users.User) guest10, equipment17, localDateTime27, localDateTime35);
        org.example.users.Guest guest44 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str45 = guest44.getIdNumber();
        org.example.equipment.Equipment equipment49 = new org.example.equipment.Equipment("", "", "");
        org.example.payment.Payment payment55 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str56 = payment55.getReservationId();
        java.time.LocalDateTime localDateTime57 = payment55.getTimestamp();
        org.example.payment.Payment payment63 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime64 = payment63.getTimestamp();
        org.example.payment.Payment payment70 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime71 = payment70.getTimestamp();
        payment63.setTimestamp(localDateTime71);
        org.example.reservation.Reservation reservation74 = new org.example.reservation.Reservation("hi!", (org.example.users.User) guest44, equipment49, localDateTime57, localDateTime71, (double) (byte) -1);
        org.example.reservation.ReservationAction reservationAction76 = new org.example.reservation.ReservationAction(reservationManager0, reservation74, "GUEST");
        org.example.equipment.Equipment equipment77 = reservation74.getEquipment();
        org.example.users.User user78 = reservation74.getUser();
        org.example.users.User user79 = reservation74.getUser();
        java.lang.String str80 = reservation74.getReservationId();
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!" + "'", str13, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus18 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus18.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hi!" + "'", str34, "hi!");
        org.junit.Assert.assertNotNull(localDateTime35);
        org.junit.Assert.assertNotNull(reservation36);
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "" + "'", str45, "");
        org.junit.Assert.assertEquals("'" + str56 + "' != '" + "hi!" + "'", str56, "hi!");
        org.junit.Assert.assertNotNull(localDateTime57);
        org.junit.Assert.assertNotNull(localDateTime64);
        org.junit.Assert.assertNotNull(localDateTime71);
        org.junit.Assert.assertNotNull(equipment77);
        org.junit.Assert.assertNotNull(user78);
        org.junit.Assert.assertNotNull(user79);
        org.junit.Assert.assertEquals("'" + str80 + "' != '" + "hi!" + "'", str80, "hi!");
    }

    @Test
    public void test192() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test192");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.equipment.Equipment equipment5 = new org.example.equipment.Equipment("", "", "");
        int int6 = equipment5.getAvailableUnits();
        java.lang.String str7 = equipment5.getDescription();
        equipment5.setAvailableUnits((int) 'a');
        cSVRepository0.updateEquipment(equipment5);
        org.example.equipment.Equipment equipment14 = new org.example.equipment.Equipment("", "", "");
        int int15 = equipment14.getAvailableUnits();
        equipment14.setName("");
        cSVRepository0.saveEquipment(equipment14);
        java.lang.String str19 = equipment14.toString();
        equipment14.setImagePath("GUEST");
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 1 + "'", int15 == 1);
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + " -  () [AVAILABLE]" + "'", str19, " -  () [AVAILABLE]");
    }

    @Test
    public void test193() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test193");
        org.example.users.Guest guest6 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest6.setIdNumber("");
        guest6.setPassword("");
        org.example.users.UserDecorator userDecorator12 = new org.example.users.UserDecorator((org.example.users.User) guest6, "");
        java.lang.String str13 = userDecorator12.getUserType();
        java.lang.String str14 = userDecorator12.getPassword();
        java.lang.String str15 = userDecorator12.getApprovalStatus();
        java.lang.String str16 = userDecorator12.getDecorationType();
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "GUEST" + "'", str13, "GUEST");
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "" + "'", str14, "");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "NOT_APPROVED" + "'", str15, "NOT_APPROVED");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "" + "'", str16, "");
    }

    @Test
    public void test194() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test194");
        org.example.equipment.EquipmentManager equipmentManager0 = null;
        org.example.reservation.ReservationManager reservationManager1 = new org.example.reservation.ReservationManager();
        reservationManager1.update("hi!", "");
        org.example.users.Guest guest11 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest11.setIdNumber("");
        java.lang.String str14 = guest11.getDepartmentId();
        org.example.equipment.Equipment equipment18 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus19 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment18.setStatus(equipmentStatus19);
        org.example.payment.Payment payment26 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str27 = payment26.getReservationId();
        java.time.LocalDateTime localDateTime28 = payment26.getTimestamp();
        org.example.payment.Payment payment34 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str35 = payment34.getReservationId();
        java.time.LocalDateTime localDateTime36 = payment34.getTimestamp();
        org.example.reservation.Reservation reservation37 = reservationManager1.createReservation((org.example.users.User) guest11, equipment18, localDateTime28, localDateTime36);
        org.example.users.Guest guest45 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str46 = guest45.getIdNumber();
        org.example.equipment.Equipment equipment50 = new org.example.equipment.Equipment("", "", "");
        org.example.payment.Payment payment56 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str57 = payment56.getReservationId();
        java.time.LocalDateTime localDateTime58 = payment56.getTimestamp();
        org.example.payment.Payment payment64 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime65 = payment64.getTimestamp();
        org.example.payment.Payment payment71 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime72 = payment71.getTimestamp();
        payment64.setTimestamp(localDateTime72);
        org.example.reservation.Reservation reservation75 = new org.example.reservation.Reservation("hi!", (org.example.users.User) guest45, equipment50, localDateTime58, localDateTime72, (double) (byte) -1);
        org.example.reservation.ReservationAction reservationAction77 = new org.example.reservation.ReservationAction(reservationManager1, reservation75, "GUEST");
        org.example.equipment.Equipment equipment78 = reservation75.getEquipment();
        equipment78.setProductStatistics("hi!");
        org.example.equipment.EquipmentAction equipmentAction82 = new org.example.equipment.EquipmentAction(equipmentManager0, equipment78, "NOT_APPROVED");
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "hi!" + "'", str14, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus19 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus19.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str27 + "' != '" + "hi!" + "'", str27, "hi!");
        org.junit.Assert.assertNotNull(localDateTime28);
        org.junit.Assert.assertEquals("'" + str35 + "' != '" + "hi!" + "'", str35, "hi!");
        org.junit.Assert.assertNotNull(localDateTime36);
        org.junit.Assert.assertNotNull(reservation37);
        org.junit.Assert.assertEquals("'" + str46 + "' != '" + "" + "'", str46, "");
        org.junit.Assert.assertEquals("'" + str57 + "' != '" + "hi!" + "'", str57, "hi!");
        org.junit.Assert.assertNotNull(localDateTime58);
        org.junit.Assert.assertNotNull(localDateTime65);
        org.junit.Assert.assertNotNull(localDateTime72);
        org.junit.Assert.assertNotNull(equipment78);
    }

    @Test
    public void test195() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test195");
        org.example.equipment.EquipmentManager equipmentManager0 = null;
        org.example.equipment.Equipment equipment1 = null;
        org.example.equipment.EquipmentAction equipmentAction3 = new org.example.equipment.EquipmentAction(equipmentManager0, equipment1, "hi!");
        equipmentAction3.undo();
        // The following exception was thrown during execution in test generation
        try {
            equipmentAction3.execute();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.example.equipment.Equipment.getStatus()\" because \"this.equipment\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test196() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test196");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        equipmentManager0.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList4 = equipmentManager0.getAllEquipment();
        equipmentManager0.enableEquipment("hi!");
        org.example.equipment.Equipment equipment8 = equipmentManager0.getEquipmentById("hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList9 = equipmentManager0.getAvailableEquipment();
        equipmentManager0.disableEquipment("RES-1775354801691");
        equipmentManager0.update("hi!", "NOT_APPROVED");
        org.junit.Assert.assertNotNull(equipmentList4);
        org.junit.Assert.assertNull(equipment8);
        org.junit.Assert.assertNotNull(equipmentList9);
    }

    @Test
    public void test197() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test197");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str6 = payment5.getReservationId();
        java.time.LocalDateTime localDateTime7 = payment5.getTimestamp();
        boolean boolean8 = payment5.isForfeited();
        double double9 = payment5.getAmount();
        boolean boolean10 = payment5.isDeposit();
        java.time.LocalDateTime localDateTime11 = payment5.getTimestamp();
        payment5.setForfeited(true);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "hi!" + "'", str6, "hi!");
        org.junit.Assert.assertNotNull(localDateTime7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + double9 + "' != '" + 10.0d + "'", double9 == 10.0d);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(localDateTime11);
    }

    @Test
    public void test198() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test198");
        org.example.sensors.EquipmentSensor equipmentSensor1 = new org.example.sensors.EquipmentSensor("RESEARCHER");
        equipmentSensor1.notifyObservers("", "FACULTY");
        org.example.reservation.ReservationManager reservationManager5 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation6 = null;
        java.time.LocalDateTime localDateTime8 = null;
        org.example.reservation.ReservationAction reservationAction9 = new org.example.reservation.ReservationAction(reservationManager5, reservation6, "", localDateTime8);
        equipmentSensor1.removeObserver((org.example.sensors.SensorObserver) reservationManager5);
    }

    @Test
    public void test199() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test199");
        boolean boolean1 = org.example.auth.PasswordValidator.isValid("NOT_APPROVED");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test200() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test200");
        org.example.users.Faculty faculty6 = new org.example.users.Faculty("hi!", "NOT_APPROVED", "hi!", "", "", "FACULTY");
    }

    @Test
    public void test201() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test201");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.equipment.Equipment equipment5 = new org.example.equipment.Equipment("", "", "");
        int int6 = equipment5.getAvailableUnits();
        java.lang.String str7 = equipment5.getDescription();
        equipment5.setAvailableUnits((int) 'a');
        cSVRepository0.updateEquipment(equipment5);
        equipment5.setImagePath("hi!");
        java.lang.String str13 = equipment5.getDescription();
        org.example.equipment.EquipmentStatus equipmentStatus14 = equipment5.getStatus();
        org.example.equipment.Equipment equipment18 = new org.example.equipment.Equipment("", "", "");
        int int19 = equipment18.getAvailableUnits();
        java.lang.String str20 = equipment18.getDescription();
        equipment18.setAvailableUnits((int) 'a');
        equipment18.setName("");
        java.util.List<java.lang.String> strList25 = equipment18.getTags();
        equipment5.setTags(strList25);
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
        org.junit.Assert.assertTrue("'" + equipmentStatus14 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus14.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertTrue("'" + int19 + "' != '" + 1 + "'", int19 == 1);
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "" + "'", str20, "");
        org.junit.Assert.assertNotNull(strList25);
    }

    @Test
    public void test202() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test202");
        org.example.sensors.EquipmentSensor equipmentSensor1 = new org.example.sensors.EquipmentSensor("RESEARCHER");
        org.example.reservation.ReservationManager reservationManager2 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation3 = null;
        java.time.LocalDateTime localDateTime5 = null;
        org.example.reservation.ReservationAction reservationAction6 = new org.example.reservation.ReservationAction(reservationManager2, reservation3, "", localDateTime5);
        equipmentSensor1.removeObserver((org.example.sensors.SensorObserver) reservationManager2);
        equipmentSensor1.triggerUpdate("");
        equipmentSensor1.triggerUpdate("RES-1775354801261");
    }

    @Test
    public void test203() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test203");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("", "", "");
        int int4 = equipment3.getAvailableUnits();
        equipment3.setName("");
        int int7 = equipment3.getAvailableUnits();
        equipment3.setImagePath(" -  () [AVAILABLE]");
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 1 + "'", int4 == 1);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 1 + "'", int7 == 1);
    }

    @Test
    public void test204() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test204");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("", "", (double) '#', "RES-1775354801691", false);
    }

    @Test
    public void test205() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test205");
        org.example.payment.UserPricingStrategy userPricingStrategy1 = new org.example.payment.UserPricingStrategy("");
        double double2 = userPricingStrategy1.calculateDeposit();
        double double4 = userPricingStrategy1.calculateTotal((int) (short) 100);
        double double6 = userPricingStrategy1.calculateTotal((int) (byte) 100);
        org.junit.Assert.assertTrue("'" + double2 + "' != '" + 0.0d + "'", double2 == 0.0d);
        org.junit.Assert.assertTrue("'" + double4 + "' != '" + 0.0d + "'", double4 == 0.0d);
        org.junit.Assert.assertTrue("'" + double6 + "' != '" + 0.0d + "'", double6 == 0.0d);
    }

    @Test
    public void test206() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test206");
        org.example.auth.PasswordValidator passwordValidator0 = new org.example.auth.PasswordValidator();
    }

    @Test
    public void test207() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test207");
        org.example.users.Guest guest6 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str7 = guest6.getUserType();
        org.example.users.UserDecorator userDecorator9 = new org.example.users.UserDecorator((org.example.users.User) guest6, "");
        java.lang.String str10 = guest6.getName();
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "GUEST" + "'", str7, "GUEST");
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "" + "'", str10, "");
    }

    @Test
    public void test208() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test208");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        java.lang.String[] strArray3 = cSVRepository0.findEquipmentRowById("RES-1775354801261");
        java.util.List<java.lang.String[]> strArrayList5 = cSVRepository0.getPaymentRowsByReservationId("NOT_APPROVED");
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertNull(strArray3);
        org.junit.Assert.assertNotNull(strArrayList5);
    }

    @Test
    public void test209() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test209");
        org.example.payment.UserPricingStrategy userPricingStrategy1 = new org.example.payment.UserPricingStrategy("");
        double double2 = userPricingStrategy1.getHourlyRate();
        org.junit.Assert.assertTrue("'" + double2 + "' != '" + 0.0d + "'", double2 == 0.0d);
    }

    @Test
    public void test210() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test210");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str6 = payment5.getReservationId();
        java.time.LocalDateTime localDateTime7 = payment5.getTimestamp();
        boolean boolean8 = payment5.isForfeited();
        org.example.payment.PaymentDecorator paymentDecorator11 = new org.example.payment.PaymentDecorator(payment5, "", "GUEST");
        boolean boolean12 = paymentDecorator11.validate();
        java.lang.String str13 = paymentDecorator11.getPaymentMethod();
        org.example.payment.Payment payment14 = paymentDecorator11.process();
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "hi!" + "'", str6, "hi!");
        org.junit.Assert.assertNotNull(localDateTime7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
        org.junit.Assert.assertNull(payment14);
    }

    @Test
    public void test211() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test211");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.User user3 = cSVRepository0.findUserByEmail("");
        org.example.equipment.Equipment equipment7 = new org.example.equipment.Equipment("", "", "");
        int int8 = equipment7.getAvailableUnits();
        equipment7.setName("");
        int int11 = equipment7.getAvailableUnits();
        cSVRepository0.saveEquipment(equipment7);
        org.example.equipment.Equipment equipment16 = new org.example.equipment.Equipment("", "", "");
        int int17 = equipment16.getAvailableUnits();
        java.lang.String str18 = equipment16.getDescription();
        java.lang.String str19 = equipment16.getEquipmentId();
        cSVRepository0.updateEquipment(equipment16);
        int int21 = equipment16.getAvailableUnits();
        java.lang.String str22 = equipment16.getName();
        org.junit.Assert.assertNotNull(strArrayList1);
// flaky:         org.junit.Assert.assertNotNull(user3);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 1 + "'", int8 == 1);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 1 + "'", int11 == 1);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + 1 + "'", int17 == 1);
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "" + "'", str18, "");
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "" + "'", str19, "");
        org.junit.Assert.assertTrue("'" + int21 + "' != '" + 1 + "'", int21 == 1);
        org.junit.Assert.assertEquals("'" + str22 + "' != '" + "" + "'", str22, "");
    }

    @Test
    public void test212() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test212");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.User user3 = cSVRepository0.findUserByEmail("");
        org.example.equipment.Equipment equipment7 = new org.example.equipment.Equipment("", "", "");
        int int8 = equipment7.getAvailableUnits();
        equipment7.setName("");
        int int11 = equipment7.getAvailableUnits();
        cSVRepository0.saveEquipment(equipment7);
        org.example.equipment.Equipment equipment16 = new org.example.equipment.Equipment("", "", "");
        int int17 = equipment16.getAvailableUnits();
        java.lang.String str18 = equipment16.getDescription();
        java.lang.String str19 = equipment16.getEquipmentId();
        cSVRepository0.updateEquipment(equipment16);
        org.example.data.CSVRepository cSVRepository21 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList22 = cSVRepository21.getAllPaymentRows();
        org.example.equipment.Equipment equipment26 = new org.example.equipment.Equipment("", "", "");
        int int27 = equipment26.getAvailableUnits();
        java.lang.String str28 = equipment26.getDescription();
        equipment26.setAvailableUnits((int) 'a');
        cSVRepository21.updateEquipment(equipment26);
        equipment26.setImagePath("hi!");
        cSVRepository0.saveEquipment(equipment26);
        org.example.equipment.Equipment equipment35 = null;
        // The following exception was thrown during execution in test generation
        try {
            cSVRepository0.updateEquipment(equipment35);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.example.equipment.Equipment.getTags()\" because \"equipment\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArrayList1);
// flaky:         org.junit.Assert.assertNotNull(user3);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 1 + "'", int8 == 1);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 1 + "'", int11 == 1);
        org.junit.Assert.assertTrue("'" + int17 + "' != '" + 1 + "'", int17 == 1);
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "" + "'", str18, "");
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "" + "'", str19, "");
        org.junit.Assert.assertNotNull(strArrayList22);
        org.junit.Assert.assertTrue("'" + int27 + "' != '" + 1 + "'", int27 == 1);
        org.junit.Assert.assertEquals("'" + str28 + "' != '" + "" + "'", str28, "");
    }

    @Test
    public void test213() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test213");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.equipment.Equipment equipment5 = new org.example.equipment.Equipment("", "", "");
        int int6 = equipment5.getAvailableUnits();
        java.lang.String str7 = equipment5.getDescription();
        equipment5.setAvailableUnits((int) 'a');
        cSVRepository0.updateEquipment(equipment5);
        java.lang.String[] strArray12 = cSVRepository0.findEquipmentRowById("STUDENT");
        java.lang.String[] strArray14 = cSVRepository0.findEquipmentRowById("RES-1775354801691");
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
        org.junit.Assert.assertNull(strArray12);
        org.junit.Assert.assertNull(strArray14);
    }

    @Test
    public void test214() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test214");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation1 = null;
        java.time.LocalDateTime localDateTime3 = null;
        org.example.reservation.ReservationAction reservationAction4 = new org.example.reservation.ReservationAction(reservationManager0, reservation1, "", localDateTime3);
        java.util.List<java.lang.String[]> strArrayList5 = reservationManager0.getAllReservationRows();
        org.example.reservation.ReservationManager reservationManager6 = new org.example.reservation.ReservationManager();
        reservationManager6.update("hi!", "");
        org.example.users.Guest guest16 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest16.setIdNumber("");
        java.lang.String str19 = guest16.getDepartmentId();
        org.example.equipment.Equipment equipment23 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus24 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment23.setStatus(equipmentStatus24);
        org.example.payment.Payment payment31 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str32 = payment31.getReservationId();
        java.time.LocalDateTime localDateTime33 = payment31.getTimestamp();
        org.example.payment.Payment payment39 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str40 = payment39.getReservationId();
        java.time.LocalDateTime localDateTime41 = payment39.getTimestamp();
        org.example.reservation.Reservation reservation42 = reservationManager6.createReservation((org.example.users.User) guest16, equipment23, localDateTime33, localDateTime41);
        long long43 = reservation42.getDurationHours();
        org.example.payment.Payment payment46 = reservationManager0.processDeposit(reservation42, "", "FACULTY");
        payment46.setForfeited(true);
        boolean boolean49 = payment46.isForfeited();
        org.junit.Assert.assertNotNull(strArrayList5);
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "hi!" + "'", str19, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus24 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus24.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str32 + "' != '" + "hi!" + "'", str32, "hi!");
        org.junit.Assert.assertNotNull(localDateTime33);
        org.junit.Assert.assertEquals("'" + str40 + "' != '" + "hi!" + "'", str40, "hi!");
        org.junit.Assert.assertNotNull(localDateTime41);
        org.junit.Assert.assertNotNull(reservation42);
        org.junit.Assert.assertTrue("'" + long43 + "' != '" + 0L + "'", long43 == 0L);
        org.junit.Assert.assertNotNull(payment46);
        org.junit.Assert.assertTrue("'" + boolean49 + "' != '" + true + "'", boolean49 == true);
    }

    @Test
    public void test215() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test215");
        org.example.auth.AuthService authService0 = new org.example.auth.AuthService();
        boolean boolean7 = authService0.register("hi!", "", "hi!", "", "", "hi!");
        boolean boolean9 = authService0.isApproved("hi!");
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
    }

    @Test
    public void test216() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test216");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        equipmentManager0.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList4 = equipmentManager0.getAllEquipment();
        equipmentManager0.enableEquipment("hi!");
        org.example.equipment.Equipment equipment8 = equipmentManager0.getEquipmentById("hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList9 = equipmentManager0.getAvailableEquipment();
        org.example.equipment.Equipment equipment11 = equipmentManager0.getEquipmentById("FACULTY");
        equipmentManager0.update("AVAILABLE", "hi!");
        org.junit.Assert.assertNotNull(equipmentList4);
        org.junit.Assert.assertNull(equipment8);
        org.junit.Assert.assertNotNull(equipmentList9);
        org.junit.Assert.assertNull(equipment11);
    }

    @Test
    public void test217() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test217");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        equipmentManager0.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList4 = equipmentManager0.getAllEquipment();
        equipmentManager0.enableEquipment("hi!");
        org.example.equipment.Equipment equipment8 = equipmentManager0.getEquipmentById("hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList9 = equipmentManager0.getAvailableEquipment();
        java.util.List<org.example.equipment.Equipment> equipmentList10 = equipmentManager0.getAllEquipment();
        org.example.reservation.ReservationManager reservationManager11 = new org.example.reservation.ReservationManager();
        reservationManager11.update("hi!", "");
        org.example.users.Guest guest21 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest21.setIdNumber("");
        java.lang.String str24 = guest21.getDepartmentId();
        org.example.equipment.Equipment equipment28 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus29 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment28.setStatus(equipmentStatus29);
        org.example.payment.Payment payment36 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str37 = payment36.getReservationId();
        java.time.LocalDateTime localDateTime38 = payment36.getTimestamp();
        org.example.payment.Payment payment44 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str45 = payment44.getReservationId();
        java.time.LocalDateTime localDateTime46 = payment44.getTimestamp();
        org.example.reservation.Reservation reservation47 = reservationManager11.createReservation((org.example.users.User) guest21, equipment28, localDateTime38, localDateTime46);
        org.example.equipment.EquipmentAction equipmentAction49 = new org.example.equipment.EquipmentAction(equipmentManager0, equipment28, "RES-1775354801691");
        org.example.equipment.Equipment equipment54 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus55 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment54.setStatus(equipmentStatus55);
        org.example.equipment.EquipmentStatus equipmentStatus57 = equipment54.getStatus();
        equipmentManager0.updateEquipmentStatus("", equipmentStatus57);
        org.junit.Assert.assertNotNull(equipmentList4);
        org.junit.Assert.assertNull(equipment8);
        org.junit.Assert.assertNotNull(equipmentList9);
        org.junit.Assert.assertNotNull(equipmentList10);
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "hi!" + "'", str24, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus29 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus29.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str37 + "' != '" + "hi!" + "'", str37, "hi!");
        org.junit.Assert.assertNotNull(localDateTime38);
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "hi!" + "'", str45, "hi!");
        org.junit.Assert.assertNotNull(localDateTime46);
        org.junit.Assert.assertNotNull(reservation47);
        org.junit.Assert.assertTrue("'" + equipmentStatus55 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus55.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertTrue("'" + equipmentStatus57 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus57.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
    }

    @Test
    public void test218() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test218");
        org.example.users.Guest guest6 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest6.setIdNumber("");
        guest6.setUserId("");
        guest6.setEmail("NOT_APPROVED");
    }

    @Test
    public void test219() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test219");
        org.example.users.Guest guest6 = new org.example.users.Guest("NOT_APPROVED", " -  () [AVAILABLE]", "GUEST", "", "STUDENT", "STUDENT");
    }

    @Test
    public void test220() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test220");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.Guest guest8 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str9 = guest8.getUserType();
        cSVRepository0.updateUser((org.example.users.User) guest8);
        org.example.payment.Payment payment16 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime17 = payment16.getTimestamp();
        org.example.payment.PaymentDecorator paymentDecorator20 = new org.example.payment.PaymentDecorator(payment16, "", "hi!");
        cSVRepository0.savePayment(payment16);
        org.example.users.LabManager labManager27 = new org.example.users.LabManager("STUDENT", "FACULTY", "", "", "FACULTY");
        cSVRepository0.updateUser((org.example.users.User) labManager27);
        java.lang.String str29 = labManager27.getUserType();
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "GUEST" + "'", str9, "GUEST");
        org.junit.Assert.assertNotNull(localDateTime17);
        org.junit.Assert.assertEquals("'" + str29 + "' != '" + "MANAGER" + "'", str29, "MANAGER");
    }

    @Test
    public void test221() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test221");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        org.example.users.Guest guest10 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest10.setIdNumber("");
        java.lang.String str13 = guest10.getDepartmentId();
        org.example.equipment.Equipment equipment17 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus18 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment17.setStatus(equipmentStatus18);
        org.example.payment.Payment payment25 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str26 = payment25.getReservationId();
        java.time.LocalDateTime localDateTime27 = payment25.getTimestamp();
        org.example.payment.Payment payment33 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str34 = payment33.getReservationId();
        java.time.LocalDateTime localDateTime35 = payment33.getTimestamp();
        org.example.reservation.Reservation reservation36 = reservationManager0.createReservation((org.example.users.User) guest10, equipment17, localDateTime27, localDateTime35);
        org.example.users.Guest guest44 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str45 = guest44.getIdNumber();
        org.example.equipment.Equipment equipment49 = new org.example.equipment.Equipment("", "", "");
        org.example.payment.Payment payment55 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str56 = payment55.getReservationId();
        java.time.LocalDateTime localDateTime57 = payment55.getTimestamp();
        org.example.payment.Payment payment63 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime64 = payment63.getTimestamp();
        org.example.payment.Payment payment70 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime71 = payment70.getTimestamp();
        payment63.setTimestamp(localDateTime71);
        org.example.reservation.Reservation reservation74 = new org.example.reservation.Reservation("hi!", (org.example.users.User) guest44, equipment49, localDateTime57, localDateTime71, (double) (byte) -1);
        org.example.reservation.ReservationAction reservationAction76 = new org.example.reservation.ReservationAction(reservationManager0, reservation74, "GUEST");
        org.example.equipment.Equipment equipment77 = reservation74.getEquipment();
        java.time.LocalDateTime localDateTime78 = reservation74.getEndTime();
        double double80 = reservation74.calculateTotal((double) 10);
        double double81 = reservation74.getDeposit();
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!" + "'", str13, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus18 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus18.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "hi!" + "'", str34, "hi!");
        org.junit.Assert.assertNotNull(localDateTime35);
        org.junit.Assert.assertNotNull(reservation36);
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "" + "'", str45, "");
        org.junit.Assert.assertEquals("'" + str56 + "' != '" + "hi!" + "'", str56, "hi!");
        org.junit.Assert.assertNotNull(localDateTime57);
        org.junit.Assert.assertNotNull(localDateTime64);
        org.junit.Assert.assertNotNull(localDateTime71);
        org.junit.Assert.assertNotNull(equipment77);
        org.junit.Assert.assertNotNull(localDateTime78);
        org.junit.Assert.assertTrue("'" + double80 + "' != '" + 0.0d + "'", double80 == 0.0d);
        org.junit.Assert.assertTrue("'" + double81 + "' != '" + (-1.0d) + "'", double81 == (-1.0d));
    }

    @Test
    public void test222() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test222");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.equipment.Equipment equipment5 = new org.example.equipment.Equipment("", "", "");
        int int6 = equipment5.getAvailableUnits();
        java.lang.String str7 = equipment5.getDescription();
        equipment5.setAvailableUnits((int) 'a');
        cSVRepository0.updateEquipment(equipment5);
        org.example.equipment.Equipment equipment14 = new org.example.equipment.Equipment("", "", "");
        int int15 = equipment14.getAvailableUnits();
        equipment14.setName("");
        cSVRepository0.saveEquipment(equipment14);
        org.example.reservation.ReservationManager reservationManager19 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation20 = null;
        java.time.LocalDateTime localDateTime22 = null;
        org.example.reservation.ReservationAction reservationAction23 = new org.example.reservation.ReservationAction(reservationManager19, reservation20, "", localDateTime22);
        java.util.List<java.lang.String[]> strArrayList24 = reservationManager19.getAllReservationRows();
        org.example.reservation.ReservationManager reservationManager25 = new org.example.reservation.ReservationManager();
        reservationManager25.update("hi!", "");
        org.example.users.Guest guest35 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest35.setIdNumber("");
        java.lang.String str38 = guest35.getDepartmentId();
        org.example.equipment.Equipment equipment42 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus43 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment42.setStatus(equipmentStatus43);
        org.example.payment.Payment payment50 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str51 = payment50.getReservationId();
        java.time.LocalDateTime localDateTime52 = payment50.getTimestamp();
        org.example.payment.Payment payment58 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str59 = payment58.getReservationId();
        java.time.LocalDateTime localDateTime60 = payment58.getTimestamp();
        org.example.reservation.Reservation reservation61 = reservationManager25.createReservation((org.example.users.User) guest35, equipment42, localDateTime52, localDateTime60);
        long long62 = reservation61.getDurationHours();
        org.example.payment.Payment payment65 = reservationManager19.processDeposit(reservation61, "", "FACULTY");
        org.example.payment.Payment payment71 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime72 = payment71.getTimestamp();
        org.example.payment.Payment payment78 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime79 = payment78.getTimestamp();
        payment71.setTimestamp(localDateTime79);
        boolean boolean81 = reservation61.arrivedOnTime(localDateTime79);
        org.example.users.User user82 = reservation61.getUser();
        cSVRepository0.updateUser(user82);
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 1 + "'", int15 == 1);
        org.junit.Assert.assertNotNull(strArrayList24);
        org.junit.Assert.assertEquals("'" + str38 + "' != '" + "hi!" + "'", str38, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus43 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus43.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str51 + "' != '" + "hi!" + "'", str51, "hi!");
        org.junit.Assert.assertNotNull(localDateTime52);
        org.junit.Assert.assertEquals("'" + str59 + "' != '" + "hi!" + "'", str59, "hi!");
        org.junit.Assert.assertNotNull(localDateTime60);
        org.junit.Assert.assertNotNull(reservation61);
        org.junit.Assert.assertTrue("'" + long62 + "' != '" + 0L + "'", long62 == 0L);
        org.junit.Assert.assertNotNull(payment65);
        org.junit.Assert.assertNotNull(localDateTime72);
        org.junit.Assert.assertNotNull(localDateTime79);
        org.junit.Assert.assertTrue("'" + boolean81 + "' != '" + true + "'", boolean81 == true);
        org.junit.Assert.assertNotNull(user82);
    }

    @Test
    public void test223() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test223");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        equipmentManager0.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList4 = equipmentManager0.getAllEquipment();
        org.example.data.CSVRepository cSVRepository5 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList6 = cSVRepository5.getAllPaymentRows();
        org.example.equipment.Equipment equipment10 = new org.example.equipment.Equipment("", "", "");
        int int11 = equipment10.getAvailableUnits();
        java.lang.String str12 = equipment10.getDescription();
        equipment10.setAvailableUnits((int) 'a');
        cSVRepository5.updateEquipment(equipment10);
        equipment10.setAvailableUnits((int) ' ');
        equipmentManager0.updateEquipment(equipment10);
        equipmentManager0.setMaintenance("");
        org.junit.Assert.assertNotNull(equipmentList4);
        org.junit.Assert.assertNotNull(strArrayList6);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 1 + "'", int11 == 1);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "" + "'", str12, "");
    }

    @Test
    public void test224() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test224");
        org.example.auth.AuthService authService0 = new org.example.auth.AuthService();
        org.example.users.User user3 = authService0.login("STUDENT", "RES-1775354801261");
        org.example.users.User user6 = authService0.login("RESEARCHER", " -  () [AVAILABLE]");
        org.junit.Assert.assertNull(user3);
        org.junit.Assert.assertNull(user6);
    }

    @Test
    public void test225() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test225");
        org.example.users.Guest guest6 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest6.setIdNumber("");
        guest6.setUserId("");
        org.example.users.UserDecorator userDecorator12 = new org.example.users.UserDecorator((org.example.users.User) guest6, "hi!");
        java.lang.String str13 = userDecorator12.getIdNumber();
        java.lang.String str14 = userDecorator12.getDecorationType();
        org.example.users.UserDecorator userDecorator16 = new org.example.users.UserDecorator((org.example.users.User) userDecorator12, "FACULTY");
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "hi!" + "'", str14, "hi!");
    }

    @Test
    public void test226() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test226");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("", "", (double) (short) 100, "FACULTY", true);
        java.lang.String str6 = payment5.getPaymentMethod();
        org.example.payment.Payment payment12 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str13 = payment12.getReservationId();
        java.time.LocalDateTime localDateTime14 = payment12.getTimestamp();
        boolean boolean15 = payment12.isForfeited();
        double double16 = payment12.getAmount();
        boolean boolean17 = payment12.isDeposit();
        java.time.LocalDateTime localDateTime18 = payment12.getTimestamp();
        payment5.setTimestamp(localDateTime18);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "FACULTY" + "'", str6, "FACULTY");
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "hi!" + "'", str13, "hi!");
        org.junit.Assert.assertNotNull(localDateTime14);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + double16 + "' != '" + 10.0d + "'", double16 == 10.0d);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(localDateTime18);
    }

    @Test
    public void test227() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test227");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.Guest guest8 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str9 = guest8.getUserType();
        cSVRepository0.updateUser((org.example.users.User) guest8);
        java.lang.String str11 = guest8.getUserType();
        java.lang.String str12 = guest8.getUserType();
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "GUEST" + "'", str9, "GUEST");
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "GUEST" + "'", str11, "GUEST");
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "GUEST" + "'", str12, "GUEST");
    }

    @Test
    public void test228() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test228");
        org.example.users.Faculty faculty6 = new org.example.users.Faculty("", "", "hi!", "hi!", "hi!", "hi!");
        java.lang.String str7 = faculty6.getUserType();
        java.lang.String str8 = faculty6.getIdNumber();
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "FACULTY" + "'", str7, "FACULTY");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "hi!" + "'", str8, "hi!");
    }

    @Test
    public void test229() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test229");
        org.example.payment.UserPricingStrategy userPricingStrategy1 = new org.example.payment.UserPricingStrategy("");
        double double2 = userPricingStrategy1.calculateDeposit();
        double double3 = userPricingStrategy1.calculateDeposit();
        double double4 = userPricingStrategy1.calculateDeposit();
        org.junit.Assert.assertTrue("'" + double2 + "' != '" + 0.0d + "'", double2 == 0.0d);
        org.junit.Assert.assertTrue("'" + double3 + "' != '" + 0.0d + "'", double3 == 0.0d);
        org.junit.Assert.assertTrue("'" + double4 + "' != '" + 0.0d + "'", double4 == 0.0d);
    }

    @Test
    public void test230() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test230");
        // The following exception was thrown during execution in test generation
        try {
            org.example.users.User user7 = org.example.users.UserFactory.createUser("NOT_APPROVED", "FACULTY", "RES-1775354801691", "RES-1775354801691", "MANAGER", "STUDENT", "STUDENT");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Unknown user type: NOT_APPROVED");
        } catch (java.lang.IllegalArgumentException e) {
            // Expected exception.
        }
    }

    @Test
    public void test231() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test231");
        org.example.auth.AuthService authService0 = new org.example.auth.AuthService();
        org.example.users.User user3 = authService0.login("hi!", "");
        org.example.users.User user6 = authService0.login("RES-1775354801691", " -  () [AVAILABLE]");
        org.junit.Assert.assertNull(user3);
        org.junit.Assert.assertNull(user6);
    }

    @Test
    public void test232() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test232");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.User user3 = cSVRepository0.findUserByEmail("");
        java.util.List<org.example.users.User> userList4 = cSVRepository0.getAllUsers();
        java.util.List<org.example.users.User> userList5 = cSVRepository0.getAllUsers();
        java.lang.String[] strArray7 = cSVRepository0.findEquipmentRowById("MANAGER");
        org.junit.Assert.assertNotNull(strArrayList1);
// flaky:         org.junit.Assert.assertNotNull(user3);
        org.junit.Assert.assertNotNull(userList4);
        org.junit.Assert.assertNotNull(userList5);
// flaky:         org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test233() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test233");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("", "", "");
        int int4 = equipment3.getAvailableUnits();
        equipment3.setProductStatistics("");
        java.lang.String str7 = equipment3.getName();
        java.lang.String str8 = equipment3.getProductStatistics();
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 1 + "'", int4 == 1);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
    }

    @Test
    public void test234() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test234");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("", "", (double) (short) 100, "FACULTY", true);
        java.lang.String str6 = payment5.getPaymentMethod();
        java.lang.String str7 = payment5.getReservationId();
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "FACULTY" + "'", str6, "FACULTY");
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
    }

    @Test
    public void test235() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test235");
        org.example.payment.UserPricingStrategy userPricingStrategy1 = new org.example.payment.UserPricingStrategy("");
        double double2 = userPricingStrategy1.calculateDeposit();
        double double3 = userPricingStrategy1.calculateDeposit();
        double double4 = userPricingStrategy1.getHourlyRate();
        org.junit.Assert.assertTrue("'" + double2 + "' != '" + 0.0d + "'", double2 == 0.0d);
        org.junit.Assert.assertTrue("'" + double3 + "' != '" + 0.0d + "'", double3 == 0.0d);
        org.junit.Assert.assertTrue("'" + double4 + "' != '" + 0.0d + "'", double4 == 0.0d);
    }

    @Test
    public void test236() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test236");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.User user3 = cSVRepository0.findUserByEmail("");
        org.example.equipment.Equipment equipment7 = new org.example.equipment.Equipment("", "", "");
        int int8 = equipment7.getAvailableUnits();
        equipment7.setName("");
        int int11 = equipment7.getAvailableUnits();
        cSVRepository0.saveEquipment(equipment7);
        org.example.equipment.Equipment equipment16 = new org.example.equipment.Equipment("", "", "");
        cSVRepository0.updateEquipment(equipment16);
        org.example.payment.Payment payment23 = new org.example.payment.Payment("GUEST", "GUEST", 30.0d, "hi!", false);
        cSVRepository0.savePayment(payment23);
        java.lang.String[] strArray26 = cSVRepository0.findEquipmentRowById("hi!");
        org.example.users.Student student33 = new org.example.users.Student("", "GUEST", "hi!", "hi!", "FACULTY", "");
        cSVRepository0.saveUser((org.example.users.User) student33);
        java.lang.String str35 = student33.getUserType();
        org.junit.Assert.assertNotNull(strArrayList1);
// flaky:         org.junit.Assert.assertNotNull(user3);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 1 + "'", int8 == 1);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 1 + "'", int11 == 1);
        org.junit.Assert.assertNull(strArray26);
        org.junit.Assert.assertEquals("'" + str35 + "' != '" + "STUDENT" + "'", str35, "STUDENT");
    }

    @Test
    public void test237() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test237");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        equipmentManager0.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList4 = equipmentManager0.getAllEquipment();
        equipmentManager0.enableEquipment("hi!");
        equipmentManager0.disableEquipment("");
        org.example.equipment.Equipment equipment12 = new org.example.equipment.Equipment("", "", "");
        int int13 = equipment12.getAvailableUnits();
        org.example.equipment.EquipmentAction equipmentAction15 = new org.example.equipment.EquipmentAction(equipmentManager0, equipment12, "RES-1775354801691");
        org.example.equipment.EquipmentStatus equipmentStatus16 = equipment12.getStatus();
        java.lang.Class<?> wildcardClass17 = equipment12.getClass();
        org.junit.Assert.assertNotNull(equipmentList4);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 1 + "'", int13 == 1);
        org.junit.Assert.assertTrue("'" + equipmentStatus16 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus16.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertNotNull(wildcardClass17);
    }

    @Test
    public void test238() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test238");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        equipmentManager0.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList4 = equipmentManager0.getAllEquipment();
        equipmentManager0.enableEquipment("hi!");
        org.example.equipment.Equipment equipment8 = equipmentManager0.getEquipmentById("hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList9 = equipmentManager0.getAvailableEquipment();
        java.util.List<org.example.equipment.Equipment> equipmentList10 = equipmentManager0.getAllEquipment();
        java.util.List<org.example.equipment.Equipment> equipmentList11 = equipmentManager0.getAllEquipment();
        java.util.List<org.example.equipment.Equipment> equipmentList12 = equipmentManager0.getAllEquipment();
        org.junit.Assert.assertNotNull(equipmentList4);
        org.junit.Assert.assertNull(equipment8);
        org.junit.Assert.assertNotNull(equipmentList9);
        org.junit.Assert.assertNotNull(equipmentList10);
        org.junit.Assert.assertNotNull(equipmentList11);
        org.junit.Assert.assertNotNull(equipmentList12);
    }

    @Test
    public void test239() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test239");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.Student student8 = new org.example.users.Student("", "GUEST", "hi!", "hi!", "FACULTY", "");
        cSVRepository0.saveUser((org.example.users.User) student8);
        java.util.List<java.lang.String[]> strArrayList11 = cSVRepository0.getPaymentRowsByReservationId("NOT_APPROVED");
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertNotNull(strArrayList11);
    }

    @Test
    public void test240() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test240");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        equipmentManager0.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList4 = equipmentManager0.getAllEquipment();
        equipmentManager0.enableEquipment("hi!");
        org.example.equipment.Equipment equipment8 = equipmentManager0.getEquipmentById("hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList9 = equipmentManager0.getAvailableEquipment();
        java.util.List<org.example.equipment.Equipment> equipmentList10 = equipmentManager0.getAllEquipment();
        org.example.equipment.Equipment equipment11 = null;
        org.example.equipment.EquipmentAction equipmentAction13 = new org.example.equipment.EquipmentAction(equipmentManager0, equipment11, "GUEST");
        // The following exception was thrown during execution in test generation
        try {
            equipmentAction13.execute();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.example.equipment.Equipment.getStatus()\" because \"this.equipment\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(equipmentList4);
        org.junit.Assert.assertNull(equipment8);
        org.junit.Assert.assertNotNull(equipmentList9);
        org.junit.Assert.assertNotNull(equipmentList10);
    }

    @Test
    public void test241() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test241");
        org.example.payment.Payment payment0 = null;
        org.example.payment.PaymentDecorator paymentDecorator3 = new org.example.payment.PaymentDecorator(payment0, "GUEST", "hi!");
        org.example.payment.Payment payment4 = paymentDecorator3.process();
        org.junit.Assert.assertNull(payment4);
    }

    @Test
    public void test242() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test242");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        java.lang.String[] strArray3 = cSVRepository0.findEquipmentRowById("RES-1775354801261");
        cSVRepository0.deleteUser("hi!");
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertNull(strArray3);
    }

    @Test
    public void test243() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test243");
        org.example.users.Guest guest6 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest6.setIdNumber("");
        guest6.setPassword("");
        org.example.users.UserDecorator userDecorator12 = new org.example.users.UserDecorator((org.example.users.User) guest6, "");
        java.lang.String str13 = userDecorator12.getUserType();
        java.lang.String str14 = userDecorator12.getPassword();
        java.lang.String str15 = userDecorator12.getApprovalStatus();
        java.lang.String str16 = userDecorator12.getEmail();
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "GUEST" + "'", str13, "GUEST");
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "" + "'", str14, "");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "NOT_APPROVED" + "'", str15, "NOT_APPROVED");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "" + "'", str16, "");
    }

    @Test
    public void test244() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test244");
        org.example.auth.AuthService authService0 = new org.example.auth.AuthService();
        boolean boolean7 = authService0.register("hi!", "", "hi!", "", "", "hi!");
        org.example.users.User user10 = authService0.login("GUEST", "hi!");
        org.example.users.User user13 = authService0.login("GUEST", "FACULTY");
        boolean boolean15 = authService0.isApproved("RESEARCHER");
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNull(user10);
        org.junit.Assert.assertNull(user13);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + true + "'", boolean15 == true);
    }

    @Test
    public void test245() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test245");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation1 = null;
        java.time.LocalDateTime localDateTime3 = null;
        org.example.reservation.ReservationAction reservationAction4 = new org.example.reservation.ReservationAction(reservationManager0, reservation1, "", localDateTime3);
        java.util.List<java.lang.String[]> strArrayList5 = reservationManager0.getAllReservationRows();
        org.example.reservation.ReservationManager reservationManager6 = new org.example.reservation.ReservationManager();
        reservationManager6.update("hi!", "");
        org.example.users.Guest guest16 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest16.setIdNumber("");
        java.lang.String str19 = guest16.getDepartmentId();
        org.example.equipment.Equipment equipment23 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus24 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment23.setStatus(equipmentStatus24);
        org.example.payment.Payment payment31 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str32 = payment31.getReservationId();
        java.time.LocalDateTime localDateTime33 = payment31.getTimestamp();
        org.example.payment.Payment payment39 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str40 = payment39.getReservationId();
        java.time.LocalDateTime localDateTime41 = payment39.getTimestamp();
        org.example.reservation.Reservation reservation42 = reservationManager6.createReservation((org.example.users.User) guest16, equipment23, localDateTime33, localDateTime41);
        long long43 = reservation42.getDurationHours();
        org.example.payment.Payment payment46 = reservationManager0.processDeposit(reservation42, "", "FACULTY");
        org.example.payment.Payment payment52 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime53 = payment52.getTimestamp();
        org.example.payment.Payment payment59 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime60 = payment59.getTimestamp();
        payment52.setTimestamp(localDateTime60);
        boolean boolean62 = reservation42.arrivedOnTime(localDateTime60);
        org.example.users.User user63 = reservation42.getUser();
        java.time.LocalDateTime localDateTime64 = reservation42.getStartTime();
        org.junit.Assert.assertNotNull(strArrayList5);
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "hi!" + "'", str19, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus24 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus24.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str32 + "' != '" + "hi!" + "'", str32, "hi!");
        org.junit.Assert.assertNotNull(localDateTime33);
        org.junit.Assert.assertEquals("'" + str40 + "' != '" + "hi!" + "'", str40, "hi!");
        org.junit.Assert.assertNotNull(localDateTime41);
        org.junit.Assert.assertNotNull(reservation42);
        org.junit.Assert.assertTrue("'" + long43 + "' != '" + 0L + "'", long43 == 0L);
        org.junit.Assert.assertNotNull(payment46);
        org.junit.Assert.assertNotNull(localDateTime53);
        org.junit.Assert.assertNotNull(localDateTime60);
        org.junit.Assert.assertTrue("'" + boolean62 + "' != '" + true + "'", boolean62 == true);
        org.junit.Assert.assertNotNull(user63);
        org.junit.Assert.assertNotNull(localDateTime64);
    }

    @Test
    public void test246() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test246");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation1 = null;
        java.time.LocalDateTime localDateTime3 = null;
        org.example.reservation.ReservationAction reservationAction4 = new org.example.reservation.ReservationAction(reservationManager0, reservation1, "", localDateTime3);
        org.example.reservation.ReservationManager reservationManager5 = new org.example.reservation.ReservationManager();
        reservationManager5.update("hi!", "");
        org.example.users.Guest guest15 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest15.setIdNumber("");
        java.lang.String str18 = guest15.getDepartmentId();
        org.example.equipment.Equipment equipment22 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus23 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment22.setStatus(equipmentStatus23);
        org.example.payment.Payment payment30 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str31 = payment30.getReservationId();
        java.time.LocalDateTime localDateTime32 = payment30.getTimestamp();
        org.example.payment.Payment payment38 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str39 = payment38.getReservationId();
        java.time.LocalDateTime localDateTime40 = payment38.getTimestamp();
        org.example.reservation.Reservation reservation41 = reservationManager5.createReservation((org.example.users.User) guest15, equipment22, localDateTime32, localDateTime40);
        org.example.reservation.ReservationManager reservationManager42 = new org.example.reservation.ReservationManager();
        reservationManager42.update("hi!", "");
        org.example.users.Guest guest52 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest52.setIdNumber("");
        java.lang.String str55 = guest52.getDepartmentId();
        org.example.equipment.Equipment equipment59 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus60 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment59.setStatus(equipmentStatus60);
        org.example.payment.Payment payment67 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str68 = payment67.getReservationId();
        java.time.LocalDateTime localDateTime69 = payment67.getTimestamp();
        org.example.payment.Payment payment75 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str76 = payment75.getReservationId();
        java.time.LocalDateTime localDateTime77 = payment75.getTimestamp();
        org.example.reservation.Reservation reservation78 = reservationManager42.createReservation((org.example.users.User) guest52, equipment59, localDateTime69, localDateTime77);
        reservationManager5.updateReservation(reservation78);
        org.example.payment.Payment payment83 = reservationManager0.processFinalPayment(reservation78, "GUEST", "FACULTY", false);
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "hi!" + "'", str18, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus23 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus23.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str31 + "' != '" + "hi!" + "'", str31, "hi!");
        org.junit.Assert.assertNotNull(localDateTime32);
        org.junit.Assert.assertEquals("'" + str39 + "' != '" + "hi!" + "'", str39, "hi!");
        org.junit.Assert.assertNotNull(localDateTime40);
        org.junit.Assert.assertNotNull(reservation41);
        org.junit.Assert.assertEquals("'" + str55 + "' != '" + "hi!" + "'", str55, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus60 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus60.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str68 + "' != '" + "hi!" + "'", str68, "hi!");
        org.junit.Assert.assertNotNull(localDateTime69);
        org.junit.Assert.assertEquals("'" + str76 + "' != '" + "hi!" + "'", str76, "hi!");
        org.junit.Assert.assertNotNull(localDateTime77);
        org.junit.Assert.assertNotNull(reservation78);
        org.junit.Assert.assertNotNull(payment83);
    }

    @Test
    public void test247() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test247");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        equipmentManager0.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList4 = equipmentManager0.getAllEquipment();
        equipmentManager0.enableEquipment("hi!");
        equipmentManager0.disableEquipment("GUEST");
        equipmentManager0.update("", "RESEARCHER");
        equipmentManager0.enableEquipment("GUEST");
        org.junit.Assert.assertNotNull(equipmentList4);
    }

    @Test
    public void test248() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test248");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation1 = null;
        java.time.LocalDateTime localDateTime3 = null;
        org.example.reservation.ReservationAction reservationAction4 = new org.example.reservation.ReservationAction(reservationManager0, reservation1, "", localDateTime3);
        org.example.reservation.ReservationManager reservationManager5 = new org.example.reservation.ReservationManager();
        reservationManager5.update("hi!", "");
        org.example.users.Guest guest15 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest15.setIdNumber("");
        java.lang.String str18 = guest15.getDepartmentId();
        org.example.equipment.Equipment equipment22 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus23 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment22.setStatus(equipmentStatus23);
        org.example.payment.Payment payment30 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str31 = payment30.getReservationId();
        java.time.LocalDateTime localDateTime32 = payment30.getTimestamp();
        org.example.payment.Payment payment38 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str39 = payment38.getReservationId();
        java.time.LocalDateTime localDateTime40 = payment38.getTimestamp();
        org.example.reservation.Reservation reservation41 = reservationManager5.createReservation((org.example.users.User) guest15, equipment22, localDateTime32, localDateTime40);
        org.example.users.Guest guest49 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str50 = guest49.getIdNumber();
        org.example.equipment.Equipment equipment54 = new org.example.equipment.Equipment("", "", "");
        org.example.payment.Payment payment60 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str61 = payment60.getReservationId();
        java.time.LocalDateTime localDateTime62 = payment60.getTimestamp();
        org.example.payment.Payment payment68 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime69 = payment68.getTimestamp();
        org.example.payment.Payment payment75 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime76 = payment75.getTimestamp();
        payment68.setTimestamp(localDateTime76);
        org.example.reservation.Reservation reservation79 = new org.example.reservation.Reservation("hi!", (org.example.users.User) guest49, equipment54, localDateTime62, localDateTime76, (double) (byte) -1);
        org.example.reservation.ReservationAction reservationAction81 = new org.example.reservation.ReservationAction(reservationManager5, reservation79, "GUEST");
        org.example.reservation.ReservationStatus reservationStatus82 = org.example.reservation.ReservationStatus.CANCELLED;
        reservation79.setStatus(reservationStatus82);
        java.time.LocalDateTime localDateTime84 = reservation79.getStartTime();
        org.example.users.User user85 = reservation79.getUser();
        java.time.LocalDateTime localDateTime87 = null;
        org.example.reservation.ReservationAction reservationAction88 = new org.example.reservation.ReservationAction(reservationManager0, reservation79, "", localDateTime87);
        org.example.reservation.Reservation reservation90 = reservationManager0.getReservationById("GUEST");
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "hi!" + "'", str18, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus23 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus23.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str31 + "' != '" + "hi!" + "'", str31, "hi!");
        org.junit.Assert.assertNotNull(localDateTime32);
        org.junit.Assert.assertEquals("'" + str39 + "' != '" + "hi!" + "'", str39, "hi!");
        org.junit.Assert.assertNotNull(localDateTime40);
        org.junit.Assert.assertNotNull(reservation41);
        org.junit.Assert.assertEquals("'" + str50 + "' != '" + "" + "'", str50, "");
        org.junit.Assert.assertEquals("'" + str61 + "' != '" + "hi!" + "'", str61, "hi!");
        org.junit.Assert.assertNotNull(localDateTime62);
        org.junit.Assert.assertNotNull(localDateTime69);
        org.junit.Assert.assertNotNull(localDateTime76);
        org.junit.Assert.assertTrue("'" + reservationStatus82 + "' != '" + org.example.reservation.ReservationStatus.CANCELLED + "'", reservationStatus82.equals(org.example.reservation.ReservationStatus.CANCELLED));
        org.junit.Assert.assertNotNull(localDateTime84);
        org.junit.Assert.assertNotNull(user85);
        org.junit.Assert.assertNull(reservation90);
    }

    @Test
    public void test249() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test249");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        equipmentManager0.update("hi!", "hi!");
        org.example.equipment.Equipment equipment7 = new org.example.equipment.Equipment("", "", "");
        int int8 = equipment7.getAvailableUnits();
        java.lang.String str9 = equipment7.getDescription();
        equipment7.setAvailableUnits((int) 'a');
        equipmentManager0.updateEquipment(equipment7);
        equipmentManager0.disableEquipment("RES-1775354801261");
        equipmentManager0.setMaintenance("FACULTY");
        org.example.equipment.EquipmentManager equipmentManager17 = new org.example.equipment.EquipmentManager();
        equipmentManager17.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList21 = equipmentManager17.getAllEquipment();
        org.example.data.CSVRepository cSVRepository22 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList23 = cSVRepository22.getAllPaymentRows();
        org.example.equipment.Equipment equipment27 = new org.example.equipment.Equipment("", "", "");
        int int28 = equipment27.getAvailableUnits();
        java.lang.String str29 = equipment27.getDescription();
        equipment27.setAvailableUnits((int) 'a');
        cSVRepository22.updateEquipment(equipment27);
        equipment27.setAvailableUnits((int) ' ');
        equipmentManager17.updateEquipment(equipment27);
        equipmentManager0.addEquipment(equipment27);
        org.example.equipment.Equipment equipment40 = new org.example.equipment.Equipment("", "", "");
        int int41 = equipment40.getAvailableUnits();
        equipment40.setName("");
        int int44 = equipment40.getAvailableUnits();
        equipmentManager0.updateEquipment(equipment40);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 1 + "'", int8 == 1);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
        org.junit.Assert.assertNotNull(equipmentList21);
        org.junit.Assert.assertNotNull(strArrayList23);
        org.junit.Assert.assertTrue("'" + int28 + "' != '" + 1 + "'", int28 == 1);
        org.junit.Assert.assertEquals("'" + str29 + "' != '" + "" + "'", str29, "");
        org.junit.Assert.assertTrue("'" + int41 + "' != '" + 1 + "'", int41 == 1);
        org.junit.Assert.assertTrue("'" + int44 + "' != '" + 1 + "'", int44 == 1);
    }

    @Test
    public void test250() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test250");
        org.example.equipment.EquipmentManager equipmentManager0 = new org.example.equipment.EquipmentManager();
        equipmentManager0.update("hi!", "hi!");
        java.util.List<org.example.equipment.Equipment> equipmentList4 = equipmentManager0.getAllEquipment();
        equipmentManager0.setMaintenance("GUEST");
        java.util.List<org.example.equipment.Equipment> equipmentList7 = equipmentManager0.getAvailableEquipment();
        org.junit.Assert.assertNotNull(equipmentList4);
        org.junit.Assert.assertNotNull(equipmentList7);
    }

    @Test
    public void test251() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test251");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.User user3 = cSVRepository0.findUserByEmail("");
        org.example.equipment.Equipment equipment7 = new org.example.equipment.Equipment("", "", "");
        int int8 = equipment7.getAvailableUnits();
        equipment7.setName("");
        int int11 = equipment7.getAvailableUnits();
        cSVRepository0.saveEquipment(equipment7);
        org.example.equipment.Equipment equipment16 = new org.example.equipment.Equipment("", "", "");
        cSVRepository0.updateEquipment(equipment16);
        java.util.List<java.lang.String[]> strArrayList19 = cSVRepository0.getReservationRowsByUserId("");
        org.example.payment.Payment payment25 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str26 = payment25.getReservationId();
        java.time.LocalDateTime localDateTime27 = payment25.getTimestamp();
        boolean boolean28 = payment25.isForfeited();
        double double29 = payment25.getAmount();
        java.time.LocalDateTime localDateTime30 = payment25.getTimestamp();
        cSVRepository0.savePayment(payment25);
        java.util.List<java.lang.String[]> strArrayList32 = cSVRepository0.getAllPaymentRows();
        org.junit.Assert.assertNotNull(strArrayList1);
// flaky:         org.junit.Assert.assertNotNull(user3);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 1 + "'", int8 == 1);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 1 + "'", int11 == 1);
        org.junit.Assert.assertNotNull(strArrayList19);
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
        org.junit.Assert.assertNotNull(localDateTime27);
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + false + "'", boolean28 == false);
        org.junit.Assert.assertTrue("'" + double29 + "' != '" + 10.0d + "'", double29 == 10.0d);
        org.junit.Assert.assertNotNull(localDateTime30);
        org.junit.Assert.assertNotNull(strArrayList32);
    }

    @Test
    public void test252() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test252");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.User user3 = cSVRepository0.findUserByEmail("");
        org.example.equipment.Equipment equipment7 = new org.example.equipment.Equipment("", "", "");
        int int8 = equipment7.getAvailableUnits();
        equipment7.setName("");
        int int11 = equipment7.getAvailableUnits();
        cSVRepository0.saveEquipment(equipment7);
        org.example.equipment.Equipment equipment16 = new org.example.equipment.Equipment("", "", "");
        cSVRepository0.updateEquipment(equipment16);
        org.example.payment.Payment payment23 = new org.example.payment.Payment("GUEST", "GUEST", 30.0d, "hi!", false);
        cSVRepository0.savePayment(payment23);
        java.util.List<java.lang.String[]> strArrayList26 = cSVRepository0.getReservationRowsByUserId("GUEST");
        org.example.reservation.ReservationManager reservationManager27 = new org.example.reservation.ReservationManager();
        reservationManager27.update("hi!", "");
        org.example.users.Guest guest37 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest37.setIdNumber("");
        java.lang.String str40 = guest37.getDepartmentId();
        org.example.equipment.Equipment equipment44 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus45 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment44.setStatus(equipmentStatus45);
        org.example.payment.Payment payment52 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str53 = payment52.getReservationId();
        java.time.LocalDateTime localDateTime54 = payment52.getTimestamp();
        org.example.payment.Payment payment60 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str61 = payment60.getReservationId();
        java.time.LocalDateTime localDateTime62 = payment60.getTimestamp();
        org.example.reservation.Reservation reservation63 = reservationManager27.createReservation((org.example.users.User) guest37, equipment44, localDateTime54, localDateTime62);
        org.example.payment.Payment payment69 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime70 = payment69.getTimestamp();
        reservation63.setEndTime(localDateTime70);
        org.example.equipment.Equipment equipment72 = reservation63.getEquipment();
        cSVRepository0.updateReservation(reservation63);
        org.junit.Assert.assertNotNull(strArrayList1);
// flaky:         org.junit.Assert.assertNotNull(user3);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 1 + "'", int8 == 1);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 1 + "'", int11 == 1);
        org.junit.Assert.assertNotNull(strArrayList26);
        org.junit.Assert.assertEquals("'" + str40 + "' != '" + "hi!" + "'", str40, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus45 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus45.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str53 + "' != '" + "hi!" + "'", str53, "hi!");
        org.junit.Assert.assertNotNull(localDateTime54);
        org.junit.Assert.assertEquals("'" + str61 + "' != '" + "hi!" + "'", str61, "hi!");
        org.junit.Assert.assertNotNull(localDateTime62);
        org.junit.Assert.assertNotNull(reservation63);
        org.junit.Assert.assertNotNull(localDateTime70);
        org.junit.Assert.assertNotNull(equipment72);
    }

    @Test
    public void test253() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test253");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        java.util.List<java.lang.String[]> strArrayList1 = reservationManager0.getAllReservationRows();
        org.example.reservation.Reservation reservation3 = reservationManager0.getReservationById(" -  () [AVAILABLE]");
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertNull(reservation3);
    }

    @Test
    public void test254() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test254");
        org.example.users.Guest guest6 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest6.setIdNumber("");
        guest6.setUserId("");
        org.example.users.UserDecorator userDecorator12 = new org.example.users.UserDecorator((org.example.users.User) guest6, "hi!");
        java.lang.String str13 = userDecorator12.getIdNumber();
        userDecorator12.setPassword("NOT_APPROVED");
        org.example.users.UserDecorator userDecorator17 = new org.example.users.UserDecorator((org.example.users.User) userDecorator12, "AVAILABLE");
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
    }

    @Test
    public void test255() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test255");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList1 = cSVRepository0.getAllPaymentRows();
        org.example.users.Guest guest8 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str9 = guest8.getUserType();
        cSVRepository0.updateUser((org.example.users.User) guest8);
        org.example.payment.Payment payment16 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime17 = payment16.getTimestamp();
        org.example.payment.PaymentDecorator paymentDecorator20 = new org.example.payment.PaymentDecorator(payment16, "", "hi!");
        cSVRepository0.savePayment(payment16);
        boolean boolean22 = payment16.isForfeited();
        payment16.setForfeited(true);
        org.junit.Assert.assertNotNull(strArrayList1);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "GUEST" + "'", str9, "GUEST");
        org.junit.Assert.assertNotNull(localDateTime17);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + false + "'", boolean22 == false);
    }

    @Test
    public void test256() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test256");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation1 = null;
        java.time.LocalDateTime localDateTime3 = null;
        org.example.reservation.ReservationAction reservationAction4 = new org.example.reservation.ReservationAction(reservationManager0, reservation1, "", localDateTime3);
        org.example.reservation.ReservationManager reservationManager6 = new org.example.reservation.ReservationManager();
        reservationManager6.update("hi!", "");
        org.example.users.Guest guest16 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest16.setIdNumber("");
        java.lang.String str19 = guest16.getDepartmentId();
        org.example.equipment.Equipment equipment23 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus24 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment23.setStatus(equipmentStatus24);
        org.example.payment.Payment payment31 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str32 = payment31.getReservationId();
        java.time.LocalDateTime localDateTime33 = payment31.getTimestamp();
        org.example.payment.Payment payment39 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str40 = payment39.getReservationId();
        java.time.LocalDateTime localDateTime41 = payment39.getTimestamp();
        org.example.reservation.Reservation reservation42 = reservationManager6.createReservation((org.example.users.User) guest16, equipment23, localDateTime33, localDateTime41);
        org.example.users.Guest guest50 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str51 = guest50.getIdNumber();
        org.example.equipment.Equipment equipment55 = new org.example.equipment.Equipment("", "", "");
        org.example.payment.Payment payment61 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str62 = payment61.getReservationId();
        java.time.LocalDateTime localDateTime63 = payment61.getTimestamp();
        org.example.payment.Payment payment69 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime70 = payment69.getTimestamp();
        org.example.payment.Payment payment76 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime77 = payment76.getTimestamp();
        payment69.setTimestamp(localDateTime77);
        org.example.reservation.Reservation reservation80 = new org.example.reservation.Reservation("hi!", (org.example.users.User) guest50, equipment55, localDateTime63, localDateTime77, (double) (byte) -1);
        org.example.reservation.ReservationAction reservationAction82 = new org.example.reservation.ReservationAction(reservationManager6, reservation80, "GUEST");
        org.example.equipment.Equipment equipment83 = reservation80.getEquipment();
        java.time.LocalDateTime localDateTime84 = reservation80.getEndTime();
        reservationManager0.modifyReservation("", localDateTime84);
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "hi!" + "'", str19, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus24 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus24.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str32 + "' != '" + "hi!" + "'", str32, "hi!");
        org.junit.Assert.assertNotNull(localDateTime33);
        org.junit.Assert.assertEquals("'" + str40 + "' != '" + "hi!" + "'", str40, "hi!");
        org.junit.Assert.assertNotNull(localDateTime41);
        org.junit.Assert.assertNotNull(reservation42);
        org.junit.Assert.assertEquals("'" + str51 + "' != '" + "" + "'", str51, "");
        org.junit.Assert.assertEquals("'" + str62 + "' != '" + "hi!" + "'", str62, "hi!");
        org.junit.Assert.assertNotNull(localDateTime63);
        org.junit.Assert.assertNotNull(localDateTime70);
        org.junit.Assert.assertNotNull(localDateTime77);
        org.junit.Assert.assertNotNull(equipment83);
        org.junit.Assert.assertNotNull(localDateTime84);
    }

    @Test
    public void test257() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test257");
        org.example.sensors.EquipmentSensor equipmentSensor1 = new org.example.sensors.EquipmentSensor("RESEARCHER");
        org.example.reservation.ReservationManager reservationManager2 = new org.example.reservation.ReservationManager();
        org.example.reservation.Reservation reservation3 = null;
        java.time.LocalDateTime localDateTime5 = null;
        org.example.reservation.ReservationAction reservationAction6 = new org.example.reservation.ReservationAction(reservationManager2, reservation3, "", localDateTime5);
        equipmentSensor1.removeObserver((org.example.sensors.SensorObserver) reservationManager2);
        java.lang.String str8 = equipmentSensor1.getCurrentStatus();
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "AVAILABLE" + "'", str8, "AVAILABLE");
    }

    @Test
    public void test258() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test258");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("", "", "");
        int int4 = equipment3.getAvailableUnits();
        equipment3.setName("");
        int int7 = equipment3.getAvailableUnits();
        equipment3.setImagePath("AVAILABLE");
        int int10 = equipment3.getAvailableUnits();
        equipment3.setProductStatistics("RES-1775354801691");
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 1 + "'", int4 == 1);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 1 + "'", int7 == 1);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 1 + "'", int10 == 1);
    }

    @Test
    public void test259() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test259");
        org.example.reservation.ReservationManager reservationManager0 = new org.example.reservation.ReservationManager();
        reservationManager0.update("hi!", "");
        org.example.reservation.ReservationManager reservationManager4 = new org.example.reservation.ReservationManager();
        reservationManager4.update("hi!", "");
        org.example.users.Guest guest14 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        guest14.setIdNumber("");
        java.lang.String str17 = guest14.getDepartmentId();
        org.example.equipment.Equipment equipment21 = new org.example.equipment.Equipment("", "", "");
        org.example.equipment.EquipmentStatus equipmentStatus22 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment21.setStatus(equipmentStatus22);
        org.example.payment.Payment payment29 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str30 = payment29.getReservationId();
        java.time.LocalDateTime localDateTime31 = payment29.getTimestamp();
        org.example.payment.Payment payment37 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str38 = payment37.getReservationId();
        java.time.LocalDateTime localDateTime39 = payment37.getTimestamp();
        org.example.reservation.Reservation reservation40 = reservationManager4.createReservation((org.example.users.User) guest14, equipment21, localDateTime31, localDateTime39);
        org.example.users.Guest guest48 = new org.example.users.Guest("", "", "", "hi!", "hi!", "");
        java.lang.String str49 = guest48.getIdNumber();
        org.example.equipment.Equipment equipment53 = new org.example.equipment.Equipment("", "", "");
        org.example.payment.Payment payment59 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.lang.String str60 = payment59.getReservationId();
        java.time.LocalDateTime localDateTime61 = payment59.getTimestamp();
        org.example.payment.Payment payment67 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime68 = payment67.getTimestamp();
        org.example.payment.Payment payment74 = new org.example.payment.Payment("", "hi!", (double) 10, "hi!", false);
        java.time.LocalDateTime localDateTime75 = payment74.getTimestamp();
        payment67.setTimestamp(localDateTime75);
        org.example.reservation.Reservation reservation78 = new org.example.reservation.Reservation("hi!", (org.example.users.User) guest48, equipment53, localDateTime61, localDateTime75, (double) (byte) -1);
        org.example.reservation.ReservationAction reservationAction80 = new org.example.reservation.ReservationAction(reservationManager4, reservation78, "GUEST");
        org.example.equipment.Equipment equipment81 = reservation78.getEquipment();
        org.example.users.User user82 = reservation78.getUser();
        org.example.reservation.ReservationStatus reservationStatus83 = reservation78.getStatus();
        org.example.payment.Payment payment87 = reservationManager0.processFinalPayment(reservation78, " -  () [AVAILABLE]", "RESEARCHER", false);
        reservationManager0.update("", "AVAILABLE");
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "hi!" + "'", str17, "hi!");
        org.junit.Assert.assertTrue("'" + equipmentStatus22 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'", equipmentStatus22.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str30 + "' != '" + "hi!" + "'", str30, "hi!");
        org.junit.Assert.assertNotNull(localDateTime31);
        org.junit.Assert.assertEquals("'" + str38 + "' != '" + "hi!" + "'", str38, "hi!");
        org.junit.Assert.assertNotNull(localDateTime39);
        org.junit.Assert.assertNotNull(reservation40);
        org.junit.Assert.assertEquals("'" + str49 + "' != '" + "" + "'", str49, "");
        org.junit.Assert.assertEquals("'" + str60 + "' != '" + "hi!" + "'", str60, "hi!");
        org.junit.Assert.assertNotNull(localDateTime61);
        org.junit.Assert.assertNotNull(localDateTime68);
        org.junit.Assert.assertNotNull(localDateTime75);
        org.junit.Assert.assertNotNull(equipment81);
        org.junit.Assert.assertNotNull(user82);
        org.junit.Assert.assertTrue("'" + reservationStatus83 + "' != '" + org.example.reservation.ReservationStatus.PENDING + "'", reservationStatus83.equals(org.example.reservation.ReservationStatus.PENDING));
        org.junit.Assert.assertNotNull(payment87);
    }

    @Test
    public void test260() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test260");
        org.example.payment.Payment payment0 = null;
        org.example.payment.PaymentDecorator paymentDecorator3 = new org.example.payment.PaymentDecorator(payment0, "GUEST", "hi!");
        java.lang.String str4 = paymentDecorator3.getPaymentMethod();
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "GUEST" + "'", str4, "GUEST");
    }
}
