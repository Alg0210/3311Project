package org.example.randoop;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RandoopRegressionTest0 {

    public static boolean debug = false;

    public void assertBooleanArrayEquals(boolean[] expectedArray, boolean[] actualArray) {
        if (expectedArray.length != actualArray.length) {
            throw new AssertionError("Array lengths differ: " + expectedArray.length + " != " + actualArray.length);
        }
        for (int i = 0; i < expectedArray.length; i++) {
            if (expectedArray[i] != actualArray[i]) {
                throw new AssertionError(
                        "Arrays differ at index " + i + ": " + expectedArray[i] + " != " + actualArray[i]);
            }
        }
    }

    @Test
    public void test001() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test001");
        org.example.auth.AuthService.logout();
    }

    @Test
    public void test002() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test002");
        org.example.users.User user0 = org.example.auth.AuthService.getCurrentUser();
        org.junit.Assert.assertNull(user0);
    }

    @Test
    public void test003() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test003");
        org.example.reservation.ReservationStatus reservationStatus0 = org.example.reservation.ReservationStatus.EXTENDED;
        org.junit.Assert.assertTrue(
                "'" + reservationStatus0 + "' != '" + org.example.reservation.ReservationStatus.EXTENDED + "'",
                reservationStatus0.equals(org.example.reservation.ReservationStatus.EXTENDED));
    }

    @Test
    public void test004() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test004");
        org.example.equipment.EquipmentStatus equipmentStatus0 = org.example.equipment.EquipmentStatus.IN_USE;
        org.junit.Assert.assertTrue(
                "'" + equipmentStatus0 + "' != '" + org.example.equipment.EquipmentStatus.IN_USE + "'",
                equipmentStatus0.equals(org.example.equipment.EquipmentStatus.IN_USE));
    }

    @Test
    public void test005() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test005");
        boolean boolean1 = org.example.auth.PasswordValidator.isValid("hi!");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test006() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test006");
        boolean boolean1 = org.example.auth.PasswordValidator.isValid("");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test007() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test007");
        org.example.users.LabManager labManager5 = new org.example.users.LabManager("", "hi!", "", "", "hi!");
        java.lang.String str6 = labManager5.getUserId();
        java.lang.String str7 = labManager5.getIdNumber();
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
        org.junit.Assert.assertNull(str7);
    }

    @Test
    public void test008() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test008");
        org.example.users.LabManager labManager5 = new org.example.users.LabManager("", "hi!", "", "", "hi!");
        java.lang.String str6 = labManager5.getName();
        java.lang.String str7 = labManager5.getIdNumber();
        java.lang.Class<?> wildcardClass8 = labManager5.getClass();
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "hi!" + "'", str6, "hi!");
        org.junit.Assert.assertNull(str7);
        org.junit.Assert.assertNotNull(wildcardClass8);
    }

    @Test
    public void test009() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test009");
        org.example.users.LabManager labManager6 = new org.example.users.LabManager("", "hi!", "", "", "hi!");
        java.lang.String str7 = labManager6.getName();
        org.example.equipment.Equipment equipment8 = null;
        java.time.LocalDateTime localDateTime9 = null;
        java.time.LocalDateTime localDateTime10 = null;
        org.example.reservation.Reservation reservation12 = new org.example.reservation.Reservation("",
                (org.example.users.User) labManager6, equipment8, localDateTime9, localDateTime10, (double) 0);
        // The following exception was thrown during execution in test generation
        try {
            long long13 = reservation12.getDurationHours();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "hi!" + "'", str7, "hi!");
    }

    @Test
    public void test010() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test010");
        org.example.equipment.EquipmentStatus equipmentStatus0 = org.example.equipment.EquipmentStatus.DISABLED;
        org.junit.Assert.assertTrue(
                "'" + equipmentStatus0 + "' != '" + org.example.equipment.EquipmentStatus.DISABLED + "'",
                equipmentStatus0.equals(org.example.equipment.EquipmentStatus.DISABLED));
    }

    @Test
    public void test011() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test011");
        org.example.users.UserFactory userFactory0 = new org.example.users.UserFactory();
    }

    @Test
    public void test012() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test012");
        org.example.users.User user7 = org.example.users.UserFactory.createUser("MANAGER", "hi!", "HEAD_COORDINATOR",
                "MANAGER", "hi!", "hi!", "hi!");
        org.junit.Assert.assertNotNull(user7);
    }

    @Test
    public void test013() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test013");
        org.example.reservation.ReservationStatus reservationStatus0 = org.example.reservation.ReservationStatus.CANCELLED;
        org.junit.Assert.assertTrue(
                "'" + reservationStatus0 + "' != '" + org.example.reservation.ReservationStatus.CANCELLED + "'",
                reservationStatus0.equals(org.example.reservation.ReservationStatus.CANCELLED));
    }

    @Test
    public void test014() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test014");
        org.example.users.LabManager labManager5 = new org.example.users.LabManager("", "hi!", "", "", "hi!");
        java.lang.String str6 = labManager5.getName();
        java.lang.String str7 = labManager5.getEmail();
        java.lang.String str8 = labManager5.getUserType();
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "hi!" + "'", str6, "hi!");
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "" + "'", str7, "");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "MANAGER" + "'", str8, "MANAGER");
    }

    @Test
    public void test015() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test015");
        org.example.auth.PasswordValidator passwordValidator0 = new org.example.auth.PasswordValidator();
    }

    @Test
    public void test016() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test016");
        org.example.users.Guest guest6 = new org.example.users.Guest("MANAGER", "HEAD_COORDINATOR", "",
                "HEAD_COORDINATOR", "HEAD_COORDINATOR", "MANAGER");
        java.lang.String str7 = guest6.getUserType();
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "GUEST" + "'", str7, "GUEST");
    }

    @Test
    public void test017() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test017");
        org.example.users.LabManager labManager5 = new org.example.users.LabManager("", "hi!", "", "", "hi!");
        java.lang.String str6 = labManager5.getName();
        java.lang.String str7 = labManager5.getUserType();
        java.lang.String str8 = labManager5.getUserType();
        java.lang.String str9 = labManager5.getIdNumber();
        java.lang.String str10 = labManager5.getUserType();
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "hi!" + "'", str6, "hi!");
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "MANAGER" + "'", str7, "MANAGER");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "MANAGER" + "'", str8, "MANAGER");
        org.junit.Assert.assertNull(str9);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "MANAGER" + "'", str10, "MANAGER");
    }

    @Test
    public void test018() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test018");
        org.example.users.Faculty faculty6 = new org.example.users.Faculty("HEAD_COORDINATOR", "", "MANAGER", "MANAGER",
                "MANAGER", "HEAD_COORDINATOR");
        java.lang.String str7 = faculty6.getUserType();
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "FACULTY" + "'", str7, "FACULTY");
    }

    @Test
    public void test019() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test019");
        org.example.users.Guest guest6 = new org.example.users.Guest("", "HEAD_COORDINATOR - MANAGER () [AVAILABLE]",
                "MANAGER", "HEAD_COORDINATOR", "hi!", "MANAGER");
    }

    @Test
    public void test020() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test020");
        org.example.users.LabManager labManager5 = new org.example.users.LabManager("", "hi!", "", "", "hi!");
        java.lang.String str6 = labManager5.getName();
        java.lang.String str7 = labManager5.getIdNumber();
        java.lang.String str8 = labManager5.getUserId();
        labManager5.setPassword("");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "hi!" + "'", str6, "hi!");
        org.junit.Assert.assertNull(str7);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
    }

    @Test
    public void test021() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test021");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<org.example.users.User> userList1 = cSVRepository0.getAllUsers();
        java.util.List<org.example.equipment.Equipment> equipmentList2 = cSVRepository0.getAllEquipment();
        org.junit.Assert.assertNotNull(userList1);
        org.junit.Assert.assertNotNull(equipmentList2);
    }

    @Test
    public void test022() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test022");
        org.example.users.LabManager labManager5 = new org.example.users.LabManager("", "hi!", "", "", "hi!");
        java.lang.String str6 = labManager5.getUserId();
        labManager5.setPassword("");
        labManager5.setEmail("");
        labManager5.setPassword("MANAGER");
        java.lang.String str13 = labManager5.getUserType();
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "MANAGER" + "'", str13, "MANAGER");
    }

    @Test
    public void test023() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test023");
        org.example.users.Guest guest6 = new org.example.users.Guest("FACULTY", "MANAGER", "hi!",
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]", "FACULTY", "hi!");
    }

    @Test
    public void test024() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test024");
        org.example.users.LabManager labManager5 = new org.example.users.LabManager("", "hi!", "", "", "hi!");
        java.lang.String str6 = labManager5.getName();
        java.lang.String str7 = labManager5.getUserType();
        labManager5.setUserId("MANAGER");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "hi!" + "'", str6, "hi!");
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "MANAGER" + "'", str7, "MANAGER");
    }

    @Test
    public void test025() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test025");
        boolean boolean1 = org.example.auth.PasswordValidator.isValid("FACULTY");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test026() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test026");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        java.lang.String str4 = equipment3.getImagePath();
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "" + "'", str4, "");
    }

    @Test
    public void test027() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test027");
        org.example.users.LabManager labManager6 = new org.example.users.LabManager("", "hi!", "", "", "hi!");
        java.lang.String str7 = labManager6.getName();
        org.example.equipment.Equipment equipment8 = null;
        java.time.LocalDateTime localDateTime9 = null;
        java.time.LocalDateTime localDateTime10 = null;
        org.example.reservation.Reservation reservation12 = new org.example.reservation.Reservation("",
                (org.example.users.User) labManager6, equipment8, localDateTime9, localDateTime10, (double) 0);
        double double13 = reservation12.getDeposit();
        java.time.LocalDateTime localDateTime14 = reservation12.getStartTime();
        java.time.LocalDateTime localDateTime15 = null;
        reservation12.setStartTime(localDateTime15);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "hi!" + "'", str7, "hi!");
        org.junit.Assert.assertTrue("'" + double13 + "' != '" + 0.0d + "'", double13 == 0.0d);
        org.junit.Assert.assertNull(localDateTime14);
    }

    @Test
    public void test028() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test028");
        org.example.users.LabManager labManager5 = new org.example.users.LabManager("", "hi!", "", "", "hi!");
        java.lang.String str6 = labManager5.getName();
        java.lang.String str7 = labManager5.getUserType();
        java.lang.String str8 = labManager5.getUserType();
        java.lang.String str9 = labManager5.getIdNumber();
        java.lang.String str10 = labManager5.getName();
        java.lang.String str11 = labManager5.getDepartmentId();
        java.lang.String str12 = labManager5.getName();
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "hi!" + "'", str6, "hi!");
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "MANAGER" + "'", str7, "MANAGER");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "MANAGER" + "'", str8, "MANAGER");
        org.junit.Assert.assertNull(str9);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "hi!" + "'", str10, "hi!");
        org.junit.Assert.assertNull(str11);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "hi!" + "'", str12, "hi!");
    }

    @Test
    public void test029() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test029");
        org.example.data.CSVHandler cSVHandler0 = org.example.data.CSVHandler.getInstance();
        java.lang.String[] strArray8 = new java.lang.String[] { "hi!", "RESEARCHER", "HEAD_COORDINATOR", "RESEARCHER",
                "", "FACULTY" };
        cSVHandler0.appendCSV("HEAD_COORDINATOR - MANAGER () [AVAILABLE]", strArray8);
        org.junit.Assert.assertNotNull(cSVHandler0);
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertArrayEquals(strArray8,
                new java.lang.String[] { "hi!", "RESEARCHER", "HEAD_COORDINATOR", "RESEARCHER", "", "FACULTY" });
    }

    @Test
    public void test030() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test030");
        org.example.users.LabManager labManager5 = new org.example.users.LabManager("", "hi!", "", "", "hi!");
        java.lang.String str6 = labManager5.getUserId();
        labManager5.setPassword("");
        labManager5.setEmail("");
        java.lang.String str11 = labManager5.getManagerId();
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "hi!" + "'", str11, "hi!");
    }

    @Test
    public void test031() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test031");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        java.lang.String str4 = equipment3.getDescription();
        java.lang.String str5 = equipment3.toString();
        java.lang.String str6 = equipment3.toString();
        org.example.equipment.Equipment equipment10 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        java.lang.String str11 = equipment10.getDescription();
        java.util.List<java.lang.String> strList12 = equipment10.getTags();
        equipment3.setTags(strList12);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "MANAGER" + "'", str4, "MANAGER");
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "HEAD_COORDINATOR - MANAGER () [AVAILABLE]" + "'", str5,
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "HEAD_COORDINATOR - MANAGER () [AVAILABLE]" + "'", str6,
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]");
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "MANAGER" + "'", str11, "MANAGER");
        org.junit.Assert.assertNotNull(strList12);
    }

    @Test
    public void test032() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test032");
        org.example.users.LabManager labManager6 = new org.example.users.LabManager("", "hi!", "", "", "hi!");
        java.lang.String str7 = labManager6.getName();
        org.example.equipment.Equipment equipment8 = null;
        java.time.LocalDateTime localDateTime9 = null;
        java.time.LocalDateTime localDateTime10 = null;
        org.example.reservation.Reservation reservation12 = new org.example.reservation.Reservation("",
                (org.example.users.User) labManager6, equipment8, localDateTime9, localDateTime10, (double) 0);
        double double13 = reservation12.getDeposit();
        java.time.LocalDateTime localDateTime14 = reservation12.getEndTime();
        // The following exception was thrown during execution in test generation
        try {
            double double16 = reservation12.calculateTotal((double) 'a');
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "hi!" + "'", str7, "hi!");
        org.junit.Assert.assertTrue("'" + double13 + "' != '" + 0.0d + "'", double13 == 0.0d);
        org.junit.Assert.assertNull(localDateTime14);
    }

    @Test
    public void test033() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test033");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        org.example.equipment.Equipment equipment4 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        equipment4.setProductStatistics("MANAGER");
        equipment4.setImagePath("hi!");
        cSVRepository0.updateEquipment(equipment4);
        org.example.payment.Payment payment10 = null;
        // The following exception was thrown during execution in test generation
        try {
            cSVRepository0.savePayment(payment10);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test034() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test034");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("MANAGER", "MANAGER",
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]");
        java.lang.String str4 = equipment3.getLabLocation();
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "HEAD_COORDINATOR - MANAGER () [AVAILABLE]" + "'", str4,
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]");
    }

    @Test
    public void test035() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test035");
        boolean boolean1 = org.example.auth.PasswordValidator.isValid("RESEARCHER");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test036() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test036");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<org.example.users.User> userList1 = cSVRepository0.getAllUsers();
        java.util.List<java.lang.String[]> strArrayList3 = cSVRepository0.getPaymentRowsByReservationId("GUEST");
        org.junit.Assert.assertNotNull(userList1);
        org.junit.Assert.assertNotNull(strArrayList3);
    }

    @Test
    public void test037() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test037");
        org.example.users.HeadCoordinator headCoordinator4 = org.example.users.HeadCoordinator.getInstance("hi!",
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]", "MANAGER", "hi!");
        org.junit.Assert.assertNotNull(headCoordinator4);
    }

    @Test
    public void test038() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test038");
        org.example.users.Guest guest6 = new org.example.users.Guest("HEAD_COORDINATOR", "hi!", "FACULTY",
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]", "", "HEAD_COORDINATOR");
    }

    @Test
    public void test039() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test039");
        org.example.users.LabManager labManager6 = new org.example.users.LabManager("", "hi!", "", "", "hi!");
        java.lang.String str7 = labManager6.getName();
        org.example.equipment.Equipment equipment8 = null;
        java.time.LocalDateTime localDateTime9 = null;
        java.time.LocalDateTime localDateTime10 = null;
        org.example.reservation.Reservation reservation12 = new org.example.reservation.Reservation("",
                (org.example.users.User) labManager6, equipment8, localDateTime9, localDateTime10, (double) 0);
        double double13 = reservation12.getDeposit();
        java.time.LocalDateTime localDateTime14 = reservation12.getStartTime();
        org.example.users.User user15 = reservation12.getUser();
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "hi!" + "'", str7, "hi!");
        org.junit.Assert.assertTrue("'" + double13 + "' != '" + 0.0d + "'", double13 == 0.0d);
        org.junit.Assert.assertNull(localDateTime14);
        org.junit.Assert.assertNotNull(user15);
    }

    @Test
    public void test040() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test040");
        org.example.users.Guest guest6 = new org.example.users.Guest("hi!", "HEAD_COORDINATOR", "HEAD_COORDINATOR",
                "hi!", "", "FACULTY");
    }

    @Test
    public void test041() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test041");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<org.example.users.User> userList1 = cSVRepository0.getAllUsers();
        org.example.payment.Payment payment2 = null;
        // The following exception was thrown during execution in test generation
        try {
            cSVRepository0.savePayment(payment2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(userList1);
    }

    @Test
    public void test042() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test042");
        org.example.reservation.ReservationStatus reservationStatus0 = org.example.reservation.ReservationStatus.CONFIRMED;
        org.junit.Assert.assertTrue(
                "'" + reservationStatus0 + "' != '" + org.example.reservation.ReservationStatus.CONFIRMED + "'",
                reservationStatus0.equals(org.example.reservation.ReservationStatus.CONFIRMED));
    }

    @Test
    public void test043() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test043");
        org.example.users.Guest guest6 = new org.example.users.Guest("MANAGER", "HEAD_COORDINATOR", "",
                "HEAD_COORDINATOR", "HEAD_COORDINATOR", "MANAGER");
        guest6.setIdNumber("hi!");
        guest6.setPassword("RESEARCHER");
    }

    @Test
    public void test044() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test044");
        org.example.users.Guest guest7 = new org.example.users.Guest("", "hi!", "HEAD_COORDINATOR", "",
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]", "HEAD_COORDINATOR");
        org.example.equipment.Equipment equipment11 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        java.lang.String str12 = equipment11.getDescription();
        java.time.LocalDateTime localDateTime13 = null;
        java.time.LocalDateTime localDateTime14 = null;
        org.example.reservation.Reservation reservation16 = new org.example.reservation.Reservation("hi!",
                (org.example.users.User) guest7, equipment11, localDateTime13, localDateTime14, (double) 1);
        // The following exception was thrown during execution in test generation
        try {
            long long17 = reservation16.getDurationHours();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "MANAGER" + "'", str12, "MANAGER");
    }

    @Test
    public void test045() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test045");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment(
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]", "MANAGER", "HEAD_COORDINATOR");
    }

    @Test
    public void test046() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test046");
        org.example.users.Researcher researcher6 = new org.example.users.Researcher("", "", "", "MANAGER", "hi!", "");
        java.lang.String str7 = researcher6.getUserType();
        java.lang.String str8 = researcher6.getUserType();
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "RESEARCHER" + "'", str7, "RESEARCHER");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "RESEARCHER" + "'", str8, "RESEARCHER");
    }

    @Test
    public void test047() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test047");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<org.example.users.User> userList1 = cSVRepository0.getAllUsers();
        java.util.List<java.lang.String[]> strArrayList2 = cSVRepository0.getAllPaymentRows();
        org.example.payment.Payment payment3 = null;
        // The following exception was thrown during execution in test generation
        try {
            cSVRepository0.savePayment(payment3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(userList1);
        org.junit.Assert.assertNotNull(strArrayList2);
    }

    @Test
    public void test048() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test048");
        org.example.users.Guest guest7 = new org.example.users.Guest("", "hi!", "HEAD_COORDINATOR", "",
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]", "HEAD_COORDINATOR");
        org.example.equipment.Equipment equipment11 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        java.lang.String str12 = equipment11.getDescription();
        java.time.LocalDateTime localDateTime13 = null;
        java.time.LocalDateTime localDateTime14 = null;
        org.example.reservation.Reservation reservation16 = new org.example.reservation.Reservation("hi!",
                (org.example.users.User) guest7, equipment11, localDateTime13, localDateTime14, (double) 1);
        java.lang.String str17 = reservation16.getReservationId();
        java.time.LocalDateTime localDateTime18 = reservation16.getEndTime();
        java.time.LocalDateTime localDateTime19 = null;
        reservation16.setStartTime(localDateTime19);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "MANAGER" + "'", str12, "MANAGER");
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "hi!" + "'", str17, "hi!");
        org.junit.Assert.assertNull(localDateTime18);
    }

    @Test
    public void test049() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test049");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        java.lang.String str4 = equipment3.getDescription();
        java.lang.String str5 = equipment3.toString();
        equipment3.setAvailableUnits((int) (short) -1);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "MANAGER" + "'", str4, "MANAGER");
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "HEAD_COORDINATOR - MANAGER () [AVAILABLE]" + "'", str5,
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]");
    }

    @Test
    public void test050() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test050");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("FACULTY", "GUEST",
                "HEAD_COORDINATOR");
    }

    @Test
    public void test051() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test051");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        equipment3.setProductStatistics("MANAGER");
        java.lang.String str6 = equipment3.getLabLocation();
        equipment3.setImagePath("GUEST");
        int int9 = equipment3.getAvailableUnits();
        equipment3.setImagePath("HEAD_COORDINATOR - MANAGER () [AVAILABLE]");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 1 + "'", int9 == 1);
    }

    @Test
    public void test052() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test052");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<org.example.users.User> userList1 = cSVRepository0.getAllUsers();
        org.example.users.Guest guest9 = new org.example.users.Guest("", "hi!", "HEAD_COORDINATOR", "",
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]", "HEAD_COORDINATOR");
        org.example.equipment.Equipment equipment13 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        java.lang.String str14 = equipment13.getDescription();
        java.time.LocalDateTime localDateTime15 = null;
        java.time.LocalDateTime localDateTime16 = null;
        org.example.reservation.Reservation reservation18 = new org.example.reservation.Reservation("hi!",
                (org.example.users.User) guest9, equipment13, localDateTime15, localDateTime16, (double) 1);
        java.lang.String str19 = reservation18.getReservationId();
        cSVRepository0.updateReservation(reservation18);
        org.junit.Assert.assertNotNull(userList1);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "MANAGER" + "'", str14, "MANAGER");
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "hi!" + "'", str19, "hi!");
    }

    @Test
    public void test053() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test053");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<org.example.users.User> userList1 = cSVRepository0.getAllUsers();
        java.util.List<java.lang.String[]> strArrayList2 = cSVRepository0.getAllPaymentRows();
        java.util.List<org.example.users.User> userList3 = cSVRepository0.getAllUsers();
        org.junit.Assert.assertNotNull(userList1);
        org.junit.Assert.assertNotNull(strArrayList2);
        org.junit.Assert.assertNotNull(userList3);
    }

    @Test
    public void test054() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test054");
        org.example.users.LabManager labManager5 = new org.example.users.LabManager("", "hi!", "", "", "hi!");
        java.lang.String str6 = labManager5.getName();
        java.lang.String str7 = labManager5.getIdNumber();
        java.lang.String str8 = labManager5.getIdNumber();
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "hi!" + "'", str6, "hi!");
        org.junit.Assert.assertNull(str7);
        org.junit.Assert.assertNull(str8);
    }

    @Test
    public void test055() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test055");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        equipment3.setProductStatistics("MANAGER");
        java.lang.String str6 = equipment3.getName();
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "MANAGER" + "'", str6, "MANAGER");
    }

    @Test
    public void test056() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test056");
        org.example.users.HeadCoordinator headCoordinator4 = org.example.users.HeadCoordinator.getInstance("MANAGER",
                "hi!", "MANAGER", "hi!");
        java.lang.String str5 = headCoordinator4.getUserType();
        org.example.users.LabManager labManager10 = headCoordinator4.generateManagerAccount("GUEST", "MANAGER",
                "HEAD_COORDINATOR", "HEAD_COORDINATOR");
        org.junit.Assert.assertNotNull(headCoordinator4);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "HEAD_COORDINATOR" + "'", str5, "HEAD_COORDINATOR");
        org.junit.Assert.assertNotNull(labManager10);
    }

    @Test
    public void test057() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test057");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("HEAD_COORDINATOR",
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]", "hi!");
    }

    @Test
    public void test058() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test058");
        org.example.users.Faculty faculty6 = new org.example.users.Faculty("FACULTY", "MANAGER", "hi!", "hi!",
                "MANAGER", "");
    }

    @Test
    public void test059() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test059");
        org.example.users.HeadCoordinator headCoordinator4 = org.example.users.HeadCoordinator
                .getInstance("HEAD_COORDINATOR", "GUEST", "HEAD_COORDINATOR", "MANAGER");
        org.example.users.LabManager labManager9 = headCoordinator4.generateManagerAccount("hi!", "RESEARCHER", "GUEST",
                "");
        org.example.users.LabManager labManager14 = headCoordinator4.generateManagerAccount("hi!", "", "FACULTY", "");
        java.lang.String str15 = headCoordinator4.getEmail();
        org.junit.Assert.assertNotNull(headCoordinator4);
        org.junit.Assert.assertNotNull(labManager9);
        org.junit.Assert.assertNotNull(labManager14);
        // flaky due to HeadCoordinator singleton state:
        // org.junit.Assert.assertEquals("'" + str15 + "' != '" + "MANAGER" + "'",
        // str15, "MANAGER");
    }

    @Test
    public void test060() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test060");
        org.example.auth.AuthService authService0 = new org.example.auth.AuthService();
        boolean boolean2 = authService0.isApproved("GUEST");
        org.example.users.User user5 = authService0.login("HEAD_COORDINATOR", "");
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + true + "'", boolean2 == true);
        org.junit.Assert.assertNull(user5);
    }

    @Test
    public void test061() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test061");
        org.example.users.HeadCoordinator headCoordinator4 = org.example.users.HeadCoordinator.getInstance("MANAGER",
                "hi!", "MANAGER", "hi!");
        java.lang.String str5 = headCoordinator4.getUserType();
        headCoordinator4.setIdNumber("");
        org.junit.Assert.assertNotNull(headCoordinator4);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "HEAD_COORDINATOR" + "'", str5, "HEAD_COORDINATOR");
    }

    @Test
    public void test062() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test062");
        org.example.users.HeadCoordinator headCoordinator4 = org.example.users.HeadCoordinator.getInstance("MANAGER",
                "hi!", "MANAGER", "hi!");
        java.lang.String str5 = headCoordinator4.getUserType();
        java.lang.String str6 = headCoordinator4.getUserId();
        headCoordinator4.setName("RESEARCHER");
        org.junit.Assert.assertNotNull(headCoordinator4);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "HEAD_COORDINATOR" + "'", str5, "HEAD_COORDINATOR");
        // flaky "1) test062(org.example.randoop.RandoopRegressionTest0)":
        // org.junit.Assert.assertEquals("'" + str6 + "' != '" + "MANAGER" + "'", str6,
        // "MANAGER");
    }

    @Test
    public void test063() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test063");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        org.example.equipment.Equipment equipment4 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        equipment4.setProductStatistics("MANAGER");
        equipment4.setImagePath("hi!");
        cSVRepository0.updateEquipment(equipment4);
        org.example.equipment.Equipment equipment13 = new org.example.equipment.Equipment("MANAGER", "MANAGER",
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]");
        cSVRepository0.saveEquipment(equipment13);
        java.util.List<org.example.users.User> userList15 = cSVRepository0.getAllUsers();
        java.util.List<java.lang.String[]> strArrayList17 = cSVRepository0.getReservationRowsByUserId("hi!");
        org.junit.Assert.assertNotNull(userList15);
        org.junit.Assert.assertNotNull(strArrayList17);
    }

    @Test
    public void test064() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test064");
        org.example.users.Guest guest7 = new org.example.users.Guest("", "hi!", "HEAD_COORDINATOR", "",
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]", "HEAD_COORDINATOR");
        org.example.equipment.Equipment equipment11 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        java.lang.String str12 = equipment11.getDescription();
        java.time.LocalDateTime localDateTime13 = null;
        java.time.LocalDateTime localDateTime14 = null;
        org.example.reservation.Reservation reservation16 = new org.example.reservation.Reservation("hi!",
                (org.example.users.User) guest7, equipment11, localDateTime13, localDateTime14, (double) 1);
        java.lang.String str17 = reservation16.getReservationId();
        java.time.LocalDateTime localDateTime18 = reservation16.getEndTime();
        org.example.equipment.Equipment equipment19 = reservation16.getEquipment();
        double double20 = reservation16.getDeposit();
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "MANAGER" + "'", str12, "MANAGER");
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "hi!" + "'", str17, "hi!");
        org.junit.Assert.assertNull(localDateTime18);
        org.junit.Assert.assertNotNull(equipment19);
        org.junit.Assert.assertTrue("'" + double20 + "' != '" + 1.0d + "'", double20 == 1.0d);
    }

    @Test
    public void test065() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test065");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("", "FACULTY", (double) (-1), "FACULTY",
                false);
    }

    @Test
    public void test066() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test066");
        org.example.users.Guest guest7 = new org.example.users.Guest("", "hi!", "HEAD_COORDINATOR", "",
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]", "HEAD_COORDINATOR");
        org.example.equipment.Equipment equipment11 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        java.lang.String str12 = equipment11.getDescription();
        java.time.LocalDateTime localDateTime13 = null;
        java.time.LocalDateTime localDateTime14 = null;
        org.example.reservation.Reservation reservation16 = new org.example.reservation.Reservation("hi!",
                (org.example.users.User) guest7, equipment11, localDateTime13, localDateTime14, (double) 1);
        java.lang.String str17 = equipment11.toString();
        java.lang.String str18 = equipment11.toString();
        equipment11.setAvailableUnits((int) (short) 100);
        equipment11.setAvailableUnits((int) 'a');
        java.lang.String str23 = equipment11.getProductStatistics();
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "MANAGER" + "'", str12, "MANAGER");
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "HEAD_COORDINATOR - MANAGER () [AVAILABLE]" + "'", str17,
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]");
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "HEAD_COORDINATOR - MANAGER () [AVAILABLE]" + "'", str18,
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]");
        org.junit.Assert.assertEquals("'" + str23 + "' != '" + "" + "'", str23, "");
    }

    @Test
    public void test067() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test067");
        org.example.data.CSVHandler cSVHandler0 = org.example.data.CSVHandler.getInstance();
        java.lang.String[] strArray2 = null;
        // The following exception was thrown during execution in test generation
        try {
            cSVHandler0.appendCSV("GUEST", strArray2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(cSVHandler0);
    }

    @Test
    public void test068() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test068");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        equipment3.setProductStatistics("MANAGER");
        equipment3.setImagePath("hi!");
        org.example.equipment.EquipmentStatus equipmentStatus8 = org.example.equipment.EquipmentStatus.AVAILABLE;
        equipment3.setStatus(equipmentStatus8);
        org.junit.Assert.assertTrue(
                "'" + equipmentStatus8 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'",
                equipmentStatus8.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
    }

    @Test
    public void test069() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test069");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<java.lang.String[]> strArrayList2 = cSVRepository0
                .getReservationRowsByUserId("HEAD_COORDINATOR - MANAGER () [AVAILABLE]");
        org.junit.Assert.assertNotNull(strArrayList2);
    }

    @Test
    public void test070() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test070");
        org.example.users.Guest guest7 = new org.example.users.Guest("", "hi!", "HEAD_COORDINATOR", "",
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]", "HEAD_COORDINATOR");
        org.example.equipment.Equipment equipment11 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        java.lang.String str12 = equipment11.getDescription();
        java.time.LocalDateTime localDateTime13 = null;
        java.time.LocalDateTime localDateTime14 = null;
        org.example.reservation.Reservation reservation16 = new org.example.reservation.Reservation("hi!",
                (org.example.users.User) guest7, equipment11, localDateTime13, localDateTime14, (double) 1);
        org.example.users.User user17 = reservation16.getUser();
        // The following exception was thrown during execution in test generation
        try {
            long long18 = reservation16.getDurationHours();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "MANAGER" + "'", str12, "MANAGER");
        org.junit.Assert.assertNotNull(user17);
    }

    @Test
    public void test071() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test071");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        java.lang.String str4 = equipment3.getDescription();
        java.lang.String str5 = equipment3.toString();
        java.lang.String str6 = equipment3.getName();
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "MANAGER" + "'", str4, "MANAGER");
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "HEAD_COORDINATOR - MANAGER () [AVAILABLE]" + "'", str5,
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "MANAGER" + "'", str6, "MANAGER");
    }

    @Test
    public void test072() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test072");
        org.example.users.Guest guest6 = new org.example.users.Guest("MANAGER", "HEAD_COORDINATOR", "",
                "HEAD_COORDINATOR", "HEAD_COORDINATOR", "MANAGER");
        guest6.setIdNumber("hi!");
        java.lang.String str9 = guest6.getUserType();
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "GUEST" + "'", str9, "GUEST");
    }

    @Test
    public void test073() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test073");
        org.example.users.LabManager labManager6 = new org.example.users.LabManager("", "hi!", "", "", "hi!");
        java.lang.String str7 = labManager6.getName();
        org.example.equipment.Equipment equipment8 = null;
        java.time.LocalDateTime localDateTime9 = null;
        java.time.LocalDateTime localDateTime10 = null;
        org.example.reservation.Reservation reservation12 = new org.example.reservation.Reservation("",
                (org.example.users.User) labManager6, equipment8, localDateTime9, localDateTime10, (double) 0);
        double double13 = reservation12.getDeposit();
        java.time.LocalDateTime localDateTime14 = reservation12.getEndTime();
        java.time.LocalDateTime localDateTime15 = reservation12.getStartTime();
        org.example.users.User user16 = reservation12.getUser();
        // The following exception was thrown during execution in test generation
        try {
            long long17 = reservation12.getDurationHours();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "hi!" + "'", str7, "hi!");
        org.junit.Assert.assertTrue("'" + double13 + "' != '" + 0.0d + "'", double13 == 0.0d);
        org.junit.Assert.assertNull(localDateTime14);
        org.junit.Assert.assertNull(localDateTime15);
        org.junit.Assert.assertNotNull(user16);
    }

    @Test
    public void test074() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test074");
        org.example.users.Faculty faculty6 = new org.example.users.Faculty("HEAD_COORDINATOR", "", "MANAGER", "MANAGER",
                "MANAGER", "HEAD_COORDINATOR");
        faculty6.setPassword("MANAGER");
        java.lang.String str9 = faculty6.getUserType();
        faculty6.setName("HEAD_COORDINATOR");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "FACULTY" + "'", str9, "FACULTY");
    }

    @Test
    public void test075() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test075");
        org.example.users.Guest guest7 = new org.example.users.Guest("", "hi!", "HEAD_COORDINATOR", "",
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]", "HEAD_COORDINATOR");
        org.example.equipment.Equipment equipment11 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        java.lang.String str12 = equipment11.getDescription();
        java.time.LocalDateTime localDateTime13 = null;
        java.time.LocalDateTime localDateTime14 = null;
        org.example.reservation.Reservation reservation16 = new org.example.reservation.Reservation("hi!",
                (org.example.users.User) guest7, equipment11, localDateTime13, localDateTime14, (double) 1);
        java.lang.String str17 = equipment11.toString();
        java.util.List<java.lang.String> strList18 = equipment11.getTags();
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "MANAGER" + "'", str12, "MANAGER");
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "HEAD_COORDINATOR - MANAGER () [AVAILABLE]" + "'", str17,
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]");
        org.junit.Assert.assertNotNull(strList18);
    }

    @Test
    public void test076() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test076");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("MANAGER", "MANAGER", (double) (byte) 1,
                "RESEARCHER", false);
        double double6 = payment5.getAmount();
        boolean boolean7 = payment5.isForfeited();
        org.junit.Assert.assertTrue("'" + double6 + "' != '" + 1.0d + "'", double6 == 1.0d);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test077() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test077");
        org.example.users.Guest guest7 = new org.example.users.Guest("", "hi!", "HEAD_COORDINATOR", "",
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]", "HEAD_COORDINATOR");
        org.example.equipment.Equipment equipment11 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        java.lang.String str12 = equipment11.getDescription();
        java.time.LocalDateTime localDateTime13 = null;
        java.time.LocalDateTime localDateTime14 = null;
        org.example.reservation.Reservation reservation16 = new org.example.reservation.Reservation("hi!",
                (org.example.users.User) guest7, equipment11, localDateTime13, localDateTime14, (double) 1);
        java.lang.String str17 = reservation16.getReservationId();
        java.time.LocalDateTime localDateTime18 = reservation16.getEndTime();
        org.example.equipment.Equipment equipment19 = reservation16.getEquipment();
        java.time.LocalDateTime localDateTime20 = reservation16.getEndTime();
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "MANAGER" + "'", str12, "MANAGER");
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "hi!" + "'", str17, "hi!");
        org.junit.Assert.assertNull(localDateTime18);
        org.junit.Assert.assertNotNull(equipment19);
        org.junit.Assert.assertNull(localDateTime20);
    }

    @Test
    public void test078() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test078");
        org.example.users.LabManager labManager6 = new org.example.users.LabManager("", "hi!", "", "", "hi!");
        java.lang.String str7 = labManager6.getName();
        org.example.equipment.Equipment equipment8 = null;
        java.time.LocalDateTime localDateTime9 = null;
        java.time.LocalDateTime localDateTime10 = null;
        org.example.reservation.Reservation reservation12 = new org.example.reservation.Reservation("",
                (org.example.users.User) labManager6, equipment8, localDateTime9, localDateTime10, (double) 0);
        org.example.equipment.Equipment equipment13 = reservation12.getEquipment();
        double double14 = reservation12.getDeposit();
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "hi!" + "'", str7, "hi!");
        org.junit.Assert.assertNull(equipment13);
        org.junit.Assert.assertTrue("'" + double14 + "' != '" + 0.0d + "'", double14 == 0.0d);
    }

    @Test
    public void test079() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test079");
        org.example.reservation.ReservationStatus reservationStatus0 = org.example.reservation.ReservationStatus.PENDING;
        org.junit.Assert.assertTrue(
                "'" + reservationStatus0 + "' != '" + org.example.reservation.ReservationStatus.PENDING + "'",
                reservationStatus0.equals(org.example.reservation.ReservationStatus.PENDING));
    }

    @Test
    public void test080() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test080");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        equipment3.setProductStatistics("MANAGER");
        java.lang.String str6 = equipment3.getLabLocation();
        equipment3.setImagePath("GUEST");
        int int9 = equipment3.getAvailableUnits();
        equipment3.setName("STUDENT");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 1 + "'", int9 == 1);
    }

    @Test
    public void test081() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test081");
        org.example.users.HeadCoordinator headCoordinator4 = org.example.users.HeadCoordinator.getInstance("GUEST",
                "GUEST", "hi!", "");
        org.junit.Assert.assertNotNull(headCoordinator4);
    }

    @Test
    public void test082() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test082");
        org.example.users.LabManager labManager6 = new org.example.users.LabManager("hi!", "RESEARCHER", "MANAGER",
                "GUEST", "MANAGER");
        org.example.equipment.Equipment equipment10 = new org.example.equipment.Equipment("MANAGER", "MANAGER",
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]");
        java.time.LocalDateTime localDateTime11 = null;
        java.time.LocalDateTime localDateTime12 = null;
        org.example.reservation.Reservation reservation14 = new org.example.reservation.Reservation("hi!",
                (org.example.users.User) labManager6, equipment10, localDateTime11, localDateTime12, (double) 10);
    }

    @Test
    public void test083() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test083");
        org.example.users.LabManager labManager6 = new org.example.users.LabManager("", "hi!", "", "", "hi!");
        java.lang.String str7 = labManager6.getName();
        java.lang.String str8 = labManager6.getUserType();
        java.lang.String str9 = labManager6.getUserType();
        java.lang.String str10 = labManager6.getIdNumber();
        java.lang.String str11 = labManager6.getName();
        labManager6.setIdNumber("MANAGER");
        org.example.data.CSVRepository cSVRepository14 = new org.example.data.CSVRepository();
        org.example.equipment.Equipment equipment18 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        equipment18.setProductStatistics("MANAGER");
        equipment18.setImagePath("hi!");
        cSVRepository14.updateEquipment(equipment18);
        org.example.equipment.Equipment equipment27 = new org.example.equipment.Equipment("MANAGER", "MANAGER",
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]");
        cSVRepository14.saveEquipment(equipment27);
        org.example.equipment.EquipmentStatus equipmentStatus29 = equipment27.getStatus();
        java.time.LocalDateTime localDateTime30 = null;
        java.time.LocalDateTime localDateTime31 = null;
        org.example.reservation.Reservation reservation33 = new org.example.reservation.Reservation(
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]", (org.example.users.User) labManager6, equipment27,
                localDateTime30, localDateTime31, (double) 100);
        org.example.reservation.ReservationStatus reservationStatus34 = org.example.reservation.ReservationStatus.COMPLETED;
        reservation33.setStatus(reservationStatus34);
        org.example.reservation.ReservationStatus reservationStatus36 = reservation33.getStatus();
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "hi!" + "'", str7, "hi!");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "MANAGER" + "'", str8, "MANAGER");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "MANAGER" + "'", str9, "MANAGER");
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "hi!" + "'", str11, "hi!");
        org.junit.Assert.assertTrue(
                "'" + equipmentStatus29 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'",
                equipmentStatus29.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertTrue(
                "'" + reservationStatus34 + "' != '" + org.example.reservation.ReservationStatus.COMPLETED + "'",
                reservationStatus34.equals(org.example.reservation.ReservationStatus.COMPLETED));
        org.junit.Assert.assertTrue(
                "'" + reservationStatus36 + "' != '" + org.example.reservation.ReservationStatus.COMPLETED + "'",
                reservationStatus36.equals(org.example.reservation.ReservationStatus.COMPLETED));
    }

    @Test
    public void test084() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test084");
        org.example.users.Faculty faculty6 = new org.example.users.Faculty("HEAD_COORDINATOR", "MANAGER", "",
                "HEAD_COORDINATOR", "HEAD_COORDINATOR", "STUDENT");
    }

    @Test
    public void test085() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test085");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        org.example.users.User user2 = cSVRepository0.findUserById("");
        java.util.List<org.example.users.User> userList3 = cSVRepository0.getAllUsers();
        org.junit.Assert.assertNull(user2);
        org.junit.Assert.assertNotNull(userList3);
    }

    @Test
    public void test086() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test086");
        org.example.users.Student student6 = new org.example.users.Student("", "RESEARCHER",
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]", "", "HEAD_COORDINATOR", "GUEST");
    }

    @Test
    public void test087() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test087");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        java.util.List<org.example.users.User> userList1 = cSVRepository0.getAllUsers();
        java.util.List<java.lang.String[]> strArrayList2 = cSVRepository0.getAllReservationRows();
        java.util.List<java.lang.String[]> strArrayList4 = cSVRepository0
                .getPaymentRowsByReservationId("HEAD_COORDINATOR - MANAGER () [AVAILABLE]");
        org.example.users.User user6 = cSVRepository0.findUserById("RESEARCHER");
        org.junit.Assert.assertNotNull(userList1);
        org.junit.Assert.assertNotNull(strArrayList2);
        org.junit.Assert.assertNotNull(strArrayList4);
        org.junit.Assert.assertNull(user6);
    }

    @Test
    public void test088() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test088");
        org.example.users.HeadCoordinator headCoordinator4 = org.example.users.HeadCoordinator.getInstance("",
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]", "RESEARCHER", "GUEST");
        org.junit.Assert.assertNotNull(headCoordinator4);
    }

    @Test
    public void test089() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test089");
        org.example.users.Student student6 = new org.example.users.Student("hi!", "GUEST", "MANAGER",
                "HEAD_COORDINATOR", "MANAGER", "hi!");
        java.lang.String str7 = student6.getUserType();
        java.lang.String str8 = student6.getUserType();
        student6.setEmail("GUEST");
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "STUDENT" + "'", str7, "STUDENT");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "STUDENT" + "'", str8, "STUDENT");
    }

    @Test
    public void test090() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test090");
        org.example.users.Guest guest6 = new org.example.users.Guest("MANAGER", "HEAD_COORDINATOR", "",
                "HEAD_COORDINATOR", "HEAD_COORDINATOR", "MANAGER");
        guest6.setIdNumber("hi!");
        org.example.users.UserDecorator userDecorator10 = new org.example.users.UserDecorator(
                (org.example.users.User) guest6, "hi!");
        java.lang.String str11 = userDecorator10.getDecorationType();
        java.lang.String str12 = userDecorator10.getCertificationNumber();
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "hi!" + "'", str11, "hi!");
        org.junit.Assert.assertNull(str12);
    }

    @Test
    public void test091() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test091");
        boolean boolean1 = org.example.auth.PasswordValidator.isValid("MANAGER");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test092() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test092");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        org.example.equipment.Equipment equipment4 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        equipment4.setProductStatistics("MANAGER");
        equipment4.setImagePath("hi!");
        cSVRepository0.updateEquipment(equipment4);
        org.example.equipment.Equipment equipment13 = new org.example.equipment.Equipment("MANAGER", "MANAGER",
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]");
        cSVRepository0.saveEquipment(equipment13);
        org.example.users.Guest guest21 = new org.example.users.Guest("MANAGER", "HEAD_COORDINATOR", "",
                "HEAD_COORDINATOR", "HEAD_COORDINATOR", "MANAGER");
        guest21.setIdNumber("hi!");
        org.example.users.UserDecorator userDecorator25 = new org.example.users.UserDecorator(
                (org.example.users.User) guest21, "hi!");
        java.lang.String str26 = userDecorator25.getCertificationNumber();
        java.lang.String str27 = userDecorator25.getName();
        userDecorator25.setApprovalStatus("");
        java.lang.String str30 = userDecorator25.getUserType();
        cSVRepository0.updateUser((org.example.users.User) userDecorator25);
        java.lang.String str32 = userDecorator25.getCertificationNumber();
        java.lang.String str33 = userDecorator25.getCertificationNumber();
        org.junit.Assert.assertNull(str26);
        org.junit.Assert.assertEquals("'" + str27 + "' != '" + "HEAD_COORDINATOR" + "'", str27, "HEAD_COORDINATOR");
        org.junit.Assert.assertEquals("'" + str30 + "' != '" + "GUEST" + "'", str30, "GUEST");
        org.junit.Assert.assertNull(str32);
        org.junit.Assert.assertNull(str33);
    }

    @Test
    public void test093() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test093");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        org.example.equipment.Equipment equipment4 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        equipment4.setProductStatistics("MANAGER");
        equipment4.setImagePath("hi!");
        cSVRepository0.updateEquipment(equipment4);
        org.example.equipment.Equipment equipment13 = new org.example.equipment.Equipment("MANAGER", "MANAGER",
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]");
        cSVRepository0.saveEquipment(equipment13);
        java.util.List<org.example.users.User> userList15 = cSVRepository0.getAllUsers();
        java.util.List<java.lang.String[]> strArrayList17 = cSVRepository0
                .getPaymentRowsByReservationId("HEAD_COORDINATOR");
        org.example.users.LabManager labManager24 = new org.example.users.LabManager("", "hi!", "", "", "hi!");
        java.lang.String str25 = labManager24.getName();
        org.example.equipment.Equipment equipment26 = null;
        java.time.LocalDateTime localDateTime27 = null;
        java.time.LocalDateTime localDateTime28 = null;
        org.example.reservation.Reservation reservation30 = new org.example.reservation.Reservation("",
                (org.example.users.User) labManager24, equipment26, localDateTime27, localDateTime28, (double) 0);
        double double31 = reservation30.getDeposit();
        java.time.LocalDateTime localDateTime32 = reservation30.getEndTime();
        java.time.LocalDateTime localDateTime33 = null;
        reservation30.setStartTime(localDateTime33);
        cSVRepository0.updateReservation(reservation30);
        org.junit.Assert.assertNotNull(userList15);
        org.junit.Assert.assertNotNull(strArrayList17);
        org.junit.Assert.assertEquals("'" + str25 + "' != '" + "hi!" + "'", str25, "hi!");
        org.junit.Assert.assertTrue("'" + double31 + "' != '" + 0.0d + "'", double31 == 0.0d);
        org.junit.Assert.assertNull(localDateTime32);
    }

    @Test
    public void test094() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test094");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("MANAGER", "MANAGER", (double) (byte) 1,
                "RESEARCHER", false);
        double double6 = payment5.getAmount();
        boolean boolean7 = payment5.isDeposit();
        org.junit.Assert.assertTrue("'" + double6 + "' != '" + 1.0d + "'", double6 == 1.0d);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test095() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test095");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        java.lang.String str4 = equipment3.getDescription();
        java.lang.String str5 = equipment3.toString();
        java.lang.String str6 = equipment3.toString();
        equipment3.setImagePath("MANAGER");
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "MANAGER" + "'", str4, "MANAGER");
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "HEAD_COORDINATOR - MANAGER () [AVAILABLE]" + "'", str5,
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "HEAD_COORDINATOR - MANAGER () [AVAILABLE]" + "'", str6,
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]");
    }

    @Test
    public void test096() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test096");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        org.example.equipment.Equipment equipment4 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        equipment4.setProductStatistics("MANAGER");
        equipment4.setImagePath("hi!");
        cSVRepository0.updateEquipment(equipment4);
        org.example.equipment.Equipment equipment13 = new org.example.equipment.Equipment("MANAGER", "MANAGER",
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]");
        cSVRepository0.saveEquipment(equipment13);
        java.util.List<java.lang.String[]> strArrayList16 = cSVRepository0.getPaymentRowsByReservationId("");
        org.example.users.LabManager labManager23 = new org.example.users.LabManager("", "hi!", "", "", "hi!");
        java.lang.String str24 = labManager23.getName();
        org.example.equipment.Equipment equipment25 = null;
        java.time.LocalDateTime localDateTime26 = null;
        java.time.LocalDateTime localDateTime27 = null;
        org.example.reservation.Reservation reservation29 = new org.example.reservation.Reservation("",
                (org.example.users.User) labManager23, equipment25, localDateTime26, localDateTime27, (double) 0);
        double double30 = reservation29.getDeposit();
        java.time.LocalDateTime localDateTime31 = null;
        reservation29.setStartTime(localDateTime31);
        cSVRepository0.updateReservation(reservation29);
        org.junit.Assert.assertNotNull(strArrayList16);
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "hi!" + "'", str24, "hi!");
        org.junit.Assert.assertTrue("'" + double30 + "' != '" + 0.0d + "'", double30 == 0.0d);
    }

    @Test
    public void test097() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test097");
        org.example.users.Faculty faculty6 = new org.example.users.Faculty("hi!", "FACULTY", "HEAD_COORDINATOR",
                "GUEST", "FACULTY", "GUEST");
    }

    @Test
    public void test098() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test098");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        org.example.equipment.Equipment equipment4 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        equipment4.setProductStatistics("MANAGER");
        equipment4.setImagePath("hi!");
        cSVRepository0.updateEquipment(equipment4);
        org.example.equipment.Equipment equipment13 = new org.example.equipment.Equipment("MANAGER", "MANAGER",
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]");
        cSVRepository0.saveEquipment(equipment13);
        java.util.List<java.lang.String[]> strArrayList16 = cSVRepository0.getPaymentRowsByReservationId("hi!");
        org.example.users.LabManager labManager22 = new org.example.users.LabManager("", "hi!", "", "", "hi!");
        java.lang.String str23 = labManager22.getName();
        java.lang.String str24 = labManager22.getUserType();
        java.lang.String str25 = labManager22.getUserType();
        java.lang.String str26 = labManager22.getIdNumber();
        java.lang.String str27 = labManager22.getName();
        labManager22.setName("FACULTY");
        java.lang.String str30 = labManager22.getUserType();
        cSVRepository0.updateUser((org.example.users.User) labManager22);
        org.junit.Assert.assertNotNull(strArrayList16);
        org.junit.Assert.assertEquals("'" + str23 + "' != '" + "hi!" + "'", str23, "hi!");
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "MANAGER" + "'", str24, "MANAGER");
        org.junit.Assert.assertEquals("'" + str25 + "' != '" + "MANAGER" + "'", str25, "MANAGER");
        org.junit.Assert.assertNull(str26);
        org.junit.Assert.assertEquals("'" + str27 + "' != '" + "hi!" + "'", str27, "hi!");
        org.junit.Assert.assertEquals("'" + str30 + "' != '" + "MANAGER" + "'", str30, "MANAGER");
    }

    @Test
    public void test099() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test099");
        org.example.users.Student student6 = new org.example.users.Student("hi!", "GUEST", "MANAGER",
                "HEAD_COORDINATOR", "MANAGER", "hi!");
        java.lang.String str7 = student6.getUserType();
        student6.setName("FACULTY");
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "STUDENT" + "'", str7, "STUDENT");
    }

    @Test
    public void test100() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test100");
        org.example.payment.Payment payment5 = new org.example.payment.Payment("MANAGER", "MANAGER", (double) (byte) 1,
                "RESEARCHER", false);
        payment5.setForfeited(true);
        java.lang.String str8 = payment5.getPaymentId();
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "MANAGER" + "'", str8, "MANAGER");
    }

    @Test
    public void test101() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test101");
        org.example.data.CSVRepository cSVRepository0 = new org.example.data.CSVRepository();
        org.example.equipment.Equipment equipment4 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        equipment4.setProductStatistics("MANAGER");
        equipment4.setImagePath("hi!");
        cSVRepository0.updateEquipment(equipment4);
        org.example.equipment.Equipment equipment13 = new org.example.equipment.Equipment("MANAGER", "MANAGER",
                "HEAD_COORDINATOR - MANAGER () [AVAILABLE]");
        cSVRepository0.saveEquipment(equipment13);
        org.example.equipment.EquipmentStatus equipmentStatus15 = equipment13.getStatus();
        java.lang.String str16 = equipment13.getDescription();
        org.junit.Assert.assertTrue(
                "'" + equipmentStatus15 + "' != '" + org.example.equipment.EquipmentStatus.AVAILABLE + "'",
                equipmentStatus15.equals(org.example.equipment.EquipmentStatus.AVAILABLE));
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "MANAGER" + "'", str16, "MANAGER");
    }

    @Test
    public void test102() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RandoopRegressionTest0.test102");
        org.example.equipment.Equipment equipment3 = new org.example.equipment.Equipment("HEAD_COORDINATOR", "MANAGER",
                "");
        java.lang.String str4 = equipment3.getDescription();
        java.util.List<java.lang.String> strList5 = equipment3.getTags();
        java.lang.String str6 = equipment3.getDescription();
        java.lang.String str7 = equipment3.getDescription();
        equipment3.setName("FACULTY");
        equipment3.setName("RESEARCHER");
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "MANAGER" + "'", str4, "MANAGER");
        org.junit.Assert.assertNotNull(strList5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "MANAGER" + "'", str6, "MANAGER");
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "MANAGER" + "'", str7, "MANAGER");
    }
}
